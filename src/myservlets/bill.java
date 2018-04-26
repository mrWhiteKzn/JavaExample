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

@WebServlet("/bill")
public class bill extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public bill() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Warehouse> warehouseList = null;
		try {
			Connection conn  = MyUtils.getStoredConnection(request);
			warehouseList = DBUtils.getListWarehouses(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("warehouseList", warehouseList);
		request.getRequestDispatcher("/WEB-INF/views/addBill.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
