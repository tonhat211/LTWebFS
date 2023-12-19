package model;

import java.sql.Date;

public class Unit {
	public Unit() {
		super();
	}
	private int imei;
	private int productID;
	private String color;
	private String size;
	private float wattage;
	private int price;
	private int amount;
	private int yearMade;
	private String madeIn;
	private Date dateImport;
	private int available;
	public Unit(int imei, int productID, String color, String size, float wattage, int price, int amount, int yearMade,
			String madeIn, Date dateImport, int available) {
		super();
		this.imei = imei;
		this.productID = productID;
		this.color = color;
		this.size = size;
		this.wattage = wattage;
		this.price = price;
		this.amount = amount;
		this.yearMade = yearMade;
		this.madeIn = madeIn;
		this.dateImport = dateImport;
		this.available = available;
	}
	public int getImei() {
		return imei;
	}
	public void setImei(int imei) {
		this.imei = imei;
	}
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public float getWattage() {
		return wattage;
	}
	public void setWattage(float wattage) {
		this.wattage = wattage;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getYearMade() {
		return yearMade;
	}
	public void setYearMade(int yearMade) {
		this.yearMade = yearMade;
	}
	public String getMadeIn() {
		return madeIn;
	}
	public void setMadeIn(String madeIn) {
		this.madeIn = madeIn;
	}
	public Date getDateImport() {
		return dateImport;
	}
	public void setDateImport(Date dateImport) {
		this.dateImport = dateImport;
	}
	public int getAvailable() {
		return available;
	}
	public void setAvailable(int available) {
		this.available = available;
	}
	
}
