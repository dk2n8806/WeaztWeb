package com.common.entity.order;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.common.adapter.shippo.LabelAdapter;

@Entity
@Table(name="GENERATED_LABEL")
public class GeneratedLabel extends LabelTransaction {

	private static final long serialVersionUID = 1L;
	
	private LabelAdapter labelAdapter;
	
	
/** 
 * CreateInstance
 * 
 * Create a new instance of shipping label
 * 
 * @param labelAdapter
 * @return
 */
	public static GeneratedLabel createEntityInstance(LabelAdapter labelAdapter) {
		try {
			GeneratedLabel label = new GeneratedLabel();
			label.labelAdapter = labelAdapter;
			return label;
		} catch(Exception e) {
			return null;
		}
	}
	
	
	@Embedded
	public LabelAdapter getLabelAdapter() {return labelAdapter;}
	public void setLabelAdapter(LabelAdapter labelAdapter) {	this.labelAdapter = labelAdapter;}

}
