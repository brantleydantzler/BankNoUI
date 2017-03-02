package edu.tridenttech.cpt237.dantzler.jisb;

public class CheckingAccount extends Account
{

	protected final static double MIN_BALANCE = 25;
	protected final double MIN_FEE = .13;
	
	public CheckingAccount(String number, double initBal)
	{
		super(number, initBal);
		System.out.printf("You've made a checking account with the account number %s with a balance of $%.2f%n%n", number, initBal);
	}
	
	@Override
	public boolean withdraw(double amount)
	{
		System.out.printf("Starting balance before transaction: $%.2f%n", balance);
		if (amount > super.balance - MIN_FEE)
		{
			System.out.printf("Not enough funds in account %s", accountNumber);
			return false;
		}
		super.balance -= amount;
		
		Transaction newTran = new Transaction("Withdrawal", amount, balance); //withdraw transaction
		transactionList.add(newTran);
		
		if (super.getBalance() < MIN_BALANCE)
		{
			super.balance -= .13;
			System.out.printf("A fee of $%.2f has been applied to account %s for not meeting the minimum balance requirement%n", MIN_FEE, accountNumber);
			
			Transaction newFee = new Transaction("Fee", MIN_FEE, balance); //fee transaction 
			transactionList.add(newFee);
			
			System.out.printf("You withdrew $%.2f%nNew balance for account %s is $%.2f%n%n", amount, accountNumber, balance);
			return true;
		}
		System.out.printf("You withdrew $%.2f%nNew balance for account %s is $%.2f%n%n", amount, accountNumber, balance);
		return true;
	}
	
	
	public static void main(String[] args) {
		

	}

}
