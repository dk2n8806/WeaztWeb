package com.core.service.shippo.adapter;

import com.common.adapter.shippo.LabelAdapter;
import com.common.adapter.shippo.LabelAdapterAttribute;
import com.core.service.shippo.adapter.impl.LabelAdapterServiceImpl;
import com.shippo.exception.APIConnectionException;
import com.shippo.exception.APIException;
import com.shippo.exception.AuthenticationException;
import com.shippo.exception.InvalidRequestException;

/******************************************************************************
 * 
 * @author dk
 *
 */
public interface LabelAdapterService {

	
	/** {@link LabelAdapterServiceImpl#create(LabelAdapterAttribute)} */
	LabelAdapter create(LabelAdapterAttribute adapterAttribute)
			throws AuthenticationException, InvalidRequestException
			, APIConnectionException, APIException;
	
	
	/** {@link LabelAdapterServiceImpl#lookup(String)} */
	LabelAdapter lookup(String tokenId)
			throws AuthenticationException, InvalidRequestException
			, APIConnectionException, APIException;
}
