package com.web.controller.page.listing;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.admin.config.anno.ActiveUserPrincipal;
import com.common.dto.review.ReviewDTO;
import com.common.entity.account.Account;
import com.common.entity.merchant.Merchant;
import com.common.entity.order.OrderBundle;
import com.common.entity.order.OrderBundleCollection;
import com.common.entity.product.DeleteProductToken;
import com.common.entity.product.Parcel;
import com.common.entity.product.ParcelAdapter;
import com.common.entity.product.Product;
import com.common.entity.product.ProductGallery;
import com.common.entity.product.embeded.Measurement;
import com.common.entity.product.embeded.ShippingInfo;
import com.common.entity.product.embeded.Weight;
import com.common.exception.MerchantException;
import com.common.exception.ProductException;
import com.common.type.DistanceUnit;
import com.common.type.MassUnit;
import com.common.type.OrderIntentStatus;
import com.common.type.OrderStatus;
import com.common.type.ProductStatus;
import com.common.type.ShippingType;
import com.common.util.PageSearch;
import com.common.util.date.DateInterval;
import com.common.util.date.DateUtil;
import com.core.service.order.MerchantOrderIntentSerivce;
import com.core.service.order.OrderBundleService;
import com.core.service.product.DeleteProductTokenService;
import com.core.service.product.ParcelService;
import com.core.service.product.ProductGalleryService;
import com.core.service.product.ProductService;
import com.core.service.review.ReviewDTOSerivice;
import com.core.service.review.ReviewService;
import com.core.service.shippo.ShippoParcelService;
import com.web.advice.PageAdvice;
import com.web.advice.UriActionRequestMapping;
import com.web.advice.UriPageRequestMapping;
import com.web.controller.AbstractMerchantBaseController;
import com.web.controller.exception.global.GeneralResourceNotFoundException;
import com.web.response.JsonResponse;

@Controller
public class ListingDetailPageController 
	extends AbstractMerchantBaseController
