package database;

import model.Branch;
import model.Datee;
import model.Employee;
import model.User;

import java.util.ArrayList;

public class test1 {
	public static void main(String[] args) {

		String s = "===";
		String info = "===";
		String sex = "";
		String birthday="";
		String position="";
		String area ="";
		if(info!=null){
			String infoTokens[] = info.split("=");

			switch (infoTokens.length) {
				case 1: {
					sex = infoTokens[0];
					break;
				}
				case 2: {
					sex = infoTokens[0];
					birthday = infoTokens[1];
					break;
				}
				case 3: {
					sex = infoTokens[0];
					birthday = infoTokens[1];
					position = infoTokens[2];
					break;
				}
				case 4: {
					sex = infoTokens[0];
					birthday = infoTokens[1];
					position = infoTokens[2];
					area = infoTokens[3];
					break;
				}
				default: {
					sex = "";
					birthday="";
					position="";
					area="";
					break;
				}

			}

			if(!birthday.equals("")) {
				String[] bdTokens = birthday.split("-");
				if((bdTokens[1].length()<2))
					bdTokens[1]="0" + bdTokens[1];
				if((bdTokens[2]).length()<2)
					bdTokens[2]="0" + bdTokens[2];
				birthday = bdTokens[0] + "-" + bdTokens[1] + "-" + bdTokens[2];
			}



		}

		System.out.println("gioi tinh: " +sex);
		System.out.println("ngay sinh: "+ birthday);
		System.out.println("chuc vu: " +position);
		System.out.println("phong ban: " +area);
	}
}
