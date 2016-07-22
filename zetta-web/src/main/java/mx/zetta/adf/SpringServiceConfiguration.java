package mx.zetta.adf;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(SpringInfrastructureConfiguration.class)
@ComponentScan(basePackages = { "mx.zetta.adf.business", "mx.zetta.adf.infra", "mx.zetta.adf.commons" })
public class SpringServiceConfiguration {

}
