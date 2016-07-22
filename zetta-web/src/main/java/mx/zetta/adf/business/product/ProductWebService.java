package mx.zetta.adf.business.product;

import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

import mx.zetta.adf.commons.business.entities.Product;

@WebService(serviceName = "ProductWebService")
public class ProductWebService {

    @Autowired
    private ProductService productService;

    public void saveOrUpdate(Product product) {
        productService.saveOrUpdate(product);
    }

    public Product getByCode(String code) {
        return productService.getByCode(code);
    }

    public void deleteByCode(String code) {
        productService.deleteByCode(code);
    }

    public List<Product> getAll() {
        return productService.getAll();
    }
}
