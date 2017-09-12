package com.checkout;

import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.BaseTest;
import com.common.calc.ProductCalculator;
import com.common.calc.ProductCalculatorResult;
import com.common.entity.account.Account;
import com.common.entity.merchant.TaxRate;
import com.common.entity.order.OrderIntent;
import com.common.entity.product.Product;
import com.common.entity.promo.Promotion;
import com.common.entity.subscription.Subscription;
import com.common.entity.subscription.embeded.ValuePerShipment;
import com.common.exception.OrderException;
import com.common.exception.SubscriptionException;
import com.common.type.ProductStatus;
import com.core.service.account.AccountService;
import com.core.service.checkout.CheckoutService;
import com.core.service.merchant.MerchantService;
import com.core.service.product.ProductService;
import com.core.service.promo.PromotionService;
import com.core.service.shippo.ShippoQuoteService;

public class TestCheckout extends BaseTest {

	@Autowired private AccountService accountService;
	@Autowired private ProductService productService;
	@Autowired private MerchantService merchantService;
	@Autowired private ShippoQuoteService quoteService;
	@Autowired private CheckoutService checkoutService;
	@Autowired private PromotionService promotionService;
	
	private Account account;
	private Product product;
	private int frequency;
	private int nos;
	private int shipmentCost;
	private TaxRate taxRate;
	private Promotion promotion;
	
	
	@Before
	public void inti() {
		account = accountService.findById(new Long(2132007));
		product = productService.findById(new Long(2132118));

		frequency = 21;
		nos = product.getSubscriptionInfo().getNos();
		System.out.println("nos " + nos );
		
		shipmentCost = 0;
		taxRate = null;
		promotion = null; // promotionService.getByCode("weznow50");
	}
	
	
	@Test
	public void test() throws SubscriptionException, OrderException {
		OrderIntent order = checkoutService.checkout(account, product, frequency, nos, promotion);
		System.out.println(order.getId());
		
		System.out.println(order.getSubscription());
		System.out.println("Total charge: " + order.getSubscription().getTotalCharge());
		System.out.println("Subscription Value: " + order.getSubscription().getPerShipment().getSubscriptionValue());
	

//		TaxRate taxRate = merchantService.getTaxRate(product.getMerchant()); 
//	
//		int shipmentCost = quoteService.getShippingCost(account, product);
//		ProductCalculator calculator = new ProductCalculator(product, nos, shipmentCost , taxRate, promotion);
//		ProductCalculatorResult result = calculator.getResult();
//
//		ValuePerShipment perShipment = ValuePerShipment.create(product.getBasicInfo().getPrice()
//																								, result.getSubscriptionPrice()
//																								, result.getSubscriptionCost()
//																								, shipmentCost
//																								, result.getTaxCost());
//		
//		Subscription subscription = Subscription.create(account, product, perShipment, nos, frequency);
//		
//		System.out.println(result);
//		System.out.println(perShipment);
//		System.out.println(subscription);
	}
}
