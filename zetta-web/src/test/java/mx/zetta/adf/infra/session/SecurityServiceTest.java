package mx.zetta.adf.infra.session;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import mx.zetta.adf.SpringTest;
import mx.zetta.adf.commons.ApplicationException;
import mx.zetta.adf.commons.dto.UsuarioFirmadoTO;

public class SecurityServiceTest extends SpringTest {

    @Autowired
    SecurityService service;

    @Test
    public void testImpletacion() {
        Assert.assertNotNull(service);
    }

    @Test
    public void test_loginT0() {
        try {
            service.doLogin("zetta", "zetta");
        } catch (ApplicationException e) {

        }
    }

    @Test
    public void test_loginT1() {
        try {
            service.doLogin("zettaX", "zetta");
        } catch (ApplicationException e) {

        }
    }

    @Test
    public void test_loginT2() {
        try {
            service.doLogin("zetta", "zettaX");
        } catch (ApplicationException e) {

        }
    }

    @Test
    public void test_loginT3() {
        try {
            UsuarioFirmadoTO firmadoTO = service.doLogin("zetta", "zetta");
            service.doLogout(firmadoTO.getSessionID());
        } catch (ApplicationException e) {

        }
    }

}
