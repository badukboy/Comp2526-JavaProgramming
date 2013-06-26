package a00580605.jms.data;

import a00580605.jms.util.InputReader;

public class Customer {
	private String firstName;
	private String lastName;
	private String creditCardNumber;
	private String addresss;
	private String phonenumber;
	
	/**
	 * Passenger default constructor
	 */
	public Customer(){
		InputReader in = new InputReader();
		System.out.println("Enter first name: ");
		this.firstName = in.getInput();
		System.out.println("Enter last name: ");
		this.lastName = in.getInput();
		System.out.println("Enter credit card number: ");
		this.creditCardNumber = in.getInput();
		System.out.println("Enter address: ");
		this.addresss = in.getInput();
		System.out.println("Enter phone number: ");
		this.phonenumber = in.getInput();
	}
	
	public Customer(String fName, String lName, String cc, String ad, String phoneNo) {
		this.firstName = fName;
		this.lastName = lName;
		this.creditCardNumber = cc;
		this.addresss = ad;
		this.phonenumber = phoneNo;
	}

	public Customer(String[] strings) {
		this.firstName = strings[0];
		this.lastName = strings[1];
		this.creditCardNumber = strings[2];
		this.addresss = strings[3];
		this.phonenumber = strings[4];
	}
	
	public String getFullName() {
		String temp = "";
		for(int i=0; i<this.firstName.length(); i++){
			if(i==0)
				temp += Character.toUpperCase(this.firstName.charAt(i));
			else
				temp += Character.toLowerCase(this.firstName.charAt(i));
		}
		temp += " ";
		for(int i=0; i<this.lastName.length(); i++){
			if(i==0)
				temp += Character.toUpperCase(this.lastName.charAt(i));
			else
				temp += Character.toLowerCase(this.lastName.charAt(i));
		}
		return temp;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		if (firstName!=null && firstName.length()>0)
			this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		if (lastName!=null && lastName.length()>0)
			this.lastName = lastName;
	}
	
	public String getCreditCardNumber() {
		return creditCardNumber;
	}
	
	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}
	
	public String getAddresss() {
		return addresss;
	}
	
	public void setAddresss(String addresss) {
		if (addresss!=null && addresss.length()>0)
			this.addresss = addresss;
	}
	
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	
	@Override
	public String toString() {
		return (
				"Customer: " + getFullName() +
				"\nCredit Card Number: " + getCreditCardNumber() +
				"\nAddress: " + getAddresss() +
				"\nPhone Number: $" + this.getPhonenumber()
		);
	}
}
