package com.sqli.train;

import java.util.ArrayList;
import java.util.List;

import com.sqli.dp.WagonProducer;

public class Train {

	private List<Wagon> wagons = new ArrayList<Wagon>();
	
	public Train(String structure) {
		char chars[] = structure.toCharArray();
		for (char c : chars) {
			Wagon wagon = new WagonProducer().create(c);
			add(wagon);
		}
	}

	public String print() {
		
		/* By default we have all time head in front of a train
		 * We add this test in order to manage all cases
		 */
		if(WagonProducer.isHead(get(0))){
			((Head) wagons.get(0)).setInFront(true);
		}
		
		/*
		 * There is another solution to escape the case and the operator instance of
		 * by adding an index to each wagon
		 * but class in oop must contains only pertinent attribute
		 */
		if(WagonProducer.isHead(get(wagons.size() - 1))){
			((Head) wagons.get(wagons.size() - 1)).setInFront(false);
		}
		
		/*
		 * it is highly recommended to use StringBuffer for appending data
		 */
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < wagons.size(); i++) {
			Wagon current = wagons.get(i);
			buffer.append(current.print() + "::");
		}
		
		String value = buffer.toString();
		return value.substring(0, value.length()-2);
	}

	public void detachEnd() {
		remove(wagons.size()-1);
	}

	public void detachHead() {
		remove(0);
	}

	public boolean fill() {
		boolean test = false;
		for (Wagon wagon : wagons) {
			/* we fill the first empty cargo */
			if(wagon instanceof DynamicWagon && ((DynamicWagon) wagon).isEmpty()){
				((DynamicWagon) wagon).setEmpty(false);
				test = true;
				break;
			}
		}
		return test;
	}
	
	/* Methodes for manage the list of wagons */
	public Wagon get(int index) {
		return wagons.get(index);
	}
	
	public void add(Wagon wagon) {
		wagons.add(wagon);
	}
	
	public void remove(int index) {
		wagons.remove(index);
	}
	
}
