package com.entity.parcel;

import static org.junit.Assert.*;

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

public class TestCreateParcel extends BaseTest{

	@Autowired private ShippoParcelService shippoService;
	@Autowired private ParcelService parcelService;
	@Autowired private ProductService productService;
	
	@Test
	public void test() {
		Product product = productService.findById(new Long(32));
		
		Measurement measurement = Measurement.create(5, 5, 5, DistanceUnit.IN);
		Weight weight = Weight.create(5, MassUnit.OZ);
		ParcelAdapter parcelAdapter = shippoService.create(measurement, weight);
		
		assertNotNull(parcelAdapter);
		Parcel parcel = parcelService.save(product, parcelAdapter );
		System.out.println(parcel.getId());
	}
	
	
}
