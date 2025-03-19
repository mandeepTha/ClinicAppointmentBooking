package org.boostphysio.Model;

public class Patient {
    private int id;
    private String PatientName;
    private String PatientAddress;
    private String PatientPhoneNumber;

    public Patient(int id, String PatientName, String PatientAddress, String PatientPhoneNumber) {
        this.id = id;
        this.PatientName = PatientName;
        this.PatientAddress = PatientAddress;
        this.PatientPhoneNumber = PatientPhoneNumber;
    }
    public String getPatientName() {
        return PatientName;
    }

    public int getId(){
        return id;
    }
    public String getPatientAddress() {
        return PatientAddress;
    }
    public String getPatientPhoneNumber() {
        return PatientPhoneNumber;
    }
    @Override
    public String toString() {
        return "Patient: " + PatientName + " (ID: " + id + ")\n" +
                "Address: " + PatientAddress + "\nPhone: " + PatientPhoneNumber;
    }






}
