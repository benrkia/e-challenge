package com.sqli.test.elevators;
//Elevator Entity
public class Elevator {
	
	private String id;
	private Integer currentFloor;
	private String direction="";
	// True when call stopAt(int floor)
	private boolean stopped;
	
	public Elevator(String id, int currentFloor) {
		
		this.id = id;
		this.currentFloor = currentFloor;
	}
	public Integer getCurrentFloor() {
		return currentFloor;
	}
	public void setCurrentFloor(int currentFloor) {
		this.currentFloor = currentFloor;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.stopped=false;
		this.direction = direction;
	}
	public boolean isStopped() {
		return stopped;
	}
	public void setStopped(boolean stopped) {
		this.stopped = stopped;
	}
	public String getId() {
		return id;
	}
	
	public boolean isResting(){
		return this.direction=="" ? true:false;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.id+" | "+this.currentFloor+" | "+this.direction;
	}
	

}
