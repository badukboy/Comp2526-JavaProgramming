import java.util.GregorianCalendar;

public class Print {
	private static String fName;
	private static String lName;
	private static String stdNum;
	private static GregorianCalendar bDate;
	
	public static void print(Student student){
		String str = "%s %s, ID# %s, Birthdate: %s";
		
		fName = student.getFirstName();
		lName = student.getLastName();
		stdNum = student.getStudentNumber();
		bDate = student.getBirthDate();
		String stdInfo = String.format(str, fName, lName, stdNum, bDate);
		System.out.println("\n\nPrinting\n");	
		System.out.println(stdInfo);
	}
}
