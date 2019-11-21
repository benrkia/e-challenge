package com.benrkia;

import com.benrkia.entities.Event;
import com.benrkia.entities.Hotel;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        int standardRooms = 12;
        int suites = 5;
        int apparts = 15;
        Hotel hotel = new Hotel(standardRooms, suites, apparts);
        Event event = new Event(hotel);

        event.register("TRINGA", "Mohammed", "Karim");
    }
}
