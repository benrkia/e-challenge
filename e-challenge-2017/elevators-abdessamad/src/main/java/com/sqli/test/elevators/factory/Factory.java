package com.sqli.test.elevators.factory;

import com.sqli.test.elevators.Elevator;
//Factory of Elevator
public interface Factory {
	public Elevator getInstance(String id,int currentFloor);
}
