package com.core.service.stripe.adapter.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.common.adapter.stripe.CardAdapterAttribute;
import com.common.adapter.stripe.CardAdapter;
import com.common.adapter.stripe.CustomerAdapterAttribute;
import com.common.adapter.stripe.CustomerAdapter;
import com.common.adapter.stripe.util.StripeRequestManage;
import com.core.service.stripe.adapter.CustomerAdapterService;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Card;
import com.stripe.model.Customer;
import com.stripe.model.ExternalAccount;
import com.stripe.model.ExternalAccountCollection;


/******************************************************************
 * 
 * @author dk
 *
 */
@Service("stripeCustomerAdapterServiceImpl")
public class CustomerAdapterServiceImpl 
implements CustomerAdapterService {

	
	
/******************************************************
 * {@link CustomerAdapterService#create(CustomerAdapterAttribute)}
 */
	@Override
	public CustomerAdapter create(CustomerAdapterAttribute adapter) 
			throws AuthenticationException, InvalidRequestException
				, APIConnectionException, CardException, APIException 
	{
		if(adapter == null) {	return null; }
		Customer customer = Customer.create(adapter.getMetadata()
									, StripeRequestManage.getOptions());
		
		return CustomerAdapter.create(customer);
	}

	
	
	
/***********************************************************
 * {@link CustomerAdapterService#lookup(String)}
 */
	@Override
	public CustomerAdapter lookup(String tokenId) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		Customer customer = Customer.retrieve(tokenId, StripeRequestManage.getOptions());
		return CustomerAdapter.create(customer);
	}

	
	
	
/**********************************************************
 * {@link CustomerAdapterService#update(String, CustomerAdapterAttribute)}
 */
	@Override
	public CustomerAdapter update(String tokenId, CustomerAdapterAttribute adapter) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		Customer customer = Customer.retrieve(tokenId, StripeRequestManage.getOptions());
		customer.update(adapter.getMetadata(), StripeRequestManage.getOptions());
		return CustomerAdapter.create(customer);
	}


	
	
	
/*****************************************************************
 * {@link CustomerAdapterServiceImpl#delete(String)}
 */
	@Override
	public void delete(String tokenId) throws AuthenticationException,
			InvalidRequestException, APIConnectionException, CardException,
			APIException {
		Customer customer = Customer.retrieve(tokenId, StripeRequestManage.getOptions());
		customer.delete(StripeRequestManage.getOptions());
	}


	

/*****************************************************************
 * {@link CustomerAdapterService#getCards(String, CustomerAdapterAttribute)}
 */
	@SuppressWarnings("deprecation")
	@Override
	public List<CardAdapter> getCards(String tokenId) throws AuthenticationException,
			InvalidRequestException, APIConnectionException, CardException,
			APIException {
		ExternalAccountCollection collection = 
				Customer.retrieve(tokenId, StripeRequestManage.getOptions())
					    .getSources().all(new CardAdapterAttribute().getMetadata()
					    					, StripeRequestManage.getOptions());
		
		List<CardAdapter> result = new ArrayList<CardAdapter>();
		for(ExternalAccount card : collection.getData()) {
			result.add(CardAdapter.create((Card)card));
		}
		return result;
	}


	
	
	


}
