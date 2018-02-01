package no.imr.nmdapi.client.biotic.export.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import no.imr.nmdapi.generic.nmdbiotic.domain.v1_4.PreylengthType;

/**
 *
 * @author Terry Hannant <a5119>
 */
public class PreyLengthMapper implements RowMapper<PreylengthType> {

    @Override
    public PreylengthType mapRow(ResultSet rs, int i) throws SQLException {
        PreylengthType result = new PreylengthType();

//        result.setLength(BigDecimal.valueOf(rs.getDouble("length")));
//
//        if (rs.getLong("count") != 0) {
//            result.setCount(BigInteger.valueOf(rs.getLong("count")));
//        }
        result.setLength(Converter.toDouble(rs.getBigDecimal("length")));        
        result.setCount(Converter.toInteger(rs.getBigDecimal("count")));
        return result;
    }

}
