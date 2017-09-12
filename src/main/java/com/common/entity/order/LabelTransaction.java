package com.common.entity.order;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import com.common.entity.AbstractPersistenceObject;


@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class LabelTransaction extends AbstractPersistenceObject {

	private static final long serialVersionUID = 1L;

}
