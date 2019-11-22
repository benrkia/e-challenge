package com.benrkia.metier;

import com.benrkia.config.ElevatorDirection;
import com.benrkia.entities.Elevator;

import java.util.Arrays;

public class ElevatorMetierImpl implements ElevatorMetier {

    @Override
    public void switchElevatorDirection(Elevator elevator, String direction) {

        switch (direction) {
            case "UP":
                elevator.setDirection(ElevatorDirection.UP);
                break;
            case "DOWN":
                elevator.setDirection(ElevatorDirection.DOWN);
                break;
        }

    }

    @Override
    public int closestElevator(Elevator elevator1, Elevator elevator2, int numberOfFloors, int currentFloor) {
        int firstElevatorDistance = distanceToElevator(elevator1, numberOfFloors, currentFloor);
        int secondElevatorDistance = distanceToElevator(elevator2, numberOfFloors, currentFloor);

        return Integer.compare(firstElevatorDistance, secondElevatorDistance);
    }

    private int distanceToElevator(Elevator elevator, int numberOfFloors, int currentFloor) {
        int distance;

        if(elevator.getCurrentFloor() == currentFloor) return 0;

        if(elevator.getDirection() == ElevatorDirection.DOWN){

            if (elevator.getCurrentFloor() > currentFloor)
                distance = Math.abs(elevator.getCurrentFloor() - currentFloor);
            else
                distance = elevator.getCurrentFloor() + currentFloor;
        }
        else {
            if (elevator.getCurrentFloor() < currentFloor)
                distance = Math.abs(elevator.getCurrentFloor() - currentFloor);
            else
                distance = Math.abs(2*numberOfFloors - elevator.getCurrentFloor() - currentFloor);
        }


        return distance;
    }

}
