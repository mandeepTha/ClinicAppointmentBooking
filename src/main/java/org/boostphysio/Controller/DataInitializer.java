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


    public static List<Patient> initializePatients() {
        List<Patient> patients = new ArrayList<>( Arrays.asList(
                new Patient(1, "Jason Becker", "40 Aviation Avenue", "07521259532"),
                new Patient(2, "Jeff Beck", "41 Aviation Avenue", "07521259514"),
                new Patient(3, "Joe Satriani", "42 Aviation Avenue", "07521259545"),
                new Patient(4, "Jimmy Page", "43 Aviation Avenue", "07521259576"),
                new Patient(5, "Jimi Hendrix", "44 Aviation Avenue", "07521259587"),
                new Patient(6, "David Gilmour", "45 Aviation Avenue", "07521259589"),
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

        physios.add(new Physiotherapist(101, "Dr. Steven Gordan", "1 Rose Street", "07521250001", Arrays.asList("Orthopaedic Physiotherapy", "Chronic joint pain therapy")));
        physios.add(new Physiotherapist(201, "Dr. Stephan Ramsey", "Clinic Street", "07521250002", Arrays.asList("Neurological Physiotherapy", "Spinal cord injuries therapy")));
        physios.add(new Physiotherapist(301, "Dr. Nishant James", "22 Rticroft Close", "07521250003", Arrays.asList("Cardio respiratory Physiotherapy", "Asthma Exercises")));
        physios.add(new Physiotherapist(401, "Dr. Diana Bolton", "19 Lincoln Gardens", "07521250004", Arrays.asList("Sports Physiotherapy", "Specialised exercises")));

        return physios;
    }

    public static List<Appointment> initializeAppointments() {
        List<Appointment> appointments = new ArrayList<>();
        List<Physiotherapist> physios = initializePhysiotherapists();

        LocalDateTime baseDate = LocalDateTime.of(2025, 5, 1, 9, 0); // Start from 9:00 AM
        int intervalMinutes = 60;

        for (int week = 0; week < 4; week++) {
            LocalDateTime currentDate = baseDate.plusWeeks(week);

            for (Physiotherapist physio : physios) {
                LocalDateTime appointmentTime = currentDate;

                for (String expertise : physio.getExpertise()) {

                    // Only allow appointments between 9 AM to 5 PM
                    if (appointmentTime.getHour() >= 17) {
                        appointmentTime = appointmentTime.plusDays(1).withHour(9); // next day at 9 AM
                        if (appointmentTime.getDayOfWeek().getValue() >= 6) {
                            // Skip Saturday (6) and Sunday (7)
                            appointmentTime = appointmentTime.plusDays(8 - appointmentTime.getDayOfWeek().getValue()).withHour(9);
                        }
                    }

                    Treatment treatment = new Treatment(expertise, intervalMinutes, physio);
                    Appointment appointment = new Appointment(physio, treatment, appointmentTime);
                    appointment.setStatus("Available");
                    appointments.add(appointment);
                    physio.addTimetable(treatment);

                    appointmentTime = appointmentTime.plusHours(1); // Next slot
                }
            }
        }

        LocalDateTime overlappingTime = LocalDateTime.of(2025, 5, 15, 10, 0); // same time for both

        Physiotherapist physio1 = physios.get(0); // e.g., Dr. Steven Gordan
        Physiotherapist physio2 = physios.get(1); // e.g., Dr. Stephan Ramsey

        Treatment treatment1 = new Treatment("Orthopaedic Physiotherapy", 60, physio1);
        Treatment treatment2 = new Treatment("Neurological Physiotherapy", 60, physio2);

        Appointment appt1 = new Appointment(physio1, treatment1, overlappingTime);
        Appointment appt2 = new Appointment(physio2, treatment2, overlappingTime);

        appt1.setStatus("Available");
        appt2.setStatus("Available");

        appointments.add(appt1);
        appointments.add(appt2);
        return appointments;

    }

}



