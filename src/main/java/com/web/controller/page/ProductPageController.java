package com.web.controller.page;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.admin.config.anno.ActiveUserPrincipal;
import com.common.dto.product.ProductTemplateDTO;
import com.common.dto.review.ReviewDTO;
import com.common.entity.account.Account;
import com.common.entity.product.Product;
import com.common.entity.tracking.ProductPageTracker;
import com.common.exception.MerchantNotFoundException;
import com.common.exception.ProductNotFoundException;
import com.common.type.ProductStatus;
import com.common.util.PageSearch;
import com.common.util.date.DateInterval;
import com.core.service.product.ProductGalleryService;
import com.core.service.product.ProductService;
import com.core.service.product.dto.ProductTemplateDTOService;
import com.core.service.review.ReviewDTOSerivice;
import com.core.service.review.ReviewService;
import com.core.service.subscriber.RecentVisitedService;
import com.core.service.tracker.PageTrackerService;
import com.web.advice.PageAdvice;
import com.web.advice.UriPageRequestMapping;

@Controller
public class ProductPageController
{

	private static final Logger logger = LogManager.getLogger();
	
	
	@Autowired private ProductGalleryService productGalleryService;
	@Autowired private ProductService productService;
	@Autowired private ProductTemplateDTOService templateService;

//	@Autowired private ReviewService reviewService;
	@Autowired private ReviewDTOSerivice reviewDTOService;
	@Autowired private ReviewService reviewService;

	@Autowired private PageTrackerService pageTrackerService;
	@Autowired private RecentVisitedService recentVisitedService;

	
	/**
	 * <p>Display a specified product</p>
	 * 
	 * {@link IProductPageAdvise#displayProduct(Model, long)}
	 * @throws ProductNotFoundException 
	 * @throws MerchantNotFoundException 
	 */
	@RequestMapping(value=UriPageRequestMapping.Shop.PRODUCT
			      , method=RequestMethod.GET)
	public String displayProduct(Model model
							, @ActiveUserPrincipal Account account
							, @PathVariable long _pid
							, HttpServletRequest request) 
									throws ProductNotFoundException, MerchantNotFoundException 
			
	{
		System.out.println();
		logger.info("Request to display a product");
		if(_pid <= 0) {
			throw new ProductNotFoundException("Invalid product_id");
		}

		Product product = this.getProduct(_pid);
		List<ReviewDTO> reviews = null;
		final ProductStatus status = ProductStatus.PUBLIC;
		final DateInterval dateInterval = null;
		

		product.setGallery(productGalleryService.getByProduct(product, true));

		
		List<ProductTemplateDTO> similarTemplates 
					= templateService.getTemplates(
							product.getCategory(), status, dateInterval, new PageSearch(0, 5));

		
		// Count total product of the merchant
//		long totalCount = productService.countProductByMerchant(
//										merchant, null, status, dateInterval);
//		
		
		// Retrieve a list of reviews on the merchant
		long reviewSize = reviewService.countByProduct(product, null);
		if(reviewSize > 0) {
			reviews = reviewDTOService.getByProduct(product, new PageSearch(0, 10));	
		}
		
		
		// Store the product for later visit
		recentVisitedService.save(account, product);
		
		// Track page movement
		pageTrackerService.save(ProductPageTracker.create(account, request.getRemoteAddr()));
		model.addAttribute("similarTemplates", similarTemplates);
		model.addAttribute("product", product);
		model.addAttribute("reviewSize", reviewSize);
		model.addAttribute("reviews", reviews);
		return PageAdvice.Shop.ITEM;
	}
	
	
		
	/********************************************
	 * 
	 * @param productId
	 * @return
	 * @throws ProductNotFoundException
	 */
	private Product getProduct(Long productId) throws ProductNotFoundException {
		try {
			Assert.isTrue(productId > 0
					, "Invalid product_id (Id must greater than 0)");	
			Product  product = productService.findById(productId);
			
			Assert.notNull(product, "Unable to find product with id [" + productId + "]");
			Assert.isTrue(product.getStatus().equals(ProductStatus.PUBLIC),
							"Invalid product_status: [" + product.getStatus() + "]");
			
			return product;
		} catch(IllegalStateException | IllegalArgumentException e) {
			e.printStackTrace();
			throw new ProductNotFoundException(e.getMessage());
		}
	}
	
}
