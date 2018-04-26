package conn;

import java.sql.SQLException;
import java.sql.Connection;

public class ConnectionUtil {
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		return  MySQLConnection.getMySQLConnection();
	}
	
	public static void closeConnection(Connection con) {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void closeQuitly(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
