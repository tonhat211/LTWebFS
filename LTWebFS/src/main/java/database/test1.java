package database;

import model.Datee;
import model.User;

public class test1 {
	public static void main(String[] args) {
//		String temp = "600k";
//		int k = temp.indexOf('k');
//		String pricestr = temp.substring(0, k);
//		int price = Integer.parseInt(pricestr);
//		System.out.println(price == 600);
//		System.out.println(price);
		String datein = "2000-05-01";
		String[] dateInTokens = datein.split("-");
		Datee dateinDatee = new Datee(Integer.parseInt(dateInTokens[0]),
				Integer.parseInt(dateInTokens[1]),
				Integer.parseInt(dateInTokens[2]));
		System.out.println(dateinDatee);
		System.out.println(dateInTokens[2]);
		System.out.println(dateInTokens[1]);
		System.out.println(dateInTokens[0]);

		String pwd = dateInTokens[2] + dateInTokens[1] + dateInTokens[0];
		System.out.println(pwd);
		pwd = User.encodePwd(pwd);
		System.out.println(pwd);
		System.out.println(pwd.equals("4948534848484850"));
	}
}
