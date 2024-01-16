package model;

public class ProductHeader {
	private int ID;
	private int brandID;
	private String name;
	private double price;
	private String imageUrl;
	
	private String priceString;

	public ProductHeader(Product p, Unit u, Image i) {
		super();
		this.ID = p.getId();
		this.brandID = p.getBrandID();
		this.name = p.getName();
		this.price = u.getPrice();
		this.imageUrl = i.getUrl();
	}

	public int getBrandID() {
		return brandID;
	}
	public void setBrandID(int iD) {
		brandID = iD;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getPriceString() {
		return priceString;
	}
	public void setPriceString(String priceString) {
		this.priceString = priceString;
	}
	
	
	
	public String toString() {
		return this.ID + "\t" + this.name + "\t" + this.price +"\t" + this.imageUrl;
	} 
	
	
	
	
}
