package mx.zetta.adf;

import java.util.logging.Logger;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringServiceConfiguration.class)
@Transactional
public abstract class SpringTest {
    public static final Logger LOGGER = Logger.getLogger(SpringTest.class.getName());

}
