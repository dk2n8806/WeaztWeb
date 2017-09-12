package com.common.entity.order;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.common.adapter.shippo.LabelAdapter;


@StaticMetamodel(GeneratedLabel.class)
public class GeneratedLabel_ extends LabelTransaction_ {

	public static volatile SingularAttribute<GeneratedLabel, LabelAdapter> labelAdapter;
}
