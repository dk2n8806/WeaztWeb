package com.common.entity.product;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.common.entity.AbstractPersistenceObject;
import com.common.type.ProductStatus;

@Entity
@Table(name="DELETED_PRODUCT")
public class DeletedProduct extends AbstractPersistenceObject {

	private static final long serialVersionUID = 1L;
	private Product product;
//	private int totalCustomers;
//	private int totalSubscriptions;
//	private int totalEarned;
	
	
	public static DeletedProduct create(Product product) {
		try {

			if(product == null) {
				throw new IllegalArgumentException("Product cannot be null");
			}
			if(!product.getStatus().equals(ProductStatus.DELETED)) {
				throw new IllegalArgumentException("Invalid product status");
			}
			
			DeletedProduct p = new DeletedProduct();
			p.product = product;
			
			return p;
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@ManyToOne
	@JoinColumn(name="PRODUCT_ID", nullable=false, updatable=false, unique=true)
	public Product getProduct() {		return product;	}
	public void setProduct(Product product) {	this.product = product;}
}
