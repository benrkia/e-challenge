package com.sqli.test.romanrunner;

import com.sqli.test.romanrunner.entities.Circenses;

public interface Builder {
	
	public CircensesBuilder addCoin();
	public CircensesBuilder addEmptySlot();
	public CircensesBuilder addObstacle();
	public Circenses build();
	
}
