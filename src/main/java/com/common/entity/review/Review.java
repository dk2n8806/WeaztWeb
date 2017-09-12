package com.common.entity.review;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.common.entity.AbstractPersistenceObject;
import com.common.entity.account.Account;
import com.common.entity.product.Product;
import com.common.type.review.ReviewType;


@Entity
@Table(name="REVIEW")
public class Review extends AbstractPersistenceObject {

	
	
	private static final long serialVersionUID = 1L;

	private String content;
	private Product product;
	private Account account;
	private ReviewType type;
	

	/** 
	 * <h3>static Review createInstance</h3>
	 * 
	 * Create a new instance of Review object
	 * 
	 * @param account The reviewer
	 * @param product The product to be reviewed
	 * @param content The content of reviewed
	 * @param type
	 * @return Review Instnac of null
	 */
	public static Review createInstance(
			Account account, Product product, String content, ReviewType type) {
		try {
			if(content == null || content.length() < 10) {
				throw new IllegalArgumentException(
						"Invalid review content - either the content is null or invalid content length");
			}
			Review review = new Review();
			review.account = account;
			review.product = product;
			review.type = type;
			review.content = content;
			return review;
		} catch(IllegalArgumentException e) {
			return null;
		}
	}
	
	
	@Enumerated(EnumType.STRING)
	@Column(name="REVIEW_TYPE", nullable=false)
	public ReviewType getType() {		return type;	}
	public void setType(ReviewType type) {	this.type = type;}
	
	

	@JoinColumn(name="ACCOUNT_ID", updatable=false)
	@ManyToOne(fetch=FetchType.LAZY)
	public Account getAccount() {return account;}
	public void setAccount(Account account) {	this.account = account;}
	
	
	@JoinColumn(name="PRODUCT_ID", updatable=false)
	@ManyToOne(fetch=FetchType.LAZY)
	public Product getProduct() {		return product;	}
	public void setProduct(Product product) {	this.product = product;	}
	
	
	@Lob
	@Column(name="CONTENT", nullable=false)
	public String getContent() {		return content;	}
	public void setContent(String content) {	this.content = content;	}
	
	
	
	
}
