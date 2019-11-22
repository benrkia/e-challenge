package com.sqli.echallenge.parking;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;


/**
 * The aim of this exercice is to fill a square shaped parking. Here are the representation rules : 
 * <ul>
 * <li>'=' is a pedestrian exit 
 * <li>'@' is a disabled-only empty bay 
 * <li>'U' is a non-disabled empty bay 
 * <li>'D' is a disabled-only occupied bay 
 * <li>Any other character but the above : the char representation of a parked vehicle for non-empty bays. 
 * <li>U, D, @ and = can be considered as reserved chars.
 *</ul>
 * Once an end of lane is reached, then the next lane is reversed (to represent the fact that cars need to turn around)
 */

public class ParkingTest
{
	//FIRST U PEDESTRIAN U EXIT U INDEX
    private static final int FIRSTUPEDESTRIANUEXITUINDEX = 8;
    private Parking parking;

    ///////////////////////////
    // Parking filling tests //
    ///////////////////////////

    /**
     * Setting up the parking for the rest of the tests For the remaining of the exercice, this parking will called
     * "The Parking" (case sensitive)
     */
    @Before
    public void setUp()
    {
        parking = new ParkingBuilder().withSquareSize(5).withPedestrianExit(FIRSTUPEDESTRIANUEXITUINDEX).withPedestrianExit(12)
                .withDisabledBay(5).withDisabledBay(10).build();
    }

    
    
    
    /**
     * The Parking has to look like this : 
     * UUUUU 
     * U=UU@
     * @U=UU 
     * UUUUU 
     * UUUUU
     */
    @Test
    public void testToString()
    {
        assertEquals("UUUUU\nU=UU@\n@U=UU\nUUUUU\nUUUUU", parking.toString());
        assertEquals(23, parking.getAvailableBays());
    }

    /**
     * When you park a car, you want it to be close to a pedestrian exit. So if the car C parks, it will be at the
     * position 7 of The Parking like so : 
     * UUUUU 
     * U=CU@
     * @U=UU 
     * UUUUU 
     * UUUUU 
     * There will also be 22 and not 23 empty spots after the car C has parked.
     */
    @Test
    public void testParkCarVehiculeTypeC()
    {
        assertEquals(7, parking.parkCar('C'));
        assertEquals(22, parking.getAvailableBays());
    }

    /**
     * Same goes for a car named M
     */
    @Test
    public void testParkCarVehiculeTypeM()
    {
        assertEquals(7, parking.parkCar('M'));
        assertEquals(22, parking.getAvailableBays());
    }

    /**
     * When parking, we look for the closest bay to any pedestrian exit. If more than one has been found, pick the
     * closest one to the entrance of The Parking. So if the car C parks, it will be at the position 7 of The Parking
     * like so : 
     * UUUUU 
     * U=CU@
     * @U=UU 
     * UUUUU 
     * UUUUU 
     * Then comes the M car : 
     * UUUUU 
     * M=CU@
     * @U=UU 
     * UUUUU 
     * UUUUU 
     * V : 
     * UUUUU 
     * M=CU@
     * @V=UU 
     * UUUUU 
     * UUUUU 
     * There will then be 20 bays remaining.
     */
    @Test
    public void testParkCarThreeVehicules()
    {
        assertEquals(7, parking.parkCar('C'));
        assertEquals(22, parking.getAvailableBays());

        assertEquals(9, parking.parkCar('M'));
        assertEquals(21, parking.getAvailableBays());

        assertEquals(11, parking.parkCar('V'));
        assertEquals(20, parking.getAvailableBays());

        assertEquals("UUUUU\nM=CU@\n@V=UU\nUUUUU\nUUUUU", parking.toString());
    }

    /**
     * Cars named D can only park on the disabled bays. The same rules apply to the D cars regarding trying to be close
     * to a pedestrian exit and to the entrance. So if the first D car parks, it will be at the position 10 of The
     * Parking like so : UUUUU U=UU@ DU=UU UUUUU UUUUU Then comes the second D car : 
     * UUUUU 
     * U=UUD 
     * DU=UU 
     * UUUUU 
     * UUUUU 
     * There will then be 21 bays remaining. 
     * The third D car has no more disabled bays left for it to park.
     */
    @Test
    public void testParkCarDisabled()
    {
        assertEquals(10, parking.parkCar('D'));
        assertEquals(22, parking.getAvailableBays());

        assertEquals(5, parking.parkCar('D'));
        assertEquals(21, parking.getAvailableBays());

        assertEquals(-1, parking.parkCar('D'));
        assertEquals(21, parking.getAvailableBays());

        assertEquals("UUUUU\nU=UUD\nDU=UU\nUUUUU\nUUUUU", parking.toString());
    }



    /**
     * After having parked all the cars, it is only natural to be able to unpark them.
     */
    @Test
    public void testUnparkCar()
    {
        final int firstCarBayIndex = parking.parkCar('C');
        assertTrue(parking.unparkCar(firstCarBayIndex));
        assertEquals(23, parking.getAvailableBays());
        assertFalse(parking.unparkCar(firstCarBayIndex));

        final int secondCarBayIndex = parking.parkCar('D');
        assertTrue(parking.unparkCar(secondCarBayIndex));
        assertEquals(23, parking.getAvailableBays());
        assertFalse(parking.unparkCar(secondCarBayIndex));

        assertFalse(parking.unparkCar(FIRSTUPEDESTRIANUEXITUINDEX));
    }

    /**
     * After we have parked all the cars, here's how The Parking should look : 
     * UUUUU 
     * C=CUD 
     * DM=MU 
     * UUUUU 
     * UUUUU 
     * The Parking has 17 bays left 
     * After we try to unpark the car at position 3, nothing should happen because there are no cars there. 
     * If we unpark the car at the position 13, The Parking then looks like : 
     * UUUUU 
     * C=CUD 
     * DM=UU 
     * UUUUU 
     * UUUUU 
     * The Parking has 18 bays left
     */
    @Test
    public void testUnparkCarToString()
    {
        assertEquals(7, parking.parkCar('C'));
        assertEquals(9, parking.parkCar('C'));
        assertEquals(11, parking.parkCar('M'));
        assertEquals(13, parking.parkCar('M'));
        assertEquals(10, parking.parkCar('D'));
        assertEquals(5, parking.parkCar('D'));
        assertEquals(-1, parking.parkCar('D'));

        assertFalse(parking.unparkCar(3));
        assertEquals("UUUUU\nC=CUD\nDM=MU\nUUUUU\nUUUUU", parking.toString());
        assertEquals(17, parking.getAvailableBays());

        assertTrue(parking.unparkCar(13));
        assertEquals("UUUUU\nC=CUD\nDM=UU\nUUUUU\nUUUUU", parking.toString());
        assertEquals(18, parking.getAvailableBays());

        assertEquals(13, parking.parkCar('V'));
    }
}
