package mx.zetta.adf.commons.dto;

import java.io.Serializable;

public class ClienteTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String nombre;
	private String estadoCivil;
	private String correo;
	private String fechaNacimiento;
	private String formaContacto;

	public ClienteTO() {
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getFormaContacto() {
		return formaContacto;
	}

	public void setFormaContacto(String formaContacto) {
		this.formaContacto = formaContacto;
	}

}
