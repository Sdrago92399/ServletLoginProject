package com.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	
	Jdbc mysql = new Jdbc();
	Methods Do = new Methods();
	Mail sendMail = new Mail();
	
	PrintWriter out = null;
	ResultSet rst = null;
	
	String user;
	String pass;
	String userdb;
	String passdb;
	String maildb;
	String mail;
	String phone;
	
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		mysql.jdbcInit();
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// TODO Auto-generated method stub
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		System.out.println("requesting password");
		
		//sendMail.changePassword();
	}

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp){
		// TODO Auto-generated method stub
		try {
			
			resp.setContentType("text/html");
			out = resp.getWriter();
			user = req.getParameter("user");
			pass = req.getParameter("pass");
			
			runDB(user,pass,mail,phone,mysql);
			
			if(user.equals(userdb)|user.equals(maildb)&pass.equals(passdb)) {
				
				Cookie c = new Cookie("user", user);
	            resp.addCookie(c);
	            
	            Do.showAlert(req,resp,"temp.jsp","login");
				
			} else if(userdb == null) {
				System.out.println("Username doesnt exist");
				Do.showAlert(req,resp,"login.jsp","notExist");
			} else if((user.equals(userdb)|user.equals(maildb))&!(pass.equals(passdb))) {
				System.out.println("Login Denied");
				Do.showAlert(req,resp,"login.jsp","notLogin");
			}
			
			//destroy();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("an error occured");
			mysql.log(e.getClass().getName(), e.getMessage()+Do.getMethodName(e.getStackTrace()));
			Do.showAlert(req,resp,"login.jsp","error");
		}
	}



	private void runDB(String user, String pass, String mail, String phone, Jdbc mysql) throws SQLException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {
		// TODO Auto-generated method stub
		
		if (mysql.tableExists()) {
			System.out.println("db already exists");
			rst = mysql.fetchData(user);
			while(rst.next()) {
				userdb = rst.getString("user");
				passdb = Do.decrypt(rst.getString("pass"));
				maildb = rst.getString("mail");
			}
		}
		else {
			System.out.println("db doesn't exists");
			mysql.createTable();
		}
	}
	

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		mysql.jdbcDestroy();
	}
	
	
}
