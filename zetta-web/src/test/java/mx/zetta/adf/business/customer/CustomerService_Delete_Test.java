package mx.zetta.adf.business.customer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import mx.zetta.adf.SpringTest;
import mx.zetta.adf.business.customer.CustomerService;
import mx.zetta.adf.commons.business.entities.Customer;

public class CustomerService_Delete_Test extends SpringTest {

	@Autowired
	CustomerService customerService;

	String code;

	@Before
	public void init() {
		code = "A01";
		Customer customer = new Customer();
		customer.setCode(code);
		customer.setName("ZAJ");
		customer.setCurrentCredit(1000.0);
		customer.setAddress("York");
		customer.setPhone1("+525512352339");
		customer.setPhone2("+525512352339");
		customerService.saveOrUpdate(customer);
	}

	@Test
	public void test() {

		final Customer customerReturn = customerService.getByCode(code);
		Assert.assertNotNull(customerReturn);

		customerService.deleteByCode(code);
		Assert.assertNull(customerService.getByCode(code));

	}

}
