package com.login;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {
	
	static String subject ;
	static String to ;  
	static String from = Globals.Google_Mail;
	static String message;
	
	boolean otp(String valueToSend, String mail) {
		
		message = "<div style=\"font-family: Helvetica,Arial,sans-serif;min-width:1000px;overflow:auto;line-height:2\">\r\n"
				+ "  <div style=\"margin:50px auto;width:70%;padding:20px 0\">\r\n"
				+ "    <div style=\"border-bottom:1px solid #eee\">\r\n"
				+ "      <a href=\"\" style=\"font-size:1.4em;color: #00466a;text-decoration:none;font-weight:600\">Login Project</a>\r\n"
				+ "    </div>\r\n"
				+ "    <p style=\"font-size:1.1em\">Hi,</p>\r\n"
				+ "    <p>Use the following OTP to complete your Sign Up procedures. OTP is valid for 5 minutes</p>\r\n"
				+ "    <h2 style=\"background: #00466a;margin: 0 auto;width: max-content;padding: 0 10px;color: #fff;border-radius: 4px;\">"+valueToSend+"</h2>\r\n"
				+ "    <p style=\"font-size:0.9em;\">Regards</p>\r\n"
				+ "    <hr style=\"border:none;border-top:1px solid #eee\" />\r\n"
				+ "    <div style=\"float:right;padding:8px 0;color:#aaa;font-size:0.8em;line-height:1;font-weight:300\">\r\n"
				+ "      <p>Shahbaaz's Eduinq project</p>\r\n"
				+ "    </div>\r\n"
				+ "  </div>\r\n"
				+ "</div>";
		subject = "Otp verification";
		to = mail;
		
		System.out.println("preparing to send message ... to "+to+" and otp is "+valueToSend);
		return sendEmail();
	}
	

	boolean changePassword(String valueToSend, String mail) {
		// TODO Auto-generated method stub
		message = "<div style=\"font-family: Helvetica,Arial,sans-serif;min-width:1000px;overflow:auto;line-height:2\">\r\n"
				+ "  <div style=\"margin:50px auto;width:70%;padding:20px 0\">\r\n"
				+ "    <div style=\"border-bottom:1px solid #eee\">\r\n"
				+ "      <a href=\"\" style=\"font-size:1.4em;color: #00466a;text-decoration:none;font-weight:600\">Login Project</a>\r\n"
				+ "    </div>\r\n"
				+ "    <p style=\"font-size:1.1em\">Hi,</p>\r\n"
				+ "    <p>Visit the following link to change your password. Link is valid for 5 minutes</p>\r\n"
				+ "	   <a href=\"" + Globals.Website + "/Login/setNewPassword.jsp?email=" + mail + "&userid=" + valueToSend + "\">\r\n"
				+ "		 <h2 style=\"background: #00466a;margin: 0 auto;width: max-content;padding: 0 10px;color: #fff;border-radius: 4px;\">Click Me!</h2>\r\n"
				+ "	   </a>\r\n"
				+ "    <p style=\"font-size:0.9em;\">Regards</p>\r\n"
				+ "    <hr style=\"border:none;border-top:1px solid #eee\" />\r\n"
				+ "    <div style=\"float:right;padding:8px 0;color:#aaa;font-size:0.8em;line-height:1;font-weight:300\">\r\n"
				+ "      <p>Shahbaaz's Eduinq project</p>\r\n"
				+ "    </div>\r\n"
				+ "  </div>\r\n"
				+ "</div>";
		subject = "Otp verification";
		to = mail;
		
		System.out.println("preparing to send message ... to "+to);
		return sendEmail();
	}
	

	//this is responsible to send email..
	private static boolean sendEmail() {
		
		//Variable for gmail
		String host="smtp.gmail.com";
		
		//get the system properties
		Properties properties = System.getProperties();
		//System.out.println("PROPERTIES "+properties);
		
		//setting important information to properties object
		
		//host set
		
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port","465");
		properties.put("mail.smtp.ssl.enable","true");
		properties.put("mail.smtp.auth","true");
		
		//Step 1: to get the session object..
		Session session=Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {				
				return new PasswordAuthentication( Globals.Google_Mail, Globals.Google_Pass );
			}
		});
		
		//session.setDebug(true);
		
		//Step 2 : compose the message [text,multi media]
		MimeMessage m = new MimeMessage(session);
		
		try {
		
		//from email
		m.setFrom(from);
		
		//adding recipient to message
		m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		
		//adding subject to message
		m.setSubject(subject);
	
		
		//adding text to message
		m.setContent(message,"text/html");
		
		//send 
		
		//Step 3 : send the message using Transport class
		Transport.send(m);
		
		System.out.println("Sent success...................");
		
		return true;
		
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
			
	}

}
