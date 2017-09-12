package com.core.service.stripe.adapter.impl;



import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.common.adapter.stripe.AccountAdapterAttribute;
import com.common.adapter.stripe.AccountAdapter;
import com.common.adapter.stripe.CardAdapterAttribute;
import com.common.adapter.stripe.CardAdapter;
import com.common.adapter.stripe.CardTokenAdapterAttribute;
import com.common.adapter.stripe.util.StripeRequestManage;
import com.core.service.stripe.adapter.AccountAdapterService;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Account;
import com.stripe.model.Card;
import com.stripe.model.ExternalAccount;
import com.stripe.model.ExternalAccountCollection;

/*************************************************************************
 * 
 * @author dk
 *
 */
@Service("stripeAccountAdapterServiceImpl")
public class AccountAdapterServiceImpl 
implements AccountAdapterService {


	
	
	
	
/**************************************************************
 * 
 * {@link AccountAdapterServiceImpl#createManagedAccount(AccountAdapterAttribute)}
 */
	@Override
	public AccountAdapter createManagedAccount(AccountAdapterAttribute adapter) 
			throws AuthenticationException, InvalidRequestException
				, APIConnectionException, CardException, APIException 
	{
		Account account = Account.create(adapter.getManageMetadata(), StripeRequestManage.getOptions());
		AccountAdapter result = AccountAdapter.create(account);
		return result;
	}


	
	
	
	
/**************************************************************
 * 
 * {@link AccountAdapterService#createStandaloneAccount(AccountAdapterAttribute)}
 */
	@Override
	public AccountAdapter createStandaloneAccount(AccountAdapterAttribute adapter)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, CardException, APIException 
	{
		Account account = Account.create(adapter.getStandaloneMetadata(), StripeRequestManage.getOptions());
		AccountAdapter result = AccountAdapter.create(account);
		return result;
	}

	
	
	
	
/**************************************************************
 * 
 * {@link AccountAdapterService#lookup(String)}
 */
	@Override
	public AccountAdapter lookup(String tokenId)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, CardException, APIException {
		Account account = Account.retrieve(tokenId, StripeRequestManage.getOptions());
		return AccountAdapter.create(account);
	}





/************************************************************
 * 
 * {@link AccountAdapterService#update(String, AccountAdapterAttribute)}
 */
	@Override
	public AccountAdapter update(String tokenId, AccountAdapterAttribute adapter)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, CardException, APIException {
		Account account = Account.retrieve(tokenId, StripeRequestManage.getOptions());
		Account updated = account.update(adapter.getMetadata()
										, StripeRequestManage.getOptions());
		return AccountAdapter.create(updated);
	}











	
/*************************************************************************
 * 
 * {@link AccountAdapterService#setExternalAccount(String, CardTokenAdapterAttribute)}
 */
	@Override
	public AccountAdapter setExternalAccount(String tokenId,
			CardTokenAdapterAttribute adapter) throws AuthenticationException,
			InvalidRequestException, APIConnectionException, CardException,
			APIException {
		Account account = Account.retrieve(tokenId, StripeRequestManage.getOptions());
		account.getExternalAccounts().create(adapter.getMetadata(), StripeRequestManage.getOptions());
		return AccountAdapter.create(account);
	}








	
	
	
	
/****************************************************************************
 * {@link AccountAdapterService#getCards(String, CardAdapterAttribute)}
 */
	@SuppressWarnings("deprecation")
	@Override
	public List<CardAdapter> getCards(String tokenId)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, CardException, APIException {
		ExternalAccountCollection collection = 
				Account.retrieve(tokenId, StripeRequestManage.getOptions())
						.getExternalAccounts().all(new CardAdapterAttribute().getMetadata()
												, StripeRequestManage.getOptions());
		List<CardAdapter> result = new ArrayList<CardAdapter>();
		for(ExternalAccount card : collection.getData()) {
			result.add(CardAdapter.create((Card)card));
		}
		return result;
	}







}
