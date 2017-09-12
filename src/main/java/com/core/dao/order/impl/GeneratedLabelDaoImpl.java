package com.core.dao.order.impl;

import org.springframework.stereotype.Repository;

import com.common.entity.order.GeneratedLabel;
import com.core.dao.generic.GenericJpaRepository;
import com.core.dao.order.GeneratedLableDao;

@Repository
public class GeneratedLabelDaoImpl 
	extends GenericJpaRepository<GeneratedLabel, Long>
	implements GeneratedLableDao
{

}
