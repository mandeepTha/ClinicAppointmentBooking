package org.boostphysio.Model;

import java.time.LocalDateTime;

public class Appointment {
    private Physiotherapist physiotherapist;
    private Patient patient;
    private Treatment treatment;
    private LocalDateTime dateTime;
    private String status = "Available";

    public Appointment(Physiotherapist physiotherapist, Treatment treatment, LocalDateTime dateTime) {
        this.physiotherapist = physiotherapist;
        this.treatment = treatment;
        this.dateTime = dateTime;
        this.status = "Available";
    }

    public void bookAppointment(Patient patient) {
            this.patient = patient;
            this.status = "Booked";

    }

    public void cancelAppointment() {
        if(status.equals("Booked")) {
            this.status = "Cancelled";
        }
    }
    public void attendedAppointment() {
        if(status.equals("Booked")) {
            this.status = "Attended";
        }
    }


    public String getStatus() {
        return status;
    }
    public LocalDateTime getDateTime() {return dateTime;}
    public Patient getPatient() {
        return this.patient;
    }
    public Physiotherapist getPhysiotherapist() {
        return physiotherapist;
    }
    public Treatment getTreatment() {
        return treatment;
    }

    @Override
    public String toString() {
        return "Appointment: " + treatment.getTreatmentName()+
                " with " + physiotherapist.getName() +
                " on " + dateTime +
                ", Status " + status;
    }

    public void setStatus(String available) {
        this.status = available;
    }

    public void setDateTime(LocalDateTime appointmentDate) {
        this.dateTime = appointmentDate;
    }
}
