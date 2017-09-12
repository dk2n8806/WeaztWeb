package com.common.entity.tracking;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.common.entity.account.Account;

@Entity
@Table(name="TRACKER_PRODUCT_PAGE")
public class ProductPageTracker  extends PageTracker {

	private static final long serialVersionUID = 1L;


	public static PageTracker create(Account account, String ip) {
		ProductPageTracker tracker = new ProductPageTracker();
		tracker.setAccount(account);
		tracker.setIp(ip);
		return tracker;
	}
	

}
