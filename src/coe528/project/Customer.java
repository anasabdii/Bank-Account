/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coe528.project;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.*;

public class Customer {
    
    private String username;
    private String password;
    private String role = "customer";
    private BankAccount account;
   
    public Customer(String username, String password){
        try{
            File loginInfo = new File(username + ".txt");
            if(loginInfo.createNewFile() == true){
                System.out.println("User created:" + loginInfo.getName());
                this.username = username;
                this.password = password;
                account = new SilverBankAccount(); //After money has been deposited, the bankAccount level can change to gold/platinum
                FileWriter writeToFile = new FileWriter(username + ".txt");
                writeToFile.write(username + "\n");
                writeToFile.write(password + "\n");
                writeToFile.write(""+100);
                writeToFile.close();
            }
            else if(loginInfo.exists()){
                this.username = username;
                this.password = password;
                account = new SilverBankAccount();
            }
            else{
                System.out.println("User already exists");
                return;
            }
        }catch(IOException e){
            System.out.println("IOException occured");
        }
    }
    
    public int getBalance(){
        return account.getBalance();
    }
    public void deposit(int amount){
        account.addAmount(amount);
        //System.out.println("$" + amount + " has been deposited");
        AlertBox.display("Deposit", "$" + amount + " has been deposited");
        this.setLevel();
    }

    public void withdraw(int amount){
        if(account.getBalance() >= amount){
            account.removeAmount(amount);
            //System.out.println("$" + amount + " Has been withdrawn\nYou new balance is $"+account.getBalance());
             AlertBox.display("Deposit", "$" + amount + " has been withdrawed"); 
            this.setLevel();
        }
        else
             AlertBox.display("Deposit", "You do not have enough funds to withdraw $" +amount); 
            //System.out.println("You do not have enough funds to withdraw $" +amount );
    }
    public void onlinePurchase(int amount){
        if(amount < 50){
            AlertBox.display("Deposit", "Online purchase must be at least $50"); 
            return; }
        account.onlinePurchase(amount);
        this.setLevel();
    }
    
    public void setLevel(){ //private because only other methods in this class use it, no reason for it to be public
        int balance = account.getBalance();
        
        if(balance < 10000){
            account = new SilverBankAccount();
            account.setBalance(balance);
        }
        if(balance >= 10000 && balance < 20000){
            account = new GoldBankAccount();
            account.setBalance(balance);
        }       
        if(balance > 20000){
            account = new PlatinumBankAccount();
            account.setBalance(balance);
        }
            
    }
    
    public String getAccountLevel(){
        return account.getAccountLevel();
    }
    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
}
