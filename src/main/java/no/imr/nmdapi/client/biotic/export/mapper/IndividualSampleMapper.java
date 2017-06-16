package no.imr.nmdapi.client.biotic.export.mapper;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import no.imr.nmdapi.client.biotic.export.pojo.IndividualSample;
import no.imr.nmdapi.generic.nmdbiotic.domain.v1_4.IndividualType;
import no.imr.nmdapi.generic.nmdbiotic.domain.v1_4.StringDescriptionType;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Terry Hannant <a5119>
 */
public class IndividualSampleMapper implements RowMapper<IndividualSample> {

    @Override
    public  IndividualSample mapRow(ResultSet rs, int i) throws SQLException {
         IndividualSample result = new  IndividualSample(rs.getString("id"));
        
         IndividualType  individualSample = new  IndividualType();

         individualSample.setLength(BigDecimal.valueOf(rs.getDouble("length")));
         
         individualSample.setSpecimenno(BigInteger.valueOf(rs.getLong("individual_no")));

         if (rs.getDouble("weight") != 0){
         individualSample.setWeight(BigDecimal.valueOf(rs.getDouble("weight")));
        }
     
         if (rs.getDouble("volume") != 0){
         individualSample.setVolume(BigDecimal.valueOf(rs.getDouble("volume")));
        }

         if (rs.getDouble("liver_weight") != 0){
         individualSample.setLiverweight(BigDecimal.valueOf(rs.getDouble("liver_weight")));
        }

        if (rs.getString("gonad_weight") !=null){
        individualSample.setGonadweight(BigDecimal.valueOf(rs.getDouble("gonad_weight")));
        }

         if (rs.getDouble("stomach_weight") != 0){
        individualSample.setStomachweight(BigDecimal.valueOf(rs.getDouble("stomach_weight")));
        }
         
       if (rs.getLong("vertebrae") != 0){
           individualSample.setVertebrae(BigInteger.valueOf(rs.getInt("vertebrae")));
      }
         
        if (rs.getString("id_r_udplist_length_type")!=null){
        individualSample.setLengthunit(newStringDescriptionType(rs.getString("id_r_udplist_length_type"),null));
        }
     
       if (rs.getString("id_r_udplist_measurement_type")!=null){
        individualSample.setProducttype(newStringDescriptionType(rs.getString("id_r_udplist_measurement_type"),null));
        }
     
        
        if (rs.getString("id_r_udplist_stage")!=null){
        individualSample.setStage(newStringDescriptionType(rs.getString("id_r_udplist_stage"),null));
        }
        if (rs.getString("id_r_udplist_sex")!=null){
        individualSample.setSex(newStringDescriptionType(rs.getString("id_r_udplist_sex"),null));
         }
           if (rs.getString("id_r_udplist_digest_deg")!=null){
        individualSample.setDigestion(newStringDescriptionType(rs.getString("id_r_udplist_digest_deg"),null));
         }
        if (rs.getString("id_r_udplist_fat")!=null){
        individualSample.setFat(newStringDescriptionType(rs.getString("id_r_udplist_fat"),null));
         }
        if (rs.getString("id_r_udplist_liver")!=null){
        individualSample.setLiver(newStringDescriptionType(rs.getString("id_r_udplist_liver"),null));
         }
        if (rs.getString("id_r_udplist_liver_parasite")!=null){
        individualSample.setLiverparasite(newStringDescriptionType(rs.getString("id_r_udplist_liver_parasite"),null));
         }
     
    
        
       if (rs.getString("id_r_udplist_stomach_fill")!=null){
        individualSample.setStomachfillfield(newStringDescriptionType(rs.getString("id_r_udplist_stomach_fill"),null));
        }
                                                                       
       if ( (rs.getString("comment")!=null) && (!rs.getString("comment").isEmpty()))  {
               individualSample.setComment(rs.getString("comment"));
       }
       
       
        result.setType(individualSample);
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
