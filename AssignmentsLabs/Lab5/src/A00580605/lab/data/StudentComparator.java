package A00580605.lab.data;

import java.util.Comparator;

public class StudentComparator {
	public static class ByLastName implements Comparator<Student>{

		@Override
		public int compare(Student arg0, Student arg1) {
			return arg0.getLastName().compareTo(arg1.getLastName());
		}
	}
	
	public static class ByStudentNumber implements Comparator<Student>{

		@Override
		public int compare(Student arg0, Student arg1) {
			return arg0.getStudentNumber().compareTo(arg1.getStudentNumber());
		}
	}
}
