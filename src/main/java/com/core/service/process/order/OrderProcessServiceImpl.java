package com.core.service.process.order;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.adapter.shippo.ShipmentAdapter;
import com.common.entity.address.Address;
import com.common.entity.order.OrderBundle;
import com.common.entity.order.OrderIntent;
import com.common.entity.order.OrderTransaction;
import com.common.entity.order.RecommendedLabel;
import com.common.entity.order.Shipment;
import com.common.entity.payout.Payout;
import com.common.entity.payout.SalePayout;
import com.common.entity.product.Parcel;
import com.common.entity.product.Product;
import com.common.entity.subscription.Subscription;
import com.common.exception.MerchantException;
import com.common.exception.OrderException;
import com.common.exception.ProductException;
import com.common.exception.SubscriptionException;
import com.common.type.OrderIntentStatus;
import com.common.type.ProductStatus;
import com.common.type.SubscriptionStatus;
import com.common.util.date.DateInterval;
import com.core.service.account.SimpleShippingService;
import com.core.service.merchant.MerchantService;
import com.core.service.order.MerchantOrderIntentSerivce;
import com.core.service.order.OrderBundleService;
import com.core.service.order.OrderIntentService;
import com.core.service.payment.SubscriptionPaymentService;
import com.core.service.payout.PayoutService;
import com.core.service.product.ParcelService;
import com.core.service.shippo.ShippoShipmentService;
import com.core.service.subscription.SubscriptionService;

@Service
public class OrderProcessServiceImpl implements IOrderProcessService{

	
	private static final Logger logger = LogManager.getLogger();
	
	@Autowired private SubscriptionService subscriptionService;
	
	@Autowired private OrderIntentService orderIntentService;
	@Autowired private OrderBundleService orderBundleService;
	
	@Autowired private ParcelService parcelService;
	
	@Autowired private SubscriptionPaymentService paymentService;
	
	@Autowired private MerchantService merchantService;
	@Autowired private MerchantOrderIntentSerivce merchantOrderIntentSerivce;
	
	@Autowired private ShippoShipmentService shippoShipmentService;
	@Autowired private SimpleShippingService simpleShippingService;

	@Autowired PayoutService payoutService;
	
	
	/** :::
	 * 
	 * <ul>
	 * 		<li>Retrieve a list of order subscriptions</li>
	 * 		<li>Ensure the subscriptions are valid</li>
	 * 		<li>Generate order transaction and shipment each subscriptions</li>
	 * 		<li>Generate order bundle for order transactions</li>
	 * 		<li>Generate next subscriptions</li>
	 * </ul>
	 * @throws MerchantException 
	 */
	@Override
	public OrderBundle generateOrder(Product product, DateInterval dateInterval) throws ProductException, OrderException, MerchantException {
		if(product == null) 
			throw new ProductException("Product cannot be null");
		if(product.getStatus().equals(ProductStatus.DELETED)) 
			throw new ProductException("Invalid product status");
		if(dateInterval == null) 
			throw new OrderException("dateinterval cannot be null");
		
		List<OrderTransaction> orderTransactions = new ArrayList<>();
		List<Subscription> subscriptions = 
				merchantOrderIntentSerivce.getScheduledSubscriptionsByProduct(
									product, OrderIntentStatus.REQUESTING, dateInterval, null);
		
		logger.info("size of subscription: " + subscriptions.size());
		
		// ::: Prepare core data for shipment
		Address sender = getMerchantAddress(product);
		Parcel parcel = getProductParcel(product);
		
		
		// ::: Generate order transaction for each valid subscriptions
		for(Iterator<Subscription> iter = subscriptions.listIterator(); iter.hasNext();) {
			Subscription subscription = iter.next();
			if(subscription.getStatus().equals(SubscriptionStatus.UNSUBSCRIBED)) {
				iter.remove();
			} else {
				orderTransactions.add(getOrderTransaction(sender, subscription, parcel));
			}
		}
		
		logger.info("Size of order Transaction: " + orderTransactions.size());
		
		OrderBundle orderBundle = orderBundleService.save(product, orderTransactions);
		logger.info("Generated a bundle of order transactions");
		
		requestPayments(subscriptions);													// Request payment on the subscriptions
		logger.info("Requested subscription payments on new subscription");
		
		orderIntentService.completeBySubscriptions(subscriptions);		// Complete order intent
		logger.info("Completed order intents by subscriptions");
		
		createNextOrderIntent(subscriptions);											// Create new order intent
		logger.info("Generated new order intents by subscription");
		
		Payout payout = payoutService.save(SalePayout.create(product.getMerchant(), orderBundle));
		logger.info("Generated payout on order bundle");
		
		if(payout == null) 
			throw new OrderException("Unable to create payout");
		return orderBundle;
	}
	
	
	
	
	/**
	 * TODO
	 * <p>This can be handle differently with schedule</p>
	 */
	private void createNextOrderIntent(List<Subscription> subscriptions) {
		List<OrderIntent> orderIntents = new ArrayList<>();
		for(Subscription subscription : subscriptions) {
			try {
				subscription.nextSubscription();
				if(subscription.getStatus().equals(SubscriptionStatus.SUBSCRIBED))
					orderIntents.add(OrderIntent.create(subscription, subscription.getScheduledOn()));
				
			} catch (SubscriptionException e) {
				e.printStackTrace();
			}
		}

		subscriptionService.update(subscriptions);
		orderIntentService.save(orderIntents);
	}
	
