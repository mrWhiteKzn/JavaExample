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

import beans.Bill;
import utils.DBUtils;
import utils.MyUtils;

@WebServlet("/billList")
public class billList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public billList() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Connection conn  = MyUtils.getStoredConnection(request);
			List<Bill> billList = DBUtils.getBill(conn);
			
			request.setAttribute("billList", billList);
			request.getRequestDispatcher("/WEB-INF/views/billView.jsp").forward(request, response);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
