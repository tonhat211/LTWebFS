package model;

public class HomeContent {
	private int id;
	private int indx;
	private String description;
	public HomeContent(int id, int indx, String description) {
		super();
		this.id = id;
		this.indx = indx;
		this.description = description;
	}
	public HomeContent() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIndx() {
		return indx;
	}
	public void setIndx(int indx) {
		this.indx = indx;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
