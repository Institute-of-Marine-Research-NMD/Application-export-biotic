package no.imr.nmdapi.client.biotic.export;


import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import no.imr.nmdapi.client.biotic.export.dao.AgeDeterminationDAO;
import no.imr.nmdapi.client.biotic.export.dao.CatchSampleDAO;
import no.imr.nmdapi.client.biotic.export.dao.FishStationDAO;
import no.imr.nmdapi.client.biotic.export.dao.PreyDAO;
import no.imr.nmdapi.client.biotic.export.dao.IndividualSampleDAO;
import no.imr.nmdapi.client.biotic.export.dao.PlatformDAO;
import no.imr.nmdapi.client.biotic.export.dao.StockDAO;
import no.imr.nmdapi.client.biotic.export.dao.TagDAO;
import no.imr.nmdapi.client.biotic.export.dao.TaxaDAO;
import no.imr.nmdapi.client.biotic.export.dao.UdpDAO;
import no.imr.nmdapi.client.biotic.export.pojo.AgeDetermination;
import no.imr.nmdapi.client.biotic.export.pojo.CatchSample;
import no.imr.nmdapi.client.biotic.export.pojo.FishStation;
import no.imr.nmdapi.client.biotic.export.pojo.IndividualSample;
import no.imr.nmdapi.client.biotic.export.pojo.Prey;

import no.imr.nmdapi.generic.nmdbiotic.domain.v1_4.AgedeterminationType;
import no.imr.nmdapi.generic.nmdbiotic.domain.v1_4.CatchsampleType;
import no.imr.nmdapi.generic.nmdbiotic.domain.v1_4.CopepodedevstageType;
import no.imr.nmdapi.generic.nmdbiotic.domain.v1_4.FishstationType;
import no.imr.nmdapi.generic.nmdbiotic.domain.v1_4.IndividualType;
import no.imr.nmdapi.generic.nmdbiotic.domain.v1_4.MissionType;
import no.imr.nmdapi.generic.nmdbiotic.domain.v1_4.PreyType;
import no.imr.nmdapi.generic.nmdbiotic.domain.v1_4.PreylengthType;
import no.imr.nmdapi.generic.nmdbiotic.domain.v1_4.StringDescriptionType;
import no.imr.nmdapi.generic.nmdbiotic.domain.v1_4.TagType;

import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import no.imr.nmdapi.client.biotic.export.pojo.Mission;
import no.imr.nmdapi.client.biotic.export.pojo.Taxa;
import no.imr.nmdapi.client.biotic.export.pojo.UdpValue;

/**
 *
 * @author Terry Hannant <a5119>
 */
public class BioticGenerator  {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(BioticGenerator.class);

    @Autowired
    PlatformDAO platformDAO;
    @Autowired
    FishStationDAO fishstationDAO;
    @Autowired
    CatchSampleDAO catchSampleDAO;
    @Autowired
    IndividualSampleDAO individualDAO;
    @Autowired
    AgeDeterminationDAO ageDeterminationDAO;

    @Autowired
    TagDAO tagDAO;

    @Autowired
    PreyDAO preyDAO;

    @Autowired
    TaxaDAO taxaDAO;
    @Autowired
    StockDAO stockDAO;
    @Autowired
    UdpDAO udpDAO;


