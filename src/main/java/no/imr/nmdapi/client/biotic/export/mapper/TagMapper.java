package no.imr.nmdapi.client.biotic.export.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import no.imr.nmdapi.generic.nmdbiotic.domain.v1_4.TagType;

/**
 *
 * @author Terry Hannant <a5119>
 */
public class TagMapper implements RowMapper<TagType> {

    @Override
    public TagType mapRow(ResultSet rs, int i) throws SQLException {
        TagType result = new TagType();

//        result.setTagno(BigInteger.valueOf(rs.getInt("mark_number")));
//
//        if (rs.getString("id_r_udplist_markingtype") != null) {
//            result.setTagtype(newStringDescriptionType(rs.getString("id_r_udplist_markingtype"), null));
//        }
        
        result.setTagno(Converter.toInteger(rs.getBigDecimal("mark_number")));
        result.setTagtype(Converter.toStringDescriptionType(rs.getString("id_r_udplist_markingtype")));        

        return result;
    }


}
