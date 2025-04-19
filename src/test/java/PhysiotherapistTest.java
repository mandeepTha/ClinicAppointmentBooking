import org.boostphysio.Model.Physiotherapist;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

public class PhysiotherapistTest {

    @Test
    public void testPhysiotherapistConstructor() {
        Physiotherapist p = new Physiotherapist(101, "Dr. Steven Gordan", "1 Rose Street", "07541236585", Arrays.asList("Orthopaedic Physiotherapy", "Chronic joint pain"));

        assertEquals(101, p.getId());
        assertEquals("Dr. Steven Gordan", p.getName());
        assertEquals("1 Rose Street", p.getAddress());
        assertEquals("07541236585", p.getPhoneNumber());
        assertTrue(p.getExpertise().contains("Orthopaedic Physiotherapy"));
    }
}
