package no.imr.nmdapi.client.biotic.export.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import no.imr.nmdapi.client.biotic.export.pojo.FishStation;
import no.imr.nmdapi.generic.nmdbiotic.domain.v1_4.FishstationType;

/**
 *
 * @author Terry Hannant <a5119>
 */
public class FishStationMapper implements RowMapper<FishStation> {

    private static final Logger LOG = LoggerFactory.getLogger(FishStationMapper.class);

    @Override
    public FishStation mapRow(ResultSet rs, int i) throws SQLException {
        FishstationType fishStation = new FishstationType();
        
        fishStation.setArea(Converter.toInteger(rs.getBigDecimal("area")));
        fishStation.setBottomdepthstart(Converter.toDouble(rs.getBigDecimal("bottom_depth_start")));      
        fishStation.setBottomdepthstop(Converter.toDouble(rs.getBigDecimal("bottom_depth_stop")));
        fishStation.setComment(rs.getString("comment"));
        //fishStation.setCountofvessels(Converter.toInteger(rs.getBigDecimal(CLF_COUNTOFVESSELS)));
        fishStation.setDataquality(Converter.toStringDescriptionType(rs.getString("id_r_udplist_data_quality")));
        fishStation.setDirection(Converter.toDouble(rs.getBigDecimal("direction_gps")));
        fishStation.setDistance(Converter.toDouble(rs.getBigDecimal("distance")));
        fishStation.setDoorspread(Converter.toDouble(rs.getBigDecimal("trawl_door_spread")));
        fishStation.setDoorspreadsd(Converter.toDouble(rs.getBigDecimal("trawl_std_door_spread")));
        //fishStation.setFishabundance(Converter.toStringDescriptionType(rs.getString(CLF_FISHABUNDANCE)));
        //fishStation.setFishdistribution(Converter.toStringDescriptionType(rs.getString("trawl_std_door_spread")));
        fishStation.setFishingdepthmax(Converter.toDouble(rs.getBigDecimal("fishing_depth_max")));
        fishStation.setFishingdepthmean(Converter.toDouble(rs.getBigDecimal("fishing_depth_mean")));
        fishStation.setFishingdepthmin(Converter.toDouble(rs.getBigDecimal("fishing_depth_min")));
        //fishStation.setFishingground(Converter.toStringDescriptionType(rs.getString(CLF_FISHINGGROUND)));
        fishStation.setFixedstation(Converter.toStringDescriptionType(rs.getString("fixedcoastalstation")));
        //fishStation.setFlora(Converter.toStringDescriptionType(rs.getString(CLF_FLORA)));
        //fishStation.setFlowconst(Converter.toDouble(rs.getBigDecimal(CLF_FLOWCONST)));
        //fishStation.setFlowcount(Converter.toInteger(rs.getBigDecimal(CLF_FLOWCOUNT)));
        fishStation.setGear(Converter.toStringDescriptionType(rs.getString("gear")));
        fishStation.setGearcondition(Converter.toStringDescriptionType(rs.getString("id_r_udplist_gearcondition")));
        fishStation.setGearcount(Converter.toInteger(rs.getBigDecimal("equipment_count")));
        fishStation.setGearno(Converter.toInteger(rs.getBigDecimal("equipment_no")));
        fishStation.setGearspeed(Converter.toDouble(rs.getBigDecimal("speed_equipment")));
        //fishStation.setHaulvalidity(Converter.toStringDescriptionType(rs.getString(CLF_HAULVALIDITY)));
        //fishStation.setLandingsite(Converter.toStringDescriptionType(rs.getString(CLF_LANDINGSITE)));
        fishStation.setLatitudeend(Converter.toDouble(rs.getBigDecimal("latitude_end")));
        fishStation.setLatitudestart(Converter.toDouble(rs.getBigDecimal("latitude_start")));
        fishStation.setLocation(rs.getString("location"));
        fishStation.setLongitudeend(Converter.toDouble(rs.getBigDecimal("longitude_end")));
        fishStation.setLongitudestart(Converter.toDouble(rs.getBigDecimal("longitude_start")));
        fishStation.setNation(Converter.toStringDescriptionType(rs.getString("nation")));
        fishStation.setPlatform(Converter.toStringDescriptionType(rs.getString("platform")));
        //fishStation.setSea(Converter.toStringDescriptionType(rs.getString(CLF_SEA)));
        fishStation.setSerialno(Converter.toInteger(rs.getBigDecimal("serial_no")));
        fishStation.setSoaktime(Converter.toDouble(rs.getBigDecimal("soaktime")));
        //fishStation.setClouds(Converter.toStringDescriptionType(rs.getString(CLF_CLOUDS)));
        fishStation.setStartdate(rs.getString("start_date"));
        fishStation.setStartlog(Converter.toDouble(rs.getBigDecimal("log_start")));
        fishStation.setStarttime(rs.getString("start_time"));
        fishStation.setStation(Converter.toInteger(rs.getBigDecimal("station_no")));
        fishStation.setStationtype(Converter.toStringDescriptionType(rs.getString("id_r_udplist_stationtype")));
        fishStation.setStopdate(rs.getString("stop_date"));
        fishStation.setStoplog(Converter.toDouble(rs.getBigDecimal("log_stop")));
        fishStation.setStoptime(rs.getString("stop_time"));
        fishStation.setSystem(Converter.toInteger(rs.getBigDecimal("system")));
        fishStation.setTrawlopening(Converter.toDouble(rs.getBigDecimal("trawl_opening")));
        fishStation.setTrawlopeningsd(Converter.toDouble(rs.getBigDecimal("trawl_std_opening")));
        fishStation.setTrawlquality(Converter.toStringDescriptionType(rs.getString("id_r_udplist_trawl_quality")));
        fishStation.setTripno(Converter.toInteger(rs.getBigDecimal("trip_no")));
        //fishStation.setVegetationcover(Converter.toStringDescriptionType(rs.getString(CLF_VEGETATIONCOVER)));
        //fishStation.setVisibility(Converter.toStringDescriptionType(rs.getString(CLF_VISIBILITY)));
        //fishStation.setWaterlevel(Converter.toDouble(rs.getBigDecimal(CLF_WATERLEVEL)));
        //fishStation.setWeather(Converter.toStringDescriptionType(rs.getString(CLF_WEATHER)));
        //fishStation.setWinddirection(Converter.toDouble(rs.getBigDecimal(CLF_WINDDIRECTION)));
        //fishStation.setWindspeed(Converter.toDouble(rs.getBigDecimal(CLF_WINDSPEED)));
        fishStation.setWirelength(Converter.toInteger(rs.getBigDecimal("wire_length")));
        

        return new FishStation(rs.getString("id"), fishStation);
    }


}

