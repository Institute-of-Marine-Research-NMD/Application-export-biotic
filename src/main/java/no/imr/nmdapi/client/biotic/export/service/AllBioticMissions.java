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
@Service("getAllBioticMissions")
public class AllBioticMissions {
    
    private static final Logger LOG = LoggerFactory.getLogger(AllBioticMissions.class);
    
    
    @Autowired
    BioticMissionDAO bioticMissionDAO;
    
    
    
    public List<String> getAllUpdatedBioticMissions() {
        ArrayList<String> result = new ArrayList<String>();

        List<Mission> missionList = bioticMissionDAO.getAllBioticMissions();
        for (Mission mission : missionList) {
                         result.add(mission.getId());                
        }
       return result;
    }
    
}
