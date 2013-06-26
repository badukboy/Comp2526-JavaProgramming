package a00123456.lab.data;

import java.io.Serializable;
import java.util.GregorianCalendar;

public class Student implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String firstName;
	private String lastName;
	private String studentNumber;
	private GregorianCalendar birthDate;

	public Student() {
	}

	/**
	 * @param firstName
	 * @param lastName
	 * @param studentNumber
	 * @param birthDate
	 */
	public Student(String firstName, String lastName, String studentNumber,
			GregorianCalendar birthDate) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.studentNumber = studentNumber;
		this.birthDate = birthDate;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the studentNumber
	 */
	public String getStudentNumber() {
		return studentNumber;
	}

	/**
	 * @param studentNumber the studentNumber to set
	 */
	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}

	/**
	 * @return the birthDate
	 */
	public GregorianCalendar getBirthDate() {
		return birthDate;
	}

	/**
	 * @param birthDate the birthDate to set
	 */
	public void setBirthDate(GregorianCalendar birthDate) {
		this.birthDate = birthDate;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Student [firstName=" + firstName + ", lastName=" + lastName
				+ ", studentNumber=" + studentNumber + ", birthDate="
				+ birthDate + "]";
	}

	

}
