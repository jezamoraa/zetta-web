package mx.zetta.adf.business.customer;

import java.util.List;
import java.util.logging.Level;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import mx.zetta.adf.SpringTest;
import mx.zetta.adf.business.customer.CustomerService;
import mx.zetta.adf.commons.business.entities.Customer;

public class CustomerService_GetAll_Test extends SpringTest {

	@Autowired
	CustomerService customerService;

	@Test
	public void test() {

		List<Customer> customers = customerService.getAll();
		for (Customer customer : customers) {
			LOGGER.log(Level.INFO, customer.toString());
		}

	}

}
