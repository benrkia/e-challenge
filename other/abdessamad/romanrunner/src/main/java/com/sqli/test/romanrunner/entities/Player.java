package com.sqli.test.romanrunner.entities;

public abstract class Player {
	protected Circenses circenses;
	protected String name;
	protected char simpleName;
	protected int score;
	protected String track;
	protected int position;
	protected boolean onCoin;
	
	
	public boolean isOnCoin() {
		return onCoin;
	}
	public void setOnCoin(boolean onCoin) {
		this.onCoin = onCoin;
	}
	public Player(String name) {
		this.name=name;
		this.simpleName=name.toUpperCase().charAt(0);
		this.track="left";
		
	}
	public void startGame(Circenses circenses) {
		this.circenses=circenses;
		circenses.getChemin().set(circenses.getChemin().size()-1, "|"+this.simpleName+" |");
		this.position=this.circenses.getChemin().size()-1;
		
		
	}
	
	public int score(){
		return this.score;
	}
	public abstract Player forward();
	public Player right() {
		this.track="right";
		if(this.position==this.circenses.getChemin().size()-1)
		this.circenses.getChemin().set(this.circenses.getChemin().size()-1, "|@"+this.simpleName+"|");
		return this;
	}
	public Player left() {
		this.track="left";
		this.circenses.getChemin().set(position, "|"+this.simpleName+" |");
		return this;
	}
	
}
