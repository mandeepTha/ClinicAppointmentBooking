package org.boostphysio.Model;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class Treatment {
    public String status = "Available";
    private String treatmentName;
    private int treatmentDuration;   // in Minutes
    private double treatmentCost;
    public String time;
    private Physiotherapist physiotherapist;
    public Patient patient;


    public Treatment(String treatmentName, int treatmentDuration, double treatmentCost, String time, Physiotherapist physiotherapist) {
        this.treatmentName = treatmentName;
        this.treatmentDuration = treatmentDuration;
        this.treatmentCost = treatmentCost;
        this.time = time;
        this.physiotherapist = physiotherapist;
    }

    public Treatment() {

    }


    public String getTreatmentName() {
        return treatmentName;
    }
    public String getTreatmentDuration() {
        return String.valueOf(treatmentDuration);
    }
    public double getTreatmentCost() {
        return treatmentCost;
    }
    public String getTime() {
        return time;
    }
   /* public String getFormattedDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE d'th' MMMM yyyy, HH:mm");
        return dateTime.format(formatter);
    }*/
    public Physiotherapist getPhysiotherapist() {
        return physiotherapist;
    }
    /*
    public LocalDate getDateTime() {
        return dateTime;
    }*/

    @Override
    public String toString() {
        return "Treatment: " + treatmentName + "\n" +
                "Duration and Cost: "+ treatmentDuration +"min"+ "£"+treatmentCost + "\n" +
                "Date & Time: " + getTime() + "\n" +
                "Physiotherapist: " + physiotherapist.getPhysiotherapistName();
    }

}
