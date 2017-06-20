package no.imr.nmdapi.client.biotic.export.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import no.imr.nmd.commons.dataset.jaxb.DataTypeEnum;
import no.imr.nmd.commons.dataset.jaxb.DatasetType;
import no.imr.nmdapi.client.biotic.export.dao.BioticMissionDAO;
import no.imr.nmdapi.client.biotic.export.dao.PlatformDAO;
import no.imr.nmdapi.dao.file.NMDDatasetDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import no.imr.nmdapi.client.biotic.export.pojo.Mission;
import no.imr.nmdapi.lib.nmdapipathgenerator.PathGenerator;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 *
 * @author Terry Hannant <a5119>
 */
@Service("getAllUpdatedBioticMissions")
public class AllUpdatedBioticMissions {

    private static final Logger LOG = LoggerFactory.getLogger(AllUpdatedBioticMissions.class);

    @Autowired
    @Qualifier("configuration")
    private org.apache.commons.configuration.Configuration configuration;

    @Autowired
    BioticMissionDAO bioticMissionDAO;

    @Autowired
    PlatformDAO platformDAO;

    @Autowired
    NMDDatasetDao nmdDatasetDao;

    private PathGenerator pathGenerator = new PathGenerator();

    public List<String> getAllUpdatedBioticMissions() {
        ArrayList<String> result = new ArrayList<String>();

        List<Mission> missionList = bioticMissionDAO.getAllBioticMissions();
        for (Mission mission : missionList) {
            String cruiseCode = bioticMissionDAO.getCruiseCode(mission.getId());

            if (cruiseCode == null) {
                cruiseCode = bioticMissionDAO.getDelivery(mission.getId());
            }

            Map<String, String> platformCodes = platformDAO.getCruisePlatformCodes(mission.getId());
            String platformPath = pathGenerator.createPlatformURICode(platformCodes);

            File dataFile = pathGenerator.generatePath(configuration.getString("file.location"),
                    mission.getMissionType(), mission.getStartYearString(), platformPath, cruiseCode, "biotic");

            LOG.debug("Check dataset:" + mission.getId() + " " + dataFile.toString());

            if (dataFile.exists()) {
                DatasetType dataset = nmdDatasetDao.getDatasetByName(DataTypeEnum.BIOTIC, "data", mission.getMissionType(),
                        mission.getStartYearString(), platformPath, cruiseCode);
                if (dataset != null) {
                    Date lastDBUpdate = bioticMissionDAO.getMaxMissionLog(mission.getId());

                    if ((lastDBUpdate != null) && (lastDBUpdate.after(dataset.getUpdated().toGregorianCalendar().getTime()))) {
                        LOG.debug("Regen as DB date is later");
                        result.add(mission.getId());
                    } else {
                        int databaseStationCount = bioticMissionDAO.getFishStationCount(mission.getId());
                        int fileStationCount = countOccurences(dataFile, "<fishstation");
                        if (databaseStationCount != fileStationCount) {
                            LOG.debug("Regen as file/db station count do not match " + mission.getId() + "  file count:" + fileStationCount + " db count " + databaseStationCount);
                            result.add(mission.getId());

                        }
                        //LOG.debug("No updated needed for:" + mission.getId()+" file count:"+fileStationCount+" db count "+databaseStationCount);

                    }

                } else {
                    LOG.debug("Regen as dataset  not present");
                    result.add(mission.getId());
                }

            } else {
                LOG.debug("Regen as file not present");

                result.add(mission.getId());

            }
        }

        return result;
    }

    private int countOccurences(File dataFile, String match) {
        int result = 0;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(dataFile));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(match)) {
                    result++;
                }
            }
        } catch (FileNotFoundException ex) {
            LOG.debug("Can not open file: " + dataFile.getAbsolutePath());
        } catch (IOException ex) {
            LOG.debug("Can not read  file: " + dataFile.getAbsolutePath());
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException ex) {
                LOG.debug("Can not close file: " + dataFile.getAbsolutePath());
            }
        }

        return result;
    }

}
