package utils;

import java.sql.Connection;

import javax.servlet.ServletRequest;

public class MyUtils {
	private static final String ATT_NAME_CONNECTION = "ATTRIBUTE_FOR_CONNECTION";
	
	public static Connection getStoredConnection(ServletRequest request) {
		
		Connection con = (Connection) request.getAttribute("ATTRIBUTE_FOR_CONNECTION");
		return con;
	}
	
	public static void storeConnection(ServletRequest request, Connection conn) {
		request.setAttribute(ATT_NAME_CONNECTION, conn);
	}
}
