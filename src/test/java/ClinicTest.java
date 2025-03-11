import org.boostphysio.Model.*;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class ClinicTest {
    @Test
    public void testaddPatient() {
        Clinic clinic = new Clinic();
        Patient patient = new Patient(1, "Jason Becker", "Welling, 34 Rail road","0739012546");
        clinic.addPatient(patient);
        assertEquals(1, clinic.getPatients().size());

    }
    @Test
    public void testaddPhysiotherapist(){
        Clinic clinic = new Clinic();
        Physiotherapist physio = new Physiotherapist(1,"Dr.Harvey", Arrays.asList("Physiotherapy"));
        clinic.addPhysiotherapist(physio);
        assertEquals(1,clinic.getPhysiotherapists().size() );
    }

    @Test
    public void testbookAppointment(){
        Clinic clinic = new Clinic();
        Patient patient = new Patient(1,"Jason Becker", "Welling, 34 Rail road","0739012546");
        Physiotherapist physiotherapist = new Physiotherapist(1,"Dr.Harvey", Arrays.asList("Physiotherapy"));
        Treatment treatment = new Treatment("Massage", 60, 50 );
        Appointment appointment = new Appointment(LocalDateTime.of(2025,4,10,15,0),"Booked",patient,physiotherapist,treatment);

        clinic.bookAppointment(appointment);
        assertEquals(1, clinic.getAppointments().size());
    }

    @Test
    public void testcancelAppointment(){
        Clinic clinic = new Clinic();
        Patient patient = new Patient(1,"Jason Becker", "Welling, 34 Rail road","0739012546");
        Physiotherapist physiotherapist = new Physiotherapist(1,"Dr.Harvey", Arrays.asList("Physiotherapy"));
        Treatment treatment = new Treatment("Massage", 60, 50 );
        Appointment appointment = new Appointment(LocalDateTime.of(2025,4,10,15,0),"Booked",patient,physiotherapist,treatment);

        clinic.cancelAppointment(appointment);
        appointment.Cancel();
        assertEquals("Cancelled",appointment.getStatus());
    }
    @Test
    public void testGetAppointments(){
        Clinic clinic = new Clinic();
        Patient patient = new Patient(1,"Jason Becker", "Welling, 34 Rail road","0739012546");
        Physiotherapist physiotherapist = new Physiotherapist(1,"Dr.Harvey", Arrays.asList("Physiotherapy"));
        Treatment treatment = new Treatment("Massage", 60, 50 );
        Appointment appointment = new Appointment(LocalDateTime.of(2025,4,10,15,0),"Booked",patient,physiotherapist,treatment);

        clinic.bookAppointment(appointment);
        clinic.GenerateReport();
        assertEquals(1,clinic.getAppointments().size());
    }
}
