package com.core.service.stripe;


import com.common.adapter.stripe.StripeTos;
import com.common.entity.account.Account;
import com.common.entity.merchant.Merchant;
import com.common.entity.stripe.StripeAccount;
import com.common.entity.stripe.StripeCharge;
import com.common.entity.stripe.StripeCustomer;
import com.common.entity.stripe.StripeTransfer;
import com.core.service.stripe.impl.StripeCreatorServiceImpl;

/**
 * 
 * @author dk2n_
 *
 */
public interface StripeCreatorService {

	
	/** {@link StripeCreatorServiceImpl#createCustomerByCardToken(Account, String)} */
	StripeCustomer createCustomerByCardToken(Account account, String cardTokenId);
	
	
	/** {@link StripeCreatorServiceImpl#createMerchantByCardToken(Merchant, StripeTos, String)} */
	StripeAccount createMerchantByCardToken(Merchant merchant, StripeTos stripeTos, String cardTokenId);
	
	
	/** {@link StripeCreatorServiceImpl#createCharge(StripeCustomer, int)} */
	StripeCharge createCharge(StripeCustomer stripeCustomer, int chargeAmount);
	
	/** {@link StripeCreatorServiceImpl#createTransfer(StripeAccount, int)} */
	StripeTransfer createTransfer(StripeAccount stripeAccount, int transferAmount);
	
}
