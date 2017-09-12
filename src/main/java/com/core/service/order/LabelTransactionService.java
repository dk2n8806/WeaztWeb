package com.core.service.order;

import com.common.entity.order.LabelTransaction;
import com.core.service.GenericService;
import com.core.service.order.impl.LabelTransactionServiceImpl;

/**
 * 
 * @see LabelTransactionServiceImpl
 * @author dk
 *
 */
public interface LabelTransactionService 
	extends GenericService<LabelTransaction, Long>
{

	/** {@link LabelTransactionServiceImpl} */
	void refresh(LabelTransaction label);
}
