package com.sqli.dp;

import com.sqli.train.Passenger;
import com.sqli.train.Wagon;

public class FactoryP implements IWagonFactory {

	@Override
	public Wagon getInstance() {
		return new Passenger();
	}

}
