package model;

import java.util.ArrayList;

public class ProductUnit {
    private int id;
    private String name;
    private int brandID;
    private int areaID;
    private String kind;
    private int amount;
    private String des;
    private int imei;
    private String color;
    private String size;
    private float wattage;
    private String phanloai = null;
    private double price;
    private int yearMade;
    private String madeIn;
    private String dateImport;
    private  int available;
    private ArrayList<String> imgrls = new ArrayList<>();
    private String brand;
    private  String img;

    public ProductUnit(int id, String name, int brandID, int areaID, String kind, int amount, String des, int imei, String color, String size, float wattage, String phanloai, double price, int yearMade, String madeIn, String dateImport, int available, ArrayList<String> imgrls, String brand,  String img) {
        this.id = id;
        this.name = name;
        this.brandID = brandID;
        this.areaID = areaID;
        this.kind = kind;
        this.amount = amount;
        this.des = des;
        this.imei = imei;
        this.color = color;
        this.size = size;
        this.wattage = wattage;
        this.phanloai = phanloai;
        this.price = price;
        this.yearMade = yearMade;
        this.madeIn = madeIn;
        this.dateImport = dateImport;
        this.available = available;
        this.imgrls = imgrls;
        this.brand = brand;
        this.img = img;
    }

    public ProductUnit(int id, int unitid) {
        this.id = id;
        this.name = "";
        this.brandID = 0;
        this.areaID = 0;
        this.kind = "";
        this.amount = 0;
        this.des = "";
        this.imei = unitid;
        this.color = "";
        this.size = "";
        this.wattage = 0;
        this.phanloai = "";
        this.price = 0;
        this.yearMade = 0;
        this.madeIn = "";
        this.dateImport = "";
        this.available = 1;
        this.imgrls = new ArrayList<>();
        this.brand = "";
        this.img = "";
    }



    public ProductUnit(int id, String name, int brandID, String kind, int amount, String phanloai, double price, String madeIn, int available, String brand, String img) {
        this.id = id;
        this.name = name;
        this.brandID = brandID;
        this.kind = kind;
        this.amount = amount;
        this.phanloai = phanloai;
        this.price = price;
        this.madeIn = madeIn;
        this.available = available;
        this.brand = brand;
        this.img = img;
    }

    public String getImg() {

        return this.img;
    }

    public ProductUnit() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getBrandID() {
        return brandID;
    }

    public int getAreaID() {
        return areaID;
    }

    public String getKind() {
        return kind;
    }

    public int getAmount() {
        return amount;
    }

    public String getDes() {
        return des;
    }

    public int getImei() {
        return imei;
    }

    public String getColor() {
        return color;
    }

    public String getSize() {
        return size;
    }

    public float getWattage() {
        return wattage;
    }

    public String getPhanloai() {
        return phanloai;
    }

    public double getPrice() {
        return price;
    }

    public int getYearMade() {
        return yearMade;
    }

    public String getMadeIn() {
        return madeIn;
    }

    public String getDateImport() {
        return dateImport;
    }

    public int getAvailable() {
        return available;
    }

    public ArrayList<String> getImgrls() {
        return imgrls;
    }

    public String getImgStrings() {
        String re = "";
        for (String s : imgrls) {
            re += s + "--";
        }
        int length = imgrls.size();

        if (length > 1) {
            re = re.substring(0, re.length() - 2);

        }
        return re;
    }

    public String getBrand() {
        return brand;
    }

    @Override
    public String toString() {
        return "ProductUnit{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", brandID=" + brandID +
                ", areaID=" + areaID +
                ", kind='" + kind + '\'' +
                ", amount=" + amount +
                ", des='" + des + '\'' +
                ", imei=" + imei +
                ", color='" + color + '\'' +
                ", size='" + size + '\'' +
                ", wattage=" + wattage +
                ", phanloai='" + phanloai + '\'' +
                ", price=" + price +
                ", yearMade=" + yearMade +
                ", madeIn='" + madeIn + '\'' +
                ", dateImport='" + dateImport + '\'' +
                ", available=" + available +
                ", imgrls=" + imgrls +
                ", brand='" + brand + '\'' +
                ", img='" + img + '\'' +
                '}';
    }

    public static void main(String[] args) {
        System.out.println(new ProductUnit().getName());
    }
}
