package model;

public class cartitem {
    private int id;
    private int proID;
    private User u;
    private ProductUnit p;
    private int qty;

    public cartitem(int id, int proID, int qty) {
        this.id = id;
        this.proID = proID;
        this.qty = qty;
    }

    public int getProID() {
        return proID;
    }

    public void setProID(int proID) {
        this.proID = proID;
    }

    public cartitem(User u, ProductUnit p, int qty) {
        this.u = u;
        this.p = p;
        this.qty = qty;
    }

    public cartitem(int id, ProductUnit p, int qty) {
        this.id = id;
        this.p = p;

        this.qty = qty;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public cartitem() {
    }

    @Override
    public String toString() {
        return "decart{" +
                "u=" + u +
                ", p=" + p +
                ", qty=" + qty +
                '}';
    }

    public User getU() {
        return u;
    }

    public void setU(User u) {
        this.u = u;
    }

    public ProductUnit getP() {
        return p;
    }

    public void setP(ProductUnit p) {
        this.p = p;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
