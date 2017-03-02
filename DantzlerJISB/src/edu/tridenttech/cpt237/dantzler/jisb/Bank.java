package edu.tridenttech.cpt237.dantzler.jisb;

import java.util.ArrayList;

public class Bank 
{

	ArrayList<SavingsAccount> savingsAccounts = new ArrayList<>();
	ArrayList<CheckingAccount> checkingAccounts = new ArrayList<>();
	static private Bank instance = new Bank();
	
	private Bank()
	{
	
	}
	
	static public Bank getInstance()
	{
		return instance;
	}
	
	public void transfer(String fromAcct, String toAcct, double amt)
	{
		if (getCheckingAccountByNum(fromAcct) != null)
		{
			System.out.printf("Found account %s in checking accounts%n", fromAcct);
			
			if (getCheckingAccountByNum(fromAcct).withdraw(amt))
			{
				System.out.println("Withdraw successful. Continuing transfer.");
				if (getCheckingAccountByNum(toAcct) != null)
				{
					System.out.printf("Found account %s in checking accounts%n", toAcct);
					getCheckingAccountByNum(toAcct).deposit(amt);
				}
				else if (getSavingsAccountByNum(toAcct) != null)
				{
					System.out.printf("Found account %s in savings accounts%n", toAcct);
					getSavingsAccountByNum(toAcct).deposit(amt);
				}
				else
				{
					System.out.printf("Account %s not found%n%n", toAcct);
					getCheckingAccountByNum(fromAcct).deposit(amt);
					System.out.printf("Money returned to account %s%n%n", fromAcct);
				}
			}
		}
		else if (getSavingsAccountByNum(fromAcct) != null)
		{
			System.out.printf("Found account %s in savings accounts%n", fromAcct);
			if (getSavingsAccountByNum(fromAcct).withdraw(amt))
			{
				System.out.println("Withdraw successful. Continuing transfer.");
				if (getCheckingAccountByNum(toAcct) != null)
				{
					System.out.printf("Found account %s in checking accounts%n", toAcct);
					getCheckingAccountByNum(toAcct).deposit(amt);
				}
				else if (getSavingsAccountByNum(toAcct) != null)
				{
					System.out.printf("Found account %s in savings accounts%n", toAcct);
					getSavingsAccountByNum(toAcct).deposit(amt);
				}
				else
				{
					System.out.printf("Account %s not found%n%n", toAcct);
					getSavingsAccountByNum(fromAcct).deposit(amt);
					System.out.printf("Money returned to account %s%n%n", fromAcct);
				}
				
			}
		}
		else
		{
			System.out.printf("Account %s not found%n%n", fromAcct);
			
		}
	}
	
	public SavingsAccount getSavingsAccountByNum(String account)
	{
		SavingsAccount foo = null;
		for (SavingsAccount test : savingsAccounts)
		{
			if (test.getAccountNumber().equalsIgnoreCase(account))
			{
				foo = test;
				break;
			}
		}
		return foo;
	}
	
	public CheckingAccount getCheckingAccountByNum(String account)
	{
		CheckingAccount foo2 = null;
		for (CheckingAccount test2 : checkingAccounts)
		{
			if (test2.getAccountNumber().equalsIgnoreCase(account))
			{
				foo2 = test2;
				break;
			}
		}
		if (foo2 != null)
		{
			
			return foo2;
		}
		else
		{
			
			return foo2;
		}
	}
	
	
	// I don't see why we would need to return false for the openSavingsAccount
	public boolean openSavingsAccount(String accntNum, double initialBal)
	{
		
		SavingsAccount newSave = new SavingsAccount(accntNum, initialBal);
		savingsAccounts.add(newSave);
		Transaction newAccount = new Transaction("Open", initialBal, initialBal);
		newSave.transactionList.add(newAccount);
		return true;
	}
	
	public boolean openCheckingAccount(String accntNum, double initialBal)
	{
		if (initialBal < CheckingAccount.MIN_BALANCE)
		{
			return false;
		}
		else
		{
		CheckingAccount newCheck = new CheckingAccount(accntNum, initialBal);
		checkingAccounts.add(newCheck);
		Transaction newAccount = new Transaction("Open", initialBal, initialBal);
		newCheck.transactionList.add(newAccount);
		
		return true;
		}
	}
	
	public ArrayList<SavingsAccount> getSavingsAccounts()
	{
		return new ArrayList<>(savingsAccounts);
		
	}
	
	public ArrayList<CheckingAccount> getCheckingAccounts()
	{
		return new ArrayList<>(checkingAccounts);
	}
	
	public static void main(String[] args) 
	{
		
		
	}

}
