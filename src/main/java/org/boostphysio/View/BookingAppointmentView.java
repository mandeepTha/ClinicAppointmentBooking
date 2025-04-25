package org.boostphysio.View;


import java.util.InputMismatchException;
import java.util.Scanner;


public class BookingAppointmentView {

    public static int showMenu(Scanner scanner) {

            System.out.println("\nBPC Booking System");
            System.out.println("1. Book an Appointment");
            System.out.println("2. Cancel an Appointment");
            System.out.println("3. Attend an Appointment");
            System.out.println("4. Add Patient");
            System.out.println("5. Remove Patient");
            System.out.println("6. View Available Appointments");
            System.out.println("7. Generate Report");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");
            while (!scanner.hasNextInt()) {
                System.out.print("Invalid input. Please enter a number: ");
                scanner.next();
            }

        return scanner.nextInt();
        }


}
