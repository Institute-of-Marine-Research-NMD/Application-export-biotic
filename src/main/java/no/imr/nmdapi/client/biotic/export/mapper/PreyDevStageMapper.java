package no.imr.nmdapi.client.biotic.export.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import no.imr.nmdapi.generic.nmdbiotic.domain.v1_4.CopepodedevstageType;

/**
 *
 * @author Terry Hannant <a5119>
 */
public class PreyDevStageMapper implements RowMapper<CopepodedevstageType> {

    @Override
    public CopepodedevstageType mapRow(ResultSet rs, int i) throws SQLException {
        CopepodedevstageType result = new CopepodedevstageType();

//        result.setCopepodedevstage(rs.getString("name"));
//        if (rs.getLong("count") != 0) {
//            result.setCount(BigInteger.valueOf(rs.getLong("count")));
//        }
        result.setCount(Converter.toInteger(rs.getBigDecimal("count")));
        result.setCopepodedevstage((rs.getString("name")));        
        return result;
    }

}
