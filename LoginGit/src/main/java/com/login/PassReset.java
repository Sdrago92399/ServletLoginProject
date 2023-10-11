package com.login;

import java.io.PrintWriter;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;  
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class PassReset extends HttpServlet {

	private static final long serialVersionUID = 5L;
	Jdbc mysql = new Jdbc();
	Methods Do = new Methods();
	Mail sendMail = new Mail();
	
	String alert;
	String email;
	String newpass;
	String chkuserid;
	String chkotp;
	int otp;
	int userid;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    
    @Override
	public void init() {
		// TODO Auto-generated method stub
		mysql.jdbcInit();
	}

    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        
    	try {
            
            response.setContentType("text/html");
    		PrintWriter out = response.getWriter();
    		
    		chkuserid = request.getParameter("userid");
    		newpass = Do.encrypt(request.getParameter("pass"));
    		email = request.getParameter("email");
    		
    		alert = runDBget(chkuserid,newpass,email);
    		Do.showAlert(request, response, "login.jsp", alert);
    		
        } catch (Exception e) {
        	e.printStackTrace();
			System.out.println("an error occured");
			mysql.log(e.getClass().getName(), e.getMessage()+Do.getMethodName(e.getStackTrace()));
			Do.showAlert(request,response,"login.jsp","error");
        }
        
    }

    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        
    	try {
            
            response.setContentType("text/html");
    		PrintWriter out = response.getWriter();
    		
    		email = request.getParameter("email");
    		userid=Do.generateUserId();
    		otp=0;
    		
    		runDBpost(userid,otp);
    		
    		Do.sendMail("pass",Integer.toString(userid),email,request,response,"login.jsp","passReset.jsp");
    		
        } catch (Exception e) {
        	e.printStackTrace();
			System.out.println("an error occured");
			mysql.log(e.getClass().getName(), e.getMessage()+Do.getMethodName(e.getStackTrace()));
			Do.showAlert(request,response,"login.jsp","error");
        }
        
    }
    
    
	private void runDBpost(int userid, int otp) throws SQLException {
		// TODO Auto-generated method stub
		
		if (mysql.tableExists()) {
			System.out.println("db already exists");
		}
		else {
			System.out.println("db doesn't exists");
			mysql.createTable();
		}
		mysql.setOtp(userid,otp);
	}
	
	
	private String runDBget(String chkuserid, String newpass, String email) throws SQLException {
		// TODO Auto-generated method stub
		
		String returnThis = null;
		
		if (mysql.tableExists()) {
			System.out.println("db already exists");
		}
		else if (!mysql.tableExists()) {
			System.out.println("db doesn't exists");
			mysql.createTable();
		}
		
		chkotp = mysql.getOtp(Integer.parseInt(chkuserid));
		
		if (chkotp.equals("0")) {
			mysql.changePassword(email, newpass);
			returnThis = "changed";
		}
		else if (chkotp.equals("expired")) {
			returnThis = "expiredLink   ";
		}
		else if (!chkotp.equals("expired") & !chkotp.equals("000000")) {
			returnThis = "invalid";
		}
		return returnThis;
		
	}
	

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		mysql.jdbcDestroy();
	}
	
	
}
