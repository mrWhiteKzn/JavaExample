package myservlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Warehouse;
import utils.DBUtils;
import utils.MyUtils;

@WebServlet("/warehouse")
public class warehouse extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public warehouse() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {			
			Connection conn  = MyUtils.getStoredConnection(request);	
			List<Warehouse> warehouseList = DBUtils.getWarehouses(conn);

			request.setAttribute("warehouseList", warehouseList);
			request.getRequestDispatcher("/WEB-INF/views/warehouseView.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

}
