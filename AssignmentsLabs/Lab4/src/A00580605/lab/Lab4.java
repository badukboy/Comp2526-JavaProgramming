package A00580605.lab;

import java.io.FileNotFoundException;
import java.util.Arrays;

import org.omg.CORBA.portable.ApplicationException;

import A00580605.lab.data.Student;
import A00580605.lab.data.StudentComparator;
import A00580605.lab.data.StudentDAO;
import A00580605.lab.ui.Printer;

/**
 * @author Steve Lo
 * 
 */
public class Lab4 {
	private static Student[] stdList;
	
	/**
	 * @param args
	 * @throws FileNotFoundException 
	 * @throws ApplicationException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		StudentDAO std = new StudentDAO();
		stdList = (Student[]) std.readTextData();
		
		System.out.println("****DEBUG: Students in original order****");
		for (Student s : stdList)
			Printer.print((Student) s);
		
		Arrays.sort(stdList, new StudentComparator.ByLastName());
		System.out.println("\n****DEBUG: Students sorted by last name****");
		for (Student s : stdList)
			Printer.print((Student) s);
		
		Arrays.sort(stdList, new StudentComparator.ByStudentNumber());
		System.out.println("\n****DEBUG: Students sorted by student number****");
		for (Student s : stdList)
			Printer.print((Student) s);
	}
}

