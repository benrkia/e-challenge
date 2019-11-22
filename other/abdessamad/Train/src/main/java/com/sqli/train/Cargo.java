package com.sqli.train;

public class Cargo extends DynamicWagon {


	@Override
	public String print() {
		/* isEmpty methode is provided by class DynamicWagon*/
		if (isEmpty()) {
			return "|____|";
		}
		return "|^^^^|";
	}

}
