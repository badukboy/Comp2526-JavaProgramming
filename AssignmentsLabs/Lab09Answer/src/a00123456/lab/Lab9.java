/**
 * 
 */
package a00123456.lab;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;
import java.util.SortedSet;
import java.util.TreeSet;

import org.omg.CORBA.portable.ApplicationException;

import a00123456.lab.data.Student;
import a00123456.lab.data.StudentComparator;
import a00123456.lab.data.StudentDAO;
import a00123456.lab.ui.AppFrame;
import a00123456.lab.ui.Printer;

/**
 * @author Bullwinkle Moose
 * 
 */
public class Lab9 {

	public static ArrayList<Student> students;
	public static final String DEFAULT_FILENAME = "studentData.txt";
	public static String savedFileName;
	private static AppFrame frame;

	/**
	 * @param args
	 * @throws IOException 
	 * @throws ApplicationException
	 */
	public static void main(String[] args) throws IOException {

		new Lab9().test();
	}

	public void test() throws IOException {
		
		File file = new File("choice.properties");
		Properties properties = new Properties();
		
		//check to see if the properties file exists
		if(file.exists()){
			properties.load(new FileInputStream(file));
			savedFileName = properties.getProperty("fileChoice");
			System.err.println("****DEBUG: Last file choice: " + savedFileName);
			
		} else {
			savedFileName = DEFAULT_FILENAME;
		}

		//check file name and take the appropriate action
		if (savedFileName.equals("studentData.txt")){
			students = StudentDAO.createDataListFromTextFile();
			
		} else if (savedFileName.equals("studentData.xml")) {
			students = StudentDAO.createDataListFromXMLFile();
			
		} else {
			
			students = StudentDAO.createDataListFromJSRFile();
		}

		System.out.println("\n****DEBUG: Students in a Student Array****");
		for (Student aStudent : students) {

			Printer.print(aStudent);
		}

		System.out.println("\n****DEBUG: Student Collection sorted by last name****");
		Collections.sort(students, new StudentComparator.ByLastName());
		Printer.printArray(students.toArray(new Student[0]));

		System.out.println("\n****DEBUG: Student Collection sorted by student number****");
		Collections.sort(students, new StudentComparator.ByStudentNumber());
		Printer.printCollection(students);

		System.out.println("\n****DEBUG: Using a sorted Collection by last name****");
		SortedSet<Student> sortedSet = new TreeSet<Student>(new StudentComparator.ByLastName());
		sortedSet.addAll(students);
		Printer.printCollection(sortedSet);
		
		frame = new AppFrame();
		showInFrame();
		frame.setVisible(true);
		

	}
	
	public static void showInFrame(){
		
		frame.printCollection(students);
	}

}
