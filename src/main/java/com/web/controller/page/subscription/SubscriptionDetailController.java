package com.web.controller.page.subscription;


import java.util.ArrayList;
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
import com.common.dto.product.ProductTemplateDTO;
import com.common.dto.review.ReviewDTO;
import com.common.dto.shipment.LastSubscriptionShipmentTemplateDTO;
import com.common.entity.account.Account;
import com.common.entity.review.Review;
import com.common.entity.subscription.Subscription;
import com.common.entity.subscription.UnsubscribeToken;
import com.common.type.SubscriptionStatus;
import com.common.type.review.ReviewType;
import com.common.util.PageSearch;
import com.core.service.product.dto.ProductTemplateDTOService;
import com.core.service.review.ReviewDTOSerivice;
import com.core.service.review.ReviewService;
import com.core.service.shipment.dto.LastSubscriptionShipmentTemplateDTOService;
import com.core.service.subscription.SubscriptionService;
import com.core.service.subscription.UnsubscribeTokenService;
import com.web.advice.PageAdvice;
import com.web.advice.UriActionRequestMapping;
import com.web.advice.UriPageRequestMapping;
import com.web.controller.exception.global.GeneralResourceNotFoundException;
import com.web.response.JsonResponse;


/**
 * Display a subscription in detail
 * Customer request to display subscription info
 * 
 * @author dk
 */
@Controller
public class SubscriptionDetailController implements ISubscriptionDetailAdvise {

	private static final Logger logger = LogManager.getLogger();
	
	@Autowired private SubscriptionService subscriptionService;
	@Autowired private ProductTemplateDTOService productTemplateService;
	
