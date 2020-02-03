package fr.epita.quiz.tests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import fr.epita.quiz.datamodel.MCQQuestion;

public class TestJDBCConnection {

	public static void main(String[] args) throws SQLException {
		
		
		Connection connection =  DriverManager.getConnection("jdbc:h2:~/test","sa","1234");
//		String schema = connection.getSchema();
//		boolean success = "PUBLIC".equals(schema);
//		System.out.println("success" + success);
		
		MCQQuestion question = new MCQQuestion();
		question.setDifficulty(3);
		question.setQuestion("What is CSV");
		
		PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO ")
		connection.close();

	}

}
