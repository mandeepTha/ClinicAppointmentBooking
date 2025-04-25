package org.boostphysio.Controller;

import org.boostphysio.Model.*;
import org.boostphysio.Model.Physiotherapist;
import java.time.format.DateTimeFormatter;

import java.util.*;
import java.util.stream.Collectors;


public class ReportGenerator {

    public void generateReport(List<Appointment> appointments, List<Physiotherapist> physiotherapists) {
        System.out.println("\n=== Appointment Report by Physiotherapist ===");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE d'th' MMM yyyy, HH:mm");

        for (Physiotherapist physio : physiotherapists) {
            System.out.println("\nPhysiotherapist: " + physio.getName());
            for (Appointment appt : appointments) {
                if (appt.getPhysiotherapist().getId() == physio.getId()) {
                    System.out.println("- " + appt.getTreatment().getTreatmentName() + " | "
                            + (appt.getPatient() != null ? appt.getPatient().getName() : "Unassigned Patient")
                            + " | "
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
        System.out.println("\nUpdated Patient List:");
        listPatientsSorted(patients);
    }

}
