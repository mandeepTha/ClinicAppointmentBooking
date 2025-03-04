package org.boostphysio.Model;

import java.util.List;

public class Physiotherapist {
    private int id;
    private String PhysiotherapistName;
    private List<String> expertise;

    public Physiotherapist(int id, String physiotherapistName, List<String> expertise) {
        this.id = id;
        this.PhysiotherapistName = physiotherapistName;
        this.expertise = expertise;
    }
    public String getPhysiotherapistName() {
        return PhysiotherapistName;
    }

    public List<String> getExpertise() {
        return expertise;
    }
}
