package com.core.service.process;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.entity.order.OrderIntent;
import com.common.entity.payment.Payment;
import com.common.entity.product.Product;
import com.common.entity.refund.Refund;
import com.common.entity.refund.SubscriptionRefund;
import com.common.entity.subscription.Subscription;
import com.common.exception.OrderException;
import com.common.exception.PaymentException;
import com.common.exception.ProductException;
import com.common.exception.SubscriptionException;
import com.common.type.OrderIntentStatus;
import com.common.type.ProductStatus;
import com.common.type.SubscriptionStatus;
import com.common.util.PageSearch;
import com.common.util.date.DateInterval;
import com.core.service.order.MerchantOrderIntentSerivce;
import com.core.service.order.OrderIntentService;
import com.core.service.payment.SubscriptionPaymentService;
import com.core.service.process.subscription.ICancelSubscriptionProcessService;
import com.core.service.product.DeletedProductService;
import com.core.service.product.ProductService;
import com.core.service.refund.RefundService;
import com.core.service.subscription.SubscriptionService;
import com.core.service.subscription.UnsubscriptionService;



/** :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 * TODO 
 * <p>There is an better way to improve the code</p>
 * <p>Since the order_intent is created by new or current subscription,
 * it would be safe to assume that order_intent contains valid subscriptions.
 * Hence improve the code by retrieve the order_intent along with
 * its subscription will reduce number of query.</p>
 * ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
@Service
@Transactional
public class DeleteProductProcess implements IDeleteProductProcessService {
	
	
	private static Logger logger = LogManager.getLogger();
	
	
	@Autowired private ProductService productService;
	@Autowired private SubscriptionService subscriptionService;
	@Autowired private DeletedProductService deletedProductService;

	@Autowired private OrderIntentService orderIntentService;
	@Autowired private RefundService refundService;
	@Autowired private SubscriptionPaymentService paymentService;
	@Autowired private UnsubscriptionService unsubscriptionService;
	
	@Autowired private MerchantOrderIntentSerivce merchantOrderIntentSerivce;

	
	@Override
	public void delete(Product product) throws ProductException, SubscriptionException, OrderException {
		if(product.getStatus().equals(ProductStatus.DELETED))
			throw new ProductException("Invalid product status - already marked as DELETED");

		cancelSubscriptions(product, SubscriptionStatus.SUBSCRIBING);
		cancelSubscriptions(product, SubscriptionStatus.SUBSCRIBED);
		
		// TODO notify the customer about the subscription refund
		
		// Cancel order intent by product
		merchantOrderIntentSerivce.cancelByProduct(product);
		
		productService.makeDelete(product);		// Mark the product as deleted
		deletedProductService.save(product);		// Create a new deletedProduct entity
		
	}

	
	
	
	
	
	/**
	 * Handle canceling recurrent subscriptions
	 */
	private void cancelSubscriptions(Product product, SubscriptionStatus status) throws SubscriptionException {
		long count = subscriptionService.countByProduct(product, status, null);
		
		// Retrieve a list of recurrent subscriptions
		if(count > 0) {
			List<Subscription>  subscriptions = 
					subscriptionService.getByProduct(product, status, null, new PageSearch(0, count));

			// Handle refund for cancel the subscription
			// Then mark the subscription as UNSUBSCRIBED
			for(Subscription subscription : subscriptions) {
				refundSubscripitonHandler(subscription);
				subscriptionService.markAsUnsubscribed(subscription.getId());		// Unsubscribe the subscripiton	
				
			}
		}
		
	}

	
	public void refundSubscripitonHandler(Subscription subscription) throws SubscriptionException {
		switch (subscription.getStatus()) {
		case SUBSCRIBED: refundPaymentHandler(subscription); break;
		case SUBSCRIBING: voidPaymentHandler(subscription); break;
		default: throw new SubscriptionException("Error while canceling the subscription");
		}
	}
	
	

	/**
	 * Cancel the subscriptions will void the payment. 
	 **/
	public void voidPaymentHandler(Subscription subscription) {
		Payment payment = paymentService.getBySubscription(subscription);
		try {
			paymentService.markAsVoided(payment);
		} catch (PaymentException e) {
			e.printStackTrace();
		}
	}

	
	

	
	/**
	 * Calculate and create a refund for the subscription
	 */
	public void refundPaymentHandler(Subscription subscription) throws SubscriptionException 
	{
		Refund refund = refundService.save(SubscriptionRefund.create(
																		subscription.getAccount(), subscription));
		if(refund == null) 
			throw new SubscriptionException("Unable to create a refund");
	}
	
	
}
