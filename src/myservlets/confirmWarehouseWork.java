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

@WebServlet("/confirmWarehouseWork")
public class confirmWarehouseWork extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public confirmWarehouseWork() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String warehouse = request.getParameter("warehouseName");
		String billNumber = request.getParameter("billNumber");
		
		Connection conn = MyUtils.getStoredConnection(request);
		
		try {
			DBUtils.confirmWarehouseWork(conn, warehouse, billNumber);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("/billList").forward(request, response);
	}

}
