package mx.zetta.adf.infra.session;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import mx.zetta.adf.SpringTest;
import mx.zetta.adf.commons.ApplicationException;
import mx.zetta.adf.commons.dto.UsuarioFirmadoTO;

public class SessionServiceTest extends SpringTest {

    @Autowired
    SessionService service;
    @Autowired
    SecurityService securityService;

    @Test
    public void testImpletacion() {
        Assert.assertNotNull(service);
    }

    @Test
    public void test_addCacheT0() {
        try {
            UsuarioFirmadoTO usuarioFirmadoTO = securityService.doLogin("zetta", "zetta");
            service.addCache(usuarioFirmadoTO);
        } catch (ApplicationException e) {

        }
    }

    @Test
    public void test_GetUsuarioCacheT0() {
        try {
            UsuarioFirmadoTO usuarioFirmadoTO = securityService.doLogin("zetta", "zetta");
            service.addCache(usuarioFirmadoTO);
            service.getUsuarioCache(usuarioFirmadoTO.getSessionID());
        } catch (ApplicationException e) {

        }
    }

    @Test
    public void test_GetUsuarioCacheT1() {

        service.getUsuarioCache("");

    }

    @Test
    public void test_RemoveT0() {
        try {
            UsuarioFirmadoTO usuarioFirmadoTO = securityService.doLogin("zetta", "zetta");
            service.addCache(usuarioFirmadoTO);
            service.doRemoveUsuario(usuarioFirmadoTO.getSessionID());
        } catch (ApplicationException e) {

        }
    }

}
