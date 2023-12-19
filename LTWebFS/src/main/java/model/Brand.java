package model;

public class Brand {
	private int id;
	private String name;
	private int available;
	public Brand(int id, String name, int available) {
		super();
		this.id = id;
		this.name = name;
		this.available = available;
	}
	public Brand(String name) {
		super();
		this.name = name;
		this.available=1;
	}
	public Brand() {
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
	public int getAvailable() {
		return available;
	}
	public void setAvailable(int available) {
		this.available = available;
	}

	public String toString(){
		return this.name + "\tavailable: " + this.available;
	}
	
}
