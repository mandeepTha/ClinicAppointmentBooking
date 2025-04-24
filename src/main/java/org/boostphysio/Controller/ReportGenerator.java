package org.boostphysio.Controller;

import org.boostphysio.Model.*;
import org.boostphysio.Model.Physiotherapist;
import java.time.format.DateTimeFormatter;

import java.util.*;
import java.util.stream.Collectors;

import static org.boostphysio.Controller.BookingManager.*;

public class ReportGenerator {

    public void generateReport(List<Appointment> appointments, List<Physiotherapist> physiotherapists) {
        System.out.println("\n=== Appointment Report by Physiotherapist ===");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE d'th' MMM yyyy, HH:mm");

        for (Physiotherapist physio : physiotherapists) {
            System.out.println("\nPhysiotherapist: " + physio.getName());
            for (Appointment appt : appointments) {
                if (appt.getPhysiotherapist().getId() == physio.getId()) {
                    System.out.println("- " + appt.getTreatment().getTreatmentName() + " | "
                            + appt.getPatient().getName() + " | "
                            + appt.getDateTime().format(formatter) + " | "
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE d'th' MMM yyyy, HH:mm");

        List<Appointment> sorted = appointments.stream()
                .filter(a -> a.getStatus().equals("Available"))
                .sorted(Comparator.comparing(Appointment::getDateTime))
                .collect(Collectors.toList());

        if (sorted.isEmpty()) {
            System.out.println("No available appointments.");
            return;
        }

        for (Appointment a : sorted) {
            System.out.println("- " + a.getTreatment().getTreatmentName() + " with " + a.getPhysiotherapist().getName()
                    + " at " + a.getDateTime().format(formatter));
        }
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
//    static void listAvailableAppointments() {
//        System.out.println("\nAvailable Appointments:");
//        appointments.stream()
//                .filter(a -> a.getStatus().equals("Available"))
//                .sorted(Comparator.comparing(Appointment::getDateTime))
//                .forEach(a -> {
//                    String formatted = formatAppointmentTime(a.getDateTime(), a.getTreatment().getDuration());
//                    System.out.printf("➤ %s with %s on %s [%s]%n",
//                            a.getTreatment().getTreatmentName(),
//                            a.getPhysiotherapist().getName(),
//                            formatted,
//                            a.getStatus());
//                });
//    }
//
//
//
//
//    static void generateReport() {
//        System.out.println("\n Appointment Report:");
//
//        // Group appointments by physiotherapist
//        Map<Physiotherapist, List<Appointment>> appointmentsByPhysio = new HashMap<>();
//        for (Appointment a : appointments) {
//            appointmentsByPhysio
//                    .computeIfAbsent(a.getPhysiotherapist(), k -> new ArrayList<>())
//                    .add(a);
//        }
//
//        for (Map.Entry<Physiotherapist, List<Appointment>> entry : appointmentsByPhysio.entrySet()) {
//            Physiotherapist physio = entry.getKey();
//            System.out.println("\n" + physio.getName());
//
//            List<Appointment> sortedAppointments = entry.getValue().stream()
//                    .sorted(Comparator.comparing(Appointment::getDateTime))
//                    .toList();
//
//            for (Appointment a : sortedAppointments) {
//                String dateTimeFormatted = formatDateTime(a.getDateTime(), a.getTreatment().getDuration());
//                String patientName = a.getPatient() != null ? a.getPatient().getName() : "N/A";
//                System.out.printf("- %s → %s → %s → %s\n",
//                        a.getTreatment().getTreatmentName(),
//                        patientName,
//                        dateTimeFormatted,
//                        a.getStatus());
//            }
//        }
//
//        // Count attended appointments for ranking
//        Map<Physiotherapist, Long> attendedCount = appointments.stream()
//                .filter(a -> Objects.equals(a.getStatus(), "Attended"))
//                .collect(Collectors.groupingBy(Appointment::getPhysiotherapist, Collectors.counting()));
//
//        System.out.println("\n Top Attending Physiotherapists:");
//        attendedCount.entrySet().stream()
//                .sorted(Map.Entry.<Physiotherapist, Long>comparingByValue().reversed())
//                .forEach(entry -> {
//                    System.out.printf("- %s – %d appointments attended\n", entry.getKey().getName(), entry.getValue());
//                });
//    }
//


}
