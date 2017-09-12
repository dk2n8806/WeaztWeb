package com.core.service.shippo.adapter.impl;

import org.springframework.stereotype.Service;

import com.common.adapter.shippo.LabelAdapter;
import com.common.adapter.shippo.LabelAdapterAttribute;
import com.common.adapter.shippo.util.ShippoRequestManager;
import com.core.service.shippo.adapter.LabelAdapterService;
import com.shippo.exception.APIConnectionException;
import com.shippo.exception.APIException;
import com.shippo.exception.AuthenticationException;
import com.shippo.exception.InvalidRequestException;
import com.shippo.model.Rate;
import com.shippo.model.Transaction;

@Service("shippoTransactionAdapterServiceImpl")
public class LabelAdapterServiceImpl implements LabelAdapterService {

	
/**
 * Create and persist a new shipping_label.
 * Create a shippo_transaction
 *  
 *  {@link LabelAdapterService#create(LabelAdapterAttribute)}
 */
	@Override
	public LabelAdapter create(
			LabelAdapterAttribute adapterAttribute)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, APIException {
		Transaction transaction = Transaction.create(adapterAttribute.getTransactionData()
													, ShippoRequestManager.getOptions());	
		Rate rate = Rate.retrieve(String.valueOf(transaction.getRate()), ShippoRequestManager.getOptions());
		int amount = (int) (Double.valueOf(String.valueOf(rate.getAmount())) * 100);
		return LabelAdapter.create(transaction, amount);
	}

	
	
/****************************************************************************
 * 
 * {@link LabelAdapterService#lookup(String)}
 */
	@Override
	public LabelAdapter lookup(String tokenId)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, APIException {
		Transaction transaction = Transaction.retrieve(tokenId
													, ShippoRequestManager.getOptions());
		Rate rate = Rate.retrieve(String.valueOf(transaction.getRate()), ShippoRequestManager.getOptions());
		int amount = (int) (Double.valueOf(String.valueOf(rate.getAmount())) * 100);
		return LabelAdapter.create(transaction, amount);
	}

}
