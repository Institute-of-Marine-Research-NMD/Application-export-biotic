package no.imr.nmdapi.client.biotic.export.mapper;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import no.imr.nmdapi.client.biotic.export.pojo.IndividualSample;
import no.imr.nmdapi.generic.nmdbiotic.domain.v1_4.IndividualType;
import no.imr.nmdapi.generic.nmdbiotic.domain.v1_4.StringDescriptionType;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Terry Hannant <a5119>
 */
public class IndividualSampleMapper implements RowMapper<IndividualSample> {

    @Override
    public IndividualSample mapRow(ResultSet rs, int i) throws SQLException {
        IndividualSample result = new IndividualSample(rs.getString("id"));

        IndividualType individualSample = new IndividualType();

        //individualSample.setAbdomenwidth(Converter.toDouble(rs.getBigDecimal(CLI_ABDOMENWIDTH)));
        //individualSample.setBlackspot(Converter.toStringDescriptionType(rs.getString(CLI_BLACKSPOT)));
        //individualSample.setCarapacelength(Converter.toDouble(rs.getBigDecimal(CLI_CARAPACELENGTH)));
        //individualSample.setCarapacewidth(Converter.toDouble(rs.getBigDecimal(CLI_CARAPACEWIDTH)));
        individualSample.setComment((rs.getString("comment")));
        //individualSample.setDevelopmentalstage(Converter.toStringDescriptionType(rs.getString(CLI_DEVELOPMENTALSTAGE)));
        //individualSample.setDiameter(Converter.toDouble(rs.getBigDecimal(CLI_DIAMETER)));
        individualSample.setDigestion(Converter.toStringDescriptionType(rs.getString("id_r_udplist_digest_deg")));
        //individualSample.setEggstage(Converter.toStringDescriptionType(rs.getString(CLI_EGGSTAGE)));
        individualSample.setFat(Converter.toStringDescriptionType(rs.getString("id_r_udplist_fat")));
        //individualSample.setFatpercent(Converter.toDouble(rs.getBigDecimal(CLI_FATPERCENT)));
        //individualSample.setForklength(Converter.toDouble(rs.getBigDecimal(CLI_FORKLENGTH)));
        //individualSample.setFungusheart(Converter.toStringDescriptionType(rs.getString(CLI_FUNGUSHEART)));
        //individualSample.setFungusouter(Converter.toStringDescriptionType(rs.getString(CLI_FUNGUSOUTER)));
        //individualSample.setFungusspores(Converter.toStringDescriptionType(rs.getString(CLI_FUNGUSSPORES)));
        //individualSample.setGeneticsnumber(Converter.toInteger(rs.getBigDecimal(CLI_GENETICSNUMBER)));
        //individualSample.setGillworms(Converter.toStringDescriptionType(rs.getString(CLI_GILLWORMS)));
        individualSample.setGonadweight(Converter.toDouble(rs.getBigDecimal("gonad_weight")));
        //individualSample.setHeadlength(Converter.toDouble(rs.getBigDecimal(CLI_HEADLENGTH)));
        //individualSample.setJapanesecut(Converter.toDouble(rs.getBigDecimal(CLI_JAPANESECUT)));
        individualSample.setLength(Converter.toDouble(rs.getBigDecimal("length")));
        individualSample.setLengthunit(Converter.toStringDescriptionType(rs.getString("id_r_udplist_length_type")));
        //individualSample.setLengthwithouthead(Converter.toDouble(rs.getBigDecimal(CLI_LENGTHWITHOUTHEAD)));
        individualSample.setLiver(Converter.toStringDescriptionType(rs.getString("id_r_udplist_liver")));
        individualSample.setLiverparasite(Converter.toStringDescriptionType(rs.getString("id_r_udplist_liver_parasite")));
        individualSample.setLiverweight(Converter.toDouble(rs.getBigDecimal("liver_weight")));
        //individualSample.setMantlelength(Converter.toDouble(rs.getBigDecimal(CLI_MANTLELENGTH)));
        //individualSample.setMeroslength(Converter.toDouble(rs.getBigDecimal(CLI_MEROSLENGTH)));
        //individualSample.setMeroswidth(Converter.toDouble(rs.getBigDecimal(CLI_MEROSWIDTH)));
        //individualSample.setMoultingstage(Converter.toStringDescriptionType(rs.getString(CLI_MOULTINGSTAGE)));
        individualSample.setProducttype(Converter.toStringDescriptionType(rs.getString("id_r_udplist_measurement_type")));
        //individualSample.setRightclawlength(Converter.toDouble(rs.getBigDecimal(CLI_RIGHTCLAWLENGTH)));
        //individualSample.setRightclawwidth(Converter.toDouble(rs.getBigDecimal(CLI_RIGHTCLAWWIDTH)));
        individualSample.setSex(Converter.toStringDescriptionType(rs.getString("id_r_udplist_sex")));
        //individualSample.setSnouttoanalfin(Converter.toDouble(rs.getBigDecimal(CLI_SNOUTTOANALFIN)));
        //individualSample.setSnouttoboneknob(Converter.toDouble(rs.getBigDecimal(CLI_SNOUTTOBONEKNOB)));
        //individualSample.setSnouttodorsalfin(Converter.toDouble(rs.getBigDecimal(CLI_SNOUTTODORSALFIN)));
        //individualSample.setSnouttoendoftail(Converter.toDouble(rs.getBigDecimal(CLI_SNOUTTOENDOFTAIL)));
        //individualSample.setSnouttoendsqueezed(Converter.toDouble(rs.getBigDecimal(CLI_SNOUTTOENDSQUEEZED)));
        //individualSample.setSpecialstage(Converter.toStringDescriptionType(rs.getString(CLI_SPECIALSTAGE)));
        individualSample.setSpecimenno(Converter.toInteger(rs.getBigDecimal("individual_no")));
        individualSample.setStage(Converter.toStringDescriptionType(rs.getString("id_r_udplist_stage")));
        individualSample.setStomachfillfield(Converter.toStringDescriptionType(rs.getString("id_r_udplist_stomach_fill")));
        individualSample.setStomachfilllab(Converter.toStringDescriptionType(rs.getString("id_r_udplist_stomach_fill_2")));
        individualSample.setStomachweight(Converter.toDouble(rs.getBigDecimal("stomach_weight")));
        //individualSample.setSwollengills(Converter.toStringDescriptionType(rs.getString(CLI_SWOLLENGILLS)));
        individualSample.setVertebrae(Converter.toInteger(rs.getBigDecimal("vertebrae")));
        individualSample.setVolume(Converter.toDouble(rs.getBigDecimal("volume")));
        individualSample.setWeight(Converter.toDouble(rs.getBigDecimal("weight")));

        result.setType(individualSample);
        return result;
    }
}
