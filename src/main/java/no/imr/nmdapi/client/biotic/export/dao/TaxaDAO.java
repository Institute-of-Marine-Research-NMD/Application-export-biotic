package no.imr.nmdapi.client.biotic.export.dao;

import java.util.HashMap;
import java.util.List;
import javax.sql.DataSource;
import no.imr.nmdapi.client.biotic.export.mapper.TaxaNameMapper;
import no.imr.nmdapi.client.biotic.export.pojo.Taxa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Terry Hannant <a5119>
 */
public class TaxaDAO {

    private JdbcTemplate jdbcTemplate;
    private HashMap<String, List<Taxa>> taxaNameCache;

    public TaxaDAO(boolean cached) {
        if (cached) {
            taxaNameCache = new HashMap<String, List<Taxa>>();
        }
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Taxa> getTaxaWithName(String taxaID) {
        List<Taxa> result = null;
        String sql = "select *  from"
                + "("
                + "SELECT ts.name, tsn,aphiaid,'1' as lorder,l.name as lang,preferred   "
                + "  FROM nmdreference.taxa ta, nmdreference.taxa_synonym ts,"
                + "  nmdreference.language l"
                + "  where ta.id = ts.id_taxa and"
                + "  l.id = ts.id_language and"
                + "  l.name ='Norwegian' and "
                + "   length(ts.name)>0 and "
                + "    ta.id=?"
                + "union "
                + "SELECT ts.name, tsn,aphiaid,'2' as norder, l.name as lang ,preferred  "
                + "  FROM nmdreference.taxa ta, nmdreference.taxa_synonym ts,"
                + "  nmdreference.language l"
                + "  where ta.id = ts.id_taxa and"
                + "  l.id = ts.id_language and"
                + "  l.name ='Scientific' and"
                + "    ta.id=? "
                + ") as la order by  preferred desc,lorder";

        if (taxaNameCache != null) {
            result = taxaNameCache.get(taxaID);
            if (result == null) {
                result = jdbcTemplate.query(sql, new TaxaNameMapper(), taxaID, taxaID);
                taxaNameCache.put(taxaID, result);
            }
        } else {
            result = jdbcTemplate.query(sql, new TaxaNameMapper(), taxaID, taxaID);
        }
        return result;
    }

    public String getTaxaTSN(String taxaID) {
        String sql = "SELECT tsn "
                + "  FROM nmdreference.taxa ta"
                + " where   ta.id=?";

        return jdbcTemplate.queryForObject(sql, String.class, taxaID);
    }

}
