package org.boostphysio.View;


import java.util.Scanner;

import static org.boostphysio.Controller.BookingManager.bookAppointment;
import static org.boostphysio.Controller.BookingManager.cancelAppointment;
import static org.boostphysio.Controller.BookingManager.markAppointmentAsAttended;
import static org.boostphysio.Controller.PatientManager.*;
import static org.boostphysio.Controller.ReportGenerator.*;


public class BookingAppointmentView {

    public static int showMenu(Scanner scanner) {


        System.out.println("\nBPC Booking System");
        System.out.println("1. Select a Patient");
        System.out.println("2. Book an Appointment");
        System.out.println("3. Cancel an Appointment");
        System.out.println("4. Mark Appointment as Attended");
        System.out.println("5. Add Patient");
        System.out.println("6. Remove Patient");
        System.out.println("7. View Available Appointments");
        System.out.println("8. Generate Report");
        System.out.println("9. Exit");
        System.out.print("Choose an option: ");
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Please enter a number: ");
            scanner.next();
        }
        return scanner.nextInt();
    }


}
