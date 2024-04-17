package model;

import java.sql.Date;
import java.sql.Time;

public class Order {
	private int id;
	private Datee dateSet;
	private Time timeSet;
	private float totalPrice;
	private int cusID;
	private int isCompleted;
	public Order(int id, Datee dateSet, Time timeSet, float totalPrice, int cusID, int isCompleted) {
		super();
		this.id = id;
		this.dateSet = dateSet;
		this.timeSet = timeSet;
		this.totalPrice = totalPrice;
		this.cusID = cusID;
		this.isCompleted = isCompleted;
	}

	public Order(int id, Datee dateSet, int totalPrice) {
		this.id = id;
		this.dateSet = dateSet;
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "Order{" +
				"id=" + id +
				", dateSet=" + dateSet +
				", timeSet=" + timeSet +
				", totalPrice=" + totalPrice +
				", cusID=" + cusID +
				", isCompleted=" + isCompleted +
				'}';
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
	public Datee getDateSet() {
		return dateSet;
	}
	public void setDateSet(Datee dateSet) {
		this.dateSet = dateSet;
	}
	public Time getTimeSet() {
		return timeSet;
	}
	public void setTimeSet(Time timeSet) {
		this.timeSet = timeSet;
	}
	public float getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(float totalPrice) {
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
