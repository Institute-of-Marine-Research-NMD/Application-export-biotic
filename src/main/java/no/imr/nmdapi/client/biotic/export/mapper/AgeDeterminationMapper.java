package no.imr.nmdapi.client.biotic.export.mapper;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import no.imr.nmdapi.client.biotic.export.pojo.AgeDetermination;
import no.imr.nmdapi.generic.nmdbiotic.domain.v1.AgeDeterminationType;
import no.imr.nmdapi.generic.nmdbiotic.domain.v1.StringDescriptionType;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Terry Hannant <a5119>
 */
public class AgeDeterminationMapper implements RowMapper<AgeDetermination> {

    @Override
    public  AgeDetermination mapRow(ResultSet rs, int i) throws SQLException {
         AgeDetermination result = new  AgeDetermination(rs.getString("id"));
        
        AgeDeterminationType  ageDeterminationType = new  AgeDeterminationType();

      //  ageDeterminationType.setNo(BigInteger.valueOf(rs.getLong("part_no")));
        if (rs.getString("age")!=null){
       ageDeterminationType.setAge(BigInteger.valueOf(rs.getLong("age")));
        }
        
     if (rs.getLong("spawning_age") != 0) {
        ageDeterminationType.setSpawningage(BigInteger.valueOf(rs.getLong("spawning_age")));
     }

      if (rs.getString("spawning_zones") != null) {
        ageDeterminationType.setSpawningzones(BigInteger.valueOf(rs.getLong("spawning_zones")));
     }
          

         
        if (rs.getString("id_r_udplist_readability")!=null){
            ageDeterminationType.setReadability(newStringDescriptionType(rs.getString("id_r_udplist_readability"), null));
        }

        if (rs.getLong("growth_zone_1")!=0){
           ageDeterminationType.setGrowthzone1(BigInteger.valueOf(rs.getLong("growth_zone_1")));
        }
        
        if (rs.getLong("growth_zone_2")!=0){
           ageDeterminationType.setGrowthzone2(BigInteger.valueOf(rs.getLong("growth_zone_2")));
        }
        if (rs.getLong("growth_zone_3")!=0){
           ageDeterminationType.setGrowthzone3(BigInteger.valueOf(rs.getLong("growth_zone_3")));
        }
        if (rs.getLong("growth_zone_4")!=0){
           ageDeterminationType.setGrowthzone4(BigInteger.valueOf(rs.getLong("growth_zone_4")));
        }
        if (rs.getLong("growth_zone_5")!=0){
           ageDeterminationType.setGrowthzone5(BigInteger.valueOf(rs.getLong("growth_zone_5")));
        }
        if (rs.getLong("growth_zone_6")!=0){
           ageDeterminationType.setGrowthzone6(BigInteger.valueOf(rs.getLong("growth_zone_6")));
        }
        if (rs.getLong("growth_zone_7")!=0){
           ageDeterminationType.setGrowthzone7(BigInteger.valueOf(rs.getLong("growth_zone_7")));
        }
        if (rs.getLong("growth_zone_8")!=0){
           ageDeterminationType.setGrowthzone8(BigInteger.valueOf(rs.getLong("growth_zone_8")));
        }
        if (rs.getLong("growth_zone_9")!=0){
           ageDeterminationType.setGrowthzone9(BigInteger.valueOf(rs.getLong("growth_zone_9")));
        }
        if (rs.getLong("growth_zones_total")!=0){
           ageDeterminationType.setGrowthzonestotal(BigInteger.valueOf(rs.getLong("growth_zones_total")));
        }

        if (rs.getString("id_r_udplist_otolith_centre")!=null){
        ageDeterminationType.setOtolithcentre(newStringDescriptionType(rs.getString("id_r_udplist_otolith_centre"), null));
        }

         if (rs.getString("id_r_udplist_otolith_edge")!=null){
        ageDeterminationType.setOtolithedge(newStringDescriptionType(rs.getString("id_r_udplist_otolith_edge"), null));
        }

       if (rs.getString("id_r_udplist_type")!=null){
            ageDeterminationType.setOtolithtype(newStringDescriptionType(rs.getString("id_r_udplist_type"), null));
        }

       if (rs.getString("calibration")!=null){
           ageDeterminationType.setCalibration(newStringDescriptionType(rs.getString("calibration"), null));
       }
        
        result.setType(ageDeterminationType);
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
