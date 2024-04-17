package model;

import java.sql.Date;
import java.time.LocalDate;

public class Datee {
	private int year;
	private int month;
	private int day;
	public Datee(int year, int month, int day) {
		super();
		this.year = year;
		this.month = month;
		this.day = day;
	}

	public String getDateInMonthDayYear(){
		String  monthh="", dayy="";
		if(this.month<10){
			monthh = "0" + this.month;
		} else monthh = month +"";
		if(this.day<10){
			dayy = "0"+ this.day;
		}  else dayy = day +"";
		return this.year  +"-" + monthh + "-" +dayy;
	}

	public String getDateInMonthDayYearSql(){
		String  monthh="", dayy="";
		if(this.month<10){
			monthh = "0" + this.month;
		} else monthh = month +"";
		if(this.day<10){
			dayy = "0"+ this.day;
		}  else dayy = day +"";
		return this.year  +"-" + monthh + "-" +dayy;
	}
//	'16-2022-4'

	public  String getDateInString(){
		return this.year  +"-" + this.month + "-" +this.day;
 	}
	
	public Datee(Date date) {
		super();
		this.year = date.toLocalDate().getYear();
		this.month = date.toLocalDate().getMonthValue();
		this.day = date.toLocalDate().getDayOfMonth();
	}

	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public String toString() {
		return this.year + "/" + this.month + "/" + this.day;
	}

	public static Datee getToday(){
		LocalDate today = LocalDate.now();
		return new Datee(today.getYear(),today.getMonthValue(),today.getDayOfMonth());
	}


	public static void main(String[] args) {
//		LocalDate today = LocalDate.now();
//		System.out.println(today.getYear());
//		System.out.println(today.getMonthValue());
//		System.out.println(today.getDayOfMonth());

		Datee d = Datee.getToday();
		System.out.println(d.getDateInMonthDayYearSql());
	}
}
