package model;

public class Address {
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

    public Address() {
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
                "userid=" + userid +
                ", receiver='" + receiver + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