	/**:::
	 *  <p>Lookup recent valid parcel of the product</p>
	 * ::: */
	private Parcel getProductParcel(Product product) throws ProductException {
		Parcel parcel = parcelService.getParcel(product);
		if(parcel == null)
			throw new ProductException("Unable to lookup product parcel");
		return parcel;
	}
	
	
	
	/** :::
	 * <p>Retrieve merchant address</p>
	 */
	private Address getMerchantAddress(Product product) throws MerchantException {
		try {
			Address sender = merchantService.getProfile(product.getMerchant()).getAddress();
			if(sender == null) 
				throw new MerchantException("Unable to lookup merchant address");
			return sender; 
		} catch(NullPointerException e) {
			throw new MerchantException("Unable to lookup merchant profile");
		}
	}
	
	
	
	/** :::
	 * <p>Request payment on the new subscriptions</p>
	 */
	private void requestPayments(List<Subscription> subscriptions) {
		List<Subscription> subscriptionPayments = new ArrayList<>();
		for(Subscription subscription : subscriptions) {
			if(subscription.getStatus().equals(SubscriptionStatus.SUBSCRIBING)) {
				subscriptionPayments.add(subscription);
			}
		}
		paymentService.triggerPayment(subscriptionPayments);
		
	}
	

	/** ::: [Helper method]
	 * 
	 * <p>Create </p>
 	 *
	 */
	private OrderTransaction getOrderTransaction(
			Address sender, Subscription subscription, Parcel parcel) throws OrderException 
	{
		// Retrieve customer's delivery address
		Address receiver = getReceiverAddress(subscription);
		ShipmentAdapter shipmentAdapter = shippoShipmentService.create(sender, receiver, parcel);
		
		// Create recommend shipment base on shipment
		RecommendedLabel recommendedLabel = RecommendedLabel.create(shipmentAdapter.getLowestRate());
		
		Shipment shipment = Shipment.create(sender, receiver, recommendedLabel);
		if(shipment == null)
			throw new OrderException("Unable to create shipment");
		
		OrderTransaction orderTransaction = OrderTransaction.create(subscription, shipment);
		if(orderTransaction == null)
			throw new OrderException("Unable to create transaction for order");
		
		return orderTransaction;
	}
	
	
	/** ::: 
	 * <p>Retrieve the intended delivery address of the customer</p> 
	 * */
	private Address getReceiverAddress(Subscription subscription) {
		try {
			return simpleShippingService.getPrimaryAddress(subscription.getAccount());
		} catch(NullPointerException e) {
			e.printStackTrace();
			return null;
		}
	}


}
