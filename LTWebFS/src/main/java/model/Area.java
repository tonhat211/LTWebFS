package model;

public class Area {
	private int id;
	private String name;
	private int available;
	public Area(int id, String name, int available) {
		super();
		this.id = id;
		this.name = name;
		this.available = available;
	}
	
	public Area(String name) {
		super();
		this.name = name;
	}

	public Area() {
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
	
	public String toString() {
		String avai="";
		if(this.available<1) {
			avai = "NGUNG hoat dong";
		} else 
			avai = "DANG hoat dong";
		return this.name + "\t" + this.id + "\t" + avai;
	}
}
