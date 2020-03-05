package fr.epita.factorial;



public class CalculateFactorial {
	
	public int CalcFact(int integer) {
		int result = integer;
		for (int i = integer - 1; i > 0; i--)  {
			
			result = result * i;
			
		}
		return result;
	}

}
