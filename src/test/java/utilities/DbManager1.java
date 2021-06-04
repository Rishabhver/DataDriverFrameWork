package utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DbManager1 {
	
	private static Connection con = null;

	public static void setMySqlDbConnection() throws SQLException
	{
		       // We have stored all the values in TestConfig1 class and fetching from there
		try {
			Class.forName(TestConfig1.driverclass);
			con = DriverManager.getConnection(TestConfig1.mySqlUrl,TestConfig1.username,TestConfig1.password);
			if(!con.isClosed())
				System.out.println("Successfully connected to MySQL server");
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		
		
		
	}
	
	
public static List<String> getMysqlQuery(String query) throws SQLException{
		
		// to fetch results from query
		Statement St = con.createStatement();
		ResultSet rs = St.executeQuery(query);
		List<String> values1 = new ArrayList<String>();
		while(rs.next()){
			
			values1.add(rs.getString(1));
			
			
		}
		return values1;
}
}
