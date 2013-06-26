package A00580605.lab.data;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class test{
	private static String dataFileName;
	private static ArrayList<Student> studentList;
	private static String firstName;
	private static String lastName;
	private static String stdNum;
	private static GregorianCalendar bDate;
	private static Student std;
	
	
	public static void main(String[] args) throws FileNotFoundException{
		int count;
		String tempDate;
		dataFileName = "studentData.txt";
	    java.io.File file = new java.io.File(dataFileName);
	    if(file.exists()){
	    	Scanner input = new Scanner(file);
	    	String temp;
	    	while(input.hasNext()){
	    		count=0;
	    		temp = input.next();
	    		//System.out.println(temp);
	    		for(int i=0; i<temp.length();i++){
	    			if(temp.charAt(i)=='|'){
	    				count++;
	    				switch(count){
	    				case 1:
	    					firstName = temp.substring(0, i);
	    					temp = temp.substring(i+1, temp.length());
	    					System.out.println(firstName);
	    					//System.out.println(temp);
	    					i=0;
	    					break;
	    				case 2: 
	    					lastName = temp.substring(0, i);
	    					temp = temp.substring(i+1, temp.length());
	    					System.out.println(lastName);
	    					//System.out.println(temp);
	    					i=0;
	    					break;
	    				case 3:
	    					stdNum = temp.substring(0, i);
	    					tempDate = temp.substring(i+1, temp.length());
	    					bDate = DateUtil.convertFromDMY(tempDate);
	    					System.out.println(stdNum);
	    					System.out.println(bDate);
	    					i=0;
	    					break;
	    				}
	    			}
	    		}
	    		std = new Student(firstName, lastName, stdNum, bDate);
	    		studentList.add(std);
	    	}
	    }
	    else
	    	System.out.println("The file specified does not exist!");
	}
}
