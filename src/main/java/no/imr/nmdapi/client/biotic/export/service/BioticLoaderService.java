package no.imr.nmdapi.client.biotic.export.service;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import no.imr.messaging.exception.ProcessingException;
import no.imr.nmd.commons.dataset.jaxb.DataTypeEnum;
import no.imr.nmd.commons.dataset.jaxb.QualityEnum;
import no.imr.nmdapi.client.biotic.export.BioticGenerator;
import no.imr.nmdapi.client.biotic.export.dao.BioticMissionDAO;
import no.imr.nmdapi.client.biotic.export.dao.PlatformDAO;
import no.imr.nmdapi.generic.nmdbiotic.domain.v1.MissionsType;
import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import no.imr.nmdapi.client.biotic.export.pojo.Mission;
import no.imr.nmdapi.exceptions.CantWriteFileException;
import no.imr.nmdapi.generic.nmdbiotic.domain.v1.MissionType;
import no.imr.nmdapi.lib.nmdapipathgenerator.PathGenerator;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 *
 * @author Terry Hannant <a5119>
 */
@Service("bioticLoaderService")
public class BioticLoaderService {

    private static final Logger LOG = LoggerFactory.getLogger(BioticLoaderService.class);
 
    private static final String DATASET_CONTAINER_DELIMITER = "/";
        
    @Autowired
    @Qualifier("configuration")
    private  org.apache.commons.configuration.Configuration configuration;
    
    
    @Autowired
    BioticMissionDAO bioticMissionDAO;

    @Autowired
    PlatformDAO platformDAO;
    
    @Autowired
    BioticGenerator bioticGenerator;
    
    @Autowired
    Marshaller marshaller;
    
    private  PathGenerator pathGenerator = new PathGenerator();
    
    
    public void generatorBioticToFile(Exchange ex) {
        
        String missionID=ex.getIn().getBody(String.class);
        String delivery;
        LOG.debug("Processsing biotic for  mission :"+missionID);
        
        Mission mission =  bioticMissionDAO.getMission(missionID);
        String cruiseCode = bioticMissionDAO.getCruiseCode(missionID); 

        MissionType bioticMission = bioticGenerator.generate(mission,cruiseCode);

        if ( (cruiseCode == null )  || (cruiseCode.trim().length()==0 ) )  {
          delivery = bioticMissionDAO.getDelivery(missionID);
        } else {
           delivery = cruiseCode;
        }
        
        Map<String, String> platformCodes = platformDAO.getCruisePlatformCodes(missionID);
        String platformPath = pathGenerator.createPlatformURICode(platformCodes);
        
        File destinationFile = pathGenerator.generatePath(configuration.getString("file.location"), bioticMission.getMissiontype(),
                bioticMission.getYear(), platformPath, delivery, "biotic");
       
        writeToFile(bioticMission,destinationFile,delivery);
        
        
        
        ex.getOut().setHeader("imr:datatype", DataTypeEnum.BIOTIC.toString());
        ex.getOut().setHeader("imr:datasetname", "data");
        ex.getOut().setHeader("imr:owner", "imr");
        ex.getOut().setHeader("imr:read", "unrestricted");
        ex.getOut().setHeader("imr:write", "SG-BIOTIC-WRITE");
        ex.getOut().setHeader("imr:qualityassured", QualityEnum.NONE.toString());
        ex.getOut().setHeader("imr:updated", getXMLGregorianCalendar().toString());
        ex.getOut().setHeader("imr:description", "");
        ex.getOut().setHeader("imr:datasetscontainer", bioticMission.getMissiontype().
                concat(DATASET_CONTAINER_DELIMITER).concat(bioticMission.getYear()).
                concat(DATASET_CONTAINER_DELIMITER).concat(platformPath).
                concat(DATASET_CONTAINER_DELIMITER).concat(delivery));
        ex.getOut().setBody("Updated cruise: "+delivery);
        
        LOG.debug("Generated btiotic for cruise:"+delivery);
        
    } 

    private void writeToFile(MissionType bioticMission,File destinationFile,String tempFilename) {
        MissionsType rootElement = new MissionsType();
        rootElement.getMission().add(bioticMission);
                 
        File tempFile = new File(FileUtils.getTempDirectory().getAbsolutePath().concat(File.separator).concat(tempFilename));
        
        try {
            LOG.error("Marshall file :"+tempFile);
            marshaller.marshal(rootElement,tempFile);
            FileUtils.copyFile(tempFile, destinationFile);
            tempFile.delete();
            
        } catch (JAXBException jex) {
            LOG.error("Unable to marshall file for biotic dataset", jex);
            throw new CantWriteFileException("Unable to marshall file for biotic dataset", tempFile, jex);
        } catch (IOException ex) {
             LOG.error("Unable to copy biotic file to destination", ex);
            throw new CantWriteFileException("Unable to copy biotic file to destination",destinationFile, ex);
        }
        
    }
    
     private XMLGregorianCalendar getXMLGregorianCalendar() {
        try {
            GregorianCalendar c = new GregorianCalendar();
            c.setTime(Calendar.getInstance().getTime());
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
        } catch (DatatypeConfigurationException ex) {
            LOG.error("Unable to create xml gregorian calendar", ex);
            throw new ProcessingException("Unable to create xml calendar",ex);
        }
    }
            
    
    
    
}
