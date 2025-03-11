package org.boostphysio.Model;

import java.time.LocalDateTime;

public class Appointment {
    private Physiotherapist physiotherapist;
    private Patient patient;
    private Treatment treatment;
    private LocalDateTime date;
    private String status;
    private String patientName;
    private String physiotherapistName;

    public Appointment(LocalDateTime date, String status, Patient patient, Physiotherapist physiotherapist, Treatment treatment) {
        this.date = date;
        this.status = "Booked";
        this.patient = patient;
        this.physiotherapist = physiotherapist;
        this.treatment = treatment;
    }

   public void Confirm(){
        status = "Confirmed";
        System.out.println("Appointment Confirmed for " + patient.getPatientFullName() + " With " + physiotherapist.getPhysiotherapistName());
   }

   public void Cancel(){

        this.status = "Cancelled";
        System.out.println("Appointment Canceled for " + patient.getPatientFullName() + " with " + physiotherapist.getPhysiotherapistName());
   }

    public Patient getPatient() {
        return patient;
    }

    public String toString(){
        return "Appointment: " + treatment.getTreatmentName()+ " with " + physiotherapist.getPhysiotherapistName()+" for " + patient.getPatientFullName()+" on "+date+ " [" + status + "]";
    }

    public String getStatus() {

        return status;
    }
}
