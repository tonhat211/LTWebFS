package model;

public class Image {
	private int id;
	private String url;
	private int parentID;
	public Image() {
		super();
	}
	public Image(int id, String url, int parentID) {
		super();
		this.id = id;
		this.url = url;
		this.parentID = parentID;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getParentID() {
		return parentID;
	}
	public void setParentID(int parentID) {
		this.parentID = parentID;
	}
	
}
