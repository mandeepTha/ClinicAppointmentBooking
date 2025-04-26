package org.boostphysio.Controller;

import org.boostphysio.Model.*;
import org.boostphysio.Model.Physiotherapist;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.*;
import java.util.stream.Collectors;


public class ReportGenerator {

    public void generateReport(List<Appointment> appointments, List<Physiotherapist> physiotherapists) {
        System.out.println("\n=== Appointment Report by Physiotherapist ===");

        boolean hasValidAppointments = appointments.stream()
                .anyMatch(a -> !a.getStatus().equals("Available"));

        if (!hasValidAppointments) {
            System.out.println("No booked, cancelled, or attended appointments to report.");
            return; // Stop generating further
        }
        List<Appointment> sortedAppointments = appointments.stream()
                .sorted(Comparator.comparing(Appointment::getDateTime))
                .collect(Collectors.toList());

        for (Physiotherapist physio : physiotherapists) {

            System.out.println("\nPhysiotherapist: " + physio.getName());
            for (Appointment appt : sortedAppointments) {
                if (appt.getStatus().equals("Available")) {
                    continue; // Skip unbooked appointments
                }
                if (appt.getPhysiotherapist().getId() == physio.getId()) {
                    String patientName = appt.getPatient() != null ? appt.getPatient().getName() : "Unassigned";
                    System.out.println("- " + appt.getTreatment().getTreatmentName() + " | "
                            + patientName + " | "
                            + formatWithEndTime(appt.getDateTime(), appt.getTreatment().getDuration()) + " | "
                            + appt.getStatus());
                }
            }
        }

        System.out.println("\n=== Physiotherapist Rankings by Attended Appointments ===");
        Map<Physiotherapist, Long> attendedCount = appointments.stream()
                .filter(a -> a.getStatus().equals("Attended"))
                .collect(Collectors.groupingBy(Appointment::getPhysiotherapist, Collectors.counting()));

        attendedCount.entrySet().stream()
                .sorted(Map.Entry.<Physiotherapist, Long>comparingByValue().reversed())
                .forEach(entry -> System.out.println(entry.getKey().getName() + " - " + entry.getValue() + " attended appointments"));
    }

    public void viewAvailableAppointments(List<Appointment> appointments) {
        System.out.println("\n=== Available Appointments ===");

        List<Appointment> sorted = appointments.stream()
                .filter(a -> a.getStatus().equals("Available"))
                .filter(a -> isFeasibleTime(a.getDateTime()))
                .sorted(Comparator.comparing(Appointment::getDateTime))
                .collect(Collectors.toList());

        if (sorted.isEmpty()) {
            System.out.println("No available appointments.");
            return;
        }

        for (Appointment a : sorted) {
            System.out.println("- " + a.getTreatment().getTreatmentName() + " with " + a.getPhysiotherapist().getName()
                    + " at " + formatWithEndTime(a.getDateTime(), a.getTreatment().getDuration()));
        }
    }

    public void printAppointmentConfirmation(Appointment appointment) {
        System.out.println("\n✅ Appointment booked successfully!");
        System.out.println("Treatment: " + appointment.getTreatment().getTreatmentName());
        System.out.println("Physiotherapist: " + appointment.getPhysiotherapist().getName());
        System.out.println("Patient: " + appointment.getPatient().getName());
        System.out.println("Time: " + formatWithEndTime(appointment.getDateTime(), appointment.getTreatment().getDuration()));
        System.out.println("Status: " + appointment.getStatus());
    }

    public void printAppointmentCancellation(Appointment appointment) {
        System.out.println("\n❌ Appointment cancelled successfully!");
        System.out.println("Treatment: " + appointment.getTreatment().getTreatmentName());
        System.out.println("Physiotherapist: " + appointment.getPhysiotherapist().getName());
        System.out.println("Patient: " + appointment.getPatient().getName());
        System.out.println("Time: " + formatWithEndTime(appointment.getDateTime(), appointment.getTreatment().getDuration()));
        System.out.println("Status: " + appointment.getStatus());
    }

    private static String formatWithEndTime(LocalDateTime dateTime, int duration) {
        int day = dateTime.getDayOfMonth();
        String suffix;
        if (day >= 11 && day <= 13) {
            suffix = "th";
        } else {
            switch (day % 10) {
                case 1: suffix = "st"; break;
                case 2: suffix = "nd"; break;
                case 3: suffix = "rd"; break;
                default: suffix = "th";
            }
        }
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("EEEE d'" + suffix + "' MMM yyyy, HH:mm");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalDateTime endTime = dateTime.plusMinutes(duration);
        return dateTime.format(dateFormatter) + " - " + endTime.format(timeFormatter);
    }

    private static boolean isFeasibleTime(LocalDateTime dateTime) {
        int hour = dateTime.getHour();
        DayOfWeek day = dateTime.getDayOfWeek();
        return (hour >= 8 && hour <= 18) && !(day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY);
    }

    public void listExpertise(List<Physiotherapist> physiotherapists) {
        Set<String> expertiseSet = new TreeSet<>();
        for (Physiotherapist p : physiotherapists) {
            expertiseSet.addAll(p.getExpertise());
        }
        System.out.println("\nAvailable Areas of Expertise:");
        for (String expertise : expertiseSet) {
            System.out.println("- " + expertise);
        }
    }

    public void listPhysiotherapists(List<Physiotherapist> physiotherapists) {
        System.out.println("\nRegistered Physiotherapists:");
        for (Physiotherapist p : physiotherapists) {
            System.out.println(p.getId() + ". " + p.getName());
        }
    }

    public void listPatients(List<Patient> patients) {
        System.out.println("\nRegistered Patients:");
        for (Patient p : patients) {
            System.out.println(p.getId() + ". " + p.getName());
        }
    }

    public void listPatientsSorted(List<Patient> patients) {
        patients.sort(Comparator.comparingInt(Patient::getId));
        listPatients(patients);
    }

    public void displayNewPatient(Patient patient, List<Patient> patients) {
        System.out.println("\nPatient added successfully:");
        System.out.println(patient.getId() + ". " + patient.getName());
        System.out.println("\nUpdated Patient List");
        listPatientsSorted(patients);
    }

    public void displayUpdatedPatientListAfterRemoval(List<Patient> patients) {
        System.out.println("\nPatient removed successfully.");
        System.out.println("\nUpdated Patient List");
        listPatientsSorted(patients);
    }
}
