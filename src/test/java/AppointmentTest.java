import org.boostphysio.Model.Appointment;
import org.boostphysio.Model.Patient;
import org.boostphysio.Model.Physiotherapist;
import org.boostphysio.Model.Treatment;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class AppointmentTest {

    @org.junit.Test
    @Test
    public void testBookAppointment() {
        Physiotherapist p = new Physiotherapist(401, "Dr. Diana Bolton", "19 Lincoln Gardens", "07523145697", Arrays.asList("Sports Physiotherapy", "Specialised exercises"));
        Treatment t = new Treatment("Sports Physiotherapy", 40, p);
        LocalDateTime dt = LocalDateTime.of(2025, 5, 9, 9, 30);
        Appointment a = new Appointment(p, t, dt);

        Patient patient = new Patient(2, "Jeff Beck", "41 Aviation Avenue", "07521259514");
        a.bookAppointment(patient);

        assertEquals("Booked", a.getStatus());
        assertEquals(patient, a.getPatient());
    }

    @org.junit.Test
    @Test
    public void testCancelAppointment() {
        Physiotherapist p = new Physiotherapist(401, "Dr. Diana Bolton", "19 Lincoln Gardens", "07523145697", Arrays.asList("Sports Physiotherapy", "Specialised exercises"));
        Treatment t = new Treatment("Sports Physiotherapy", 40, p);
        LocalDateTime dt = LocalDateTime.of(2025, 5, 9, 9, 30);
        Appointment a = new Appointment(p, t, dt);

        Patient patient = new Patient(2, "Jeff Beck", "41 Aviation Avenue", "07521259514");
        a.bookAppointment(patient);
        a.cancelAppointment();

        assertEquals("Cancelled", a.getStatus());
    }
}
