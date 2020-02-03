/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databsedemo;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author Vatsalya
 */
public class DatabaseDemo {

  
   public static void main(String[] args) throws Exception{ 
      
       //Scannin user Input
       Scanner cs = new Scanner(System.in);
       
       
       CrudOperation co = new CrudOperation();
    
       
       //Database Connection
       DatabaseConnection.buildDatabase();
       
       String status = "";
             
       Application:
       while(!status.equals("q")){
           
           System.out.println("Enter the below Alphabet to operate the Application\n1. [i]Insert Question.\n2. [v]View the Data.\n3. [q]Quit the Application\n*****More features yet to ADD.*****");
           status = cs.next();           
        
           switch(status){
       
            case "i" :
                String question, answer, diffi = "";
                System.out.println("Enter the Question: ");
                question = cs.next();
                System.out.println("Enter the Answer: ");
                answer = cs.next();
                System.out.println("Enter the Difficulty: ");
                diffi = cs.next();
                System.out.println(co.insert(question, answer, diffi));
                co.viewData();
                break;
               
            case "v" :
                co.viewData();
                break;
            
            default:
                System.out.println("Ok Bye.....");
                break Application;
               
           }
       }       
       
       //Closing the Connection
       DatabaseConnection.closeDatabase();
   }
    
}
