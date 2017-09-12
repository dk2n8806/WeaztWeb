package com.common.entity.product;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.common.entity.AbstractPersistenceObject;
import com.common.entity.support.embeded.ImagePath;

@Entity
@Table(name="PRODUCT_GALLERY")
public class ProductGallery extends AbstractPersistenceObject 
{

	private static final long serialVersionUID = 1L;

	private Product product;
	private ImagePath imagePath;
	
	
	public static ProductGallery create(ImagePath image) {
		try {
			if(image == null) 
				throw new IllegalArgumentException("ImagePath is required");
			
			ProductGallery gallery = new ProductGallery();
			gallery.imagePath = image;
			return gallery;
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static ProductGallery createEntityInstance(Product product, ImagePath image) {
		try {
			if(product == null) 
				throw new IllegalArgumentException("product is required");
			if(image == null)
				throw new IllegalArgumentException("image_path is required");
			

			ProductGallery gallery = new ProductGallery();
			gallery.product = product;
			gallery.imagePath = image;
			return gallery;
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
	}
	

	@Embedded
	public ImagePath getImagePath() {return imagePath;}
	public void setImagePath(ImagePath imagePath) {	this.imagePath = imagePath;}
	
	@ManyToOne
	@JoinColumn(name="PRODUCT_ID", nullable=false, updatable=false)
	public Product getProduct() {		return product;	}
	public void setProduct(Product product) {	this.product = product;}
	
}
