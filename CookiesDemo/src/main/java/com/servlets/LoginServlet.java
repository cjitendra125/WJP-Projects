package com.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pwtr=response.getWriter();
		try {
			String username=request.getParameter("username");
			String password=request.getParameter("password");
			if(username.equals("admin") && password.equals("admin@123")) {
				Cookie ck=new Cookie("usernameCookie", username);
				ck.setMaxAge(20);
				response.addCookie(ck);
				response.sendRedirect("ProfileServlet");
			}else {
				pwtr.println("<script>alert('Invalid username or password'); location.href='index.html';</script>");
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
	}

}
