package com.lms.login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lms.DBConnection.DB;
import com.mysql.cj.protocol.Resultset;


@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String un = request.getParameter("username");
		String pass = request.getParameter("password");
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher = null;
		
		try {
			Connection conn = DB.connect();
			PreparedStatement pst = conn.prepareStatement("SELECT username,role,firstname,lastname FROM users WHERE username=? AND password=? AND status='active'");
			pst.setString(1, un);
			pst.setString(2, pass);
			
			ResultSet rs = pst.executeQuery();		
			
			if(rs.next()) {
				int role = rs.getInt("role");
				if(role==1) {
					dispatcher = request.getRequestDispatcher("admin_dashboard/index.jsp");
				}
			 }else {
					request.setAttribute("status", "failed");
					dispatcher = request.getRequestDispatcher("index.jsp");
				}
			 	
				dispatcher.forward(request, response);
			}
		catch(Exception e) {
			
		}
	}

}
