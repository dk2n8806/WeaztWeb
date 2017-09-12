package com.core.service;

import java.io.Serializable;
import java.util.List;

public interface GenericService<E, I extends Serializable>{

	E findById(I id);
	E getReference(I id);
	long getRowCount();
	
}
