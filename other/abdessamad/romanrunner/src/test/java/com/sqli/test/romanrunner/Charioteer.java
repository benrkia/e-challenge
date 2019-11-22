package com.sqli.test.romanrunner;

import com.sqli.test.romanrunner.entities.Player;

public class Charioteer extends Player {
	
	public Charioteer(String name) {
		super(name);
	}

	@Override
	public Player forward() {
		
		
		if(this.position==this.circenses.getChemin().size()-1){
			
			this.circenses.getChemin().set(this.circenses.getChemin().size()-1, "|@ |");
			
		}
		
			
			String slot=this.circenses.getChemin().get(this.position-1);
			
			
			switch (slot){
			case "|  |" : {
				if(this.track.equals("left")){
					this.circenses.getChemin().set(this.position-1, "|"+this.simpleName+" |");
				
				}
				else{
					this.circenses.getChemin().set(this.position-1, "| "+this.simpleName+"|");
				}
				break;
					}
			case "|o |":{
				if(this.track.equals("left")){
					this.circenses.getChemin().set(this.position-1, "|"+this.simpleName+" |");
					this.score+=10;
					
				
				}
				else{
					this.circenses.getChemin().set(this.position-1, "|o"+this.simpleName+"|");
				}
				this.setOnCoin(true);
				break;
			}
			case "|##|":{
				if(this.track.equals("left")){
					this.circenses.getChemin().set(this.position-1, "|"+this.simpleName+"#|");
				
				}
				else{
					this.circenses.getChemin().set(this.position-1, "|#"+this.simpleName+"|");
				}
				this.score+=100;
				
				break;
			}
			default:break;
			}
			
			if(this.position!=this.circenses.getChemin().size()-1){
			if(this.isOnCoin())
				if(this.track.equals("left"))
					this.circenses.getChemin().set(this.position, "|x |");
				else
					this.circenses.getChemin().set(this.position, "| x|");
			else
				this.circenses.getChemin().set(this.position, "|  |");
			
			}
		this.position--;
		
		
		return this;
	}

}
