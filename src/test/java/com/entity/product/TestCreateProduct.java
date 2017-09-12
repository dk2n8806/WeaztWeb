package com.entity.product;

import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.BaseTest;
import com.common.entity.category.Category;
import com.common.entity.merchant.Merchant;
import com.common.entity.product.Product;
import com.common.entity.product.ProductGallery;
import com.common.entity.product.embeded.BasicInfo;
import com.common.entity.product.embeded.ShippingInfo;
import com.common.entity.product.embeded.SubscriptionInfo;
import com.common.entity.support.embeded.ImagePath;
import com.common.type.MerchantStatus;
import com.common.type.ShippingType;
import com.common.util.PageSearch;
import com.core.service.category.CategoryService;
import com.core.service.merchant.MerchantService;
import com.core.service.product.ProductService;

public class TestCreateProduct extends BaseTest{

	@Autowired private ProductService productService;
	@Autowired private MerchantService merchantService;
	@Autowired private CategoryService categoryService;
	
	private Merchant merchant;
	private Category category;
	private BasicInfo basicInfo;
	private SubscriptionInfo subscriptionInfo;
	private ShippingInfo shippingInfo;
	private ImagePath displayImage;
	private List<ProductGallery> gallery;
	
	
	@Before
	public void init() {
		
//		gallery = new ArrayList<>();
//		for(int i = 0; i < 3; i++) {
//			ProductGallery p = new ProductGallery();
//			p.setImagePath(ImagePath.create("exaple path " + i));
//			gallery.add(p);
//		}
	
	}
	
	
	@Test
	public void test() {
		
		merchant = merchantService.getMerchants(MerchantStatus.ACTIVE,null,new PageSearch(0, 1)).get(0);
		List<Category> categories = categoryService.getList();
		
		for(int i = 0; i < 100; i++) {
			category = categories.get(new Random().nextInt(categories.size()));
			basicInfo = BasicInfo.create(1000, "this is a title example", "this is a description example");
			shippingInfo = ShippingInfo.create(new Random().nextInt(1000), ShippingType.FLAT);
			subscriptionInfo = SubscriptionInfo.create(new Random().nextInt(5) + 2, new Random().nextInt(9) * 100, (new Random().nextInt(5) + 2) * 7);
			displayImage = ImagePath.create(getImage());
			Product product = productService.save(merchant, category
																	, basicInfo, subscriptionInfo, shippingInfo
																	, displayImage, gallery);
			System.out.println(product.getId());
		}
	}
	
	
	private String getImage() {
		String[] images = {
				"https://s3-us-west-2.amazonaws.com/mingofy.com/app/product/photos/0dcc0ebb-3093-431b-bbb9-33b9e48a7bac.png"
				, "https://s3-us-west-2.amazonaws.com/mingofy.com/app/product/photos/107ce57d-c603-45c4-a1d1-490dfcb68dea.png"
				, "https://s3-us-west-2.amazonaws.com/mingofy.com/app/product/photos/2e7e60bf-30ea-4a8c-b997-332f44a79ad4.png"
				, "https://s3-us-west-2.amazonaws.com/mingofy.com/app/product/photos/312b7911-1fbe-4445-8244-925ac1b8bb50.png"
				, "https://s3-us-west-2.amazonaws.com/mingofy.com/app/product/photos/486be2d7-4a54-43d0-932a-eef09e6ff964.png"
				, "https://s3-us-west-2.amazonaws.com/mingofy.com/app/product/photos/6bf8e261-3eed-4959-9aa0-cf90bcecd3ad.png"
				, "https://s3-us-west-2.amazonaws.com/mingofy.com/app/product/photos/75432e43-394a-436c-b208-856cca8ce6f3.png"
				, "https://s3-us-west-2.amazonaws.com/mingofy.com/app/product/photos/7752d565-8503-4018-92ad-a7d052d38394.png"
				, "https://s3-us-west-2.amazonaws.com/mingofy.com/app/product/photos/8be43dd8-0f17-46e3-974f-edbd75b55aa5.png"
				, "https://s3-us-west-2.amazonaws.com/mingofy.com/app/product/photos/8cc2fd8c-3c5b-4ba1-8433-78b716d2f7c5.png"
				, "https://s3-us-west-2.amazonaws.com/mingofy.com/app/product/photos/97973913-204c-4398-a226-31eac937a383.png"
				, "https://s3-us-west-2.amazonaws.com/mingofy.com/app/product/photos/9840b1f2-92c7-4eb1-a8d6-57c8e1a7b2ac.png"
				, "https://s3-us-west-2.amazonaws.com/mingofy.com/app/product/photos/a8250e23-1477-4514-8a4e-d4fe38af5b75.png"
				, "https://s3-us-west-2.amazonaws.com/mingofy.com/app/product/photos/ac866b5e-56fd-4afd-9f62-e62fb93a7d5a.png"
				, "https://s3-us-west-2.amazonaws.com/mingofy.com/app/product/photos/af390dec-f672-4c57-bcaf-10cec3a58e1a.png"
				, "https://s3-us-west-2.amazonaws.com/mingofy.com/app/product/photos/b2146f68-b6a4-47c7-a6a3-15d321920311.png"
				, "https://s3-us-west-2.amazonaws.com/mingofy.com/app/product/photos/bfa795d5-5b84-4efa-992e-c1d43ae86019.png"
				, "https://s3-us-west-2.amazonaws.com/mingofy.com/app/product/photos/c06193ec-c222-4b5e-834b-ce7ab0feff69.png"
				, "https://s3-us-west-2.amazonaws.com/mingofy.com/app/product/photos/c9a67db6-4d1d-443b-8d8b-858d999d9a0e.png"
				, "https://s3-us-west-2.amazonaws.com/mingofy.com/app/product/photos/e0e74c39-1693-4975-a13d-11df36b2ef46.png"
				, "https://s3-us-west-2.amazonaws.com/mingofy.com/app/product/photos/fe3540ca-1a3c-41ae-a084-c9b65e6949c6.png"

		};
		
		return images[new Random().nextInt(images.length)];
	}
	
	
	
	public static ImagePath getDisplayImage() {
		return ImagePath.create(getImagePath());
	}
	
	
	public static SubscriptionInfo getSubscription() {
		int percentSave = (new Random().nextInt(100) + 50) * 10;
		int nos = new Random().nextInt(7) + 2;
		int frequency = (new Random().nextInt(7) + 1) * 7;
		return SubscriptionInfo.create(nos, percentSave, frequency);
	}
	
	
	public static ShippingInfo getShipping() {
		int shippingCost = 0;
		ShippingType type = ShippingType.FREE;
		return ShippingInfo.create(shippingCost, type);
	}
	
	public static BasicInfo getBasic() {
		int price = new Random().nextInt(3000) + 500;
		String title = "examplze title";
		String description = "examplze description";
		return BasicInfo.create(price, title, description);
	}

	
	private static String getImagePath() {
		String[] images = new String[]{
				"https://s3-us-west-2.amazonaws.com/test.mingofy/05a4945a-57ec-4c4d-b37e-eb941015b161.png"	
				, "https://s3-us-west-2.amazonaws.com/test.mingofy/05b2d4ae-33a4-4f9a-868c-f2eef448f5fb.png"
		
	
		};
		
		return images[new Random().nextInt(images.length)];
	}
	
}
