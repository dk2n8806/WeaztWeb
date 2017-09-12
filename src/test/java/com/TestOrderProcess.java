package com;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.common.adapter.shippo.ShippoAddressAdapter;
import com.common.adapter.shippo.ShippoParcelAdapter;
import com.common.entity.address.Address;
import com.common.entity.order.OrderIntent;
import com.common.entity.product.Parcel;
import com.common.entity.product.Product;
import com.common.entity.subscription.Subscription;
import com.common.exception.MerchantException;
import com.common.exception.OrderException;
import com.common.exception.ProductException;
import com.common.type.OrderIntentStatus;
import com.common.type.SubscriptionStatus;
import com.common.util.date.DateInterval;
import com.common.util.date.DateUtil;
import com.core.service.account.SimpleShippingService;
import com.core.service.order.MerchantOrderIntentSerivce;
import com.core.service.order.OrderBundleService;
import com.core.service.order.OrderIntentService;
import com.core.service.order.OrderTransactionService;
import com.core.service.order.ShipmentService;
import com.core.service.payment.PaymentService;
import com.core.service.payout.PayoutService;
import com.core.service.process.order.IOrderProcessService;
import com.core.service.product.ParcelService;
import com.core.service.product.ProductService;
import com.core.service.subscription.SubscriptionService;

public class TestOrderProcess extends BaseTest{

	@Autowired private OrderBundleService orderBundleService;
	@Autowired private SubscriptionService subscriptionService;
	@Autowired private PaymentService paymentService;
	@Autowired private PayoutService payoutService;
	@Autowired private OrderIntentService orderIntentService;
	@Autowired private OrderTransactionService transactionService;
	@Autowired private ShipmentService shipmentService;
	
	
	
	@Autowired private IOrderProcessService orderProcessService;
	@Autowired private ProductService productService;
	
	
	@Autowired private MerchantOrderIntentSerivce merchantOrderIntentSerivce;
	private Product product;
	private DateInterval dateInterval;
	
	
	@Before
	public void init() {
		product = productService.findById(new Long(32));
		
		dateInterval = new DateUtil();
		dateInterval.setInterval(new Date(), -7);
	}
	
	@Autowired private ParcelService parcelService;
	
	@Test
	public void test() throws ProductException, OrderException, MerchantException {
		long orderBundleCount = orderBundleService.getRowCount();
		long payoutCount = payoutService.getRowCount();
		long orderIntentCount = orderIntentService.getRowCount();
		long orderTransactionCount = transactionService.getRowCount();
		long shipmentCount = shipmentService.getRowCount();
		
		long subscriptionCount = subscriptionService.countSubscriptions(SubscriptionStatus.SUBSCRIBING, null);
		List<Subscription> subscriptions = merchantOrderIntentSerivce.getScheduledSubscriptionsByProduct(product, OrderIntentStatus.REQUESTING	, dateInterval, null);

		orderProcessService.generateOrder(product, dateInterval);

		assertEquals(subscriptionCount - 2, subscriptionService.countSubscriptions(SubscriptionStatus.SUBSCRIBING, null));
		assertEquals(subscriptionCount, subscriptionService.countSubscriptions(SubscriptionStatus.SUBSCRIBED, null));
		assertEquals(orderBundleCount + 1, orderBundleService.getRowCount());
		assertEquals(payoutCount + 1, payoutService.getRowCount());
		assertEquals(orderIntentCount + 2, orderIntentService.getRowCount());
		assertEquals(orderTransactionCount + 2, transactionService.getRowCount());
		assertEquals(shipmentCount + 2, shipmentService.getRowCount());
		
		
		
		
	}
	
	@Autowired private SimpleShippingService simpleShippingService;
	
	
	
}
