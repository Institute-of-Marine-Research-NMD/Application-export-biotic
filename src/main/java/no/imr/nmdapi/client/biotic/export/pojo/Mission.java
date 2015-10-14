package no.imr.nmdapi.client.biotic.export.pojo;

/**
 *
 * @author Terry Hannant <a5119>
 */
public class Mission {

    String id;
    Integer  missionNumber;
    String startTime;
    String stopTime;
    String startYear;
    String purpose;
    String missionTypeCode;
    String missionType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getMissionNumber() {
        return missionNumber;
    }

    public void setMissionNumber(Integer missionNumber) {
        this.missionNumber = missionNumber;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStopTime() {
        return stopTime;
    }

    public void setStopTime(String stopTime) {
        this.stopTime = stopTime;
    }

    public String getStartYear() {
        return startYear;
    }

    public void setStartYear(String startYear) {
        this.startYear = startYear;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getMissionTypeCode() {
        return missionTypeCode;
    }

    public void setMissionTypeCode(String missionTypeCode) {
        this.missionTypeCode = missionTypeCode;
    }

    public String getMissionType() {
        return missionType;
    }

    public void setMissionType(String missionType) {
        this.missionType = missionType;
    }

    
    
}
