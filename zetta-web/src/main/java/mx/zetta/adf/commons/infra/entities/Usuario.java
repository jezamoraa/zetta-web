package mx.zetta.adf.commons.infra.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import mx.zetta.adf.commons.BVal.VCreate;
import mx.zetta.adf.commons.BVal.VUpdate;
import mx.zetta.adf.commons.BVal.VUpdatePassword;

@Entity
@Table(name = "USUARIO")
public class Usuario implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "USUARIO_ID")
    @NotNull(groups = { VCreate.class, VUpdate.class }, message = "{usuario.usuarioID.constraints.NotNull.message}")
    @Size(min = 1, max = 64, groups = { VCreate.class, VUpdate.class }, message = "{usuario.usuarioID.constraints.Size.message}")
    private String usuarioID;
    @Column(name = "USUARIO_NOMBRE")
    @NotNull(groups = { VCreate.class, VUpdate.class })
    @Size(min = 1, max = 256, groups = { VCreate.class, VUpdate.class })
    private String nombre;
    @Column(name = "USUARIO_DESCRIPCION")
    @NotNull(groups = { VCreate.class, VUpdate.class })
    @Size(min = 1, max = 512, groups = { VCreate.class, VUpdate.class })
    private String descripcion;
    @Column(name = "USUARIO_HABILITADO")
    @NotNull(groups = { VCreate.class, VUpdate.class })
    private boolean habilitado;
    @Column(name = "USUARIO_CONTRASENA")
    @NotNull(groups = { VCreate.class, VUpdatePassword.class })
    @Size(min = 1, max = 64, groups = { VCreate.class, VUpdatePassword.class })
    private String contrasena;
    @NotNull(groups = { VCreate.class, VUpdatePassword.class })
    @Size(min = 1, max = 64, groups = { VCreate.class, VUpdatePassword.class })
    @Transient
    private String repetirContrasena;
    @Column(name = "USUARIO_CORREO")
    @NotNull(groups = { VCreate.class, VUpdate.class })
    @Size(min = 1, max = 64, groups = { VCreate.class, VUpdate.class })
    private String correo;
    @Column(name = "USUARIO_ADMINISTRADOR")
    @NotNull(groups = { VCreate.class, VUpdate.class })
    private boolean administrador;

    public Usuario() {
    }

    public Usuario(String id) {
        this.usuarioID = id;
    }

    public String getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(String usuarioID) {
        this.usuarioID = usuarioID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public boolean isAdministrador() {
        return administrador;
    }

    public void setAdministrador(boolean administrador) {
        this.administrador = administrador;
    }

    public String getRepetirContrasena() {
        return repetirContrasena;
    }

    public void setRepetirContrasena(String repetirContrasena) {
        this.repetirContrasena = repetirContrasena;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}