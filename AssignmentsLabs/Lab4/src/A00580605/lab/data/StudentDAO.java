package A00580605.lab.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class StudentDAO implements ScanTextFile {
	private static final int NUM_OF_STD = 3;
	private static String dataFileName;
	private Student[] stdList = new Student[NUM_OF_STD];
		
	@Override
	public Object[] readTextData() throws FileNotFoundException {
		//dataFileName = "studentData.txt"; //should not hard code, should use a set and get for that
		setDataFileName("studentData.txt");
	    File file = new File(getDataFileName());
	    
		if(!file.exists()){
	    	System.err.println("File " + getDataFileName() + " does not exist");
	    	System.exit(0);
	    }
	    Scanner input = new Scanner(new File(getDataFileName()));

	    for (int i = 0; i < NUM_OF_STD; i++) {
			String line = input.nextLine();
			String[] itemData = line.split("\\|");

			stdList[i] = new Student(itemData[0], itemData[1], itemData[2],
					DateUtil.convertFromDMY(itemData[3]));
		}
	    return stdList;
	}

	private void setDataFileName(String fName) {
		// TODO Auto-generated method stub
		dataFileName = fName;
	}
	
	private String getDataFileName(){
		return dataFileName;
	}
}
