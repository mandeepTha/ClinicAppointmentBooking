package org.boostphysio;

import org.boostphysio.Model.Appointment;
import org.boostphysio.Model.Patient;
import org.boostphysio.Model.Physiotherapist;
import org.boostphysio.Model.Treatment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import java.util.*;



public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Patient> patients = new ArrayList<>();
    private static final List<Physiotherapist> physiotherapists = new ArrayList<>();
    private static final List<Treatment> treatments = new ArrayList<>();
    private static final List<Appointment> appointments = new ArrayList<>();
    private static java.util.List<Treatment> List;


    public static void main(String[] args) {
        initializeData();
        showMenu();


    }

    private static void initializeData() {



        //Adding Patients
        patients.add(new Patient(1, "Jason Becker", "40 Aviation Avenue", "07521259532"));
        patients.add(new Patient(2, "Jeff Beck", "40 Aviation Avenue", "07521259514"));
        patients.add(new Patient(3, "Joe Satriani", "40 Aviation Avenue", "07521259545"));
        patients.add(new Patient(4, "Jimmy Page", "40 Aviation Avenue", "07521259576"));
        patients.add(new Patient(5, "Jimi Hendrix", "40 Aviation Avenue", "07521259587"));
        patients.add(new Patient(6, "David Gilmour", "40 Aviation Avenue", "07521259589"));
        patients.add(new Patient(7, "James Hetfield", "40 Aviation Avenue", "07521259508"));
        patients.add(new Patient(8, "Chuck Berry", "40 Aviation Avenue", "07521259565"));
        patients.add(new Patient(9, "Eric Clapton", "40 Aviation Avenue", "07521259595"));
        patients.add(new Patient(10, "Robert Johnson", "40 Aviation Avenue", "07521259503"));
        patients.add(new Patient(11, "John Petrucci", "40 Aviation Avenue", "07521259520"));
        patients.add(new Patient(12, "Guthrie Govan", "40 Aviation Avenue", "07521259551"));

        //Adding Physiotherapist
        Physiotherapist p1 = new Physiotherapist(1, "Dr. Steven Gordan", "", Arrays.asList("Orthopaedic Physiotherapy", "Chronic joint pain"));
        Physiotherapist p2 =new Physiotherapist(2, "Dr. Stephan Ramsey", "", Arrays.asList("Neurological Physiotherapy", "Spinal cord injuries"));
        Physiotherapist p3 =new Physiotherapist(3, "Dr. Nishant James", "", Arrays.asList("Cardiorespiratory Physiotherapy", "Asthma"));
        Physiotherapist p4 = new Physiotherapist(4, "Dr. Diana Bolton", "", Arrays.asList("Sports Physiotherapy", "Specialised exercises"));

        treatments.add(new Treatment("Orthopaedic Physiotherapy", 40, 45.0,"Sunday",p1));
        treatments.add(new Treatment("Chronic Joint Pain Treatment", 50, 55.0,"Sunday", p1));
        treatments.add(new Treatment("Neurological Physiotherapy", 45, 45.0, "Monday", p2));
        treatments.add(new Treatment("Spinal Cord Injuries Treatment", 60, 70.0, "Monday", p2));
        treatments.add(new Treatment("Cardiorespiratory Physiotherapy", 30, 50.0, "Tuesday", p3));
        treatments.add(new Treatment("Asthma Exercises", 35, 40.0, "Tuesday", p3));
        treatments.add(new Treatment("Sports Physiotherapy", 40, 45.0, "Wednesday", p4));
        treatments.add(new Treatment("Specialized Exercise Session", 60, 30.0,"Wednesday", p4));


    }

    static void showMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nBPC Booking System");
            System.out.println("1. View Available Appointments");
            System.out.println("2. Book an Appointment");
            System.out.println("3. Cancel an Appointment");
            System.out.println("4. View Report");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    listAvailableAppointments();
                    break;
                case 2:
                    bookAppointment(scanner);
                    break;
                case 3:
                    cancelAppointment(scanner);
                    break;
                case 4:
                    generateReport();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option, try again.");
            }
        }
    }


    static void listAvailableAppointments() {
        System.out.println("\nAvailable Appointments:");
        for (Treatment t : appointments) {
            if ("Available".equalsIgnoreCase(t.status.trim())) {
                System.out.println(t.time + " - " + t.getTreatmentName() + " by " + t.getPhysiotherapist()+" -Status"+t.status);
            }
        }
    }

    static void bookAppointment(Scanner scanner) {
        listAvailableAppointments();
        System.out.print("Enter Patient ID to book: ");
        int patientId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Appointment Time: ");
        String time = scanner.nextLine();

        Patient patient = patients.stream().filter(p -> p.getId() == patientId).findFirst().orElse(null);
        if (patient == null) {
            System.out.println("Invalid patient ID.");
            return;
        }

        for (Treatment t : appointments) {
            if (t.getTime().equals(time) && t.status.equals("Available")) {
                t.patient = patient;
                t.status = "Booked";
                System.out.println("Appointment booked successfully!");
                return;
            }
        }
        System.out.println("No available appointment at this time.");
    }

    static void cancelAppointment(Scanner scanner) {
        System.out.print("Enter Patient ID to cancel: ");
        int patientId = scanner.nextInt();
        scanner.nextLine();

        for (Treatment t : appointments) {
            if (t.patient != null && t.patient.getId() == patientId && t.status.equals("Booked")) {
                t.patient = null;
                t.status = "Available";
                System.out.println("Appointment cancelled successfully.");
                return;
            }
        }
        System.out.println("No booked appointment found for this patient.");
    }

    static void generateReport() {
        System.out.println("\nAppointment Report:");
        for (Treatment t : appointments) {
            String patientName = (t.patient != null) ? t.patient.getPatientName() : "None";
            System.out.println(t.getPhysiotherapist() + " - " + t.getTreatmentName() + " - " + patientName + " - " + t.getTime() + " - " + t.status);
        }
    }
}

