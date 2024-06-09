package model;

import database.EmployeeDAO;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;

public class Employee {
    private int id;
    private String name;
    private String email;
    private String pwd;
    private int level;
    private String phone;
    private String address;
    private int branchID;
    private String branch;
    private String info;
    private String sex;
    private String birthday;
    private String position;
    private String area;
    private Datee datein;
    private Datee dateout;
    private int available;
    private int imgid;
    private String imgurl;
    private String role;

    public Employee(int id, String name, String email, String pwd, int level, String phone, String address, int branchID, String branch, String info, Datee datein, Datee dateout, int available, int imgid, String imgurl,String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.pwd = pwd;
        this.level = level;
        this.phone = phone;
        this.address = address;
        this.branchID = branchID;
        this.branch = branch;
        this.info = info;
        this.datein = datein;
        this.dateout = dateout;
        this.available = available;
        this.imgid = imgid;
        this.imgurl = imgurl;
        if(info !=null){
            String[] infoTokens = info.split("=");
            if(infoTokens.length == 4){
                this.sex = infoTokens[0];
                this.birthday = infoTokens[1];
                this.position = infoTokens[2];
                this.area = infoTokens[3];
            }
        }
        else {
            this.sex ="";
            this.birthday ="";
            this.position = "";
            this.area ="";
        }
        this.role = role;
    }
    public Employee(int id, String name, String email, String pwd, int level, String phone, String address, int branchID, String branch, String info, Datee datein, int available, int imgid, String imgurl,String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.pwd = pwd;
        this.level = level;
        this.phone = phone;
        this.address = address;
        this.branchID = branchID;
        this.branch = branch;
        this.info = info;
        this.datein = datein;
        this.available = available;
        this.imgid = imgid;
        this.imgurl = imgurl;
        if(info !=null){
            String[] infoTokens = info.split("=");
            if(infoTokens.length == 4){
                this.sex = infoTokens[0];
                this.birthday = infoTokens[1];
                this.position = infoTokens[2];
                this.area = infoTokens[3];
            }
        }
        else {
            this.sex ="";
            this.birthday ="";
            this.position = "";
            this.area ="";
        }
        this.role = role;
    }

    public Employee(int id, String name, String email, String pwd, int level, String phone, String address, int branchID, String info, Datee datein, int available, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.pwd = pwd;
        this.level = level;
        this.phone = phone;
        this.address = address;
        this.branchID = branchID;
        this.info = info;
        this.datein = datein;
        this.available = available;
        this.role = role;
    }

    public Employee(int id, String name, String email, String pwd, int level, String phone, String address, int branchID, String branch, String info, Datee datein, int available, String imgurl, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.pwd = pwd;
        this.level = level;
        this.phone = phone;
        this.address = address;
        this.branchID = branchID;
        this.branch = branch;
        this.info = info;
        this.datein = datein;
        this.available = available;
        this.imgurl = imgurl;
        if(info !=null){
            String[] infoTokens = info.split("=");
            if(infoTokens.length == 4){
                this.sex = infoTokens[0];
                this.birthday = infoTokens[1];
                this.position = infoTokens[2];
                this.area = infoTokens[3];
            }
        }
        else {
            this.sex ="";
            this.birthday ="";
            this.position = "";
            this.area ="";
        }
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public Employee(int id, String name, String email, String phone, String address, String info, String imgurl) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.info = info;
        this.imgurl = imgurl;
    }

    public Employee(int id, String name, String email, String pwd, int level, String phone, String address, int branchID, String branch, String info, Datee datein, Datee dateout, int available) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.pwd = pwd;
        this.level = level;
        this.phone = phone;
        this.address = address;
        this.branchID = branchID;
        this.branch = branch;
        this.info = info;
        this.datein = datein;
        this.dateout = dateout;
        this.available = available;
        if(info !=null){
            String[] infoTokens = info.split("=");
            if(infoTokens.length == 4){
                this.sex = infoTokens[0];
                this.birthday = infoTokens[1];
                this.position = infoTokens[2];
                this.area = infoTokens[3];
            }
        }
        else {
            this.sex ="";
            this.birthday ="";
            this.position = "";
            this.area ="";
        }
    }

    public Employee(User u) {
        this.id = u.getId();
        this.name = u.getName();
        this.email = u.getEmail();
        this.pwd = u.getPwd();
        this.level = 1;
        this.phone = u.getPhone();
        this.address = u.getAddress();
        this.branchID = u.getBranchID();
        this.branch = "";
        this.info = u.getInfo();
        this.datein = u.getDateIn();
        this.dateout = new Datee();
        this.available = u.getAvailable();

        if(info !=null){
            String[] infoTokens = info.split("=");
            if(infoTokens.length == 4){
                this.sex = infoTokens[0];
                this.birthday = infoTokens[1];
                this.position = infoTokens[2];
                this.area = infoTokens[3];
            }
        }
        else {
            this.sex ="";
            this.birthday ="";
            this.position = "";
            this.area ="";
        }
    }

    public int getImgid() {
        return imgid;
    }

    public void setImgid(int imgid) {
        this.imgid = imgid;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public Employee() {
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

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Datee getDatein() {
        return datein;
    }

    public void setDatein(Datee datein) {
        this.datein = datein;
    }

    public Datee getDateout() {
        return dateout;
    }

    public void setDateout(Datee dateout) {
        this.dateout = dateout;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", pwd='" + pwd + '\'' +
                ", level=" + level +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", branchID=" + branchID +
                ", branch='" + branch + '\'' +
                ", info='" + info + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday='" + birthday + '\'' +
                ", position='" + position + '\'' +
                ", area='" + area + '\'' +
                ", datein=" + datein +
                ", dateout=" + dateout +
                ", available=" + available +
                '}';
    }

    public Employee(int id) {
        this.id = id;
        this.name = "";
        this.email = "";
        this.pwd = "";
        this.level = 1;
        this.phone = "";
        this.address = "";
        this.branchID = 0;
        this.branch = "";
        this.info = "";
        this.datein = Datee.getToday();
        this.dateout = null;
        this.available = 0;
       this.sex ="";
        this.birthday ="";
        this.position = "";
        this.area ="";
        this.role = "";
        this.imgurl="";

    }

    public static void main(String[] args) {

        String employee = "e";
        String customer = "c";
        String product = "p";
        String order = "o";
        String dashboard = null;

        ArrayList<String> roles = new ArrayList<>();
        roles.add(employee);
        roles.add(customer);
        roles.add(product);
        roles.add(order);
        roles.add(dashboard);
        String role="";
        for (String s : roles) {
            if(s!=null) {
                role += s +"=";
            }
        }
        String n = "";
        String[] tokens = role.split("=");
        ArrayList<String> list = new ArrayList<String>(Arrays.asList(tokens));
//        System.out.println(n.contains("e=c"));
        Employee e = EmployeeDAO.getInstance().selectById(3035);
        System.out.println(e);
    }




}
