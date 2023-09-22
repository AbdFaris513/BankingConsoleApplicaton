package atmpackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class Transaction_History {
	public static void reciver(int acc,int amt,int pin,int anotherpin) throws SQLException
	{
		boolean in = true;
		String query = "select name from accounts where account_num = "+acc;
		Connection con = ConnectionOfSql.ConnectionOfMysql();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		
		String nme = "";
		while(rs.next())
		{
			nme = (rs.getString(1));
		}
		con.close();
		InsertRecords(in,nme,acc,amt,anotherpin);
	}
	public static void sender(int acc,int amt,int pin,int anotherpin) throws SQLException
	{
		boolean in = false;
		String query = "select name from accounts where account_num = "+acc;
		Connection con = ConnectionOfSql.ConnectionOfMysql();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		
		String nme = "";
		while(rs.next())
		{
			nme = (rs.getString(1));
		}
		con.close();
		InsertRecords(in,nme,acc,amt,anotherpin);
	}
	public static void InsertRecords(boolean in,String name,int acc,int amt,int pin) throws SQLException
	{
		String query = "insert into trans_hist values(?,?,?,?,?)";
		Connection con = ConnectionOfSql.ConnectionOfMysql();
		PreparedStatement pst = con.prepareStatement(query);
		pst.setBoolean(1, in);
		pst.setString(2, name);
		pst.setInt(3, acc);
		pst.setInt(4, amt);
		pst.setInt(5, pin);
		pst.executeUpdate();
//		System.out.println("Insert rows with PST = " + rows);
	}
	public static void showHistory(int pin) throws SQLException
	{
		String query = "select * from trans_hist where pin = "+pin;
		Connection con = ConnectionOfSql.ConnectionOfMysql();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		
		boolean in = false;
		String name = "";
		int acc = 0;
		int amt = 0;
		modenView();
		while(rs.next())
		{
			in = (rs.getBoolean(1));
			name = rs.getString(2);
			acc = rs.getInt(3);
			amt = rs.getInt(4);
			setModenView(name, acc, amt);
		}
		con.close();
		
		
	}
	public static void modenView()
	{
		System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-++-+-+-+-+-+-+-+-+-+");
		System.out.println("|  In/Out   |           Name            |  Acc/No  |   Amount  |");
		System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-++-+-+-+-+-+-+-+-+-+");
	}
	public static void setModenView(String name,int acc,int amt)
	{
		if(amt>=0)//\u001B[32m =>\u001B[0m
		{
			System.out.println("|   \u001B[31m <=\u001B[0m     |"+ nme(name) +"|"+ acc(acc) + "|"+ amt(amt) + "|");
		}
		else
		{
			System.out.println("|   \u001B[32m =>\u001B[0m     |"+ nme(name) +"|"+ acc(acc) + "|"+ amt(amt) + "|");
		}
		lastline();
	}
	public static String nme(String n)
	{
		int l = n.length();int a = (27-l)/2;int b = 27-(l+a);
		for(int i=0;i<a;i++)n = " "+n;
		for(int i=0;i<b;i++)n = n + " ";
		return n;
	}
	public static String acc(int x)
	{
		String n = x+"";
		int l = n.length();
		int a = (10-l)/2;int b = 10-(l+a);
		for(int i=0;i<a;i++)n = " "+n;
		for(int i=0;i<b;i++)n = n + " ";
		return (n);
	}
	public static String amt(int x)
	{
		x = (x)*-1;
		String n = x+"";
		int l = n.length();
		int a = (11-l)/2;int b = 11-(l+a);
		for(int i=0;i<a;i++)n = " "+n;
		for(int i=0;i<b;i++)n = n + " ";
		return (n);
	}
	public static void lastline()
	{
		System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-++-+-+-+-+-+-+-+-+-+");
	}
}
