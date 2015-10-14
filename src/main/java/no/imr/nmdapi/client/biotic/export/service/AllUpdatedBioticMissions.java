package no.imr.nmdapi.client.biotic.export.service;

import java.io.File;
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
                    mission.getMissionType(), mission.getStartYear(), platformPath, cruiseCode, "biotic");
            if (dataFile.exists()) {
                DatasetType dataset = nmdDatasetDao.getDatasetByName(DataTypeEnum.BIOTIC, "data", mission.getMissionType(),
                        mission.getStartYear(), platformPath, cruiseCode);
                if (dataset != null) {
                    Date lastDBUpdate = bioticMissionDAO.getMaxMissionLog(mission.getId());
                    if (lastDBUpdate.after(dataset.getUpdated().toGregorianCalendar().getTime())) {
                        result.add(mission.getId());
                    } else {
                        LOG.debug("No updated needed for:" + mission.getId());
                    }
                    
                } else {
                    result.add(mission.getId());
                }
            } else {
                result.add(mission.getId());                
                
            }
        }
        
/*        for (int i = result.size() - 1; i > 2; i--) {
            result.remove(i);
        }*/
        
        return result;
    }
    
}
