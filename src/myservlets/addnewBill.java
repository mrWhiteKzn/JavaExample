package myservlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import conn.ConnectionUtil;
import utils.DBUtils;

@WebServlet("/addnewBill")
public class addnewBill extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public addnewBill() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String clientName = request.getParameter("clientName");
		String billNumber = request.getParameter("billNumber");
		String time = request.getParameter("time");	
		
		String[] warehouses = request.getParameterValues("warehouses");
		
		try {
			Connection conn = ConnectionUtil.getConnection();
			DBUtils.addBill(conn, clientName, billNumber, time, warehouses);
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("billList").forward(request, response);
	}

}
