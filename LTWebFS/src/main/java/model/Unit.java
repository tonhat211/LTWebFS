package model;

import java.sql.Date;
import java.util.Calendar;
import java.util.Collections;
import java.util.Objects;

public class Unit{
	public Unit() {
		super();
	}
	private int imei;
	private int productID;
	private String color;
	private String size;
	private double wattage;
	private double price;
	private int amount;
	private int yearMade;
	private Datee dateImport;
	private int available;

	public Unit(int imei, int productID, String color, String size, double wattage, double price, int yearMade, Datee dateImport, int available) {
		this.imei = imei;
		this.productID = productID;
		this.color = color;
		this.size = size;
		this.wattage = wattage;
		this.price = price;
		this.yearMade = yearMade;
		this.dateImport = dateImport;
		this.available = available;
	}

	public Unit(String color, String size, double wattage, double price, int amount, int yearMade, Datee dateImport) {
		this.color = color;
		this.size = size;
		this.wattage = wattage;
		this.price = price;
		this.amount = amount;
		this.yearMade = yearMade;
		this.dateImport = dateImport;
	}

	public Unit(int imei, int productID, String color, String size, double wattage, double price, int amount, int yearMade,
				Datee dateImport, int available) {
		super();
		this.imei = imei;
		this.productID = productID;
		this.color = color;
		this.size = size;
		this.wattage = wattage;
		this.price = price;
		this.amount = amount;
		this.yearMade = yearMade;
		this.dateImport = dateImport;
		this.available = available;
	}

	public Unit(int imei, int productID, String color, String size, double wattage, double price, int amount, int yearMade,
				String dateImport, int available) {
		super();
		this.imei = imei;
		this.productID = productID;
		this.color = color;
		this.size = size;
		this.wattage = wattage;
		this.price = price;
		this.amount = amount;
		this.yearMade = yearMade;
		String[] dateImportTokns = dateImport.split("-");
		int year = Integer.parseInt(dateImportTokns[0]);
		int month = Integer.parseInt(dateImportTokns[1]);
		int day = Integer.parseInt(dateImportTokns[2]);
		Datee d = new Datee(day,month,year);
		this.dateImport = d;
		this.available = available;
	}


	
	
	public Unit(int productID) {
		super();
		this.productID = productID;
	}
	
	


	@Override
	public int hashCode() {
		return Objects.hash(productID);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Unit other = (Unit) obj;
		return productID == other.productID;
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
	public double getWattage() {
		return wattage;
	}
	public void setWattage(double wattage) {
		this.wattage = wattage;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
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

	public Datee getDateImport() {
		return dateImport;
	}
	public void setDateImport(Datee dateImport) {
		this.dateImport = dateImport;
	}
	public int getAvailable() {
		return available;
	}
	public void setAvailable(int available) {
		this.available = available;
	}
	
	public String  toString() {
		return this.imei + "\t" + this.productID + "\t" + this.color + "\t" + this.size + "\t"
				+ this.wattage + "\t" + this.price + "\t" + this.amount + "\t"  + this.yearMade + "\t"+ this.dateImport + "\t" + this.available;
	}
	
}
