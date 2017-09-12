package com.entity.orderBundle;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.BaseTest;
import com.common.adapter.shippo.ShipmentAdapter;
import com.common.entity.address.Address;
import com.common.entity.order.OrderBundle;
import com.common.entity.order.OrderTransaction;
import com.common.entity.order.RecommendedLabel;
import com.common.entity.order.Shipment;
import com.common.entity.product.Parcel;
import com.common.entity.product.Product;
import com.common.entity.subscription.Subscription;
import com.common.exception.OrderException;
import com.core.service.account.SimpleShippingService;
import com.core.service.merchant.MerchantService;
import com.core.service.order.OrderBundleService;
import com.core.service.order.OrderTransactionService;
import com.core.service.order.ShipmentService;
import com.core.service.product.ParcelService;
import com.core.service.product.ProductService;
import com.core.service.shippo.ShippoShipmentService;
import com.core.service.subscription.SubscriptionService;

public class TestCreateBundle extends BaseTest {

	@Autowired private OrderBundleService orderBundleService;
	@Autowired private ProductService productService;
	@Autowired private SubscriptionService subscriptionSerivce;
	@Autowired private ShippoShipmentService shippoShipmentService;
	@Autowired private SimpleShippingService simpleShippingService;
	@Autowired private ParcelService parcelService;
	@Autowired private MerchantService merchantService;
	
	@Autowired private ShipmentService shipmentService;
	@Autowired private OrderTransactionService transactionService;
	
	@Test
	public void test() throws OrderException {
		long obc = orderBundleService.getRowCount();
		long otc = transactionService.getRowCount();
		long sc = shipmentService.getRowCount();
		Subscription subscription = subscriptionSerivce.findById(new Long(44));
		Product product = productService.findById(subscription.getProduct().getId());
		Address sender = merchantService.getProfile(product.getMerchant()).getAddress();
		Parcel parcel = parcelService.getParcel(product);
	

		List<OrderTransaction> orderTransactions  = new ArrayList<>();
		orderTransactions.add(getOrderTransaction(sender, subscription, parcel));
		OrderBundle bundle = orderBundleService.save(product, orderTransactions);
		assertNotNull(bundle);
		System.out.println(bundle.getId());
		
		assertEquals(obc + 1, orderBundleService.getRowCount());
		assertEquals(otc + orderTransactions.size(), transactionService.getRowCount());
		assertEquals(sc + orderTransactions.size(), shipmentService.getRowCount());
		
	}
	
	private OrderTransaction getOrderTransaction(
			Address sender, Subscription subscription, Parcel parcel) throws OrderException 
	{
		// Retrieve customer's delivery address
		Address receiver = simpleShippingService.getPrimaryAddress(subscription.getAccount());
		ShipmentAdapter shipmentAdapter = shippoShipmentService.create(sender, receiver, parcel);
		
		System.out.println("Shipment token: " + shipmentAdapter.getTokenId());
		
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
	
}
