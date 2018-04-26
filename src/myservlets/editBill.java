package myservlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Warehouse;
import utils.DBUtils;
import utils.MyUtils;

@WebServlet("/editBill")
public class editBill extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public editBill() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = MyUtils.getStoredConnection(request);
		
		String billNumberToEdit;
		request.setCharacterEncoding("UTF-8");
		billNumberToEdit = request.getParameter("billNumberToEdit");
		List<Warehouse> warehouseList = new ArrayList<>();
		
		try {
			 warehouseList = DBUtils.getListWarehouses(conn, billNumberToEdit);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("billNumber", billNumberToEdit);
		request.setAttribute("warehouseListInJob",	warehouseList);
		
		request.getRequestDispatcher("WEB-INF/views/editBill.jsp").forward(request, response);
	}

}
