package database;

import model.Branch;
import model.Datee;
import model.Employee;
import model.User;

import java.util.ArrayList;

public class test1 {
	public static void main(String[] args) {
		int sales=1;
		double salesGap=100;
			String salesStatus ="";
			if(salesGap > 100) salesStatus = "tang";
			else if(salesGap <100) {
				salesStatus = "giam";
				salesGap = 100 - salesGap;
			} else {
				salesStatus = "duy tri";
			}
			String html ="";
			html = "                 <h6>"+sales +"</h6>\n" +
					"                                        <span class=\"text-success small pt-1 fw-bold\">"+salesGap+"</span>\n" +
					"                                        <span\n" +
					"                                            class=\"text-muted small pt-2 ps-1\">"+salesStatus+"</span>";

			System.out.println(html);
	}
}
