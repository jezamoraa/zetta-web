package mx.zetta.adf.infra.usuario;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import mx.zetta.adf.SpringTest;
import mx.zetta.adf.commons.ApplicationException;
import mx.zetta.adf.commons.dto.ParamInputTO;
import mx.zetta.adf.commons.infra.entities.Usuario;

public class UsuarioService_Delete_FAIL extends SpringTest {

    @Autowired
    UsuarioService service;

    @Test
    public void testDeleteFail_01() {
        Usuario usuario = null;
        try {
            service.doDelete(new ParamInputTO<Usuario>(null, usuario)).getOutput();
            Assert.fail("Las Validaci�n no fueron activadas");
        } catch (ApplicationException e) {
            for (String key : e.getMessages().keySet()) {
                LOGGER.info(String.format("Campo: %s = %s", key, e.getMessages().get(key)));
            }
        }

    }

    @Test
    public void testDeleteFail_02() {
        Usuario usuario = new Usuario();
        usuario.setUsuarioID(null);
        try {
            service.doDelete(new ParamInputTO<Usuario>(null, usuario)).getOutput();
            Assert.fail("Las Validaci�n no fueron activadas");
        } catch (ApplicationException e) {
            for (String key : e.getMessages().keySet()) {
                LOGGER.info(String.format("Campo: %s = %s", key, e.getMessages().get(key)));
            }
        }

    }

}
