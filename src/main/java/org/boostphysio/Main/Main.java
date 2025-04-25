package org.boostphysio.view;

import org.boostphysio.Controller.*;
import org.boostphysio.Model.*;
import org.boostphysio.View.BookingAppointmentView;

import java.util.*;

public class Main {

    public void run() {
        Scanner scanner = new Scanner(System.in);

        List<Patient> patients = DataInitializer.initializePatients();
        List<Physiotherapist> physiotherapists = DataInitializer.initializePhysiotherapists();
        List<Appointment> appointments = DataInitializer.initializeAppointments();

        BookingManager bookingManager = new BookingManager(appointments, physiotherapists, patients, scanner);
        PatientManager patientManager = new PatientManager(patients, scanner);
        ReportGenerator reportGenerator = new ReportGenerator();

        int choice;
        do {
            choice = BookingAppointmentView.showMenu(scanner);

            switch (choice) {
                case 1 -> bookingManager.bookAppointment();
                case 2 -> bookingManager.cancelAppointment();
                case 3 -> bookingManager.attendAppointment();
                case 4 -> patientManager.addPatient();
                case 5 -> patientManager.removePatient();
                case 6 -> reportGenerator.viewAvailableAppointments(appointments);
                case 7 -> reportGenerator.generateReport(appointments, physiotherapists);
                case 8 -> System.out.println("Exiting system...");
                default -> System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 0);
    }

    public static void main(String[] args) {
        new Main().run();
    }
}
