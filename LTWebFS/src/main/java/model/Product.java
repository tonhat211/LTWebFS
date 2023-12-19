package model;

public class Product {
	private int id;
	private String name;
	private int brandID;
	private String kind;
	private String description;
	public Product(int id, String name, int brandID, String kind, String description) {
		super();
		this.id = id;
		this.name = name;
		this.brandID = brandID;
		this.kind = kind;
		this.description = description;
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
	
}	
