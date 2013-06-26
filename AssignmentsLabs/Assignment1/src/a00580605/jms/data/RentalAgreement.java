package a00580605.jms.data;

import java.util.GregorianCalendar;

import a00580605.jms.util.DateUtil;

public class RentalAgreement {
	private Customer customer;
	private GregorianCalendar rentalDate;
	private MusicalInstrument instrumentRented;
	private double rentalFeePerDay;
	private int numberOfDaysRented;
	private double totalRentalFee;
	
	public RentalAgreement(Customer customer, GregorianCalendar date,
			MusicalInstrument instrumentRented) {
		this.customer = customer;
		this.rentalDate = date;
		this.instrumentRented = instrumentRented;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	
	public GregorianCalendar getRentalDate() {
		return rentalDate;
	}
	
	public MusicalInstrument getInstrumentRented() {
		return instrumentRented;
	}
	
	public double getTotalRentalFee() {
		totalRentalFee = getRentalFeePerDay() * getNumberOfDaysRented();
		return totalRentalFee;
	}

	public void setTotalRentalFee(double totalRentalFee) {
		this.totalRentalFee = totalRentalFee;
	}

	public double getRentalFeePerDay() {
		return rentalFeePerDay;
	}

	public void setRentalFeePerDay(double d) {
		if(d>0)
			this.rentalFeePerDay = d;
	}

	public void setNumberOfDaysRented(int i) {
		if(i>0 && i<32)
			this.numberOfDaysRented = i;
	}
	
	public int getNumberOfDaysRented() {
		return numberOfDaysRented;
	}
	
	public void displayRentalInformation() {
		System.out.println(
				"Customer: " + customer.getFullName() +
				"\nRental date: " + DateUtil.format(getRentalDate()) +
				"\nInstrument type: " + instrumentRented.getDescription() +
				"\nFee per day: $" + getRentalFeePerDay() +
				"\nNumber of days rented: " + numberOfDaysRented +
				"\nTotal rental fee: " + getTotalRentalFee()
		);		
	}

}
