package no.imr.nmdapi.client.biotic.export.config;

import no.imr.messaging.processor.ExceptionProcessor;
import no.imr.nmdapi.client.biotic.export.BioticGenerator;
import no.imr.nmdapi.client.biotic.route.GenerateAll;
import no.imr.nmdapi.client.biotic.route.GenerateUpdated;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.commons.configuration.reloading.ReloadingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Terry Hannant <a5119>
 */
@Configuration
public class ApplicationConfig {
    
      
    @Bean(name = "configuration")
    public PropertiesConfiguration configuration() throws ConfigurationException {
        org.apache.commons.configuration.PropertiesConfiguration configuration = new org.apache.commons.configuration.PropertiesConfiguration(System.getProperty("catalina.base") + "/conf/biotic_queue_v1.properties");
        ReloadingStrategy reloadingStrategy = new FileChangedReloadingStrategy();
        configuration.setReloadingStrategy(reloadingStrategy);
        return configuration;
    }

    @Bean(name= "errorProcessor")
    public ExceptionProcessor exceptionProcessor(){
        return new ExceptionProcessor("bioticQueue");
    }
   
    @Bean
    public BioticGenerator bioticGenerator() {
        return new BioticGenerator();
    }
    
    @Bean
    public GenerateAll generareAllRoute() {
        return new GenerateAll();
    }

   @Bean
    public GenerateUpdated generareUpdatedlRoute() {
        return new GenerateUpdated();
    }

    
    

    
     
}
