package email;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.BaseTest;
import com.common.entity.account.Account;
import com.core.service.account.AccountService;
import com.core.service.email.IEmailVerificationService;
import com.core.service.email.IWelcomeEmailService;

public class TestSendVerificationCode extends BaseTest{

	@Autowired private IEmailVerificationService verificationService;
	@Autowired private IWelcomeEmailService welcomeEmailService;
	@Autowired private AccountService accountService;
	
	private Account account;
	
	@Before
	public void init() {
		account = accountService.findById(new Long(1));
	}
	
	@Test
	public void test() {
		try {
			//verificationService.sendVerfiicationEmail(account);
			welcomeEmailService.sendWelcomeEmail(account);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
