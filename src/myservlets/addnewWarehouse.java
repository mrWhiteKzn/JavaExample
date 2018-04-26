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

@WebServlet("/addnewWarehouse")
public class addnewWarehouse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public addnewWarehouse() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn;
		
		request.setCharacterEncoding("UTF-8");
		String wh_name = request.getParameter("newWarehouse");

		try {
			conn = ConnectionUtil.getConnection();
			System.out.println(wh_name);
			DBUtils.addWarehouse(conn, wh_name);
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
		
		response.sendRedirect(request.getContextPath()+"/warehouse");
	}

}
