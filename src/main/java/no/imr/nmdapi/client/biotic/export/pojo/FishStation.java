package no.imr.nmdapi.client.biotic.export.pojo;

import no.imr.nmdapi.generic.nmdbiotic.domain.v1_4.FishstationType;

/**
 *
 * @author Terry Hannant <a5119>
 */
public class FishStation {

    private String id;
    private FishstationType type;

    public FishStation(String id, FishstationType type) {
        this.id = id;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public FishstationType getType() {
        return type;
    }

}
