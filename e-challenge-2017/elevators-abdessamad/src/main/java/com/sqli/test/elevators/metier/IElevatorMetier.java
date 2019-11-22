package com.sqli.test.elevators.metier;

import java.util.List;

import com.sqli.test.elevators.Elevator;
//Interface helper 
public interface IElevatorMetier {
	public List<Elevator> getRestingElevators();
	public List<Elevator> getMovingUpElevators();
	public List<Elevator> getMovingDownElevators();
	
}
