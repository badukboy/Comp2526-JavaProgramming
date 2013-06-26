package A00580605.lab.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSeparator;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class Lab8Frame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField result;
	private int num1;
	private int num2;
	private int operator;
	private double num3;
	private String answer;
	private JLabel num1Label;
	private JLabel num2Label;
	private JLabel operationLabel;
	private JLabel equalsLabel;
	private JSpinner num1Spinner;
	private JSpinner num2Spinner;
	private JComboBox<String> operationComboBox;
	private JSeparator separator1;
	private JSeparator separator2;
	private JButton calculateBtn;
	private JLabel workArea;
	private DecimalFormat df = new DecimalFormat("#.####");

	/**
	 * Create the frame.
	 */
	public Lab8Frame() {
		setTitle("Simple Calculator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][358.00,grow]", "[][][][][8.00][][][grow][][]"));
		
		num1Label = new JLabel("Number 1");
		contentPane.add(num1Label, "cell 0 1");
		
		num1Spinner = new JSpinner();
		num1Spinner.setModel(new SpinnerNumberModel(0, -10, 10, 1));
		contentPane.add(num1Spinner, "cell 2 1,grow");
		
		operationLabel = new JLabel("Operation");
		contentPane.add(operationLabel, "cell 0 2,alignx trailing");
		
		operationComboBox = new JComboBox<String>();
		operationComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"+", "-", "*", "/"}));
		operationComboBox.setMaximumRowCount(4);
		contentPane.add(operationComboBox, "cell 2 2,growx");
		
		num2Label = new JLabel("Number 2");
		contentPane.add(num2Label, "flowy,cell 0 3");
		
		num2Spinner = new JSpinner();
		num2Spinner.setModel(new SpinnerNumberModel(0, -10, 10, 1));
		contentPane.add(num2Spinner, "cell 2 3,grow");
		
		separator1 = new JSeparator();
		separator1.setToolTipText("");
		separator1.setBackground(Color.GRAY);
		separator1.setForeground(Color.GRAY);
		contentPane.add(separator1, "cell 0 4,growx");
		
		separator2 = new JSeparator();
		separator2.setBackground(Color.GRAY);
		separator2.setForeground(Color.GRAY);
		contentPane.add(separator2, "cell 2 4,growx");
		
		equalsLabel = new JLabel("=");
		contentPane.add(equalsLabel, "cell 0 6,alignx right");
		
		result = new JTextField();
		result.setHorizontalAlignment(SwingConstants.RIGHT);
		result.setEditable(false);
		contentPane.add(result, "cell 2 6,grow");
		result.setColumns(10);
		
		calculateBtn = new JButton("Calculate");
		
		workArea = new JLabel(num1Spinner.getValue() + " "+ operationComboBox.getSelectedItem() + " " + num2Spinner.getValue());
		workArea.setOpaque(true);
		workArea.setForeground(Color.BLACK);
		workArea.setHorizontalAlignment(SwingConstants.RIGHT);
		workArea.setBackground(Color.LIGHT_GRAY);
		contentPane.add(workArea, "cell 0 7 3 1,grow");
		calculateBtn.setVerticalAlignment(SwingConstants.BOTTOM);
		contentPane.add(calculateBtn, "cell 0 8 3 2,alignx center,aligny bottom");
			
		calculateBtn.addActionListener(new calculateListener());
		operationComboBox.addActionListener(new operationBoxListener());
		num1Spinner.addChangeListener(new spinnerListener());
		num2Spinner.addChangeListener(new spinnerListener());
		/*num1Spinner.addChangeListener(new num1SpinnerListener());
		num2Spinner.addChangeListener(new num2SpinnerListener());*/
	}
	
	private class operationBoxListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			workArea.setText(num1Spinner.getValue() + " "+ operationComboBox.getSelectedItem().toString() + " " + num2Spinner.getValue());
			operator = operationComboBox.getSelectedIndex();
			//System.out.println("" + operator);
		}
	}
	
	private class spinnerListener implements ChangeListener {
		public void stateChanged(ChangeEvent arg0) {
			workArea.setText(num1Spinner.getValue() + " "+ operationComboBox.getSelectedItem().toString() + " " + num2Spinner.getValue());
		}
	}
	
	private class calculateListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			workArea.setText("");
			num1 = (int) num1Spinner.getValue();
			num2 = (int) num2Spinner.getValue();
			if(operator==0)
				num3 = num1+num2;
			if(operator==1)
				num3 = num1-num2;
			if(operator==2)
				num3 = num1*num2;
			if(operator==3)
				num3 = (double)(num1)/(double)(num2);
			answer = df.format(num3);
			if(operator==3 && num2==0)
				result.setText("error");
			else
				result.setText(""+answer);
		}
	}
}
