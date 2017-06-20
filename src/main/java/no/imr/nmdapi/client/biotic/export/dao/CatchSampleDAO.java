package no.imr.nmdapi.client.biotic.export.dao;

import java.util.List;
import javax.sql.DataSource;
import no.imr.nmdapi.client.biotic.export.mapper.CatchSampleMapper;
import no.imr.nmdapi.client.biotic.export.pojo.CatchSample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Terry Hannant <a5119>
 */
public class CatchSampleDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private String baseQueryString = " select s.id as id,"
            + "sample_no,"
            + "id_r_taxa,"
            + "s.total_weight,"
            + "s.total_count,"
            + "s.total_volume,"
            + "id_r_udplist_sampletype,"
            + "id_r_udplist_conservationtype,"
            + "id_r_udplist_group,"
            + "id_r_udplist_length_type,"
            + "id_r_udplist_parasite,"
            + "id_r_udplist_measurement_type_total,"
            + "id_r_udplist_measurement_type_sampled,"
            + "id_r_udplist_non_biological,"
            + "id_r_udplist_genetics_spd,"
            + " sampled_weight,"
            + " sampled_count,"
            + " s.sampled_volume,"
            + "id_r_udplist_stomach,"
            + " id_r_stock,"
            + "id_r_udplist_otskj,"
            + "inumber,"
            + "comment"
            + " FROM nmdbiotic.catch c,"
            + " nmdbiotic.sample s "
            + " where c.id = s.id_catch"
            + " and c.id_fishstation = ?";

    public List<CatchSample> getCatchSamples(String fishStationID) {
        return jdbcTemplate.query(baseQueryString, new CatchSampleMapper(), fishStationID);
    }

}
