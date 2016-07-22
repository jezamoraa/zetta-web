package mx.zetta.adf;

import java.util.List;

import javax.jws.WebService;

import mx.zetta.adf.commons.business.entities.Customer;

@WebService(serviceName = "CustomerWebService2")
public class CustomerWebService2 {

    public void saveOrUpdate(Customer customer) {
    }

    public Customer getByCode(String code) {
        return null;
    }

    public void deleteByCode(String code) {
    }

    public List<Customer> getAll() {
        return null;
    }
}
