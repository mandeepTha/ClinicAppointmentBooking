package org.boostphysio.Model;
import java.util.Date;

public class Treatment {

    private String treatmentName;
    public int duration;
    private Physiotherapist physiotherapist;


    public Treatment(String treatmentName, int duration, Physiotherapist physiotherapist) {
        this.treatmentName = treatmentName;
        this.duration = duration;
        this.physiotherapist = physiotherapist;
    }



    public String getTreatmentName() {
        return treatmentName;
    }


    public Physiotherapist getPhysiotherapist()
    {
        return physiotherapist;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return "Treatment: " + treatmentName + "\n" +
                "Duration " + duration+ "\n" +
                "Physiotherapist: " + physiotherapist.getName();
    }

}
