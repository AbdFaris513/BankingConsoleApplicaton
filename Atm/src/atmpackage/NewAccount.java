package atmpackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class NewAccount {
	private static String name;
	private static int pin;
	private static int age;
	private static long account_num = 10001;
	private static int balance;
	
	Scanner sc = new Scanner(System.in);
	public NewAccount() throws SQLException
	{
		while(true)
		{
			System.out.println("Enter your Name : ");
			name = sc.nextLine();
			if(!name.matches("[a-zA-Z ]+"))
				System.out.println("Enter a Valid Name");
			else
				break;
		}
		while(true)
		{
			System.out.println("Enter your Age : ");
			age = sc.nextInt();
			if(age<18)
				System.out.println("Sorry you have atleast 18+");
			else if(age>150)
				System.out.println("Please Enter Valid Age!!!");
			else
				break;
		}
		while(true)
		{
			System.out.println("Create your Pincode : ");
			pin = sc.nextInt();
			String pins = ""+pin;
			if(pins.length()!=4)
				System.out.println("Please only four digit");
			else
				break;
		}

		String query = "insert into accounts (name,age,pin) values('"+name+"',"+age+","+pin+")";
		Connection con = ConnectionOfSql.ConnectionOfMysql();
		Statement st = con.createStatement();
		int rows = st.executeUpdate(query);
		System.out.println("\n\u001B[32mSuccessfuly Created Your Account !!!\u001B[0m");
		long acc = CommonSqlFun.getAccountNum(pin);
		System.out.println("Your Account number is : \u001B[32m" + acc +"\u001B[0m");
		
		Atm_DashBoard.displayFirstPage();
	}
	public NewAccount(String name,int pin)
	{
		
	}
	public NewAccount(String name,int pin,long account_num)
	{
		
	}
}
