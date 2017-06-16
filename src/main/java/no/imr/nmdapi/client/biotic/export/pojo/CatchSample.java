package no.imr.nmdapi.client.biotic.export.pojo;

import no.imr.nmdapi.generic.nmdbiotic.domain.v1_4.CatchsampleType;

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
    
    
    private CatchsampleType type;

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

    public void setType(CatchsampleType type) {
        this.type = type;
    }

    
    
    public CatchsampleType getType() {
        return type;
    }
    
    
    
            

}
