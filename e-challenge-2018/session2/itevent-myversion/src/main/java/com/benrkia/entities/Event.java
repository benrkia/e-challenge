package com.benrkia.entities;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Event {

    private Hotel hotel;

    Map<String, Set<String>> attendees;

    public Event() {
        attendees = new HashMap<>();
        attendees.put("TRINGA", new TreeSet<String>());
        attendees.put("CONF", new TreeSet<String>());
        attendees.put("DEEP DIVE", new TreeSet<String>());
        attendees.put("STAFF", new TreeSet<String>());
        attendees.put("SPEAKER", new TreeSet<String>());
    }

    public Event(Hotel hotel) {
        this();
        this.hotel = hotel;
    }

    private int getFirstAvailableRoom(Set<String> names) {
        int firstAvailableRoom = Integer.MAX_VALUE;

        for(String name:names) {
            int roomNumber = hotel.getGuestRoomNumber(name);
            if(roomNumber<firstAvailableRoom) {
                firstAvailableRoom = roomNumber;
            }
        }

        return firstAvailableRoom;
    }

    private int getAvailableStandardRoom(String passName) {
        int availableStandard = hotel.getStandards();
        if(passName.equalsIgnoreCase("DEEP DIVE")) {
            availableStandard += attendees.get("CONF").size();
        }
        else if(passName.equalsIgnoreCase("CONF")) {
            availableStandard += attendees.get("DEEP DIVE").size();
        }

        return availableStandard;
    }

    private void registerSpeaker(String attendeeName) {
        hotel.bookSuiteRoom(attendeeName);
    }

    private void registerStandardRoom(String attendeeName, String passName) {
        Set<String> passNames = attendees.get(passName);
        int firstAvailableRoom = getFirstAvailableRoom(passNames);

        if(firstAvailableRoom != Integer.MAX_VALUE) {
            hotel.bookStandardRoom(attendeeName, firstAvailableRoom);
        }
        else {
            hotel.bookStandardRoom(attendeeName);
        }
    }

    private boolean registerAppartmentRoom(String passName, String attendeeName) {
        Set<String> passList = attendees.get(passName);

        boolean booked = passList.add(attendeeName);

        if(booked) {
            hotel.bookAppartmentRoom(attendeeName);
        }

        return booked;
    }

    public boolean register(String passName, String attendeeName) {
        Set<String> passList = attendees.get(passName);

        boolean booked = passList.add(attendeeName);

        if(!booked) return booked;

        switch (passName) {
            case "SPEAKER":
                registerSpeaker(attendeeName);
                break;
            case "DEEP DIVE":
                registerStandardRoom(attendeeName, "CONF");
                break;
            case "CONF":
                registerStandardRoom(attendeeName, "DEEP DIVE");
                break;
            default:
                hotel.bookStandardRoom(attendeeName);
                break;
        }

        return booked;
    }

    public boolean register(String passName, String ...attendeeNames) {
        boolean booked = true;

        if(!passName.equalsIgnoreCase("SPEAKER")) {
            int numberOfAvailableStandard = getAvailableStandardRoom(passName);
            if(numberOfAvailableStandard < attendeeNames.length) {
                for (String attendeeName : attendeeNames)
                    booked |= registerAppartmentRoom(passName, attendeeName);
                return booked;
            }
        }

        for(String attendeeName:attendeeNames)
            booked |= register(passName, attendeeName);

        return booked;
    }
}
