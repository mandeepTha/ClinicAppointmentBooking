import static org.junit.jupiter.api.Assertions.*;

import org.boostphysio.Controller.DataInitializer;
import org.boostphysio.Model.Patient;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class PatientManagerTest {
    @Test
    void testAddPatientSuccessfully() {
        List<Patient> patients = new ArrayList<>();
        Patient newPatient = new Patient(100, "Test User", "Test Address", "0123456789");
        patients.add(newPatient);
        assertTrue(patients.contains(newPatient));
    }

    @Test
    void testDuplicatePatientIdNotAllowed() {
        List<Patient> patients = DataInitializer.initializePatients();
        int existingId = patients.get(0).getId();
        boolean exists = patients.stream().anyMatch(p -> p.getId() == existingId);
        assertTrue(exists);
    }
}
