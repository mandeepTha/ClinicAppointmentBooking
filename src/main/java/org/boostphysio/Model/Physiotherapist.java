package org.boostphysio.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Physiotherapist {
    private int id;
    private String name;
    private String address;
    private String phoneNumber;
    private List<String> expertise;
    private List<Treatment> timetable;


    public Physiotherapist(int id, String name, String address, String phoneNumber, List<String> expertise) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.expertise = expertise;
        this.timetable = new ArrayList<>();
    }
public void addTimetable(Treatment timetable) {
        this.timetable.add(timetable);
}

    public int getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getName() {
        return name;
    }

    public List<String> getExpertise() {
        return expertise;
    }

    public List<Treatment> getTimetable() {
        return timetable;
    }

    @Override
    public String toString() {
        return "Physiotherapist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", expertise=" + expertise +
                '}';
    }
}
