package mx.zetta.adf.commons.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mx.zetta.adf.commons.infra.entities.Usuario;


public class UsuarioFirmadoTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String sessionID;
	private List<String> servicios;
	private Usuario usuario;

	public UsuarioFirmadoTO() {
	}

	public UsuarioFirmadoTO(String sessionID, List<String> servicios, Usuario usuario) {
		super();
		this.sessionID = sessionID;
		this.servicios = servicios;
		this.usuario = usuario;
		if (this.servicios == null) {
			this.servicios = new ArrayList<String>();
		}
	}

	public boolean containAction(String action) {
		return servicios.contains(action);
	}

	public String getSessionID() {
		return sessionID;
	}

	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<String> getServicios() {
		return servicios;
	}

	public void setServicios(List<String> servicios) {
		this.servicios = servicios;
	}

}
