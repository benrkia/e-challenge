package com.sqli.test.romanrunner;

import java.util.ArrayList;
import java.util.List;

import com.sqli.test.romanrunner.entities.Circenses;
public class CircensesBuilder implements Builder{
	List<String> chemin=new ArrayList<>();
	private String position;

	public CircensesBuilder() {
		addEmptySlot();
		this.position="left";
	}
    public CircensesBuilder addCoin() {
        if(position.equals("left"))
        	this.chemin.add("|o |");
        else
        	this.chemin.add("| o|");
        return this;
    }

    public CircensesBuilder addEmptySlot() {
    	this.chemin.add("|  |");
        return this;
    }

    public CircensesBuilder addObstacle() {
    	if(position.equals("left"))
    		this.chemin.add("|_ |");
    	else
    		this.chemin.add("| _|");
        return this;
    }

    public Circenses build() {
    	this.chemin.add("|##|");
        List<String> switchedChemin = new ArrayList<>();
        int i=chemin.size()-1;
        
        while(i>=0){
        	
        	switchedChemin.add(chemin.get(i));
        	i--;
        }
        Circenses circenses= new Circenses();
        circenses.setChemin(switchedChemin);
       
        return circenses;
    }

    public CircensesBuilder right() {
        this.position="right";
        return this;
    }

    public CircensesBuilder left() {
        this.position="left";
        return this;
    }

}
