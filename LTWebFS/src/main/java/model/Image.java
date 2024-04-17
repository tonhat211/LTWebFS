package model;

import java.util.Objects;

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
	
	
	public Image(int parentID) {
		super();
		this.parentID = parentID;
	}

	public Image(String url, int parentID) {
		this.url = url;
		this.parentID = parentID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(parentID);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Image other = (Image) obj;
		return parentID == other.parentID;
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
	
	public String toString() {
		return this.id  + "\t" + this.url  + "\t" + this.parentID;
	}
	
}
