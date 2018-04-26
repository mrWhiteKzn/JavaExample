package conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {
	public static Connection getMySQLConnection() throws SQLException, ClassNotFoundException {
		
		String host = "192.168.5.248";
		String port = "3306";
		String dbName = "dbassembly";
		String loginName = "root";
		String password = "1234567";
		
		return getMySQLConnection(host, port, dbName, loginName, password);		
	}
	
	public static Connection getMySQLConnection(String host, String port, String dbName, String loginName, String password) throws SQLException, ClassNotFoundException {
		
		String stringCon = "jdbc:mysql://"+host+":"+port+"/"+dbName;			
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(stringCon, loginName, password);
		return con;
	}
}