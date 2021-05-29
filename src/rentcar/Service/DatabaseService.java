package rentcar.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseService {
	
	public static Connection con;
	
	public static void initConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");  
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rentcar","root","");  		
		} catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public static PreparedStatement prepareStatement(final String statement) throws SQLException {
		PreparedStatement stmt = con.prepareStatement(statement);
		return stmt;
	}
	
	public static void closeConnection() {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
}
