package com.core.service.promo;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.common.entity.promo.NewLetter;
import com.core.service.promo.impl.NewLetterServiceImpl;

/**
 * 
 * @author dk
 *
 */
public interface NewLetterService {

	/** Create & save a new NewLetter entity
	 * {@link NewLetterServiceImpl#save(String)} */
	NewLetter save(String email);
	

	/** Retrieve a list of NewLetter entities 
	 * {@link NewLetterServiceImpl#getList(Pageable)}*/
	List<NewLetter> getList(Pageable pageable);
}
