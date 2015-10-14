package no.imr.nmdapi.client.biotic.export.mapper;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import no.imr.nmdapi.client.biotic.export.pojo.IndividualSample;
import no.imr.nmdapi.client.biotic.export.pojo.Prey;
import no.imr.nmdapi.generic.nmdbiotic.domain.v1.IndividualType;
import no.imr.nmdapi.generic.nmdbiotic.domain.v1.PreyType;
import no.imr.nmdapi.generic.nmdbiotic.domain.v1.StringDescriptionType;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Terry Hannant <a5119>
 */
public class PreyMapper implements RowMapper<Prey> {

    @Override
    public  Prey mapRow(ResultSet rs, int i) throws SQLException {
         Prey result = new  Prey(rs.getString("id"));
        result.setindividualID(rs.getString("id_individual"));
        PreyType  preyType = new  PreyType();

        preyType.setPartno(BigInteger.valueOf(rs.getLong("part_no")));
        
        
        if (rs.getBigDecimal("total_weight") != null ){
            
        if (rs.getDouble("total_weight") != 0){
             preyType.setTotalweight(BigDecimal.valueOf(rs.getDouble("total_weight")).toPlainString());
           } else {
                 preyType.setTotalweight("0.0");
           }
        }
        
        if (rs.getDouble("total_count") != 0){
         preyType.setTotalcount(BigInteger.valueOf(rs.getLong("total_count")));
        }
 
        if (rs.getString("id_r_udplist_digest_deg")!=null){
            preyType.setDigestion(newStringDescriptionType(rs.getString("id_r_udplist_digest_deg"), null));
        }
         
        if (rs.getString("id_r_udplist_weight_unit")!=null){
            result.setWeighUnittID(rs.getString("id_r_udplist_weight_unit"));
        }

         if (rs.getString("id_r_udplist_interval")!=null){
            preyType.setInterval(newStringDescriptionType(rs.getString("id_r_udplist_interval"), null));
        }

        if (rs.getString("id_r_udplist_length_type")!=null){
            preyType.setLengthmeasurement(newStringDescriptionType(rs.getString("id_r_udplist_length_type"), null));
        }

        if (rs.getString("id_r_dev_stage")!=null){
            preyType.setDevstage(newStringDescriptionType(rs.getString("id_r_dev_stage"), null));
        }
        
        if (rs.getString("id_r_taxa")!=null){
         result.setTaxaID(rs.getString("id_r_taxa"));
        }
   
        
        result.setType(preyType);
        return result;
    }

    
    private StringDescriptionType newStringDescriptionType(String value,String description){
        StringDescriptionType result = new StringDescriptionType();
        result.setValue(value);
        if (description != null){
          result.setDescription(description);
        }                
       return result;
    }
    
}
