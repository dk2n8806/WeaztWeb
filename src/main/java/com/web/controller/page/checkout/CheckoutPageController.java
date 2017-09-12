package com.web.controller.page.checkout;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.admin.config.anno.ActiveUserPrincipal;
import com.admin.resource.WeaztAppResourceKeys;
import com.common.adapter.shippo.AddressAdapterAttribute;
import com.common.entity.account.Account;
import com.common.entity.account.SimpleShipping;
import com.common.entity.address.Address;
import com.common.entity.order.OrderIntent;
import com.common.entity.product.Product;
import com.common.entity.promo.ClaimedPromotion;
import com.common.entity.promo.Promotion;
import com.common.entity.stripe.StripeCustomer;
import com.common.exception.OrderException;
import com.common.exception.ProductNotFoundException;
import com.common.exception.SubscriptionException;
import com.common.type.ProductStatus;
import com.common.type.USAStates;
import com.core.service.account.SimpleShippingService;
import com.core.service.checkout.CheckoutService;
import com.core.service.merchant.MerchantService;
import com.core.service.product.ProductService;
import com.core.service.promo.ClaimedPromotionService;
import com.core.service.promo.PromotionService;
import com.core.service.shippo.ShippoAddressService;
import com.core.service.shippo.ShippoQuoteService;
import com.core.service.stripe.StripeCreatorService;
import com.core.service.stripe.StripeCustomerService;
import com.web.advice.PageAdvice;
import com.web.advice.UriActionRequestMapping;
import com.web.advice.UriPageRequestMapping;
import com.web.controller.exception.global.GeneralResourceNotFoundException;
import com.web.response.JsonResponse;


@Controller
public class CheckoutPageController implements ICheckoutPageAdvise {

	private static final Logger logger = LogManager.getLogger();
	
	@Autowired private ProductService productService;
	
	@Autowired private SimpleShippingService simpleShippingService;
	@Autowired private CheckoutService checkoutService;
	
	@Autowired private MerchantService merchantService;
	
	
	@Autowired private StripeCustomerService stripeCustomerService;
	@Autowired private StripeCreatorService stripeCreatorService;
	
	@Autowired private ShippoAddressService shippoAddressService;
	
	@Autowired private ShippoQuoteService shippoQuoteService;
	
	
	@Autowired private PromotionService promotionService;
	@Autowired private ClaimedPromotionService claimedPromotionService;
	
	
	
	@RequestMapping(value=UriPageRequestMapping.User.Checkout.CHECKOUT_FAIL)
	public String showCheckoutFailPage(Model model) {
		System.out.println();
		logger.error("Show Checkout fail page");
		return PageAdvice.Checkout.CHECKOUT_FAIL_PAGE;
	}
	
	
	
	
	
	/****************************************************************************
	 * Request to show checkout-error page
	 * 
	 * {@link ICheckoutPageAdvise#showCheckoutErrorPage(Model)}
	 */
	@RequestMapping(value=UriPageRequestMapping.User.Checkout.CHECKOUT_ERROR)
	public String showCheckoutErrorPage(Model model)  {
		System.out.println();
		logger.info("Show checkout error page");
		return PageAdvice.Checkout.CHECKOUT_ERROR_PAGE;
	}
	


	
	/** :::
	 * Display error page when the client try to access the page incorrectly.
	 * 
	 * {@link ICheckoutPageAdvise#showCheckoutPage(Model, Account)}
	 */
	@RequestMapping(value=UriPageRequestMapping.User.Checkout.CHECKOUT
			  , method=RequestMethod.GET)
	@Override
	public void showCheckoutPage(Model model
							, @ActiveUserPrincipal Account account)
	{
		throw new GeneralResourceNotFoundException();
	}
	
	
	
		
	/** :::
	 * Display check out page on a product
	 * 
	 * {@link ICheckoutPageAdvise#showCheckoutPage(Model, Account, Long)}
	 * 
	 */
	@Override
	@RequestMapping(value=UriPageRequestMapping.User.Checkout.CHECKOUT
				  , method=RequestMethod.POST)
	public String showCheckoutPage(Model model
					, @ActiveUserPrincipal Account account
					, @RequestParam(defaultValue="0") long _pid) 
							throws ProductNotFoundException 
	{
		System.out.println();
		logger.info("Show checkout page");
		
		Product product = this.getProduct(_pid);
		model.addAttribute("product", product);
		model.addAttribute("address", simpleShippingService.getPrimaryAddress(account));
		model.addAttribute("creditcard", stripeCustomerService.getDefaultByAccount(account));
		model.addAttribute("shippingCost", shippoQuoteService.getShippingCost(account, product));
		model.addAttribute("taxRate", merchantService.getTaxRate(product.getMerchant()));
		model.addAttribute("subscriptionInfo", product.getSubscriptionInfo());
		model.addAttribute("stripeKey", WeaztAppResourceKeys.StripeResource.STRIPE_PUBLIC_KEY);
		return PageAdvice.Checkout.CHECKOUT_PAGE;
	}
	
	
 	

	
	
