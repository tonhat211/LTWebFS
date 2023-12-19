package model;

import java.sql.Date;
import java.sql.Time;

public class Order {
	private int id;
	private Date dateSet;
	private Time timeSet;
	private int totalPrice;
	private int cusID;
	private int isCompleted;
	public Order(int id, Date dateSet, Time timeSet, int totalPrice, int cusID, int isCompleted) {
		super();
		this.id = id;
		this.dateSet = dateSet;
		this.timeSet = timeSet;
		this.totalPrice = totalPrice;
		this.cusID = cusID;
		this.isCompleted = isCompleted;
	}
	public Order() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDateSet() {
		return dateSet;
	}
	public void setDateSet(Date dateSet) {
		this.dateSet = dateSet;
	}
	public Time getTimeSet() {
		return timeSet;
	}
	public void setTimeSet(Time timeSet) {
		this.timeSet = timeSet;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	public int getCusID() {
		return cusID;
	}
	public void setCusID(int cusID) {
		this.cusID = cusID;
	}
	public int getIsCompleted() {
		return isCompleted;
	}
	public void setIsCompleted(int isCompleted) {
		this.isCompleted = isCompleted;
	}
	
}
