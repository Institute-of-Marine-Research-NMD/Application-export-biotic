package no.imr.nmdapi.client.biotic.export.mapper;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import no.imr.nmdapi.client.biotic.export.pojo.FishStation;
import no.imr.nmdapi.generic.nmdbiotic.domain.v1.FishStationType;
import no.imr.nmdapi.generic.nmdbiotic.domain.v1.StringDescriptionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Terry Hannant <a5119>
 */
public class FishStationMapper implements RowMapper<FishStation> {
    private static final Logger LOG = LoggerFactory.getLogger(FishStationMapper.class);
    

    @Override
    public FishStation mapRow(ResultSet rs, int i) throws SQLException {
        FishStationType fishStation = new FishStationType();
        
        fishStation.setSerialno(BigInteger.valueOf(rs.getLong("serial_no")));
        
        fishStation.setNation(newStringDescriptionType(rs.getString("nation"), null));
        fishStation.setPlatform(newStringDescriptionType(rs.getString("platform"), null));
        fishStation.setStation(BigInteger.valueOf(rs.getLong("station_no")));
        fishStation.setStartdate(rs.getString("start_date"));
        fishStation.setStarttime(rs.getString("start_time"));
        fishStation.setStopdate(rs.getString("stop_date"));
        fishStation.setStoptime(rs.getString("stop_time"));
        if (rs.getString("id_r_udplist_stationtype") != null){
            fishStation.setStationtype(newStringDescriptionType(rs.getString("id_r_udplist_stationtype"), null));
        } 
      
        
        fishStation.setLatitudestart(BigDecimal.valueOf(rs.getDouble("latitude_start")));
        
        fishStation.setLongitudestart(BigDecimal.valueOf(rs.getDouble("longitude_start")));
        if (rs.getString("latitude_end") != null){
        fishStation.setLatitudeend(BigDecimal.valueOf(rs.getDouble("latitude_end")));
        }

        if (rs.getString("longitude_end") != null){
            fishStation.setLongitudeend(BigDecimal.valueOf(rs.getDouble("longitude_end")));
          }
        
        if (rs.getString("system") != null){
        fishStation.setSystem(newStringDescriptionType(rs.getString("system"), null));
        }
        if (rs.getString("area") != null){
        fishStation.setArea(newStringDescriptionType(rs.getString("area"), null));
        }
     
           if (rs.getString("location")!= null){
                  fishStation.setLocation(newStringDescriptionType(rs.getString("location"), null));
         }
     
        
        fishStation.setBottomdepthstart(BigDecimal.valueOf(rs.getDouble("bottom_depth_start")));
        fishStation.setBottomdepthstop(BigDecimal.valueOf(rs.getDouble("bottom_depth_stop")));
        fishStation.setFishingdepthmin(rs.getBigDecimal("fishing_depth_min"));
        fishStation.setFishingdepthmax(rs.getBigDecimal("fishing_depth_max"));
        if ((rs.getString("equipment_no")!= null) && (!rs.getString("equipment_no").equals(""))){
               fishStation.setGearno(newStringDescriptionType(rs.getString("equipment_no"), null));
        }
        fishStation.setGear(newStringDescriptionType(rs.getString("gear"), null));
        fishStation.setGearcount(BigInteger.valueOf(rs.getInt("equipment_count")));
        
        fishStation.setDirection(BigDecimal.valueOf(rs.getDouble("direction_gps")));
        fishStation.setGearspeed(BigDecimal.valueOf(rs.getDouble("speed_equipment")));
        
        fishStation.setStartlog(BigDecimal.valueOf(rs.getDouble("log_start")));
        if (rs.getDouble("log_stop")!=0){
        fishStation.setStoplog(BigDecimal.valueOf(rs.getDouble("log_stop")));
        }
        
        fishStation.setDistance(BigDecimal.valueOf(rs.getDouble("distance")));
        
       if (rs.getString("id_r_udplist_gearcondition") != null){
                   fishStation.setGearcondition(newStringDescriptionType(rs.getString("id_r_udplist_gearcondition"),null));
       }
       
        if (rs.getString("id_r_udplist_trawl_quality")!=null){
                fishStation.setTrawlquality(newStringDescriptionType(rs.getString("id_r_udplist_trawl_quality"), null));
        } 
        
        fishStation.setTrawlopening(rs.getBigDecimal("trawl_opening"));
        fishStation.setDoorspread(rs.getBigDecimal("trawl_door_spread"));
        if (rs.getLong("wire_length") !=0){
        fishStation.setWirelength(BigInteger.valueOf(rs.getLong("wire_length")));;
        }
        
        if ( (rs.getString("comment")!=null) && (!rs.getString("comment").isEmpty()))
        {
        fishStation.setComment(rs.getString("comment"));
        }
        
        return new FishStation(rs.getString("id") ,fishStation);
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