	/*
	 * Lookup product_data
	 */
	private Product getProduct(Long productId) throws ProductNotFoundException {
		try {
			Assert.isTrue(productId > 0, "product id must > 0");	
			Product  product = productService.findById(productId);
		
			Assert.notNull(product, "product cannot be null");
			Assert.isTrue(product.getStatus().equals(ProductStatus.PUBLIC), "Invalid produc status");
			return product;
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
			throw new ProductNotFoundException("Unable to lookup produc data");
		}
	}
	
	
	


		
	
	
	/**
	 * <p>Request to checkout a product</p>
	 * {@link ICheckoutPageAdvise#processCheckout(Account, long, int, int)}
	 */
	@RequestMapping(value=UriActionRequestMapping.User.Checkout.CHECKOUT_REQUEST
					, method=RequestMethod.POST)
	@Override
	@ResponseBody
	public JsonResponse processCheckout(@ActiveUserPrincipal Account account
																	, @RequestParam long _pid
																	, @RequestParam(defaultValue="7") int _freq
																	, @RequestParam(defaultValue="1") int _nos
																	, @RequestParam(name="promo", required=false) String promotionCode) 
	{
		JsonResponse jsonResponse = new JsonResponse();
		try {
			Assert.isTrue(_freq > 0, "Deliver frequency cant be less than 0");
			Assert.isTrue(_nos > 0, "Number of subscription can't be less than 0");
			
			Product product = this.getProduct(new Long(_pid));
			
			SimpleShipping address = simpleShippingService.getPrimaryByAccount(account);
			Assert.notNull(address, "Please update your account shipping");
			StripeCustomer stripe = stripeCustomerService.getDefaultByAccount(account);
			Assert.notNull(stripe, "Please update your payment method");
			
			
			Promotion promotion = getPromotion(account, promotionCode);
			OrderIntent order = checkoutService.checkout(account, product, _freq, _nos, promotion);
			jsonResponse.setState(true);
			jsonResponse.setResult(UriPageRequestMapping.User.Checkout.CHECKOUT_CONFIRM 
									+ "?_sid=" + order.getSubscription().getId());
		} 
		catch (ProductNotFoundException | OrderException | SubscriptionException e) {
			logger.error("Unable to generate subscription");
			e.printStackTrace();
			jsonResponse.setState(false);
		} 
		catch(IllegalArgumentException | NullPointerException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			jsonResponse.setState(false);
			jsonResponse.setResult(e.getMessage());
		}
		return jsonResponse;
	}


	private Promotion getPromotion(Account account, String code) {
		Promotion promotion = promotionService.getByCode(code);
//		ClaimedPromotion claimedPromotion = claimedPromotionService.save(account, promotion);
//		if(claimedPromotion == null) {
//			return null;
//		}
		return promotion;
	}
	

	
	
