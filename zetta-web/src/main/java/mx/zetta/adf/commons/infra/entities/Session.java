package mx.zetta.adf.commons.infra.entities;

import java.nio.ByteBuffer;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "USESSION")
public class Session implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "USESSION_ID")
	@Id
	private String id;
	@Column(name = "USESSION_DAT")
	private byte[] dat;
	@Column(name = "USESSION_DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dateTime;

	public Session() {
	}

	public Session(String id, ByteBuffer dat, Calendar dateTime) {
		this.id = id;
		this.dat = dat.array();
		this.dateTime = dateTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ByteBuffer getDat() {
		return ByteBuffer.wrap(dat);
	}

	public void setDat(ByteBuffer dat) {
		this.dat = dat.array();
	}

	public Calendar getDateTime() {
		return dateTime;
	}

	public void setDateTime(Calendar dateTime) {
		this.dateTime = dateTime;
	}

}