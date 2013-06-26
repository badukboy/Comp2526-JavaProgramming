package examples.collections;

/**
 * 
 */

/**
 * @author A00195151
 * 
 */
public class Employee implements Comparable {

	private final String name;
	private double salary;

	public Employee(String n, double s) {
		name = n;
		salary = s;
	}

	public Employee(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public double getSalary() {
		return salary;
	}

	public void raiseSalary(double byPercent) {
		double raise = salary * byPercent / 100;
		salary += raise;
	}

	/**
	 * Compares employees by salary
	 * 
	 * @param otherObject
	 *            another Employee object
	 * @return a negative value if this employee has a lower salary than otherObject, 0 if the salaries are the same, a
	 *         positive value otherwise
	 */
	@Override
	public int compareTo(Object otherObject) {
		Employee other = (Employee) otherObject;
		if (salary < other.salary)
			return -1;
		if (salary > other.salary)
			return 1;
		return 0;
	}
}