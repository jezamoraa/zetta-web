package mx.zetta.adf.business.product;

import java.util.List;
import java.util.logging.Level;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import mx.zetta.adf.SpringTest;
import mx.zetta.adf.business.product.ProductService;
import mx.zetta.adf.commons.business.entities.Product;

public class ProductService_GetAll_Test extends SpringTest {

	@Autowired
	ProductService productService;

	@Test
	public void test() {

		List<Product> products = productService.getAll();
		for (Product product : products) {
			LOGGER.log(Level.INFO, product.toString());
		}

	}

}
