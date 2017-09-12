package com.core.service.order;

import com.common.adapter.shippo.LabelAdapter;
import com.common.entity.order.GeneratedLabel;

/**
 * 
 * @author dk
 *
 */
public interface GeneratedLabelService {

	/** 
	 * Create & save a new {@link GeneratedLabel} entity
	 */
	GeneratedLabel save(LabelAdapter labelAdapter); 
}
