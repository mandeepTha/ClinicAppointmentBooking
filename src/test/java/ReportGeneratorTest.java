import org.boostphysio.Controller.DataInitializer;
import org.boostphysio.Controller.ReportGenerator;

import static org.junit.jupiter.api.Assertions.*;

import org.boostphysio.Model.*;
import org.junit.jupiter.api.Test;

import java.util.List;



public class ReportGeneratorTest {
    @Test
    void testViewAvailableAppointments() {
        ReportGenerator rg = new ReportGenerator();
        List<Appointment> appointments = DataInitializer.initializeAppointments();
        rg.viewAvailableAppointments(appointments);
        assertNotNull(appointments);
    }

    @Test
    void testGenerateReport() {
        ReportGenerator rg = new ReportGenerator();
        List<Appointment> appointments = DataInitializer.initializeAppointments();
        List<Physiotherapist> physios = DataInitializer.initializePhysiotherapists();
        rg.generateReport(appointments, physios);
        assertNotNull(appointments);
    }
}
