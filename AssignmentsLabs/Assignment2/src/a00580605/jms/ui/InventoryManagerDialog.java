package a00580605.jms.ui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import a00580605.jms.data.MusicalInstrument;
import a00580605.jms.data.NotAnIntegerException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;


public class InventoryManagerDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField description;
	private JTextField stockCode;
	private JTextField qtyInStock;
	private JTextField numberRented;
	private JTextField sellingPrice;
	private JTextField purchasePrice;
	private JTextField qtySold;
	private JComboBox<String> itemComboBox;
	private JButton saveBtn;
	private JButton closeButton;
	private JOptionPane op;
	private static InventoryManagerDialog dialog;
	private ArrayList<MusicalInstrument> inventoryArr = new ArrayList<MusicalInstrument>();
	private String tempDescription;
	private String tempStockCode;
	private int tempQtyInStock;
	private int tempQtySold;
	private int tempNumRented;
	private double tempPurchasePrice;
	private double tempSellingPrice;
	private final String TXTFILE = "inventory.txt";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			dialog = new InventoryManagerDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @param inventoryArr 
	 */
	public InventoryManagerDialog() {
		setBounds(100, 100, 450, 350);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder((Border) new LineBorder(Color.LIGHT_GRAY), "Inventory Item", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[84.00][grow][]", "[][][][][][][][][]"));
		{
			JLabel lblDescription = new JLabel("Description: ");
			lblDescription.setHorizontalAlignment(SwingConstants.RIGHT);
			lblDescription.setFont(new Font("Tahoma", Font.BOLD, 11));
			contentPanel.add(lblDescription, "cell 0 0,alignx right,growy");
		}
		{
			description = new JTextField();
			contentPanel.add(description, "cell 1 0 2 1,growx");
			description.setColumns(10);
		}
		{
			JLabel StockCodeLabel = new JLabel("Stock Code: ");
			StockCodeLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
			contentPanel.add(StockCodeLabel, "cell 0 1,alignx right,growy");
		}
		{
			stockCode = new JTextField();
			contentPanel.add(stockCode, "cell 1 1 3 1,grow");
			stockCode.setColumns(10);
		}
		{
			JLabel qtyInStockLabel = new JLabel("Quantity In Stock:");
			qtyInStockLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
			contentPanel.add(qtyInStockLabel, "cell 0 2,alignx right,growy");
		}
		{
			qtyInStock = new JTextField();
			contentPanel.add(qtyInStock, "cell 1 2 4 1,grow");
			qtyInStock.setColumns(10);
		}
		{
			JLabel qtySoldLabel = new JLabel("Quantity Sold: ");
			qtySoldLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
			contentPanel.add(qtySoldLabel, "cell 0 3,alignx right,growy");
		}
		{
			qtySold = new JTextField();
			contentPanel.add(qtySold, "cell 1 3 5 1,grow");
			qtySold.setColumns(10);
		}
		{
			JLabel purchasePriceLabel = new JLabel("Purchase Price: ");
			purchasePriceLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
			contentPanel.add(purchasePriceLabel, "cell 0 4,alignx right,growy");
		}
		{
			purchasePrice = new JTextField();
			contentPanel.add(purchasePrice, "cell 1 4 6 1,grow");
			purchasePrice.setColumns(10);
		}
		{
			JLabel sellingPriceLabel = new JLabel("Selling Price: ");
			sellingPriceLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
			contentPanel.add(sellingPriceLabel, "cell 0 5,alignx right,growy");
		}
		{
			sellingPrice = new JTextField();
			contentPanel.add(sellingPrice, "cell 1 5 7 1,grow");
			sellingPrice.setColumns(10);
		}
		{
			JLabel numberRentedLabel = new JLabel("Number Rented: ");
			numberRentedLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
			contentPanel.add(numberRentedLabel, "cell 0 6,alignx right,growy");
		}
		{
			numberRented = new JTextField();
			contentPanel.add(numberRented, "cell 1 6 8 1,grow");
			numberRented.setColumns(10);
		}
		{
			JLabel selectItemLabel = new JLabel("Select Item: ");
			selectItemLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
			contentPanel.add(selectItemLabel, "cell 0 7,alignx trailing,growy");
		}
		{
			itemComboBox = new JComboBox<String>();
			initializeItem();
			contentPanel.add(itemComboBox, "cell 1 7,growx");
			itemComboBox.addActionListener(new selectItemListener());
		}
		{
			saveBtn = new JButton("Save");
			saveBtn.setFont(new Font("Tahoma", Font.BOLD, 11));
			contentPanel.add(saveBtn, "cell 2 8,alignx right,growy");
			saveBtn.addActionListener(new saveBtnListener());
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new MigLayout("", "[351.00px][]", "[23px]"));
			{
				closeButton = new JButton("Close");
				closeButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//System.exit(0);
						InventoryManagerDialog.this.setVisible(false);
						InventoryManagerDialog.this.dispatchEvent(
								new WindowEvent(InventoryManagerDialog.this, WindowEvent.WINDOW_CLOSING)
						);
					}
				});
				closeButton.setFont(new Font("Tahoma", Font.BOLD, 11));
				closeButton.setActionCommand("Cancel");
				buttonPane.add(closeButton, "cell 1 0,alignx left,aligny top");
				closeButton.addActionListener(new closeBtnListener());
			}
			//setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{description, stockCode, qtyInStock, qtySold, purchasePrice, sellingPrice, numberRented, itemComboBox, saveBtn, closeButton}));
		}
	}
	
	public void setInvenArr (ArrayList<MusicalInstrument> arr) {
		inventoryArr = arr;
	}
	
	public void initializeItem(){
		DefaultComboBoxModel<String> theModel = (DefaultComboBoxModel<String>)itemComboBox.getModel();
		theModel.removeAllElements();
		itemComboBox.addItem("");
		for(MusicalInstrument m:inventoryArr){
			itemComboBox.addItem(m.getStockCode());
		}
	}
	
	private class selectItemListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if(itemComboBox.getSelectedIndex()>0) {
				description.setText(inventoryArr.get(itemComboBox.getSelectedIndex()-1).getDescription());
				stockCode.setText(inventoryArr.get(itemComboBox.getSelectedIndex()-1).getStockCode());
				qtyInStock.setText(inventoryArr.get(itemComboBox.getSelectedIndex()-1).getQuantityInStock()+"");
				qtySold.setText(inventoryArr.get(itemComboBox.getSelectedIndex()-1).getQuantitySold()+"");
				purchasePrice.setText(inventoryArr.get(itemComboBox.getSelectedIndex()-1).getPurchasePrice()+"");
				sellingPrice.setText(inventoryArr.get(itemComboBox.getSelectedIndex()-1).getSellingPrice()+"");
				numberRented.setText(inventoryArr.get(itemComboBox.getSelectedIndex()-1).getNumberRented()+"");
			}
		}
	}
	
	private class saveBtnListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			boolean ifExist = false;
			tempDescription = description.getText();
			tempStockCode = stockCode.getText();
			tempQtyInStock = getIntValue(qtyInStock);
			tempQtySold = getIntValue(qtySold);
			tempPurchasePrice = getDoubleValue(purchasePrice);
			tempSellingPrice = getDoubleValue(sellingPrice);
			tempNumRented = getIntValue(numberRented);
			
			for(MusicalInstrument m : inventoryArr) {
				if(m.getStockCode().equals(tempStockCode)) {
					if(tempSellingPrice/tempPurchasePrice >= 1.1) {
						m.setDescription(tempDescription);
						m.setQuantityInStock(tempQtyInStock);
						m.setQuantitySold(tempQtySold);
						m.setPurchasePrice(tempPurchasePrice);
						m.setSellingPrice(tempSellingPrice);
						m.setNumberRented(tempNumRented);
						ifExist = true;
						JOptionPane.showMessageDialog(op, "Item has been modified sucessfully", "Modification completed", JOptionPane.INFORMATION_MESSAGE);
					}
					else
						JOptionPane.showMessageDialog(op, "Selling price is too low", "Price error", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			if(!ifExist) {
				if(tempSellingPrice/tempPurchasePrice >= 1.1) {
					inventoryArr.add(new MusicalInstrument(tempDescription, tempStockCode, tempQtyInStock,
						tempQtySold, tempPurchasePrice, tempSellingPrice, tempNumRented));
					JOptionPane.showMessageDialog(op, "New item has been added to the inventory sucessfully", "New inventory", JOptionPane.INFORMATION_MESSAGE);
				}
				else
					JOptionPane.showMessageDialog(op, "Selling price is too low", "Price error", JOptionPane.INFORMATION_MESSAGE);
			}
			initializeItem();
		}
	}
	
	private class closeBtnListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			try {
				writeToTxt();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void writeToTxt() throws FileNotFoundException {
		//instrumentData[0], instrumentData[1], stockQty, soldQty, pPrice, sPrice, numRented
		FileOutputStream fileOut = new FileOutputStream(TXTFILE);
		PrintStream out = new PrintStream(fileOut);
		for(MusicalInstrument m : inventoryArr)
			out.print("\n" + m.getDescription() + "|" + m.getStockCode() + "|" + m.getQuantityInStock() + 
					"|" + m.getQuantitySold() + "|" + m.getPurchasePrice() + "|" + m.getSellingPrice() + 
					"|" + m.getNumberRented()
					);
		out.flush();
		out.close();
	}
	
	
	public void updateTxt(ArrayList<MusicalInstrument> arr) throws FileNotFoundException {
		//instrumentData[0], instrumentData[1], stockQty, soldQty, pPrice, sPrice, numRented
		FileOutputStream fileOut = new FileOutputStream(TXTFILE);
		PrintStream out = new PrintStream(fileOut);
		for(MusicalInstrument m : arr)
			out.print("\n" + m.getDescription() + "|" + m.getStockCode() + "|" + m.getQuantityInStock() + 
					"|" + m.getQuantitySold() + "|" + m.getPurchasePrice() + "|" + m.getSellingPrice() + 
					"|" + m.getNumberRented()
					);
		out.flush();
		out.close();
	}
	/**
	 * return the integer in the JTextField if it is an integer
	 * if the value in the textfield is not an integer, return 0
	 * @param field
	 * @return integer in the field or an 0
	 */
	private int getIntValue(JTextField field) {
		int temp;
		JOptionPane errorMsg = null;
		try {
			temp = Integer.parseInt(field.getText());
		} catch (NumberFormatException e1) {
			try {
				JOptionPane.showMessageDialog(errorMsg, "Integer value please!", "Invalid value", JOptionPane.INFORMATION_MESSAGE);
				throw new NotAnIntegerException("Integers inputs only please!");
			} catch (NotAnIntegerException e2) {
				return 0;
			}
		}
		return temp;
	}
	
	/**
	 * return the double in the JTextField if it is a double
	 * if the value in the textfield is not an double, return 0.0
	 * @param field
	 * @return integer in the field or an 0
	 */
	private double getDoubleValue(JTextField field) {
		double temp;
		JOptionPane errorMsg = null;
		try {
			temp = Double.valueOf(field.getText()).doubleValue();
		} catch (NumberFormatException e1) {
			try {
				JOptionPane.showMessageDialog(errorMsg, "Double value please!", "Invalid value", JOptionPane.INFORMATION_MESSAGE);
				throw new NotAnIntegerException("Double inputs only please!");
			} catch (NotAnIntegerException e2) {
				return 0.0;
			}
		}
		return temp;
	}
}
