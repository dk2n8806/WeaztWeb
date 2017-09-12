package com.entity.shipping;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.BaseTest;
import com.common.adapter.shippo.AddressAdapterAttribute;
import com.common.entity.account.Account;
import com.common.entity.account.SimpleShipping;
import com.common.entity.address.Address;
import com.common.type.USAStates;
import com.core.service.account.AccountService;
import com.core.service.account.SimpleShippingService;
import com.core.service.address.AddressService;
import com.core.service.shippo.ShippoAddressService;

public class TestCreateSimpleShipment extends BaseTest {
	
	@Autowired private SimpleShippingService simpleShippingService;
	@Autowired private ShippoAddressService shippoAddressService;
	@Autowired private AccountService accountService;
	
	private Account account;
	
	@Before
	public void inti() {
		account = accountService.findById(new Long(8));
	}
	
	
	@Test
	public void test() {

		String _name = "customer";
		String _street = "500 West Broadway";
		String _city = "San Diego";
		String _zipcode = "92101";
		Address address = shippoAddressService.create(
				new AddressAdapterAttribute(_name, _street, _city
						, USAStates.CALIFORNIA, Address.COUNTRY_DEFAULT, _zipcode));
		// Create a new simple shipping_info for the account
		SimpleShipping shipping = simpleShippingService.save(account, address);
		System.out.println(shipping.getId());
	}
}
