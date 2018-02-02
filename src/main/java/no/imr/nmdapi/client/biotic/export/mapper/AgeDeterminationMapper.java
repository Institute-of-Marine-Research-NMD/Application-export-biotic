package no.imr.nmdapi.client.biotic.export.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import no.imr.nmdapi.client.biotic.export.pojo.AgeDetermination;
import no.imr.nmdapi.generic.nmdbiotic.domain.v1_4.AgedeterminationType;

/**
 *
 * @author Terry Hannant <a5119>
 */
public class AgeDeterminationMapper implements RowMapper<AgeDetermination> {

    @Override
    public AgeDetermination mapRow(ResultSet rs, int i) throws SQLException {
        AgeDetermination result = new AgeDetermination(rs.getString("id"));

        AgedeterminationType ageDeterminationType = new AgedeterminationType();

        ageDeterminationType.setAge(Converter.toInteger(rs.getBigDecimal("age")));
        ageDeterminationType.setCalibration(Converter.toInteger(rs.getBigDecimal("calibration")));
        ageDeterminationType.setCoastalannuli(Converter.toInteger(rs.getBigDecimal("coastal_annuli")));
        ageDeterminationType.setGrowthzone1(Converter.toInteger(rs.getBigDecimal("growth_zone_1")));
        ageDeterminationType.setGrowthzone2(Converter.toInteger(rs.getBigDecimal("growth_zone_2")));
        ageDeterminationType.setGrowthzone3(Converter.toInteger(rs.getBigDecimal("growth_zone_3")));
        ageDeterminationType.setGrowthzone4(Converter.toInteger(rs.getBigDecimal("growth_zone_4")));
        ageDeterminationType.setGrowthzone5(Converter.toInteger(rs.getBigDecimal("growth_zone_5")));
        ageDeterminationType.setGrowthzone6(Converter.toInteger(rs.getBigDecimal("growth_zone_6")));
        ageDeterminationType.setGrowthzone7(Converter.toInteger(rs.getBigDecimal("growth_zone_7")));
        ageDeterminationType.setGrowthzone8(Converter.toInteger(rs.getBigDecimal("growth_zone_8")));
        ageDeterminationType.setGrowthzone9(Converter.toInteger(rs.getBigDecimal("growth_zone_9")));
        ageDeterminationType.setGrowthzonestotal(Converter.toInteger(rs.getBigDecimal("growth_zones_total")));

        ageDeterminationType.setOceanicannuli(Converter.toInteger(rs.getBigDecimal("oceanic_annuli")));
        ageDeterminationType.setOtolithcentre(Converter.toStringDescriptionType(rs.getString("id_r_udplist_otolith_centre")));
        ageDeterminationType.setOtolithedge(Converter.toStringDescriptionType(rs.getString("id_r_udplist_otolith_edge")));
        ageDeterminationType.setOtolithtype(Converter.toStringDescriptionType(rs.getString("id_r_udplist_type")));
        ageDeterminationType.setReadability(Converter.toStringDescriptionType(rs.getString("id_r_udplist_readability")));
        ageDeterminationType.setSpawningage(Converter.toInteger(rs.getBigDecimal("spawning_age")));
        ageDeterminationType.setSpawningzones(Converter.toInteger(rs.getBigDecimal("spawning_zones")));
        result.setType(ageDeterminationType);
        return result;
    }

}
