package no.imr.nmdapi.client.biotic.export.mapper;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import no.imr.nmdapi.client.biotic.export.pojo.CatchSample;
import no.imr.nmdapi.generic.nmdbiotic.domain.v1_4.CatchsampleType;
import no.imr.nmdapi.generic.nmdbiotic.domain.v1_4.StringDescriptionType;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Terry Hannant <a5119>
 */
public class CatchSampleMapper implements RowMapper<CatchSample> {

    @Override
    public CatchSample mapRow(ResultSet rs, int i) throws SQLException {
        CatchSample result = new CatchSample(rs.getString("id"));

        CatchsampleType catchSample = new CatchsampleType();

        catchSample.setSamplenumber(BigInteger.valueOf(rs.getLong("sample_no")));

        if (rs.getLong("total_count") != 0) {
            catchSample.setCount(BigInteger.valueOf(rs.getLong("total_count")));
        }

        if (rs.getString("total_weight") != null) {
            catchSample.setWeight(BigDecimal.valueOf(rs.getDouble("total_weight")));
        }

        if (rs.getLong("sampled_count") != 0) {
            catchSample.setLengthsamplecount(BigInteger.valueOf(rs.getLong("sampled_count")));
        }

        if (rs.getDouble("sampled_weight") != 0) {
            catchSample.setLengthsampleweight(BigDecimal.valueOf(rs.getDouble("sampled_weight")));
        } else if (rs.getLong("sampled_count") != 0) {
            catchSample.setLengthsampleweight(BigDecimal.valueOf(rs.getDouble("total_weight")));
        }
        if (rs.getDouble("total_volume") != 0) {
            catchSample.setVolume(BigDecimal.valueOf(rs.getDouble("total_volume")));
        }
        
        if (rs.getDouble("sampled_volume") != 0) {
            catchSample.setLengthsamplevolume(BigDecimal.valueOf(rs.getDouble("sampled_volume")));
        } else if (rs.getLong("sampled_count") != 0) {
            catchSample.setLengthsamplevolume(BigDecimal.valueOf(rs.getDouble("total_volume")));
        }
        
        if (rs.getString("id_r_udplist_sampletype") != null) {
            catchSample.setSampletype(newStringDescriptionType(rs.getString("id_r_udplist_sampletype"), null));
        }
        if (rs.getString("id_r_udplist_conservationtype") != null) {
            catchSample.setConservation(newStringDescriptionType(rs.getString("id_r_udplist_conservationtype"), null));
        }
        if (rs.getString("id_r_udplist_group") != null) {
            catchSample.setGroup(newStringDescriptionType(rs.getString("id_r_udplist_group"), null));
        }
        if (rs.getString("id_r_udplist_length_type") != null) {
            catchSample.setLengthmeasurement(newStringDescriptionType(rs.getString("id_r_udplist_length_type"), null));
        }

        if (rs.getString("id_r_udplist_measurement_type_total") != null) {
            catchSample.setProducttype(newStringDescriptionType(rs.getString("id_r_udplist_measurement_type_total"), null));
        }

        if (rs.getString("id_r_udplist_measurement_type_sampled") != null) {
            catchSample.setSampleproducttype(newStringDescriptionType(rs.getString("id_r_udplist_measurement_type_sampled"), null));
        }

        if (rs.getString("id_r_udplist_stomach") != null) {
            catchSample.setStomach(newStringDescriptionType(rs.getString("id_r_udplist_stomach"), null));
        }

        if (rs.getString("id_r_udplist_parasite") != null) {
            catchSample.setParasite(newStringDescriptionType(rs.getString("id_r_udplist_parasite"), null));
        }

        if (rs.getString("id_r_udplist_genetics_spd") != null) {
            catchSample.setGenetics(newStringDescriptionType(rs.getString("id_r_udplist_genetics_spd"), null));
        }

        if (rs.getString("id_r_udplist_otskj") != null) {
            catchSample.setAgingstructure(newStringDescriptionType(rs.getString("id_r_udplist_otskj"), null));
        }
        if (rs.getString("id_r_udplist_non_biological") != null) {
            catchSample.setForeignobject(newStringDescriptionType(rs.getString("id_r_udplist_non_biological"), null));
        }

        if (rs.getLong("inumber") != 0) {
            catchSample.setSpecimensamplecount(BigInteger.valueOf(rs.getLong("inumber")));
        }

        if (rs.getString("id_r_stock") != null) {
            result.setStockID(rs.getString("id_r_stock"));
        }

        if ((rs.getString("comment") != null) && (rs.getString("comment").trim().length() > 0)) {
            catchSample.setComment(rs.getString("comment"));
        }

        result.setTaxaID(rs.getString("id_r_taxa"));

        result.setType(catchSample);
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
