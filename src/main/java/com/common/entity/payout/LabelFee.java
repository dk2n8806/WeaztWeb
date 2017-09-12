package com.common.entity.payout;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.common.entity.merchant.Merchant;
import com.common.entity.order.GeneratedLabel;
import com.common.type.FeeStatus;

@Entity
@Table(name="LABEL_FEE")
public class LabelFee extends MerchantFee {

	private static final long serialVersionUID = 1L;
	private GeneratedLabel label;
	
	public static LabelFee create(Merchant merchant, GeneratedLabel label) {
		try {
			if(merchant == null)
				throw new IllegalArgumentException("merchant entity is required");
			if(label == null)
				throw new IllegalArgumentException("label entity is required");
			if(label.getLabelAdapter().getShippingCost() <= 0)
				throw new IllegalArgumentException("amount must > 0");
			
			LabelFee fee = new LabelFee();
			fee.setMerchant(merchant);
			fee.setAmount(label.getLabelAdapter().getShippingCost());
			fee.setStatus(FeeStatus.PENDING);
			fee.label = label;
			return fee;
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@ManyToOne
	@JoinColumn(name="LABEL_ID", nullable=false, updatable=false, unique=true)
	public GeneratedLabel getLabel() {	return label;}
	public void setLabel(GeneratedLabel label) {	this.label = label;}
}
