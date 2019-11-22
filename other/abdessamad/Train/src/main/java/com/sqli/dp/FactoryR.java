package com.sqli.dp;

import com.sqli.train.Restaurant;
import com.sqli.train.Wagon;

public class FactoryR  implements IWagonFactory {

	@Override
	public Wagon getInstance() {
	return new Restaurant();
	}

}
