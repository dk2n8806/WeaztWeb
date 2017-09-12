package com.core.dao.order.impl;

import org.springframework.stereotype.Repository;

import com.common.entity.order.LabelTransaction;
import com.core.dao.generic.GenericJpaRepository;
import com.core.dao.order.LabelTransactionDao;


/**
 * 
 * @author dk
 *
 */
@Repository
public class LabelTransactionDaoImpl 
	extends GenericJpaRepository<LabelTransaction, Long>
	implements LabelTransactionDao
{

	@Override
	public void refresh(LabelTransaction labelTransaction) {
		entityManager.refresh(labelTransaction);
	}

}
