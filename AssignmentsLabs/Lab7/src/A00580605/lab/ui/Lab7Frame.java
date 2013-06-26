package A00580605.lab.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class Lab7Frame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField result;

	/**
	 * Create the frame.
	 */
	public Lab7Frame() {
		setTitle("Simple Calculator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][358.00,grow]", "[][][][][8.00][][][grow][][]"));
		
		JLabel num1Label = new JLabel("Number 1");
		contentPane.add(num1Label, "cell 0 1");
		
		JSpinner num1Spinner = new JSpinner();
		num1Spinner.setModel(new SpinnerNumberModel(0, -10, 10, 1));
		contentPane.add(num1Spinner, "cell 2 1,grow");
		
		JLabel operationLabel = new JLabel("Operation");
		contentPane.add(operationLabel, "cell 0 2,alignx trailing");
		
		JComboBox<String> operationComboBox = new JComboBox<String>();
		operationComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"+", "-", "*", "/"}));
		operationComboBox.setMaximumRowCount(4);
		contentPane.add(operationComboBox, "cell 2 2,growx");
		
		JLabel num2Label = new JLabel("Number 2");
		contentPane.add(num2Label, "flowy,cell 0 3");
		
		JSpinner num2Spinner = new JSpinner();
		num2Spinner.setModel(new SpinnerNumberModel(0, -10, 10, 1));
		contentPane.add(num2Spinner, "cell 2 3,grow");
		
		JSeparator separator1 = new JSeparator();
		separator1.setBackground(Color.GRAY);
		separator1.setForeground(Color.GRAY);
		contentPane.add(separator1, "cell 0 4,growx");
		
		JSeparator separator2 = new JSeparator();
		separator2.setBackground(Color.GRAY);
		separator2.setForeground(Color.GRAY);
		contentPane.add(separator2, "cell 2 4,growx");
		
		JLabel equalsLabel = new JLabel("=");
		contentPane.add(equalsLabel, "cell 0 6,alignx right");
		
		result = new JTextField();
		result.setEditable(false);
		contentPane.add(result, "cell 2 6,grow");
		result.setColumns(10);
		
		JButton calculateBtn = new JButton("Calculate");
		calculateBtn.setVerticalAlignment(SwingConstants.BOTTOM);
		contentPane.add(calculateBtn, "cell 0 8 3 2,alignx center,aligny bottom");
	}

}
