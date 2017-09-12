package com.web.controller.page.order;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.admin.config.anno.ActiveUserPrincipal;
import com.common.dto.order.OrderBundleWrapperDTO;
import com.common.dto.order.OrderTransactionWrapperDTO;
import com.common.entity.account.Account;
import com.common.entity.merchant.Merchant;
import com.common.entity.order.GeneratedLabel;
import com.common.entity.order.OrderBundle;
import com.common.entity.order.OrderTransaction;
import com.common.entity.order.Shipment;
import com.common.entity.payout.LabelFee;
import com.common.entity.payout.MerchantFee;
import com.common.exception.MerchantException;
import com.common.type.ShipmentStatus;
import com.core.service.order.MerchantOrderBundleService;
import com.core.service.order.OrderBundleService;
import com.core.service.order.OrderTransactionService;
import com.core.service.order.ShipmentService;
import com.core.service.order.ShipmentTransactionService;
import com.core.service.order.dto.OrderBundleWrapperDTOService;
import com.core.service.order.dto.OrderTransactionWrapperDTOSerivce;
import com.core.service.payout.MerchantFeeService;
import com.web.advice.PageAdvice;
import com.web.advice.UriActionRequestMapping;
import com.web.advice.UriPageRequestMapping;

@Controller
public class OrderTransactionDetailController
extends AbstractOrderHelper implements IOrderTransactionDetailAdvise{

	private static final Logger logger = LogManager.getLogger();


	
	@Autowired private MerchantOrderBundleService merchantOrderService;
	@Autowired private OrderBundleWrapperDTOService orderWrapperService;
	
	@Autowired private OrderBundleService orderService;
	@Autowired private OrderTransactionService orderTransactionService;
	@Autowired private OrderTransactionWrapperDTOSerivce transactionTemplateService;
	
	@Autowired private ShipmentService shipmentService;
	@Autowired private ShipmentTransactionService shipmentTransactionService;
	@Autowired private MerchantFeeService merchantFeeService;
	
	//@Autowired private LabelFeeService labelFeeService;
	
		
	/** :::
	 * <p>Show a list of order transaction on an order</p>
	 * {@link IOrderTransactionDetailAdvise#showOrderTransactionDetail(Model, Account, Long)}
	 */
	@RequestMapping(value=UriPageRequestMapping.Merchant.Order.ORDER_TRANSACTION_DETAIL
					, method=RequestMethod.GET)
	public String showOrderTransactionDetail(Model model
																	, @ActiveUserPrincipal Account customer
																	, @PathVariable(value="_oid") Long _oid) 
	{
		System.out.println();
		logger.info("Request to display order history in detail");
		final Merchant merchant = customer.getMerchant();
		final OrderBundle order = merchantOrderService.getByMerchant(_oid, merchant);
		Assert.notNull(order, "Unable to lookup order data");
		
		OrderBundleWrapperDTO orderWrapper = orderWrapperService.getByMerchant(_oid, merchant);
		List<OrderTransactionWrapperDTO> templates = transactionTemplateService.getByOrder(order);
		
		model.addAttribute("order", orderWrapper);
		model.addAttribute("templates", templates);
		//model.addAttribute("customers", customers);
		//model.addAttribute("shipments", shipments);
		
		return PageAdvice.Merchant.Order.ORDER_TRANSACTION_DETAIL;
	}

	


	/** :::
	 * Request to download shiping label on an order shipment
	 * 
	 * {@link IOrderTransactionDetailAdvise#downloadLabel(Account, Long, HttpServletResponse)}
	 * ::: */
	@RequestMapping(
			value=UriActionRequestMapping.Merchant.Order.Shipment.DOWNLOAD_LABEL
		  , method=RequestMethod.GET)
	@Override
	public void downloadLabel(@ActiveUserPrincipal Account customer
											,@RequestParam(name="_oid") Long _orderTransactionId
											, HttpServletResponse response
											) throws MerchantException 
	{
		synchronized (this) {
			
			try {
	
				String labelUrl = "";
				final Merchant merchant = this.getAuthorizedMerchant(customer);
				
				Assert.isTrue(_orderTransactionId > 0, "Invalid order_transaction");
				Shipment shipment = orderTransactionService.getShipmentByMerchant(_orderTransactionId, merchant);
				Assert.notNull(shipment, "Shipment cannot be null");
				
//				logger.info("Retrieved shipment entity");
				
				// :::
				// Create a shipping label for a shipment
				// Then retrieve the url for download the shipping label.
				// If the shipment has already processed. Allow the 
				// merchant redownload the shipping label.
				// :::
				if(shipment.getStatus().equals(ShipmentStatus.PENDING)) {
					shipmentTransactionService.generateShippingLabel(
								shipment, shipment.getRecommendedShipment().getRate());

					GeneratedLabel label = (GeneratedLabel) shipment.getLabel();
					MerchantFee labelFee = merchantFeeService.save(LabelFee.create(merchant, label));
					
					Assert.notNull(labelFee, "Label Fee is not null");
					
//					System.out.println("URL_ " + label.getLabelAdapter().getLabelUrl());
//					System.out.println("TRACK " + label.getLabelAdapter().getTrackingNumber());
//					System.out.println("COST" + label.getLabelAdapter().getShippingCost());
					
					labelUrl = label.getLabelAdapter().getLabelUrl();
				} 
				else if(shipment.getStatus().equals(ShipmentStatus.SHIPPED)) {
					if(shipment.getLabel() instanceof GeneratedLabel) {
						GeneratedLabel label = (GeneratedLabel) shipment.getLabel();
						labelUrl = label.getLabelAdapter().getLabelUrl();
					}
				}
				
				
//				logger.info("received lable_url: " + labelUrl);
				//logger.info("Label url: " + shipment.getLabelAdapter().getLabelUrl());
				//String dlLink = "https://s3-us-west-2.amazonaws.com/mppphotoes/af35a293-07a7-4b82-9866-53299a51e489.jpg";
				
				
				String name = "shipment" + shipment.getId();
				URL url = new URL(labelUrl);		
				response.setHeader("Content-disposition", "attachment;filename=" + name);
				response.setContentType("application/pdf");
	
				//URLConnection connection = url.openConnection();
				//InputStream stream = connection.getInputStream();
	    		InputStream stream = url.openStream();
	    		
				BufferedOutputStream outs = new BufferedOutputStream(response.getOutputStream());
	    		int len;
			    byte[] buf = new byte[1024];
			    while ((len = stream.read(buf)) > 0) {
			      outs.write(buf, 0, len);
			    }
			    outs.close();
			    
			    // ::: complete order
			    shipmentService.markAsShipped(shipment);
			    
			    
//			    logger.info("Completed shipment");
			    
			    OrderTransaction transaction = orderTransactionService.getByShipment(shipment);
			    
			    Assert.notNull(transaction, "Transaction should not be null");
			    

//			    logger.info("Retrieved order_transaction of the shipment");
			    long count = orderTransactionService.countByOrder(transaction.getOrderBundle(), ShipmentStatus.PENDING);
			  
//			    logger.info("Count total pending shipment: " + count );
			    if(count == 0) {
			    	OrderBundle order = orderTransactionService.getOrder(transaction);
			    	Assert.notNull(order, "Order should not be null");
			    	if(order != null) {
			    		orderService.completed(order.getId());
			    	}
			    }
			    logger.info("Complete order bundle");
			} catch(IllegalArgumentException e) {
				e.printStackTrace();
			} catch(MalformedURLException e) {
				e.printStackTrace();
			} catch(IOException e) {
				e.printStackTrace();
			} 
		}
		
	}
	
	
}
