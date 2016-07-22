package mx.zetta.adf.business.salesorder;

import java.util.List;
import java.util.logging.Level;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import mx.zetta.adf.SpringTest;
import mx.zetta.adf.business.salesorder.SalesOrderService;
import mx.zetta.adf.commons.business.entities.SalesOrder;

public class SalesOrderService_GetAllWithCustomer_Test extends SpringTest {

	@Autowired
	SalesOrderService salesOrderService;

	@Test
	public void test() {

		List<SalesOrder> salesOrders = salesOrderService.getAllWithCustomer();
		for (SalesOrder salesOrder : salesOrders) {
			LOGGER.log(Level.INFO, salesOrder.toString());
		}

	}

}
