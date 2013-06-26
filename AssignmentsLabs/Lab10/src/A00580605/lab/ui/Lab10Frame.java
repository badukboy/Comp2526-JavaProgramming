package A00580605.lab.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import A00580605.lab.data.DivideByZeroException;
import A00580605.lab.data.NotAnIntegerException;
import A00580605.lab.data.NotAnIntegerResult;

public class Lab10Frame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField result;
	private double num1;
	private double num2;
	private int error;
	private double num3;
	private String answer;
	private JLabel num1Label;
	private JLabel num2Label;
	private JButton divideBtn;
	private DecimalFormat df = new DecimalFormat("#.####");
	private JLabel resultLabel;
	private static JTextField num1In;
	private JTextField num2In;
	private JTextField errMsg;

	/**
	 * Create the frame.
	 */
	public Lab10Frame() {
		setTitle("Lab 10");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 146);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[64.00,grow][100.00,grow][59.00,grow][93.00,grow][71.00][83.00]", "[][42.00][41.00]"));
		
		num1Label = new JLabel("Number 1");
		contentPane.add(num1Label, "cell 0 0,alignx trailing");
		
		num1In = new JTextField();
		num1In.setHorizontalAlignment(SwingConstants.LEFT);
		contentPane.add(num1In, "cell 1 0,grow");
		num1In.setColumns(10);
		
		num2Label = new JLabel("Number 2");
		contentPane.add(num2Label, "flowy,cell 2 0,alignx trailing");
		
		num2In = new JTextField();
		num2In.setHorizontalAlignment(SwingConstants.LEFT);
		contentPane.add(num2In, "cell 3 0,growx");
		num2In.setColumns(10);
		
		resultLabel = new JLabel("Result");
		contentPane.add(resultLabel, "cell 4 0,alignx trailing");
		
		result = new JTextField();
		result.setHorizontalAlignment(SwingConstants.LEFT);
		result.setEditable(false);
		contentPane.add(result, "cell 5 0,grow");
		result.setColumns(10);
		
		errMsg = new JTextField();
		errMsg.setBorder(null);
		errMsg.setEditable(false);
		errMsg.setHorizontalAlignment(SwingConstants.LEFT);
		contentPane.add(errMsg, "cell 0 1 6 1,grow");
		errMsg.setColumns(10);
		
		divideBtn = new JButton("Divide");
		divideBtn.setVerticalAlignment(SwingConstants.BOTTOM);
		contentPane.add(divideBtn, "cell 0 2 6 1,growx,aligny bottom");
			
		divideBtn.addActionListener(new calculateListener());
	}
	
	private class calculateListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			error = 0;
			try {
				num1 = Integer.parseInt(num1In.getText());
				num2 = Integer.parseInt(num2In.getText());
			} catch (NumberFormatException e1) {
					try {
						error = 1;
						errMsg.setText("Integers inputs only please!");
						throw new NotAnIntegerException("Integers inputs only please!");
					} catch (NotAnIntegerException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
			}
			
			if(num2 == 0) {
				try {
					error = 1;
					errMsg.setText("The second number cannot be zero!");
					throw new DivideByZeroException("The second number cannot be zero!");
				} catch (DivideByZeroException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			num3 = num1/num2;
			if(Math.floor(num3) != Math.ceil(num3)){
				try {
					error = 1;
					errMsg.setText("The result is not an integer!");
					throw new NotAnIntegerResult("The result is not an integer!");
				} catch (NotAnIntegerResult e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			if(error == 0) {
				answer = df.format(num3);
				result.setText(answer);
				errMsg.setText("");
			}
		}
	}
}
