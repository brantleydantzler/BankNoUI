package edu.tridenttech.cpt237.dantzler.jisb;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
public class MainClass 
{
	
	
	
	
	public static void main(String[] args) throws FileNotFoundException
	{
		Scanner input;
		input = new Scanner(new File("f:/tmp/transactions.txt"));
		Bank bank = Bank.getInstance();
		while (input.hasNext())
		{
			String line = input.nextLine();
			String[] fields = line.split(",");
			double d = Double.parseDouble(fields[3]);
			
			switch (fields[0].charAt(0))
			{
				case 'o':
				case 'O': 
				{
					if (fields[2].charAt(0) == 'S')
					{
						bank.openSavingsAccount(fields[1], d);
					}
					else if (fields[2].charAt(0) == 'C')
					{
						bank.openCheckingAccount(fields[1],  d);
					}
					else
					{
						System.out.println("Error: Account must be Checking or Savings");
					}
				} break;
				case 'd':
				case 'D':
				{
					System.out.println("Attempting to deposit");
					if (fields[2].charAt(0) == 'S')
					{
						
						bank.getSavingsAccountByNum(fields[1]).deposit(d);
						
					}
					else if (fields[2].charAt(0) == 'C')
					{
						
						bank.getCheckingAccountByNum(fields[1]).deposit(d);
						
					}
					else
					{
						System.out.println("Error: Account must be Checking or Savings");
					}
				} break;
				case 'w':
				case 'W':
				{
					System.out.println("Attempting to withdraw");
					if (fields[2].charAt(0) == 'S')
					{
						bank.getSavingsAccountByNum(fields[1]).withdraw(d);
					}
					else if (fields[2].charAt(0) == 'C')
					{
						bank.getCheckingAccountByNum(fields[1]).withdraw(d);
					}
					else
					{
						System.out.println("Error: Account must be Checking or Savings");
					}
				} break;
				case 't':
				case 'T':
				{
					System.out.println("Attempting to transfer");
					bank.transfer(fields[1], fields[2], d);
				}
			}
		}
		input.close();
		
		Scanner input2 = new Scanner(System.in);
		
		char quit = 'y';
		String testAccount = "";
		double amT;
		String account2;
		while (quit != 'n')
		{
			System.out.println("Input account number to continue.");
			testAccount = input2.nextLine();
			
			if (bank.getCheckingAccountByNum(testAccount) != null)
			{
				System.out.printf("Select an option:%n(D)eposit%n(W)ithdraw%n(S)how Balance%n(T)ransfer%n");
				switch (input2.nextLine().charAt(0))
				{
					case 'd':
					case 'D':
					{
						System.out.println("Please input an amount to deposit");
						amT = input2.nextDouble();
						input2.nextLine();
						bank.getCheckingAccountByNum(testAccount).deposit(amT);
					} break;
					case 'w':
					case 'W':
					{
						System.out.println("Please input an amount to withdraw");
						amT = input2.nextDouble();
						input2.nextLine();
						bank.getCheckingAccountByNum(testAccount).withdraw(amT);
					} break;
					case 's':
					case 'S':
					{
						System.out.printf("$%.2f%n",bank.getCheckingAccountByNum(testAccount).getBalance());
					} break;
					case 't':
					case 'T':
					{
						System.out.println("Input account number to transfer to");
						account2 = input2.nextLine();
						System.out.printf("Input an amount to transfer to account %s%n", account2);
						amT = input2.nextDouble();
						input2.nextLine();
						if (bank.getCheckingAccountByNum(account2) != null)
						{
						bank.transfer(bank.getCheckingAccountByNum(testAccount).getAccountNumber(), account2, amT);
						}
						else if (bank.getSavingsAccountByNum(account2) != null)
						{
							bank.transfer(bank.getCheckingAccountByNum(testAccount).getAccountNumber(), account2, amT);
						}
						else
						{
							System.out.printf("Account %s not found%n", account2);
						}
					} break;
					case 'Z':
					case 'z':
					{
						String newA;
						char aType;
						System.out.printf("Input account number to be opened%n");
						newA = input2.nextLine();
						System.out.printf("Input account type [(S)avings/(C)hecking]%n");
						aType = input2.nextLine().charAt(0);
						
						switch (aType)
						{
							case 'S':
							case 's':
							{
								bank.openSavingsAccount(newA, 0);
								char dep;
								System.out.printf("Would you like to deposit a starting amount into savings account %s?%n", newA);
								dep = input2.nextLine().charAt(0);
								switch (dep)
								{
									case 'y':
									case 'Y':
									{
										double dA;
										System.out.println("Input amount to deposit");
										dA = input2.nextDouble();
										input2.nextLine();
										bank.getSavingsAccountByNum(newA).deposit(dA);
									} break;
								}
							} break;
							case 'C':
							case 'c':
							{
								double dep;
								System.out.printf("How much will you be depositing into checking account %s?%n", newA);
								dep = input2.nextDouble();
								bank.openCheckingAccount(newA, dep);
								
							}
						}
						
					}
				}
				
			}
			else if (bank.getSavingsAccountByNum(testAccount) != null)
			{
				System.out.printf("Select an option:%n(D)eposit%n(W)ithdraw%n(S)how Balance%n(T)ransfer%n");
				switch (input2.nextLine().charAt(0))
				{
					case 'd':
					case 'D':
					{
						System.out.println("Please input an amount to deposit");
						amT = input2.nextDouble();
						input2.nextLine();
						bank.getSavingsAccountByNum(testAccount).deposit(amT);
					} break;
					case 'w':
					case 'W':
					{
						System.out.println("Please input an amount to withdraw");
						amT = input2.nextDouble();
						input2.nextLine();
						bank.getSavingsAccountByNum(testAccount).withdraw(amT);
					} break;
					case 's':
					case 'S':
					{
						System.out.printf("$%.2f%n", bank.getSavingsAccountByNum(testAccount).getBalance());
					} break;
					case 't':
					case 'T':
					{
						System.out.println("Input account number to transfer to");
						account2 = input2.nextLine();
						System.out.printf("Input an amount to transfer to account %s%n", account2);
						amT = input2.nextDouble();
						input2.nextLine();
						if (bank.getCheckingAccountByNum(account2) != null)
						{
							bank.transfer(bank.getSavingsAccountByNum(testAccount).getAccountNumber(), account2, amT);
						}
						else if (bank.getSavingsAccountByNum(account2) != null)
						{
							bank.transfer(bank.getSavingsAccountByNum(testAccount).getAccountNumber(), account2, amT);
						}
						else
						{
							System.out.printf("Account %s not found%n", account2);
						}
					} break;
					case 'z':
					case 'Z':
					{
						String newA;
						char aType;
						System.out.printf("Input account number to be opened%n");
						newA = input2.nextLine();
						System.out.printf("Input account type [(S)avings/(C)hecking]%n");
						aType = input2.nextLine().charAt(0);
						
						switch (aType)
						{
							case 'S':
							case 's':
							{
								bank.openSavingsAccount(newA, 0);
								char dep;
								System.out.printf("Would you like to deposit a starting amount into savings account %s?%n", newA);
								dep = input2.nextLine().charAt(0);
								switch (dep)
								{
									case 'y':
									case 'Y':
									{
										double dA;
										System.out.println("Input amount to deposit");
										dA = input2.nextDouble();
										input2.nextLine();
										bank.getSavingsAccountByNum(newA).deposit(dA);
									}
								}
							} break;
							case 'C':
							case 'c':
							{
								
								double dep;
								System.out.printf("How much will you be depositing into checking account %s?%n", newA);
								dep = input2.nextDouble();
								input2.nextLine();
								Bank.getInstance().openCheckingAccount(newA, dep);
							}
						}
						
					}
				}
			}
			else
			{
				System.out.printf("Account %s does not exist%n", testAccount);
			}
			System.out.println("Would you like to continue? [y/n]");
			quit = input2.nextLine().charAt(0);
		}
		input2.close();
		
		for (CheckingAccount f: bank.getCheckingAccounts())
		{
			System.out.println(f.getAccountNumber());
			System.out.printf("%-20s%10s%20s%n", "Transaction Type", "Amount", "Balance");
			for (Transaction s: f.getTransactionList())
			{
				System.out.printf("%-20s%10.2f%20.2f%n", s.getTranType(), s.getTranAmount(), s.getEndBal());
			}
			System.out.printf("%n");
		}
		
		for (SavingsAccount f: bank.getSavingsAccounts())
		{
			System.out.println(f.getAccountNumber());
			System.out.printf("%-20s%10s%20s%n", "Transaction Type", "Amount", "Balance");
			for (Transaction s: f.getTransactionList())
			{
				System.out.printf("%-20s%10.2f%20.2f%n", s.getTranType(), s.getTranAmount(), s.getEndBal());
			}
		}
		
		
	}

}
