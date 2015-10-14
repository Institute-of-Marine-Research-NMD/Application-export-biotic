package no.imr.nmdapi.client.biotic.export.pojo;

import no.imr.nmdapi.generic.nmdbiotic.domain.v1.FishStationType;

/**
 *
 * @author Terry Hannant <a5119>
 */
public class FishStation {
  
    private String  id;
    private FishStationType type;

    
    public FishStation(String id, FishStationType type) {
        this.id = id;
        this.type = type;
    }
    
       
    public String getId() {
        return id;
    }
    public FishStationType getType() {
        return type;
    }

    
}
