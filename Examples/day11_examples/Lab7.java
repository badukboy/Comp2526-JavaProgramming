import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

public class Lab7 extends JFrame {

	private JTextField yearOfBirthField;

	private JLabel outputLabel;

	private final String OUTPUT = "Your age is: ";

	private JComboBox monthField;

	private JSpinner daySpinner;

	private final static String[] MONTHS = { "January", "February", "March",
			"April", "May", "June", "July", "August", "September", "October",
			"November", "December" };

	public static void main(String[] args) {
		Lab7 lab = new Lab7();
		lab.create();
		lab.setSize(410, 200);
		lab.setLocationRelativeTo(null);
		lab.setVisible(true);
		lab.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	Lab7() {
		super("Calculate Age");
		create();

	}

	void create() {

		Container contentPane = getContentPane();

		JPanel fieldsPanel = new JPanel();

		yearOfBirthField = new JTextField(4);
		monthField = new JComboBox(MONTHS);
		daySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 31, 1));

		fieldsPanel.setLayout(new GridLayout(2, 3));
		fieldsPanel.add(new MyLabel("Year"));
		fieldsPanel.add(new MyLabel("Month"));
		fieldsPanel.add(new MyLabel("Day"));
		fieldsPanel.add(yearOfBirthField);
		fieldsPanel.add(monthField);
		fieldsPanel.add(daySpinner);
		contentPane.add(fieldsPanel, BorderLayout.NORTH);

		JPanel actionPanel = new JPanel();

		JButton calculateAge = new JButton("Calculate Age");
		actionPanel.add(calculateAge);
		contentPane.add(actionPanel, BorderLayout.CENTER);
		calculateAge.addActionListener(new CalculateAction());
		getRootPane().setDefaultButton(calculateAge);

		JPanel displayPanel = new JPanel();
		displayPanel.setLayout(new GridLayout(1, 1));
		displayPanel.setPreferredSize(new Dimension(300, 50));
		displayPanel.setBackground(Color.LIGHT_GRAY);

		outputLabel = new JLabel(OUTPUT);
		Font font = new Font("Arial Bold", Font.ITALIC, 24);
		outputLabel.setFont(font);
		outputLabel.setVerticalAlignment(SwingConstants.CENTER);
		displayPanel.add(outputLabel);

		/* ageField = new JTextField(); */
		contentPane.add(new JPanel().add(displayPanel), BorderLayout.SOUTH);
	}

	// inner classes
	private class CalculateAction implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			try {

				int yearOfBirth = Integer.parseInt(yearOfBirthField.getText());
				int monthOfBirth = monthField.getSelectedIndex();
				int dayOfBirth = Integer.valueOf(daySpinner.getValue().toString());

				calAge(yearOfBirth, monthOfBirth, dayOfBirth);
				
				yearOfBirthField.selectAll();

			} catch (NumberFormatException nfe) {
				outputLabel.setText(OUTPUT + "INVALID YEAR INPUT");
			}
		}

		public void calAge(int yearOfBirth, int monthOfBirth, int dayOfBirth) {
			
			int ageInYears = 0;
			GregorianCalendar cal = new GregorianCalendar();
			int thisYear = cal.get(Calendar.YEAR);
			int thisMonth = cal.get(Calendar.MONTH);
			int thisDay = cal.get(Calendar.DAY_OF_MONTH);

			ageInYears = thisYear - yearOfBirth;

			if (thisMonth < monthOfBirth) {
				ageInYears--;
			} else if (thisMonth > monthOfBirth) {
				ageInYears = ageInYears;
			} else if (monthOfBirth == thisMonth) {
				if (thisDay < dayOfBirth) {
					ageInYears--;
				} else if (thisDay >= dayOfBirth) {
					ageInYears = ageInYears;
				} else {
					ageInYears = -1;
				}
			} else {
				ageInYears = -1;
			}
			
			if (monthOfBirth == thisMonth && dayOfBirth == thisDay) {
				outputLabel.setText(OUTPUT + ageInYears + ", HAPPY BIRTHDAY!");
			} else {				
				outputLabel.setText(OUTPUT + ageInYears);
			}

		}

	}

	private class MyLabel extends JLabel {
		public MyLabel(String text) {
			super(text);
			setHorizontalAlignment(SwingConstants.CENTER);
		}
	}

}
