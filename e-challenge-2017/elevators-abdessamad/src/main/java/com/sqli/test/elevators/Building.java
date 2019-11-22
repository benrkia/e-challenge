package com.sqli.test.elevators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sqli.test.elevators.factory.ElevatorFactory;
import com.sqli.test.elevators.factory.Factory;
import com.sqli.test.elevators.metier.ElevatorMetierImpl;
import com.sqli.test.elevators.metier.IElevatorMetier;

public class Building {
	   private int numberOfFloors;
	    private List<Elevator> elevators= new ArrayList<>();
	    private Factory elevatorFactory= new ElevatorFactory();
	    private IElevatorMetier elevatorMetier;
    /**
     * @param numberOfFloors: the number of floors in the building
     * @param elevators: an array of descriptions of the elevators of the building. 
     *                   A description has the following format "[elevator_id]:[elevator_current_floor]".
	 *
     */
    public Building(int numberOfFloors, String... elevators) {
    	//Creating Elevators and adding then to the elevators list 
    	this.numberOfFloors=numberOfFloors;
    	
    	for(String elevator : elevators){
    		String [] elevatorIdandFloor=elevator.split(":");
    		String id=elevatorIdandFloor[0];
    		int currentFloor= Integer.parseInt(elevatorIdandFloor[1]);
    		this.elevators.add(this.elevatorFactory.getInstance(id, currentFloor));
    	}
    	
    	//Initialising the helper
    	this.elevatorMetier= new ElevatorMetierImpl(this.elevators);
    	
    }

    /**
     * Request an elevator at floor zero.
     * @return the id of the elevator that should serve the request.
     */
    public String requestElevator() {
    	//Get Never Used Elevators
    		List<Elevator> restingElevators = elevatorMetier.getRestingElevators();
    		//Get MovingDown Elevators and Add of Unused ones 
    		List<Elevator> movingDownAndRestingElevators= elevatorMetier.getMovingDownElevators();
    		movingDownAndRestingElevators.addAll(restingElevators);
    		//Sorting by currentFloor from the first to the top
    		Collections.sort(movingDownAndRestingElevators,
        			(a,b)-> a.getCurrentFloor().compareTo(b.getCurrentFloor()));
    		//Searching for the near Elevator of floor 0 excluding stopped ones
    		for(Elevator elevator : movingDownAndRestingElevators){
    			if(!elevator.isStopped()){
    				return elevator.getId();
    			}
    		}
    		
    		// if all elevators are moving Up getting them
    		List<Elevator> movingUpElevators = elevatorMetier.getMovingUpElevators();
    		//Sorting by currentFloor from the top to the first
    		Collections.sort(movingUpElevators,
    				(a,b)-> b.getCurrentFloor().compareTo(a.getCurrentFloor()));
    		/*Searching for the near Elevator of top floor excluding stopped ones
		* First one arrive to top floor will return first to the floor zero
		*/
    		for(Elevator elevator : movingUpElevators){
    			if(!elevator.isStopped()){
    				return elevator.getId();
    			}
    		}
    		
    	
    	return null;
    }

    /**
     * Request an elevator at floor indicate by the {@code floor} param.
     * @param floor : the floor where the request is made.
     * @return the id of the elevator that should serve the request.
     */
    public String requestElevator(int floor) {

    	
    	Map<Integer,Elevator > distances = new HashMap<>();
    	
    	List<Integer> dists =new ArrayList<>();
    	//Calculating distances between request floor and all elevators
    	for(Elevator elevator: this.elevators){
    		int dist=elevator.getCurrentFloor()-floor;
    		
    		if(dist<0) dist*=-1;
    		distances.put(dist, elevator);
    	}
    	
    	
    	dists.addAll(distances.keySet());
    	//Sorting Distances from the lower to higher 
    	Collections.sort(dists,(a,b)->a.compareTo(b));
    	
    	for(int i=0;i<dists.size();i++){
    		Elevator elevator=distances.get(dists.get(i));
    		int dist = elevator.getCurrentFloor()-floor;
    		/* Elevator can be near but not moving to direction of
		       requested floor should be excluded
	 		 * 
	 		 */
    		if( (dist<0 && !elevator.getDirection().equals("DOWN")) 
    				|| (dist>0 && !elevator.getDirection().equals("UP")) )
    			return elevator.getId();
    		
    			
    	}
    	return null;
    	
    }

    /**
     * Request the elevator with the id {@code elevatorId} to stop at the floor indicated by the {@code floor} param.
     * @param elevatorId : the id of the elevator to whom give the order.
     * @param floor : the floor at which the elevator should stop
     */
    public void stopAt(String elevatorId, int floor) {
    	for(Elevator elevator : this.elevators){
   		 if(elevator.getId().compareTo(elevatorId)==0){
   			 elevator.setCurrentFloor(floor);
   			 // turn stopped flag to true
   			 elevator.setStopped(true);
   			 // Changing automatically direction when achieving edges  
   			 if(floor==0) elevator.setDirection("UP");
   			 else if(floor==numberOfFloors) elevator.setDirection("DOWN");
   		 }
   	}
    }

    /**
     * Move the elevator with the id {@code elevatorId} in the direction indicated by the {@code direction} param.
     * @param elevatorId : the id of the elevator to move.
     * @param direction : the direction to go. Can be "UP" or "DOWN".
     */
    public void move(String elevatorId, String direction) {

    	
        for(Elevator elevator : this.elevators){
     	   if(elevator.getId().compareTo(elevatorId)==0){
     		   
     		 if(direction=="UP"){
     			//we cannot go more up when we achieve the top 
     			 if(elevator.getCurrentFloor()!=numberOfFloors ){
     				 elevator.setDirection(direction);
     			 }
     		 }else{ 
     			// we cannot go more down when we are in the zero floor
	 			 if(elevator.getCurrentFloor()!=0 ){
	 				 elevator.setDirection(direction);
	 			 }
     		 }
     	   }
     		   
        }

    } 

}
