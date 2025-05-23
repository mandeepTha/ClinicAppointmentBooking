package org.boostphysio.Controller;
import org.boostphysio.Model.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.util.*;
import java.util.stream.Collectors;

public class BookingManager {
    private static List<Appointment> appointments;
    private static List<Physiotherapist> physiotherapists;
    private static List<Patient> patients;
    private static Scanner scanner;

    public BookingManager(List<Appointment> appointments, List<Physiotherapist> physiotherapists, List<Patient> patients, Scanner scanner) {
        this.appointments = appointments;
        this.physiotherapists = physiotherapists;
        this.patients = patients;
        this.scanner = scanner;
    }

    public static List<Physiotherapist> getPhysiotherapists() {
        return new ArrayList<>(new HashSet<>(appointments.stream().map(Appointment::getPhysiotherapist).toList()));
    }

    private static void searchAndBookByExpertise(Scanner scanner, Patient patient) {
        ReportGenerator reportGen = new ReportGenerator();
        reportGen.listExpertise(getPhysiotherapists());

        System.out.print("Enter area of expertise: ");
        String expertise = scanner.nextLine().trim().toLowerCase();

        List<Appointment> matchingAppointments = appointments.stream()
                .filter(a -> a.getStatus().equals("Available"))
                .filter(a -> a.getTreatment().getTreatmentName().toLowerCase().contains(expertise))
                .collect(Collectors.toList());

        matchingAppointments.sort(Comparator.comparing(Appointment::getDateTime));

        if (matchingAppointments.isEmpty()) {
            System.out.println(" No available appointments for this expertise.");
            return;
        }

        System.out.println("Available Appointments:");
        for (Appointment a : matchingAppointments) {
            String timeFormatted = formatAppointmentTime(a.getDateTime(), a.getTreatment().getDuration());
            System.out.printf("\u2794 %s with %s on %s [%s]%n",
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
            System.out.println(" Invalid date format. Use: yyyy-MM-ddTHH:mm");
            return;
        }

        Optional<Appointment> appointmentOpt = matchingAppointments.stream()
                .filter(a -> a.getDateTime().equals(selectedDateTime))
                .findFirst();

        if (appointmentOpt.isEmpty()) {
            System.out.println(" No appointment found at that date and time.");
            return;
        }

        Appointment selectedAppointment = appointmentOpt.get();


        if (patient == null) {
            System.out.println(" Patient not found.");
            return;
        }

        if (hasConflict(patient, selectedAppointment.getDateTime(), selectedAppointment.getTreatment().getDuration())) {
            System.out.println(" Patient already has an appointment at this time. Cannot double book.");
            return;
        }

        selectedAppointment.bookAppointment(patient);
        new ReportGenerator().printAppointmentConfirmation(selectedAppointment);
    }

    private static void searchAndBookByPhysiotherapist(Scanner scanner, Patient patient) {
        ReportGenerator reportGen = new ReportGenerator();
        reportGen.listPhysiotherapists(getPhysiotherapists());

        System.out.print("Enter Physiotherapist's name: ");
        String physioName = scanner.nextLine().trim().toLowerCase();

        List<Appointment> matchingAppointments = appointments.stream()
                .filter(a -> a.getStatus().equals("Available"))
                .filter(a -> a.getPhysiotherapist().getName().toLowerCase().contains(physioName))
                .collect(Collectors.toList());

        matchingAppointments.sort(Comparator.comparing(Appointment::getDateTime));

        if (matchingAppointments.isEmpty()) {
            System.out.println(" No available appointments for this physiotherapist.");
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
            System.out.println(" Invalid date format. Use: yyyy-MM-ddTHH:mm");
            return;
        }

        Optional<Appointment> appointmentOpt = matchingAppointments.stream()
                .filter(a -> a.getDateTime().equals(selectedDateTime))
                .findFirst();

        if (appointmentOpt.isEmpty()) {
            System.out.println(" No appointment found at that date and time.");
            return;
        }

        Appointment selectedAppointment = appointmentOpt.get();

        if (patient == null) {
            System.out.println(" Patient not found.");
            return;
        }

        if (hasConflict(patient, selectedAppointment.getDateTime(), selectedAppointment.getTreatment().getDuration())) {
            System.out.println(" Patient already has an appointment at this time. Cannot double book.");
            return;
        }

        selectedAppointment.bookAppointment(patient);
        new ReportGenerator().printAppointmentConfirmation(selectedAppointment);
    }

    //Date time formatter for Correct date and time including the day
    static String formatAppointmentTime(LocalDateTime dateTime, int durationMinutes) {
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

    public static boolean hasConflict(Patient patient, LocalDateTime newDateTime, int newDuration) {
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


    public static void bookAppointment() {
        System.out.println("Select a patient by ID for booking an appointment.");
        new ReportGenerator().listPatients(patients);
        System.out.print("Enter patient ID: ");
        int patientId = scanner.nextInt();
        scanner.nextLine();

        Patient selectedPatient = null;
        for (Patient p : patients) {
            if (p.getId() == patientId) {
                selectedPatient = p;
                break;
            }
        }

        if (selectedPatient == null) {
            System.out.println("Invalid patient ID.");
            return;
        }

        while (true) {
            System.out.println("\n📘 Book an Appointment:");
            System.out.println("1. Search & Book by Expertise");
            System.out.println("2. Search & Book by Physiotherapist");
            System.out.println("3. Back to Main Menu");
            System.out.print("Choose an option: ");
            String input = scanner.nextLine().trim();

            int subChoice;
            try {
                subChoice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input. Please enter a number from the menu.");
                continue;
            }

            if (input.isEmpty()) {
                System.out.println("No input provided. Cancelling operation.");
                return;
            }

            switch (subChoice) {
                case 1:
                    searchAndBookByExpertise(scanner, selectedPatient);
                    break;
                case 2:
                    searchAndBookByPhysiotherapist(scanner, selectedPatient);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public static void cancelAppointment() {
        new ReportGenerator().listPatients(patients);
        System.out.print("Enter Patient ID to cancel appointment: ");
        if (scanner.hasNextLine()) scanner.nextLine();  // flush leftover newline
        String input = scanner.nextLine().trim();
        if (input.isEmpty()) {
            System.out.println("No input provided. Cancelling operation.");
            return;
        }

        int patientId;
        try {
            patientId = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format. Please enter a valid patient ID.");
            return;
        }

        List<Appointment> bookedAppointments = appointments.stream()
                .filter(a -> a.getPatient() != null)
                .filter(a -> a.getPatient().getId() == patientId)
                .filter(a -> a.getStatus().equals("Booked"))
                .sorted(Comparator.comparing(Appointment::getDateTime))
                .toList();

        if (bookedAppointments.isEmpty()) {
            System.out.println("No booked appointments found for this patient.");
            return;
        }

        System.out.println("\n Booked Appointments:");
        bookedAppointmentList(bookedAppointments);

        System.out.print("Select appointment number to cancel: ");
        String cancelInput = scanner.nextLine().trim();

        int selected;
        try {
            selected = Integer.parseInt(cancelInput);
        } catch (NumberFormatException e) {
            System.out.println("Invalid selection.");
            return;
        }

        if (selected < 1 || selected > bookedAppointments.size()) {
            System.out.println("Invalid appointment number.");
            return;
        }

        Appointment toCancel = bookedAppointments.get(selected - 1);
        toCancel.cancelAppointment();
        new ReportGenerator().printAppointmentCancellation(toCancel);
        System.out.println("Appointment cancelled successfully.");
    }

    private static void bookedAppointmentList(List<Appointment> bookedAppointments) {
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
    }

    public static void attendAppointment() {
        new ReportGenerator().listPatients(patients);
        System.out.print("Enter Patient ID to check-in: ");
        int patientId = scanner.nextInt();
        scanner.nextLine();

        List<Appointment> bookedAppointments = appointments.stream()
                .filter(a -> a.getPatient() != null
                        && a.getPatient().getId() == patientId
                        && Objects.equals(a.getStatus(), "Booked"))
                .toList();

        if (bookedAppointments.isEmpty()) {
            System.out.println("No booked appointment found for this patient.");
            return;
        }

        System.out.println("Booked Appointments:");
        bookedAppointmentList(bookedAppointments);

        System.out.print("Select appointment number to mark as attended: ");
        int selected = scanner.nextInt();
        scanner.nextLine();

        if (selected >= 1 && selected <= bookedAppointments.size()) {
            Appointment toMark = bookedAppointments.get(selected - 1);
            toMark.attendedAppointment(); //  sets status to ATTENDED
            System.out.println("Appointment marked as attended.");
        } else {
            System.out.println("Invalid selection.");
        }
    }

    public static String formatDateTime(LocalDateTime dateTime, int duration) {
        DateTimeFormatter startFormat = DateTimeFormatter.ofPattern("EEEE d'th' MMMM yyyy, HH:mm");
        LocalDateTime endTime = dateTime.plusMinutes(duration);
        DateTimeFormatter endFormat = DateTimeFormatter.ofPattern("HH:mm");

        return dateTime.format(startFormat) + " - " + endTime.format(endFormat);
    }

    public static void setAppointments(List<Appointment> appts) {
        appointments = appts;
    }
}