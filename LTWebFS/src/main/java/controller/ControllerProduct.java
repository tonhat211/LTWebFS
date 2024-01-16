package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.mysql.cj.protocol.PacketReceivedTimeHolder;

import database.BrandDAO;
import database.ImageDAO;
import database.ProductDAO;
import database.UnitDAO;
import model.Brand;
import model.Image;
import model.Product;
import model.ProductHeader;
import model.Unit;

public class ControllerProduct {
	private String kind;
	
	
	private ArrayList<Brand> brandList;
	private ArrayList<Brand> brandListCache;
	private ArrayList<Product> productList;
	private ArrayList<Product> productListCache;
	private ArrayList<Unit> unitList;
	private ArrayList<Image> imageList;
	private ArrayList<ProductHeader> productHeaderList;
	private FilterUtil ft;
	
	public ControllerProduct(String kind) {
		super();
		this.kind = kind;
		brandListCache = this.loadBrandsByProductKind(kind);
		brandList = this.loadBrandsByProductKind(kind);
		productListCache = this.loadProducts(kind);
		productList = this.loadProducts(kind);
		unitList =  this.loadUnits();
		imageList = this.loadImagesByProducts();
		productHeaderList = this.loadProHeadersByUnits();
		ft = new FilterUtil("TẤT CẢ", "TẤT CẢ");
		
		
	}


	public ArrayList<Brand> loadBrandsByProductKind(String kind){
		return BrandDAO.getInstance().selectBrandsByProductKind(kind);
	}



	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public ArrayList<Brand> getBrandList() {
		return brandList;
	}

	public void setBrandList(ArrayList<Brand> brandList) {
		this.brandList = brandList;
	}

	public ArrayList<Product> getProductList() {
		return productList;
	}

	public void setProductList(ArrayList<Product> productList) {
		this.productList = productList;
	}

	public ArrayList<Product> getProductListCache() {
		return productListCache;
	}

	public void setProductListCache(ArrayList<Product> productListCache) {
		this.productListCache = productListCache;
	}

	public ArrayList<Unit> getUnitList() {
		return unitList;
	}

	public void setUnitList(ArrayList<Unit> unitList) {
		this.unitList = unitList;
	}

	public ArrayList<Image> getImageList() {
		return imageList;
	}

	public void setImageList(ArrayList<Image> imageList) {
		this.imageList = imageList;
	}

	public ArrayList<ProductHeader> getProductHeaderList() {
		return productHeaderList;
	}

	public void setProductHeaderList(ArrayList<ProductHeader> productHeaderList) {
		this.productHeaderList = productHeaderList;
	}

	public FilterUtil getFt() {
		return ft;
	}

	public void setFt(FilterUtil ft) {
		this.ft = ft;
	}
	
	
	public ArrayList<Product> loadProducts(String kind){
		ArrayList<Product> ps = ProductDAO.getInstance().selectAll();
		ArrayList<Product> res = new ArrayList<>();
		for(Product p : ps) {
			if(p.getKind().equalsIgnoreCase(kind)) {
				res.add(p);
			}
		}
		return res;
	}
	
	public ArrayList<Unit> loadUnits(){
		ArrayList<Unit> res = new ArrayList<Unit>();
//		ArrayList<Integer> idList = new ArrayList<>();
		for(Product p : productList) {
			res.add(UnitDAO.getInstance().selectById(p.getId()));
		}
		
		return res;
	}
	
	public ArrayList<Image> loadImagesByProducts(){
		ArrayList<Integer> proIDs = new ArrayList<>();
		for(Product p : productList) {
			proIDs.add(p.getId());
		}
		ArrayList<Image> temp = ImageDAO.getInstance().selectAll();
		ArrayList<Image> res = new ArrayList<>();
		

		for(Image i : temp) {
			if(proIDs.contains(i.getParentID())) {
				res.add(i);		
			}
		}
		return res;
	}
	
	public ArrayList<ProductHeader> loadProHeadersByUnits() {
		ArrayList<ProductHeader> res = new ArrayList<>();
		ArrayList<Integer> ids = new ArrayList<>();
		
		for(Unit u :this.unitList) {
			ids.add(u.getProductID());
		}
		
		for(Integer id : ids) {
			Product p = productList.get(this.getIndexProByProID(productList, id));
			Unit u = unitList.get(this.getIndexUnitByProID(unitList, id));
			Image i = imageList.get(this.getIndexImageByProID(imageList, id));
			res.add(new ProductHeader(p, u, i));
		}
		
		
		return res;
	}
	
