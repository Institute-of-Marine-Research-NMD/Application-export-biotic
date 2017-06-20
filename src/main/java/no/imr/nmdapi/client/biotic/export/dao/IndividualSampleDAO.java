package no.imr.nmdapi.client.biotic.export.dao;

import java.util.List;
import javax.sql.DataSource;
import no.imr.nmdapi.client.biotic.export.mapper.IndividualSampleMapper;
import no.imr.nmdapi.client.biotic.export.pojo.IndividualSample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Terry Hannant <a5119>
 */
public class IndividualSampleDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private String baseQueryString = " select i.id as id,"
            + " length,"
            + "weight,"
            + "volume,"
            + "individual_no,"
            + "liver_weight,"
            + "gonad_weight,"
            + "stomach_weight,"
            + "vertebrae,"
            + " id_r_udplist_length_type,"
            + " id_r_udplist_sex,"
            + "id_r_udplist_stage,"
            + "id_r_udplist_digest_deg,"
            + "id_r_udplist_measurement_type,"
            + "id_r_udplist_fat,"
            + "id_r_udplist_liver,"
            + "id_r_udplist_liver_parasite,"
            + "id_r_udplist_stomach_fill,"
            + "id_r_udplist_stomach_fill_2,"
            + "comment"
            + " FROM nmdbiotic.individual i "
            + " where i.id_sample = ?";
//            + " order by individual_no";

    public List<IndividualSample> getIndividualSamples(String sampleID) {
        return jdbcTemplate.query(baseQueryString, new IndividualSampleMapper(), sampleID);
    }

}
