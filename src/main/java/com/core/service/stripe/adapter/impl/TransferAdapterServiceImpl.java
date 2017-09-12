package com.core.service.stripe.adapter.impl;


import org.springframework.stereotype.Service;

import com.common.adapter.stripe.TransferAdapterAttribute;
import com.common.adapter.stripe.TransferAdapter;
import com.common.adapter.stripe.util.StripeRequestManage;
import com.core.service.stripe.adapter.TransferAdapterService;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Transfer;


/******************************************************************
 * 
 * @author dk
 *
 */
@Service("stripeTransferAdapterServiceImpl")
public class TransferAdapterServiceImpl implements TransferAdapterService {

	
/**********************************************************************
 * 
 *  {@link TransferAdapterService#create(TransferAdapterAttribute)}
 * */
	@Override
	public TransferAdapter create(TransferAdapterAttribute adapter) 
			throws AuthenticationException, InvalidRequestException
			, APIConnectionException, CardException, APIException 
	{
		Transfer transfer = Transfer.create(adapter.getTransferMetadata()
											, StripeRequestManage.getOptions());
		return TransferAdapter.convert(transfer);
	}
	
	
	
	
	
	

/**********************************************************************
 * 
 *  {@link TransferAdapterService#lookup(String)}
 * */
	@Override
	public TransferAdapter lookup(String tokenId)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, CardException, APIException {
		Transfer transfer = Transfer.retrieve(tokenId
											, StripeRequestManage.getOptions());
		return TransferAdapter.convert(transfer);
	}


}
