package com.core.service.shippo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.adapter.shippo.ShipmentAdapter;
import com.common.entity.account.Account;
import com.common.entity.account.SimpleShipping;
import com.common.entity.merchant.MerchantProfile;
import com.common.entity.product.ParcelAdapter;
import com.common.entity.product.Product;
import com.common.type.ShippingType;
import com.core.service.account.SimpleShippingService;
import com.core.service.merchant.MerchantService;
import com.core.service.product.ParcelService;
import com.core.service.shippo.ShippoQuoteService;
import com.core.service.shippo.ShippoShipmentService;

@Service
@Transactional(readOnly=true)
public class ShippoQuoteServiceImpl implements ShippoQuoteService {

	
	
	@Autowired private ParcelService parcelService;
	@Autowired private MerchantService merchantService;
	@Autowired private SimpleShippingService simpleShippingService;
	
	@Autowired private ShippoShipmentService shippoShipmentService;
	
	
	
	
	@Override
	public Integer getShippingCost(Account customer, Product product) {
		Integer shippingCost = null;
		try {
			ShippingType type = product.getShippingInfo().getType();
			if(type == ShippingType.AUTO) {
				ParcelAdapter parcel = parcelService.getParcel(product).getParcelAdapter();
				if(parcel == null) 
					throw new IllegalArgumentException("Unable to retrieve parcel info");
			
				MerchantProfile merchantContact = merchantService.getProfile(product.getMerchant());
				if(merchantContact == null) 
					throw new IllegalArgumentException("Unable to retrieve merchant profile");
				
				
				SimpleShipping customerAddress = simpleShippingService.getPrimaryByAccount(customer);
				if(customerAddress == null) 
					throw new IllegalArgumentException("Unable to retrieve customer primary shipping address");

				
				// Get label quote
				ShipmentAdapter labelAdapter = 
						shippoShipmentService.create(merchantContact.getAddress()
																		, customerAddress.getAddress(), parcel);
				
				shippingCost = labelAdapter.getLowestRate().getAmount();
			} else {
				shippingCost = product.getShippingInfo().getShippingCost();
			}
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
		}
		return shippingCost;
	}


	

}
