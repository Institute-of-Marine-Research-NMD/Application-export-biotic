package no.imr.nmdapi.client.biotic.export.mapper;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import no.imr.nmdapi.generic.nmdbiotic.domain.v1.PreylengthType;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Terry Hannant <a5119>
 */
public class PreyLengthMapper implements RowMapper<PreylengthType> {

    @Override
    public  PreylengthType mapRow(ResultSet rs, int i) throws SQLException {
         PreylengthType result = new  PreylengthType();
        
        result.setLength(BigDecimal.valueOf(rs.getDouble("length")));
        
            
        if (rs.getLong("count") != 0){
             result.setCount(BigInteger.valueOf(rs.getLong("count")));
        }
        return result;
    }

    
}
