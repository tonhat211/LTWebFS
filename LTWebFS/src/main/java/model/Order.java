package model;

import java.sql.Time;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Order {
	public static final int WAITING = 0;
	public static final int CONFIRM = 1;
	public static final int PACKAGED = 2;
	public static final int DELIVERYING = 3;
	public static final int COMPLETED = 4;
	public static final int CANCELED = -1;
	public static final int CONFIRM_RETURN = -2;
	public static final int RETURNING = -3;
	public static final int COMPLETED_RETURN = -4;
	private int id;
	private Datee dateSet;
	private Time timeSet;
	private int totalPrice;
	private int cusID;

	private String receiverInfo;
	private int status;

	private int deliveryFee;


	private int isCompleted;

	public Order(int id, int totalPrice, int cusID, String receiverInfo, int deliveryFee, int status) {
		this.id = id;
		this.totalPrice = totalPrice;
		this.cusID = cusID;
		this.receiverInfo = receiverInfo;
		this.status = status;
	}

	public Order(int id) {
		this.id = id;
	}

	public Order(int id, Datee dateSet, Time timeSet, int totalPrice, int cusID, String receiverInfo, int deliveryFee, int status) {
		this.id = id;
		this.dateSet = dateSet;
		this.timeSet = timeSet;
		this.totalPrice = totalPrice;
		this.cusID = cusID;
		this.receiverInfo = receiverInfo;
		this.status = status;
		this.deliveryFee = deliveryFee;
	}

	public Order(int id, Datee dateSet, Time timeSet, int cusID) {
		this.id = id;
		this.dateSet = dateSet;
		this.timeSet = timeSet;
		this.cusID = cusID;
	}

	public String getReceiver() {
		String temp = this.receiverInfo;
		String tokens[] = temp.split("=");
		if (tokens!=null || tokens.length>0) return tokens[0];
		else return null;
	}
	public String getReceiverPhone() {
		String temp = this.receiverInfo;
		String tokens[] = temp.split("=");
		if (tokens!=null || tokens.length>0) return tokens[1];
		else return null;
	}
	public String getAddress() {
		String temp = this.receiverInfo;
		String tokens[] = temp.split("=");
		if (tokens!=null || tokens.length>0) return tokens[2];
		else return null;
	}

	public String getTotalPriceString() {
		int intNumber = this.totalPrice;
//		double doubleNumber = intNumber;

		DecimalFormat df = new DecimalFormat("#,###");
		df.setMaximumFractionDigits(0);

		String formattedNumber = df.format(intNumber);
		return formattedNumber;
	}

	public static String formatPriceOf(int num) {


		DecimalFormat df = new DecimalFormat("#,###");
		df.setMaximumFractionDigits(0);

		String formattedNumber = df.format(num);
		return formattedNumber;
	}



	public String getReceiverInfo() {
		return receiverInfo;
	}

	public void setReceiverInfo(String receiverInfo) {
		this.receiverInfo = receiverInfo;
	}

	public int getStatus() {
		return status;
	}

	public String getColorByStatus() {
		switch (this.status) {

			case 1: return "blue-color";
			case 2: return "yellow-color";
			case 3: return "yellow-color";
			case 4: return "green-color";
			case -1: return "orange-color";
			case -2: return "yellow-color";
			case -3: return "yellow-color";
			case -4: return "orange-color";
			default: return "";
		}
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Order(int id, Datee dateSet, Time timeSet, int totalPrice, int cusID, int isCompleted) {
		super();
		this.id = id;
		this.dateSet = dateSet;
		this.timeSet = timeSet;
		this.totalPrice = totalPrice;
		this.cusID = cusID;
		this.isCompleted = isCompleted;
	}

	public Order(int id, int totalPrice, int cusID, int deliveryFee,int isCompleted) {
		this.id = id;
		this.totalPrice = totalPrice;
		this.cusID = cusID;
		this.deliveryFee = deliveryFee;
		this.isCompleted = isCompleted;
	}

	public int getDeliveryFee() {
		return deliveryFee;
	}

	public void setDeliveryFee(int deliveryFee) {
		this.deliveryFee = deliveryFee;
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
				", receiverInfo='" + receiverInfo + '\'' +
				", status=" + status +
				", deliveryFee=" + deliveryFee +
				'}';
	}

//	public Order getInstance() { return new Order()}



	public String getStatusBefore() {
		int status = this.status;
		switch (status) {
			case 0: return "Chờ xác nhận";
			case 1:
				return "Đã xác nhận";
			case 2:
				return "Đã được đóng gói";
			case 3:
				return "Đã bàn giao cho đơn vị vận chuyển";
			case 4:
				return "Đã hoàn thành";
			case -1:
				return "Đã hủy";
			case -2:
				return "Đã xác nhận trả";
			case -3:
				return "Đã bàn giao trả";
			case -4:
				return "Đã trả thành công";
		}
		return "Không xác định";
	}


	public String getStatusAfter() {
		int status = this.status;
		switch (status) {
			case 1:
				return "Da xac nhan truoc do";
			case 2:
				return "Da duoc dong goi truoc do";
			case 3:
				return "Da duoc ban giao cho vanj chuyen";
			case 4:
				return "Da hoan thanh";
			case -1:
				return "Da huy";
			case -2:
				return "Da xac nhan tra";
			case -3:
				return "Da ban giao tra va dang duoc van chuyen ve lai kho";
			case -4:
				return "Da xac nhan tra thanh cong";
		}
		return "khong co thong tin";
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Order)) return false;
		Order order = (Order) o;
		return getId() == order.getId();
	}



	@Override
	public int hashCode() {
		return Objects.hash(getId());
	}

	public static void main(String[] args) {
		Order o1 = new Order(1,90,1,"nhan",0,0);
		Order o2 = new Order(1);
		System.out.println(o1.equals(o2));

		Map<Order,String> map = new HashMap<>();
		map.put(o1,"");
		System.out.println(map.containsKey(o2));
	}
}
