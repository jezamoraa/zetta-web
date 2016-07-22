package mx.zetta.adf.commons;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import mx.zetta.adf.SpringTest;

public class MessageSourceTest extends SpringTest {

    @Autowired
    MessageSource messageSource;

    @Test
    public void testImpletacion() {
        LOGGER.info(messageSource.getMessage("USUARIO_M01", null, null));
    }

}
