package a00580605.jms.data;

public class MusicalInstrument {
	private String description;
	private String stockCode;
	private int quantityInStock;
	private int quantitySold;
	private double purchasePrice;
	private double sellingPrice;
	private int numberRented;
	
	public MusicalInstrument() {
		this.quantityInStock = 0;
		this.quantitySold = 0;
		this.purchasePrice = 0;
		this.sellingPrice = 0;
		this.numberRented = 0;
		this.description = null;
		this.stockCode = null;
	}
	
	public MusicalInstrument(String desc, String stockC, double pPrice, double sPrice) {
		this.quantityInStock = 0;
		this.quantitySold = 0;
		this.purchasePrice = 0;
		this.sellingPrice = 0;
		this.numberRented = 0;
		this.description = desc;
		this.stockCode = stockC;
		
		if(pPrice >= 1 && sPrice >= 1) {
			if(pPrice > 1 && sPrice <= 1) {
				sellingPrice = 1;
				purchasePrice = pPrice;
			}
			else if(pPrice <= 1 && sPrice > 1) {
				purchasePrice = 1;
				sellingPrice = sPrice;
			}
			
			if(pPrice/sPrice >= 1){
				this.description = desc;
				this.stockCode = stockC;
			}
		}
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	public int getQuantityInStock() {
		return quantityInStock;
	}

	public void setQuantityInStock(int quantityInStock) {
		this.quantityInStock = quantityInStock;
	}

	public int getQuantitySold() {
		return quantitySold;
	}

	public void setQuantitySold(int quantitySold) {
		this.quantitySold = quantitySold;
	}

	public double getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(double purchasePrice) {
		if(purchasePrice >= 1)
			this.purchasePrice = purchasePrice;
	}

	public double getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(double sellingPrice) {
		if(sellingPrice >= getPurchasePrice()*1.1)
			this.sellingPrice = sellingPrice;
	}

	public int getNumberRented() {
		return numberRented;
	}
	
	public void setNumberRented(int i) {
		if(i < (double)((double)(this.quantityInStock)/2))
			this.numberRented = i;		
	}

	public void restock(int i) {
		if(i<1)
			System.out.println("Invalid restock quantity.\n");
		else
			this.quantityInStock += i;
	}

	public void sell(int i) {
		if(i>0 && i<500){
			this.quantityInStock -= i;
			this.quantitySold += i;
		}
		else
			System.out.println("Invalid quantity being sold.");	
	}
	
	public double calcProfit() {
		//profit is the difference of purchase and selling price
		return this.sellingPrice - this.purchasePrice;
	}
	
	public double calcTotalProfit() {
		return this.quantitySold * calcProfit();
	}
	
	public double calcInventoryValue() {
		return this.quantityInStock*calcProfit();
	}

	public void printDetails() {
		System.out.println(
				"Description: " + getDescription() +
				"\n\tStock code: " + getStockCode() +
				"\n\tPurchase price: " + getPurchasePrice() +
				"\n\tSelling price: " + getSellingPrice() +
				"\n\tQuantity in stock: " + getQuantityInStock() +
				"\n\tQuantity sold: " + getQuantitySold() +
				"\n\tInventory value: " + calcInventoryValue() +
				"\n\tProfit on sales: " + calcTotalProfit() +
				"\n\tNumber of instruments rented: " + getNumberRented()
		);
	}

	@Override
	public String toString() {
		return "MusicalInstrument [description=" + description + ", stockCode="
				+ stockCode + ", quantityInStock=" + quantityInStock
				+ ", quantitySold=" + quantitySold + ", purchasePrice="
				+ purchasePrice + ", sellingPrice=" + sellingPrice
				+ ", numberRented=" + numberRented + "]";
	}

}
