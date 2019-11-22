package com.benrkia.entities;

import com.benrkia.config.ElevatorDirection;
import com.benrkia.metier.ElevatorMetier;
import com.benrkia.metier.ElevatorMetierImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Building {

    private int floors;
    private List<Elevator> elevators;
    ElevatorMetier elevatorMetier;

    public Building(int floors) {
        this.floors = floors;
        elevators = new ArrayList<>();
        elevatorMetier = new ElevatorMetierImpl();
    }

    public Building(int numberOfFloors, String ...elevatorDescriptions) {
        this(numberOfFloors);

        Arrays.stream(elevatorDescriptions).forEach(elevatorDescription -> {
            String id = elevatorDescription.split(":")[0];
            int currentFloor = Integer.parseInt(elevatorDescription.split(":")[1]);

            Elevator elevator = new Elevator(id, currentFloor, this);
            addElevator(elevator);
        });
    }

    public String requestElevator() {

        return requestElevator(0);

    }

    public String requestElevator(int requestFloor) {

        Elevator toBeRequestedElevator = elevators.stream()
                .min((elevator1, elevator2) -> elevatorMetier.closestElevator(elevator1, elevator2, floors, requestFloor))
                .orElse(null);

        return toBeRequestedElevator.getId();

    }

    public void move(String elevatorId, String direction) {
        Elevator elevator = elevators.stream()
                .filter(e -> e.getId().equals(elevatorId))
                .findFirst()
                .get();

        elevatorMetier.switchElevatorDirection(elevator, direction);
    }

    public void addElevator(Elevator elevator) {
        elevators.add(elevator);
    }

    public void removeElevator(Elevator elevator) {
        elevators.remove(elevator);
    }

    public int getFloors() {
        return floors;
    }

    public void setFloors(int floors) {
        this.floors = floors;
    }
}
