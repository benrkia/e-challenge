package com.sqli.test.romanrunner.entities;

import java.util.ArrayList;
import java.util.List;

public class Circenses {
	private List<String> chemin = new ArrayList<>();
	public List<String> getChemin() {
		return chemin;
	}
	private String cheminString;
	private Player player;
	public Circenses() {
		
	}
	public void setChemin(List<String> chemin) {
		this.chemin = chemin;
	}
	public String draw() {
		cheminString="";
		chemin.forEach((e) -> {
			cheminString+=e+"\n";
		});
		cheminString=cheminString.substring(0, cheminString.length()-1);
		
		return cheminString;
	}
}
