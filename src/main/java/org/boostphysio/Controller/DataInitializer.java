package org.boostphysio.Controller;

import org.boostphysio.Model.Appointment;
import org.boostphysio.Model.Patient;
import org.boostphysio.Model.Physiotherapist;
import org.boostphysio.Model.Treatment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataInitializer {

    private static final java.util.List<Patient> patients = new ArrayList<>();
    private static final List<Physiotherapist> physiotherapists = new ArrayList<>();
    private static final List<Treatment> treatments = new ArrayList<>();
    private static final List<Appointment> appointments = new ArrayList<>();
    private static java.util.List<Treatment> List;


    public static List<Patient> initializePatients() {
        List<Patient> patients = new ArrayList<>( Arrays.asList(
                new Patient(1, "Jason Becker", "40 Aviation Avenue", "07521259532"),
                new Patient(2, "Jeff Beck", "40 Aviation Avenue", "07521259514"),
                new Patient(3, "Joe Satriani", "40 Aviation Avenue", "07521259545"),
                new Patient(4, "Jimmy Page", "40 Aviation Avenue", "07521259576"),
                new Patient(5, "Jimi Hendrix", "40 Aviation Avenue", "07521259587"),
                new Patient(6, "David Gilmour", "40 Aviation Avenue", "07521259589"),
                new Patient(7, "James Hetfield", "40 Aviation Avenue", "07521259508"),
                new Patient(8, "Chuck Berry", "40 Aviation Avenue", "07521259565"),
                new Patient(9, "Eric Clapton", "40 Aviation Avenue", "07521259595"),
                new Patient(10, "Robert Johnson", "40 Aviation Avenue", "07521259503"),
                new Patient(11, "John Petrucci", "39 Aviation Avenue", "07521259520"),
                new Patient(12, "Guthrie Govan", "20 Aviation Avenue", "07521259551")

        ));
        return patients;
    }

    public static List<Physiotherapist> initializePhysiotherapists() {
        List<Physiotherapist> physios = new ArrayList<>();

        physios.add(new Physiotherapist(101, "Dr. Steven Gordan", "Clinic Street", "07521250001", Arrays.asList("Orthopaedic Physiotherapy", "Chronic joint pain therapy")));
        physios.add(new Physiotherapist(201, "Dr. Stephan Ramsey", "Clinic Street", "07521250002", Arrays.asList("Neurological Physiotherapy", "Spinal cord injuries therapy")));
        physios.add(new Physiotherapist(301, "Dr. Nishant James", "Clinic Street", "07521250003", Arrays.asList("Cardio respiratory Physiotherapy", "Asthma Exercise")));
        physios.add(new Physiotherapist(401, "Dr. Diana Bolton", "Clinic Street", "07521250004", Arrays.asList("Sports Physiotherapy", "Specialised exercises")));

        return physios;
    }

    public static List<Appointment> initializeAppointments() {
        List<Appointment> appointments = new ArrayList<>();
        List<Physiotherapist> physios = initializePhysiotherapists();

        LocalDateTime baseDate = LocalDateTime.of(2025, 5, 1, 10, 0);
        int intervalMinutes = 60;

        for (int week = 0; week < 4; week++) {
            for (Physiotherapist physio : physios) {
                for (String expertise : physio.getExpertise()) {
                    LocalDateTime appointmentDate = baseDate.plusWeeks(week);
                    Treatment treatment = new Treatment(expertise, intervalMinutes, physio);
                    Appointment appointment = new Appointment(physio, treatment, appointmentDate);
                    appointment.setDateTime(appointmentDate);
                    appointment.setStatus("Available");
                    appointments.add(appointment);
                    physio.addTimetable(treatment);
                    baseDate = baseDate.plusHours(1); // next slot
                }
            }
        }
        return appointments;
    }

}



