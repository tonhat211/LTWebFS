package model;

public class DeOrder {
	private int ordID;
	private int imei;
	private int qty;
	public DeOrder(int ordID, int imei, int qty) {
		super();
		this.ordID = ordID;
		this.imei = imei;
		this.qty = qty;
	}
	public DeOrder() {
		super();
	}
	public int getOrdID() {
		return ordID;
	}
	public void setOrdID(int ordID) {
		this.ordID = ordID;
	}
	public int getImei() {
		return imei;
	}
	public void setImei(int imei) {
		this.imei = imei;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	
}
