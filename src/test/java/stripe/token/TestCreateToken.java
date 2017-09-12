package stripe.token;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.BaseTest;
import com.common.adapter.stripe.TokenAdapter;
import com.common.adapter.stripe.TokenAdapterAttribute;
import com.core.service.stripe.adapter.TokenAdapterService;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;



public class TestCreateToken extends BaseTest{


	@Autowired private TokenAdapterService service;
	
	public static final String TOKEN_ID = "tok_199qGFJ8HqLJrKlOpeBOOPlO";
	
	@Test
	public void test() throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		//String number = "4000000000000077";
		String number = "4000056655665556";
		int cvc = 321;
		int expYear = 2018;
		int expMonth = 2;
		TokenAdapterAttribute card = new TokenAdapterAttribute("customer_name", number, expMonth, expYear, cvc);
		TokenAdapter result = service.create(card );
		System.out.println(result.getTokenId());
	}
	
	
//	
//	public String create() throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
//		String number = "4000056655665556";
//		int cvc = 321;
//		int expYear = 2018;
//		int expMonth = 2;
//		TokenAdapterAttribute card = new TokenAdapterAttribute(number, expMonth, expYear, cvc);
//		TokenAdapter result = service.create(card);
//		return result.getTokenId();
//	}
}
