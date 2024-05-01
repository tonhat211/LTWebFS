package model;

public class Brand {
	private int id;
	private String name;
	private String country;
	private int available;
	public Brand(int id, String name,String country, int available) {
		super();
		this.id = id;
		this.name = name;
		this.country = country;
		this.available = available;
	}
	public Brand() {
		super();
	}
	
	public Brand(String country) {
		super();
		this.country = country;
	}

	public Brand(int id) {
		this.id = id;
	}

	public Brand(String name, String country) {
		super();
		this.name = name;
		this.country = country;
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
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getAvailable() {
		return available;
	}
	public void setAvailable(int available) {
		this.available = available;
	}

	public String toString() {
		return this.id + "\t" + this.name + " (" + this.country + ")" + "\t" + this.available;
	}
	
}
