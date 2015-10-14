package no.imr.nmdapi.client.biotic.export.pojo;

import java.math.BigInteger;

/**
 *
 * @author Terry Hannant <a5119>
 */
public class Taxa {
    
    private String tsn;
    private BigInteger aphia;
    private String name;
    private String language;

    public String getTsn() {
        return tsn;
    }

    public void setTsn(String tsn) {
        this.tsn = tsn;
    }

    public BigInteger getAphia() {
        return aphia;
    }

    public void setAphia(BigInteger aphia) {
        this.aphia = aphia;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

     
    
    
    

}
