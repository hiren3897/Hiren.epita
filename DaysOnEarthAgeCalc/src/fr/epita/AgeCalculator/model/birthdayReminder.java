package fr.epita.AgeCalculator.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class birthdayReminder {
	

	//global variable.
	static int year;
	static int month;
	static int date;
	static String username;
	static List<Person> users = new ArrayList<Person>();
	
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		GetUserInput(sc);
		String Answer;
		do{
		System.out.println("Do you want to Input more Entries select 'y' for YES and 'n' for NO ");
		Answer = sc.nextLine();
		System.out.println();
		switch (Answer) {
		case "y":
			GetUserInput(sc); //Take User Input.
			break;
		case "n":
			Output();// print output
			break;

		default:
			break;
		}
		
		}while(!"n".equals(Answer));
		sc.close();
		
	}

	//Output the result when user enter "NO"
	private static void Output() {
		for (int i = 0; i < users.size(); i++) {
			CalcDays days = new CalcDays(users.get(i).getYear(), users.get(i).getMonth(),users.get(i).getDate());
			int TotalDays = days.findDays();
			users.get(i).setDays(TotalDays);
			
		}
		//sorting the entries for printing nearest birthday first.
		CalcDays.quickSort(users, 0, users.size()-1);
		
		
		for (int i = 0; i < users.size(); i++) {
			System.out.println(users.get(i).getName() + " in " + users.get(i).getDays() + " days.");// print final output after sorting.
		}
	}
	
	
	private static void GetUserInput(Scanner sc) {
		System.out.println("Enter Your Name: ");
		username = sc.nextLine();
		System.out.println("****************Enter Your BirthDate****************");
		System.out.println("Please Enter Year, month and date");
		year = sc.nextInt();
		month = sc.nextInt();
		date = sc.nextInt();
		Person person = new Person(username, date, month, year);
		users.add(person);
	}
	
}