/*

    private static <T> T selectFromList(List<T> options) {
        for (int i = 0; i < options.size(); i++) {
            if (options.get(i) instanceof Patient) {
                Patient p = (Patient) options.get(i);
                System.out.println((i + 1) + ". " + p.getPatientName());
            } else if (options.get(i) instanceof Physiotherapist) {
                Physiotherapist ph = (Physiotherapist) options.get(i);
                System.out.println((i + 1) + ". " + ph.getPhysiotherapistName() + " - " + ph.getExpertise());
            } else if (options.get(i) instanceof Treatment) {
                Treatment t = (Treatment) options.get(i);
                System.out.println((i + 1) + ". " + t);

            }
        }
        int choice;
        while (true) {
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            if (choice > 0 && choice <= options.size()) {
                break;
            }
            System.out.println("❌ Invalid selection, try again.");
        }
        return options.get(choice - 1);
    }
}

public class Main {
    public static void main(String[] args) {
        Clinic clinic = new Clinic();



        //Adding Patient
        Patient patient1 = new Patient(1, "Jason Becker", "40 Aviation Avenue", "07521259532");
        Patient patient2 = new Patient(2, "Jeff Beck", "40 Aviation Avenue", "07521259514");
        Patient patient3 = new Patient(3, "Joe Satriani", "40 Aviation Avenue", "07521259545");
        Patient patient4 = new Patient(4, "Jimmy Page", "40 Aviation Avenue", "07521259576");
        Patient patient5 = new Patient(5, "Jimi Hendrix", "40 Aviation Avenue", "07521259587");
        Patient patient6 = new Patient(6, "David Gilmour", "40 Aviation Avenue", "07521259589");
        Patient patient7 = new Patient(7, "James Hetfield", "40 Aviation Avenue", "07521259508");
        Patient patient8 = new Patient(8, "Chuck Berry", "40 Aviation Avenue", "07521259565");
        Patient patient9 = new Patient(9, "Eric Clapton", "40 Aviation Avenue", "07521259595");
        Patient patient10 = new Patient(10, "Robert Johnson", "40 Aviation Avenue", "07521259503");
        Patient patient11 = new Patient(11, "John Petrucci", "40 Aviation Avenue", "07521259520");
        Patient patient12 = new Patient(12, "Guthrie Govan", "40 Aviation Avenue", "07521259551");
        clinic.addPatient(patient1);
        clinic.addPatient(patient2);
        clinic.addPatient(patient3);
        clinic.addPatient(patient4);
        clinic.addPatient(patient5);
        clinic.addPatient(patient6);
        clinic.addPatient(patient7);
        clinic.addPatient(patient8);
        clinic.addPatient(patient9);
        clinic.addPatient(patient10);
        clinic.addPatient(patient11);
        clinic.addPatient(patient12);

        System.out.println("\nPatients Registered in the Clinic:");
        for (Patient patient : clinic.getPatients()) {
            System.out.println(patient.getId() + ". " + patient.getPatientName() + " - " + patient.getAddress());
        }
        // Adding Physiotherapist
        Physiotherapist physiotherapist1 = new Physiotherapist(1, "Dr. Steven Gordan", Arrays.asList("Orthopaedic Physiotherapy","Chronic joint pain"));
        Physiotherapist physiotherapist2 = new Physiotherapist(2, "Dr. Stephan Ramsey", Arrays.asList("Neurological Physiotherapy","Spinal cord injuries"));
        Physiotherapist physiotherapist3 = new Physiotherapist(3, "Dr. Nishant James", Arrays.asList("Cardiorespiratory Physiotherapy","Asthma"));
        Physiotherapist physiotherapist4 = new Physiotherapist(4, "Dr. Diana Bolton", Arrays.asList("Sports Physiotherapy","Specialised exercises"));

        //Store Data in the Clinic Sys
        clinic.addPhysiotherapist(physiotherapist1);
        clinic.addPhysiotherapist(physiotherapist2);
        clinic.addPhysiotherapist(physiotherapist3);
        clinic.addPhysiotherapist(physiotherapist4);

        List<Treatment> treatments= Arrays.asList(
                new Treatment("Orthopaedic Physiotherapy", 40, 45),
                new Treatment("Chronic Joint Pain Treatment", 50, 55),
                new Treatment("Neurological Physiotherapy", 45,45),
                new Treatment("Spinal Cord Injuries Treatment", 60, 70 ),
                new Treatment("Cardiorespiratory Physiotherapy", 30, 50),
                new Treatment("Asthma Exercises", 35, 40),
                new Treatment("Sports Physiotherapy", 40, 45),
                new Treatment("Specialized Exercise Session", 60, 30)
        );

        LocalDateTime startDate = LocalDateTime.now();
        for(int week =0; week < 4; week++){
            for(Physiotherapist physio : clinic.getPhysiotherapists()){
             for(int day = 0; day < 5; day++){

                 LocalDateTime appointmentDateTime = startDate.plusWeeks(week).plusDays(day).withHour(10);
                 Appointment appointment = new Appointment(appointmentDateTime,"Scheduled",patient2.get(day),physio,treatments.get(day %treatments.size()));
                 clinic.bookAppointment(appointment);
             }
            }
        }
            // Initial Display
        System.out.println("Physiotherapists:");
        for(Physiotherapist physio : clinic.getPhysiotherapists()){
            System.out.println(physio.getPhysiotherapistName()+ " - " +physio.getExpertise() );
        }

        System.out.println("/nPatients:");
        for(Patient p : clinic.getPatients()){
            System.out.println(p.getPatientName()+" - "+ p.getAddress());
        }




        //Cancelling the appointment
        if(!clinic.getAppointments().isEmpty()) {
            Appointment firstAppointment = clinic.getAppointments().get(0);
            clinic.cancelAppointment(firstAppointment);
            System.out.println("Appointment cancelled for:" + firstAppointment.getPatientName());
        }
        //generate a clinic report
        clinic.GenerateReport();

    }


}
*/
