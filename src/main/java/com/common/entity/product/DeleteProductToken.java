package com.common.entity.product;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.common.entity.AbstractPersistenceToken;
import com.common.entity.PersistenceTokenPrefix;

@Entity
@Table(name="DELETE_PRODUCT_TOKEN")
public class DeleteProductToken extends AbstractPersistenceToken {

	private static final long serialVersionUID = 1L;

	private Product product;
	
	public DeleteProductToken() {}
	private DeleteProductToken(Product product) {
		super(PersistenceTokenPrefix.DELETE_PRODUCT_TOKEN_PREFIX);
		this.product = product;
	}
	
	public static DeleteProductToken create(Product product) {
		DeleteProductToken token = new DeleteProductToken(product);
		return token;
	}
	
	
	@ManyToOne
	@JoinColumn(name="PRODUCT_ID", nullable=false, updatable=false)
	public Product getProduct() {		return product;	}
	public void setProduct(Product product) {	this.product = product;}
	
}
