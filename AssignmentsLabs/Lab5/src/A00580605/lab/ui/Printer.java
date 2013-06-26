package A00580605.lab.ui;

import java.util.Collection;

import A00580605.lab.data.DateUtil;
import A00580605.lab.data.Student;

public class Printer {

	public static void print(Student student) {	
		System.out.println(String.format("%s %s, ID# %s, Birthdate: %s", student.getFirstName(), student.getLastName(),
				student.getStudentNumber(), DateUtil.format(student.getBirthDate())));
	}
	
	public static void printArray(Student[] array){
		for(Student std : array){
			System.out.println(String.format("%s %s, ID# %s, Birthdate: %s", std.getFirstName(), std.getLastName(),
					std.getStudentNumber(), DateUtil.format(std.getBirthDate())));
		}
	}
	
	public static void printCollection(Collection<Student> collection){
		for(Student std : collection){
			System.out.println(String.format("%s %s, ID# %s, Birthdate: %s", std.getFirstName(), std.getLastName(),
					std.getStudentNumber(), DateUtil.format(std.getBirthDate())));
		}
	}
}
