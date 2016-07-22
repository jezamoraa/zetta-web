package mx.zetta.adf.commons.business.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
@Table(name = "Customer")
@NamedQueries({
		@NamedQuery(name = "Customer.getByCode", query = "select e from Customer e WHERE e.deleted = FALSE AND e.code = :paramCode "),
		@NamedQuery(name = "Customer.getAll", query = "select NEW  mx.zetta.adf.commons.business.entities.Customer(e.code,e.name,e.phone1,e.currentCredit) from Customer e WHERE e.deleted = FALSE "),
		@NamedQuery(name = "Customer.deleteByCode", query = "UPDATE Customer e SET e.deleted = TRUE WHERE e.code = :paramCode ") })
public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "code")
	private String code;

	@Column(name = "name")
	private String name;

	@Column(name = "address")
	private String address;

	@Column(name = "phone1")
	private String phone1;
	@Column(name = "phone2")
	private String phone2;

	@Column(name = "currentCredit")
	private Double currentCredit;

	@Column(name = "deleted")
	private boolean deleted;

	public Customer(String code, String name, String phone1, Double currentCredit) {
		this.code = code;
		this.name = name;
		this.phone1 = phone1;
		this.currentCredit = currentCredit;
	}

	public Customer() {
		code = null;
	}

	public Customer(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getCurrentCredit() {
		return currentCredit;
	}

	public void setCurrentCredit(Double currentCredit) {
		this.currentCredit = currentCredit;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
