package com.sqli.test.elevators.factory;

import com.sqli.test.elevators.Elevator;
//Elevator Factory Implementation
public class ElevatorFactory implements  Factory{
	//return Elevator instance with giving Id and currentFloor
@Override
public Elevator getInstance(String id, int currentFloor) {
	
	return new Elevator(id, currentFloor);
}
}
