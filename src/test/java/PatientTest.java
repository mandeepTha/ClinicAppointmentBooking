import org.boostphysio.Model.Patient;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PatientTest {

    @Test
    public void testPatientConstructor() {
        Patient p = new Patient(1, "Jason Becker", "40 Aviation Avenue", "07521259532");
        assertEquals(1, p.getId());
        assertEquals("Jason Becker", p.getName());
        assertEquals("40 Aviation Avenue", p.getAddress());
        assertEquals("07521259532", p.getPhoneNumber());
    }
}
