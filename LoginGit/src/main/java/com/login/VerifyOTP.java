package com.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VerifyOTP extends HttpServlet {

	private static final long serialVersionUID = 3L;
	
	Jdbc mysql = new Jdbc();
	Methods Do = new Methods();
	Mail sendMail = new Mail();
	
	PrintWriter out = null;
	
	private int otp;
	
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		mysql.jdbcInit();
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
			resp.setContentType("text/html");
			out = resp.getWriter();
			
			String email = req.getParameter("email");
			String user = req.getParameter("user");
			String pass = req.getParameter("pass");
			String phone = req.getParameter("phone");
			String userid = req.getParameter("userid");
			
			//System.out.println(new Signup().email+email+pass+req.getAttribute("user")+userid);

			otp=Do.generateOtp();
			mysql.setOtp(Integer.parseInt(userid),otp);
			sendMail.otp(Integer.toString(otp),email);
			
			Do.setAttribute(email,user,pass,phone,userid,req);
			
			RequestDispatcher rd = req.getRequestDispatcher("emailOTP.jsp");
			rd.forward(req, resp);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("an error occured");
			mysql.log(e.getClass().getName(), e.getMessage()+Do.getMethodName(e.getStackTrace()));
			Do.showAlert(req,resp,"emailOTP.jsp","error");
		}
	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		try{
			resp.setContentType("text/html");
			out = resp.getWriter();
			
			String email = req.getParameter("email");
			String user = req.getParameter("user");
			String pass = req.getParameter("pass");
			String phone = req.getParameter("phone");
			String userid = req.getParameter("userid");
			String serverotp = mysql.getOtp(Integer.parseInt(userid));
			String otpInput = req.getParameter("first") 
					+ req.getParameter("second")
					+ req.getParameter("third")
					+ req.getParameter("fourth")
					+ req.getParameter("fifth")
					+ req.getParameter("sixth");
			
			if(otpInput.equals(serverotp)) {
				runDB(user,pass,email,phone,mysql);
				System.out.println("Registration success");
				out.println("Registration success");
				
//				new Signup().showAlert(req,resp,"signup.jsp","success");
				
			} else if(otpInput.equals("expired")) {
				
				System.out.println("Otp expired");
				
				Do.setAttribute(email,user,pass,phone,userid,req);
				Do.showAlert(req,resp,"emailOTP.jsp","expired");
				
			} else if(!(otpInput.equals(serverotp))) {
				
				System.out.println("Wrong Otp. Try again");
				
				Do.setAttribute(email,user,pass,phone,userid,req);
				Do.showAlert(req,resp,"emailOTP.jsp","notOtp");
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("an error occured");
			
			mysql.log(e.getClass().getName(), e.getMessage()+Do.getMethodName(e.getStackTrace()));
			Do.showAlert(req,resp,"emailOTP.jsp","error");
		
		}
	}


	private void runDB(String user, String pass, String mail, String phone, Jdbc mysql) throws ServletException, SQLException {
		// TODO Auto-generated method stub
		
		try {
			if (mysql.tableExists()) {
				System.out.println("db already exists");
				mysql.addData(user,pass,mail,phone);
			}
			else if(!mysql.tableExists()) {
				System.out.println("db doesn't exists");
				mysql.createTable();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mysql.backup(Integer.toString(e.getErrorCode()));
			mysql.log(Integer.toString(e.getErrorCode()),e.getMessage());
			mysql.delTable();
			mysql.createTable();
			mysql.addData(user,pass,mail,phone);
		}
	}
	
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		mysql.jdbcDestroy();
	}
	
}
