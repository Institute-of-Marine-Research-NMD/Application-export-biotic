package no.imr.nmdapi.client.biotic.export.dao;

import java.util.List;
import javax.sql.DataSource;
import no.imr.nmdapi.client.biotic.export.mapper.TagMapper;
import no.imr.nmdapi.generic.nmdbiotic.domain.v1.TagType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Terry Hannant <a5119>
 */
public class TagDAO {

 private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
     
    private String baseQueryString = " select m.id as id,"
            + "mark_number,"
            + "id_r_udplist_markingtype"
            + " FROM nmdbiotic.marking m"
             + " where m .id_individual = ?";
    
     public List<TagType> getTag(String individualID)
    {
        return jdbcTemplate.query(baseQueryString,new TagMapper(),individualID);
    }

}
