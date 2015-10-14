package no.imr.nmdapi.client.biotic.export.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import no.imr.nmdapi.client.biotic.export.pojo.UdpValue;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Terry Hannant <a5119>
 */
public class UdpValueMapper implements RowMapper<UdpValue> {
    
    public UdpValue mapRow(ResultSet rs, int rowNum) throws SQLException {
        UdpValue udpValue = new UdpValue();
       
        udpValue.setValueText(rs.getString("value_text"));
        udpValue.setValueUDPListID(rs.getString("value_udp_list_guid"));
        udpValue.setValueDouble(rs.getDouble("value_double"));
        udpValue.setValueInteger(rs.getLong("value_integer"));
        
        udpValue.setPropertyName(rs.getString("propertyname"));
        
        return udpValue;
    } 
    
}
