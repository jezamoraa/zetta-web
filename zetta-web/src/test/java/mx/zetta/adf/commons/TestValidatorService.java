package mx.zetta.adf.commons;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import mx.zetta.adf.SpringTest;
import mx.zetta.adf.commons.BVal.VCreate;
import mx.zetta.adf.commons.BVal.VUpdatePassword;
import mx.zetta.adf.commons.infra.entities.Usuario;

public class TestValidatorService extends SpringTest {

    @Autowired
    ValidatorService service;

    @Test
    public void testImpletacion() {
        Assert.assertNotNull(service);
    }

    @Test
    public void test_init() {
        service.init();
    }

    @Test
    public void test_validate_ObjectNULL_Group() {
        try {
            service.validate(null, VCreate.class);
            Assert.fail("Prueba fallida");
        } catch (ApplicationException e) {
            LOGGER.info("Exception OK" + e.getMessage());
        }
    }

    @Test
    public void test_validate_Object_GroupNULL() {
        try {
            service.validate(new Object(), null);
            Assert.fail("Prueba fallida");
        } catch (ApplicationException e) {
            LOGGER.info("Exception OK" + e.getMessage());
        }
    }

    @Test
    public void test_validate_Object_Group_Errors() {
        try {
            Usuario usuario = new Usuario();
            service.validate(usuario, VCreate.class);
            Assert.fail("Prueba fallida");
        } catch (ApplicationException e) {
            LOGGER.info("Exception OK" + e.getMessage());
        }
    }

    @Test
    public void test_validate_Object_Group_NOErrors() {
        try {
            Usuario usuario = new Usuario();
            usuario.setContrasena("secret");
            usuario.setRepetirContrasena("secret");
            service.validate(usuario, VUpdatePassword.class);

        } catch (ApplicationException e) {
            Assert.fail("Prueba fallida");
            LOGGER.info("Exception NO" + e.getMessage());
        }
    }

    //
    @Test
    public void test_validate_ObjectNULL() {
        try {
            service.validate(null);
            Assert.fail("Prueba fallida");
        } catch (ApplicationException e) {
            LOGGER.info("Exception OK" + e.getMessage());
        }
    }

    @Test
    public void test_validate_Object_Errors() {
        try {
            MyUser usuario = new MyUser();
            service.validate(usuario);
            Assert.fail("Prueba fallida");
        } catch (ApplicationException e) {
            LOGGER.info("Exception OK" + e.getMessage());
        }
    }

    @Test
    public void test_validate_Object_NOErrors() {
        try {
            MyUser usuario = new MyUser();
            usuario.name = "secret";
            service.validate(usuario);

        } catch (ApplicationException e) {
            Assert.fail("Prueba fallida");
            LOGGER.info("Exception NO" + e.getMessage());
        }
    }

}
