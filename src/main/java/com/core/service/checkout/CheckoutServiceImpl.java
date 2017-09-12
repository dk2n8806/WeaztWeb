package com.core.service.checkout;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.calc.ProductCalculator;
import com.common.calc.ProductCalculatorResult;
import com.common.entity.account.Account;
import com.common.entity.merchant.TaxRate;
import com.common.entity.order.OrderIntent;
import com.common.entity.payment.SubscriptionPayment;
import com.common.entity.product.Product;
import com.common.entity.promo.Promotion;
import com.common.entity.subscription.Subscription;
import com.common.entity.subscription.embeded.ValuePerShipment;
import com.common.exception.OrderException;
import com.common.exception.SubscriptionException;
import com.core.service.merchant.MerchantService;
import com.core.service.order.OrderIntentService;
import com.core.service.payment.PaymentService;
import com.core.service.shippo.ShippoQuoteService;
import com.core.service.subscription.SubscriptionService;

@Service 
@Transactional
public class CheckoutServiceImpl
	implements CheckoutService
{

	private static final Logger logger = LogManager.getLogger();
	
	
	@Autowired private SubscriptionService subscriptionService;
	@Autowired private PaymentService paymentService;
	
	@Autowired private ShippoQuoteService quoteService;
	@Autowired private MerchantService merchantService;
	@Autowired private OrderIntentService orderIntentService;
	
	
	/** ::: 
	 * <h1>Checkout</h1>
	 * {@link CheckoutService#checkout(Account, Product, int, int, Promotion)}
	 * ::: 
	 * @throws OrderException */
	@Override
	public OrderIntent checkout(Account account, Product product
								, int frequency, int nos, Promotion promotion)
			throws SubscriptionException, OrderException 
	{
		try {
			TaxRate taxRate = merchantService.getTaxRate(product.getMerchant()); 
			int shipmentCost = quoteService.getShippingCost(account, product);
			
			if(promotion == null) {
				logger.info("Null promotion");
			} else {
				logger.info("code: " + promotion.getCode() + " - value " + promotion.getValue());
			}
			ProductCalculator calculator = new ProductCalculator(product, nos, shipmentCost , taxRate, promotion);
		
			ProductCalculatorResult result = calculator.getResult();

			ValuePerShipment perShipment = ValuePerShipment.create(product.getBasicInfo().getPrice()
																									, result.getSubscriptionPrice()
																									, result.getSubscriptionCost()
																									, shipmentCost
																									, result.getTaxCost());
			
			Subscription subscription = subscriptionService.save(account, product, nos, frequency, perShipment);
			
//			System.out.println(result);
//			System.out.println(perShipment);
//			System.out.println(subscription);
//			
			// Create a payment for the subscription
			if(subscription == null) {
				throw new SubscriptionException("Unable to create subscription entity");
			}
			paymentService.save(SubscriptionPayment.create(account, subscription, subscription.getTotalCharge()));
			
			
			
			return orderIntentService.save(subscription, subscription.getScheduledOn());
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
			throw new OrderException("Unable to complete order");
		}
	}
}
