package shippo;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.BaseTest;
import com.common.adapter.shippo.AddressAdapterAttribute;
import com.common.adapter.shippo.ShippoAddressAdapter;
import com.common.type.USAStates;
import com.core.service.shippo.ShippoAddressService;

public class TestCreateShippoAddress extends BaseTest {

	@Autowired private ShippoAddressService shippoService;
	
	public static AddressAdapterAttribute getMerchantAddressAttribute() {
		String name = "Merchant_Name";
		String street = "4061 Van Dyke Ave";
		String city = "San Diego";
		USAStates state = USAStates.CALIFORNIA;
		String country = "US";
		String zipcode = "92105";
		return new AddressAdapterAttribute(name, street, city, state, country, zipcode);
	}
	
	public static AddressAdapterAttribute getCustomerAddress() {
		String name = "Customer_Name";
		String street = "7250 Mesa College Dr";
		String city = "San Diego";
		USAStates state = USAStates.CALIFORNIA;
		String country = "US";
		String zipcode = "92111";
		return new AddressAdapterAttribute(name, street, city, state, country, zipcode);
	}
	
	@Test
	public void test() {
		ShippoAddressAdapter adapter = shippoService.create(getMerchantAddressAttribute());
		System.out.println("-----------");
		System.out.println(adapter);
	}
	
}
