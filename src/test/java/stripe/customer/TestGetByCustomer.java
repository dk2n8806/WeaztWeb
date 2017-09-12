package stripe.customer;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import com.BaseTest;
import com.common.entity.account.Account;
import com.common.entity.stripe.StripeCustomer;
import com.common.entity.stripe.StripeStatus;
import com.core.service.account.AccountService;
import com.core.service.stripe.StripeCustomerService;

public class TestGetByCustomer extends BaseTest{

	@Autowired private StripeCustomerService stripeService;
	@Autowired private AccountService accountService;
	
	private Account account;
	private StripeStatus status;
	private Pageable pageable;
	
	@Before
	public void init() {
		account = accountService.findById(new Long(1));
	}
	
	@Test
	public void test() {
		List<StripeCustomer> list = stripeService.getByAccount(account, status, pageable);
		for(StripeCustomer l : list) {
			System.out.println(l);
		}
	}
}
