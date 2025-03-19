package org.boostphysio.Model;

public class Appointment extends Treatment {
    private Physiotherapist physiotherapist;
    private Patient patient;
    private Treatment treatment;
    private String status;

    public Appointment(Patient patient, Physiotherapist physiotherapist, Treatment treatment, String status) {
        super();

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


    public String toString(){
        return "Appointment Details:\n" +
                "Patient: " +patient.getPatientName()+ "(Phone number: "+ patient.getPatientPhoneNumber()+")\n"+
                "Physiotherapist: "+physiotherapist.getPhysiotherapistName()+ "(Expertise: "+physiotherapist.getExpertise()+")\n"+
                "Treatment: "+treatment.getTreatmentName()+ "\n" +
                "Date & Time: "+ treatment.getTime() ;

    }

}
