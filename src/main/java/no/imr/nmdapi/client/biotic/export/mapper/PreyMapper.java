package no.imr.nmdapi.client.biotic.export.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import no.imr.nmdapi.client.biotic.export.pojo.Prey;
import no.imr.nmdapi.generic.nmdbiotic.domain.v1_4.PreyType;

/**
 *
 * @author Terry Hannant <a5119>
 */
public class PreyMapper implements RowMapper<Prey> {

    @Override
    public Prey mapRow(ResultSet rs, int i) throws SQLException {
        Prey result = new Prey(rs.getString("id"));
        result.setindividualID(rs.getString("id_individual"));
        PreyType preyType = new PreyType();

        if (rs.getString("id_r_udplist_weight_unit") != null) {
            result.setWeighUnittID(rs.getString("id_r_udplist_weight_unit"));
        }
        
        preyType.setDevstage(Converter.toStringDescriptionType(rs.getString("id_r_dev_stage")));
        preyType.setDigestion(Converter.toStringDescriptionType(rs.getString("id_r_udplist_digest_deg")));
        //preyType.setFishno(Converter.toInteger(rs.getBigDecimal(CLP_FISHNO)));
        preyType.setInterval(Converter.toStringDescriptionType(rs.getString("id_r_udplist_interval")));
        preyType.setLengthmeasurement(Converter.toStringDescriptionType(rs.getString("id_r_udplist_length_type")));
        preyType.setPartno(Converter.toInteger(rs.getBigDecimal("part_no")));
        //preyType.setSpecies((rs.getString(CLP_SPECIES)));
        preyType.setTotalcount(Converter.toInteger(rs.getBigDecimal("total_count")));
        preyType.setTotalweight(Converter.toDouble(rs.getBigDecimal("total_weight")));
        //preyType.setWeightresolution(Converter.toStringDescriptionType(rs.getString(CLP_WEIGHTRESOLUTION)));
        

        if (rs.getString("id_r_taxa") != null) {
            result.setTaxaID(rs.getString("id_r_taxa"));
        }

        result.setType(preyType);
        return result;
    }

}