	/** :::
	 * Request to update promotion by the client
	 */
	@RequestMapping(value=UriActionRequestMapping.User.Checkout.CHECKOUT_UPDATE_PROMOTION
					, method=RequestMethod.POST)
	@ResponseBody
	public JsonResponse updatePromotion(@ActiveUserPrincipal Account account, @RequestParam String code) {
		synchronized (this) {
			JsonResponse jsonResponse = new JsonResponse();
			// Verify if there is a promotion
			logger.info("code: " + code);
			Promotion promotion = promotionService.getByCode(code);
			logger.info(promotion);
			if(promotion != null && promotion.isAppliable()) {
				// Determine if the client has claimed the promotion
//				ClaimedPromotion claimed = claimedPromotionService.getByAccount(account, promotion);
//				if(claimed == null) {
//				}
				
				logger.info("good promotion");
				
				jsonResponse.setState(true);
				jsonResponse.setResult(promotion);
			}
			
			return jsonResponse;
		}
	}


	/** :::
	 * <p>Update a new shipping info for the account, and set the new shipping info as primary</p>
	 * 
	 * {@link ICheckoutPageAdvise#updateCheckoutAddress} 
	 */
	@Override
	@RequestMapping(value=UriActionRequestMapping.User.Checkout.CHECKOUT_UPDATE_SHIPPING
					, method=RequestMethod.POST)
	@ResponseBody
	public JsonResponse updateCheckoutAddress(@ActiveUserPrincipal Account account
								, @RequestParam long _pid
								, @RequestParam(defaultValue="") String _name
								, @RequestParam(defaultValue="") String _street
								, @RequestParam(defaultValue="") String _city
								, @RequestParam(defaultValue="") String _state
								, @RequestParam(defaultValue="") String _zipcode) throws ProductNotFoundException 
	{
		System.out.println();
		logger.info("Update name and address on account_profile");
		JsonResponse jsonResponse = new JsonResponse();
		try {
			
			synchronized (this) {

				Product product = this.getProduct(_pid);
				
				Address address = shippoAddressService.create(
						new AddressAdapterAttribute(_name, _street, _city
								, USAStates.lookup(_state), Address.COUNTRY_DEFAULT, _zipcode));
				// Create a new simple shipping_info for the account
				SimpleShipping shipping = simpleShippingService.save(account, address);
				Assert.notNull(shipping, "Unable to retrieve account_profile");
				
				// Set the shipping_info as primary
				simpleShippingService.setPrimary(shipping);
				

				Integer shippingCost = shippoQuoteService.getShippingCost(account, product);
				
				
				jsonResponse.setResult(shippingCost);
				jsonResponse.setState(true);
			} 
		} catch(NullPointerException | IllegalArgumentException e) {
			logger.error("Unable to update address of the account: [" + account + "]");
			jsonResponse.setState(false);
		}
		return jsonResponse;
	}








		
	/** :::
	 * <p>Update a new payment method for the account and set the method as default</p>
	 * 
	 * {@link ICheckoutPageAdvise#updatePaymentInfo(Account, String)}
	 */
	@Override@RequestMapping(value=UriActionRequestMapping.User.Checkout.CHECKOUT_UPDATE_PAYMENT
			, method=RequestMethod.POST)
	@ResponseBody
	public JsonResponse updatePaymentInfo(@ActiveUserPrincipal Account account
										, @RequestParam(defaultValue="") String _tokenId)
	{
		System.out.println();
		logger.info("Update stripe_account on account");
		JsonResponse jsonResponse = new JsonResponse();
		try {
			Assert.isTrue(!_tokenId.equals(""), "Token can't be empty");
			synchronized (this) {
				StripeCustomer stripeCustomer = 
						stripeCreatorService.createCustomerByCardToken(account, _tokenId);
				
				Assert.notNull(stripeCustomer
						, "Unable to create/save a new stripe_account for"
								+ " the account: [" + account.getId() + "]"
								+ " - with token: [" + _tokenId + "]");
				
				logger.info("Create and update new stripe_account for the account");
				stripeCustomerService.setDefaultByAccount(stripeCustomer.getId(), account);
				jsonResponse.setState(true);
				
			}
		} catch(NullPointerException | IllegalArgumentException e) {
			logger.error("Unable to create a new stripe_account for the account: [" + account.getId() + "]");
			logger.error(e.getMessage());
			jsonResponse.setState(false);
		
		}
		return jsonResponse;
	}
}
