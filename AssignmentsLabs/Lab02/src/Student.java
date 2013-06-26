import java.util.GregorianCalendar;

public class Student {
	private String firstName;
	private String lastName;
	private String studentNumber;
	private GregorianCalendar birthDate;
	
	public Student(String fName, String lName, String stdNum, GregorianCalendar bDate){
		firstName = fName;
		lastName = lName;
		studentNumber = stdNum;
		birthDate = bDate;
	}
	
	public void setFirstName(String name) {
		firstName = name;
	}

	public String getFirstName() {
		return firstName;
	}
	
	public void setLastName(String name) {
		lastName = name;
	}

	public String getLastName() {
		return lastName;
	}
	
	public void setStudentNumber(String num) {
		studentNumber = num;
	}

	public String getStudentNumber() {
		return studentNumber;
	}
	
	public void setBirthDate(GregorianCalendar date) {
		birthDate = date;
	}
	
	public GregorianCalendar getBirthDate() {
		return birthDate;
	}
}
