package edu.tridenttech.cpt237.dantzler.jisb;



public class Transaction {

	protected String tranType;
	protected double tranAmount;
	protected double endBal;
	
	
	public Transaction(String type, double amount, double endBal)
	{
		tranType = type;
		tranAmount = amount;
		this.endBal = endBal;
	}
	

	public String getTranType() 
	{
		return tranType;
	}

	public double getTranAmount() 
	{
		return tranAmount;
	}
	
	public double getEndBal() 
	{
		return endBal;
	}


	public static void main(String[] args) 
	{
		Bank bank2 = Bank.getInstance();
		bank2.openCheckingAccount("ABC123", 400);
		bank2.getCheckingAccountByNum("ABC123").deposit(3);
		

	}

}
