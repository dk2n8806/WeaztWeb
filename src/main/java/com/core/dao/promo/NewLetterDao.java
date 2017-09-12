package com.core.dao.promo;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.common.entity.promo.NewLetter;
import com.core.dao.generic.GenericRepository;
import com.core.dao.promo.impl.NewLetterDaoImpl;

public interface NewLetterDao 
extends GenericRepository<NewLetter, Long>
{
	/** Retrieve a list of NewLetter entities
	 * {@link NewLetterDaoImpl#getList(Pageable)}*/
	List<NewLetter> getList(Pageable pageable);
}
