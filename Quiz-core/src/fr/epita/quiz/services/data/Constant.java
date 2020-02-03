package fr.epita.quiz.services.data;
import fr.epita.quiz.datamodel.MCQQuestion;

public class Constant {
	
	public static void printQuestion(MCQQuestion q) {
		System.out.println(q.getId() +". Question :"+q.getQuestion());
		System.out.println("Difficulty :"+q.getDifficulty());
		System.out.println("Topics: ");
		for (String op : q.getTopics()) {
			System.out.println(op);
		}
	}

}
