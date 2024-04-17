package model;

import java.util.Objects;

public class Product {
	private int id;
	private String name;
	private int brandID;
	private int areaID;
	private String kind;
	private int amount;
	private String description;
	public Product(int id, String name, int brandID,int areaID, String kind, String description) {
		super();
		this.id = id;
		this.name = name;
		this.brandID = brandID;
		this.areaID = areaID;
		this.kind = kind;
		this.description = description;
	}

	public Product(int id, String name, int brandID, int areaID, String kind, int amount, String description) {
		this.id = id;
		this.name = name;
		this.brandID = brandID;
		this.areaID = areaID;
		this.kind = kind;
		this.amount = amount;
		this.description = description;
	}

	public Product(int id) {
		super();
		this.id = id;
	}


	public Product(int id, String name, int brandID, String kind, int amount, String description) {
		this.id = id;
		this.name = name;
		this.brandID = brandID;
		this.kind = kind;
		this.amount = amount;
		this.description = description;
	}

	public Product(String name, int brandID, int areaID, String kind, String description) {
		super();
		this.name = name;
		this.brandID = brandID;
		this.areaID = areaID;
		this.kind = kind;
		this.description = description;
	}

	public int getAmount() {
		return amount;
	}

	public Product() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getBrandID() {
		return brandID;
	}
	public void setBrandID(int brandID) {
		this.brandID = brandID;
	}
	public int getAreaID() {
		return areaID;
	}
	public void setAreaID(int areaID) {
		this.areaID = areaID;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String toString() {
		return this.name + "\t" + this.brandID + "\t" + this.areaID+ "\t" + this.kind + "\t" + this.description;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return id == other.id;
	}
	
	public static void main(String[] args) {
		Product p1 = new Product(1,"nhat", 1, 1, "A", "hhhhhhhhhhhhhhhhhhhh");
		Product p2 = new Product(2);
		
		System.out.println(p1.equals(p2));
	}
	
}	
