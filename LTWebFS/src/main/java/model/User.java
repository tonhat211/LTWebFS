package model;

import java.sql.Date;

public class User {
	private int id;
	private String name;
	private String email;
	private String pwd;
	private int level;
	private String phone;
	private String address;
	private int branchID;
	private String info;
	private Date dateIn;
	private Date dateOut;
	private int available;
	
	public User() {
		super();
	}

	public User(int id, String name, String email, String pwd, int level, String phone, String address, int branchID,
			String info, Date dateIn, Date dateOut, int available) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.pwd = pwd;
		this.level = level;
		this.phone = phone;
		this.address = address;
		this.branchID = branchID;
		this.info = info;
		this.dateIn = dateIn;
		this.dateOut = dateOut;
		this.available = available;
	}

	public User(String email, String pwd) {
		this.email = email;
		this.pwd = pwd;
	}

	public User(String email, String pwd, int level) {
		this.email = email;
		this.pwd = pwd;
		this.level = level;
	}

	public User(String name, String email, String pwd, String phone, String address, String info) {
		this.name = name;
		this.email = email;
		this.pwd = pwd;
		this.phone = phone;
		this.address = address;
		this.info = info;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getBranchID() {
		return branchID;
	}

	public void setBranchID(int branchID) {
		this.branchID = branchID;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Date getDateIn() {
		return dateIn;
	}

	public void setDateIn(Date dateIn) {
		this.dateIn = dateIn;
	}

	public Date getDateOut() {
		return dateOut;
	}

	public void setDateOut(Date dateOut) {
		this.dateOut = dateOut;
	}

	public int getAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}
	
	
	
	
}
