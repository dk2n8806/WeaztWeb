package com.core.service.order.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.adapter.shippo.LabelAdapter;
import com.common.entity.order.GeneratedLabel;
import com.core.dao.order.GeneratedLableDao;
import com.core.service.order.GeneratedLabelService;


/**
 * 
 * @author dk
 *
 */
@Service
@Transactional
public class GeneratedLabelServiceImpl implements GeneratedLabelService 
{
	
	@Autowired private GeneratedLableDao dao;

	
/**
 * Create & persist a new {@link GeneratedLabel} entity
 * {@link GeneratedLabelService#save(LabelAdapter)}
 */
	@Override
	public GeneratedLabel save(LabelAdapter labelAdapter) {
		GeneratedLabel label = GeneratedLabel.createEntityInstance(labelAdapter);
		if(label != null) {
			dao.persist(label);
		}
		return label;
	}

}
