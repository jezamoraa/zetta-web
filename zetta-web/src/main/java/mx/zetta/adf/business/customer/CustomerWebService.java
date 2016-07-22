package mx.zetta.adf.business.customer;

import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

import mx.zetta.adf.commons.business.entities.Customer;

@WebService(serviceName = "CustomerWebService")
public class CustomerWebService {

	@Autowired
	private CustomerService customerService;

	public void saveOrUpdate(Customer customer) {
		customerService.saveOrUpdate(customer);
	}

	public Customer getByCode(String code) {
		return customerService.getByCode(code);
	}

	public void deleteByCode(String code) {
		customerService.deleteByCode(code);
	}

	public List<Customer> getAll() {
		return customerService.getAll();
	}
}
