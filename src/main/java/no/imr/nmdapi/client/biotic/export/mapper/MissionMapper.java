package no.imr.nmdapi.client.biotic.export.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import no.imr.nmdapi.client.biotic.export.pojo.Mission;

/**
 *
 * @author Terry Hannant <a5119>
 */
public class MissionMapper implements RowMapper<Mission> {

    @Override
    public Mission mapRow(ResultSet rs, int i) throws SQLException {
        Mission m = new Mission();
        m.setId(rs.getString("id"));
        m.setMissionNumber(rs.getInt("missionnumber"));
        m.setStartTime(rs.getString("start_time"));
        m.setStopTime(rs.getString("stop_time"));
        m.setStartYear(rs.getInt("startyear"));
        m.setPurpose(rs.getString("purpose"));
        m.setMissionTypeCode(rs.getString("missiontypecode"));
        m.setMissionType(rs.getString("missiontype"));
        return m;
    }

}