	public int getIndexProByProID(ArrayList<Product> list,int id) {
		for(int i =0; i< list.size();i++) {
			if(list.get(i).getId() == id)
				return i;
		}
		return -1;
	}
	public int getIndexUnitByProID(ArrayList<Unit> list,int id) {
		for(int i =0; i< list.size();i++) {
			if(list.get(i).getProductID() == id)
				return i;
		}
		return -1;
	}
	public int getIndexImageByProID(ArrayList<Image> list,int id) {
		for(int i =0; i< list.size();i++) {
			if(list.get(i).getParentID() == id)
				return i;
		}
		return -1;
	}

	public void init() {
		loadProHeadersByUnits();
	}
	
	public ArrayList<Brand> getCurrentBrands(){
		return this.brandList;
	}

	public ArrayList<String> getCountries(){
		ArrayList<String>res=  new ArrayList<>();
		for(Brand b : this.brandList) {
			if(!res.contains(b.getCountry()))
				res.add(b.getCountry());
		}

		return res;
	}
	
	public void filter() {
		String country = ft.getCountry();
		String price = ft.getPrice();
		brandList.clear(); //reset brandlist
		ArrayList<Integer> brandIDs  = new ArrayList<>();
		if(country.equalsIgnoreCase("TẤT CẢ")) {
			brandList = brandListCache;
		} else {
			for(Brand b : this.brandListCache) {
				if(b.getCountry().equalsIgnoreCase(country)) {
					brandList.add(b);
					brandIDs.add(b.getId());
				}
			}
		}
		
		
		productList.clear(); //reset productlist
		for(Product p : productListCache) {
			if(brandIDs.contains(p.getBrandID())) {
				productList.add(p);
			}
			
		}
		
		
		this.unitList = this.loadUnits();
		if(price.equalsIgnoreCase("TẤT CẢ")) {
			this.unitList = this.loadUnits();
		} else {
			if(price.equalsIgnoreCase("CAO DẦN")) {
				this.arrangeUnitsByPrice("up", this.unitList);
			}  else if(price.equalsIgnoreCase("THẤP DẦN")) {
				this.arrangeUnitsByPrice("down", this.unitList);
			}
		}
		
		productHeaderList = this.loadProHeadersByUnits();

		
	}
	
	public void arrangeUnitsByPrice(String direction, ArrayList<Unit> units) {
		if(direction.equalsIgnoreCase("down")) {
	        //Sắp xếp danh sách theo giá giảm dần!
			Collections.sort(units, new Comparator<Unit>() {

				@Override
				public int compare(Unit o1, Unit o2) {
					if(o1.getPrice() < o2.getPrice()) {
						return 1;
					} else {
						if(o1.getPrice() == o2.getPrice()) {
							return 0;
						} else {
							return -1;
						}
					}
			
				
				}
			});
		} else if(direction.equalsIgnoreCase("up")) {
//			sap xep tang dan
			Collections.sort(units, new Comparator<Unit>() {

				@Override
				public int compare(Unit o1, Unit o2) {
					// TODO Auto-generated method stub
					if(o1.getPrice() < o2.getPrice()) {
						return -1;
					} else {
						if(Math.abs(o1.getPrice() - o2.getPrice()) < 0.0001)
							return 0;
						 else {
								return 1;
						}
					}
				}
			});
		}
		
	}
	
	public void setFtCountry(String country) {
		this.ft.setCountry(country);
		filter();

		
	}
	
	public void setFtPrice(String price) {
		this.ft.setPrice(price);
		filter();
		
	}
	
	public static <T> void printList(ArrayList<T> list) {
		for(T t : list) {
			System.out.println(t);
		}
	}
	
	
	public static void main(String[] args) {
		ControllerProduct q = new ControllerProduct("c");
		q.setFtCountry("đức");
		q.setFtPrice("thấp dần");
//		printList(q.brandList);
		printList(q.productHeaderList);
		printList(q.brandList);
	}
	
}
