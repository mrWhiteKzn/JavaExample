package myservlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.DBUtils;
import utils.MyUtils;

/**
 * Servlet implementation class changeClientProperties
 */
@WebServlet("/changeClientProperties")
public class changeClientProperties extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public changeClientProperties() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Connection conn = MyUtils.getStoredConnection(request);
		
		String oldName = request.getParameter("oldName");
		String newName = request.getParameter("clientNameField");
		
		try {
			DBUtils.changeClientProperties(conn, oldName, newName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("/ClientList").forward(request, response);
	}

}
