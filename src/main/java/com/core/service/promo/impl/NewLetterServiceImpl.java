package com.core.service.promo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.entity.promo.NewLetter;
import com.core.dao.promo.NewLetterDao;
import com.core.service.promo.NewLetterService;

/**
 * 
 * @author dk
 *
 */
@Service
@Transactional
public class NewLetterServiceImpl implements NewLetterService {

	
	@Autowired private NewLetterDao dao;
	
	
/**
 * Create & save new NewLetter entity
 * 
 * @param email
 * @return new entity or null
 * {@link NewLetterService#save(String)}
 */
	@Override
	public NewLetter save(String email) {
		NewLetter letter = NewLetter.create(email);
		if(letter != null) {
			dao.persist(letter);
		}
		return letter;
	}
	
	
	
/**
 * Retrieve a list of NewLetter entities
 * 
 * @return NewLetter list
 * {@link NewLetterService#getList(Pageable)}
 */
	@Override
	public List<NewLetter> getList(Pageable pageable) {
		return dao.getList(pageable);
	}

	
}
