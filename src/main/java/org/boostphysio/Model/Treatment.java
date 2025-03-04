package org.boostphysio.Model;

public class Treatment {
    private String treatmentName;
    private int treatmentDuration;   // in Minutes
    private double treatmentCost;

    public Treatment(String treatmentName, int treatmentDuration, double treatmentCost) {
        this.treatmentName = treatmentName;
        this.treatmentDuration = treatmentDuration;
        this.treatmentCost = treatmentCost;
    }

    public String getTreatmentName() {
        return treatmentName;
    }

}
