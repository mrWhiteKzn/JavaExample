package myservlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/editClient")
public class editClient extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public editClient() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String clientName = request.getParameter("clientName");
		
		request.setAttribute("clientName", clientName);
		
		request.getRequestDispatcher("WEB-INF/views/editClient.jsp").forward(request, response);
		
	}

}
