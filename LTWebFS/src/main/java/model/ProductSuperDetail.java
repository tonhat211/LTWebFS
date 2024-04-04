package model;

import database.*;

import java.util.ArrayList;

public class ProductSuperDetail {
    private int id;
    private  String name;
    private  String  country;
    private  String brand;
    private String  kind;
    private String area;
    private  double price;
    private String type;
    private int amount;
    private int yearMade;
    private String dateImport;
    private int imei;
    private String des;
    private  String imgsUrl;
    private int  available;

    Product p;
    ArrayList<Unit> us;
    ArrayList<Image> is;
    Brand b;
    Area a;

    public ProductSuperDetail(int id) {

        p = ProductDAO.getInstance().selectById(id);
        us = UnitDAO.getInstance().selectByProId(id);
        is = ImageDAO.getInstance().selectByParentID(id);
        b = BrandDAO.getInstance().selectById(p.getBrandID());
        a = AreaDAO.getInstance().selectById(p.getAreaID());

        this.id = id;
        this.name = p.getName();
        this.country = b.getCountry();
        this.brand = b.getName();
        this.kind = p.getKind();
        this.area = a.getName();
        this.price = us.get(0).getPrice();

        this.type = "";
        for(Unit u : us){
            this.type+=u.getSize()  + "-";
        }
        this.type = this.type.substring(0, this.type.length()-1);
        this.amount = us.get(0).getAmount();
        this.yearMade  = us.get(0).getYearMade();
        this.dateImport = us.get(0).getDateImport().getDateInString();
        this.imei  =  us.get(0).getImei();
        this.des  = p.getDescription();
        imgsUrl="";
        for(Image i : is){
            imgsUrl += i.getUrl() + "==";
        }
        this.imgsUrl = this.imgsUrl.substring(0, this.type.length()-2);

    }

    public ProductSuperDetail() {
        this.id = -1;
        this.name = "";
        this.country = "";
        this.brand = "";
        this.kind = "";
        this.area = "";
        this.price = -1;
        this.type = "";
        this.amount = -1;
        this.yearMade = -1;
        this.dateImport = "";
        this.imei = -1;
        this.des = "";
        this.imgsUrl = "";
    }

    public ProductSuperDetail(int id, String name, String country, String brand, String kind, String area, double price, String type, int amount, int yearMade, String dateImport, int imei, String des, String imgsUrl, int available) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.brand = brand;
        this.kind = kind;
        this.area = area;
        this.price = price;
        this.type = type;
        this.amount = amount;
        this.yearMade = yearMade;
        this.dateImport = dateImport;
        this.imei = imei;
        this.des = des;
        this.imgsUrl = imgsUrl;
        this.available = available;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public String getImgsUrl() {
        return imgsUrl;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getBrand() {
        return brand;
    }

    public String getKind() {
        return kind;
    }

    public String getArea() {
        return area;
    }

    public double getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public int getAmount() {
        return amount;
    }

    public int getYearMade() {
        return yearMade;
    }

    public String getDateImport() {
        return dateImport;
    }

    public int getImei() {
        return imei;
    }

    public String getDes() {
        return des;
    }
}
