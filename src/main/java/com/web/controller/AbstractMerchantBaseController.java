package com.web.controller;



import com.common.entity.account.Account;
import com.common.entity.merchant.Merchant;
import com.common.exception.MerchantException;
import com.common.type.MerchantStatus;
import com.common.type.Role;

public class AbstractMerchantBaseController {
	
	/** :::
	 * <p>Prerequisite check about account merchant eligibility</p>
	 * 
	 * @param account
	 * @return
	 * @throws MerchantException
	 */
	protected Merchant getAuthorizedMerchant(Account account) throws MerchantException
	{
		Merchant merchant = account.getMerchant();
		if(merchant == null)
			return null;
		if(!account.getRole().equals(Role.MERCHANT))
			throw new MerchantException("Invalid merchant account");
		if(!merchant.getStatus().equals(MerchantStatus.ACTIVE))
			throw new MerchantException("Invalid merchant status");
		return merchant;
	}

}
