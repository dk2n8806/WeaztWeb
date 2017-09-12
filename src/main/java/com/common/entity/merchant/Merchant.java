package com.common.entity.merchant;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.common.entity.AbstractPersistenceObject;
import com.common.entity.account.Account;
import com.common.type.AccountStatus;
import com.common.type.MerchantStatus;
import com.common.type.Role;


/**
 * 
 * @author dk2n_
 *
 */
@Entity
@Table(name="MERCHANT")
public class Merchant extends AbstractPersistenceObject {

	private static final long serialVersionUID = 1L;

	private String businessName;
	private String websiteUrl;
	private MerchantProfile profile;
	private Account account;
	private MerchantStatus status;
	private CommissionRate commissionRate;
	private TaxRate taxRate;
	
	
	/** :::
	 * 
	 * @param account
	 * @param businessName
	 * @param websiteUrl
	 * @param profile
	 * @return
	 */
	public static Merchant createEntityInstance(
			Account account, String businessName, String websiteUrl, MerchantProfile profile) {
		try {
			if(account == null || account.getStatus().equals(AccountStatus.DEACTIVE)) {
				throw new IllegalArgumentException("Invalid account status");
			}
			if(!account.getRole().equals(Role.CUSTOMER))
				throw new IllegalArgumentException("Invalid account role");
			if(businessName == null || businessName.length() < 3) {
				throw new IllegalArgumentException("Invalid business_name");
			}
			if(profile == null) {
				throw new IllegalArgumentException("Invalid Merchant_Profile");
			}
			Merchant merchant = new Merchant();
			merchant.account = account;
			merchant.businessName = businessName;
			merchant.websiteUrl = websiteUrl;
			merchant.profile = profile;
			merchant.status = MerchantStatus.ACTIVE;
			merchant.commissionRate = CommissionRate.create();
			merchant.taxRate = TaxRate.create();
			return merchant;
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	

	@Enumerated(EnumType.STRING)
	@Column(name="STATUS", nullable=false)
	public MerchantStatus getStatus() {return status;}
	public void setStatus(MerchantStatus status) {this.status = status;}





	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="COMMISSION_RATE_ID", nullable=false, unique=true, updatable=false)
	public CommissionRate getCommissionRate() {	return commissionRate;}
	public void setCommissionRate(CommissionRate commissionRate) {	this.commissionRate = commissionRate;}


	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="TAX_RATE_ID", nullable=false, unique=true, updatable=false)
	public TaxRate getTaxRate() {	return taxRate;}
	public void setTaxRate(TaxRate taxRate) {	this.taxRate = taxRate;}




	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="account_id", unique=true, nullable=false, updatable=false)
	public Account getAccount() {return account;}
	public void setAccount(Account account) {this.account = account;}
	
	
	@Column(name="BUSINESS_NAME", nullable=false)
	public String getBusinessName() {return businessName;}
	public void setBusinessName(String businessName) {this.businessName = businessName;}
	
	
	@Column(name="WEBSITE_URL")
	public String getWebsiteUrl() {	return websiteUrl;}
	public void setWebsiteUrl(String websiteUrl) {	this.websiteUrl = websiteUrl;}
	
	
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="MERCHANT_PROFILE_ID", nullable=false, updatable=false, unique=true)
	public MerchantProfile getProfile() {return profile;}
	public void setProfile(MerchantProfile profile) {this.profile = profile;}
	
	
	
}
