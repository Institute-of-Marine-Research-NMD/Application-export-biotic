/*
 */
package no.imr.nmdapi.client.biotic.export.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import no.imr.nmdapi.client.biotic.export.pojo.FixedCoastalStation;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author sjurl
 */
public class FixedCoastalStationMapper implements RowMapper<FixedCoastalStation> {

    @Override
    public FixedCoastalStation mapRow(ResultSet rs, int rowNum) throws SQLException {
        FixedCoastalStation fs = new FixedCoastalStation();

        fs.setName(rs.getString("name"));
        fs.setStation(rs.getInt("station"));
        return fs;
    }

}
