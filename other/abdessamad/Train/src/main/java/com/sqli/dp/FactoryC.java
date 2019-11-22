package com.sqli.dp;

import com.sqli.train.Cargo;
import com.sqli.train.Wagon;

public class FactoryC implements IWagonFactory {

	@Override
	public Wagon getInstance() {
		return new Cargo();
	}

}
