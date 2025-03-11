package org.boostphysio;

import org.boostphysio.Model.Appointment;
import org.boostphysio.Model.Patient;
import org.boostphysio.Model.Physiotherapist;
import org.boostphysio.Model.Treatment;
import org.boostphysio.Model.Clinic;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
;

public class Main {
    public static void main(String[] args) {
        Clinic clinic = new Clinic();
        // Creates New Patient
        Patient patient = new Patient(123, "Sarah Willims", "Watford", "07573264859" );
        // Creates New Physiotherapist
        Physiotherapist physiotherapist = new Physiotherapist(2345, "Dr. Steven Gordan", Arrays.asList("Rehabilitation",""));
        //Creates new Treatment
        Treatment treatment = new Treatment("Leg Massage",45, 50.5);
        // Books an Appointment
        Appointment appointment = new Appointment(LocalDateTime.of(2025, 1, 10,1,0),"",patient,physiotherapist, treatment);

        //Store Data in the Clinic Sys
        clinic.addPatient(patient);
        clinic.addPhysiotherapist(physiotherapist);
        clinic.bookAppointment(appointment);
        clinic.cancelAppointment(appointment);
        appointment.Confirm();
        //generate a clinic report
        clinic.GenerateReport();

    }


}
