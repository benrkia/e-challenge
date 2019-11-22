package com.benrkia.entities;

import com.benrkia.config.ElevatorDirection;

public class Elevator {

    private String id;
    private int currentFloor;
    private Building building;
    private ElevatorDirection direction;

    public Elevator(String id, int currentFloor, Building building) {
        this.id = id;
        this.currentFloor = currentFloor;
        this.building = building;
        this.direction = ElevatorDirection.DOWN;
    }

    public ElevatorDirection getDirection() {
        return direction;
    }

    public void setDirection(ElevatorDirection direction) {
        this.direction = direction;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }
}
