package A00580605.lab.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollBar;
import java.awt.BorderLayout;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import A00580605.lab.data.DateUtil;
import A00580605.lab.data.Student;
import A00580605.lab.data.StudentDAO;


public class AppFrame extends JFrame{
	private static Student[] stdList;
	private static ArrayList<Student> arrListStd;
	private static final int NUM_OF_STD = 3;
	private StudentDAO std;
	
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
		
		arrListStd = std.createDataList();
		
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
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		final JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		readTextFile.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				try {
					stdList = (Student[]) std.readTextData();
					textArea.append("****DEBUG: Stduents in a Student Array****\n");
					for(Student aStd : arrListStd)
						textArea.append(
								String.format("%s %s, ID# %s, Birthdate: %s\n", aStd.getFirstName(), aStd.getLastName(),
										aStd.getStudentNumber(), DateUtil.format(aStd.getBirthDate()))
						);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}
		});
	}
	
	public void shutDown() {
		JOptionPane.showMessageDialog(this, "I'm outta here!", "Goodbye", JOptionPane.INFORMATION_MESSAGE);
		System.exit(0);
	}
}
