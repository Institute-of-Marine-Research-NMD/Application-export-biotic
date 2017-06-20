package no.imr.nmdapi.client.biotic.export.pojo;

import no.imr.nmdapi.generic.nmdbiotic.domain.v1_4.PreyType;

/**
 *
 * @author Terry Hannant <a5119>
 */
public class Prey {

    private String id;
    private String individualID;
    private String taxaID;
    private String weighUnittID;

    public String getWeighUnittID() {
        return weighUnittID;
    }

    public void setWeighUnittID(String weighUnittID) {
        this.weighUnittID = weighUnittID;
    }

    public void setindividualID(String individualID) {
        this.individualID = individualID;
    }

    public String getIndividualID() {
        return individualID;
    }

    private PreyType type;

    public PreyType getType() {
        return type;
    }

    public void setType(PreyType type) {
        this.type = type;
    }

    public Prey(String id) {
        this.id = id;
    }

    public String getTaxaID() {
        return taxaID;
    }

    public void setTaxaID(String taxaID) {
        this.taxaID = taxaID;
    }

    public String getId() {
        return id;
    }

}
