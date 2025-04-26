import static org.junit.jupiter.api.Assertions.*;

import org.boostphysio.Controller.DataInitializer;
import org.boostphysio.Model.*;
import org.junit.jupiter.api.Test;

import java.util.List;

public class DataInitializerTest {
    @Test
    void testInitializePatients() {
        List<Patient> patients = DataInitializer.initializePatients();
        assertFalse(patients.isEmpty());
    }

    @Test
    void testInitializePhysiotherapists() {
        List<Physiotherapist> physios = DataInitializer.initializePhysiotherapists();
        assertFalse(physios.isEmpty());
    }

    @Test
    void testInitializeAppointments() {
        List<Appointment> appointments = DataInitializer.initializeAppointments();
        assertFalse(appointments.isEmpty());
    }
}
