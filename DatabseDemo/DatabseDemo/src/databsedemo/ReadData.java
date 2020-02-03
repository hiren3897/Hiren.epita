/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databsedemo;

import java.util.Scanner;

/**
 *
 * @author Vatsalya
 */
public class ReadData {
    
    
    private String question, answer, diffi ;
    
    
    public void readData(){
    
        Scanner cs = new Scanner(System.in);
        System.out.println("Enter the Question: ");
        String question = cs.next();
        System.out.println("Enter the Answer: ");
        String answer = cs.next();
        System.out.println("Enter the Difficulty: ");
        String diffi = cs.next();
        
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getDiffi() {
        return diffi;
    }

    public void setDiffi(String diffi) {
        this.diffi = diffi;
    }
    
    
    
}
