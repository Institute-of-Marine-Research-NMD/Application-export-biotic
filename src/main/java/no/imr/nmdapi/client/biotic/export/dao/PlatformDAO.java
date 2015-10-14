package no.imr.nmdapi.client.biotic.export.dao;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import no.imr.nmdapi.client.biotic.export.mapper.TypeValueMapper;
import no.imr.nmdapi.client.biotic.export.pojo.TypeValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Terry Hannant <a5119>
 */
public class PlatformDAO {
    
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
      
    private String platformCodesBeforeStartQuery = " select platformcode , "
            + "pcs.platformcodesysname as platformcodesysname  "
            + "from nmdreference.platformcode pc,"
            + " nmdreference.platformcodesys pcs,"
            + " nmdmission.mission m " 
            + " where pc.id_platformcodesys = pcs.id"
            + " and  pc.id_platform = m.id_r_platform" 
            + " and m.id = ? "
            + " and m.start_time >= pc.firstvaliddate  "
            + " order by   pc.firstvaliddate ";

   private String platformCodesAfterStartQuery = " select platformcode , "
            + "pcs.platformcodesysname as platformcodesysname  "
            + "from nmdreference.platformcode pc,"
            + " nmdreference.platformcodesys pcs,"
            + " nmdmission.mission m " 
            + " where pc.id_platformcodesys = pcs.id"
            + " and  pc.id_platform = m.id_r_platform" 
            + " and m.id = ? "
            + " and m.start_time < pc.firstvaliddate  "
            + " order by   pc.firstvaliddate ";
    
   public String getPlatform(String missionID){
      String sql =" SELECT platform "
              + " FROM nmdreference.platform p,"
              + "nmdmission.mission m"
              + "  where   m.id = ? and"
              + "  p.id = m.id_r_platform  ";
      
      return jdbcTemplate.queryForObject(sql,String.class,missionID);
   }
           
 
    
    public Map<String,String> getCruisePlatformCodes(String missionID){
        HashMap<String,String> result = new HashMap<String,String>();

        //The query will return all platform codes that were valid before mission start.
        //Since we are ordering by firstvaliddate the last platform code of each type found wil be
        // the valid one at mission start. 
        //Using a hash map so previous values will be discarded and only last added (for each type) will be kept
        List<TypeValue> platformList  = jdbcTemplate.query(platformCodesBeforeStartQuery,new TypeValueMapper("platformcodesysname","platformcode"),missionID);
            for ( TypeValue platform:platformList){
                result.put(  platform .getType(),platform.getValue());
            }
        
        return result;
     }

    public Map<String,TypeValue> getCruisePlatformAfterStart(String missionID){
        HashMap<String,TypeValue> result = new HashMap<String,TypeValue>();

        List platformList  = jdbcTemplate.query(platformCodesAfterStartQuery,new TypeValueMapper("platformcodesysname","platformcode"),missionID);
            for ( Object platform:platformList){
                result.put( ((TypeValue) platform ).getType(),((TypeValue) platform ));
            }
        
        return result;
     }
   
    
  
}
