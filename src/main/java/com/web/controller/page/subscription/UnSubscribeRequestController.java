package com.web.controller.page.subscription;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
import com.common.calc.SubscriptionRefundCalculator;
import com.common.entity.account.Account;
import com.common.entity.subscription.Subscription;
import com.common.entity.subscription.UnsubscribeToken;
import com.common.exception.SubscriptionException;
import com.common.type.SubscriptionStatus;
import com.core.service.process.subscription.ICancelSubscriptionProcessService;
import com.core.service.product.dto.ProductTemplateDTOService;
import com.core.service.subscription.SubscriptionService;
import com.core.service.subscription.UnsubscribeTokenService;
import com.web.advice.PageAdvice;
import com.web.advice.UriActionRequestMapping;
import com.web.advice.UriPageRequestMapping;
import com.web.controller.exception.global.GeneralResourceNotFoundException;
import com.web.response.JsonResponse;

/**************************************************************
 * 
 * @author dk
 *
 */
@Controller
public class UnSubscribeRequestController implements IUnsubscribedRequestAdvise {

	private static final Logger logger = LogManager.getLogger();
	
	@Autowired private ProductTemplateDTOService productTemplateService;
	@Autowired private SubscriptionService subscriptionService;
	@Autowired private UnsubscribeTokenService tokenService;
	
	@Autowired private ICancelSubscriptionProcessService cancelService;
	
	
		
	/** :::
	 * <p>Display unsubscribe_request page by a account with a valid token</p>
	 * 
	 * {@link IUnsubscribedRequestAdvise#showUnsubscribePage(Model, Account, String)}
	 */
	@RequestMapping(value=UriPageRequestMapping.User.Subscription.UNSUBSCRIBE_REQUEST_PAGE
					, method=RequestMethod.GET)
	@Override
	public String showUnsubscribePage(Model model
							, @ActiveUserPrincipal Account account
							, @RequestParam(name="token") String unrequestToken) {
		System.out.println();
		logger.info("Show unsubscribe_request page by account");
		
		try {
			UnsubscribeToken token = tokenService.getByCustomer(account, unrequestToken);
			Assert.notNull(token, "[ERROR] Unable to retrieve token by account");
			Assert.isTrue(token.getValidate(), "[ERROR] invalid token");
			
			
			Subscription subscription = subscriptionService.getByAccount(
											token.getSubscription().getId(), account);
			Assert.isTrue(!subscription.getStatus().equals(SubscriptionStatus.UNSUBSCRIBED)
							, "Invalid subscription status");
			

			SubscriptionRefundCalculator calculator = new SubscriptionRefundCalculator(subscription);
			Map<String, Object> params = new HashMap<>();
			params.put("refund amount", "$" + ((double)calculator.getRefundAmount() / 100));
			params.put("remain shipment", calculator.getRemainShipment());
			
			
			model.addAttribute("status", subscription.getStatus());
			model.addAttribute("params", params);
			model.addAttribute("token", token);
			model.addAttribute("product", productTemplateService.getTemplate(subscription.getProduct()));	
		} catch(IllegalArgumentException e) {
			logger.error(e.getMessage());
			throw new GeneralResourceNotFoundException(e.getMessage());
		}
		
		return PageAdvice.User.UNSUBSCRIBE;
	}

	
	
	
	
	/** :::
	 * <p>Request to cancel the subscription</p>
	 * 
	 * {@link IUnsubscribedRequestAdvise#unsubscribeRequest(Account, Long, String, int)}
	 */
	@Override
	@RequestMapping(value=UriActionRequestMapping.User.Subscription.CONFIRM_UNSUBSCRIB
								, method=RequestMethod.POST)
	@ResponseBody
	public JsonResponse unsubscribeRequest(@ActiveUserPrincipal Account account
										 , @RequestParam Long _sid
										 , @RequestParam String _token
										 , @RequestParam(defaultValue="0") Integer _quote) {
		synchronized (this) {
			JsonResponse json = new JsonResponse();
			try {
				
				UnsubscribeToken token = tokenService.getByCustomer(account, _token);

				Assert.notNull(token, "Unable to find the unsubscribe_token object"
									+ " by the account_id: [" + account.getId() + "]"
									+ " on token_id [" + _token + "]");	

				Assert.isTrue(token.getValidate(), "Token already expires");
				Assert.isTrue(token.getSubscription().getId().equals(_sid), "[ERROR] Invalid subscription token");
				
				
				// Mark subscription as 'UNSUBSCRIBED'
				Subscription subscription = subscriptionService.getByAccount(_sid, account);
				Assert.notNull(subscription, "[ERROR] Unable to retrieve subscription for the account");
				Assert.isTrue(!subscription.getStatus().equals(SubscriptionStatus.UNSUBSCRIBED)
														, "[ERROR] Invalid subscription status");
				
				cancelService.cancel(subscription);
				tokenService.markAsUsed(Arrays.asList(token));
				json.setState(true);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
				json.setState(false);
			} catch (SubscriptionException e) {
				e.printStackTrace();
				json.setState(false);
			} 
			return json;
		}
	}


}
