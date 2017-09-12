package com.core.dao.order;

import com.common.entity.order.LabelTransaction;
import com.core.dao.generic.GenericRepository;
import com.core.dao.order.impl.LabelTransactionDaoImpl;


/**
 * 
 * @see LabelTransactionDaoImpl
 * @author dk
 *
 */
public interface LabelTransactionDao 
	extends GenericRepository<LabelTransaction, Long>
{
	
	void refresh(LabelTransaction labelTransaction);

}