	@Autowired private UnsubscribeTokenService tokenService;
	@Autowired private LastSubscriptionShipmentTemplateDTOService shipmentTemplateService;
	
	
	@Autowired private ReviewService reviewService;
	@Autowired private ReviewDTOSerivice reviewDTOservice;
	
		
	/** :::::::::::::::::::::::::::::::::::
	 * Lookup and display a subscription by a account
	 * 
	 * {@link ISubscriptionDetailAdvise#showSubscription(Model, Account, Long)}
	 * ::::::::::::::::::::::::::::::::::: */
	@RequestMapping(value=UriPageRequestMapping.User.Subscription.SUBSCRIPTION_DETAIL_PAGE
					, method=RequestMethod.GET)
	@Override
	public String showSubscription(Model model
													, @ActiveUserPrincipal Account account
													, @PathVariable Long _sid) 
	{
		try {
			Assert.isTrue(_sid > 0, "subscription id must > 0");
		
			Subscription subscription = null;
			ProductTemplateDTO productTemplate = null;
			List<LastSubscriptionShipmentTemplateDTO> 	shipmentHistoryTemplates = null;
			List<ReviewDTO> reviews = null;
			
			subscription = subscriptionService.getByAccount(_sid, account);
			
			Assert.notNull(subscription
						, "Unable to retrieve subscription with id [" + _sid + "]");
			Assert.isTrue(!subscription.getStatus().equals(SubscriptionStatus.UNSUBSCRIBED)
						, "Invalid subscription status - already marked as 'UNSUBSCRIBED'");
			
			productTemplate = productTemplateService.getTemplate(subscription.getProduct());
			
			// Look up shipment history
			Pageable shipmentPage = new PageSearch(0,  3);
			shipmentHistoryTemplates = shipmentTemplateService.getHistoryShipments(account, subscription, shipmentPage);
			
			// Look up product's reviews
			long reviewSize = reviewService.countByProduct(subscription.getProduct(), null); 
			if(reviewSize > 0){
				Pageable page = new PageSearch(0, reviewSize);
				reviews = reviewDTOservice.getByProduct(subscription.getProduct(), page);
			}
			

			model.addAttribute("reviewSize", reviewSize);
			model.addAttribute("reviews", reviews);
			model.addAttribute("subscription", subscription);
			model.addAttribute("product", productTemplate);
			model.addAttribute("historyShipments", shipmentHistoryTemplates);
			
			return PageAdvice.User.SUBSCRIPTION_DETAIL;
		} catch(IllegalArgumentException e) {
			throw new GeneralResourceNotFoundException(e);
		}
	}
	
	
	
	

/**
 * <p>Write a review for the subscription</p>
 * 
 * @param account
 * @param _sid
 * @param content
 * 
 * {@link ISubscriptionDetailAdvise#writeReview(Account, Long, String)}
 */
	@RequestMapping(value=UriActionRequestMapping.User.Review.WRITE_REVIEW
				, method=RequestMethod.POST)
	@ResponseBody
	@Override
	public JsonResponse writeReview(@ActiveUserPrincipal Account account
								, @RequestParam(name="_sid") Long _sid
								, @RequestParam(name="_c") 	 String content) 
	{
		synchronized (this) {
			JsonResponse json = new JsonResponse();
			try {
				Subscription subscription = subscriptionService.getByAccount(_sid, account);
				Assert.notNull(subscription, "[ERROR] Unable to lookup the subscription with id [" + _sid + "]");
				
				Review review = reviewService.save(
						account, subscription.getProduct(), content, ReviewType.VERIFIED);
				Assert.notNull(review, "[ERROR] Unable to create a review for the subscription");
				json.setState(true);
			} catch(IllegalArgumentException e) {
				throw new IllegalArgumentException(e.getMessage());
			}
			return json;
		}
	}
	
	


	
	



	
		
		
	/** :::
	 * <p>Update delivery frequency of a subscription</p>
	 *
	 * {@link ISubscriptionDetailAdvise#updateDeliverFrequency(Account, Long, int)}
	 */
	@RequestMapping(value=UriActionRequestMapping.User.Subscription.REQUEST_UPDATE_SUBSCRIPTION_DELIVER_FREQ
			, method=RequestMethod.POST)
	@ResponseBody
	@Override
	public JsonResponse updateDeliverFrequency(@ActiveUserPrincipal Account account
											, @RequestParam(name="_sid") Long subscriptionId
											, @RequestParam(name="_freq") String _freq) {
		System.out.println();
		logger.info("Request update delivery_frequency of a subscription with id [" + subscriptionId + "]");
		JsonResponse jsonResponse = new JsonResponse();
		try {
			int freq = Integer.valueOf(_freq);
			Assert.isTrue(freq > 0, "new delivery frequency must be greater than 0");
			Subscription subscription = subscriptionService.getByAccount(subscriptionId, account);
			if(subscription != null && !subscription.getStatus().equals(SubscriptionStatus.UNSUBSCRIBED)) {
				subscription.setFrequency(freq);
				subscriptionService.update(subscription);
				jsonResponse.setState(true);
				jsonResponse.setResult(freq);
			} else {
				jsonResponse.setState(false);
				jsonResponse.setResult("The subscription is not available for update");
			}
		} catch(IllegalArgumentException  e) {
			e.printStackTrace();
			jsonResponse.setState(false);
		} 
		return jsonResponse;
	}
		
		
	
	
	/** :::
	 * <p>Request to cancel a subscription</p>
	 * 
	 * {@link ISubscriptionDetailAdvise#generateUnsubscribeToken(Account, Long)}
	 */
	@RequestMapping(value=UriActionRequestMapping.User.Subscription.UNSUBSCRIBE_REQUEST
								, method=RequestMethod.POST)
	@ResponseBody
	@Override
	public JsonResponse generateUnsubscribeToken(@ActiveUserPrincipal Account account
									, @RequestParam(name="_sid") Long subscriptionId) {
		System.out.println();
		logger.info("Generate unsubscribe_token by custoemr");
		JsonResponse jsonResponse = new JsonResponse();
		try {
			synchronized (this) 
			{
				Subscription subscription = subscriptionService.getByAccount(subscriptionId, account);
			
				Assert.notNull(subscription, "Unable to retrieve subscription");
				Assert.isTrue(!subscription.getStatus().equals(SubscriptionStatus.UNSUBSCRIBED)
							, "Subscription is already mark as 'UNSUBSCRIBED'");
			
				
				// Create an usable unsubscribe_token
				UnsubscribeToken token = tokenService.save(subscription);
			
				
				Assert.notNull(token, "Unable to generate token");
				
				jsonResponse.setState(true);
				jsonResponse.setResult(UriPageRequestMapping.User.Subscription.UNSUBSCRIBE_REQUEST_PAGE + "?token=" + token.getToken());
			}
		} catch(IllegalArgumentException e) {
			logger.error(e.getMessage());
			jsonResponse.setState(false);
			jsonResponse.setResult(e.getMessage());
		}
		return jsonResponse;
	}





	/**
	 * {@link ISubscriptionDetailAdvise#toggleRating}
	 */
	@RequestMapping(value=UriActionRequestMapping.User.Subscription.TOGGLE_RATING_REQUEST
								, method=RequestMethod.POST)
	@ResponseBody
	@Override
	public void toggleRating(@ActiveUserPrincipal Account account
														, @RequestParam(name="s") Long subscriptionId
														, @RequestParam(name="w") String which) 
	{
		System.out.println("s: " + subscriptionId + " - w: " + which);
		Subscription subscription = subscriptionService.getByAccount(subscriptionId, account);
		if(subscription != null) {
			
		}
		synchronized (this) {
			switch (which) {
			case "f": subscriptionService.toggleFavorite(subscriptionId, account); 	break;
			case "r" : subscriptionService.toggleRecommend(subscriptionId, account); break;
			default:
				break;
			}
		}
	}

	
}
