package model;

import javax.sound.midi.Soundbank;
import java.sql.Date;
import java.sql.SQLOutput;
import java.util.Objects;

public class User {
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
    private String role;
    private Date orderDate;

    public User(int id, String name, String email, String phone, Date orderDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.orderDate = orderDate;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public User(int id, String name, String email, String pwd, int level, String phone, String address, int branchID, String info, Datee dateIn, Datee dateOut, int available, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.pwd = pwd;
        this.level = level;
        this.phone = phone;
        this.address = address;
        this.branchID = branchID;
        this.info = info;
        this.dateIn = dateIn;
        this.dateOut = dateOut;
        this.available = available;
        this.role = role;
    }

    public User(int id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getId() == user.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User(int id) {
        this.id = id;
        this.name = "";
        this.email = "";
        this.pwd = "1234";
        this.level = 0;
        this.phone = "";
        this.address = "";
        this.branchID = 0;
        this.info = "";
        this.dateIn = Datee.getToday();
        this.dateOut = null;
        this.available = 0;

    }

    public User() {
        super();
    }

    public User(int id, String name, String email, String pwd, int level, String phone, String address, int branchID,
                String info, Datee dateIn, Datee dateOut, int available) {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
        this.pwd = pwd;
        this.level = level;
        this.phone = phone;
        this.address = address;
        this.branchID = branchID;
        this.info = info;
        this.dateIn = dateIn;
        this.dateOut = dateOut;
        this.available = available;
    }

    public User(String email, String pwd) {
        this.email = email;
        this.pwd = pwd;
    }



    public User(int id, String name, String email, String phone, String info, Datee dateIn) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.info = info;
        this.dateIn = dateIn;
    }

    public User(int id, String name, String email, String phone, String address, String info, Datee dateIn) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.info = info;
        this.dateIn = dateIn;
    }


    public User(String name,String email, String pwd, int level) {
        this.name = name;
        this.email = email;
        this.pwd = pwd;
        this.level = level;
    }

    public User(String name, String email, String pwd, int level, String phone, String address, String info) {
        this.name = name;
        this.email = email;
        this.pwd = pwd;
        this.level = level;
        this.phone = phone;
        this.address = address;
        this.info = info;
    }

    public User(int id, String name, String email, int level, Datee dateIn, int available) {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
        this.level = level;
        this.dateIn = dateIn;
        this.available = available;
    }

    public User(int id, String name, int level, Datee dateIn, int available) {
        super();
        this.id = id;
        this.name = name;
        this.level = level;
        this.dateIn = dateIn;
        this.available = available;
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

    @Override
    public String toString() {
        return "User{" +
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
                '}';
    }

    public boolean checkLogin(String emailin, String pwdin) {
        if(this.email.equals(emailin) && this.pwd.equals(pwdin))
            return true;
        return false;
    }

    public boolean isAdmin() {
        if(this.level>0) {
            return true;
        }
        return false;
    }

    public void encodePwd() {
        this.pwd = encodePwd(this.pwd);
    }

    public static String encodePwd(String pwd) {
        String code ="";
        String temp = "";
        for(int i=pwd.length() - 1 ; i>=0;i--){
            code+= (int) pwd.charAt(i);
        }

        return code;
    }

    public static void main(String[] args) {
        System.out.println(User.encodePwd("123456"));
        Date date = new Date(System.currentTimeMillis());
        Datee dateimportDatee = new Datee(java.time.LocalDate.now().getYear(),java.time.LocalDate.now().getMonthValue(), java.time.LocalDate.now().getDayOfMonth());
        System.out.println(java.time.LocalDate.now().toString());
    }
}
