package mx.zetta.adf.business.customer;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import mx.zetta.adf.commons.ApplicationException;
import mx.zetta.adf.commons.JPAUtils;
import mx.zetta.adf.commons.business.entities.Customer;

@Service
public class CustomerService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = ApplicationException.class)
    public void saveOrUpdate(Customer customer) {

        if (getByCode(customer.getCode()) == null) {
            customer.setDeleted(false);
            entityManager.persist(customer);
        } else {
            update(customer);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Customer getByCode(String code) {
        final TypedQuery<Customer> query = entityManager.createNamedQuery("Customer.getByCode", Customer.class);
        query.setParameter("paramCode", code);
        return JPAUtils.getSingleResult(query);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void update(Customer customer) {

        final Customer customerDB = entityManager.find(Customer.class, customer.getCode());
        customerDB.setCurrentCredit(customer.getCurrentCredit());
        customerDB.setName(customer.getName());
        customerDB.setAddress(customer.getAddress());
        customerDB.setPhone1(customer.getPhone1());
        customerDB.setPhone2(customer.getPhone2());
        entityManager.merge(customerDB);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteByCode(String code) {
        final Query query = entityManager.createNamedQuery("Customer.deleteByCode");
        query.setParameter("paramCode", code);
        query.executeUpdate();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Customer> getAll() {
        final TypedQuery<Customer> query = entityManager.createNamedQuery("Customer.getAll", Customer.class);
        return query.getResultList();
    }

}
