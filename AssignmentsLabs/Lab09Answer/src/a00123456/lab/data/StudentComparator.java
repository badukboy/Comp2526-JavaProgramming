package a00123456.lab.data;

import java.util.Comparator;

public class StudentComparator {

	public static class ByLastName implements Comparator<Student> {

		public int compare(Student s1, Student s2) {

			return s1.getLastName().compareTo(s2.getLastName());
		}
	}

	public static class ByStudentNumber implements Comparator<Student> {

		public int compare(Student s1, Student s2) {

			return s1.getStudentNumber().compareTo(s2.getStudentNumber());
		}
	}

	
}
