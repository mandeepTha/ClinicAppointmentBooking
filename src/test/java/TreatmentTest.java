import org.boostphysio.Model.Physiotherapist;
import org.boostphysio.Model.Treatment;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

public class TreatmentTest {

    @Test
    public void testTreatmentConstructor() {
        Physiotherapist p = new Physiotherapist(301, "Dr. Nishant James", "22 Rticroft Close", "07452315985", Arrays.asList("Cardiorespiratory Physiotherapy", "Asthma"));
        Treatment t = new Treatment("Cardiorespiratory Physiotherapy", 60, p);

        assertEquals("Cardiorespiratory Physiotherapy", t.getTreatmentName());
        assertEquals(60, t.getDuration());
        assertEquals(p, t.getPhysiotherapist());
    }
}
