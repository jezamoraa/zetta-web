package mx.zetta.adf.business.salesorder;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import mx.zetta.adf.SpringTest;
import mx.zetta.adf.business.customer.CustomerService;
import mx.zetta.adf.business.product.ProductService;
import mx.zetta.adf.business.salesorder.SalesOrderService;
import mx.zetta.adf.commons.business.entities.Customer;
import mx.zetta.adf.commons.business.entities.OrderLines;
import mx.zetta.adf.commons.business.entities.Product;
import mx.zetta.adf.commons.business.entities.SalesOrder;

public class SalesOrderService_Update_Test extends SpringTest {

	@Autowired
	SalesOrderService salesOrderService;

	@Autowired
	ProductService productService;

	@Autowired
	CustomerService customerService;

	Customer customer;

	@Before
	public void init() {
		customer = new Customer();
		customer.setCode("TAC03");
		customer.setName("ZAJ");
		customer.setCurrentCredit(1000.0);
		customer.setAddress("Merida");
		customer.setPhone1("+525512352339");
		customer.setPhone2("+525512352339");
		customerService.saveOrUpdate(customer);

		Product product = new Product();
		product.setCode("TAAR01");
		product.setDescription("AA Water of bottle 1");
		product.setPrice(10.0);
		product.setQuantity(10);
		productService.saveOrUpdate(product);

		product = new Product();
		product.setCode("TAAR02");
		product.setDescription("AA Water of bottle 2");
		product.setPrice(10.0);
		product.setQuantity(10);
		productService.saveOrUpdate(product);

		final SalesOrder salesOrder = new SalesOrder();
		salesOrder.setCustomer(new Customer(customer.getCode()));
		salesOrder.setOrderNumber(2232);
		salesOrder.setTotalPrice(20.00);

		List<OrderLines> orderLines = new ArrayList<>();
		orderLines.add(new OrderLines("TAAR01", 1));
		orderLines.add(new OrderLines("TAAR02", 1));
		salesOrder.setOrderLines(orderLines);

		salesOrderService.saveOrUpdate(salesOrder);

	}

	@Test
	public void test() {

		SalesOrder salesOrder = salesOrderService.getByOrderNumberWithDetail(2232);
		LOGGER.log(Level.INFO, salesOrder.toString());
		salesOrder.setTotalPrice(0.0);
		salesOrder.setOrderLines(new ArrayList<>());
		salesOrderService.update(salesOrder);

		salesOrder = salesOrderService.getByOrderNumberWithDetail(2232);

		Assert.assertEquals(Double.valueOf(0.0), salesOrder.getTotalPrice());
		Assert.assertEquals(true, salesOrder.getOrderLines().isEmpty());

		LOGGER.log(Level.INFO, salesOrder.toString());

	}

}
