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
        return Arrays.asList(
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

        );
    }

    public static List<Physiotherapist> initializePhysiotherapists() {
        List<Physiotherapist> physios = new ArrayList<>();

        physios.add(new Physiotherapist(101, "Dr. Steven Gordan", "Clinic Street", "07521250001", Arrays.asList("Orthopaedic Physiotherapy", "Chronic joint pain")));
        physios.add(new Physiotherapist(201, "Dr. Stephan Ramsey", "Clinic Street", "07521250002", Arrays.asList("Neurological Physiotherapy", "Spinal cord injuries")));
        physios.add(new Physiotherapist(301, "Dr. Nishant James", "Clinic Street", "07521250003", Arrays.asList("Cardiorespiratory Physiotherapy", "Asthma")));
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


        public static void initializeData() {
            patients.add(new Patient(1, "Jason Becker", "40 Aviation Avenue", "07521259532"));
            patients.add(new Patient(2, "Jeff Beck", "41 Aviation Avenue", "07521259514"));
            patients.add(new Patient(3, "Joe Satriani", "42 Aviation Avenue", "07521259545"));
            patients.add(new Patient(4, "Jimmy Page", "43 Aviation Avenue", "07521259576"));
            patients.add(new Patient(5, "Jimi Hendrix", "44 Aviation Avenue", "07521259587"));
            patients.add(new Patient(6, "David Gilmour", "30 Aviation Avenue", "07521259589"));
            patients.add(new Patient(7, "James Hetfield", "33 Aviation Avenue", "07521259508"));
            patients.add(new Patient(8, "Chuck Berry", " 36 Aviation Avenue", "07521259565"));
            patients.add(new Patient(9, "Eric Clapton", "37 Aviation Avenue", "07521259595"));
            patients.add(new Patient(10, "Robert Johnson", "38 Aviation Avenue", "07521259503"));
            patients.add(new Patient(11, "John Petrucci", "39 Aviation Avenue", "07521259520"));
            patients.add(new Patient(12, "Guthrie Govan", "20 Aviation Avenue", "07521259551"));

            Physiotherapist p1 = new Physiotherapist(101, "Dr. Steven Gordan", "1 Rose Street", "07541236585", Arrays.asList("Orthopaedic Physiotherapy", "Chronic joint pain"));
            Physiotherapist p2 = new Physiotherapist(201, "Dr. Stephan Ramsey", "33 Cavendish Avenue", "07541523658", Arrays.asList("Neurological Physiotherapy", "Spinal cord injuries"));
            Physiotherapist p3 = new Physiotherapist(301, "Dr. Nishant James", "22 Rticroft Close", "07452315985", Arrays.asList("Cardiorespiratory Physiotherapy", "Asthma"));
            Physiotherapist p4 = new Physiotherapist(401, "Dr. Diana Bolton", "19 Lincoln Gardens", "07523145697", Arrays.asList("Sports Physiotherapy", "Specialised exercises"));

            physiotherapists.add(p1);
            physiotherapists.add(p2);
            physiotherapists.add(p3);
            physiotherapists.add(p4);



            Treatment Ortho = new Treatment("Orthopaedic Physiotherapy", 50, p1);
            Treatment JointPain = new Treatment("Chronic Joint Pain Treatment", 35, p1);
            Treatment Neuro = new Treatment("Neurological Physiotherapy", 45, p2);
            Treatment Spinal = new Treatment("Spinal Cord Injuries Treatment", 35, p2);
            Treatment Cardio = new Treatment("Cardiorespiratory Physiotherapy", 60, p3);
            Treatment Asthma = new Treatment("Asthma Exercises", 30, p3);
            Treatment Physio = new Treatment("Sports Physiotherapy", 40, p4);
            Treatment Special = new Treatment("Specialized Exercise Session", 60, p4);

            LocalDateTime startDateTime = LocalDateTime.of(2025, 5, 1, 10, 0);

            for (int week = 0; week < 4; week++) {
                LocalDateTime weekStart = startDateTime.plusWeeks(week);

                // Dr. Steven Gordan's schedule
                appointments.add(new Appointment(p1, Ortho, weekStart.withHour(10).withMinute(0))); // Thursday 10am
                appointments.add(new Appointment(p1, JointPain, weekStart.plusDays(6).withHour(11).withMinute(0))); // Wednesday 11am

                // Dr. Stephan Ramsey's schedule
                appointments.add(new Appointment(p2, Neuro, weekStart.plusDays(1).withHour(11).withMinute(0))); // Friday 11am
                appointments.add(new Appointment(p2, Spinal, weekStart.plusDays(5).withHour(10).withMinute(30))); // Tuesday 10:30am

                // Dr. Nishant James’s schedule
                appointments.add(new Appointment(p3, Cardio, weekStart.withHour(12).withMinute(30))); // Thursday 12:30am
                appointments.add(new Appointment(p3, Asthma, weekStart.plusDays(4).withHour(11).withMinute(0))); // Monday 11am

                // Dr. Diana Bolton
                appointments.add(new Appointment(p4, Physio, weekStart.plusDays(1).withHour(9).withMinute(30))); // Friday 9:30am
                appointments.add(new Appointment(p4, Special, weekStart.plusDays(4).withHour(11).withMinute(0))); //Monday 11am
            }
        }
}


