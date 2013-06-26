package a00123456.lab.ui;

import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

import a00123456.lab.Lab9;
import a00123456.lab.data.DateUtil;
import a00123456.lab.data.Student;
import a00123456.lab.data.StudentDAO;

public class AppFrame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea outputArea;
	
	public AppFrame(){
		
		setTitle("Lab 9 File I/O");
		setSize(400, 300);
		setLocationRelativeTo(null);
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we){
				
				shutDown();
			}
		});
		
		buildMenu();
		buildDisplayArea();
	}
	
	public void buildMenu(){
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu file = new JMenu("File");
		file.setMnemonic('F');
		menuBar.add(file);
		
		JMenuItem exit = new JMenuItem("Exit");
		exit.setMnemonic('x');
		exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, Event.ALT_MASK));
		exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				shutDown();
			}
		});
		file.add(exit);
		
		JMenu data = new JMenu("Data");
		data.setMnemonic('D');
		menuBar.add(data);
		
		JMenu read = new JMenu("Read Data");
		read.setMnemonic('R');
		data.add(read);
		
		JMenuItem readTextFile = new JMenuItem("Text File");
		readTextFile.setMnemonic('T');
		readTextFile.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				
				try {
					printCollection(StudentDAO.createDataListFromTextFile());
					
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		});
		read.add(readTextFile);
		
		JMenuItem readXMLFile = new JMenuItem("XML File");
		readXMLFile.setMnemonic('X');
		readXMLFile.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				
				try {
					printCollection(StudentDAO.createDataListFromXMLFile());
					
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		});
		read.add(readXMLFile);
		
		JMenuItem readSerializedFile = new JMenuItem("Serialized File");
		readSerializedFile.setMnemonic('S');
		readSerializedFile.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				
				try {
					printCollection(StudentDAO.createDataListFromJSRFile());
					
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		read.add(readSerializedFile);
		
		data.addSeparator();
		
		JMenu write = new JMenu("Write Data");
		write.setMnemonic('W');
		data.add(write);
		
		JMenuItem writeTextFile = new JMenuItem("Text File");
		writeTextFile.setMnemonic('T');
		writeTextFile.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				try {
					StudentDAO.writeToTextFile(Lab9.students);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				
			}
		});
		write.add(writeTextFile);
		
		JMenuItem writeXMLFile = new JMenuItem("XML File");
		writeXMLFile.setMnemonic('X');
		write.add(writeXMLFile);
		writeXMLFile.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				StudentDAO.writeToXMLFile(Lab9.students);
			}
		});
		
		JMenuItem writeSerializedFile = new JMenuItem("Serialized File");
		writeSerializedFile.setMnemonic('S');
		writeSerializedFile.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				try {
					StudentDAO.writeToJSRFile(Lab9.students);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		write.add(writeSerializedFile);
		
		
	}
	
	public void buildDisplayArea(){
		
		outputArea = new JTextArea();
		outputArea.setLineWrap(true);
		outputArea.setWrapStyleWord(true);
		add(new JScrollPane(outputArea));
	}
	
	public void printCollection(Collection<Student> collection) {

		outputArea.setText("");
		if (collection.size() > 0) {
			for (Student student : collection) {

				outputArea.append(formatStudent(student));
			}
		}

	}

	private String formatStudent(Student student) {

		return String.format("%s %s, ID# %s, Birthdate: %s\n",
				student.getFirstName(), student.getLastName(),
				student.getStudentNumber(),
				DateUtil.format(student.getBirthDate()));
	}
	
	public void shutDown() {
		
		File file = new File("choice.properties");
		Properties properties = new Properties();
		properties.put("fileChoice", Lab9.savedFileName);
		FileOutputStream out;
		try {
			out = new FileOutputStream(file);
			properties.store(out, "My file persistance choice");
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		JOptionPane.showMessageDialog(this, "Data saved to " + Lab9.savedFileName, "Goodbye", JOptionPane.INFORMATION_MESSAGE);
		
		System.exit(0);
	}

}
