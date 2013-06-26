package a00580605.midterm.data;

public class Passenger {
	private String firstName;
	private String lastName;
	private String creditCard;
	private String email;
	private boolean status; //false = Economy, true = First Class
	
	/**
	 * Passenger default constructor
	 */
	public Passenger(){
		this.firstName = null;
		this.lastName = null;
		this.creditCard = null;
		this.email = null;
		this.status = false;
	}
	
	public Passenger(String fName, String lName, String cc, String eAddress) {
		this.firstName = fName;
		this.lastName = lName;
		this.creditCard = cc;
		this.email = eAddress;
	}

	public Passenger(String[] strings) {
		this.firstName = strings[0];
		this.lastName = strings[1];
		this.creditCard = strings[2];
		this.email = strings[3];
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String fName) {
		this.firstName = fName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lName) {
		this.lastName = lName;
	}

	public String getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(String cc) {
		this.creditCard = cc;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String eAddress) {
		this.email = eAddress;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean s) {
		this.status = s;
	}
	
	@Override
	public String toString() {
		return "Passenger [firstName=" + firstName + ", lastName=" + lastName
				+ ", creditCard=" + creditCard + ", email=" + email
				+ ", status=" + status + "]";
	}
}
