package com.common.entity.tracking;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.common.entity.account.Account;

@Entity
@Table(name="TRACKER_INDEX_PAGE")
public class IndexPageTracker extends PageTracker {

	private static final long serialVersionUID = 1L;


	public static PageTracker create(Account account, String ip) {
		IndexPageTracker tracker = new IndexPageTracker();
		tracker.setAccount(account);
		tracker.setIp(ip);
		return tracker;
	}
	
	
}
