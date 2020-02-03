/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databsedemo;

import static databsedemo.DatabaseConnection.conn;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Vatsalya
 */
public class CrudOperation {
    
    Statement stmt = null;
    PreparedStatement preparedStmt = null;
    
    
    //Inserting Data into 
    public String insert(String QUESTION, String ANSWER, String DIFFICULTY){
        
        String query = " insert into MST_QUESTION (QUESTION, ANSWER, DIFFICULTY) values (?, ?, ?)";
        String st = "";
        boolean status = false;
        
        //preparedstatement to Insert
        try{
            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString (1, QUESTION);
            preparedStmt.setString (2, ANSWER);
            preparedStmt.setString (3, DIFFICULTY);
      
            // execute the preparedstatement
            status = preparedStmt.execute();
        }
        catch(SQLException sqlex){
            //Handles SQL Exception
            sqlex.printStackTrace();
        }
        return st = status == true ? "Data Not Inserted." : "Data Inserted.";
    }
    
    
    //Viewing Database
    public void viewData(){
       
       String query = "select * from MST_QUESTION";
       
       try{
           
        stmt = DatabaseConnection.conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
      
        //Iterating Database Result
        System.out.println("--------------------Viewing Data from Database--------------------");
        while (rs.next())
        {
            int id = rs.getInt("QUEST_ID");
            String QUESTION = rs.getString("QUESTION");
            String ANSWER = rs.getString("ANSWER");
            String DIFFICULTY = rs.getString("DIFFICULTY");
        
            //Printing Results in Format
            System.out.format("%s, %s, %s, %s\n", id, QUESTION, ANSWER, DIFFICULTY);
        }
        System.out.println("------------------------------------------------------------");
      
       }catch(SQLException sqlex){
           
            //Handles SQL Exception
           sqlex.printStackTrace();
       }
        
    }
    
}
