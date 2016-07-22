package mx.zetta.adf.commons.business.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
@Table(name = "SalesOrder")
@NamedQueries({
        @NamedQuery(name = "SalesOrder.getAllWithCustomer", query = "select DISTINCT NEW mx.zetta.adf.commons.business.entities.SalesOrder(e.orderNumber,e.customer,e.totalPrice) from SalesOrder e WHERE e.deleted = FALSE "),
        @NamedQuery(name = "SalesOrder.deleteByCode", query = "UPDATE SalesOrder e SET e.deleted = TRUE WHERE e.orderNumber = :paramOrderNumber "),
        @NamedQuery(name = "SalesOrder.getByOrderNumberWithDetail", query = "select DISTINCT NEW mx.zetta.adf.commons.business.entities.SalesOrder(e) from SalesOrder e where e.deleted = FALSE AND e.orderNumber = :paramOrderNumber") })
public class SalesOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "orderNumber")
    private Integer orderNumber;

    @ManyToOne
    @JoinColumn(name = "customerCode")
    private Customer customer;

    @Column(name = "totalPrice")
    private Double totalPrice;

    @Column(name = "deleted")
    private boolean deleted;

    @OneToMany(mappedBy = "salesOrder")
    private List<OrderLines> orderLines;

    @Transient
    private OrderLines[] ordeLinesFrondEnd;

    public SalesOrder() {
        orderNumber = null;
    }

    public SalesOrder(SalesOrder e) {
        this.orderNumber = e.orderNumber;
        this.customer = e.customer;
        this.totalPrice = e.totalPrice;
        this.orderLines = new ArrayList<>();
        for (OrderLines orderl : orderLines) {
            this.orderLines.add(new OrderLines(orderl.getProduct().getCode(), orderl.getQuantity()));
        }

    }

    public SalesOrder(Integer orderNumber, Customer customer, Double totalPrice) {
        this.orderNumber = orderNumber;
        this.customer = customer;
        this.totalPrice = totalPrice;
    }

    public SalesOrder(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public List<OrderLines> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLines> orderLines) {
        this.orderLines = orderLines;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    public OrderLines[] getOrdeLinesFrondEnd() {
        return ordeLinesFrondEnd;
    }

    public void setOrdeLinesFrondEnd(OrderLines[] ordeLinesFrondEnd) {
        this.ordeLinesFrondEnd = ordeLinesFrondEnd;
    }

}
