package a00580605.jms.ui;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.BorderFactory;

import net.miginfocom.swing.MigLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import a00580605.jms.data.MusicalInstrument;
import a00580605.jms.data.NotAnIntegerException;
import a00580605.jms.data.RentalAgreement;
import a00580605.jms.data.ShoppingCart;
import a00580605.jms.util.ItemSorter;

import java.awt.event.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class JMSFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<String> itemSelecComboBox = new JComboBox<String>();
	private JTextField sellingPrice;
	private JTextField qty;
	private JTextField rentTotDisplay;
	private JTextField finalTotal;
	private JTextField salesTot;
	private JTextArea purchasedDisplay;
	private JTextArea rentalDisplay;
	
	private JOptionPane op;
	private JOptionPane errorMsg;
	private ArrayList<MusicalInstrument> inventoryArr;
	private String FILENAME = "inventory.txt";
	private String line;
	private String[] instrumentData;
	private double pPrice;
	private double sPrice;
	private int stockQty;
	private int soldQty; 
	private int numRented;
	private double salesTotal = 0;
	private double rentTotal = 0;
	private double finalSales = 0;
	private double salesPrice = 0;
	private double unitPrice = 0;	//price of one instrument
	private int pQty; //purchase quantity
	private int qtyError = 0;
	private InventoryManagerDialog inDialog = new InventoryManagerDialog();
	private RentalDialog rentDialog = new RentalDialog();
	private ShoppingCart cart;// = new ShoppingCart();
	private ArrayList<RentalAgreement> rentList;
	
	/**
	 * Create the frame.
	 * @throws FileNotFoundException 
	 */
	public JMSFrame() throws FileNotFoundException {
		cart = new ShoppingCart();
		rentList = new ArrayList<RentalAgreement>();
		createDataListFromText();
		
		setTitle("Java Music Store");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 469);
		setLocationRelativeTo(null);
		BorderFactory.createTitledBorder("Select Items");

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic('F');
		menuBar.add(fileMenu);
		
		JMenuItem menuSaveData = new JMenuItem("Save Data");
		menuSaveData.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		fileMenu.add(menuSaveData);
		menuSaveData.addActionListener(new saveBtnListener());
		
		JMenuItem menuRentalAgreements = new JMenuItem("Rental Agreements");
		menuRentalAgreements.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
		fileMenu.add(menuRentalAgreements);
		
		JMenuItem menuExit = new JMenuItem("Exit");
		menuExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
		fileMenu.add(menuExit);
		menuExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		
		JMenu ManageMenu = new JMenu("Manage");
		menuBar.add(ManageMenu);
		
		JMenuItem menuRental = new JMenuItem("Rental");
		menuRental.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
		ManageMenu.add(menuRental);
		menuRental.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rentDialog = new RentalDialog();
				rentDialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				rentDialog.setLocationRelativeTo(null);
				rentDialog.setInvenArr(inventoryArr);
				rentDialog.initializeItem();
				rentDialog.setVisible(true);
				rentDialog.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent e) {
						//System.out.println(inDialog.getTitle() + " window closing");
						//reloadComboBoxes();
						rentList = rentDialog.getRentalList();
						fillRentalCart();
						/*try {
							inDialog.updateTxt(inventoryArr);
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						finally {
						}*/
							showAgreements();
					}
				});
			}
		});
		
		JMenuItem menuInventory = new JMenuItem("Inventory");
		menuInventory.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_MASK));
		ManageMenu.add(menuInventory);
		menuInventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inDialog = new InventoryManagerDialog();
				inDialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				inDialog.setLocationRelativeTo(null);
				inDialog.setInvenArr(inventoryArr);
				inDialog.initializeItem();
				inDialog.setVisible(true);
				inDialog.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent e) {
						reloadComboBoxes();
					}
				});
			}
		});
		
		JMenu HelpMenu = new JMenu("Help");
		menuBar.add(HelpMenu);
		
		JMenuItem menuAbout = new JMenuItem("About");
		menuAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
		HelpMenu.add(menuAbout);
		menuAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(op, "COMP 2526 Assignment 2\nby Steve Lo, A00580605", "About", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][93.00,grow][48.00][42.00][grow][][]", "[43.00,grow][grow][][][40.00,grow][][grow][]"));
		
		JPanel ItemSelectPane = new JPanel();
		ItemSelectPane.setBorder(new TitledBorder(new LineBorder(Color.LIGHT_GRAY), "Select Items", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(ItemSelectPane, "cell 0 0 7 1,grow");
		ItemSelectPane.setLayout(new MigLayout("", "[][121.00,grow][grow][][grow][][][]", "[]"));
		
		JLabel ItemsLabel = new JLabel("Items:");
		ItemSelectPane.add(ItemsLabel, "cell 0 0,alignx trailing,growy");
		
		ItemSelectPane.add(itemSelecComboBox, "cell 1 0,growx");
		itemSelecComboBox.addActionListener(new itemChoiceListener());
		
		sellingPrice = new JTextField();
		sellingPrice.setBorder(null);
		sellingPrice.setText("$0.00");
		sellingPrice.setHorizontalAlignment(SwingConstants.CENTER);
		sellingPrice.setEditable(false);
		ItemSelectPane.add(sellingPrice, "cell 2 0,growx");
		sellingPrice.setColumns(10);
		
		JLabel QtyLabel = new JLabel("Qty:");
		ItemSelectPane.add(QtyLabel, "cell 3 0,alignx trailing,growy");
		
		qty = new JTextField();
		ItemSelectPane.add(qty, "cell 4 0,growx");
		qty.setColumns(10);
		
		JButton addBtn = new JButton("Add");
		ItemSelectPane.add(addBtn, "cell 6 0");
		addBtn.addActionListener(new addItemListener());
		
		JPanel PurchasedPane = new JPanel();
		PurchasedPane.setFont(new Font("Tahoma", Font.BOLD, 11));
		PurchasedPane.setBorder(new TitledBorder(new LineBorder(Color.LIGHT_GRAY), "Purchased Items", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(PurchasedPane, "cell 0 1 7 3,grow");
		PurchasedPane.setLayout(new MigLayout("", "[282.00,grow][grow]", "[-53.00,grow][71.00,grow][][][][]"));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setAutoscrolls(true);
		PurchasedPane.add(scrollPane, "cell 0 1 1 5,grow");
		
		purchasedDisplay = new JTextArea();
		purchasedDisplay.setEditable(false);
		scrollPane.setViewportView(purchasedDisplay);
		
		JLabel salesTotLabel = new JLabel("SALES TOTAL");
		salesTotLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		PurchasedPane.add(salesTotLabel, "cell 1 2");
		
		salesTot = new JTextField();
		salesTot.setEditable(false);
		PurchasedPane.add(salesTot, "cell 1 3,growx");
		salesTot.setColumns(10);
		
		JPanel RentalAgreePane = new JPanel();
		RentalAgreePane.setFont(new Font("Tahoma", Font.BOLD, 11));
		RentalAgreePane.setBorder(new TitledBorder(new LineBorder(Color.LIGHT_GRAY), "Rental Agreements", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(RentalAgreePane, "cell 0 4 7 2,grow");
		RentalAgreePane.setLayout(new MigLayout("", "[292.00,grow][grow]", "[grow][44.00,grow][][][]"));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		RentalAgreePane.add(scrollPane_1, "cell 0 1 1 3,grow");
		
		rentalDisplay = new JTextArea();
		rentalDisplay.setEditable(false);
		scrollPane_1.setViewportView(rentalDisplay);
		
		
		JLabel rentTotalLabel = new JLabel("RENT TOTAL");
		rentTotalLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		RentalAgreePane.add(rentTotalLabel, "cell 1 2");
		
		rentTotDisplay = new JTextField();
		rentTotDisplay.setEditable(false);
		RentalAgreePane.add(rentTotDisplay, "cell 1 3,growx");
		rentTotDisplay.setColumns(10);
		
		JPanel summaryPane = new JPanel();
		summaryPane.setBorder(new TitledBorder(new LineBorder(Color.LIGHT_GRAY), "Purchase Summary", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(summaryPane, "cell 0 6 7 2,grow");
		summaryPane.setLayout(new MigLayout("", "[282.00][grow]", "[][]"));
		
		JLabel finalLabel = new JLabel("FINAL SALES");
		finalLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		summaryPane.add(finalLabel, "cell 1 0,growy");
		
		finalTotal = new JTextField();
		finalTotal.setEditable(false);
		summaryPane.add(finalTotal, "cell 1 1,grow");
		finalTotal.setColumns(10);
		
		setItemChoiceCombo();
		//itemSelecComboBox.addItem("");
		//for(MusicalInstrument m:inventoryArr)
		//	itemSelecComboBox.addItem(m.getDescription());
	}
	
	private void reloadComboBoxes() {
		try {
			createDataListFromText();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setItemChoiceCombo();
	}
	
	/**
	 * create an arraylist of type MusicalInstrument and fill that up using
	 * the data from the inventory.txt file.
	 * @throws FileNotFoundException
	 */
	private void createDataListFromText() throws FileNotFoundException {
	    File file = new File(FILENAME);
		inventoryArr = new ArrayList<MusicalInstrument>();
		
	    
		if(!file.exists()){
	    	System.err.println("File " + FILENAME + " does not exist");
	    	System.exit(0);
	    }
	    Scanner input = new Scanner(new File(FILENAME));
	    
	    //String desc, String stockC, int stockQty, int soldQty, double pPrice, double sPrice, int numRented
	    while(input.hasNext()) {
	    	line = input.next();					//read the data from the file line by line
		    instrumentData = line.split("\\|");		//split the line into separate attributes for the constructors
	    	if(ifFullInstruDetail(line) == 3) {
		    	pPrice = Double.valueOf(instrumentData[2]).doubleValue();
		    	sPrice = Double.valueOf(instrumentData[3]).doubleValue();											//create and add the musical
		    	inventoryArr.add(new MusicalInstrument(instrumentData[0], instrumentData[1], 0, 0, pPrice, sPrice, 0));//instrument into the arraylist
	    	}
	    	if(ifFullInstruDetail(line) == 6) {
	    		pPrice = Double.valueOf(instrumentData[4]).doubleValue();
		    	sPrice = Double.valueOf(instrumentData[5]).doubleValue();
		    	stockQty = Integer.parseInt(instrumentData[2]);
		    	soldQty = Integer.parseInt(instrumentData[3]);
		    	numRented = Integer.parseInt(instrumentData[6]);
		    	inventoryArr.add(																					//create and add the musical
		    		new MusicalInstrument(instrumentData[0], instrumentData[1], stockQty, soldQty, pPrice, sPrice, numRented)	//instrument into the arraylist
		    	);
	    	}
	    }
		Collections.sort(inventoryArr, new ItemSorter.SortByDescription());	//when everything from the file is added to the arraylist, sort it.
	}
	
	/**
	 * Set and update the available choices from the item combobox
	 */
	private void setItemChoiceCombo() {
		DefaultComboBoxModel<String> theModel = (DefaultComboBoxModel<String>)itemSelecComboBox.getModel();
		theModel.removeAllElements();	//empty the combobox
		itemSelecComboBox.addItem("");	//add an empty string as the first element in the combobox
		for(MusicalInstrument m:inventoryArr)
			itemSelecComboBox.addItem(m.getDescription());	//add the descriptions into the combobox
	}
	
	/**
	 * ActionListener for choosing an option from the item combobox
	 * @author Steve
	 *
	 */
	private class itemChoiceListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(itemSelecComboBox.getSelectedIndex()>0)
				sellingPrice.setText("$" + inventoryArr.get(itemSelecComboBox.getSelectedIndex()-1).getSellingPrice());
		}
	}
	
	/**
	 * ActionListener for the add button
	 * item will be added to the shopping cart, the price will be shown on the purchase item display
	 * @author Steve
	 *
	 */
	private class addItemListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//purchasedDisplay
			try {
				pQty = Integer.parseInt(qty.getText());	//make sure the user enters an integer as the quantity
				qtyError = 0;
			} catch (NumberFormatException e1) {	//error message is displayed if it is not a valid quantity
				try {
					qty.setText("");
					JOptionPane.showMessageDialog(errorMsg, "Integer quantity please!", "Invalid Quantity", JOptionPane.INFORMATION_MESSAGE);
					throw new NotAnIntegerException("Integers inputs only please!");
				} catch (NotAnIntegerException e2) {
					// TODO Auto-generated catch block
					qtyError = 1;
					e2.printStackTrace();
				}
			}
			if(qtyError == 0) {	//if the quantity is valid, results will be shown in the displays
				if(itemSelecComboBox.getSelectedIndex()>0)
					unitPrice = inventoryArr.get(itemSelecComboBox.getSelectedIndex()-1).getSellingPrice();
				salesPrice = pQty*unitPrice;
				salesTotal += salesPrice;
				finalSales = rentTotal + salesTotal;
				if(itemSelecComboBox.getSelectedIndex()>0)
					purchasedDisplay.append(inventoryArr.get(itemSelecComboBox.getSelectedIndex()-1).getDescription() + "\t$" + unitPrice + " X" + pQty + "\n");
				salesTot.setText("$" + salesTotal);
				finalTotal.setText("$" + finalSales);
				cart.addToPurchaseCart(inventoryArr.get(itemSelecComboBox.getSelectedIndex()-1), pQty);
				qty.setText("");
			}
		}
	}
	
	/**
	 * ActionListener for the save button. Not sure what and where should be saved, so the purchase and rental information
	 * are saved in the receipt.txt file.
	 * @author Steve
	 *
	 */
	private class saveBtnListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			/*FileOutputStream fileOut = null;
			try {
				fileOut = new FileOutputStream(TXTFILE);
			} catch (FileNotFoundException e) {
				System.out.println(TXTFILE + " is not found");
				e.printStackTrace();
			}
			PrintStream out = new PrintStream(fileOut);
			out.print(cart.viewPurchase());
			out.print(cart.viewRental());
			out.flush();
			out.close();
			JOptionPane.showMessageDialog(op, "Receipt has been saved", "Save completed", JOptionPane.INFORMATION_MESSAGE);*/
			try {
				createDataListFromText();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			for(MusicalInstrument m:inventoryArr)
				if(m.getNumberRented()>0)
					System.out.println(m.getDescription());
			try {
				inDialog.writeToTxt();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("printed");
			/*try {
				inDialog.writeToTxt();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				rentDialog.writeToTxtListener();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		}
	}
	
	/**
	 * check and return how many attributes are given in the line
	 * @param line
	 * @return
	 */
	private int ifFullInstruDetail(String line) {
		int count=0;
		for(int i=0; i<line.length(); i++)
			if(line.charAt(i)=='|')	//simply count the number of | character
				count++;
		return count;
	}
	
	/**
	 * add each rental agreement into the shopping cart.
	 */
	private void fillRentalCart() {
		for(RentalAgreement r : rentList) {
			cart.addToRentalCart(r);
			r.getInstrumentRented().itemRented();
		}
	}
	
	/**
	 * display the rental agreements
	 */
	private void showAgreements() {
		//rentalDisplay rentTotDisplay finalTotal
		for(RentalAgreement r : rentList) {
		System.out.println("4");
			rentalDisplay.append(r.getInstrumentRented().getDescription() + "\t$" + r.getRentalFeePerDay() + "/day\t" + r.getNumberOfDaysRented() + "days");
			rentTotal += r.getTotalRentalFee();
			rentTotDisplay.setText("$" + rentTotal);
			finalSales = rentTotal + salesTotal;
			finalTotal.setText("$" + finalSales);
		}
	}
}
