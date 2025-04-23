package org.boostphysio;

import org.boostphysio.Model.Appointment;
import org.boostphysio.Model.Patient;
import org.boostphysio.Model.Physiotherapist;
import org.boostphysio.Model.Treatment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import java.util.*;
import java.util.stream.Collectors;

import java.time.format.TextStyle;
import java.util.Locale;


public class Main {
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

        //Adding Physiotherapist
        Physiotherapist p1 = new Physiotherapist(101, "Dr. Steven Gordan", "1 Rose Street", "07541236585", Arrays.asList("Orthopaedic Physiotherapy", "Chronic joint pain"));
        Physiotherapist p2 =new Physiotherapist(201, "Dr. Stephan Ramsey", "33 Cavendish Avenue","07541523658", Arrays.asList("Neurological Physiotherapy", "Spinal cord injuries"));
        Physiotherapist p3 =new Physiotherapist(301, "Dr. Nishant James", "22 Rticroft Close", "07452315985", Arrays.asList("Cardiorespiratory Physiotherapy", "Asthma"));
        Physiotherapist p4 = new Physiotherapist(401, "Dr. Diana Bolton", "19 Lincoln Gardens", "07523145697",Arrays.asList("Sports Physiotherapy", "Specialised exercises"));

        physiotherapists.add(p1);
        physiotherapists.add(p2);
        physiotherapists.add(p3);
        physiotherapists.add(p4);

        Treatment Ortho = new Treatment("Orthopaedic Physiotherapy", 50 , p1);
        Treatment JointPain = new Treatment("Chronic Joint Pain Treatment",35, p1);
        Treatment Neuro = new Treatment("Neurological Physiotherapy", 45, p2);
        Treatment Spinal = new Treatment("Spinal Cord Injuries Treatment",35, p2);
        Treatment Cardio = new Treatment("Cardiorespiratory Physiotherapy",60, p3);
        Treatment Asthma = new Treatment("Asthma Exercises", 30, p3);
        Treatment Physio = new Treatment("Sports Physiotherapy", 40, p4);
        Treatment Special = new Treatment("Specialized Exercise Session", 60, p4);

        LocalDateTime startDateTime = LocalDateTime.of(2025, 5, 1,10,0);

