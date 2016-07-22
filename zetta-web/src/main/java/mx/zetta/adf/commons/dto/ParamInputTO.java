package mx.zetta.adf.commons.dto;

import java.io.Serializable;

/**
 * Clase que representa un Transfer Object para los parametros de entrada de los
 * servicios
 * 
 */
public class ParamInputTO<T> implements Serializable {

	/** Version del objeto serializable */
	private static final long serialVersionUID = 1L;
	private String sessionID;
	private UsuarioFirmadoTO usuarioFirmadoTO;
	private String trn;
	private T input;

	/** Constructor por omision */
	public ParamInputTO() {
	}

	public ParamInputTO(String sessionID) {
		this.sessionID = sessionID;
	}

	public ParamInputTO(String sessionID, T input) {
		this.sessionID = sessionID;
		this.input = input;
	}

	public String getSessionID() {
		return sessionID;
	}

	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}

	public UsuarioFirmadoTO getUsuarioFirmadoTO() {
		return usuarioFirmadoTO;
	}

	public void setUsuarioFirmadoTO(UsuarioFirmadoTO usuarioFirmadoTO) {
		this.usuarioFirmadoTO = usuarioFirmadoTO;
	}

	public String getTrn() {
		return trn;
	}

	public void setTrn(String trn) {
		this.trn = trn;
	}

	public T getInput() {
		return input;
	}

	public void setInput(T input) {
		this.input = input;
	}

}
