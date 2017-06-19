package no.imr.nmdapi.client.biotic.export.dao;

import java.util.List;
import javax.sql.DataSource;
import no.imr.nmdapi.client.biotic.export.mapper.FishStationMapper;
import no.imr.nmdapi.client.biotic.export.pojo.FishStation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Terry Hannant <a5119>
 */
public class FishStationDAO {
    
      
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
     
    String sql="SELECT  f.id,"
            + "serial_no,"
            + "n.nation,"
            + "p.platform,"
            + "station_no,"
            + "to_char(start_date, 'DD/MM/YYYY') as start_date,"
            + "to_char(stop_date, 'DD/MM/YYYY') as stop_date,"
            + "to_char(start_date, 'HH24:MI:SS') as start_time,"
            + "to_char(stop_date, 'HH24:MI:SS') as stop_time,"
            + "id_r_udplist_stationtype," //May be empty
            + "latitude_start,"
            + "longitude_start,"
            + "latitude_end,"
            + "longitude_end,"
            + "system,"
            + "f.area,"
            + "location,"
            + "bottom_depth_start,"
            + "bottom_depth_stop,"
            + "round(cast(fishing_depth_max as numeric),9) as fishing_depth_max,"
            + "round(cast(fishing_depth_min as numeric),9) as fishing_depth_min,"
            + "equipment_no,"  //Gear no
            + " e.code as gear," //Gear
            + "equipment_count," // gearcount
            + "direction_gps," //direction
            + "round(cast(speed_equipment as numeric),9) as speed_equipment ," //gearspeed
            + "log_start,"
            + "log_stop,"
            + "round(cast(distance as numeric),9) as distance,"
           /* + "distance,"*/
            + "id_r_udplist_gearcondition,"  // Gear condition
            + "id_r_udplist_trawl_quality,"
            + "id_r_udplist_data_quality,"
            + "round(cast(trawl_opening as numeric),9) as trawl_opening,"  //Based on feedback email (1/6/2017) from Asmund
            + "round(cast(trawl_door_spread as numeric),9) as trawl_door_spread,"
            + "fx.station as fixedcoastalstation,"
            + "soaktime,"
            + "tripno,"
            + "wire_length, comment"
            + " FROM nmdbiotic.fishstation f,"
            + "nmdreference.equipment e,"
            + "nmdreference.nation n,"
            + "nmdreference.platform p, "
            + "nmdreference.fixedcoastalstation fx"
            + " where id_m_mission =?"
            + " and f.id_r_equipment = e.id"
            + " and f.id_r_nation = n.id "
            + "and f.id_r_platform = p.id"
            + "and f.id_r_fixedstation = fx. id"
            + " order by serial_no";
      
    public List<FishStation> getFIshStations(String missionID)
    {
        return jdbcTemplate.query(sql, new FishStationMapper(),missionID);
    }
    
    
    
    
            


}
