package com.web.controller.page.listing;

import java.util.ArrayList;
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
import com.common.dto.product.DeletedProductTemplateDTO;
import com.common.entity.account.Account;
import com.common.entity.merchant.Merchant;
import com.common.exception.MerchantException;
import com.common.util.PageSearch;
import com.core.service.product.DeletedProductService;
import com.core.service.product.dto.DeletedProductTemplateDTOService;
import com.web.advice.PageAdvice;
import com.web.advice.UriPageRequestMapping;
import com.web.controller.AbstractMerchantBaseController;


/**
 * @author dk2n_
 *
 */
@Controller
public class DeletedListingPageController 
	extends AbstractMerchantBaseController 
{

	private static final Logger logger = LogManager.getLogger();
	
	@Autowired private DeletedProductTemplateDTOService templateDTOService;
	@Autowired private DeletedProductService deletedProductService;
	
		
	/** :::
	 * Display a list of products deleted by the client
	 * 
	 * {@link IHistoryAdvise#showPage(Model, Account)}
	 * ::: */
	@RequestMapping(value=UriPageRequestMapping.Merchant.Listing.DELETED_LISTING
					, method=RequestMethod.GET)
	public String showPage(Model model
										, @ActiveUserPrincipal Account account
										, @RequestParam(name="p", defaultValue="0") int pageNumber
										, @RequestParam(name="s", defaultValue="20") int pageSize) 
									throws MerchantException 
	{
		
		logger.info("show page");
		final Merchant merchant = getAuthorizedMerchant(account);
		List<DeletedProductTemplateDTO> templates = new ArrayList<>();
		long size = deletedProductService.countByMerchant(merchant);
		Pageable pageable = new PageSearch((pageNumber < 0) ? 0 : pageNumber, pageSize);
		
		if(size > 0) {
			templates = templateDTOService.getByMerchant(merchant, null, pageable);
		}
		
		model.addAttribute("size", size);
		model.addAttribute("page", pageable);
		model.addAttribute("templates", templates);
		return PageAdvice.Merchant.Listing.HISTORY_LISTING;
	}

}
