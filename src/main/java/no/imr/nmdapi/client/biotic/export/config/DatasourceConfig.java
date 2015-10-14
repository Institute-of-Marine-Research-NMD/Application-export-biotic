package no.imr.nmdapi.client.biotic.export.config;

import javax.sql.DataSource;
import no.imr.nmdapi.client.biotic.export.dao.AgeDeterminationDAO;
import no.imr.nmdapi.client.biotic.export.dao.BioticMissionDAO;
import no.imr.nmdapi.client.biotic.export.dao.CatchSampleDAO;
import no.imr.nmdapi.client.biotic.export.dao.FishStationDAO;
import no.imr.nmdapi.client.biotic.export.dao.IndividualSampleDAO;
import no.imr.nmdapi.client.biotic.export.dao.PlatformDAO;
import no.imr.nmdapi.client.biotic.export.dao.PreyDAO;
import no.imr.nmdapi.client.biotic.export.dao.StockDAO;
import no.imr.nmdapi.client.biotic.export.dao.TagDAO;
import no.imr.nmdapi.client.biotic.export.dao.TaxaDAO;
import no.imr.nmdapi.client.biotic.export.dao.UdpDAO;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Terry Hannant <a5119>
 */
@Configuration
public class DatasourceConfig {

    
    @Autowired
    @Qualifier("datasourceConf")
    PropertiesConfiguration configuration;
    
     
    @Bean
     public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();

         dataSource.setDriverClassName(configuration.getString("jdbc.driver"));
         dataSource.setUrl(configuration.getString("jdbc.url"));
         dataSource.setUsername(configuration.getString("jdbc.user"));
         dataSource.setPassword(configuration.getString("jdbc.password"));
        
         return dataSource;
     }
    
     

     @Bean
     public PlatformDAO platformCallsignDAO(){
       return new PlatformDAO();         
     }

     @Bean
     public BioticMissionDAO  bioticMissions(){
       return new BioticMissionDAO();         
     }

     @Bean
     public FishStationDAO   fishStations(){
       return  new FishStationDAO();         
     }

     @Bean
     public CatchSampleDAO   catchSamples(){
       return  new CatchSampleDAO();         
     }

       @Bean
     public IndividualSampleDAO   individualSamples(){
       return  new IndividualSampleDAO();         
     }
     
     @Bean
     public UdpDAO   udp(){
       return  new UdpDAO();         
     }

     @Bean
     public TaxaDAO   taxa(){
       return  new TaxaDAO(true);         
     }
     
     
    @Bean
     public AgeDeterminationDAO   ageDetermination(){
       return  new AgeDeterminationDAO();         
     }

    @Bean
     public PreyDAO   prey(){
       return  new PreyDAO();         
     }

    @Bean
     public TagDAO   tag(){
       return  new TagDAO();         
     }

     
     @Bean
     public StockDAO   stock(){
       return  new StockDAO();         
     }

     
}
