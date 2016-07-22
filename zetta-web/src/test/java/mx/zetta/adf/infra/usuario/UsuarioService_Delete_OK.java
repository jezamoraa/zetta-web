package mx.zetta.adf.infra.usuario;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import mx.zetta.adf.SpringTest;
import mx.zetta.adf.commons.ApplicationException;
import mx.zetta.adf.commons.dto.ParamInputTO;
import mx.zetta.adf.commons.infra.entities.Usuario;

public class UsuarioService_Delete_OK extends SpringTest {

    @Autowired
    UsuarioService service;

    String usuarioID;
    Usuario usuarioAActualizar;

    @Before
    public void before() throws ApplicationException {
        usuarioID = "nuevousuario";
        String password = "secret";
        Usuario usuario = new Usuario();
        usuario.setUsuarioID(usuarioID);
        usuario.setAdministrador(false);
        usuario.setContrasena(password);
        usuario.setCorreo("newusuario@localhost.com");
        usuario.setDescripcion("Prueba de Creaci�n OK");
        usuario.setHabilitado(true);
        usuario.setNombre("nuevo");
        usuario.setRepetirContrasena(password);

        try {
            service.doCreate(new ParamInputTO<Usuario>(null, usuario)).getOutput();

        } catch (ApplicationException e) {
        }
        usuarioAActualizar = new Usuario();
        usuarioAActualizar.setUsuarioID(usuarioID);
        usuarioAActualizar = service.getByID(new ParamInputTO<Usuario>(null, usuarioAActualizar)).getOutput();

    }

    @After
    public void after() throws ApplicationException {

        Usuario usuario = new Usuario();
        usuario.setUsuarioID(usuarioID);
        service.doDelete(new ParamInputTO<Usuario>(null, usuario));
    }

    @Test
    public void testDeleteOK() throws ApplicationException {
        try {
            usuarioAActualizar.setCorreo("nuevo@correo.com");
            service.doDelete(new ParamInputTO<Usuario>(null, usuarioAActualizar)).getOutput();
        } catch (ApplicationException e) {
            Assert.fail(e.getMessage());
            for (String key : e.getMessages().keySet()) {
                LOGGER.info(String.format("Campo: %s = %s", key, e.getMessages().get(key)));
            }
        }

    }

}
