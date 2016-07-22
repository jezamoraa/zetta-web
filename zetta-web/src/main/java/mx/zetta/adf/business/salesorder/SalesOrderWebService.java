package mx.zetta.adf.business.salesorder;

import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

import mx.zetta.adf.commons.business.entities.OrderLines;
import mx.zetta.adf.commons.business.entities.SalesOrder;

@WebService(serviceName = "SalesOrderWebService")
public class SalesOrderWebService {

	@Autowired
	private SalesOrderService salesOrderService;

	public void saveOrUpdate(SalesOrder salesOrder, List<OrderLines> orderLines) {
		salesOrder.setOrderLines(orderLines);
		salesOrderService.saveOrUpdate(salesOrder);
	}

	public void deleteByOrderNumber(Integer orderNumber) {
		salesOrderService.deleteByOrderNumber(orderNumber);
	}

	public SalesOrder getByOrderNumberWithDetail(Integer orderNumber) {
		return salesOrderService.getByOrderNumberWithDetail(orderNumber);
	}

	public List<SalesOrder> getAllWithCustomer() {
		return salesOrderService.getAllWithCustomer();
	}
}
