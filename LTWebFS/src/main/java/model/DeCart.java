package model;

public class DeCart {
    private int idCart;
    private int idProduct;
    private int qty;

    public DeCart(int idCart, int idProduct, int qty) {
        this.idCart = idCart;
        this.idProduct = idProduct;
        this.qty = qty;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public DeCart() {
    }

    public int getIdCart() {
        return idCart;
    }

    public void setIdCart(int idCart) {
        this.idCart = idCart;
    }

    @Override
    public String toString() {
        return "DeCart{" +
                "idCart=" + idCart +
                ", idProduct=" + idProduct +
                ", qty=" + qty +
                '}';
    }
}
