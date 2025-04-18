package org.boostphysio.Model;

import java.util.ArrayList;
import java.util.List;

public class Clinic {


    private List<Patient> patients = new ArrayList<>();
    private List<Appointment> appointments = new ArrayList<>();
    private List<Physiotherapist> physiotherapists = new ArrayList<>();

        // Adds New Patient
    public void addPatient(Patient patient) {
        patients.add(patient);
        System.out.println("Added Patient: "+patient.getName());
    }

        //Adds New Physiotherapist
    public void addPhysiotherapist(Physiotherapist physiotherapist) {
        physiotherapists.add(physiotherapist);
        System.out.println("Added Physiotherapist: "+physiotherapist.getName());

    }

        //Books an Appointment
   public void bookAppointment(Appointment appointment) {
        appointments.add(appointment);
        System.out.println("Appointment Booked for: "+appointment);
   }
   public List<Appointment> getAppointments() {
        return appointments;
   }
    public List<Patient> getPatients() {
        return patients;
    }
    public List<Physiotherapist> getPhysiotherapists() {
        return physiotherapists;
    }

        //
    public void GenerateReport(){
        System.out.println("\n Appointment Report");
        for (Appointment appointment : appointments) {
            System.out.println(appointment);
        }
    }
}
