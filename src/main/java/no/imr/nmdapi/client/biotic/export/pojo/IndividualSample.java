package no.imr.nmdapi.client.biotic.export.pojo;

import no.imr.nmdapi.generic.nmdbiotic.domain.v1.IndividualType;

/**
 *
 * @author Terry Hannant <a5119>
 */
public class IndividualSample {

   private String id;
   private IndividualType type;

    public IndividualType getType() {
        return type;
    }

    public void setType(IndividualType type) {
        this.type = type;
    }

    public IndividualSample(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
   
   
    
}
