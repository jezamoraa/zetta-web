package mx.zetta.adf;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class SpringInfrastructureConfiguration {

    @Bean(name = { "crossover-pu" })
    public EntityManagerFactory entityManagerFactory() {
        return Persistence.createEntityManagerFactory("crossover-pu");
    }

    @Bean(name = { "transactionManager" })
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
        return jpaTransactionManager;
    }

    @Bean(name = "messageSource")
    public org.springframework.context.support.ResourceBundleMessageSource messageSource() {
        final ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
        String[] resources = null;
        try (Stream<Path> stream = Files
                .walk(new File(SpringInfrastructureConfiguration.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()).toPath())) {
            List<String> messagesResources = stream//
                    .filter(Files::isRegularFile)//
                    .filter(currentPath -> currentPath.toString().endsWith("Resources.properties"))//
                    .map(currentPath -> currentPath.toString())//
                    .map(file -> file.substring(file.indexOf("mx")))//
                    .map(file -> file.substring(0, file.indexOf(".")))//
                    .collect(Collectors.toList());
            resources = messagesResources.toArray(new String[] {});
        } catch (IOException | URISyntaxException e) {
        }
        resourceBundleMessageSource.setBasenames(resources);
        resourceBundleMessageSource.setDefaultEncoding("UTF-8");
        return resourceBundleMessageSource;
    }

}
