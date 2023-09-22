package atmpackage;
import java.sql.*;
public class ConnectionOfSql {
	private static final String url = "jdbc:mysql://localhost:3306/jdbc";
	private static final String username = "root";
	private static final String password = "abdf";
	public static Connection ConnectionOfMysql() throws SQLException
	{
		return DriverManager.getConnection(url, username, password);
	}
	
	
}
