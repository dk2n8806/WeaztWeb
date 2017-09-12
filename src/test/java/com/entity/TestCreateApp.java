package com.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.BaseTest;
import com.common.adapter.shippo.ShippoAddressAdapter;
import com.common.entity.account.Account;
import com.common.entity.account.SimpleShipping;
import com.common.entity.category.Category;
import com.common.entity.category.GroupCategory;
import com.common.entity.category.group.Beverage;
import com.common.entity.merchant.Merchant;
import com.common.entity.order.OrderIntent;
import com.common.entity.product.Parcel;
import com.common.entity.product.ParcelAdapter;
import com.common.entity.product.Product;
import com.common.entity.product.ProductGallery;
import com.common.entity.product.embeded.BasicInfo;
import com.common.entity.product.embeded.Measurement;
import com.common.entity.product.embeded.ShippingInfo;
import com.common.entity.product.embeded.SubscriptionInfo;
import com.common.entity.product.embeded.Weight;
import com.common.entity.promo.Promotion;
import com.common.entity.subscription.Subscription;
import com.common.entity.support.embeded.ImagePath;
import com.common.entity.support.embeded.Phone;
import com.common.exception.AccountException;
import com.common.exception.MerchantException;
import com.common.exception.OrderException;
import com.common.exception.ProductException;
import com.common.exception.SubscriptionException;
import com.common.type.DistanceUnit;
import com.common.type.MassUnit;
import com.common.type.OrderIntentStatus;
import com.common.type.Role;
import com.common.type.SubscriptionStatus;
import com.common.util.date.DateInterval;
import com.common.util.date.DateUtil;
import com.core.service.account.AccountService;
import com.core.service.account.ProfileService;
import com.core.service.account.SimpleShippingService;
import com.core.service.account.auth.AccountAuthenticationService;
import com.core.service.address.AddressService;
import com.core.service.category.CategoryService;
import com.core.service.category.GroupCategoryService;
import com.core.service.checkout.CheckoutService;
import com.core.service.merchant.MerchantProfileService;
import com.core.service.merchant.MerchantService;
import com.core.service.order.MerchantOrderIntentSerivce;
import com.core.service.order.OrderBundleService;
import com.core.service.order.OrderIntentService;
import com.core.service.order.OrderTransactionService;
import com.core.service.order.ShipmentService;
import com.core.service.payment.PaymentService;
import com.core.service.payout.PayoutService;
import com.core.service.process.order.IOrderProcessService;
import com.core.service.product.ParcelService;
import com.core.service.product.ProductService;
import com.core.service.shippo.ShippoAddressService;
import com.core.service.shippo.ShippoParcelService;
import com.core.service.subscription.SubscriptionService;
import com.entity.account.TestCreateAccount;
import com.entity.category.TestCreateCategory;
import com.entity.product.TestCreateProduct;

import shippo.TestCreateShippoAddress;

public class TestCreateApp extends BaseTest {


	@Autowired private AccountService accountService;
	@Autowired private ProfileService profileService;
	@Autowired private AccountAuthenticationService authenService;
	
	@Autowired private CategoryService categoryService;
	@Autowired private GroupCategoryService groupCategoryService;
	
	@Autowired private MerchantService merchantService;
	@Autowired private MerchantProfileService merchantProfileService;
	
	
	@Autowired private ProductService productService;
	
	@Autowired private SubscriptionService subscriptionService;

	
	@Autowired private MerchantOrderIntentSerivce merchantOrderIntentSerivce;
	@Autowired private OrderIntentService orderIntentService;
	@Autowired private OrderBundleService orderBundleService;
	@Autowired private OrderTransactionService transactionService;
	@Autowired private ShipmentService shipmentService;
	@Autowired private IOrderProcessService orderProcessService;
	

	@Autowired private PayoutService payoutService;
	@Autowired private PaymentService paymentService;

	@Autowired private CheckoutService checkoutService;
	
	
	@Autowired private ShippoParcelService shippoParcelService;
	@Autowired private ParcelService parcelService;
	

	@Autowired private SimpleShippingService simpleShippingService;
	@Autowired private AddressService addressService;
	@Autowired private ShippoAddressService shippoAddressService;

	
	
	
	
	
	
	private List<Category> categories = new ArrayList<>();
	private List<Account> accounts = new ArrayList<>();
	private List<Merchant> merchants = new ArrayList<>();
	private List<Product> products = new ArrayList<>();
	private List<Subscription> subscriptions = new ArrayList<>();
	private List<OrderIntent> orderIntents = new ArrayList<>();

	
	
