package com.sqli.echallenge.parking;

/**
 * Handles the parking mechanisms: park/unpark a car (also for disabled-only bays) and provides a string representation of its state.
 */
public class Parking {
	
	private char[] square;
	
    public Parking(ParkingBuilder parkingBuilder) {
    	square = parkingBuilder.getSquare();
    }

	/**
     * @return the number of available parking bays left
     */
    public int getAvailableBays() {
    	int availableBays = 0;
    	for(int i = 0; i< square.length; i++){
    		if(square[i] == 'U' || square[i] == '@') availableBays++;
    	}
    	return availableBays;
    }

    /**
     * Park the car of the given type ('D' being dedicated to disabled people) in closest -to pedestrian exit- and first (starting from the parking's entrance)
     * available bay. Disabled people can only park on dedicated bays.
     *
     *
     * @param carType
     *            the car char representation that has to be parked
     * @return bay index of the parked car, -1 if no applicable bay found
     */
    
    public int parkCar(final char carType) {
    	String str = String.valueOf(square);
    	int value = 0;
    	if(str.contains("=U")){
    		value = str.indexOf("=U")-1;
    		
    	}else if(str.contains("U=")){
    		value = str.indexOf("U=");
    	}
    	square[value] = carType;
    	return value;
    }

    /**
     * Unpark the car from the given index
     *
     * @param index
     * @return true if a car was parked in the bay, false otherwise
     */
    public boolean unparkCar(final int index) {
    	square[index] = 'U';
    	return false;
    }

    /**
     * Print a 2-dimensional representation of the parking with the following rules:
     * <ul>
     * <li>'=' is a pedestrian exit
     * <li>'@' is a disabled-only empty bay
     * <li>'U' is a non-disabled empty bay
     * <li>'D' is a disabled-only occupied bay
     * <li>the char representation of a parked vehicle for non-empty bays.
     * </ul>
     * U, D, @ and = can be considered as reserved chars.
     *
     * Once an end of lane is reached, then the next lane is reversed (to represent the fact that cars need to turn around)
     *
     * @return the string representation of the parking as a 2-dimensional square. Note that cars do a U turn to continue to the next lane.
     */
    
    
    
    
    /**
     * The Parking has to look like this : 
     * UUUUU 
     * U=UU@
     * @U=UU 
     * UUUUU 
     * UUUUU
     */
//    @Test
//    public void testToString()
//    {
//        assertEquals("UUUUU\nU=UU@\n@U=UU\nUUUUU\nUUUUU", parking.toString());
//        assertEquals(23, parking.getAvailableBays());
//    }
//    
    
    ////UUUUU@UU=U@U=UUUUUUUUUUUU
    
    @Override
    public String toString() {
    	int squareSize = (int) Math.sqrt(square.length);
    	String str = String.valueOf(square);
    	
    	StringBuilder sb = new StringBuilder();
    	
    	for(int i = 0; i< squareSize; i++){
    		if(i % 2 == 0){
    			sb.append(str.substring(i*squareSize, squareSize+i*squareSize));
    		}else{
    			sb.append(new StringBuilder(str.substring(i*squareSize, squareSize+i*squareSize)).reverse().toString());
    		}
    		sb.append("\n");
    	}
    	//Delete last \n
    	return sb.toString().substring(0, sb.toString().length()-1);
    }

}
