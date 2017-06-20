package no.imr.nmdapi.client.biotic.export.config;

import java.io.File;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Terry Hannant <a5119>
 */
@Configuration
public class ComponentConfig {

    private static final String CATALINA_BASE = "catalina.base";

    @Autowired
    @Qualifier("configuration")
    PropertiesConfiguration configuration;

    @Bean(name = "datasourceConf")
    public PropertiesConfiguration datasourceConfig() throws ConfigurationException {
        return getConfigiration(configuration.getString("file.configuration.datasource"));
    }

    @Bean(name = "activeMQConf")
    public PropertiesConfiguration activeMQConfiguration() throws ConfigurationException {
        return getConfigiration(configuration.getString("file.configuration.activemq"));
    }

    private PropertiesConfiguration getConfigiration(String propertiesPath) throws ConfigurationException {
        if (!(propertiesPath.startsWith(File.separator))) {
            propertiesPath = System.getProperty(CATALINA_BASE) + "/conf/" + propertiesPath;
        }
        PropertiesConfiguration conf = new PropertiesConfiguration(propertiesPath);
        conf.setReloadingStrategy(new FileChangedReloadingStrategy());
        return conf;
    }

}
