
import static org.junit.jupiter.api.Assertions.*;

import org.boostphysio.Controller.BookingManager;
import org.boostphysio.Controller.DataInitializer;
import org.boostphysio.Model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.*;

public class BookingManagerTest {
    private List<Appointment> appointments;
    private List<Patient> patients;
    private Patient patient;

    @BeforeEach
    void setUp() {
        patients = DataInitializer.initializePatients();
        appointments = DataInitializer.initializeAppointments();
        patient = patients.get(0);
        BookingManager.setAppointments(appointments);

    }

    @Test
    void testSuccessfulBooking() {
        Appointment appt = appointments.get(0);
        appt.bookAppointment(patient);
        assertEquals("Booked", appt.getStatus());
        assertEquals(patient, appt.getPatient());
    }

    @Test
    void testCancelBooking() {
        Appointment appt = appointments.get(0);
        appt.bookAppointment(patient);
        appt.cancelAppointment();
        assertEquals("Cancelled", appt.getStatus());
    }

    @Test
    void testConflictBookingPrevention() {
        Appointment appt1 = appointments.get(0);
        Appointment appt2 = appointments.get(1);
        appt1.bookAppointment(patient);
        boolean conflict = BookingManager.hasConflict(patient, appt2.getDateTime(), appt2.getTreatment().getDuration());
        assertTrue(conflict);
    }
}
