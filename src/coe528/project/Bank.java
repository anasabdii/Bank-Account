/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package coe528.project;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import java.util.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Bank extends Application {
   

    private Button button;
    private Scene loginPage, managerPage,customerPage;
    private static ArrayList<Customer> customers = new ArrayList<Customer>();
    private static Manager admin = new Manager();
    private Customer tempCust;
    private int index = -1;
    
    

    public static void main(String[] args) {
               
         String currentDirectory = System.getProperty("user.dir"); 
         //Adding already existing text files to arrayList of customers
         File dir = new File(currentDirectory);
         for(File file : dir.listFiles()){
             if(file.getName().endsWith((".txt"))){
                    try(Scanner readFile = new Scanner(file)){
                        String un = readFile.next();
                        String pw = readFile.next();
                        int balance = Integer.parseInt(readFile.next());
                    //    System.out.println(un);
                    //    System.out.println(pw);
                     //   System.out.println(balance);
                        customers.add(new Customer(un,pw));
                        for(Customer c : customers){
                            if(c.getUsername().equals(un))
                                c.deposit(balance - 100); 
                                c.setLevel();
                        }
                    }
                    catch(Exception e3){}  
             }
         }
         
        launch(args);
        
    }

    
    @Override
    public void start(Stage window) throws Exception {
        
        window.setTitle("Bank Login");
        
        //Labels for loginPage
        Label label1 = new Label("Username");
        Label label2 = new Label("Password");
        
        //Labels for Manager page
        Label label3 = new Label("To add new customer, enter username and password, then click 'Create Customer' ");
        Label label4 = new Label("New Customer's username:");
        Label label5 = new Label("New Customer's password:");
        Label label6 = new Label("\n\n\n");
        Label label7 = new Label("Enter the username of the customer you wish to delete:");
        Label label8 = new Label("\n\n\n");
        
        //Labels for Customer page
        Label dLabel = new Label("\n\nEnter amount you wish to deposit:");
        Label wLabel = new Label("\n\nEnter amount you wish to withdraw:");
        Label opLabel = new Label("\n\nEnter cost of product you wish to purchase online:");
        Label spaces = new Label("\n\n\n");
        
        //Textfields for customer page
        TextField depositMoney = new TextField();
        TextField withdrawMoney = new TextField();
        TextField op = new TextField();
         
        //Buttons for customer page
        Button getBalanceButton = new Button("Get Balance");
        Button getCurrentLevel = new Button("Get Current Level");
        Button doDepositAction = new Button("Deposit");
        Button doWithdrawAction = new Button("Withdraw");
        Button doOnlinePurchase = new Button("Complete Transaction");
        Button userLogout = new Button("Logout");
        
        
        
        //Username and password inputs and button for login page
        TextField username = new TextField();
        TextField password = new TextField();
        button = new Button("Login");
        Button login2 = new Button("Login2");
        login2.setOnAction(e -> {
        AlertBox.display("Test", "Test");
    });
        
        //Interactive stuff for the manager scene
        Button createCustomer = new Button("Create Customer");
        Button deleteCustomer = new Button("Delete Customer");
        Button managerLogout = new Button("Logout");
        TextField createUsername = new TextField();
        TextField createPassword = new TextField();
        TextField userToDelete = new TextField();
        
        //Setting all button actions
        button.setOnAction(e ->{ 
               String inputtedFileName = username.getText();
               String inputtedPassword = password.getText();
               File f = new File(inputtedFileName + ".txt");
               if(f.exists()) { 
                    try(Scanner readFile = new Scanner(f)){
                         String usernameOnFile = readFile.next();
                         String passwordOnFile = readFile.next();
                         
                     
                     //write something here that will create a new customer object if that customer object is NOT already in the arraylist - we also need to add the balance to our shit
                     
                     
                    if(inputtedFileName.equals(usernameOnFile) && inputtedPassword.equals(passwordOnFile)){ //still inside my try block here
                        if(usernameOnFile.equals("admin")){
                            window.setScene(managerPage);
                            username.clear();
                            password.clear();
                        }
                        
                        
                        else {  //we are also setting our tempCust variable to the 
                           for(int i = 0; i < customers.size(); i++){    
                                  if(customers.get(i).getUsername().equals(inputtedFileName)){
                                      tempCust = customers.get(i);
                                      index = i;
                                  }                        
                           }
                                      window.setScene(customerPage);
                                      username.clear();
                                      password.clear();
                          

                        } 
                        //We will only enter here if username is not admin and the username is not in the array list, but it is still in a text file, otherwise we wouldnt have even entered this if statement
  
                    }
                    else
                        AlertBox.display("Error", "Invalid login credentials!");//ik the password is wrong because we wouldnt have enetered the if statement to begin with if the username was wrong
               
                    }  
                    catch(Exception e1){
                         System.out.println("Something went wrong");
                        }            
               } //End of if statement
               
               else{
                   AlertBox.display("Error", "User does not exist!");// because the file does not exist
                           
               }
                                           
        });
        //end of the set on action thing
        
        managerLogout.setOnAction(e -> {
          
            window.setScene(loginPage);
            createUsername.clear();
            createPassword.clear();
            userToDelete.clear();
         
        } );
        
        
        userLogout.setOnAction(e -> { //updating user balance and loging out
                        try{
                FileWriter writeToFile = new FileWriter(tempCust.getUsername() + ".txt");
                writeToFile.write(tempCust.getUsername() + "\n");
                writeToFile.write(tempCust.getPassword() + "\n");
                writeToFile.write(""+tempCust.getBalance());
                writeToFile.close();
            }
            catch(Exception w){}
            
            window.setScene(loginPage);
            depositMoney.clear();
            withdrawMoney.clear();
            op.clear(); //online purchase 
                });
        
        createCustomer.setOnAction(e -> {
                customers.add(new Customer(createUsername.getText(),createPassword.getText()));
                createUsername.clear();
                createPassword.clear();
                AlertBox.display("New Customer", "Customer has been created!");
                //System.out.println(customers.get(0).getUsername()); //should print out whatever was inputted
                });
        
        deleteCustomer.setOnAction(e -> {
           String deleteThisUsername = userToDelete.getText();
           for(int i = 0; i < customers.size() ; i++){
               if(customers.get(i).getUsername().equals(deleteThisUsername)){
                   admin.deleteC(customers.get(i));
                   customers.remove(i);
                   userToDelete.clear(); //removing text from field
                   break;
               }
  /*             else if (i >= customers.size() || i == 0)
                   AlertBox.display("Error", "File Not Found!");   */
           }          
        });
        
        getBalanceButton.setOnAction(e ->{
            AlertBox.display("Balance", "Balance is $" +tempCust.getBalance());
        });
        
        
        getCurrentLevel.setOnAction(e -> {
            AlertBox.display("Balance", tempCust.getAccountLevel());
        });
       
        doDepositAction.setOnAction(e ->{
            //textfield is depositMoney
            try{
                int amount = Integer.parseInt(depositMoney.getText());
                tempCust.deposit(amount);
                depositMoney.clear();
            }
            catch(NumberFormatException e1){
                     AlertBox.display("Error", "An integer must be entered!");  
                     depositMoney.clear();
            }      
        });
        
        doWithdrawAction.setOnAction(e -> {
            //textfield is withdraw money
            try{
                int amount = Integer.parseInt(withdrawMoney.getText());
                tempCust.withdraw(amount);
                withdrawMoney.clear();
            }
            catch(NumberFormatException e1){
                     AlertBox.display("Error", "An integer must be entered!");  
                     withdrawMoney.clear();
            }      
        });
        
        doOnlinePurchase.setOnAction(e -> {
            //textfield is op
             try{
                int amount = Integer.parseInt(op.getText());
                tempCust.onlinePurchase(amount);
                op.clear();
            }
            catch(NumberFormatException e1){
                     AlertBox.display("Error", "An integer must be entered!");
                     op.clear();
            }  
        });
        
        
        
                    
        //Layout of login page
        VBox layout1 = new VBox(10);
        layout1.setPadding(new Insets(20,20,20,20));
        layout1.getChildren().addAll(label1,username,label2,password,button, login2);
        
        loginPage = new Scene(layout1,1000,700);
       
        //Layout of Manager page   
        VBox layout2 = new VBox(10);
        layout2.setPadding(new Insets(20,20,20,20));
        layout2.getChildren().addAll(label3,label4,createUsername,label5,createPassword,createCustomer,label6,label7,userToDelete,deleteCustomer,label8,managerLogout);
        
        managerPage = new Scene(layout2,1000,700);
        
        //Layout of Customer page
        VBox layout3 = new VBox(10);
        layout3.setPadding(new Insets(20,20,20,20));
        layout3.getChildren().addAll(getBalanceButton,getCurrentLevel,dLabel,depositMoney,doDepositAction,wLabel,withdrawMoney,doWithdrawAction,opLabel,op,doOnlinePurchase,spaces,userLogout);
        
        customerPage = new Scene(layout3,1000,700);
        
        window.setScene(loginPage);
        window.show();
       
        
    }
}