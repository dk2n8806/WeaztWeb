package com.web.controller.page.listing;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.admin.config.anno.ActiveUserPrincipal;
import com.common.entity.account.Account;
import com.common.entity.category.Category;
import com.common.entity.category.GroupCategory;
import com.common.entity.category.group.Beverage;
import com.common.entity.merchant.Merchant;
import com.common.entity.product.Parcel;
import com.common.entity.product.ParcelAdapter;
import com.common.entity.product.Product;
import com.common.entity.product.ProductGallery;
import com.common.entity.product.embeded.BasicInfo;
import com.common.entity.product.embeded.Measurement;
import com.common.entity.product.embeded.ShippingInfo;
import com.common.entity.product.embeded.SubscriptionInfo;
import com.common.entity.product.embeded.Weight;
import com.common.entity.support.embeded.ImagePath;
import com.common.exception.MerchantException;
import com.common.type.DistanceUnit;
import com.common.type.MassUnit;
import com.common.type.ShippingType;
import com.core.amazon.bucket.IS3ProductUploadService;
import com.core.service.category.CategoryService;
import com.core.service.category.GroupCategoryService;
import com.core.service.product.ParcelService;
import com.core.service.product.ProductService;
import com.core.service.shippo.ShippoParcelService;
import com.mysema.commons.lang.Assert;
import com.web.advice.PageAdvice;
import com.web.advice.UriActionRequestMapping;
import com.web.advice.UriManager;
import com.web.advice.UriPageRequestMapping;
import com.web.controller.AbstractMerchantBaseController;


/** 
 * 
 * @author dk2n_
 *
 */
@Controller
public class NewListingPageController 
extends AbstractMerchantBaseController implements INewListingAdvise {

	private static final Logger logger = LogManager.getLogger();
	
	@Autowired private ProductService productService;
	@Autowired private CategoryService categoryService;
	@Autowired private GroupCategoryService groupCategoryService;
	@Autowired private ParcelService parcelService;
	@Autowired private IS3ProductUploadService s3ProductUpload;
	@Autowired private ShippoParcelService shippoParcelService;
		
		
	/** :::
	 * <p>Display new listing page (form)</p>
	 * 
	 * {@link INewListingAdvise#showPage(Model, Account)}
	 * ::: */
	@RequestMapping(value=UriPageRequestMapping.Merchant.Listing.NEW_LISTING
								, method=RequestMethod.GET)
	public String showPage(Model model, @ActiveUserPrincipal Account customer) throws MerchantException {
		System.out.println();
		logger.info("Request to display new_listing page");
		Merchant merchant = getAuthorizedMerchant(customer);
		if(merchant == null)
			throw new MerchantException("merchant account is required");

		List<GroupCategory> groups = groupCategoryService.getList();
		
		for(GroupCategory group : groups) {
			String var = "";
			if(group instanceof Beverage) {
				var = "beverage";
				model.addAttribute(var, categoryService.getByGroup(group, true));
			}
//			if(group instanceof HealhAndBeauty) var = "hb";
//			if(group instanceof Food) var = "food";
//			if(group instanceof Household) var="house";
		}
		
		return PageAdvice.Merchant.Listing.NEW_LISTING;
	}

	

	

	


	/** :::
	 * <p>Request by  a merchant to list a new product</p>
	 * 
	 * {@link INewListingAdvise#createNewlListing}
	 * */
	@RequestMapping(value=UriActionRequestMapping.Merchant.Listing.CREATE_LISTING
								, method=RequestMethod.POST
								, headers="content-type=multipart/*")
	@Override
	public String createNewListing(@ActiveUserPrincipal Account customer
										, @RequestParam(name="_image") 		MultipartFile _mainImage
										, @RequestParam(name="_thumb", required=false)		MultipartFile[] _thumbImages
										, @RequestParam(name="_category") 	Long 		_categoryId
										, @RequestParam 					String 		_title
										, @RequestParam 					String 		_description
										, @RequestParam 					Double 		_price
										, @RequestParam(defaultValue="0")   Double 		_shipping
										, @RequestParam(defaultValue="flat")String		_shipType
										, @RequestParam(defaultValue="0") 	Float 		_weight
										, @RequestParam(defaultValue="oz") 	String		_mUnit
										, @RequestParam(defaultValue="0") 	Float 		_height
										, @RequestParam(defaultValue="0") 	Float 		_width
										, @RequestParam(defaultValue="0") 	Float 		_length
										, @RequestParam(defaultValue="in") 	String 		_dUnit
										, @RequestParam(defaultValue="2") 	Integer 	_shipment			// number of shipment
										, @RequestParam(defaultValue="0") 	Double 		_saveRate
										, @RequestParam(defaultValue="7") 	Integer 	_freq
	)
												throws MerchantException 
	{	
		try {
			final Merchant merchant = getAuthorizedMerchant(customer);
			
			Category category = categoryService.findById(_categoryId);
			
			// ::: Handle basic info of the product
			BasicInfo basicInfo = BasicInfo.create((int)(_price * 100), _title, _description);
			SubscriptionInfo subscriptionInfo = SubscriptionInfo.create(_shipment, (int)(_saveRate * 100), _freq);
			ShippingInfo shippingInfo = ShippingInfo.create((int) (_shipping * 100), ShippingType.lookup(_shipType));
			Assert.notNull(basicInfo, "Product basic info is required");
			Assert.notNull(subscriptionInfo, "Product subscription is required");
			Assert.notNull(shippingInfo, "Product shipping info is required");
			

			
			
			Weight weight = Weight.create(_weight, MassUnit.lookup(_mUnit));
			Measurement measurement = Measurement.create(_width, _height, _length, DistanceUnit.lookup(_dUnit));
			//System.out.println(weight);
			//System.out.println(measurement);
			Assert.notNull(weight, "weight is required");
			Assert.notNull(measurement, "Measurement is required");
			ParcelAdapter parcelAdapter = shippoParcelService.create(measurement, weight);
			Assert.notNull(parcelAdapter, "Parcel adapter is required");
			
			
			// ::: Handle product display image and thumb nails
			ImagePath displayImage = getDisplayImage(_mainImage);
			List<ProductGallery> gallery = getThumbs(_thumbImages);
			Product product = productService.save(merchant, category, basicInfo, subscriptionInfo, shippingInfo, displayImage, gallery );
			Assert.notNull(product, "Unable to create a new product");
			
			
			Parcel parcel = parcelService.save(product, parcelAdapter );
			Assert.notNull(parcel, "Unable to create parcel object for the product");
			
			
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
		}
		
		return new UriManager().setRequest(UriPageRequestMapping.Merchant.Listing.LISTING)
								.getRedirectRequest();
	}
	
	
	private ImagePath getDisplayImage(MultipartFile file) {
		String path = s3ProductUpload.uploadProduct(file);
		return ImagePath.create(path );
	}
	
	
	private List<ProductGallery> getThumbs(MultipartFile[] _files) {
		List<ProductGallery> imagePaths = new ArrayList<>();
		for(MultipartFile _file : _files) {
			if(!_file.isEmpty()) {
				String imagePath = s3ProductUpload.uploadProduct(_file);
				imagePaths.add(ProductGallery.create(ImagePath.create(imagePath)));
			}
		}
		return imagePaths;
		
	}
	
	
}
