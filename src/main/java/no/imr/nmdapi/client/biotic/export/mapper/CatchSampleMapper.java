package no.imr.nmdapi.client.biotic.export.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import no.imr.nmdapi.client.biotic.export.pojo.CatchSample;
import no.imr.nmdapi.generic.nmdbiotic.domain.v1_4.CatchsampleType;

/**
 *
 * @author Terry Hannant <a5119>
 */
public class CatchSampleMapper implements RowMapper<CatchSample> {

    @Override
    public CatchSample mapRow(ResultSet rs, int i) throws SQLException {
        CatchSample result = new CatchSample(rs.getString("id"));

        CatchsampleType catchSample = new CatchsampleType();

        //catchsample.setAbundancecategory(Converter.toStringDescriptionType(rs.getString(CLC_ABUNDANCECATEGORY)));
        catchSample.setAgingstructure(Converter.toStringDescriptionType(rs.getString("id_r_udplist_otskj")));
        //catchSample.setAphia(rs.getString(CLC_APHIA));
        catchSample.setConservation(Converter.toStringDescriptionType(rs.getString("id_r_udplist_conservationtype")));
        catchSample.setForeignobject(Converter.toStringDescriptionType(rs.getString("id_r_udplist_non_biological")));
        catchSample.setGenetics(Converter.toStringDescriptionType(rs.getString("id_r_udplist_genetics_spd")));
        catchSample.setGroup(Converter.toStringDescriptionType(rs.getString("id_r_udplist_group")));
        catchSample.setLengthmeasurement(Converter.toStringDescriptionType(rs.getString("id_r_udplist_length_type")));
        catchSample.setLengthsamplecount(Converter.toInteger(rs.getBigDecimal("sampled_count")));
        catchSample.setLengthsamplevolume(Converter.toDouble(rs.getBigDecimal("sampled_volume")));
        catchSample.setLengthsampleweight(Converter.toDouble(rs.getBigDecimal("sampled_weight")));
        //catchSample.setNoname(rs.getString(CLC_NONAME));
        catchSample.setParasite(Converter.toStringDescriptionType(rs.getString("id_r_udplist_parasite")));
        catchSample.setProducttype(Converter.toStringDescriptionType(rs.getString("id_r_udplist_measurement_type_total")));
        catchSample.setRaisingfactor(Converter.toDouble(rs.getBigDecimal("raising_factor")));
        catchSample.setCount(Converter.toInteger(rs.getBigDecimal("total_count")));
        catchSample.setSamplenumber(Converter.toInteger(rs.getBigDecimal("sample_no")));
        catchSample.setVolume(Converter.toDouble(rs.getBigDecimal("total_volume")));
        catchSample.setSampleproducttype(Converter.toStringDescriptionType(rs.getString("id_r_udplist_measurement_type_sampled")));
        catchSample.setSampletype(Converter.toStringDescriptionType(rs.getString("id_r_udplist_sampletype")));
        //catchSample.setSpecies(rs.getString(CLC_SPECIES));
        catchSample.setSpecimensamplecount(Converter.toInteger(rs.getBigDecimal("inumber")));
        catchSample.setStomach(Converter.toStringDescriptionType(rs.getString("id_r_udplist_stomach")));
        catchSample.setWeight(Converter.toDouble(rs.getBigDecimal("total_weight")));
        

        catchSample.setComment(rs.getString("comment"));
        
        result.setStockID(rs.getString("id_r_stock"));

        result.setTaxaID(rs.getString("id_r_taxa"));

        result.setType(catchSample);
        return result;
    }

}
