package no.imr.nmdapi.client.biotic.export.dao;

import java.util.HashMap;
import java.util.List;
import javax.sql.DataSource;
import no.imr.nmdapi.client.biotic.export.mapper.UdpValueMapper;
import no.imr.nmdapi.client.biotic.export.pojo.UdpValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Terry Hannant <a5119>
 */
public class UdpDAO {

    private JdbcTemplate jdbcTemplate;
    private final HashMap<String, String> nameMap;

    private final String sql = "SELECT name "
            + " from nmdreference.u_udplist ul "
            + " where ul.id = ?";

    public UdpDAO() {
        nameMap = new HashMap<String, String>();
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public String getName(String udpListID) {

        if (udpListID == null) {
            return null;
        }
        String result = nameMap.get(udpListID);
        if (result == null) {

            result = jdbcTemplate.queryForObject(sql, String.class, udpListID);
            nameMap.put(udpListID, result);
        }
        return result;
    }

    public List<UdpValue> getUdpValues(String schema, String id) {
        String queryString = "SELECT "
                + "value_udp_list_guid,value_double,value_integer,value_text,"
                + "case when (case when uv.value_udp_list_guid is null then null else (select udp.udp_name from nmdreference.u_udplist ul, "
                + "nmdreference.u_udp udp where uv.value_udp_list_guid = ul.id and ul.id_u_udp = udp.id) end) = "
                + "'Spesialstadier_lodde_Forberg_hunn' then 'eggstage' else vop.propertyname end as propertyname"
                + " FROM "
                + schema + ".udp_value uv,"
                + " nmdreference.u_vobject_property vop where "
                + " uv.id_guid = ? "
                + " and vop.id = uv.id_r_u_vobject_property ";

        return jdbcTemplate.query(queryString, new UdpValueMapper(), id);
    }

}
