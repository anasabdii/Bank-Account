/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coe528.project;

public class GoldBankAccount extends BankAccount {
    
      public void onlinePurchase(int amount){
          if(balance >= amount + 10){
               balance -= (amount + 10);
           // System.out.println("You have made an online purchase of $" + (amount + 10));
         AlertBox.display("Gold Member Purchase Confirmation", "You have made a purchase of $" + amount +"\nand with the additional service charge of $10\nthe total cost is $" + (amount +10));
          }
          else
              AlertBox.display("Error Message", "You do not have the funds to make a puchase of $" + amount +"\nwhich has a total cost of $" + (amount+10));
    }
      public String getAccountLevel(){
          return "You are a Gold Member";
      }
    
}