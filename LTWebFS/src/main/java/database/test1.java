package database;

public class test1 {
	public static void main(String[] args) {
		String temp = "600k";
		int k = temp.indexOf('k');
		String pricestr = temp.substring(0, k);
		int price = Integer.parseInt(pricestr);
		System.out.println(price == 600);
		System.out.println(price);
	}
}
