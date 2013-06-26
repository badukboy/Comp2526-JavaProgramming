package a00580605.jms.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.SpinnerNumberModel;

import a00580605.jms.data.Customer;
import a00580605.jms.data.MusicalInstrument;
import a00580605.jms.data.RentalAgreement;
import a00580605.jms.util.DateUtil;
import a00580605.jms.util.ItemSorter;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;


public class RentalDialog extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel firstNameLabel;
	private JLabel lastNameLabel;
	private JLabel creditCardNumLabel;
	private JLabel addressLabel;
	private JLabel phoneNumLabel;
	private JLabel dateLabel;
	private JLabel itemLabel;
	private JLabel numOfDaysLabel;
	private JLabel dailyFeeLabel;
	private JLabel totalFeeLabel;
	private JTextField firstName;
	private JTextField lastName;
	private JTextField creditCardNum;
	private JTextField address;
	private JTextField phoneNum;
	private JTextField currentDate;
	private JComboBox<String> item;
	private JSpinner numOfDays;
	private JTextField dailyFee;
	private JTextField totalFee;
	private JButton clearBtn;
	private JButton saveBtn;
	private JOptionPane op;
	
	private ArrayList<MusicalInstrument>inventoryArr = new ArrayList<MusicalInstrument>();
	private ArrayList<RentalAgreement>rentArr = new ArrayList<RentalAgreement>();
	private GregorianCalendar today = new GregorianCalendar();
	private final String RENTSFILE = "rentals.txt";
	
	//temp variables for creating RentalAgreement object
	private Customer cust;
	private GregorianCalendar tempDate;
	private MusicalInstrument tempInstrument;
	private double tempDailyRent;
	private int tempDays;
	private double tempTotal;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RentalDialog dialog = new RentalDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RentalDialog() {
		setBounds(100, 100, 431, 470);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[grow][][]", "[180.00,grow][]"));
		{
			JPanel custInfoPanel = new JPanel();
			custInfoPanel.setBorder(new TitledBorder(null, "Customer Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			JPanel rentInfoPanel = new JPanel();
			rentInfoPanel.setBorder(new TitledBorder(null, "Rental Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(custInfoPanel, "flowy,cell 0 0,growx");
			custInfoPanel.setLayout(new MigLayout("", "[right][203.00,grow,fill][][89.00]", "[][][][][][29.00]"));
			{
				firstNameLabel = new JLabel("First Name:");
				custInfoPanel.add(firstNameLabel, "cell 0 0,alignx right,growy");
			}
			{
				firstName = new JTextField();
				custInfoPanel.add(firstName, "cell 1 0 3 1,alignx left");
			}
			{
				lastNameLabel = new JLabel("Last Name:");
				custInfoPanel.add(lastNameLabel, "cell 0 1,alignx trailing,growy");
			}
			{
				lastName = new JTextField();
				custInfoPanel.add(lastName, "cell 1 1 3 1,alignx left");
			}
			{
				creditCardNumLabel = new JLabel("Credit Card #:");
				custInfoPanel.add(creditCardNumLabel, "cell 0 2,alignx trailing,growy");
			}
			{
				creditCardNum = new JTextField();
				custInfoPanel.add(creditCardNum, "cell 1 2 3 1,alignx left");
			}
			{
				addressLabel = new JLabel("Address:");
				custInfoPanel.add(addressLabel, "cell 0 3,alignx trailing,growy");
			}
			{
				address = new JTextField();
				custInfoPanel.add(address, "cell 1 3 3 1,alignx left");
			}
			{
				phoneNumLabel = new JLabel("Phone Number:");
				custInfoPanel.add(phoneNumLabel, "cell 0 4,alignx trailing,growy");
			}
			{
				phoneNum = new JTextField();
				custInfoPanel.add(phoneNum, "cell 1 4 3 1,alignx left");
			}
			{
				clearBtn = new JButton();
				clearBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						firstName.setText("");
						lastName.setText("");
						creditCardNum.setText("");
						address.setText("");
						phoneNum.setText("");						
					}
				});
				clearBtn.setText("Clear");
				custInfoPanel.add(clearBtn, "cell 2 5,alignx right,growy");
			}
			{
				saveBtn = new JButton();
				saveBtn.setText("Save");
				custInfoPanel.add(saveBtn, "cell 3 5,alignx right,growy");
				saveBtn.addActionListener(new saveBtnListener());
			}
			
			//rent info panel
			contentPanel.add(rentInfoPanel, "flowy,cell 0 1,grow");
			rentInfoPanel.setLayout(new MigLayout("", "[][77.00][214.00][-151.00]", "[][][][][]"));
			{
				dateLabel = new JLabel("Date:");
				rentInfoPanel.add(dateLabel, "cell 0 0,alignx right,growy");
			}
			{
				currentDate = new JTextField();
				currentDate.setEditable(false);
				rentInfoPanel.add(currentDate, "cell 1 0,growx");
				//Date date = new Date();
				//currentDate.setText(f.format(date));
				currentDate.setText(DateUtil.format(today));
			}
			{
				itemLabel = new JLabel("Item:");
				rentInfoPanel.add(itemLabel, "cell 0 1,alignx trailing,growy");
			}
			{
				item = new JComboBox<String>();
				rentInfoPanel.add(item, "cell 1 1 2 1,growx");
			}
			{
				numOfDaysLabel = new JLabel("# of Days:");
				rentInfoPanel.add(numOfDaysLabel, "cell 0 2,alignx right,growy");
			}
			{
				numOfDays = new JSpinner();
				numOfDays.setModel(new SpinnerNumberModel(1, 1, 31, 1));
				rentInfoPanel.add(numOfDays, "cell 1 2,alignx left");
			}
			{
				dailyFeeLabel = new JLabel("Daily Fee:");
				rentInfoPanel.add(dailyFeeLabel, "cell 0 3,alignx right,growy");
			}
			{
				dailyFee = new JTextField();
				rentInfoPanel.add(dailyFee, "cell 1 3,growx");
				dailyFee.addFocusListener(new test());
			}
			{
				totalFeeLabel = new JLabel("Total Fee:");
				rentInfoPanel.add(totalFeeLabel, "cell 0 4,alignx trailing,growy");
			}
			{
				totalFee = new JTextField();
				rentInfoPanel.add(totalFee, "cell 1 4,growx");
				totalFee.setEditable(false);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton closeButton = new JButton("Close");
				closeButton.addActionListener(new ActionListener() {	//close button action listener
					public void actionPerformed(ActionEvent e) {
						try {
							writeToTxtListener();		// write the rental agreements to the text file
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						RentalDialog.this.setVisible(false);	//close the dialog
						RentalDialog.this.dispatchEvent(
								new WindowEvent(RentalDialog.this, WindowEvent.WINDOW_CLOSING)
						);
					}
				});
				closeButton.setActionCommand("Cancel");
				buttonPane.add(closeButton);
			}
		}
	}
	
	/**
	 * initialize all the possible item for rental
	 * @param arr
	 */
	public void setInvenArr (ArrayList<MusicalInstrument> arr) {
		inventoryArr = arr;
	}
	
	/**
	 * initialize the combobox with all the possible items
	 */
	public void initializeItem(){
		DefaultComboBoxModel<String> theModel = (DefaultComboBoxModel<String>)item.getModel();
		theModel.removeAllElements();	//first remove everything
		Collections.sort(inventoryArr, new ItemSorter.SortByDescription());	//sort the inventoryArr incase new items are added
		item.addItem("");	//add the first item, empty string
		for(MusicalInstrument m:inventoryArr){
			item.addItem(m.getDescription() + " " + m.getStockCode());	//add each item into the combobox
		}
	}
	
	private class test implements FocusListener {
		public void focusGained(FocusEvent arg0) {}

		public void focusLost(FocusEvent arg0) {
			double rentalFee = Integer.parseInt(numOfDays.getValue().toString()) * Double.valueOf(dailyFee.getText());
			totalFee.setText(rentalFee+"");
		}
	}
	
	/**
	 * action listener for the save button
	 * create a customer and the rental agreement and use them to create an agreement
	 * @author Steve
	 *
	 */
	private class saveBtnListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			//Customer(String fName, String lName, String cc, String ad, String phoneNo) //create a customer using the information given
			cust = new Customer(firstName.getText(), lastName.getText(), creditCardNum.getText(), address.getText(), phoneNum.getText());
			//RentalAgreement(Customer customer, GregorianCalendar date, MusicalInstrument instrumentRented)
			if(item.getSelectedIndex()>0) {
				tempDate = DateUtil.convertFromDMY(DateUtil.format(today));
				tempInstrument = inventoryArr.get(item.getSelectedIndex()-1);
				tempDailyRent = Double.valueOf(dailyFee.getText());
				tempDays = Integer.parseInt(numOfDays.getValue().toString());
				tempTotal = Double.valueOf(totalFee.getText());
				//RentalAgreement(Customer customer, GregorianCalendar date, MusicalInstrument instrumentRented, 
				//					double dailyRent, int rentDays, double totalRent)
				rentArr.add(new RentalAgreement(cust, tempDate, tempInstrument, tempDailyRent, tempDays, tempTotal));
				JOptionPane.showMessageDialog(op, "Rental agreement has been submitted sucessfully", "Rental accepted", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	
	/**
	 * write the rental agreements into the text file.
	 * @throws FileNotFoundException
	 */
	public void writeToTxtListener() throws FileNotFoundException {
		//instrumentData[0], instrumentData[1], stockQty, soldQty, pPrice, sPrice, numRented
		/*FileOutputStream fileOut = new FileOutputStream(RENTSFILE);
		PrintStream out = new PrintStream(fileOut);
		for(RentalAgreement r : rentArr) {
			out.print(r.getCustomer().toString() + 
					"\nDate: " + DateUtil.format(r.getRentalDate()) + 
					"\nInstrument Rented: " + r.getInstrumentRented().getDescription() + 
					"(" + r.getInstrumentRented().getStockCode() + ")" +
					"\nRent per day: $" + r.getRentalFeePerDay() + 
					"\nNumber of days: " + r.getNumberOfDaysRented() +
					"\nTotal rental cost: $" + r.getTotalRentalFee() + "\n\n");
		}
		out.flush();
		out.close();*/
		writeToFile();
	}
	
	public void writeToFile() throws FileNotFoundException {
		FileOutputStream fileOut = new FileOutputStream(RENTSFILE);
		PrintStream out = new PrintStream(fileOut);
		for(RentalAgreement r : rentArr) {
			out.print(r.getCustomer().toString() + 
					"\nDate: " + DateUtil.format(r.getRentalDate()) + 
					"\nInstrument Rented: " + r.getInstrumentRented().getDescription() + 
					"(" + r.getInstrumentRented().getStockCode() + ")" +
					"\nRent per day: $" + r.getRentalFeePerDay() + 
					"\nNumber of days: " + r.getNumberOfDaysRented() +
					"\nTotal rental cost: $" + r.getTotalRentalFee() + "\n\n");
		}
		out.flush();
		out.close();
	}
	
	/**
	 * return the rental agreement arraylist
	 * @return
	 */
	public ArrayList<RentalAgreement> getRentalList() {
		return rentArr;
	}
}
