package no.imr.nmdapi.client.biotic.export.mapper;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import no.imr.nmdapi.client.biotic.export.pojo.Taxa;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Terry Hannant <a5119>
 */
public class TaxaNameMapper implements RowMapper<Taxa> {
    
    public Taxa mapRow(ResultSet rs, int rowNum) throws SQLException {
        Taxa taxa = new Taxa();
        taxa.setTsn(rs.getString("tsn"));
        if (rs.getString("aphiaid") !=null)
        {
        taxa.setAphia(BigInteger.valueOf(rs.getLong("aphiaid")));
        }
        taxa.setName(rs.getString("name"));
        taxa.setName(rs.getString("name"));
        taxa.setLanguage(rs.getString("lang"));

        return taxa;
    } 
    
}
