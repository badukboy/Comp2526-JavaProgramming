package a00123456.lab.ui;

import java.util.Collection;

import a00123456.lab.data.DateUtil;
import a00123456.lab.data.Student;

public class Printer {

	public static void print(Student student) {

		if (student != null) {
			System.out.println(formatStudent(student));
		} else {
			System.out.println("Student is null");
		}
	}

	public static void printArray(Student[] array) {

		if (array.length > 0) {
			for (Student student : array) {

				System.out.println(formatStudent(student));
			}
		}

	}

	public static void printCollection(Collection<Student> collection) {

		if (collection.size() > 0) {
			for (Student student : collection) {

				System.out.println(formatStudent(student));
			}
		}

	}

	private static String formatStudent(Student student) {

		return String.format("%s %s, ID# %s, Birthdate: %s",
				student.getFirstName(), student.getLastName(),
				student.getStudentNumber(),
				DateUtil.format(student.getBirthDate()));
	}

}
