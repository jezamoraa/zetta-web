package mx.zetta.adf.business.product;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import mx.zetta.adf.commons.JPAUtils;
import mx.zetta.adf.commons.business.entities.Product;

@Service
public class ProductService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(propagation = Propagation.REQUIRED)
    public void saveOrUpdate(Product product) {

        if (getByCode(product.getCode()) == null) {
            product.setDeleted(false);
            entityManager.persist(product);
        } else {
            update(product);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Product getByCode(String code) {
        final TypedQuery<Product> query = entityManager.createNamedQuery("Product.getByCode", Product.class);
        query.setParameter("paramCode", code);
        return JPAUtils.getSingleResult(query);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void update(Product product) {
        Product productDB = entityManager.find(Product.class, product.getCode());
        productDB.setDescription(product.getDescription());
        productDB.setPrice(product.getPrice());
        productDB.setQuantity(product.getQuantity());
        entityManager.merge(productDB);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteByCode(String code) {
        final Query query = entityManager.createNamedQuery("Product.deleteByCode");
        query.setParameter("paramCode", code);
        query.executeUpdate();
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<Product> getAll() {
        final TypedQuery<Product> query = entityManager.createNamedQuery("Product.getAll", Product.class);
        return query.getResultList();
    }

}
