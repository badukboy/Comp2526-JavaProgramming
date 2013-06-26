package A00580605.lab.data;

import java.io.FileNotFoundException;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class StudentDAO implements ScanTextFile {
	private static final int NUM_OF_STD = 3;
	private static String dataFileName;
	private Object[] stdList = new Object[NUM_OF_STD];
	private String firstName;
	private String lastName;
	private String stdNum;
	private GregorianCalendar bDate;
	private static Student std;
	
	@Override
	public Object[] readTextData() throws FileNotFoundException {
		int count;
		int stdCount = 0;
		String tempDate;
		//dataFileName = "studentData.txt"; //should not hard code, should use a set and get for that
		setDataFileName("studentData.txt");
	    java.io.File file = new java.io.File(getDataFileName());
	    if(file.exists()){
	    	Scanner input = new Scanner(file);
	    	String temp;
	    	while(input.hasNext()){
	    		count=0;
	    		temp = input.next();
	    		for(int i=0; i<temp.length();i++){
	    			if(temp.charAt(i)=='|'){
	    				count++;
	    				switch(count){
	    				case 1:
	    					firstName = temp.substring(0, i);
	    					temp = temp.substring(i+1, temp.length());
	    					//System.out.println(firstName);
	    					i=0;
	    					break;
	    				case 2: 
	    					lastName = temp.substring(0, i);
	    					temp = temp.substring(i+1, temp.length());
	    					//System.out.println(lastName);
	    					i=0;
	    					break;
	    				case 3:
	    					stdNum = temp.substring(0, i);
	    					tempDate = temp.substring(i+1, temp.length());
	    					bDate = DateUtil.convertFromDMY(tempDate);
	    					//System.out.println(stdNum);
	    					//System.out.println(bDate);
	    					break;
	    				}
	    			}
	    		}
	    		std = new Student(firstName, lastName, stdNum, bDate);
	    		stdList[stdCount] = std;
	    		stdCount++;
	    	}
	    	return stdList;
	    }
	    System.out.println("The file specified does not exist!");
	    System.exit(0);
		return null;
	}

	private void setDataFileName(String fName) {
		// TODO Auto-generated method stub
		dataFileName = fName;
	}
	
	private String getDataFileName(){
		return dataFileName;
	}
}
