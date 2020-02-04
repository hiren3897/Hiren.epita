package fr.epita.AgeCalculator.model;

public class Person {
	private String name;
	private int date;
	private int month;
	private int year;
	private int days; // 
	
	/**
	 * 
	 * @param name
	 * @param date
	 * @param month
	 * @param year
	 */
	public Person(String name, int date, int month, int year) {
		this.name = name;
		this.date = date;
		this.month = month;
		this.year = year;
	}
	
	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public int getDate() {
		return date;
	}
	public void setDate(int date) {
		this.date = date;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	
	

}