	@Before
	public void init() {
		categories = categoryService.getList();
		accounts = accountService.getAccounts(null, null, null, null);
		merchants = merchantService.getMerchants(null, null, null);
		products = productService.getProducts(null, null, null, null);
		
		subscriptions = subscriptionService.getSubscriptions(null, null, null);
		
		orderIntents = orderIntentService.getList();
	}
	
	
	@Test
	public void test() throws ProductException, OrderException, MerchantException {
		testCreateAccount();
		persistMerchant();
		persistProduct();
		persistParcel();
		testSimpleAddress();
		testCheckout();
		testOrderProcess();
		
		
		
	}

	
	private void testOrderProcess() throws ProductException, OrderException, MerchantException {
		
		Product product = subscriptions.get(0).getProduct();
		DateInterval dateInterval = new DateUtil();
		dateInterval.setInterval(new Date(), -7);
		

		orderProcessService.generateOrder(product, dateInterval);

		assertEquals(0, subscriptionService.countSubscriptions(SubscriptionStatus.SUBSCRIBING, null));
		assertEquals(1, subscriptionService.countSubscriptions(SubscriptionStatus.SUBSCRIBED, null));
		assertEquals(1, orderBundleService.getRowCount());
		assertEquals(1, payoutService.getRowCount());
		assertEquals(2, orderIntentService.getRowCount());
		assertEquals(1, transactionService.getRowCount());
		assertEquals(1, shipmentService.getRowCount());
		
	}
	
	
	private void testSimpleAddress() {
		long c1 = simpleShippingService.getRowCount();
		for(Account account : accounts) {
			ShippoAddressAdapter address = shippoAddressService.create(
					TestCreateShippoAddress.getCustomerAddress());
			SimpleShipping simpleShipping = simpleShippingService.save(account, address);
			simpleShippingService.setPrimary(simpleShipping);
		}
		assertEquals(c1 + accounts.size(), simpleShippingService.getRowCount());
	}
	
	
	private void testCheckout() {
		Account customer = accounts.get(new Random().nextInt(accounts.size()));
		Product product = products.get(new Random().nextInt(products.size()));
		int frequency = product.getSubscriptionInfo().getFrequency();
		int numberOfShipment = product.getSubscriptionInfo().getNos();
		Promotion promotion = null;
		
		long sc = subscriptionService.getRowCount();
		long oc = orderIntentService.getRowCount();
		long pc = paymentService.getRowCount();
		try {
			OrderIntent orderIntent = checkoutService.checkout(customer, product, frequency, numberOfShipment, promotion);
		
			
			orderIntents.add(orderIntent);
			subscriptions.add(orderIntent.getSubscription());
		} catch (SubscriptionException | OrderException e) {
			e.printStackTrace();
		}
		

		assertEquals(sc + 1, subscriptionService.getRowCount());
		assertEquals(oc + 1, orderIntentService.getRowCount());
		assertEquals(pc + 1, paymentService.getRowCount());
	}
	
	private void persistParcel() {
		long c = parcelService.getRowCount();
		for(Product product : products) {
			Measurement measurement = Measurement.create(5, 5, 5, DistanceUnit.IN);
			Weight weight = Weight.create(5, MassUnit.OZ);
			ParcelAdapter parcelAdapter = shippoParcelService.create(measurement, weight);
			Parcel parcel = parcelService.save(product, parcelAdapter );
			assertNotNull(parcel);
		}
		
		assertEquals(c + products.size(), parcelService.getRowCount());
	}
	
	private void persistProduct() {
		long productCount = productService.getRowCount();
		
		
		Merchant merchant = merchants.get(new Random().nextInt(merchants.size()));
		Category category = categories.get(new Random().nextInt(categories.size()));
		BasicInfo basicInfo = TestCreateProduct.getBasic();
		SubscriptionInfo subscriptionInfo = TestCreateProduct.getSubscription();
		ShippingInfo shippingInfo = TestCreateProduct.getShipping();
		ImagePath displayImage = TestCreateProduct.getDisplayImage();
		List<ProductGallery> gallery = null;
		Product product = productService.save(merchant, category
				, basicInfo, subscriptionInfo, shippingInfo, displayImage, gallery);
		
		
		assertEquals(productCount + 1, productService.getRowCount());
		
		products.add(product);
	}
	
	
	
	private void persistMerchant() {
		long addressCount = addressService.getRowCount();
		long profileCount = merchantProfileService.getRowCount();
		long merchantCount = merchantService.getRowCount();
		ShippoAddressAdapter address = shippoAddressService.create(
				TestCreateShippoAddress.getMerchantAddressAttribute());
		String companyName = "MERCHANT";
		String webstie = "";
		Phone phone = Phone.create("1231231234");
		
		Account account = accounts.get(0);
		Merchant merchant = merchantService.save(account, companyName, webstie, phone, address);
		merchants.add(merchant);
		
		account.setRole(Role.MERCHANT);
		try {
			accountService.promoteToMerchant(account.getId());
		} catch (AccountException e) {
			e.printStackTrace();
		}
		
		assertEquals(merchantCount +1, merchantService.getRowCount());
		assertEquals(profileCount +1, merchantProfileService.getRowCount());
		assertEquals(addressCount + 1, addressService.getRowCount());
	}
	
	
	private void testCreateAccount() {
		long accountCount = accountService.getRowCount();
		long profilecount = profileService.getRowCount();
		accounts.add(authenService.save(TestCreateAccount.getAccounts(), "password"));
		assertEquals(accountCount + 1, accountService.getRowCount());
		assertEquals(profilecount + 1, profileService.getRowCount());
	}
	
}
