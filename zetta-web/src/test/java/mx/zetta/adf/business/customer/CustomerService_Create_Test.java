package mx.zetta.adf.business.customer;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import mx.zetta.adf.SpringTest;
import mx.zetta.adf.business.customer.CustomerService;
import mx.zetta.adf.commons.business.entities.Customer;

public class CustomerService_Create_Test extends SpringTest {

	@Autowired
	CustomerService customerService;

	@Test
	public void test() {
		final Customer customer = new Customer();
		customer.setCode("C01");
		customer.setName("ZAJ");
		customer.setCurrentCredit(1000.0);
		customer.setAddress("York");
		customer.setPhone1("+525512352339");
		customer.setPhone2("+525512352339");
		customerService.saveOrUpdate(customer);
	}

}
