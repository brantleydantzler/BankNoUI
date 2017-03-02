package edu.tridenttech.cpt237.dantzler.jisb;

public class SavingsAccount extends Account 
{
	protected final int MAX_WITHDRAWS = 5;
	protected final double SERVICE_FEE = .23;
	protected int numWithdrawals;
	
	
	public SavingsAccount(String account, double initBal)
	{
		super(account, initBal);
		System.out.printf("You've made a savings account with the account number %s with a balance of $%.2f%n%n", account, initBal);
	}
	
	public boolean withdraw(double amount)
	{
		System.out.printf("Starting balance before transaction: $%.2f%n", balance);
		if (amount > super.balance)
		{
			System.out.printf("Not enough funds in account %s%n", accountNumber);
			return false;
		}
		
		numWithdrawals++;
		if (numWithdrawals > MAX_WITHDRAWS)
		{
			
			super.balance -= (amount + SERVICE_FEE);
			Transaction newFee = new Transaction("Fee", SERVICE_FEE, balance); //fee transaction
			transactionList.add(newFee); 
			System.out.printf("A fee of $%.2f has been applied to account %s for going over the maximum number of withdraws%n", SERVICE_FEE, accountNumber);
			System.out.printf("You withdrew $%.2f%nNew balance for account %s is %.2f%n%n", amount, accountNumber, balance);
			return true;
		}
		super.balance -= amount;
		Transaction newTran = new Transaction("Withdraw", amount, balance); //withdraw transaction
		transactionList.add(newTran);
		System.out.printf("You withdrew $%.2f%nNew balance for account %s is $%.2f%n%n", amount, accountNumber, balance);
		return true;
	}
	
	public void resetWithdrawalCount() 
	{
		numWithdrawals = 0;
	}
	
	public static void main(String[] args) 
	{
		

	}

}
