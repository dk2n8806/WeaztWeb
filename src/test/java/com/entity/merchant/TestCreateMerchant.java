package com.entity.merchant;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.BaseTest;
import com.common.adapter.shippo.ShippoAddressAdapter;
import com.common.entity.account.Account;
import com.common.entity.merchant.Merchant;
import com.common.entity.support.embeded.Phone;
import com.core.service.account.AccountService;
import com.core.service.merchant.MerchantService;

public class TestCreateMerchant extends BaseTest {

	@Autowired private MerchantService merchantService;
	@Autowired private AccountService accountService;
	
	private Account account;
	private String companyName;
	private String webstie;
	private Phone phone;
	private ShippoAddressAdapter address;
	
	
	@Before
	public void init() {
		account = accountService.findById(new Long(44));
		
		companyName = "derek";
		phone = Phone.create("1231231234");
		//address = ShippoAddressAdapter.createEntityInstance("street", "city", USAStates.UTAH, "zipcode", "token");
	}
	
	
	@Test
	public void test() {
		Merchant merchant = merchantService.save(account, companyName, webstie, phone, address);
		
		assertNotNull(merchant.getId());
	}
	
	
}
