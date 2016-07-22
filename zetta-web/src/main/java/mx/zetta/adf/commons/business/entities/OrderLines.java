package mx.zetta.adf.commons.business.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
@Table(name = "OrderLines")
@NamedQueries({
		@NamedQuery(name = "OrderLines.deleteByOrderNumber", query = "DELETE FROM OrderLines e WHERE e.salesOrder.orderNumber = :paramOrderNumber ") })
public class OrderLines implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "orderLinesId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderLinesId;

	@ManyToOne
	@JoinColumn(name = "orderNumber")
	private SalesOrder salesOrder;

	@JoinColumn(name = "productCode")
	@ManyToOne
	private Product product;
	@Column(name = "quantity")
	private Integer quantity;

	public OrderLines() {
		orderLinesId = null;
	}

	public OrderLines(String productCode, Integer quantity) {
		super();
		this.product = new Product(productCode);
		this.quantity = quantity;
	}

	public SalesOrder getSalesOrder() {
		return salesOrder;
	}

	public void setSalesOrder(SalesOrder salesOrder) {
		this.salesOrder = salesOrder;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getOrderLinesId() {
		return orderLinesId;
	}

	public void setOrderLinesId(Integer orderLinesId) {
		this.orderLinesId = orderLinesId;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
