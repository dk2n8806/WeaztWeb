package com.common.entity.promo;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.common.entity.AbstractPersistenceObject;
import com.common.entity.account.Account;

@Entity
@Table(name="CLAIMED_PROMOTION")
public class ClaimedPromotion extends AbstractPersistenceObject {

	
	private static final long serialVersionUID = 1L;
	private Account account;
	private Promotion promotion;
	
	
	public static ClaimedPromotion create(Account account, Promotion promotion) {
		try {
			if(promotion == null || !promotion.isAppliable()) 
				throw new IllegalArgumentException("promotion is not applicable");
			
			ClaimedPromotion claim = new ClaimedPromotion();
			claim.account = account;
			claim.promotion = promotion;
		return claim;
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	@ManyToOne
	@JoinColumn(name="ACCOUNT_ID", nullable=false, updatable=false)
	public Account getAccount() {return account;}
	public void setAccount(Account account) {this.account = account;}
	
	

	@ManyToOne
	@JoinColumn(name="PROMOTION_ID", nullable=false, updatable=false)
	public Promotion getPromotion() {	return promotion;}
	public void setPromotion(Promotion promotion) {this.promotion = promotion; }
	
	
}
