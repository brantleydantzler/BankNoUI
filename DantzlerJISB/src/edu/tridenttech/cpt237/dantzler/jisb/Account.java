package edu.tridenttech.cpt237.dantzler.jisb;

import java.util.ArrayList;

public abstract class Account 
{
	protected double balance;
	protected String accountNumber;
	protected ArrayList<Transaction> transactionList = new ArrayList<>();
	
	
	public Account(String number, double initBal)
	{
		accountNumber = number;
		balance = initBal;
	}
	
	public boolean deposit(double amount) 
	{
		System.out.printf("Starting balance before transaction: $%.2f%n", balance);
		balance += amount;
		Transaction newTran = new Transaction("Deposit", amount, balance); // deposit transaction
		transactionList.add(newTran);
		System.out.printf("You deposited $%.2f%nNew balance for account %s is $%.2f%n%n", amount, accountNumber, balance);
		return true;
	}
	
	public boolean withdraw(double amount)
	{
		if (amount > balance)
		{
			System.out.printf("Not enough funds in account %s%n", accountNumber);
			return false;
		}
		System.out.printf("Starting balance before transaction: $%.2f%n", balance);
		this.balance -= amount;
		Transaction newTran = new Transaction("Withdrawal", amount, balance); // withdraw transaction
		transactionList.add(newTran);
		System.out.printf("You withdrew $%.2f%nNew balance for account %s is %.2f%n%n", amount, accountNumber, balance);
		return true;
	}
	
	public double getBalance() 
	{
		return balance;
	}

	public String getAccountNumber() 
	{
		return accountNumber;
	}

	
	public ArrayList<Transaction> getTransactionList() {
		return new ArrayList<>(transactionList);
	}

	public static void main(String[] args) 
	{
		

	}

}
