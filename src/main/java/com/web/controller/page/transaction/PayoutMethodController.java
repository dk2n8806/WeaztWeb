package com.web.controller.page.transaction;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.admin.config.anno.ActiveUserPrincipal;
import com.admin.resource.WeaztAppResourceKeys;
import com.common.adapter.stripe.StripeTos;
import com.common.entity.account.Account;
import com.common.entity.merchant.Merchant;
import com.common.entity.stripe.StripeAccount;
import com.common.entity.stripe.StripeStatus;
import com.common.exception.MerchantException;
import com.common.util.PageSearch;
import com.core.service.stripe.StripeAccountService;
import com.core.service.stripe.StripeCreatorService;
import com.web.advice.PageAdvice;
import com.web.advice.UriActionRequestMapping;
import com.web.advice.UriPageRequestMapping;
import com.web.controller.AbstractMerchantBaseController;
import com.web.response.JsonResponse;


@Controller
public class PayoutMethodController extends AbstractMerchantBaseController
implements IPayoutMethodAdvise{
	
	private static final Logger log = LogManager.getLogger();

	@Autowired private StripeAccountService stripeAccountService;
	@Autowired private StripeCreatorService stripeCreatorService;

	
/****************************************************************
 * {@link IPayoutMethodAdvise#showPayoutMethods(Model, Account)}
 * 
 * Show all payout method to the account
 * 
 * *************************************************************/
	@Override
	@RequestMapping(value=UriPageRequestMapping.Transaction.PAYOUT
					, method=RequestMethod.GET)
	public String showPayoutMethods(Model model
					, @ActiveUserPrincipal Account customer) {

		System.out.println();
		log.info("Display a list of payout_methods");
		try {
			final Merchant merchant = getAuthorizedMerchant(customer);
			final StripeStatus status = StripeStatus.ACTIVE;
			List<StripeAccount> stripes = new ArrayList<StripeAccount>();
			long count = stripeAccountService.countByMerchant(merchant, status);
			if(count > 0) {
				Pageable pageable = new PageSearch(0, count);
				stripes = stripeAccountService.getByMerchant(merchant, status, pageable );
			}
		
			StripeAccount stripeDefault = stripeAccountService.getDefaultByMerchant(merchant);
			if(stripeDefault != null) {
				count++;
				stripes.add(0, stripeDefault);
			}
			model.addAttribute("stripes", stripes);
			model.addAttribute("count", count);
			model.addAttribute("stripeKey", WeaztAppResourceKeys.StripeResource.STRIPE_PUBLIC_KEY);
		} 
		catch (MerchantException e) {
			log.error("The account is not merchant_account");
			e.printStackTrace();
		}		
		return PageAdvice.Transaction.PAYOUT;
	}
	

	
	
	
/****************************************************************
 * Add a new payout method by a merchant
 * 
 * {@link IPayoutMethodAdvise#addNewMethod(Account, String, HttpServletRequest)}
 * 
 * *************************************************************/
	@Override
	@RequestMapping(value=UriActionRequestMapping.Transaction.CREATE_NEW_PAYOUT
					, method=RequestMethod.POST)
	@ResponseBody
	public JsonResponse	addNewMethod(@ActiveUserPrincipal Account customer
									, @RequestParam(defaultValue="") String _tokenId
									, HttpServletRequest request)
		throws MerchantException
	{
		System.out.println();
		log.info("Customer request to add a new payment method");
		JsonResponse jsonResponse = new JsonResponse();

		final Merchant merchant = getAuthorizedMerchant(customer);
		String tosIp = request.getRemoteAddr();
		StripeTos stripeTos = new StripeTos(tosIp, new Date());
		StripeAccount stripe = stripeCreatorService.createMerchantByCardToken(
														merchant, stripeTos , _tokenId);
		
		if(stripe != null) {
			jsonResponse.setState(true);
		} else {
			jsonResponse.setState(false);
		}
		return jsonResponse;
	}
	

	
	
	
	
	

/****************************************************************
 * {@link IPayoutMethodAdvise#deleteMethod(Account, Long)}
 * 
 * Delete a payout method by a merchant
 * 
 * *************************************************************/
	@Override
	@RequestMapping(value=UriActionRequestMapping.Transaction.DELETE_A_PAYOUT
					, method=RequestMethod.POST)
	@ResponseBody
	public JsonResponse	deleteMethod(@ActiveUserPrincipal Account customer
						, @RequestParam(name="_id") Long _stripeId) 
										throws MerchantException
	{
		System.out.println();
		log.info("Customer request to remove a payment method " + _stripeId);
		JsonResponse jsonResponse = new JsonResponse();

		final Merchant merchant = getAuthorizedMerchant(customer);
		
		StripeAccount stripe = stripeAccountService.getByMerchant(merchant, _stripeId);
		try {
			Assert.notNull(stripe, "Unable to retrieve the stripe_account with id [" + _stripeId + "]");
			stripeAccountService.markAsDelete(stripe);
			jsonResponse.setState(true);
		} catch(IllegalArgumentException e){
			log.error(e.getMessage());
			jsonResponse.setState(false);
		}
		return jsonResponse;
	}
	

	
	


/****************************************************************
 * {@link IPayoutMethodAdvise#setDefaultMethod(Account, Long)}
 * 
 * Set a payout method by a merchant
 * 
 * *************************************************************/
	@Override
	@RequestMapping(value=UriActionRequestMapping.Transaction.SET_DEFAULT_A_PAYOUT
					, method=RequestMethod.POST)
	@ResponseBody
	public JsonResponse	setDefaultMethod(@ActiveUserPrincipal Account customer
							, @RequestParam(name="_id", defaultValue="0") Long _stripeId) 
									throws MerchantException
	{
		System.out.println();
		log.info("Customer request to set a payment method as default");
		JsonResponse jsonResponse = new JsonResponse();

		final Merchant merchant = getAuthorizedMerchant(customer);

		StripeAccount stripe = stripeAccountService.getByMerchant(merchant, _stripeId);
		
		try {
			Assert.notNull(stripe, "Unable to retrieve the stripe_account with id [" + _stripeId + "]");
			System.out.println("status: " + stripe.getStatus());
			stripeAccountService.markAsDefault(stripe);
			jsonResponse.setState(true);
		} catch(IllegalArgumentException e){
			log.error(e.getMessage());
			jsonResponse.setState(false);
		}
		return jsonResponse;
	}
}
