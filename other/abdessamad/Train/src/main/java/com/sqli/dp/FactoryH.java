package com.sqli.dp;

import com.sqli.train.Head;
import com.sqli.train.Wagon;

public class FactoryH  implements IWagonFactory {

	@Override
	public Wagon getInstance() {
		return new Head();
	}


}
