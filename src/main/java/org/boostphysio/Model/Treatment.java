package org.boostphysio.Model;
import java.util.Date;

public class Treatment {
    public String status = "Available";
    private String treatmentName;
    public Date time;
    private Physiotherapist physiotherapist;
    public Patient patient;


    public Treatment(String treatmentName, Date time, Physiotherapist physiotherapist) {
        this.treatmentName = treatmentName;
        this.time = time;
        this.physiotherapist = physiotherapist;
    }

    public Treatment() {

    }


    public String getTreatmentName() {
        return treatmentName;
    }

   /* public String getFormattedDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE d'th' MMMM yyyy, HH:mm");
        return dateTime.format(formatter);
    }*/
    public Physiotherapist getPhysiotherapist() {
        return physiotherapist;
    }

    public Date getTime() {
            return time;
    }
    /*
    public LocalDate getDateTime() {
        return dateTime;
    }*/

    @Override
    public String toString() {
        return "Treatment: " + treatmentName + "\n" +
                "Date & Time: " + getTime()+ "\n" +
                "Physiotherapist: " + physiotherapist.getPhysiotherapistName();
    }

}
