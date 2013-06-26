package a00580605.jms.util;

import a00580605.jms.data.MusicalInstrument;
import java.util.Comparator;

public class ItemSorter {
	public static class SortByDescription implements Comparator<MusicalInstrument>{
		@Override
		public int compare(MusicalInstrument arg0, MusicalInstrument arg1) {
			return arg0.getDescription().compareTo(arg1.getDescription());
		}	
	}
	
	public static class SortByPrice implements Comparator<MusicalInstrument>{

		@Override
		public int compare(MusicalInstrument o1, MusicalInstrument o2) {
			// TODO Auto-generated method stub
			return (int) (o1.getSellingPrice()-o2.getSellingPrice());
		}
		
	}
}


/*
import A00580605.lab.data.Student;
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
}*/