package com.sqli.train;
/* We create two wlasses in order to respect the liskov principle,
 * Only Head class need empty method 
 */
public abstract class DynamicWagon extends Wagon{
	private boolean empty = true;
	
	public DynamicWagon() {
	}

	public void setEmpty(boolean empty) {
		this.empty = empty;
	}
	
	public boolean isEmpty() {
		return empty;
	}
}
