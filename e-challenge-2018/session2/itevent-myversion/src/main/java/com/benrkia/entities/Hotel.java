package com.benrkia.entities;


import java.util.HashMap;
import java.util.Map;

public class Hotel {

    private int standards;
    private int suites;
    private int appartments;

    private int nextAvailableStandard, nextAvailableSuite, nextAvailableAppartment;

    Map<String, Guest> guests;

    public Hotel() {
        nextAvailableStandard = 1;
        nextAvailableSuite = 101;
        nextAvailableAppartment = 201;

        guests = new HashMap<>();
    }

    public Hotel(int standards, int suites, int apparts) {
        this();

        this.standards = standards;
        this.suites = suites;
        this.appartments = apparts;
    }

    public int getGuestRoomNumber(String guestName) {
        return guests.get(guestName).getRoomNumber();
    }

    public String checkAvailibility() {
        return "Standard rooms: " + standards + "|Suites: " + suites + "|Aparts: "+ appartments;
    }

    public void bookStandardRoom(String guestName) {
        if(standards == 0) {
            bookAppartmentRoom(guestName);
            return;
        }
        bookStandardRoom(guestName, nextAvailableStandard++);

        --standards;
    }

    void bookStandardRoom(String guestName, int firstAvailableRoom) {
        if(firstAvailableRoom>100)
            bookAppartmentRoom(guestName, firstAvailableRoom);

        Guest guest = new Guest(guestName,"Standard", firstAvailableRoom);

        guests.put(guestName, guest);
    }

    public void bookAppartmentRoom(String guestName) {
        bookAppartmentRoom(guestName, nextAvailableAppartment++);
        --appartments;
    }

    private void bookAppartmentRoom(String guestName, int firstAvailableRoom) {
        Guest guest = new Guest(guestName,"Apart", firstAvailableRoom);
        guests.put(guestName, guest);
    }

    public void bookSuiteRoom(String guestName) {
        Guest guest = new Guest(guestName,"Suite", nextAvailableSuite++);

        guests.put(guestName, guest);

        --suites;
    }

    public String getRoomFor(String guestName) {
        Guest guest = guests.get(guestName);
        StringBuilder room = new StringBuilder(guest.getRoomType()+" ");

        if(guest.getRoomType().equalsIgnoreCase("Standard"))
            room.append("room ");

        room.append("N°" + guest.getRoomNumber());

        return room.toString();
    }

    public int getStandards() {
        return standards;
    }
}
