import org.boostphysio.Model.Patient;

import static org.junit.Assert.assertEquals;


class PatientTest {
    void testGetName() {
        Patient patient = new Patient(23456, "Sarah Willims", "Hemel Hempstead","07572598562");
        assertEquals("Sarah Willims",patient.getName());
    }


}
