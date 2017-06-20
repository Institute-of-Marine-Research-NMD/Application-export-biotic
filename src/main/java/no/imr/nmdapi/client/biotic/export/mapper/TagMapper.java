package no.imr.nmdapi.client.biotic.export.mapper;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import no.imr.nmdapi.generic.nmdbiotic.domain.v1_4.StringDescriptionType;
import no.imr.nmdapi.generic.nmdbiotic.domain.v1_4.TagType;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Terry Hannant <a5119>
 */
public class TagMapper implements RowMapper<TagType> {

    @Override
    public TagType mapRow(ResultSet rs, int i) throws SQLException {
        TagType result = new TagType();

        result.setTagno(BigInteger.valueOf(rs.getInt("mark_number")));

        if (rs.getString("id_r_udplist_markingtype") != null) {
            result.setTagtype(newStringDescriptionType(rs.getString("id_r_udplist_markingtype"), null));
        }

        return result;
    }

    private StringDescriptionType newStringDescriptionType(String value, String description) {
        StringDescriptionType result = new StringDescriptionType();
        result.setValue(value);
        if (description != null) {
            result.setDescription(description);
        }
        return result;
    }

}