        for (int week = 0; week < 4; week++) {
            LocalDateTime weekStart = startDateTime.plusWeeks(week);

            // Dr. Steven Gordan's schedule
            appointments.add(new Appointment(p1, Ortho, weekStart.withHour(10).withMinute(0))); // Thursday 10am
            appointments.add(new Appointment(p1, JointPain, weekStart.plusDays(6).withHour(11).withMinute(0))); // Wednesday 11am

            // Dr. Stephan Ramsey's schedule
            appointments.add(new Appointment(p2, Neuro,weekStart.plusDays(1).withHour(11).withMinute(0))); // Friday 11am
            appointments.add(new Appointment(p2, Spinal , weekStart.plusDays(5).withHour(10).withMinute(30))); // Tuesday 10:30am

            // Dr. Nishant James’s schedule
            appointments.add(new Appointment(p3, Cardio, weekStart.withHour(12).withMinute(30))); // Thursday 12:30am
            appointments.add(new Appointment(p3, Asthma, weekStart.plusDays(4).withHour(11).withMinute(0))); // Monday 11am

            // Dr. Diana Bolton
            appointments.add(new Appointment(p4, Physio, weekStart.plusDays(1).withHour(9).withMinute(30))); // Friday 9:30am
            appointments.add(new Appointment(p4, Special, weekStart.plusDays(4).withHour(11).withMinute(0))); //Monday 11am
        }
    }

    static void showMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nBPC Booking System");
            System.out.println("1. View Available Appointments");
            System.out.println("2. Book an Appointment");
            System.out.println("3. Cancel an Appointment");
            System.out.println("4. View Report");
            System.out.println("5. Add Patient");
            System.out.println("6. Remove Patient");
            System.out.println("7. Mark Appointment as Attended");
            System.out.println("8. Exit");
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
                    addPatient(scanner);
                    break;
                case 6:
                    removePatient(scanner);
                    break;
                case 7:
                    markAppointmentAsAttended(scanner);
                    break;
                case 8:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option, try again.");
            }
        }
    }


    static void listAvailableAppointments() {
        System.out.println("\nAvailable Appointments:");
        appointments.stream()
                .filter(a -> a.getStatus().equals("Available"))
                .sorted(Comparator.comparing(Appointment::getDateTime))
                .forEach(a -> {
                    String formatted = formatAppointmentTime(a.getDateTime(), a.getTreatment().getDuration());
                    System.out.printf("➤ %s with %s on %s [%s]%n",
                            a.getTreatment().getTreatmentName(),
                            a.getPhysiotherapist().getName(),
                            formatted,
                            a.getStatus());
                });
    }

    private static void searchAndBookByExpertise(Scanner scanner) {
        System.out.print("Enter area of expertise: ");
        String expertise = scanner.nextLine().trim().toLowerCase();

        List<Appointment> matchingAppointments = appointments.stream()
                .filter(a -> a.getStatus().equals("Available"))
                .filter(a -> a.getTreatment().getTreatmentName().toLowerCase().contains(expertise))
                .collect(Collectors.toList());

        matchingAppointments.sort(Comparator.comparing(Appointment::getDateTime));

        if (matchingAppointments.isEmpty()) {
            System.out.println("❌ No available appointments for this expertise.");
            return;
        }

        System.out.println("Available Appointments:");
        for (Appointment a : matchingAppointments) {
            String timeFormatted = formatAppointmentTime(a.getDateTime(), a.getTreatment().getDuration());
            System.out.printf("➤ %s with %s on %s [%s]%n",
                    a.getTreatment().getTreatmentName(),
                    a.getPhysiotherapist().getName(),
                    timeFormatted,
                    a.getStatus());
        }

        System.out.print("Enter appointment date and time (e.g., 2025-05-01T10:00): ");
        String inputDateTime = scanner.nextLine();

        LocalDateTime selectedDateTime;
        try {
            selectedDateTime = LocalDateTime.parse(inputDateTime);
        } catch (DateTimeParseException e) {
            System.out.println("❌ Invalid date format. Use: yyyy-MM-ddTHH:mm");
            return;
        }

        Optional<Appointment> appointmentOpt = matchingAppointments.stream()
                .filter(a -> a.getDateTime().equals(selectedDateTime))
                .findFirst();

        if (appointmentOpt.isEmpty()) {
            System.out.println("❌ No appointment found at that date and time.");
            return;
        }

        Appointment selectedAppointment = appointmentOpt.get();

        System.out.print("Enter Patient ID to book an appointment: ");
        int patientId = Integer.parseInt(scanner.nextLine());
        Patient patient = patients.stream().filter(p -> p.getId() == patientId).findFirst().orElse(null);

        if (patient == null) {
            System.out.println("❌ Patient not found.");
            return;
        }

        if (hasConflict(patient, selectedAppointment.getDateTime(), selectedAppointment.getTreatment().getDuration())) {
            System.out.println("⚠️ Patient already has an appointment at this time. Cannot double book.");
            return;
        }

        selectedAppointment.bookAppointment(patient);
        System.out.println("✅ Appointment booked successfully.");
    }

       private static void searchAndBookByPhysiotherapist(Scanner scanner) {
        System.out.print("Enter Physiotherapist's name: ");
        String physioName = scanner.nextLine().trim().toLowerCase();

        List<Appointment> matchingAppointments = appointments.stream()
                .filter(a -> a.getStatus().equals("Available"))
                .filter(a -> a.getPhysiotherapist().getName().toLowerCase().contains(physioName))
                .collect(Collectors.toList());

        matchingAppointments.sort(Comparator.comparing(Appointment::getDateTime));

           if (matchingAppointments.isEmpty()) {
            System.out.println("❌ No available appointments for this physiotherapist.");
            return;
        }

        System.out.println("Available Appointments:");
        for (Appointment a : matchingAppointments) {
            String formatted = formatAppointmentTime(a.getDateTime(), a.getTreatment().getDuration());
            System.out.printf("➤ %s with %s on %s [%s]%n",
                    a.getTreatment().getTreatmentName(),
                    a.getPhysiotherapist().getName(),
                    formatted,
                    a.getStatus());
        }

        System.out.print("Enter appointment date and time (e.g., 2025-05-01T10:00): ");
        String inputDateTime = scanner.nextLine();

        LocalDateTime selectedDateTime;
        try {
            selectedDateTime = LocalDateTime.parse(inputDateTime);
        } catch (DateTimeParseException e) {
            System.out.println("❌ Invalid date format. Use: yyyy-MM-ddTHH:mm");
            return;
        }

        Optional<Appointment> appointmentOpt = matchingAppointments.stream()
                .filter(a -> a.getDateTime().equals(selectedDateTime))
                .findFirst();

        if (appointmentOpt.isEmpty()) {
            System.out.println("❌ No appointment found at that date and time.");
            return;
        }

        Appointment selectedAppointment = appointmentOpt.get();

        System.out.print("Enter Patient ID to book an appointment: ");
        int patientId = Integer.parseInt(scanner.nextLine());
        Patient patient = patients.stream()
                .filter(p -> p.getId() == patientId)
                .findFirst()
                .orElse(null);

        if (patient == null) {
            System.out.println("❌ Patient not found.");
            return;
        }

        if (hasConflict(patient, selectedAppointment.getDateTime(), selectedAppointment.getTreatment().getDuration())) {
            System.out.println("⚠️ Patient already has an appointment at this time. Cannot double book.");
            return;
        }

           selectedAppointment.bookAppointment(patient);
        System.out.println("✅ Appointment booked successfully.");
    }

    //Date time formatter for Correct date and time including the day
    private static String formatAppointmentTime(LocalDateTime dateTime, int durationMinutes) {
        String dayOfWeek = dateTime.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        String month = dateTime.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        int day = dateTime.getDayOfMonth();
        String suffix = getDaySuffix(day);
        int year = dateTime.getYear();
        String startTime = dateTime.toLocalTime().toString();
        String endTime = dateTime.plusMinutes(durationMinutes).toLocalTime().toString();

        return String.format("%s %d%s %s %d, %s - %s", dayOfWeek, day, suffix, month, year, startTime, endTime);
    }

    private static String getDaySuffix(int day) {
        if (day >= 11 && day <= 13) return "th";
        return switch (day % 10) {
            case 1 -> "st";
            case 2 -> "nd";
            case 3 -> "rd";
            default -> "th";
        };
    }

    private static boolean hasConflict(Patient patient, LocalDateTime newDateTime, int newDuration) {
        for (Appointment a : appointments) {
            if (a.getPatient() != null && a.getPatient().getId() == patient.getId()) {
                LocalDateTime existingStart = a.getDateTime();
                LocalDateTime existingEnd = a.getDateTime().plusMinutes(a.getTreatment().getDuration());
                LocalDateTime newEnd = newDateTime.plusMinutes(newDuration);

                boolean overlaps = !(newDateTime.isAfter(existingEnd) || newEnd.isBefore(existingStart));

                if (overlaps && Objects.equals(a.getStatus(), "Booked")) {
                    return true;
                }
            }
        }
        return false;
    }


    static void bookAppointment(Scanner scanner) {

        while (true) {
            System.out.println("\n📘 Book an Appointment:");
            System.out.println("1. Search & Book by Expertise");
            System.out.println("2. Search & Book by Physiotherapist");
            System.out.println("3. Back to Main Menu");
            System.out.print("Choose an option: ");

            int subChoice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (subChoice) {
                case 1:
                    searchAndBookByExpertise(scanner);
                    break;
                case 2:
                    searchAndBookByPhysiotherapist(scanner);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void cancelAppointment(Scanner scanner) {
        System.out.print("Enter Patient ID to cancel appointment: ");
        int patientId = Integer.parseInt(scanner.nextLine());

        Optional<Appointment> appointmentOpt = appointments.stream()
                .filter(a -> a.getPatient() != null)
                .filter(a -> a.getPatient().getId() == patientId)
                .filter(a -> a.getStatus().equals("Booked"))
                .findFirst();

        if (appointmentOpt.isEmpty()) {
            System.out.println("❌ No booked appointment found for this patient.");
            return;
        }

        Appointment appointment = appointmentOpt.get();
        appointment.cancelAppointment();  // sets status = "Cancelled"
        System.out.println("✅ Appointment cancelled successfully.");
    }



    static void generateReport() {
        System.out.println("\n📄 Appointment Report:");

        // Group appointments by physiotherapist
        Map<Physiotherapist, List<Appointment>> appointmentsByPhysio = new HashMap<>();
        for (Appointment a : appointments) {
            appointmentsByPhysio
                    .computeIfAbsent(a.getPhysiotherapist(), k -> new ArrayList<>())
                    .add(a);
        }

        for (Map.Entry<Physiotherapist, List<Appointment>> entry : appointmentsByPhysio.entrySet()) {
            Physiotherapist physio = entry.getKey();
            System.out.println("\n" + physio.getName());

            List<Appointment> sortedAppointments = entry.getValue().stream()
                    .sorted(Comparator.comparing(Appointment::getDateTime))
                    .toList();

            for (Appointment a : sortedAppointments) {
                String dateTimeFormatted = formatDateTime(a.getDateTime(), a.getTreatment().getDuration());
                String patientName = a.getPatient() != null ? a.getPatient().getName() : "N/A";
                System.out.printf("- %s → %s → %s → %s\n",
                        a.getTreatment().getTreatmentName(),
                        patientName,
                        dateTimeFormatted,
                        a.getStatus());
            }
        }

        // Count attended appointments for ranking
        Map<Physiotherapist, Long> attendedCount = appointments.stream()
                .filter(a -> a.getStatus() == "Attended")
                .collect(Collectors.groupingBy(Appointment::getPhysiotherapist, Collectors.counting()));

        System.out.println("\n🏆 Top Attending Physiotherapists:");
        attendedCount.entrySet().stream()
                .sorted(Map.Entry.<Physiotherapist, Long>comparingByValue().reversed())
                .forEach(entry -> {
                    System.out.printf("- %s – %d appointments attended\n", entry.getKey().getName(), entry.getValue());
                });
    }

    private static String formatDateTime(LocalDateTime dateTime, int duration) {
        DateTimeFormatter startFormat = DateTimeFormatter.ofPattern("EEEE d'th' MMMM yyyy, HH:mm");
        LocalDateTime endTime = dateTime.plusMinutes(duration);
        DateTimeFormatter endFormat = DateTimeFormatter.ofPattern("HH:mm");

        return dateTime.format(startFormat) + " - " + endTime.format(endFormat);
    }


    private static void addPatient(Scanner scanner) {
        System.out.print("Enter Patient Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Address: ");
        String address = scanner.nextLine();
        System.out.print("Enter Phone: ");
        String phone = scanner.nextLine();

        int newId = patients.size() + 1;
        Patient newPatient = new Patient(newId, name, address, phone);
        patients.add(newPatient);

        System.out.println("Patient added successfully: " + newPatient.getName());
    }

    private static void removePatient(Scanner scanner) {
        System.out.print("Enter Patient ID to remove: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean removed = patients.removeIf(p -> p.getId() == id);
        if (removed) {
            System.out.println("✅ Patient removed.");
        } else {
            System.out.println("No patient found with that ID.");
        }
    }

    static void markAppointmentAsAttended(Scanner scanner) {
        System.out.print("Enter Patient ID to check-in: ");
        int patientId = scanner.nextInt();
        scanner.nextLine();

        List<Appointment> bookedAppointments = appointments.stream()
                .filter(a -> a.getPatient() != null
                        && a.getPatient().getId() == patientId
                        && a.getStatus() == "Booked")
                .toList();

        if (bookedAppointments.isEmpty()) {
            System.out.println("No booked appointment found for this patient.");
            return;
        }

        System.out.println("Booked Appointments:");
        for (int i = 0; i < bookedAppointments.size(); i++) {
            Appointment a = bookedAppointments.get(i);
            String formatted = formatDateTime(a.getDateTime(), a.getTreatment().getDuration());
            System.out.printf("%d. %s with %s at %s [%s]\n",
                    i + 1,
                    a.getTreatment().getTreatmentName(),
                    a.getPhysiotherapist().getName(),
                    formatted,
                    a.getStatus());
        }

        System.out.print("Select appointment number to mark as attended: ");
        int selected = scanner.nextInt();
        scanner.nextLine();

        if (selected >= 1 && selected <= bookedAppointments.size()) {
            Appointment toMark = bookedAppointments.get(selected - 1);
            toMark.attendedAppointment(); //  sets status to ATTENDED
            System.out.println("✅ Appointment marked as attended.");
        } else {
            System.out.println("Invalid selection.");
        }
    }

}

