package model;

public class DeOrder {
	private int ordID;
	private int proID;
	private String pName;
	private float priceUnit;
	private int qty;

	public DeOrder() {
	}

	public DeOrder(int ordID, int proID, String pName, float priceUnit, int qty) {
		this.ordID = ordID;
		this.proID = proID;
		this.pName = pName;
		this.priceUnit = priceUnit;
		this.qty = qty;
	}

	@Override
	public String toString() {
		return "DeOrder{" +
				"ordID=" + ordID +
				", proID=" + proID +
				", pName='" + pName + '\'' +
				", priceUnit=" + priceUnit +
				", qty=" + qty +
				'}';
	}

	public int getOrdID() {
		return ordID;
	}

	public void setOrdID(int ordID) {
		this.ordID = ordID;
	}

	public int getProID() {
		return proID;
	}

	public void setProID(int proID) {
		this.proID = proID;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public float getPriceUnit() {
		return priceUnit;
	}

	public void setPriceUnit(float priceUnit) {
		this.priceUnit = priceUnit;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}
}
