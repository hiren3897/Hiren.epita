package fr.epita.quiz.tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import fr.epita.quiz.datamodel.MCQQuestion;
import fr.epita.quiz.services.data.Constant;
import fr.epita.quiz.services.data.MCQQuestionCSVDAO;

public class TestMcqQuestionCSVDOAO {

	public static void main(String[] args) throws IOException {
		MCQQuestion question = new MCQQuestion();
		question.setDifficulty(3);
		question.setTopics(new String[] {"java","uml","oop"});
		question.setQuestion("What is the keyword to define inheritance between 2 classes in java?");
		question.setId(1l);
	
		/***
		 * read the value from file and display
		 */
		MCQQuestionCSVDAO dao = new MCQQuestionCSVDAO();
		dao.create(question);
		List<MCQQuestion> listQuestion = dao.readAll();
		
		for (int i = 0; i < listQuestion.size(); i++) {
			MCQQuestion q = listQuestion.get(i);
			

			Constant.printQuestion(q);
			
			
		}

	/***
	 * display the value by getting ID from the User
	 * getbyID Method
	 */
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter ID to get information: ");
		int getid = scanner.nextInt();
		MCQQuestion getByIdValues = dao.getById(getid);
		Constant.printQuestion(getByIdValues);
		
		scanner.close();


	}

}
