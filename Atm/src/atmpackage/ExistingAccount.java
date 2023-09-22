package atmpackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ExistingAccount {
	static Scanner sc = new Scanner(System.in);
	public static void displayExistinAcc() throws SQLException
	{
		
		System.out.println("Enter You Pin : ");
		int pin  = sc.nextInt();
		System.out.println("Enter You Account Number : ");
		int ac  = sc.nextInt();
		read(pin,ac);
	}
	public static void read(int pin,int ac) throws SQLException
	{
		String name = "";
		String query = "select * from accounts where pin = "+pin+" And account_num = "+ac;
		Connection con = ConnectionOfSql.ConnectionOfMysql();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		boolean in = false;
		while(rs.next())
		{
			name = (rs.getString(1) + " ");
//			System.out.print(rs.getInt(2) + " ");
			System.out.println();
			in = true;
		}
		if(!in)
		{
			System.out.println("\u001B[31m" +"Error: "+"\u001B[0m" + "Pin or Account Number is Wrong!!!\n" );
			Atm_DashBoard.displayFirstPage();
		}
		else
		{
			System.out.println("\t *** Welcome " + name.toUpperCase()+" ***\n");
			fourProcess(pin,ac);
		}
		
	}
	public static void fourProcess(int pin,int ac) throws SQLException
	{
		System.out.println("Withdraw            : 1 ");
		System.out.println("Deposite            : 2 ");
		System.out.println("Check Balance       : 3 ");
		System.out.println("Money Tansfore      : 4 ");
		System.out.println("Transaction History : 5");
		System.out.println("Home                : 6 ");
		int opperation = sc.nextInt();
		if(opperation == 1)
		{
			withdraw(pin, ac);
			fourProcess(pin, ac);
		}
		else if(opperation == 2)
		{
			deposit(pin, ac);fourProcess(pin, ac);
		}
		else if(opperation == 3)
		{
			checkbalance(pin, ac);fourProcess(pin, ac);
		}
		else if(opperation == 4)
		{
			moneyTrans(pin, ac);fourProcess(pin, ac);
		}
		else if(opperation == 5)
		{
			Transaction_History.showHistory(pin);
		}
		else
			Atm_DashBoard.displayFirstPage();
	}
	public static void withdraw(int oldPin,long acN) throws SQLException
	{
		int amt = 0;
		do{
			System.out.println("Enter Your Withdraw Amount : ");
			amt = sc.nextInt();
			if(amt%10 != 0)
			{
				System.out.println("Please Enter Valid Amount!!! ");
			}
			else
				break;
		}while(true);
		int b = CommonSqlFun.checkbalance(oldPin,acN);
		if(b>=amt)// summa
		{
			amt = b - amt ;
			CommonSqlFun.withdraw(oldPin, amt,acN);
		}
		else
		{
			System.out.println("Your Bank Balance Are "+ b);
		}
	}
	public static void deposit(int oldPin,long acN) throws SQLException
	{
		System.out.println("Enter Your Amount : ");
		int amt = sc.nextInt();
		CommonSqlFun.deposit(oldPin, amt,acN);
	}
	public static void checkbalance(int oldPin,long acN) throws SQLException
	{
		System.out.println("\u001B[32mYour Account Balance Are  Rs "+CommonSqlFun.checkbalance(oldPin,acN)+"\u001B[0m");
	}
	public static void moneyTrans(int pin,int acc) throws SQLException
	{
		System.out.println("\t *** Welcome to HighBank Monet Transfer ***");
		int oppAcc = 0;
		do {
			System.out.println("Enter Another Person Account Number : ");
			oppAcc = sc.nextInt();
			String a = oppAcc + "";
			if(!CommonSqlFun.checkAcc(oppAcc))
			{
				System.out.println("Enter a Correct Account number");
			}
			else
			{
				break;
			}
		}while(true);
		int oopPin = CommonSqlFun.OpposityeDetailes(oppAcc);
		System.out.println("Enter Your Amount : ");
		int amt = sc.nextInt();
		int b = CommonSqlFun.checkbalance(pin, acc);
		int minusAmt = b-amt;
		if(amt<=b)
		{
			CommonSqlFun.moneyTransDeposit(oopPin, amt, oppAcc,pin);
			CommonSqlFun.minus(pin,acc,minusAmt,amt,oopPin);
		}
		else
		{
			System.out.println("You Don't Have that Amount of Money");
		}
	}
}
