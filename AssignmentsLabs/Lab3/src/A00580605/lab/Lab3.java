package A00580605.lab;

import java.io.FileNotFoundException;
import org.omg.CORBA.portable.ApplicationException;

import A00580605.lab.data.Student;
import A00580605.lab.data.StudentDAO;
import A00580605.lab.ui.Printer;

/**
 * @author Steve Lo
 * 
 */
public class Lab3 {
	private static Object[] stdList;
	private static final int NUM_OF_STD = 3;
	
	/**
	 * @param args
	 * @throws FileNotFoundException 
	 * @throws ApplicationException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		StudentDAO std = new StudentDAO();
		stdList = std.readTextData();
		for(int i=0; i<NUM_OF_STD;i++)
			Printer.print((Student) stdList[i]);
	}
}

