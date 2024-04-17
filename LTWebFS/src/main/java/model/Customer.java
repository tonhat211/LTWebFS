package model;

import java.util.ArrayList;

public class Customer {
    private int id;
    private String name;
    private String email;
    private String pwd;
    private int level;
    private String phone;
    private String address;
    private int branchID;
    private String info;
    private Datee dateIn;
    private Datee dateOut;
    private int available;

    private ArrayList<Order> olsit = new ArrayList<>();

    private double totalSpend;

    public Customer(User u, double totalSpend) {
        this.id = u.getId();
        this.name = u.getName();
        this.email = u.getEmail();
        this.pwd = u.getPwd();
        this.level = u.getLevel();
        this.phone = u.getPhone();
        this.address = u.getAddress();
        this.info = u.getInfo();
        this.dateIn = u.getDateIn();
        this.available = u.getAvailable();
//        this.olsit = new ArrayList<>();
        this.totalSpend = totalSpend;
    }

    public Customer() {
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

    public Datee getDateIn() {
        return dateIn;
    }

    public void setDateIn(Datee dateIn) {
        this.dateIn = dateIn;
    }

    public Datee getDateOut() {
        return dateOut;
    }

    public void setDateOut(Datee dateOut) {
        this.dateOut = dateOut;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public ArrayList<Order> getOlsit() {
        return olsit;
    }

    public void setOlsit(ArrayList<Order> olsit) {
        this.olsit = olsit;
    }

    public double getTotalSpend() {
        return totalSpend;
    }

    public void setTotalSpend(double totalSpend) {
        this.totalSpend = totalSpend;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", pwd='" + pwd + '\'' +
                ", level=" + level +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", branchID=" + branchID +
                ", info='" + info + '\'' +
                ", dateIn=" + dateIn +
                ", dateOut=" + dateOut +
                ", available=" + available +
                ", olsit=" + olsit +
                ", totalSpend=" + totalSpend +
                "}\n";
    }

    public static void main(String[] args) {

    }
}
