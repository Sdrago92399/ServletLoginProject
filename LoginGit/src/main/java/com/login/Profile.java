package com.login;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
						HttpServletResponse response)
		throws ServletException, IOException
	{

		PrintWriter out = response.getWriter();

		String name = "";
		boolean islogin = false;

		// request object will return an array of cookies
		Cookie[] cookies = request.getCookies();
		if (cookies == null) {
			out.println(
				"<h1> You are a new user, kindly login. </h1>");
			request.getRequestDispatcher("login.jsp")
				.include(request, response);
		}
		else {
			for (Cookie c : cookies) {
				String tempName
					= c.getName();
			
				if (tempName.equals("user"))

				{
					name = c.getValue(); 
					
					out.println(
						"<a href='logout' style='font-size:25px;'>Logout </a>");
					out.println(
						"<h1>Welcome to your profile, "
						+ name);
					
					islogin=true;
				}
			}
			
			if (!islogin) {
				out.println(
						"<h1>You are not logged in</h1>");
			}
		}
	}

	protected void doPost(HttpServletRequest request,
						HttpServletResponse response)
		throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}

