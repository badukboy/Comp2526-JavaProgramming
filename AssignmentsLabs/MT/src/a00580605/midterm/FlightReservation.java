/*
 * Created on Oct 15, 2006
 */
package a00580605.midterm;

import a00580605.midterm.data.*;

/**
 * @author Paul
 */
public class FlightReservation {

	//initialization data
	private final String[][] GUEST_DATA = { { "Daffy", "Duck", "4565 1236 8895 7891", "quack@me.up" },
											{ "Porky", "Pig", "4002 2268 5394 4859", "oinker@oink.ca" }, 
											{ "Sylvester", "Cat", "3569 558 665 1587", "meow@purr.mew" }, 
											{ "Yosemite", "Sam", "456 687 898 881 127", "sam@i.am" },
											{ "Foghorn", "Leghorn", "4546 7897 213 1594", "now_see@here.boy" }, 
											{ "Bugs", "Bunny", "5866 544 8914 123", "whats@up.doc" } };



	public static void main(String[] args) {
		new FlightReservation().test();
	}


	public void test() {	
		
		Aircraft aircraft = Aircraft.getTheInstance();
		aircraft.setAircraftType("Gas Bag");
		
		Flight flight = new Flight(aircraft, "GB101");
		flight.setAmount(75);
		
		Passenger[] passengers = new Passenger[GUEST_DATA.length];

		for (int i = 0; i < GUEST_DATA.length; i++) {

			//call Passenger constructor to create one Passenger
			passengers[i] = new Passenger(GUEST_DATA[i]);
			//add the above Passenger to the flight list
			flight.reserve(passengers[i]);
			
		}
		
		//upgrading from "Economy" to "First Class"
		passengers[0].setStatus(true);
		passengers[4].setStatus(true);
		
		System.out.println();
		//one Passenger cancels their flight
		flight.cancel(passengers[2]);
		//Display all flight info including Passenger list
		flight.displayFlightInfo();			
	}
}
