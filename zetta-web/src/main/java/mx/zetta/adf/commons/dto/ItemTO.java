package mx.zetta.adf.commons.dto;

import java.io.Serializable;

public class ItemTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
	private String descripcion;

	public ItemTO() {
	}

	public ItemTO(String id, String des) {
		this.id = id;
		this.descripcion = des;

	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		if (descripcion != null) {
			this.descripcion = descripcion.trim();
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
