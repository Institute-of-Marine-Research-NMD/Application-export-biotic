package no.imr.nmdapi.client.biotic.export.pojo;

/**
 *
 * @author Terry Hannant <a5119>
 */
public class UdpValue {

    private String propertyName;
    private String valueText;
    private String valueUDPListID;
    private double valueDouble;
    private long valueInteger;

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getValueText() {
        return valueText;
    }

    public void setValueText(String valueText) {
        this.valueText = valueText;
    }

    public String getValueUDPListID() {
        return valueUDPListID;
    }

    public void setValueUDPListID(String valueUDPListID) {
        this.valueUDPListID = valueUDPListID;
    }

    public double getValueDouble() {
        return valueDouble;
    }

    public void setValueDouble(double valueDouble) {
        this.valueDouble = valueDouble;
    }

    public long getValueInteger() {
        return valueInteger;
    }

    public void setValueInteger(long valueInteger) {
        this.valueInteger = valueInteger;
    }

}
