package no.imr.nmdapi.client.biotic.export.dao;

import java.util.HashMap;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Terry Hannant <a5119>
 */
public class StockDAO {

    private JdbcTemplate jdbcTemplate;
    private final HashMap<String, String> nameMap;

    private final String sql = "SELECT code "
            + " from nmdreference.stock s "
            + " where s.id = ?";

    public StockDAO() {
        nameMap = new HashMap<String, String>();
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public String getCode(String stockID) {
        String result = "";
        if (nameMap.containsKey(stockID)) {
            result = nameMap.get(stockID);
        } else {
            result = jdbcTemplate.queryForObject(sql, String.class, stockID);
            nameMap.put(stockID, result);
        }

        return result;
    }

}
