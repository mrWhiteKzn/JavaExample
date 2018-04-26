package myservlets;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.DBUtils;
import utils.MyUtils;
import beans.Client;

@WebServlet("/ClientList")
public class ClientList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ClientList() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con  = MyUtils.getStoredConnection(request);
		List<Client> clientList = null;		

		try {
			clientList = DBUtils.getClients(con);
		}catch(Exception e){
			System.out.println(e);
		}
		request.setAttribute("clientList", clientList);
		request.getRequestDispatcher("/WEB-INF/views/clientView.jsp").forward(request, response);		
	}
	
	
}
