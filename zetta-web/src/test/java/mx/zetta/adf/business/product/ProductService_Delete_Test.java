package mx.zetta.adf.business.product;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import mx.zetta.adf.SpringTest;
import mx.zetta.adf.business.product.ProductService;
import mx.zetta.adf.commons.business.entities.Product;

public class ProductService_Delete_Test extends SpringTest {

	@Autowired
	ProductService productService;

	String code;

	@Before
	public void init() {
		code = "WB05";
		Product product = new Product();
		product.setCode(code);
		product.setDescription("Water of bottle 2");
		product.setPrice(10.0);
		product.setQuantity(1000);
		productService.saveOrUpdate(product);
	}

	@Test
	public void test() {

		final Product productReturn = productService.getByCode(code);
		Assert.assertNotNull(productReturn);

		productService.deleteByCode(code);
		Assert.assertNull(productService.getByCode(code));

	}

}
