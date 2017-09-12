package stripe.customer;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.BaseTest;
import com.common.entity.account.Account;
import com.common.entity.stripe.StripeCustomer;
import com.core.service.stripe.StripeCreatorService;

import stripe.token.TestCreateToken;

public class TestCreateStripeCustomer extends BaseTest{

	@Autowired private StripeCreatorService stripeService;
	private Account account;
	private String cardTokenId;
	
	public static final String TOKEN_ID = "cus_9SlkJBvr110Oxe";
	
	@Before
	public void init() {
		account = new Account();
		account.setId(new Long(1));
		
		cardTokenId = TestCreateToken.TOKEN_ID;
	}
	
	@Test
	public void test() {
		StripeCustomer customer = stripeService.createCustomerByCardToken(account, cardTokenId);
		System.out.println(customer.getId());
		System.out.println(customer.getCustomerAdapter().getTokenId());
	}
}
