package org.boostphysio.Model;

public class Appointment {
    private Physiotherapist physiotherapist;
    private Patient patient;
    private Treatment treatment;
    private String status;

    public Appointment(Patient patient, Physiotherapist physiotherapist, Treatment treatment, String status) {
        this.patient = patient;
        this.physiotherapist = physiotherapist;
        this.treatment = treatment;
        this.status = "APPOINTMENT";
    }



    public String getStatus() {
        return status;
    }
    public Patient getPatient() {
        return patient;
    }
    public Physiotherapist getPhysiotherapist() {
        return physiotherapist;
    }
    public Treatment getTreatment() {
        return treatment;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "physiotherapist=" + physiotherapist +
                ", patient=" + patient +
                ", treatment=" + treatment +
                ", status='" + status + '\'' +
                '}';
    }

}
