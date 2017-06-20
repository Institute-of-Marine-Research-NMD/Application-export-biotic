package no.imr.nmdapi.client.biotic.export.dao;

import java.util.List;
import javax.sql.DataSource;
import no.imr.nmdapi.client.biotic.export.mapper.AgeDeterminationMapper;
import no.imr.nmdapi.client.biotic.export.pojo.AgeDetermination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Terry Hannant <a5119>
 */
public class AgeDeterminationDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private String baseQueryString = " select a.id as id,"
            + "age,"
            + "spawning_age,"
            + "spawning_zones,"
            + " id_r_udplist_readability,"
            + "id_r_udplist_type,"
            + "id_r_udplist_otolith_edge,"
            + "id_r_udplist_otolith_centre,"
            + "calibration,"
            + "growth_zone_1,"
            + "growth_zone_2,"
            + "growth_zone_3,"
            + "growth_zone_4,"
            + "growth_zone_5,"
            + "growth_zone_6,"
            + "growth_zone_7,"
            + "growth_zone_8,"
            + "growth_zone_9,"
            + " growth_zones_total, "
            + " coastal_annuli,"
            + " oceanic_annuli"
            + " FROM nmdbiotic.age_determination a"
            + " where  a.id_individual = ?";

    public List<AgeDetermination> getAgeDetermination(String individualID) {
        return jdbcTemplate.query(baseQueryString, new AgeDeterminationMapper(), individualID);
    }

}
