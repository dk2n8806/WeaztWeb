package com.web.controller.page.listing;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.admin.config.anno.ActiveUserPrincipal;
import com.common.dto.product.ProductTemplateDTO;
import com.common.entity.account.Account;
import com.common.entity.category.Category;
import com.common.entity.merchant.Merchant;
import com.common.exception.MerchantException;
import com.common.type.ProductStatus;
import com.common.util.PageSearch;
import com.common.util.date.DateInterval;
import com.core.service.product.ProductService;
import com.core.service.product.dto.ProductTemplateDTOService;
import com.web.advice.PageAdvice;
import com.web.advice.UriPageRequestMapping;
import com.web.controller.AbstractMerchantBaseController;

@Controller
public class ListingPageController extends AbstractMerchantBaseController 
{

	private static final Logger logger = LogManager.getLogger();
	
	@Autowired private ProductTemplateDTOService templateService;
	@Autowired private ProductService productService;
	
	
		
	/** :::
	 * <p>Retrieve a list of </p>
	 * {@link IListingAdvise#showPage(Model, Account, String, int)}
	 */
	@RequestMapping(value=UriPageRequestMapping.Merchant.Listing.LISTING
					, method=RequestMethod.GET)
	public String showPage(Model model
												, @ActiveUserPrincipal Account customer
												, @RequestParam(name="p", defaultValue="0") int pageNumber
												, @RequestParam(name="s", defaultValue="20") int pageSize) 
	{
		logger.info("show page");
		Category category = null;
		DateInterval dateInteval = null;
		List<ProductTemplateDTO> products = null;
		long listingSize = 0;
		ProductStatus productStatus = ProductStatus.PUBLIC;
		Pageable pageable = new PageSearch((pageNumber < 0) ? 0 : pageNumber, pageSize);
		
		try {
			final Merchant merchant = this.getAuthorizedMerchant(customer);

			listingSize = productService.countByMerchant(merchant, category, ProductStatus.PUBLIC, dateInteval);	

			if(listingSize > 0) {
				products = templateService.getByMerchant(merchant, category, productStatus, dateInteval, pageable);
			}
			
		} catch(MerchantException e) {
			e.printStackTrace();
		}

		model.addAttribute("size", listingSize);
		model.addAttribute("page", pageable);
		model.addAttribute("products", products);
		return PageAdvice.Merchant.Listing.LISTING;
	}

	
	

}
