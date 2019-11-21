package com.benrkia.entities;

public class Guest {

    private String name;
    private String roomType;
    private int roomNumber;

    public Guest(String name, String roomType, int roomNumber) {
        this.name = name;
        this.roomType = roomType;
        this.roomNumber = roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }
}