    public MissionType generate(Mission  mission,String cruiseCode) {

        boolean missingTaxa = false;
        boolean missingTaxaName = false;
     
      
        MissionType biotic = new MissionType();
        biotic.setMissionnumber(BigInteger.valueOf(mission.getMissionNumber()));
        biotic.setMissiontype(mission.getMissionTypeCode());
        biotic.setMissiontypename(mission.getMissionType());
        biotic.setYear(BigInteger.valueOf(mission.getStartYear()));
        
        
        Map<String, String> platformCodes = platformDAO.getCruisePlatformCodes(mission.getId());
        
        //TODO check if this is still needed
        if (platformCodes.containsKey("Ship Name"))
         {
             //biotic.setPlatformName(platformCodes.get("Ship Name"));
         }
        
         if (platformCodes.containsKey("ITU Call Sign")){
             
             biotic.setCallsignal(platformCodes.get("ITU Call Sign"));
         }
         biotic.setPlatform(platformDAO.getPlatform(mission.getId()));
         
        if ((cruiseCode != null) && (cruiseCode.trim().length() > 0)) {
            biotic.setCruise(cruiseCode);
        } 
        
        biotic.setStartdate(mission.getStartTime());
        biotic.setStopdate(mission.getStopTime());
        biotic.setPurpose(mission.getPurpose());

        String stockCode;
        List<FishStation> fishStations = fishstationDAO.getFIshStations(mission.getId());
     
        for (FishStation fishStation : fishStations) {
            addFishStationUDPLookups(fishStation.getType());
            List<CatchSample> catchSamples = catchSampleDAO.getCatchSamples(fishStation.getId());
            for (CatchSample catchSample : catchSamples) {
                if (catchSample.getTaxaID() != null) {
                    List<Taxa> taxaNames = taxaDAO.getTaxaWithName(catchSample.getTaxaID());
                    //Mandatory checks
                    if (!taxaNames.isEmpty()) {
                        catchSample.getType().setAphia(taxaNames.get(0).getAphia());
                        
                        if (catchSample.getStockID()!=null){
                            //Hard coded in Editor
                            stockCode = stockDAO.getCode(catchSample.getStockID());
                            catchSample.getType().setSpecies(taxaNames.get(0).getTsn()+"."+stockCode);
                            catchSample.getType().setNoname(taxaNames.get(0).getName()+"'"+stockCode);
                  
                        } else { 
                        catchSample.getType().setSpecies(taxaNames.get(0).getTsn());
                        catchSample.getType().setNoname(taxaNames.get(0).getName());
                        }
                    } else {
                        //TODO Should we bale out at this point or keep generating
                        missingTaxaName = true;
                    }
                    addCatchSampleUDILookups(catchSample.getType());

                    
                    HashMap<String,BigInteger> individualSpecienno = new HashMap<String,BigInteger>();
                    List<IndividualSample> individualSamples = individualDAO.getIndividualSamples(catchSample.getId());
                    for (IndividualSample individualSample : individualSamples) {
                        addIndividualSampleUDPLookups(individualSample.getType());
                        addAgeDetermination(individualSample);
                        addTag(individualSample);
                        addIndividualUdapValues(individualSample);
                        
                        catchSample.getType().getIndividual().add(individualSample.getType());
                        individualSpecienno.put(individualSample.getId(),individualSample.getType().getSpecimenno());
                    }
                        
                        //Now deal with prey child records
                        List<Prey> preyList = preyDAO.getPreyBySample(catchSample.getId());
                        for (Prey prey : preyList) {
                            //prey.getType().setFishno(individualSample.getType().getSpecimenno());
                          prey.getType().setFishno(individualSpecienno.get(prey.getIndividualID()));
                            
                            if (prey.getTaxaID() != null){
                              prey.getType().setSpecies(taxaDAO.getTaxaTSN(prey.getTaxaID()));
                            }
                            if (prey.getWeighUnittID() != null) {
                                String weightRes = udpDAO.getName(prey.getWeighUnittID());
                                if ((weightRes != null)  && (!weightRes.isEmpty())){
                                   StringDescriptionType weigthResType = new StringDescriptionType();
                                   weigthResType.setValue(weightRes);
                                    prey.getType().setWeightresolution(new StringDescriptionType());
                                }
                             }
                            addPreyUDPLookups(prey.getType());
                            addPreyLengths(prey);
                            addPreyDevStage(prey);
                            catchSample.getType().getPrey().add(prey.getType());
                        }
                        
                    
                } else {
                    missingTaxa = true;
                }
                fishStation.getType().getCatchsample().add(catchSample.getType());
            }

            biotic.getFishstation().add(fishStation.getType());
        }

        //Check for missing manadatory data
        if ((missingTaxa) || (missingTaxaName)) {
           //TODO Should throw processing exception
        }

        return biotic;  
    }

    
    private void addFishStationUDPLookups(FishstationType fishStation) {
        fishStation.setStationtype(getUDPLookup(fishStation.getStationtype()));
        fishStation.setGearcondition(getUDPLookup(fishStation.getGearcondition()));
        fishStation.setTrawlquality(getUDPLookup(fishStation.getTrawlquality()));
        fishStation.setDataquality(getUDPLookup(fishStation.getDataquality()));
                
    }

    private void addCatchSampleUDILookups(CatchsampleType catchSample) {
        catchSample.setSampletype(getUDPLookup(catchSample.getSampletype()));
        catchSample.setConservation(getUDPLookup(catchSample.getConservation()));
        catchSample.setGroup(getUDPLookup(catchSample.getGroup()));
        catchSample.setLengthmeasurement(getUDPLookup(catchSample.getLengthmeasurement()));
        catchSample.setProducttype(getUDPLookup(catchSample.getProducttype()));
        catchSample.setParasite(getUDPLookup(catchSample.getParasite()));
        catchSample.setGenetics(getUDPLookup(catchSample.getGenetics()));
        
        catchSample.setSampleproducttype(getUDPLookup(catchSample.getSampleproducttype()));
        catchSample.setStomach(getUDPLookup(catchSample.getStomach()));
        catchSample.setAgingstructure(getUDPLookup(catchSample.getAgingstructure()));
    }

