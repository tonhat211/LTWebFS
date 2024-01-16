package model;

import java.util.ArrayList;

import database.BrandDAO;
import database.ImageDAO;
import database.ProductDAO;
import database.UnitDAO;

public class ProductDetail {

    private int imei;
    private int productID;
    private String name;
    private double price;
    private String types;
    private String brand;
    private String country;
    private String description;
    private int brandID;
    private int yearMade;
    private ArrayList<String> imageUrl = new ArrayList<>();
	private int available;
	private int amount;

    private Product pro;
    private ArrayList<Unit> units  = new ArrayList<Unit>();
    private ArrayList<Image> imgs = new ArrayList<>();

    public ProductDetail(int id) {
        super();
        this.pro = getProductByID(id);
        this.units = getUnitByID(id);
        this.imgs = getImagesByParrentID(id);
        this.imei = getImeiFromUnit();
        this.productID = pro.getId();
        this.name = pro.getName();
        this.price = getPriceFromUnit();
        Brand br = getBrandByID(pro.getBrandID());
        this.brand = br.getName();
        this.country = br.getCountry();
		this.yearMade = getYearMadeFromUnit();
        this.description = pro.getDescription();
        this.imageUrl = getUrlImgsById(id);
//        this.imageUrl = getUrlImgsById(id);
		this.available = getAvailableFromUnit();
		this.amount  = getAmountFromUnit();
       
        
    }
    
    public Product getProductByID(int id){
        return ProductDAO.getInstance().selectById(id);
    }  
    
    public ArrayList<Unit> getUnitByID(int id){
        return UnitDAO.getInstance().selectByProId(id);
    }
    
    public ArrayList<Image> getImagesByParrentID(int id){
    	return ImageDAO.getInstance().selectByParentID(id);
    }
    
    public ArrayList<String>  getUrlImgsById(int id){
    	ArrayList<Image> imgs = getImagesByParrentID(id);
    	ArrayList<String> urls = new ArrayList<>();
    	for(Image i  :imgs) {
    		urls.add(i.getUrl());
    	}
    	return urls;
    }
    
    public Brand getBrandByID(int id) {
    	return BrandDAO.getInstance().selectById(id);
    }
    
    public String getUnitTypes() {
    	String res  ="";
    	for(Unit u : this.units) {
    		String type=null;
    		if(u.getColor()!=null) {
    			type=u.getColor();
    		}
    		
    		else if(u.getSize()!=null) {
    			type=u.getSize();
    		}
    		
    		else if(String.valueOf(u.getWattage())!=null) {
    			type=String.valueOf(u.getWattage());
    		}
    		res+=type +" - ";
    		
    	}
    	res = res.substring(0, res.length()-3);
    	return  res;
    	
    }
    
    public double getPriceFromUnit() {
    	double res = 0.0;
    	for(Unit  u : this.units) {
    		res   = u.getPrice();
    		break;
    	}
    	return res;
		
    }
    
    
    public int getImeiFromUnit() {
    	int res = 0;
    	for(Unit  u : this.units) {
    		res   = u.getImei();
    		break;
    	}
    	return res;
		
    }

	public int getYearMadeFromUnit() {
		int res = 0;
		for(Unit  u : this.units) {
			res   = u.getYearMade();
			break;
		}
		return res;

	}
	public int getAmountFromUnit() {
		int res = 0;
		for(Unit  u : this.units) {
			res   = u.getAmount();
			break;
		}
		return res;

	}
	public int getAvailableFromUnit() {
		int res = 0;
		for(Unit  u : this.units) {
			res   = u.getAvailable();
			break;
		}
		return res;

	}




	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

	public int getImei() {
		return imei;
	}

	public void setImei(int imei) {
		this.imei = imei;
	}
	public int getAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}


	public int getYearMade() {
		return yearMade;
	}

	public void setYearMade(int yearMade) {
		this.yearMade = yearMade;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getBrandID() {
		return brandID;
	}

	public void setBrandID(int brandID) {
		this.brandID = brandID;
	}

	public ArrayList<String> getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(ArrayList<String> imageUrl) {
		this.imageUrl = imageUrl;
	}
    
	public static void main(String[] args) {
		String  test  = "tominhnhat1- ";
		System.out.println(test.substring(0, test.length()-3));
	}
    


}
