package mx.zetta.adf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@EnableWebMvc
@Configuration
@Import(SpringServiceConfiguration.class)
//@ImportResource("classpath:mx/zetta/adf/web-services.xml")
public class SpringWebConfiguration {

    @Bean
    public ViewResolver viewResolver() {
        final InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/classes/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
    
    /*-@Bean
    public ContentNegotiationManagerFactoryBean contentNegotiationManagerFactoryBean() {
        final ContentNegotiationManagerFactoryBean contentNegotiationManagerFactoryBean = new ContentNegotiationManagerFactoryBean();
        contentNegotiationManagerFactoryBean.setFavorPathExtension(false);
        contentNegotiationManagerFactoryBean.setFavorParameter(true);
        final Properties mediaTypes = new Properties();
        mediaTypes.setProperty("json", "application/json");
        mediaTypes.setProperty("xml", "application/xml");
        mediaTypes.setProperty("html", "text/html");
        contentNegotiationManagerFactoryBean.setMediaTypes(mediaTypes);
        return contentNegotiationManagerFactoryBean;
    }*/
}
