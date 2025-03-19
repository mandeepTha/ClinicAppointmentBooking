package org.boostphysio.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Physiotherapist {
    private int id;
    private String PhysiotherapistName;
    private String PhysiotherapistAddress;
    private String PhysiotherapistPhone;
    private List<String> expertise;
    List <Treatment> timetable = new ArrayList<>();


    public Physiotherapist(int id, String PhysiotherapistName, String PhysiotherapistAddress, List<String> expertise) {
        this.id = id;
        this.PhysiotherapistName = PhysiotherapistName;
        this.PhysiotherapistAddress = PhysiotherapistAddress;
        this.expertise = expertise;
    }
    public void addTreatment(Treatment treatment) {
        timetable.add(treatment);
    }
    public int getId() {
        return id;
    }
    public String getPhysiotherapistName() {
        return PhysiotherapistName;
    }
    public String getPhysiotherapistAddress() {
        return PhysiotherapistAddress;
    }
    public List<String> getExpertise() {
        return expertise;
    }
    @Override
    public String toString() {
        return "Physiotherapist: " + getPhysiotherapistName() + " (ID: " + id + ")\n" +
                "Address: " + PhysiotherapistAddress + "\nPhone: " + PhysiotherapistPhone + "\n" +
                "Expertise: " + expertise + "\nWorking Hours: " + timetable;
    }
}