implements IListingDetailAdvise 
{

	private static final Logger logger = LogManager.getLogger();

	
	
	@Autowired private ProductService productService;
	@Autowired private ProductGalleryService galleryService;

	@Autowired private ShippoParcelService shippoParcelService;
	@Autowired private ParcelService parcelService;

	@Autowired private ReviewService reviewService;
	@Autowired private ReviewDTOSerivice reviewDTOservice;
	@Autowired private DeleteProductTokenService tokenService;
	@Autowired private OrderBundleService orderBundleService;
	@Autowired private MerchantOrderIntentSerivce merchantOrderIntentService;
	
	
	/** :::
	 * <p>Display a listing in detail</p>
	 * 
	 * 
	 * ::: */
	@RequestMapping(value=UriPageRequestMapping.Merchant.Listing.LISTING_DETAIL
								, method = RequestMethod.GET)
	public String showPage(Model model
												, @ActiveUserPrincipal Account customer
												, @PathVariable Long productId) throws MerchantException 
	{
		try {
			System.out.println();
			logger.info("Lookup a product on a specific product Id by a merchant");
			final Merchant merchant = this.getAuthorizedMerchant(customer);
			List<ProductGallery> galleries = null;
			List<ReviewDTO> reviews = null;
			Long reviewSize = null;
			Product product = null;
			Parcel parcel = null;
			
			
			product = productService.getByMerchant(productId, merchant);
			//logger.info("Lookup product data");
			
			Assert.notNull(product, "Unable to retrieve product data");
			Assert.isTrue(!product.getStatus().equals(ProductStatus.DELETED)
						, "Invalid product status - it's ready marked as 'DELETED'");
			
			
			parcel = parcelService.getParcel(product);
			
			reviewSize = reviewService.countByProduct(product, null);
			reviews = reviewDTOservice.getByProduct(product, new PageSearch(0, 8));
			
			galleries = galleryService.getByProduct(product, true);
			product.setGallery(galleries);
		

			model.addAttribute("reviewSize", reviewSize);
			model.addAttribute("reviews", reviews);
			model.addAttribute("parcel", parcel);
			model.addAttribute("product", product);
			model.addAttribute("orderBundleCollection", getOrderBundleCollection(product));
			model.addAttribute("orderIntentRequestSize", getOrder(product));
			return PageAdvice.Merchant.Listing.LISTING_DETAIL;
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
			throw new GeneralResourceNotFoundException();
		}
	}
	
	// Count total order intent that the client has to process 
	private long getOrder(Product product) {
		OrderIntentStatus status = OrderIntentStatus.REQUESTING;
		DateInterval dateInterval = new DateUtil();
		dateInterval.setInterval(new Date(), -10, 7);
		return merchantOrderIntentService.countByProduct(product, status, dateInterval);
	}
	
	
	// Retrieve latest order bundle stats
	private OrderBundleCollection getOrderBundleCollection(Product product) {
		OrderStatus status = null;
		DateInterval dateInterval = null;
		Pageable page = null;
		long size = 0;
		List<OrderBundle> orderBundles = null;
		
		size = orderBundleService.countByProduct(product, status, dateInterval);
		if(size > 0) {
			page = new PageSearch(0, 1);
			orderBundles = orderBundleService.getByProduct(product, status, dateInterval, page);
		}
		
		return new OrderBundleCollection(size, orderBundles);
	}
	
	
	
		
	/** :::
	 * 
	 * <p>Request to mark the product as PUBLIC by a merchant</p>
	 * 
	 * {@link IListingDetailAdvise#activeListing(Account, Long)}
	 *
	 * ::: */
	@RequestMapping(value=UriActionRequestMapping.Merchant.Listing.ACTIVE_LISTING
			, method=RequestMethod.POST)
	@ResponseBody
	@Override
	public JsonResponse	activeListing(@ActiveUserPrincipal Account customer
															, @RequestParam Long _pid) throws MerchantException 
	{
		synchronized (this) {
			JsonResponse json = new JsonResponse();
			final Merchant merchant = this.getAuthorizedMerchant(customer);
			try {
				Product product = productService.getByMerchant(_pid, merchant);
				productService.makePublic(product);
				json.setState(true);
				json.setResult(product.getStatus());
			} catch(ProductException e) {
				json.setState(false);
			}	
			return json;
		}
	}


	
		
		
	/** :::
	 * 
	 * <p>Request by a merchant to mark the product as private</p>
	 * 
	 * {@link IListingDetailAdvise#deactiveListing(Account, Long)}
	 *
	 * ::: */
	@RequestMapping(value=UriActionRequestMapping.Merchant.Listing.DEACTIVE_LISTING
								, method=RequestMethod.POST)
	@ResponseBody
	@Override
	public JsonResponse deactiveListing(@ActiveUserPrincipal Account customer
																, @RequestParam Long _pid) 
												throws MerchantException
	{
		synchronized (this) {
			JsonResponse json = new JsonResponse();
			final Merchant merchant = this.getAuthorizedMerchant(customer);
			try {
				Product product = productService.getByMerchant(_pid, merchant);
				productService.makePrivate(product);
				json.setState(true);
				json.setResult(product.getStatus());
			} catch(ProductException e) {
				json.setState(false);
			}	
			return json;
		}
		
	}

	
	/** :::
	 * <p>Request by a merchant to delete the product</p>
	 * 
	 * {@link IListingDetailAdvise#deleteListing(Account, Long)}
	 * ::: */
	@RequestMapping(value=UriActionRequestMapping.Merchant.Listing.DELETE_LISTING
								, method=RequestMethod.POST)
	@ResponseBody
	@Override
	public JsonResponse deleteListing(@ActiveUserPrincipal Account customer
														, @RequestParam Long _pid) 
											throws MerchantException
	{
		JsonResponse json = new JsonResponse();
		synchronized (this) {
			try  {
				
				
				final Merchant merchant = this.getAuthorizedMerchant(customer);
				Product product = productService.getByMerchant(_pid, merchant);		
				
				Assert.notNull(product, "Unable to retrieve product of id: [" + _pid + "] by the merchant");
				
				DeleteProductToken token = tokenService.save(product);
				json.setState(true);
				json.setResult(UriPageRequestMapping.Merchant.Listing.LISTING_DELETE_CONFIRM + "?token=" + token.getToken());
		
			} 
			catch(IllegalArgumentException e) {
				json.setState(false);
			}
		}
		return json;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
		
	/** :::
	 * <p>Request by a merchant to update a product title</p>
	 * 
	 * {@link IListingDetailAdvise#updateTitle(Account, Long, String)}
	 * ::: */
	@RequestMapping(value=UriActionRequestMapping.Merchant.Listing.UPDATE_LISTING_TITLE
									, method=RequestMethod.POST)
	@ResponseBody
	@Override
	public JsonResponse updateTitle(@ActiveUserPrincipal Account customer
							,@RequestParam(defaultValue="0") Long _pid
							,@RequestParam(defaultValue="") String title) 
	{	
		synchronized (this) {
			JsonResponse json = new JsonResponse();
			try {
				
				// Retrieve the product on a product id by the merchant
				
				final Merchant merchant = getAuthorizedMerchant(customer);
				Product product = productService.getByMerchant(_pid, merchant);
				Assert.notNull(product, "[ERROR] Unable to retrieve the product");
				Assert.isTrue(title.length() > 0, "[ERROR] Title can't be empty");
				
				
				// Update the product title
				
				product.getBasicInfo().setTitle(title);
				productService.update(product);
				json.setState(true);
			} catch (MerchantException | IllegalArgumentException | ProductException e) {
				json.setState(false);
				json.setResult(e.getMessage());
			}	
			return json;
		}
	}
	
	

		
	/** :::
	 * <p>Update a new description for the item</p>
	 * 
	 * {@link IListingDetailAdvise#updateDescription}
	 * ::: */
	@RequestMapping(value=UriActionRequestMapping.Merchant.Listing.UPDATE_LISTING_DESCRIPTION
				, method=RequestMethod.POST)
	@ResponseBody
	@Override
	public JsonResponse updateDescription(@ActiveUserPrincipal Account customer
								,@RequestParam(defaultValue="0") Long _pid
								,@RequestParam(defaultValue="") String description) 
	{
		synchronized (this) {
			JsonResponse json = new JsonResponse();
			try {
				
				// Retrieve the product on a product_id by the merchant
				
				final Merchant merchant = getAuthorizedMerchant(customer);
				Product product = productService.getByMerchant(_pid, merchant);
				Assert.notNull(product, "[ERROR] Unable to retrieve the product");
				Assert.isTrue(description.length() > 0, "[ERROR] Description can't be empty");
				
				// Set new description to the product
				product.getBasicInfo().setDescription(description);
				productService.update(product);
				json.setState(true);
			} catch (MerchantException | IllegalArgumentException | ProductException e) {
				json.setState(false);
				json.setResult(e.getMessage());
			}	
			return json;
		}
	}
	

	
	
	
		
	/** :::
	 * <p>Request to update product image by a merchant</p>
	 * {@link IListingDetailAdvise#updateImage}
	 * ::: */
	/*@Deprecated
	@RequestMapping(value=UriActionRequestMapping.Merchant.Listing.UPDATE_LISTING_IMAGE
				, method=RequestMethod.POST)
	@ResponseBody
	@Override
	public JsonResponse updateImage(@ActiveUserPrincipal Account customer
							,@RequestParam(defaultValue="0") Long _pid
							,@RequestParam(required=false) MultipartFile _file) 
	{
		synchronized (this) {
			JsonResponse json = new JsonResponse();
			
			try {
				final Merchant merchant = getAuthorizedMerchant(customer);
				Product product = productService.getByMerchant(_pid, merchant);
				Assert.notNull(product, "[ERROR] Unable to retrieve the product");
				Assert.notNull(_file, "[ERROR] image cannot be null");
				
				
				if(!_file.isEmpty()) {
					String imagePath = s3UploadService.uploadProduct(_file);
					Assert.notNull(imagePath, "[ERROR] Unable to upload image");
					
					ProductGallery gallery = galleryService.findById(product.getImage().getId());
					galleryService.markAsDeactive(gallery);
					removeService.save(gallery.getImage());
					
					product.setImage(ProductGallery.create(imagePath));
					productService.update(product);
					json.setState(true);
				}
			} catch (MerchantException | IllegalArgumentException e) {
				json.setState(false);
			}	
			return json;
		}
	}*/
	
	
	
		
	/** :::
	 * <p>Update a new price to a product</p>
	 * {@link IListingDetailAdvise#updatePrice}
	 * ::: */
	@RequestMapping(value=UriActionRequestMapping.Merchant.Listing.UPDATE_LISTING_PRICE
				, method=RequestMethod.POST)
	@ResponseBody
	@Override
	public JsonResponse updatePrice(@ActiveUserPrincipal Account customer
							,@RequestParam(defaultValue="0") Long _pid
							,@RequestParam(defaultValue="0") double price) 
	{
		synchronized (this) {
			JsonResponse json = new JsonResponse();
			
			try {
				final Merchant merchant = getAuthorizedMerchant(customer);
				Product product = productService.getByMerchant(_pid, merchant);
				Assert.notNull(product, "[ERROR] Unable to retrieve the product");
				
				int p = (int)(price * 100);
				Assert.isTrue(p >= 50, "[ERROR] Price must be >= 50");
				
				product.getBasicInfo().setPrice(p);
				productService.update(product);
				json.setState(true);
			} catch (MerchantException | IllegalArgumentException | ProductException e) {
				json.setState(false);
				json.setResult(e.getMessage());
			}	
			return json;
		}
	}
	
	

	

	
	/** ::: 
	 * <h1>Update Subscription Rate</h1>
	 * 
	 * <p>Provide a new subscription rate on the product. This update will
	 * provide a new price to new subscribers but doesn't take any affect
	 * on the current subscribers.</p>
	 * 
	 * {@link IListingDetailAdvise#updateSubscriptionRate}
	 ** ::: */
	@RequestMapping(value=UriActionRequestMapping.Merchant.Listing.UPDATE_LISTING_RATE
				, method=RequestMethod.POST)
	@ResponseBody
	@Override
	public JsonResponse updateSubscriptionRate(@ActiveUserPrincipal Account customer
										,@RequestParam(defaultValue="0") Long _pid
										,@RequestParam(defaultValue="0") double rate) 
	{
		synchronized (this) {
			JsonResponse json = new JsonResponse();		
			try {
				final Merchant merchant = getAuthorizedMerchant(customer);
				Product product = productService.getByMerchant(_pid, merchant);
				Assert.notNull(product, "[ERROR] Unable to retrieve the product");
				
				int r = (int)(rate * 100);
				Assert.isTrue(r >= 0, "[ERROR] Subscription rate must be > 0");
				
				product.getSubscriptionInfo().setPercentSave(r);
				productService.update(product);
				json.setState(true);
				
			} catch (MerchantException | IllegalArgumentException | ProductException e) {
				json.setState(false);
				json.setResult(e.getMessage());
			}	
			return json;
		}	
	}

	
	
	

	/** :::
	 * <p>Request to update shipping by the merchant</p>
	 * 
	 * {@link IListingDetailAdvise#updateSubscriptionShipment(Account, Long, int)}
	 * ::: */
	@RequestMapping(value=UriActionRequestMapping.Merchant.Listing.UPDATE_LISTING_SHIPMENT
				, method=RequestMethod.POST)
	@ResponseBody
	@Override
	public JsonResponse updateSubscriptionShipment(@ActiveUserPrincipal Account customer
											,@RequestParam(defaultValue="0") Long _pid
											,@RequestParam(defaultValue="0") int nos) 
	{
		synchronized (this) {
			JsonResponse json = new JsonResponse();
			
			try {
				final Merchant merchant = getAuthorizedMerchant(customer);
				Product product = productService.getByMerchant(_pid, merchant);
				Assert.notNull(product, "[ERROR] Unable to retrieve the product");
				Assert.isTrue(nos >= 2, "[ERROR] Number of shipment must be >= 2");
				
				
				product.getSubscriptionInfo().setNos(nos);
				productService.update(product);
				json.setState(true);
				
			} catch (MerchantException | IllegalArgumentException | ProductException e) {
				json.setState(false);
				json.setResult(e.getMessage());
			}	
			return json;
		}	
	}

	
		
		
	/** :::
	 * <p>Request update product frequency by the merchant</p>
	 * 
	 * {@link IListingDetailAdvise#updateFrequency(Account, Long, int)}
	 * ::: */
	@RequestMapping(value=UriActionRequestMapping.Merchant.Listing.UPDATE_LISTING_FREQUENCY
				, method=RequestMethod.POST)
	@ResponseBody
	@Override
	public JsonResponse updateFrequency(@ActiveUserPrincipal Account customer
								,@RequestParam(required=false) Long _pid
								,@RequestParam int frequency) 
	{
		synchronized (this) {
			JsonResponse json = new JsonResponse();
			
			try {
				final Merchant merchant = getAuthorizedMerchant(customer);
				Product product = productService.getByMerchant(_pid, merchant);
				Assert.notNull(product, "[ERROR] Unable to retrieve the product");
				Assert.isTrue(frequency > 7, "[ERROR] frequency must be >= 7");
	
				product.getSubscriptionInfo().setFrequency(frequency);
				productService.update(product);
				json.setState(true);
				
			} catch (MerchantException | IllegalArgumentException | ProductException e) {
				json.setState(false);
				json.setResult(e.getMessage());
			}	
			return json;
		}	
	}
	
		
		
	/**
	 * <p>Request update product category</p>
	 * {@link IListingDetailAdvise#updateCategory(Account, Long, double)}
	 */
	/*@RequestMapping(value=UriActionRequestMapping.Merchant.Listing.UPDATE_LISTING_CATEGORY
								, method=RequestMethod.POST)
	@Deprecated
	@ResponseBody
	@Override
	public JsonResponse updateCategory(@ActiveUserPrincipal Account customer
									, @RequestParam(required=false) Long _pid
									, @RequestParam(defaultValue="0") Long _cId) {
		synchronized (this) {
			JsonResponse json = new JsonResponse();
			
			try {
				final Merchant merchant = getAuthorizedMerchant(customer);
				Product product = productService.getByMerchant(_pid, merchant);
				Assert.notNull(product, "[ERROR] Unable to retrieve the product");
				Assert.isTrue(_cId > 0, "[ERROR] Invalid Category");
	
				Category category = categoryService.findById(_cId);
				Assert.notNull(category, "[ERROR] Invalid Category");
				
				product.setCategory(category);
				productService.update(product);
				json.setState(true);
						
				
			} catch (MerchantException | IllegalArgumentException e) {
				json.setState(false);
				json.setResult(e.getMessage());
			}	
			return json;
		}	
	}
	*/
	
	
	
	
	
		
	/** :::
	 * <p>Update product parcel detail by the merchant</p>
	 *
	 *{@link IListingDetailAdvise#updateParcel(Account, Long, Float, String, Float, Float, Float, String)}
	 * ::: */
	@RequestMapping(value=UriActionRequestMapping.Merchant.Listing.UPDATE_PARCEL
			, method=RequestMethod.POST)
	@ResponseBody
	@Override
	public JsonResponse updateParcel(@ActiveUserPrincipal Account customer
								, @RequestParam Long _pid
								, @RequestParam(defaultValue="0") Float _weight
								, @RequestParam(defaultValue="oz") String _mUnit
								, @RequestParam(defaultValue="0") Float _length
								, @RequestParam(defaultValue="0") Float _width
								, @RequestParam(defaultValue="0") Float _height
								, @RequestParam(defaultValue="in") String _dUnit) 
	{
		synchronized (this) {
			JsonResponse json = new JsonResponse();
			
			try {

				final Merchant merchant = getAuthorizedMerchant(customer);
				Product product = productService.getByMerchant(_pid, merchant);
				Assert.notNull(product, "[ERROR] Unable to retrieve the product");
				
				
				Weight w = Weight.create(_weight, MassUnit.lookup(_mUnit));
				Measurement m = Measurement.create(_width, _height, _length, DistanceUnit.lookup(_dUnit));
				ParcelAdapter adapter= shippoParcelService.create(m, w);
				
				Parcel parcel = parcelService.save(product, adapter);
				
				Assert.notNull(parcel);
				json.setState(true);
			} catch(MerchantException | IllegalArgumentException e) {
				json.setState(false);
			}
			return json;
		}
	}
	
	
	
	

	/** :::
	 * <p>Update product shipping info</p>
	 * 
	 * {@link IListingDetailAdvise#updateShipping}
	 */
	@RequestMapping(value=UriActionRequestMapping.Merchant.Listing.UPDATE_SHIPPING
				, method=RequestMethod.POST)
	@ResponseBody
	@Override
	public JsonResponse updateShipping(@ActiveUserPrincipal Account customer
								, @RequestParam Long _pid
								, @RequestParam(required=false) String _type
								, @RequestParam(defaultValue="0") Double _cost) {

		synchronized (this) {
			JsonResponse json = new JsonResponse();
			try {

				final Merchant merchant = getAuthorizedMerchant(customer);
				Product product = productService.getByMerchant(_pid, merchant);
				Assert.notNull(product, "[ERROR] Unable to retrieve the product");

				ShippingInfo shippingInfo = ShippingInfo.create((int)(_cost * 100), ShippingType.lookup(_type));
				if(shippingInfo != null) {
					product.setShippingInfo(shippingInfo);
					productService.update(product);
					json.setState(true);
				}
			} catch(MerchantException | IllegalArgumentException | ProductException e) {
				json.setState(false);
			}
			return json;
		}
	}
		
	


}
