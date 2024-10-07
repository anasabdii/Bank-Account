/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coe528.project;

import java.util.*;

//Overview: a BankAccount represents the amount of money a user has
//          a BankAccount's balance must be greater than or equal to 
//          zero. BankAccounts are mutable.

// AF(c) = a bank account, B, such that
//         B.balance = c.balance

// RI(c) = true if c.balance >= 0
//       = false otherwise

public abstract class BankAccount {
    
    //rep
    protected int balance;
    
    //EFFECTS: sets this.balance to 100
    public BankAccount(){
        balance = 100;
    }
    
    //EFFECTS: returns balance
    protected int getBalance(){
        return balance;
    }
    
    //EFFECTS: Sets this.balance to newBalance if newBalance >= 0
    protected void setBalance(int newBalance){
        if(newBalance >= 0)
            this.balance = newBalance;
    }
    
    
    //EFFECTS: adds amount to this.balance if amount >= 0
    protected void addAmount(int amount){
        balance += amount;
    }
    
    //EEFECTS: removes amount from this.balance if amount >= 0 and amount <= this.balance
    protected void removeAmount(int amount){
            balance -= amount;
    }
    
    //EFFECTS: If amount >= 50, and if this.balance >= (amount + a varying service charge), then amount + varying service charge is subtracted from this.balance 
    public void onlinePurchase(int amount){} 
    
    
    //EFFECTS: Returns state of current account level
    public  String getAccountLevel(){return "String in abstract";}
    
    
    //EFFECTS: returns abstraction function - that is returns balance of this
    @Override
    public String toString(){
        return "Balance is $" + balance;
    }
    
    //EFFECTS: Returns true if the Rep Invariant 
    //holds, otherwise returns false
    public boolean RepOK(){
        if(this.balance > 0)
            return true;
        return false;
    }
    
}
