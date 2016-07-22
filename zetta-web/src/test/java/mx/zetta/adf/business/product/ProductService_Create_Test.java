package mx.zetta.adf.business.product;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import mx.zetta.adf.SpringTest;
import mx.zetta.adf.business.product.ProductService;
import mx.zetta.adf.commons.business.entities.Product;

public class ProductService_Create_Test extends SpringTest {

	@Autowired
	ProductService productService;

	@Test
	public void test() {
		final Product product = new Product();
		product.setCode("PR04");
		product.setDescription("Water of bottle 2");
		product.setPrice(10.0);
		product.setQuantity(1000);
		productService.saveOrUpdate(product);
	}

}
