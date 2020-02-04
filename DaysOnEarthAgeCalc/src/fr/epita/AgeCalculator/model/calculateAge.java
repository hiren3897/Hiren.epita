/*
 * HIREN RATHOD
 * GROUP 2
 * */

package fr.epita.AgeCalculator.model;
import java.util.Scanner;


public class calculateAge {
	static int year;
	static int month;
	static int date;
	int birthyear;
	int birthmonth;
	int birthdate;
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in); 
		
		System.out.println("*************ENTER CURRENT DATE*************");
		System.out.println("*******YYYY/MM/DD:*******");
		year = scanner.nextInt(); // Read year input
		month = scanner.nextInt(); // Read month input
		date = scanner.nextInt(); // Read date input
		System.out.println("*************BIRTH-DATE*************");
		System.out.println("*******YYYY/MM/DD:*******");
		int birthyear = scanner.nextInt(); 
		int birthmonth = scanner.nextInt(); 
		int birthdate = scanner.nextInt(); 
		
		
		scanner.close();
		calculateDays days= new calculateDays(date, month, year, birthdate, birthmonth, birthyear);
		
		int totalDaysLive = days.findDays();
		System.out.println("Total days lived: " + totalDaysLive);
		
		String age = days.findAge();
		System.out.println("Your Age:");
		System.out.println(age);

	}
	
}
