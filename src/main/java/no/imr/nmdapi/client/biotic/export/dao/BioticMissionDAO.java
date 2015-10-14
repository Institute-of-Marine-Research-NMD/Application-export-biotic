package no.imr.nmdapi.client.biotic.export.dao;

import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import no.imr.nmdapi.client.biotic.export.mapper.MissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import no.imr.nmdapi.client.biotic.export.pojo.Mission;

/**
 *
 * @author Terry Hannant <a5119>
 */
public class BioticMissionDAO {
    
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
     
    
    private String baseQueryString = " select m.id as id,"
            + " missionnumber,"
            + "  to_char(start_time, 'DD/MM/YYYY') as start_time,"
            + "  to_char(stop_time,  'DD/MM/YYYY') as stop_time,"
            + " startyear,"
            + " purpose,"
            + " mt.code as missiontypecode,"   
            + " mt.description as missiontype "   
            + " from nmdmission.mission m,"
            + " nmdreference.missiontype mt"
            +"  where m.id_r_missiontype  = mt.id";
     
    private String  GET_DELIVERY_CODE=  "select  mt.code||'-'||m.startyear||'-'||p.platform||'-'||missionnumber "
             + " from nmdmission.mission m,"
             + "  nmdreference.missiontype mt,"
             + "  nmdreference.platform p  "
             + " where m.id_r_missiontype  = mt.id and"
             + "  m.id_r_platform = p.id and"
             + "  m.id  = ?";
    
    private String GET_CRUISE_CODE =  "SELECT cruisecode as delivery "
                + " from nmdmission.cruisemission cm"
                + " where cm.id_mission = ?";
  
    private String GET_MAX_MISSION_LOG = " SELECT  max(\"timestamp\") "
            + " FROM nmdmission.missionlog "
            + " where datatype = 'biotic' and"
            + " id_mission = ?  ";
        
   
      public List<Mission>  getAllBioticMissions(){
        String queryString = baseQueryString  + " and m.id in  ( select distinct id_m_mission from nmdbiotic.fishstation) ";
                
       return    jdbcTemplate.query(queryString,new MissionMapper());
     }


      public Mission getMission(String id) {
        String queryString = baseQueryString  + " and m.id  = ? ";
        return    jdbcTemplate.queryForObject(queryString,new MissionMapper(),id);
      }
      
      public Date getMaxMissionLog(String missionID) {
          return jdbcTemplate.queryForObject(GET_MAX_MISSION_LOG,Date.class,missionID);
      }
      
    
    public String getDelivery(String missionID){
        return jdbcTemplate.queryForObject(GET_DELIVERY_CODE, String.class, missionID);
    }
    
    public String getCruiseCode(String missionID)
    {
        String result = null;
        
        try
        {
        result = jdbcTemplate.queryForObject(GET_CRUISE_CODE, String.class, missionID);
        } catch (org.springframework.dao.EmptyResultDataAccessException erdae)
        {
            //Just return null;
        }
        
        return result ;
    } 
    
}
