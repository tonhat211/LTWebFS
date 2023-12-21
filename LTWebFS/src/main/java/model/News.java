package model;

public class News {
	private int id;
	private String title;
	private String link;
	public News(int id, String title, String link) {
		super();
		this.id = id;
		this.title = title;
		this.link = link;
	}
	public News() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	
}
