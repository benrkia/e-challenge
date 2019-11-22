package com.sqli.echallenge.parking;

import java.util.Arrays;

/**
 * Builder class to get a parking instance
 */
public class ParkingBuilder {
	
	private char[] square;
	
	public char[] getSquare() {
		return square;
	}
	
    public ParkingBuilder withSquareSize(final int size) {
    	square = new char[size*size];
    	Arrays.fill(square, 'U');
    	return this;
    }

    public ParkingBuilder withPedestrianExit(final int pedestrianExitIndex) {
        square[pedestrianExitIndex] = '=';
        return this;
    }

    public ParkingBuilder withDisabledBay(final int disabledBayIndex) {
        square[disabledBayIndex] = '@';
        return this;
    }

    public Parking build() {
    	return new Parking(this);
    }
}
