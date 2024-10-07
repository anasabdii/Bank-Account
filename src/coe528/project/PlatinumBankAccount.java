/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coe528.project;


public class PlatinumBankAccount extends BankAccount {
    
     public void onlinePurchase(int amount){
         if(balance >= amount){
            balance -= amount;
          // System.out.println("You have made an online purchase of $" + amount);
                AlertBox.display("Platinum Member Purchase Confirmation", "You have made a purchase of $" + amount +"\nand with no additional service charge\nthe total cost is $" + (amount));
         }
         else
             AlertBox.display("Error Message", "You do not have the funds to make a puchase of $" + amount);
    }
     public String getAccountLevel(){
         return "You are a Platinum Member";
     }
    
}