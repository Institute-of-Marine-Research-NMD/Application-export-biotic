package no.imr.nmdapi.client.biotic.export.dao;

import java.util.List;
import javax.sql.DataSource;
import no.imr.nmdapi.client.biotic.export.mapper.FixedCoastalStationMapper;
import no.imr.nmdapi.client.biotic.export.pojo.FixedCoastalStation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author sjurl
 */
public class FixedCoastalstationDAO {

    private JdbcTemplate jdbcTemplate;

    private String sql = "select name, station from nmdreference.fixedcoastalstation"
            + " where id = ?";

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<FixedCoastalStation> getFIshStations(String fxstationId) {
        return jdbcTemplate.query(sql, new FixedCoastalStationMapper(), fxstationId);
    }
}
