package a00580605.jms.data;

import java.util.ArrayList;

public class ShoppingCart {
	private ArrayList<MusicalInstrument> purchasedInstruments;
	private ArrayList<RentalAgreement> rentalAgreements;
	
	public ShoppingCart() {
		purchasedInstruments = new ArrayList<MusicalInstrument>();
		rentalAgreements = new ArrayList<RentalAgreement>();
	}
	
	public void addToPurchaseCart(MusicalInstrument instrument, int quantity) {
		if(quantity <= instrument.getQuantityInStock()){
			for(int i=0; i<quantity; i++){
				purchasedInstruments.add(instrument);
			}
			instrument.sell(quantity);
		}
	}

	public void addToRentalCart(RentalAgreement agreement) {
		rentalAgreements.add(agreement);
	}

	public void viewShoppingCarts() {
		double purchaseTotal = 0;
		double rentalTotal = 0;
		System.out.println("Your are purchasing the following item:");
		
		for(MusicalInstrument m : purchasedInstruments) {
			purchaseTotal += m.getSellingPrice();
			System.out.println(m.getDescription() + " $" + m.getSellingPrice());
		}
		System.out.println("TOTAL: $" + purchaseTotal);
		
		System.out.println("\nYou are renting the following items:");
		for(RentalAgreement a:rentalAgreements){
			rentalTotal += a.getTotalRentalFee();
			System.out.println(a.getInstrumentRented().getDescription());
		}
		System.out.println("Rental fees due: $" + rentalTotal);
	}

}
