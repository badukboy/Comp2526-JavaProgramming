package a00123456.lab.data;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import a00123456.lab.Lab9;


public class StudentDAO implements ScanTextFile {

	private static final String TEXT_FILENAME = "studentData.txt";
	private static final String XML_FILENAME = "studentData.xml";
	private static final String JSR_FILENAME = "studentData.jsr";
	private int numberOfRows;

	public Object[] readTextData() throws FileNotFoundException {

		System.out.println("DEBUG *** Reading Student data from the file...");
		Student[] inputData = new Student[numberOfRows];

		Scanner scanner = null;
		File file = new File(TEXT_FILENAME);

		if (!file.exists()) {
			System.err.println("File " + TEXT_FILENAME + " does not exist.");
			System.exit(0);
		}

		scanner = new Scanner(new File(TEXT_FILENAME));

		for (int i = 0; i < numberOfRows; i++) {
			String line = scanner.nextLine();
			String[] itemData = line.split("\\|");

			inputData[i] = new Student(itemData[0], itemData[1], itemData[2],
					DateUtil.convertFromDMY(itemData[3]));
		}

		return inputData;
	}

	public static void writeToTextFile(Collection<Student> collection) throws FileNotFoundException{
		
		File file = new File(TEXT_FILENAME);
		
		PrintWriter output = new PrintWriter(file);
		
		for(Student student : collection){
			output.print(student.getFirstName() + "|");
			output.print(student.getLastName() + "|");
			output.print(student.getStudentNumber() + "|");
			output.println(DateUtil.format(student.getBirthDate()));
		}
		
		Lab9.savedFileName = TEXT_FILENAME;
		
		output.close();
		
		
	}

	public static ArrayList<Student> createDataListFromTextFile()
			throws FileNotFoundException {

		System.out.println("DEBUG *** Reading Student data from the file...");
		ArrayList<Student> inputData = new ArrayList<Student>();

		Scanner scanner = null;
		File file = new File(TEXT_FILENAME);

		if (!file.exists()) {
			System.err.println("File " + TEXT_FILENAME + " does not exist.");
			System.exit(0);
		}

		scanner = new Scanner(new File(TEXT_FILENAME));

		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			String[] itemData = line.split("\\|");

			inputData.add(new Student(itemData[0], itemData[1], itemData[2],
					DateUtil.convertFromDMY(itemData[3])));
		}

		return inputData;
	}

	public static void writeToXMLFile(Collection<Student> colleciton){
		
		XMLEncoder out = null;
		try {
			out = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(XML_FILENAME)));
			out.writeObject(colleciton);
		} catch (IOException e) {
			System.out.println(e);
		}
		
		Lab9.savedFileName = XML_FILENAME;
		out.close();
	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList<Student> createDataListFromXMLFile()
			throws FileNotFoundException {

		ArrayList<Student> inputData = new ArrayList<Student>();

		XMLDecoder in = null;

		try {
			in = new XMLDecoder(new BufferedInputStream(new FileInputStream(
					XML_FILENAME)));
			inputData = (ArrayList<Student>) in.readObject();

		} catch (Exception e) {
			System.out.println(e);
		}

		in.close();

		return inputData;
	}
	
	public static void writeToJSRFile(Collection<Student> collection) throws IOException{
		
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(new FileOutputStream(JSR_FILENAME));
			out.writeObject(collection);
		} catch (IOException e) {
			System.out.println(e);
		}
		
		Lab9.savedFileName = JSR_FILENAME;
		out.close();
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<Student> createDataListFromJSRFile()
			throws IOException {

		ArrayList<Student> inputData = new ArrayList<Student>();
		ObjectInputStream objectIn = null;

		try {
			objectIn = new ObjectInputStream(
					new FileInputStream(JSR_FILENAME));
			Object o = objectIn.readObject();
			
			inputData = (ArrayList<Student>) o;
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		objectIn.close();

		return inputData;
	}
	

	public int getNumberOfRows() {
		return numberOfRows;
	}

	public void setNumberOfRows(int numberOfRows) {
		this.numberOfRows = numberOfRows;
	}

}
