package no.imr.nmdapi.client.biotic.export.pojo;

import no.imr.nmdapi.generic.nmdbiotic.domain.v1.CatchSampleType;

/**
 *
 * @author Terry Hannant <a5119>
 */
public class CatchSample {
    
    private String id;
    private String stockID;
    private String taxaID;
    private String groupID;
    private String ID;

    public String getStockID() {
        return stockID;
    }

    public void setStockID(String stockID) {
        this.stockID = stockID;
    }
    
    
    private CatchSampleType type;

    public CatchSample(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setTaxaID(String taxaID) {
        this.taxaID = taxaID;
    }

    public String getTaxaID() {
        return taxaID;
    }

    public void setType(CatchSampleType type) {
        this.type = type;
    }

    
    
    public CatchSampleType getType() {
        return type;
    }
    
    
    
            

}
