package com.benrkia.metier;

import com.benrkia.entities.Elevator;

public interface ElevatorMetier {

    void switchElevatorDirection(Elevator elevator, String direction);

    int closestElevator(Elevator elevator1, Elevator elevator2, int numberOfFloors, int currentFloor);
}
