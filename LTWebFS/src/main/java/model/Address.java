package model;

public class Address {
    private int id;
    private int userid;
    private String receiver;
    private String phone;
    private String address;

    public Address(int userid, String receiver, String phone, String address) {
        this.userid = userid;
        this.receiver = receiver;
        this.phone = phone;
        this.address = address;
    }

    public Address(int id, int userid, String receiver, String phone, String address) {
        this.id = id;
        this.userid = userid;
        this.receiver = receiver;
        this.phone = phone;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Address() {
    }

    public Address(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
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

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", userid=" + userid +
                ", receiver='" + receiver + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
