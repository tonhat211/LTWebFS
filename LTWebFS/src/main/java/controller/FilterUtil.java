package controller;

import java.util.ArrayList;

import model.Brand;

public class FilterUtil {
	
	private String country;
	private String price;
	ArrayList<Brand> bs = new ArrayList<>();
	
	public FilterUtil() {
		super();
		this.country = "TẤT CẢ";
		this.price = "TẤT CẢ";
		
	}

	public FilterUtil(String country, String price) {
		super();
		this.country = country;
		this.price = price;

	}


	
	



	public FilterUtil(String country, String price, ArrayList<Brand> bs) {
		super();
		this.country = country;
		this.price = price;
		this.bs = bs;
	}





	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public ArrayList<Brand> getBs() {
		return bs;
	}

	public void setBs(ArrayList<Brand> bs) {
		this.bs = bs;
	}
	
	public String toString() {
		String brands="";
		for(Brand b : this.bs) {
			brands+=b + "||";
		}
		return this.country + "\t"+ this.price + brands;
	}
	
	public static void main(String[] args) {
		FilterUtil f = new FilterUtil();
		System.out.println(f.getBs().equals(new ArrayList<>()));
	}
	
	
	
	
	
}
