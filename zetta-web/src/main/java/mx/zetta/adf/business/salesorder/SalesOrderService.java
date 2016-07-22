package mx.zetta.adf.business.salesorder;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import mx.zetta.adf.commons.JPAUtils;
import mx.zetta.adf.commons.business.entities.Customer;
import mx.zetta.adf.commons.business.entities.OrderLines;
import mx.zetta.adf.commons.business.entities.Product;
import mx.zetta.adf.commons.business.entities.SalesOrder;

@Service
public class SalesOrderService {

    private static final String PARAM_ORDER_NUMBER = "paramOrderNumber";
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(propagation = Propagation.REQUIRED)
    public void saveOrUpdate(SalesOrder salesOrder) {
        if (entityManager.find(SalesOrder.class, salesOrder.getOrderNumber()) == null) {

            salesOrder.setDeleted(false);
            salesOrder.setCustomer(entityManager.getReference(Customer.class, salesOrder.getCustomer().getCode()));
            entityManager.persist(salesOrder);
            for (OrderLines orderLines : salesOrder.getOrderLines()) {
                orderLines.setSalesOrder(salesOrder);
                orderLines.setProduct(entityManager.getReference(Product.class, orderLines.getProduct().getCode()));
                entityManager.persist(orderLines);
            }
        } else {
            update(salesOrder);
        }

    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void update(SalesOrder salesOrder) {

        final Query query = entityManager.createNamedQuery("OrderLines.deleteByOrderNumber");
        query.setParameter(PARAM_ORDER_NUMBER, salesOrder.getOrderNumber());
        query.executeUpdate();

        SalesOrder salesOrderDB = entityManager.find(SalesOrder.class, salesOrder.getOrderNumber());
        salesOrderDB.setCustomer(entityManager.getReference(Customer.class, salesOrder.getCustomer().getCode()));
        salesOrderDB.setTotalPrice(salesOrder.getTotalPrice());
        entityManager.merge(salesOrderDB);
        for (OrderLines orderLines : salesOrder.getOrderLines()) {
            orderLines.setSalesOrder(salesOrder);
            orderLines.setProduct(entityManager.getReference(Product.class, orderLines.getProduct().getCode()));
            entityManager.persist(orderLines);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteByOrderNumber(Integer orderNumber) {
        final Query query = entityManager.createNamedQuery("SalesOrder.deleteByCode");
        query.setParameter(PARAM_ORDER_NUMBER, orderNumber);
        query.executeUpdate();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public SalesOrder getByOrderNumberWithDetail(Integer orderNumber) {
        final TypedQuery<SalesOrder> query = entityManager.createNamedQuery("SalesOrder.getByOrderNumberWithDetail", SalesOrder.class);
        query.setParameter(PARAM_ORDER_NUMBER, orderNumber);
        return JPAUtils.getSingleResult(query);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<SalesOrder> getAllWithCustomer() {
        final TypedQuery<SalesOrder> query = entityManager.createNamedQuery("SalesOrder.getAllWithCustomer", SalesOrder.class);
        return query.getResultList();
    }

}
