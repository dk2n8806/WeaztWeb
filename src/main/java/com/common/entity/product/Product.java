package com.common.entity.product;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.common.entity.AbstractPersistenceObject;
import com.common.entity.category.Category;
import com.common.entity.merchant.Merchant;
import com.common.entity.product.embeded.BasicInfo;
import com.common.entity.product.embeded.ShippingInfo;
import com.common.entity.product.embeded.SubscriptionInfo;
import com.common.entity.support.embeded.ImagePath;
import com.common.type.ProductStatus;


/**
 * 
 * @author dk2n_
 *
 */
@Entity
@Table(name="PRODUCT")
public class Product extends AbstractPersistenceObject {

	private static final Logger logger = LogManager.getLogger();
	
	private static final long serialVersionUID = 1L;
	private Merchant merchant;
	private Category category;
	private ProductStatus status;
	private ImagePath displayImage;
	private List<ProductGallery> gallery;
	private BasicInfo basicInfo;
	private ShippingInfo shippingInfo;
	private SubscriptionInfo subscriptionInfo;
	
	
	
	public static Product create(
			Merchant merchant, Category category
			, BasicInfo basicInfo, SubscriptionInfo subscriptionInfo, ShippingInfo shippingInfo
			, ImagePath displayImage, List<ProductGallery> gallery) 
	{
		try {
			if(category == null)
				throw new IllegalArgumentException("category entity is required");
			if(displayImage == null) 
				throw new IllegalArgumentException("product display_image is required");
			if(basicInfo == null) 
				throw new IllegalArgumentException("product basic_info is required");
			if(shippingInfo == null)
				throw new IllegalArgumentException("product shipping_info is required");
			if(subscriptionInfo == null)
				throw new IllegalArgumentException("product subscription_info is required");
			Product product = new Product();
			
			product.status = ProductStatus.PUBLIC;
			product.merchant = merchant;
			product.category = category;
			product.basicInfo = basicInfo;
			product.shippingInfo = shippingInfo;
			product.subscriptionInfo = subscriptionInfo;
			product.displayImage = displayImage;
			product.gallery = gallery;
			
			if(gallery != null) {
				for(ProductGallery photo : gallery) {
					photo.setProduct(product);
				}
			}
			return product;
		} catch(IllegalArgumentException e) {
			//e.printStackTrace();
			logger.error(e.getMessage());
			return null;
		}
	}
	
	
	
	












	@Override
	public String toString() {
		return getClass().getName() + " {\n\tmerchant: " + merchant.getId() 
				+ "\n\tcategory: " + category.getId() + "\n\tstatus: "
				+ status + "\n\tdisplayImage: " + displayImage + "\n\tbasicInfo: "
				+ basicInfo + "\n\tshippingInfo: " + shippingInfo + "\n\tsubscriptionInfo: " + subscriptionInfo + "\n}";
	}
















	@Embedded
	public BasicInfo getBasicInfo() {	return basicInfo;}
	public void setBasicInfo(BasicInfo basicInfo) {		this.basicInfo = basicInfo;}


	@Embedded
	public ShippingInfo getShippingInfo() {	return shippingInfo;}
	public void setShippingInfo(ShippingInfo shippingInfo) {this.shippingInfo = shippingInfo;}

	@Embedded
	public SubscriptionInfo getSubscriptionInfo() {		return subscriptionInfo;}
	public void setSubscriptionInfo(SubscriptionInfo subscriptionInfo) {this.subscriptionInfo = subscriptionInfo;}


	@Embedded
	public ImagePath getDisplayImage() {return displayImage;}
	public void setDisplayImage(ImagePath displayImage) {	this.displayImage = displayImage;}

	

	@OneToMany(mappedBy="product", cascade=CascadeType.ALL, orphanRemoval=true)
	public List<ProductGallery> getGallery() {		return gallery;}
	public void setGallery(List<ProductGallery> gallery) {	this.gallery = gallery;}








	@ManyToOne
	@JoinColumn(name="MERCHANT_ID", nullable=false, updatable=false)
	public Merchant getMerchant() {	return merchant;}
	public void setMerchant(Merchant merchant) {this.merchant = merchant;}



	@ManyToOne
	@JoinColumn(name="CATEGORY_ID", nullable=false, updatable=false)
	public Category getCategory() {	return category;}
	public void setCategory(Category category) {this.category = category;}

	

	@Enumerated(EnumType.STRING)
	@Column(name="STATUS", nullable=false)
	public ProductStatus getStatus() {	return status;}
	public void setStatus(ProductStatus status) {	this.status = status;}
	
	
	
	
}
