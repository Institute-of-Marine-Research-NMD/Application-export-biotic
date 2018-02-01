package no.imr.nmdapi.client.biotic.export.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import no.imr.nmdapi.client.biotic.export.mapper.FishStationMapper;
import no.imr.nmdapi.client.biotic.export.pojo.FishStation;

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

    String sql = "SELECT  f.id," 
            + "serial_no,"
            + "n.nation,"
            + "p.platform,"
            + "station_no,"
            + "to_char(start_date, 'DD/MM/YYYY') as start_date,"
            + "to_char(stop_date, 'DD/MM/YYYY') as stop_date,"
            + "case when hour_unknown_start_date then null else to_char(start_date, 'HH24:MI:SS') end as start_time,"
            + "case when hour_unknown_stop_date then null else to_char(stop_date, 'HH24:MI:SS') end as stop_time,"
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
            + "fishing_depth_max,"
            + "fishing_depth_min,"
            + "depth_gear as fishing_depth_mean," // called fishingdepthmean in the xsd
            + "equipment_no," //Gear no
            + " e.code as gear," //Gear
            + "equipment_count," // gearcount
            + "direction_gps," //direction
            //+ "round(cast(speed_equipment as numeric),9) as speed_equipment ," //gearspeed
            + "speed_equipment ," //gearspeed
            + "log_start,"
            + "log_stop,"
            //+ "round(cast(distance as numeric),9) as distance,"
            + "distance,"
            /* + "distance,"*/
            + "id_r_udplist_gearcondition," // Gear condition
            + "id_r_udplist_trawl_quality,"
            + "id_r_udplist_data_quality,"
            + "trawl_opening," //Based on feedback email (1/6/2017) from Asmund
            + "trawl_door_spread,"
            + "trawl_std_opening,"
            + "trawl_std_door_spread,"
            + "id_r_fixedstation as fixedcoastalstation,"
            + "soaktime,"
            + "trip_no,"
            + "wire_length, comment"
            + " FROM "
            + "((nmdbiotic.fishstation f left join nmdreference.equipment e on f.id_r_equipment = e.id) left join nmdreference.nation n on f.id_r_nation = n.id) left join nmdreference.platform p on f.id_r_platform = p.id "
            + " where id_m_mission =?"
            + " order by serial_no";

    public List<FishStation> getFIshStations(String missionID) {
        return jdbcTemplate.query(sql, new FishStationMapper(), missionID);
    }
}
