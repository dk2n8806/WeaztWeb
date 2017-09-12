package shippo;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.BaseTest;
import com.common.entity.product.Parcel;
import com.common.entity.product.ParcelAdapter;
import com.common.entity.product.Product;
import com.common.entity.product.embeded.Measurement;
import com.common.entity.product.embeded.Weight;
import com.common.type.DistanceUnit;
import com.common.type.MassUnit;
import com.core.service.product.ParcelService;
import com.core.service.product.ProductService;
import com.core.service.shippo.ShippoParcelService;

public class TestCreateShippoParcel extends BaseTest{

	@Autowired private ParcelService parcelService;
	@Autowired private ProductService productService;
	@Autowired private ShippoParcelService shippoParcelService;
	private Product product;
	private ParcelAdapter parcelAdapter;
	
	
	@Before
	public void init() {
		product = productService.findById(new Long(38));
		Measurement measurement = Measurement.create(6.9, 6, 6.9, DistanceUnit.IN);
		Weight weight = Weight.create(1.9, MassUnit.LB);
		parcelAdapter = shippoParcelService.create(measurement, weight );
	}
	
	
	@Test
	public void test() {
		Parcel parcel = parcelService.save(product, parcelAdapter);
		System.out.println(parcel.getId());
	}
}
