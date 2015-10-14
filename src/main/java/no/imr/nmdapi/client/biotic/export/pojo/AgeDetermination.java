package no.imr.nmdapi.client.biotic.export.pojo;


import no.imr.nmdapi.generic.nmdbiotic.domain.v1.AgeDeterminationType;

/**
 *
 * @author Terry Hannant <a5119>
 */
public class AgeDetermination {

   private String id;
 
   private AgeDeterminationType type;

    public AgeDeterminationType getType() {
        return type;
    }

    public void setType(AgeDeterminationType type) {
        this.type = type;
    }

    public AgeDetermination(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    
   
   
    
}
