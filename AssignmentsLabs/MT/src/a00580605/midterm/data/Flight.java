package a00580605.midterm.data;

import java.util.ArrayList;
import java.util.Collection;

public class Flight implements PassengerBooking{
	private double amount;
	private Collection<Passenger> passCollection;
	private Aircraft aircraft;
	private String flightNumber;
		
	public Flight(Aircraft aircraft, String string) {
		passCollection = new ArrayList<Passenger>();
		this.aircraft = aircraft;
		this.flightNumber = string;
	}

	public void setAmount(double d) {
		this.amount = d;		
	}
	
	public double getAmount() {
		return this.amount;		
	}

	public void reserve(Passenger passenger) {
		passCollection.add(passenger);
		System.out.println(passenger.getFirstName() + " " + passenger.getLastName() + " has BOOKED their reservation for the flight.");
	}

	public void cancel(Passenger passenger) {
		for(Passenger p : passCollection){
			if(p.getFirstName() == passenger.getFirstName()){
				if(p.getLastName() == passenger.getLastName()){
					passCollection.remove(p);
					System.out.println(p.getFirstName() + " " + p.getLastName() + " has CANCELLED their reservation for the flight.\n");
					break;
				}
			}
		}
	}
	
	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNo) {
		this.flightNumber = flightNo;
	}
	
	public void displayFlightInfo() {
		System.out.println("Flight " + getFlightNumber() + " in the " + aircraft.getAircraftType() + " is ready for boarding.");
		System.out.println("Here is the final boarding list for this flight:\n");
		for(Passenger p : passCollection){
			if(p.getStatus())
				setAmount(112.50);
			else
				setAmount(75.00);
			System.out.println(p.getFirstName() + " " + p.getLastName() + " - " + (p.getStatus()? "FIRST CLASS" : "ECONOMY CLASS") + 
					" passenger. $" + getAmount());//(p.getStatus()? 112.50 : 75.00));
		}
	}
}
