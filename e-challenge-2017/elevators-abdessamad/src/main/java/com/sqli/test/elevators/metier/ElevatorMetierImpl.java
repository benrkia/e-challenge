package com.sqli.test.elevators.metier;

import java.util.ArrayList;
import java.util.List;

import com.sqli.test.elevators.Elevator;
//Helper implementation
public class ElevatorMetierImpl  implements IElevatorMetier{

	private List<Elevator> elevators;
	
	public ElevatorMetierImpl(List<Elevator> elevators) {
		this.elevators=elevators;
	}
	//Get all Elevators moving DOWN
	@Override
	public List<Elevator> getMovingDownElevators() {
		List<Elevator> movingDownElevators = new ArrayList<>();
		for(Elevator elevator : this.elevators){
			if(elevator.getDirection().compareTo("DOWN")==0){
				movingDownElevators.add(elevator);
			}
			
		}
		return movingDownElevators;
	}
	//Get all Elevators never moved after creation
	@Override
	public List<Elevator> getRestingElevators() {
		List<Elevator> restingElevators = new ArrayList<>();
		for(Elevator elevator : this.elevators){
			if(elevator.isResting()){
				restingElevators.add(elevator);
			}
			
		}
		return restingElevators;
	}
	//Get all Elevators moving UP
	@Override
	public List<Elevator> getMovingUpElevators() {

		List<Elevator> movingUpElevators = new ArrayList<>();
		for(Elevator elevator : this.elevators){
			if(elevator.getDirection().compareTo("UP")==0){
				movingUpElevators.add(elevator);
			}
			
		}
		return movingUpElevators;
	}
}
