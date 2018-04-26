package myservlets;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.DBUtils;
import utils.MyUtils;

@WebServlet("/deleteWh")
public class deleteWh extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public deleteWh() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		Connection conn  = MyUtils.getStoredConnection(request);
		String wh_name = request.getParameter("name");
		
		DBUtils.deleteWarehouse(conn, wh_name);
		}catch(Exception ex){}
		
		response.sendRedirect(request.getContextPath()+"/warehouse");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
