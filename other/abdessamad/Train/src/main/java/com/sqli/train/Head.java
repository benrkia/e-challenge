package com.sqli.train;

public class Head extends StaticWagon {

	private boolean isInFront;

	public void setInFront(boolean isInFront) {
		this.isInFront = isInFront;
	}

	@Override
	public String print() {
		if (isInFront) {
			return "<HHHH";
		}
		return "HHHH>";
	}

}
