package a00580605.jms;
import java.util.GregorianCalendar;

import a00580605.jms.data.Customer;
import a00580605.jms.data.MusicalInstrument;
import a00580605.jms.data.RentalAgreement;
import a00580605.jms.data.ShoppingCart;
import a00580605.jms.util.DateUtil;

public class AssignmentDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//MusicalInstrument test
		System.out.print("Creating a MusicalInstrument:\n");
		MusicalInstrument instrument = new MusicalInstrument("Kazzoo", "kaz001", 1.00, 2.00);
		instrument.restock(10);
		instrument.sell(5);
		instrument.setNumberRented(2);
		instrument.printDetails();
		
		//RentalCustomer test
		System.out.print("\nCreating a Customer:\n");
		//overloaded constructor for test application functions during development
		Customer customer = new Customer("darbY", "doG", "123456789", "Here", "123-123-1234");
		//Customer customer = new Customer();
		System.out.println(customer);
		
		//DateUtil test
		System.out.print("\nThe date is:\n");
		//GregorianCalendar rentalDate = DateUtil.convertFromDMY("27-5-2011");
		GregorianCalendar today = new GregorianCalendar();
		System.out.println(DateUtil.format(today));
		
		//RentalAgreement test
		System.out.print("\nCreate a Rental Agreement:\n");
		RentalAgreement agreement = new RentalAgreement(customer, today, instrument);
		agreement.setRentalFeePerDay(1.0);
		agreement.setNumberOfDaysRented(31);
		agreement.displayRentalInformation();
		
		//ShoppingCart Test
		System.out.print("\nCreate purchase and rental shopping carts:\n");
		ShoppingCart cart = new ShoppingCart();
		cart.addToPurchaseCart(instrument, 5); //purchasing 5 instruments
		cart.addToRentalCart(agreement); // adding the above RentalAgreement
		cart.viewShoppingCarts(); // displaying the purchased and rented items
		
	}

}
