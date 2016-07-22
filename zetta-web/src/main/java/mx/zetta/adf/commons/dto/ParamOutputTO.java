package mx.zetta.adf.commons.dto;

import java.io.Serializable;

/**
 * Clase que representa un Transfer Object para los parametros de salida de los
 * servicios
 * 
 */
public class ParamOutputTO<T> implements Serializable {

	/** Version del objeto serializable */
	private static final long serialVersionUID = 1L;

	private T output;

	/** Constructor por omision */
	public ParamOutputTO() {
	}

	public ParamOutputTO(T output) {
		this.output = output;
	}

	public T getOutput() {
		return output;
	}

	public void setOutput(T output) {
		this.output = output;
	}
}
