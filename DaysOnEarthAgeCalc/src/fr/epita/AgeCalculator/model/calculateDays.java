package fr.epita.AgeCalculator.model;

public class calculateDays {
	private int CD;
	private int CM;
	private int CY;
	private int BD;
	private int BM;
	private int BY;

	private int month[] = { 31, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	
	public calculateDays(int CD, int CM, int CY, int BD, int BM, int BY) {
		this.CD = CD;
		this.CM = CM;
		this.CY = CY;
		this.BD = BD;
		this.BM = BM;
		this.BY = BY;
		
	}
	public int findDays() {
		int differenceBirthDate = 0;
		int birthYearRemDays = 0;
		int substractAgeInDays = 0;
		int differenceCurrentDate = 0;
		int leapDays = 0;
		
		if (CY - BY == 0 && (CM - BM) >= 0) {
			if ((CM - BM) > 0) {
				leapDays = findLeapYearBetweenTwoYear(CY, BY);
				differenceBirthDate = differenceBirthDate(BY, BM, BD);
				birthYearRemDays = findRemMonthBirthDays(BM, CM);
			}else {
				System.out.println("----------- totalDays--------------" + (CD - BD));
				return CD - BD;
			}
		} else if (CY - BY >= 0) {
			leapDays = findLeapYearBetweenTwoYear(CY, BY);
			differenceBirthDate = differenceBirthDate(BY, BM, BD);
			birthYearRemDays = findRemBirthDays(BM);
			
			if (CY - BY > 1) {
				substractAgeInDays = ((((CY - 1) - (BY + 1)) + 1) * 365) + leapDays;
			}
			differenceCurrentDate = findRemianingCurrentDays(CM,CY);
		}

		int totaldays = differenceBirthDate + birthYearRemDays + substractAgeInDays + differenceCurrentDate
				+ CD;
		
		return totaldays;
	}
	
	
	public int differenceBirthDate(int BY, int BM, int BD) {
		if (isLeapYear(BY)) {
			month[2] = 29;
			return (month[BM] - BD);
		} else {
			month[2] = 28;
			return (month[BM] - BD);

		}
	}

	
	public int findRemMonthBirthDays(int BM, int CM) {
		int birthYearRemDays = 0;
		if ((CM - BM) > 1) {
			for (int i = BM + 1; i < CM; i++) {
				birthYearRemDays += month[i];
			}
		}
		
		return birthYearRemDays;
	}
	
	public int findRemBirthDays(int BM) {
		int birthYearRemDays = 0;
		if ((12 - BM) > 0) {
			for (int i = BM + 1; i <= 12; i++) {
				birthYearRemDays += month[i];
			}
		}
		
		return birthYearRemDays;
	}
	
	public int findRemianingCurrentDays(int CM, int CY) {

		if (isLeapYear(CY)) {
			month[2] = 29;
		} else {
			month[2] = 28;
		}
		
		int differenceCurrentDate = 0;
		if (CM - 1 >= 1) {
			for (int i = 1; i < CM; i++) {
				differenceCurrentDate += month[i];
			}
		}

		return differenceCurrentDate;
	}
	
	public String findAge() {
		
		if (calculateDays.isLeapYear(BY)) {
			month[2] = 29;
		} else {
			month[2] = 28;
		}
		
		if (BD > CD) {
			CD = CD + month[CM-1];
			CM = CM - 1;
		}

		if (BM > CM) {
			CY = CY - 1;
			CM = CM + 12;
		}

		// calculate date, month, year
		int days_Calc = CD - BD;
		int month_Calc = CM - BM;
		int year_Calc = CY - BY;
		
		
		return year_Calc + " :Years  " + month_Calc + " :Months  " + days_Calc + " :Days";

	}

	public static boolean isLeapYear(int year) {
		if ((year % 4 == 0) && year % 100 != 0) {
			return true;
		} else if ((year % 4 == 0) && (year % 100 == 0) && (year % 400 == 0)) {
			return true;
		} else {
			return false;
		}
	}

	public int findLeapYearBetweenTwoYear(int CY, int BY) {
		int leapDaysCount = 0;
		for (int i = BY + 1; i < CY; i++) {
			if (isLeapYear(i)) {
				leapDaysCount++;
			}
		}
		//System.out.println("Leap Days : " + leapDaysCount);
		return leapDaysCount;
	}
}
