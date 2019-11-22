package com.sqli.echallenge.parking;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


/**
 * The aim of this exercice is to build a square shaped parking. Here are the representation rules :
 * <ul>
 * <li>'=' is a pedestrian exit
 * <li>'@' is a disabled-only empty bay
 * <li>'U' is a non-disabled empty bay
 * <li>'D' is a disabled-only occupied bay
 * <li>Any other character but the above : the char representation of a parked vehicle for non-empty bays.
 * <li>U, D, @ and = can be considered as reserved chars.
 * </ul>
 * Once an end of lane is reached, then the next lane is reversed (to represent the fact that cars need to turn around)
 */
public class ParkingBuilderTest
{

	////////////////////////////
	// Parking building tests //
	////////////////////////////

	/**
	 * Building a 4 by 4 square shaped parking with 16 bays available: 
	 * UUUU 
	 * UUUU 
	 * UUUU 
	 * UUUU
	 */
	@Test
	public void testBuildBasicParking()
	{
		final Parking p = new ParkingBuilder().withSquareSize(4).build();
		assertEquals(16, p.getAvailableBays());
	}

	/**
	 * Building a 3 by 3 square shaped parking with 8 bays available: 
	 *  UUU
	 *  =UU
	 *  UUU
	 */
	@Test
	public void testBuildParkingWithPedestrianExit()
	{
		final Parking p = new ParkingBuilder().withSquareSize(3).withPedestrianExit(5).build();
		assertEquals(8, p.getAvailableBays());
	}

	/**
	 * Building a 2 by 2 square shaped parking with 4 bays available: 
	 * UU
	 * U@
	 */
	@Test
	public void testBuildParkingWithDisabledSlot()
	{
		final Parking p = new ParkingBuilder().withSquareSize(2).withDisabledBay(2).build();
		assertEquals(4, p.getAvailableBays());
	}
}
