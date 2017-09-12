package com.core.service.stripe.impl;

import java.math.BigDecimal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.adapter.stripe.AccountAdapter;
import com.common.adapter.stripe.AccountAdapterAttribute;
import com.common.adapter.stripe.CardTokenAdapterAttribute;
import com.common.adapter.stripe.ChargeAdapter;
import com.common.adapter.stripe.ChargeAdapterAttribute;
import com.common.adapter.stripe.CustomerAdapter;
import com.common.adapter.stripe.CustomerAdapterAttribute;
import com.common.adapter.stripe.StripeTos;
import com.common.adapter.stripe.TransferAdapter;
import com.common.adapter.stripe.TransferAdapterAttribute;
import com.common.entity.account.Account;
import com.common.entity.merchant.Merchant;
import com.common.entity.stripe.StripeAccount;
import com.common.entity.stripe.StripeCharge;
import com.common.entity.stripe.StripeCustomer;
import com.common.entity.stripe.StripeTransfer;
import com.core.service.stripe.StripeAccountService;
import com.core.service.stripe.StripeCreatorService;
import com.core.service.stripe.StripeCustomerService;
import com.core.service.stripe.adapter.AccountAdapterService;
import com.core.service.stripe.adapter.ChargeAdapterService;
import com.core.service.stripe.adapter.CustomerAdapterService;
import com.core.service.stripe.adapter.TransferAdapterService;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;

@Service
@Transactional
public class StripeCreatorServiceImpl implements StripeCreatorService {

	
	private static final Logger logger = LogManager.getLogger();
	
	@Autowired private StripeAccountService stripeAccountService;
	@Autowired private StripeCustomerService stripeCustomerService;
	
	
	@Autowired private AccountAdapterService accountAdapterService;
	@Autowired private CustomerAdapterService customerAdapterService;
	@Autowired private ChargeAdapterService chargeAdapterService;
	@Autowired private TransferAdapterService transferAdapterService;

	
	
	
/*****************************************************************************
 * 
 * {@link StripeCreatorService#createCustomerByCardToken(Account, String)}
 */
	@Override
	public StripeCustomer createCustomerByCardToken(Account account,
			String tokenId) {
		CardTokenAdapterAttribute cardToken = new CardTokenAdapterAttribute(tokenId);
		CustomerAdapterAttribute adapter = new CustomerAdapterAttribute(cardToken );
		CustomerAdapter customerAdapter;
		try {
			customerAdapter = customerAdapterService.create(adapter);
			
			StripeCustomer stripeCustomer = stripeCustomerService.create(account, customerAdapter);
			return stripeCustomer;
		} catch (AuthenticationException | InvalidRequestException
				| APIConnectionException | CardException | APIException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	
	
	

/*********************************************************************************
 * {@link StripeCreatorService#createStripeAccountByCardToken(Merchant, String)}
 */
	@Override
	public StripeAccount createMerchantByCardToken(Merchant merchant
			, StripeTos stripeTos, String cardTokenId) {
		CardTokenAdapterAttribute cardToken = new CardTokenAdapterAttribute(cardTokenId);
		String displayName = merchant.getBusinessName();
		AccountAdapterAttribute adapter = new AccountAdapterAttribute(displayName, stripeTos, cardToken);
		AccountAdapter accountAdapter;
		try {
			accountAdapter = accountAdapterService.createManagedAccount(adapter );
			StripeAccount stripeAccount = stripeAccountService.create(merchant, accountAdapter);
			return stripeAccount;
		} catch (AuthenticationException | InvalidRequestException
				| APIConnectionException | CardException | APIException e) {
			e.printStackTrace();
		}
		return null;
	}




	
		
	/** :::
	 * <p>Create stripe_charge on a stripe_customer with a specified charge_amount</p>
	 * 
	 * {@link StripeCreatorService#createCharge(StripeCustomer, BigDecimal)}
	 */
	@Override
	public StripeCharge createCharge(
			StripeCustomer stripeCustomer, int chargeAmount) 
	{
		if(stripeCustomer == null) {
			logger.error("[ERROR] stripe_customer must not be null");
			return null;
		}

		try {
			//logger.info("stripe_customer: " + stripeCustomer.getCustomerAdapter().getTokenId());
			ChargeAdapterAttribute chargeAdapter = 
					new ChargeAdapterAttribute(stripeCustomer.getCustomerAdapter(), chargeAmount);
			ChargeAdapter adapter = chargeAdapterService.create(chargeAdapter );
			if(adapter == null) {
				logger.error("null charge_adapter");
			}
			StripeCharge stripeCharge = StripeCharge.create(stripeCustomer, adapter);
			return stripeCharge;
		} catch (AuthenticationException | InvalidRequestException
				| APIConnectionException | CardException | APIException e) {
			e.printStackTrace();
		}
		return null;
	}





	
	
/***********************************************************************************
 * 
 * {@link StripeCreatorService#createTransfer(StripeAccount, BigDecimal)}
 */
	@Override
	public StripeTransfer createTransfer(StripeAccount stripeAccount, int transferAmount) {
		if(stripeAccount == null) {
			logger.error("Unable to find stripe_account");
			return null;
		}
		
		AccountAdapter account = stripeAccount.getAccountAdapter();
		TransferAdapterAttribute adapter = new TransferAdapterAttribute(account , transferAmount);
		TransferAdapter transferAdapter;
		try {
			transferAdapter = transferAdapterService.create(adapter );
			//StripeTransfer stripeTransfer = stripeTransferService.create(stripeAccount, transferAdapter );
			StripeTransfer stripeTransfer = StripeTransfer.create(stripeAccount, transferAdapter);
			return stripeTransfer;
		} catch (AuthenticationException | InvalidRequestException
				| APIConnectionException | CardException | APIException e) {
			e.printStackTrace();
		}
		return null;
	}

}
