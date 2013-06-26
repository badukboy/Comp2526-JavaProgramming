package A00580605.lab.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
//import java.util.Collections;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import A00580605.lab.data.DateUtil;
import A00580605.lab.data.Student;
//import A00580605.lab.data.StudentComparator;
import A00580605.lab.data.StudentDAO;


public class AppFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static ArrayList<Student> arrListStd;
	private StudentDAO std;
	private JTextArea textArea;
	private JOptionPane op = null;
	
	public AppFrame() throws FileNotFoundException {
		setTitle("lab 9 File I/O");
		setSize(400, 300);
		setLocationRelativeTo(null); //center
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				shutDown();
			}
		});
		
		buildMenu();
	}
	
	public void buildMenu() throws FileNotFoundException {
		std = new StudentDAO();
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu file = new JMenu("File");
		file.setMnemonic('F');
		menuBar.add(file);
		JMenuItem exit = new JMenuItem("Exit");
		exit.setMnemonic('x');
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shutDown();			
			}
		});
		
		JMenu data = new JMenu("Data");
		data.setMnemonic('D');
		menuBar.add(data);
		
		JMenu read = new JMenu("Read Data");
		read.setMnemonic('r');
		JMenuItem readTextFile = new JMenuItem("Text File");
		readTextFile.setMnemonic('t');
		JMenuItem readJsrFile = new JMenuItem("JSR File");
		readJsrFile.setMnemonic('j');
		JMenuItem readXmlFile = new JMenuItem("XML File");
		readXmlFile.setMnemonic('x');
		data.add(read);
		read.add(readTextFile);
		read.add(readJsrFile);
		read.add(readXmlFile);
		
		JMenu write = new JMenu("Write Data");
		write.setMnemonic('w');
		file.add(exit);
		JRadioButtonMenuItem writeTextFile = new JRadioButtonMenuItem("Text File");
		writeTextFile.setMnemonic('t');
		JRadioButtonMenuItem writeJsrFile = new JRadioButtonMenuItem("JSR File");
		writeJsrFile.setMnemonic('j');
		JRadioButtonMenuItem writeXmlFile = new JRadioButtonMenuItem("XML File");
		writeXmlFile.setMnemonic('x');
		write.add(writeTextFile);
		write.add(writeJsrFile);
		write.add(writeXmlFile);
		data.add(write);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setAutoscrolls(true);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		
		readTextFile.addActionListener(new readTxtListener());
		readXmlFile.addActionListener(new readXMLListener());
		readJsrFile.addActionListener(new readJsrListener());
		
		writeTextFile.addActionListener(new writeTxtListener());
		writeXmlFile.addActionListener(new writeXmlListener());
		writeJsrFile.addActionListener(new writeJsrListener());
	}
	
	public void printRecords(ArrayList<Student>stdList) {
		for(Student std : arrListStd){
			textArea.append(
				String.format("%s %s, ID# %s, Birthdate: %s\n", std.getFirstName(), std.getLastName(),
						std.getStudentNumber(), DateUtil.format(std.getBirthDate()))
			);
		}
		textArea.append("\n");
	}
	
	public void shutDown() {
		JOptionPane.showMessageDialog(this, "I'm outta here!", "Goodbye", JOptionPane.INFORMATION_MESSAGE);
		System.exit(0);
	}
	
	private class readTxtListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			try {
				arrListStd = std.createDataListFromText();
				
				textArea.append("Content from the selected Text file\n");
				printRecords(arrListStd);
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
		}
	}
	
	private class writeTxtListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			Student data = new Student("Tony", "Stark", "A00987655", DateUtil.convertFromDMY("10-5-1978")); //data the will be written to the text file
			try {
				std.writeToText(data);
				JOptionPane.showMessageDialog(op, "The student record has been written to the text file", "Done", JOptionPane.INFORMATION_MESSAGE);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private class readXMLListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			try {
				arrListStd = std.createDataListFromXml();
				textArea.append("Content from the selected XML file\n");
				printRecords(arrListStd);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private class writeXmlListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			try {
				arrListStd = std.createDataListFromXml();
				//arrListStd = std.createDataListFromText(); //reset the xml file to be the same as the txt file
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Student data = new Student("Joe", "Smith", "A00587621", DateUtil.convertFromDMY("4-24-1986"));
			arrListStd.add(data);
			try {
				std.writeToXml(arrListStd);
				JOptionPane.showMessageDialog(op, "The student record has been written to the XML file", "Done", JOptionPane.INFORMATION_MESSAGE);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private class readJsrListener implements ActionListener {
		@SuppressWarnings("unchecked")
		public void actionPerformed(ActionEvent arg0) {
			final String JSRFILE = "studentData.jsr";
			try {
				ObjectInputStream objectIn = new ObjectInputStream(new FileInputStream(JSRFILE));
				Object o = objectIn.readObject();
				assert (o instanceof ArrayList<?>) : "Bad student record file";
				arrListStd = (ArrayList<Student>) o;
				objectIn.close();
			} catch (Exception e) {
				System.out.println(e);
			}
			//System.out.println("Reconstructed object from " + "studentData.jsr" + "\n");
			textArea.append("Content from the selected JSR file\n");
			printRecords(arrListStd);
		}
	}
	
	private class writeJsrListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			try {
				arrListStd = std.createDataListFromText();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//std.createJsr(arrListStd);  //reset the jsr file to be the same as the txt file
			Student data = new Student("Tom", "Jones", "A00587762", DateUtil.convertFromDMY("29-2-1982"));
			arrListStd.add(data);
			std.writeToJsr(arrListStd);JOptionPane.showMessageDialog(op, "The student record has been written to the JSR file", "Done", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
