package org.boostphysio.Controller;
import org.boostphysio.Model.*;

import java.util.List;
import java.util.Scanner;

public class PatientManager {
    private static List<Patient> patients;
    private static Scanner scanner;

    public PatientManager(List<Patient> patients, Scanner scanner) {
        this.patients = patients;
        this.scanner = scanner;
    }


    public static void addPatient() {
        System.out.print("Enter Patient ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        // Check if ID already exists
        boolean exists = patients.stream().anyMatch(p -> p.getId() == id);
        if (exists) {
            System.out.println(" A patient with this ID already exists. Please try another ID.");
            return;
        }

        System.out.print("Enter Patient Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Address: ");
        String address = scanner.nextLine();
        System.out.print("Enter Phone Number: ");
        String phone = scanner.nextLine();

        Patient patient = new Patient(id, name, address, phone);
        patients.add(patient);
        System.out.println("Patient added successfully.");
    }


    public static void removePatient() {
        System.out.print("Enter Patient ID to remove: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean removed = patients.removeIf(p -> p.getId() == id);
        if (removed) {
            System.out.println("Patient removed.");
        } else {
            System.out.println("No patient found with that ID.");
        }
    }
}
