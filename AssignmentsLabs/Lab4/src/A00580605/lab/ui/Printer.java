package A00580605.lab.ui;

import A00580605.lab.data.DateUtil;
import A00580605.lab.data.Student;

public class Printer {

	public static void print(Student student) {
		
		System.out.println(String.format("%s %s, ID# %s, Birthdate: %s", student.getFirstName(), student.getLastName(),
				student.getStudentNumber(), DateUtil.format(student.getBirthDate())));
	}

}
