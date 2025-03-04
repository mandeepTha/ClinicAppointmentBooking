package org.boostphysio.Model;

public class Patient {
    private int id;
    private String PatientFullName;
    private String address;
    private String phoneNumber;

    public Patient(int id, String fullName, String address, String phone) {
        this.id = id;
        this.PatientFullName = fullName;
        this.address = address;
        this.phoneNumber = phone;
    }
    public String getPatientFullName() {
        return PatientFullName;
    }

    public int getId(){
        return id;
    }

    public void BookAppointment(Appointment appointment) {
        System.out.println(PatientFullName + "Booked an appointment at: " + appointment);
    }

    public void CancelAppointment(Appointment appointment) {
        System.out.println(PatientFullName + "Cancelled an appointment: " + appointment);
    }
}
