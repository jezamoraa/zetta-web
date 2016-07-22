package mx.zetta.adf.infra.usuario;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import mx.zetta.adf.SpringTest;
import mx.zetta.adf.commons.ApplicationException;
import mx.zetta.adf.commons.dto.ParamInputTO;
import mx.zetta.adf.commons.infra.entities.Usuario;

public class UsuarioService_Create_FAIL extends SpringTest {

    @Autowired
    UsuarioService service;

    @Test
    public void testCreateFail_01() {
        Usuario usuario = new Usuario();
        usuario.setUsuarioID("fffffffffffffffffffdfasdfjasdlkfajs�flkasjdf�lkasjfd�laskjdf�alkjsdf");
        try {
            service.doCreate(new ParamInputTO<Usuario>(null, usuario)).getOutput();
            Assert.fail("Las Validaci�n no fueron activadas");
        } catch (ApplicationException e) {
            for (String key : e.getMessages().keySet()) {
                LOGGER.info(String.format("Campo: %s = %s", key, e.getMessages().get(key)));
            }
        }

    }

    @Test
    public void testCreateFail_02() {
        String password = "secret";
        Usuario usuario = new Usuario();
        usuario.setUsuarioID("nuevoUsuario");
        usuario.setAdministrador(false);
        usuario.setContrasena(password);
        usuario.setCorreo("newusuario@localhost.com");
        usuario.setDescripcion("Prueba de Creaci�n OK");
        usuario.setHabilitado(true);
        usuario.setNombre("nuevo");
        usuario.setRepetirContrasena(password + 2);
        try {
            service.doCreate(new ParamInputTO<Usuario>(null, usuario)).getOutput();
            Assert.fail("Las Validaci�n no fueron activadas");
        } catch (ApplicationException e) {
            for (String key : e.getMessages().keySet()) {
                LOGGER.info(String.format("Campo: %s = %s", key, e.getMessages().get(key)));
            }
        }

    }

    @Test
    public void testCreateFail_03() {
        String password = "secret";
        Usuario usuario = new Usuario();
        usuario.setUsuarioID("zetta");
        usuario.setAdministrador(false);
        usuario.setContrasena(password);
        usuario.setCorreo("newusuario@localhost.com");
        usuario.setDescripcion("Prueba de Creaci�n OK");
        usuario.setHabilitado(true);
        usuario.setNombre("nuevo");
        usuario.setRepetirContrasena(password);
        try {
            service.doCreate(new ParamInputTO<Usuario>(null, usuario)).getOutput();
            Assert.fail("Las Validaci�n no fueron activadas");
        } catch (ApplicationException e) {
            for (String key : e.getMessages().keySet()) {
                LOGGER.info(String.format("Campo: %s = %s", key, e.getMessages().get(key)));
            }
        }

    }

}
