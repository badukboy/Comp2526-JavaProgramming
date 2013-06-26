package A00580605.lab;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;

import org.omg.CORBA.portable.ApplicationException;

import A00580605.lab.data.Student;
import A00580605.lab.data.StudentComparator;
import A00580605.lab.data.StudentDAO;
import A00580605.lab.ui.AppFrame;
import A00580605.lab.ui.Printer;

/**
 * @author Steve Lo
 * 
 */
public class Lab5 {
	private static Student[] stdList;
	private static ArrayList<Student> arrListStd;
	private static final int NUM_OF_STD = 3;
	
	/**
	 * @param args
	 * @throws FileNotFoundException 
	 * @throws ApplicationException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		StudentDAO std = new StudentDAO();
		stdList = (Student[]) std.readTextData();
		
		arrListStd = std.createDataList();
		/*
		System.out.println("****DEBUG: Stduents in a Student Array****");
		for(Student aStd : arrListStd)
			Printer.print(aStd);

		System.out.println("\n****DEBUG: Students Collection sorted by last name****");
		Collections.sort(arrListStd, new StudentComparator.ByLastName());
		Printer.printArray(arrListStd.toArray(new Student[0]));		

		System.out.println("\n****DEBUG: Students Collection sorted by student number****");
		Collections.sort(arrListStd, new StudentComparator.ByStudentNumber());
		Printer.printCollection(arrListStd);

		System.out.println("\n****DEBUG: Using a sorted Collection by last name****");
		SortedSet<Student> stdSet = new TreeSet<Student>(new StudentComparator.ByLastName());
		stdSet.addAll(arrListStd);
		Printer.printCollection(stdSet);
		*/
		new AppFrame().setVisible(true);
	}
}

