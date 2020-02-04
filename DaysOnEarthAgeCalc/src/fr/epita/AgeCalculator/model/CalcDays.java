package fr.epita.AgeCalculator.model;

import java.util.List;


public class CalcDays {
	/**
	 * Current Date Is: 15/12/2019.
	 */
	private int C_Y = 2019; //static current year
	private int C_M = 12; //static current month
	private int C_D = 16; //static current day
	private int year;
	private int month;
	private int date;
	
	/**
	 * Fixed months for the year 2020. which is leap year.
	 */
	private int[] month_array = { 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	//constructor
	public CalcDays(int year, int month, int date) {
		this.year = year;
		this.month = month;
		this.date = date;
	}
	
	//calculate days
	public int findDays() {
		int totalMonthdays = 0;
		if (C_M > month) {
			int currentMonthRemainingDays = month_array[C_M - 1] - C_D + 1;
			for (int i = 0; i < month - 1; i++) {
				totalMonthdays += month_array[i];
			}
			return currentMonthRemainingDays + totalMonthdays + date;
		}
		return 0;
	}

	//sorting implementation.
	private static int partition(List<Person> array, int begin, int end) {
		int pivot = end;

		int counter = begin;
		for (int i = begin; i < end; i++) {
			if (array.get(i).getDays() < array.get(pivot).getDays()) {
				Person temp = array.get(counter);
				array.set(counter, array.get(i));
				array.set(i, temp);
				counter++;
			}
		}

		Person temp = array.get(pivot);
		array.set(pivot, array.get(counter));
		array.set(counter, temp);

		return counter;
	}

	public static void quickSort(List<Person> array, int begin, int end) {
		if (end <= begin)
			return;
		int pivot = partition(array, begin, end);
		quickSort(array, begin, pivot - 1);
		quickSort(array, pivot + 1, end);
	}

}
