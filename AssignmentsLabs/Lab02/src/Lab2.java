import java.io.File;
import java.util.GregorianCalendar;
import java.util.Scanner;


public class Lab2 {
	private static int numOfStd;
	private static int numToPrint;
	private static String firstName;
	private static String lastName;
	private static String studentNum;
	private static GregorianCalendar birthDate;
	
	private Lab2(String[] args) {
	}
	
	private static String[] readData(String string) {
		String [] std = new String [4];
		String temp = "";
		int j=0;
		for(int i=1;i<string.length()-1;i++){
			if(string.charAt(i)!='|'){
				temp+=string.charAt(i);
			}
			else{
				std[j]=temp;
				j++;
				temp="";
			}				
		}
		std[3] = temp;
		return std;		
	}
	
	private static Student createStudent(String[] data) throws Exception {
		firstName = data[0];
		lastName = data[1];
		studentNum = data[2];

		int day = 0;
		int month = 0;
		int year = 0;
		int length=0;
		try {
			length = data[3].length();
		}
		catch(Exception e){
			System.out.println("Invalid length," + length);
		}

		if(length>9){
			day = Integer.parseInt(data[3].substring(0, 1));
			month = Integer.parseInt(data[3].substring(3, 4));
			year = Integer.parseInt(data[3].substring(6, 9));
		}
		else {
			try {
				day = Integer.parseInt(data[3].substring(0, 1));
				month =data[3].charAt(3);
				year = Integer.parseInt(data[3].substring(5, 8));
			}
			catch(Exception e){
				System.out.println("Problem");
			}
		}
		birthDate = new GregorianCalendar(day,month,year);
		Student std = new Student(firstName, lastName, studentNum, birthDate);
		return std;
	}
	
	public static void main(String[] args) throws Exception {
		numOfStd = Integer.parseInt(args[1]);
		String [] data = new String [numOfStd];
		Student[] student = new Student[numOfStd];
		if (args.length < 3) {
			System.out.println(args.length+"arguments - expected 3");
			System.exit(0);
		}
		else if (args.length > 3) {
			System.out.println("Too many arguments - expected 3");
			System.exit(0);
		}
		else {
			File file = new File(args[0]);
			Scanner scan = new Scanner(file);
			numToPrint = Integer.parseInt(args[2]);
			String entry;
			
			for(int i=0; i<numOfStd; i++){
				entry = scan.next();
				data = readData(entry);
				student[i] = createStudent(data);
				System.out.println("\n" + student[i].getFirstName() + "\n");
			}
		}
		Print.print(student[numToPrint]);
	}
}
