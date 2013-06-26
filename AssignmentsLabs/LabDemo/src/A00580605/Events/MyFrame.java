package A00580605.Events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MyFrame extends JFrame {// implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton submit;
	private JButton exit;
	private JTextField input;
	private JLabel output;
	
	public static void main(String[] args) {
		new MyFrame().test();
	}
	
	public void test() {
		setSize(300, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		add(panel); //same as frame.add or MyFrame.add()
		
		input = new JTextField(20);
		output = new JLabel("OUTPUT APPEARS HERE");
		submit = new JButton("Submit");
		exit = new JButton("Exit");
		//submit.addActionListener(this);
		//exit.addActionListener(this);
		//input.addActionListener(this);
		submit.addActionListener(new ActionHandler());
		exit.addActionListener(new ActionHandler());
		exit.addActionListener(new MessageHandler());
		input.addActionListener(new ActionHandler());
		
		/* Third method, least least prefer!
		exit.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.exit(0);
					}
				}
		);
		
		submit.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						output.setText(input.getText());
					}
				}
		);
		
		input.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						output.setText(input.getText());
					}
				}
		);*/
		
		panel.add(input);
		panel.add(output);
		panel.add(submit);
		panel.add(exit);
		
		setVisible(true);
	}
	
	//method two, most prefer	
	private class ActionHandler implements ActionListener { //inner class implements the action listener instead
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(submit) || e.getSource().equals(input))
				output.setText(input.getText());
			else
				System.exit(0);
		}
	}
	
	private class MessageHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Gone fishing");
		}
	}
	//methord one, least prefer
	/*
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(submit) || e.getSource().equals(input))
			output.setText(input.getText());
		else
			System.exit(0);
	}*/
}