    private void addIndividualSampleUDPLookups(IndividualType individualSample) {
        individualSample.setLengthunit(getUDPLookup(individualSample.getLengthunit()));
        individualSample.setSex(getUDPLookup(individualSample.getSex()));
        individualSample.setStage(getUDPLookup(individualSample.getStage()));
        individualSample.setDigestion(getUDPLookup(individualSample.getDigestion()));
        individualSample.setFat(getUDPLookup(individualSample.getFat()));
        individualSample.setLiver(getUDPLookup(individualSample.getLiver()));
        individualSample.setStomachfillfield(getUDPLookup(individualSample.getStomachfillfield()));
        individualSample.setLiverparasite(getUDPLookup(individualSample.getLiverparasite()));
        individualSample.setProducttype(getUDPLookup(individualSample.getProducttype()));
        //special case to ensure no blank elements are added if blank udp values are found
//        if ( (individualSample.getProducttype()!= null) &&  (individualSample.getProducttype().getValue().isEmpty())) {
//            individualSample.setProducttype(null);
//       }
     }
    
     private void addPreyUDPLookups(PreyType prey) {
        prey.setDigestion(getUDPLookup(prey.getDigestion()));
        prey.setLengthmeasurement(getUDPLookup(prey.getLengthmeasurement()));
        prey.setInterval(getUDPLookup(prey.getInterval()));
        prey.setDevstage(getUDPLookup(prey.getDevstage()));
         
    }
    
    private void addAgeDeterminationUDPLookups(AgedeterminationType ageDetermination) {
        ageDetermination.setReadability(getUDPLookup(ageDetermination.getReadability()));
        ageDetermination.setOtolithcentre(getUDPLookup(ageDetermination.getOtolithcentre()));
        ageDetermination.setOtolithedge(getUDPLookup(ageDetermination.getOtolithedge()));
        ageDetermination.setOtolithtype(getUDPLookup(ageDetermination.getOtolithtype()));

    }
    

    private StringDescriptionType getUDPLookup(StringDescriptionType type) {
        String udpName;
        StringDescriptionType result = null;
        if (type != null) {
            udpName = udpDAO.getName(type.getValue());
            if (udpName.length()>0){
            type.setValue(udpName);
            result = type;
            }
        }
        return result;
    }

    private void addAgeDetermination(IndividualSample individualSample) {
        List<AgeDetermination> ageDeterminationList = ageDeterminationDAO.getAgeDetermination(individualSample.getId());
        BigInteger no=BigInteger.ONE;
        for (AgeDetermination ageDetermine:ageDeterminationList) {
            ageDetermine.getType().setNo(no);
            addAgeDeterminationUDPLookups(ageDetermine.getType());
            individualSample.getType().getAgedetermination().add(ageDetermine.getType());
            no.add(BigInteger.ONE);
        }
    }

    
     private void addTag(IndividualSample individualSample) {
        List<TagType> tagList = tagDAO.getTag(individualSample.getId());
        if (tagList.size() >0) {
            //TODO Can there ever be more than one tag?
            individualSample.getType().getTag().addAll(tagList);
            }
     }


    private void addPreyDevStage(Prey prey) {
        List<CopepodedevstageType> preyDevStageList = preyDAO.getCopepodedevstage(prey.getId());
            for (CopepodedevstageType preyDevStage:preyDevStageList){
                prey.getType().getCopepodedevstage().add(preyDevStage);
            }
    }

    
    private void addPreyLengths(Prey prey) {
            List<PreylengthType> preyLengthList = preyDAO.getPreyLength(prey.getId());
            for (PreylengthType preyLength:preyLengthList){
                prey.getType().getPreylength().add(preyLength);
            }
    }

    private void addIndividualUdapValues(IndividualSample individualSample) {
        List<UdpValue> udpValues = udpDAO.getUdpValues("nmdbiotic",individualSample.getId());
        for (UdpValue udpValue:udpValues) {
            
            StringDescriptionType stringDesc = new StringDescriptionType();
            stringDesc.setValue(udpValue.getValueUDPListID());
             
            //TODO Check if single property value can apear more than once
              switch (udpValue.getPropertyName()) {
                  case "sopp_hjerte":
                      individualSample.getType().setFungusheart(getUDPLookup(stringDesc));
                      break;
                  case "sopp_ytre":
                      individualSample.getType().setFungusouter(getUDPLookup(stringDesc));
                      break;
                  case "specialstage":
                       
                       individualSample.getType().setSpecialstage(getUDPLookup(stringDesc));
                      break;
                  case "gjellesvull":
                      individualSample.getType().setSwollengills(getUDPLookup(stringDesc));
                      break;
                  case "carapacewidth":
                      individualSample.getType().setCarapacewidth(BigDecimal.valueOf(udpValue.getValueDouble()));
                      break;
              }
        }
        
    }

}
