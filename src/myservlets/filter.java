package myservlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import conn.ConnectionUtil;
import utils.MyUtils;

@WebFilter(filterName="filter", urlPatterns= {"/*"})
public class filter implements Filter {

    public filter() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			conn.setAutoCommit(false);
						
			MyUtils.storeConnection(request, conn);
			chain.doFilter(request, response);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionUtil.closeQuitly(conn);
		}
		
		
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
