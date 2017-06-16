package no.imr.nmdapi.client.biotic.export.pojo;


import no.imr.nmdapi.generic.nmdbiotic.domain.v1_4.AgedeterminationType;

/**
 *
 * @author Terry Hannant <a5119>
 */
public class AgeDetermination {

   private String id;
 
   private AgedeterminationType type;

    public AgedeterminationType getType() {
        return type;
    }

    public void setType(AgedeterminationType type) {
        this.type = type;
    }

    public AgeDetermination(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    
   
   
    
}
