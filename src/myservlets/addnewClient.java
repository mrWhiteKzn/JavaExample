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

@WebServlet("/addnewClient")
public class addnewClient extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public addnewClient() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			Connection conn =ConnectionUtil.getConnection();
			request.setCharacterEncoding("UTF-8");
			String newClient = request.getParameter("newClientName");
			DBUtils.addClient(conn, newClient);
			
			response.sendRedirect(request.getContextPath()+"/ClientList");
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
