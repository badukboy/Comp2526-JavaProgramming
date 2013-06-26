package A00580605.lab.data;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentDAO implements ScanTextFile {
	private static final int NUM_OF_STD = 3;
	private static String dataFileName;
	private Student[] stdList = new Student[NUM_OF_STD];
	private final static String TXTFILE = "studentData.txt";
	private final String JSRFILE = "studentData.jsr";
	private final static String XMLFILE = "studentData.xml";
	
	@Override
	public Object[] readTextData() throws FileNotFoundException {
		//dataFileName = "studentData.txt"; //should not hard code, should use a set and get for that
		setDataFileName(TXTFILE);
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
	
	public void writeToText(Student std) throws IOException {
		final String TEXTFILE = "studentData.txt";
		FileOutputStream fileOut = new FileOutputStream(TEXTFILE, true);
		PrintStream out = new PrintStream(fileOut);
		out.print("\n" + std.getFirstName() + "|" + std.getLastName() + "|" + std.getStudentNumber() + "|" + DateUtil.convertToDMY(std.getBirthDate()));
		out.flush();
		out.close();
	}
	
	public static ArrayList<Student> createDataListFromText() throws FileNotFoundException {
		ArrayList<Student> aListStd = new ArrayList<Student>();
		setDataFileName(TXTFILE);
	    File file = new File(getDataFileName());
	    
		if(!file.exists()){
	    	System.err.println("File " + getDataFileName() + " does not exist");
	    	System.exit(0);
	    }
	    Scanner textInput = new Scanner(new File(getDataFileName()));

	    //for (int i = 0; textInput.hasNext()/*i < NUM_OF_STD*/; i++) {
	    while(textInput.hasNext()) {
			String line = textInput.nextLine();
			String[] itemData = line.split("\\|");

			aListStd.add(new Student(itemData[0], itemData[1], itemData[2],
					DateUtil.convertFromDMY(itemData[3])));
		}
		/*System.out.println(aListStd.size());*/
	    textInput.close();
		return aListStd;		
	}
	

	@SuppressWarnings("unchecked")
	public static ArrayList<Student> createDataListFromXml() throws FileNotFoundException {
		XMLDecoder xDecoder = new XMLDecoder(
			new BufferedInputStream (new FileInputStream(XMLFILE))
		);
		ArrayList<Student> arrListStd = (ArrayList<Student>) xDecoder.readObject();
		xDecoder.close();
		return arrListStd;
	}
	
	public void writeToXml(ArrayList<Student> arrListStd) throws FileNotFoundException {
		XMLEncoder e = new XMLEncoder(
				new BufferedOutputStream(new FileOutputStream(XMLFILE))
		);
		e.writeObject(arrListStd);
		e.close();
	}
	
	public void createJsr(ArrayList<Student> stdList) {
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(new FileOutputStream(JSRFILE));
			out.writeObject(stdList);
			System.out.println("Finished writing records to " + JSRFILE);
		} catch (IOException e) {
			System.out.println("Unable to  write");
			System.out.println(e);
		} finally {
			try {
				out.close();
			} catch (IOException e) {
			}
		}
	}
	
	public void writeToJsr(ArrayList<Student> stdList) {
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(new FileOutputStream(JSRFILE));
			out.writeObject(stdList);
			System.out.println("Finished writing records to " + JSRFILE);
		} catch (IOException e) {
			System.out.println("Unable to  write");
			System.out.println(e);
		} finally {
			try {
				out.close();
			} catch (IOException e) {
			}
		}
	}
	
	private static void setDataFileName(String fName) {
		// TODO Auto-generated method stub
		dataFileName = fName;
	}
	
	private static String getDataFileName(){
		return dataFileName;
	}

	public static ArrayList<Student> createDataListFromJSRFile() {
		// TODO Auto-generated method stub
		return null;
	}
}
