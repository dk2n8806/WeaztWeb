package com.entity.merchant;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.BaseTest;
import com.common.entity.account.Account;
import com.common.entity.merchant.CommissionRate;
import com.common.entity.merchant.Merchant;
import com.common.entity.merchant.MerchantProfile;
import com.common.entity.merchant.TaxRate;
import com.core.service.merchant.MerchantService;

public class TestGetMechantService extends BaseTest{

	@Autowired private MerchantService merchantService;
	private Merchant merchant;
	
	
	@Before
	public void init() {
		merchant = merchantService.getReference(new Long(26));
	}
	
	
	@Test
	public void testGetByAccount() {

		Account account = merchantService.getAccount(merchant);
		Merchant other = merchantService.getByAccount(account);
		assertEquals(other.getId(), merchant.getId());
	}
	
	
	@Test
	public void testGetAccount() {
		Account account = merchantService.getAccount(merchant);
		assertNotNull(account);
		assertNotNull(account.getStatus());
	}
	
	@Test
	public void testGetTaxRate() {
		TaxRate tax = merchantService.getTaxRate(merchant);
		assertNotNull(tax);
		assertNotNull(tax.getRateValue());
	}
	
	@Test
	public void testGetCommissionRate() {
		CommissionRate rate = merchantService.getCommissionRate(merchant);
		assertNotNull(rate);
		assertNotNull(rate.getRateValue());
	}
	
	@Test
	public void testGetProfile() {
		MerchantProfile profile = merchantService.getProfile(merchant);
		assertNotNull(profile);
		assertNotNull(profile.getPhone().getDisplayableNumber());
	}
}
