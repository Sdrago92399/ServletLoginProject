package com.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class Signup extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Jdbc mysql = new Jdbc();
	Methods Do = new Methods();
	
	PrintWriter out = null;
	RequestDispatcher rd = null;
	
	String user;
	String pass;
	String email;
	String phone;
	int otp;
	int userid;
	
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		mysql.jdbcInit();
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("uncool");
	}

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub  
		try {
			
			resp.setContentType("text/html");
			out = resp.getWriter();
			
			user = req.getParameter("user");
			email = req.getParameter("mail");
			phone = req.getParameter("phone");
			pass = Do.encrypt(req.getParameter("pass"));
			
			otp = Do.generateOtp();
			userid = Do.generateUserId();
			
			runDB(user,email,phone,mysql);
			
			if(!mysql.duplicateEntry(phone,email,user)) {
				
				Do.setAttribute(email, user, pass, phone, Integer.toString(userid), req);
				Do.sendMail("otp",Integer.toString(otp),email,req,resp,"emailotp.jsp","signup.jsp");
				
			} else if(mysql.duplicateEntry(phone,email,user)) {
				System.out.println("Username or phone-number already registered");
				Do.showAlert(req,resp,"signup.jsp","duplicate");
			}
			
			//destroy();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("an error occured");
			mysql.log( e.getClass().getName(), e.getMessage()+Do.getMethodName(e.getStackTrace()));
			Do.showAlert(req,resp,"signup.jsp","error");
		}
	}


	private void runDB(String user, String email, String phone, Jdbc mysql) throws SQLException, ServletException {
		// TODO Auto-generated method stub
		
		if (mysql.tableExists()) {
			System.out.println("db already exists");
			if(!mysql.duplicateEntry(phone,email,user)) {
				mysql.setOtp(userid,otp);
			}
		}
		else if(!mysql.tableExists()) {
			System.out.println("db doesn't exists");
			mysql.createTable();
			mysql.setOtp(userid,otp);
		}
	}
	
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		mysql.jdbcDestroy();
	}
	
	
}
