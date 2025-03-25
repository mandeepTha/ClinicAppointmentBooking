package org.boostphysio.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Physiotherapist {
    private int id;
    private String physiotherapistName;
    private String physiotherapistAddress;
    private String physiotherapistPhone;
    private List<String> expertise;
    List <Date> timetable = new ArrayList<>();


    public Physiotherapist(int id, String PhysiotherapistName, String PhysiotherapistAddress, List<String> expertise) {
        this.id = id;
        this.physiotherapistName = PhysiotherapistName;
        this.physiotherapistAddress = PhysiotherapistAddress;
        this.expertise = expertise;
    }

    public List<Date> getTimetable() {
        return timetable;
    }

    public int getId() {
        return id;
    }
    public String getPhysiotherapistName() {
        return physiotherapistName;
    }
    public String getphysiotherapistAddress() {
        return physiotherapistAddress;
    }
    public List<String> getExpertise() {
        return expertise;
    }
    @Override
    public String toString() {
        return "Physiotherapist: " + getPhysiotherapistName() + " (ID: " + id + ")\n" +
                "Address: " + physiotherapistAddress + "\nPhone: " + physiotherapistPhone + "\n" +
                "Expertise: " + expertise + "\nWorking Hours: " + timetable;
    }
}
