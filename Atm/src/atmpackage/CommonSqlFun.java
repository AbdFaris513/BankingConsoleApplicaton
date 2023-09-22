package atmpackage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CommonSqlFun {
	public static int checkbalance(int pin,long acN) throws SQLException
	{
		String query = "select balance from accounts where pin = "+pin+" AND account_num = "+acN;
		Connection con = ConnectionOfSql.ConnectionOfMysql();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		
		int b=0;
		while(rs.next())
		{
			b = (rs.getInt(1));
			System.out.println();
		}
		con.close();
		return b;
	}
	public static void deposit(int pin,int amt,long acN) throws SQLException
	{
		int b = checkbalance(pin,acN);
		amt += b;
		
		String query = "update accounts set balance = "+ amt +" where pin = " + pin +" And account_num = "+acN;
		Connection con = ConnectionOfSql.ConnectionOfMysql();
		Statement st = con.createStatement();
		st.executeUpdate(query);
		System.out.println("\u001B[32mSuccessfuly Update Your Account !!!\u001B[0m");
		System.out.println("Your Balance are : Rs "+ amt);
	}
	public static void withdraw(int pin,int amt,long acc) throws SQLException
	{
		String query = "update accounts set balance = "+ amt +" where pin = " + pin +" And account_num = "+acc;
		Connection con = ConnectionOfSql.ConnectionOfMysql();
		Statement st = con.createStatement();
		st.executeUpdate(query);
		System.out.println("\u001B[32mSuccessfuly Withdraw Your Account !!!\u001B[0m");
	}
	public static int OpposityeDetailes(long acc) throws SQLException {
		String query = "select * from accounts where account_num = "+acc;
		Connection con = ConnectionOfSql.ConnectionOfMysql();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		
		String n = "";
		int age = 0;
		long accN=0;
		int oppPin = 0;
		boolean in = false;
		while(rs.next())
		{
			in = true;
			n = rs.getString(1);
			age = (rs.getInt(2));
			oppPin = rs.getInt(3);
			accN = rs.getInt(4);
			System.out.println();
		}
		con.close();
		
		if(in)
		{
			System.out.println("Opposite Account Details");
			System.out.println("Name       : " + n);
			System.out.println("Age        : " + age);
			System.out.println("Account No : " + accN);
		}
		return oppPin;
	}
	public static void moneyTransDeposit(int pin,int amt,int acN,int anotherPin) throws SQLException
	{
		Transaction_History.reciver(acN,amt,pin,anotherPin);
		int b = checkbalance(pin,acN);
		amt += b;
		
		String query = "update accounts set balance = "+ amt +" where pin = " + pin +" And account_num = "+acN;
		Connection con = ConnectionOfSql.ConnectionOfMysql();
		Statement st = con.createStatement();
		st.executeUpdate(query);
	}
	public static void minus(int pin,int acc,int amt,int minusAmt,int anotherpin) throws SQLException
	{
		Transaction_History.sender(acc, (minusAmt)*-1,pin,anotherpin);
		String query = "update accounts set balance = "+ amt +" where pin = " + pin +" And account_num = "+acc;
		Connection con = ConnectionOfSql.ConnectionOfMysql();
		Statement st = con.createStatement();
		st.executeUpdate(query);
		System.out.println("\u001B[32mSuccessfuly Money Transfer Your Account !!!\u001B[0m");
	}
	public static long getAccountNum(int pin) throws SQLException
	{
		String query = "select account_num from accounts where pin = "+pin;
		Connection con = ConnectionOfSql.ConnectionOfMysql();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		
		long acc = 0;
		while(rs.next())
		{
			acc = (rs.getLong(1));
			System.out.println();
		}
		con.close();
		return acc;
	}
	public static boolean checkAcc(int acc) throws SQLException
	{
		String query = "select account_num from accounts where account_num = "+acc;
		Connection con = ConnectionOfSql.ConnectionOfMysql();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		
		while(rs.next())
		{
			return true;
		}
		con.close();
		return false;
	}
}
