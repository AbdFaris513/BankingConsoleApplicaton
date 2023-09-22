package atmpackage;

import java.sql.SQLException;
import java.util.Scanner;

public class Atm_DashBoard {
	
	public static void displayFirstPage() throws SQLException
	{
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Existing Account Click  : 1");
		System.out.println("Create a Account Click  : 2");
		System.out.println("Exit a Account Click    : 3");
		int x = sc.nextInt();
		if(x == 2)
		{
			NewAccount o = new NewAccount();
		}
		else if(x==1)
		{
			ExistingAccount.displayExistinAcc();
		}
		else
		{
			return;
		}
	}
}
