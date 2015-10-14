package no.imr.nmdapi.client.biotic.export.config;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import no.imr.messaging.processor.ExceptionProcessor;
import no.imr.nmdapi.client.biotic.export.BioticGenerator;
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
     public Marshaller marshaller() throws JAXBException {
     JAXBContext ctx = JAXBContext.newInstance("no.imr.nmdapi.generic.nmdbiotic.domain.v1");
      Marshaller bioticMarshaller = ctx.createMarshaller();
        bioticMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        bioticMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        return bioticMarshaller;
     }
        
   
    
     
}
