package com.core.service.order.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.entity.order.LabelTransaction;
import com.core.dao.order.LabelTransactionDao;
import com.core.service.order.LabelTransactionService;


/**
 * 
 * @author dk
 *
 */
@Service
@Transactional
public class LabelTransactionServiceImpl 
	implements LabelTransactionService
{
	
	@Autowired private LabelTransactionDao dao;

	@Override
	public LabelTransaction findById(Long id) {
		return dao.findById(id);
	}

	

	@Override
	public long getRowCount() {
		return dao.getRowCount();
	}


	@Override
	public LabelTransaction getReference(Long id) {
		return dao.getReference(id);
	}

	@Override
	public void refresh(LabelTransaction label) {
		dao.refresh(label);
	}

}
