package fr.epita.quiz.services.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import fr.epita.quiz.datamodel.MCQQuestion;

public class MCQQuestionCSVDAO {
	
	private File file = new File("/eclipse/MCQQuestion.csv");
	private PrintWriter writer;
	
	private static final String TOPIC_DELIMITER = "|";
	private static final String DELIMITER = "@@@@";
	
	
	
	public void create(MCQQuestion question) throws FileNotFoundException {
		String formatted = String.valueOf(question.getId()) + DELIMITER;
		formatted += question.getQuestion() + DELIMITER;
		formatted += question.getDifficulty() + DELIMITER;
		
	    
		String[] topics = question.getTopics();
		for (int i = 0; i < topics.length; i++) {
			if(i != topics.length -1) {
			formatted += topics[i] + TOPIC_DELIMITER;
			}
			else {
				formatted += topics[i];
			}
		}
		
		//TODO complete by appending the formatted line in file
		writer = new PrintWriter(new FileOutputStream(file));
		writer.append(formatted);
		//writer.flush();
		writer.close();
	}
	
	
	public List<MCQQuestion> readAll() throws IOException {
		
		List<MCQQuestion> results = new ArrayList<>();
		//TODO complete by reading all the lines from a file
		//File file = new File("C:\\eclipse\\MCQQuestion.txt");
		String line = null;
		
		try (FileReader filereader = new FileReader(file)) {
			BufferedReader reader = new BufferedReader(filereader);
			
			line = reader.readLine();
		   // System.out.println(line);
				
			
		} catch (Exception e) {
			System.out.println("error");
			// TODO: handle exception
		}
	    
		
		//while there is something to read in the file;
		
			//String formatted = line; //TODO this is the current read line
	
			String[] parts = line.split(DELIMITER);
			Long id = Long.valueOf(parts[0]);
			String readQuestion = parts[1];
			Integer difficulty = Integer.valueOf(parts[2]);
			String rawTopics = parts[3];
			String[] subparts = rawTopics.split("\\" +TOPIC_DELIMITER);
			
			
			MCQQuestion readInstance = new MCQQuestion();
			readInstance.setId(id);
			readInstance.setQuestion(readQuestion);
			readInstance.setDifficulty(difficulty);
			readInstance.setTopics(subparts);
		
		results.add(readInstance);
		return results;
		
		
	}
	
	public MCQQuestion getById(int q_id) throws IOException {
		
		/***
		 * lecture work
		 * List<Mcqquestion
		 * for lambda expression it the new instance that only have one method
		 * 
		 */
//		List<MCQQuestion> list = readAll().stream()
//				.filter(mcqQuestion -> MCQQuestion.getId() == q_id)
//				.collect(Collectors.toList());
		
		
		List<MCQQuestion> getValue = readAll();
		
		for (int i = 0; i < getValue.size(); i++) {
			MCQQuestion mainValue = getValue.get(i);	
			if(q_id == mainValue.getId()) {
				
				return mainValue;
			 
			}
		}
		
		return null;
		
		
	
	}

}
