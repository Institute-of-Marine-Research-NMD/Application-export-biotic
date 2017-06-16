package no.imr.nmdapi.client.biotic.export.dao;

import java.util.List;
import javax.sql.DataSource;
import no.imr.nmdapi.client.biotic.export.mapper.PreyDevStageMapper;
import no.imr.nmdapi.client.biotic.export.mapper.PreyLengthMapper;
import no.imr.nmdapi.client.biotic.export.mapper.PreyMapper;
import no.imr.nmdapi.client.biotic.export.pojo.Prey;
import no.imr.nmdapi.generic.nmdbiotic.domain.v1_4.CopepodedevstageType;
import no.imr.nmdapi.generic.nmdbiotic.domain.v1_4.PreylengthType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Terry Hannant <a5119>
 */
public class PreyDAO {

 private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
     
    private String basePreyQueryString = " select p.id as id,"
            + " total_count,"
            + "total_weight,"
            + "part_no,"
            + " id_r_taxa,"
            + "id_r_udplist_length_type,"
            + "id_r_udplist_interval,"
            + "id_r_udplist_weight_unit,"
            + "id_r_dev_stage,"
            + " id_r_udplist_digest_deg"
            + " FROM nmdbiotic.prey p"
             + " where  p.id_individual = ?";

        private String basePreyLengthQueryString = " select pl.id as id,"
            + " count,"
            + "length"
            + " FROM nmdbiotic.prey_length pl"
             + " where  pl.id_prey = ?";
        
       private String baseCopepodedevstageQueryString = " select pd.id as id,"
            + " count,"
            + " ul.name as  name "
            + " FROM nmdbiotic.prey_dev_stage pd,"
            + " nmdreference.u_udplist ul"
            + " where  pd.id_prey = ?"
            + " and ul.id = pd.id_r_udplist_dev_stage ";


     public List<Prey> getPrey(String individualID)
    {
        return jdbcTemplate.query(basePreyQueryString, new PreyMapper(),individualID);
    }

      public List<Prey> getPreyBySample(String sampleID)
    {
            String queryString = " select p.id as id,"
                    + "id_individual,"
            + " total_count,"
            + "total_weight,"
            + "p.part_no,"
            + "p.id_r_taxa,"
            + "p.id_r_udplist_length_type,"
            + "p.id_r_udplist_interval,"
            + "p.id_r_udplist_weight_unit,"
            + "p.id_r_dev_stage,"
            + "p.id_r_udplist_digest_deg"
            + " FROM nmdbiotic.prey p,"
             + "  nmdbiotic.individual i"
             + " where "
                    + " i.id = p.id_individual and "
                    + "i.id_sample = ?";

        
        return jdbcTemplate.query(queryString, new PreyMapper(),sampleID);
    }

     public List<PreylengthType> getPreyLength(String preyID)
    {
        return jdbcTemplate.query(basePreyLengthQueryString, new PreyLengthMapper(),preyID);
    }
     
     public List<CopepodedevstageType> getCopepodedevstage(String preyID){
        return jdbcTemplate.query(baseCopepodedevstageQueryString, new PreyDevStageMapper(),preyID);
     }
        

     
     
}
