package com.login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;
import java.sql.Timestamp;

public class Jdbc {
	
	Methods Do = new Methods();
	
	private ResultSet resultset = null;
	private int tableResponse, functionResponse, triggerResponse, otpResponse, backupResponse, logResponse;
	private PreparedStatement insertStatement = null, selectStatement = null, deleteStatement = null, updateStatement = null;
	
	Connection connection = null;
	Statement statement = null;
	
	final String dburl = Globals.MySQL_Address;
	final String dbuser = Globals.MySQL_User;
	final String dbpass = Globals.MySQL_Pass;
	
	private String table, function, trigger, otp, backup, log, insertQuery, selectQuery, deleteQuery, updateQuery, error = "";
	
	
	void jdbcInit() {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection=DriverManager.getConnection(dburl,dbuser,dbpass);
			statement=connection.createStatement();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log(e.getClass().getName(), e.getMessage()+Do.getMethodName(e.getStackTrace()));
		}
	}
	
	
	boolean tableExists() throws SQLException {
		// TODO Auto-generated method stub
		
		table = "SELECT COUNT(*) AS table_count \r\n"
				+ "FROM information_schema.tables \r\n"
				+ "WHERE \r\n"
				+ "	table_schema = 'test' \r\n"
				+ "	AND table_name = 'db'";
		function = "Select count(*) as routine_count\r\n"
				+ "from information_schema.routines \r\n"
				+ "where \r\n"
				+ "	routine_schema=\"test\" \r\n"
				+ "	and routine_name=\"increment_base36\"\r\n";
		trigger = "Select count(*) as trigger_name\r\n"
				+ "from information_schema.triggers\r\n"
				+ "where \r\n"
				+ "	trigger_schema=\"test\" \r\n"
				+ "	and trigger_name=\"tr_db_insert\"";
		otp = "SELECT COUNT(*) AS table_count \r\n"
				+ "FROM information_schema.tables \r\n"
				+ "WHERE \r\n"
				+ "	table_schema = 'test' \r\n"
				+ "	AND table_name = 'otp_table'";
		backup = "SELECT COUNT(*) AS table_count \r\n"
				+ "FROM information_schema.tables \r\n"
				+ "WHERE \r\n"
				+ "	table_schema = 'test' \r\n"
				+ "	AND table_name = 'db_backup'";
		log = "SELECT COUNT(*) AS table_count \r\n"
				+ "FROM information_schema.tables \r\n"
				+ "WHERE \r\n"
				+ "	table_schema = 'test' \r\n"
				+ "	AND table_name = 'log'\r\n";

	    resultset = statement.executeQuery(table);
	    resultset.next();
	    tableResponse=resultset.getInt(1);
	    
	    resultset = statement.executeQuery(function);
	    resultset.next();
	    functionResponse=resultset.getInt(1);
	    
	    resultset = statement.executeQuery(trigger);
	    resultset.next();
	    triggerResponse=resultset.getInt(1);
	    
	    resultset = statement.executeQuery(otp);
	    resultset.next();
	    otpResponse=resultset.getInt(1);
	    
	    resultset = statement.executeQuery(backup);
	    resultset.next();
	    backupResponse=resultset.getInt(1);
	    
	    resultset = statement.executeQuery(log);
	    resultset.next();
	    logResponse=resultset.getInt(1);
	    
	    return (tableResponse != 0 & functionResponse != 0 & triggerResponse != 0 & otpResponse != 0 & backupResponse != 0 & logResponse != 0);
	}


	void delTable() throws SQLException {
		// TODO Auto-generated method stub
		//create a query
		table="mysqldump test db > db.sql";
		function="drop function increment_base36";
		trigger="drop trigger tr_db_insert";
		otp="drop table otp_table";
		backup="drop table db_backup";
		log="drop table log";
		
		//create a statement
		statement.addBatch(table);
		statement.addBatch(function);
		statement.addBatch(trigger);
		statement.addBatch(otp);
//		statement.addBatch(backup);
//		statement.addBatch(log);
		
		statement.executeBatch();
		
		System.out.println("table deleted in database...");
		
		statement.clearBatch();
	}


	void addData(String user, String pass, String mail, String phone) throws SQLException {
		// TODO Auto-generated method stub
		insertQuery ="insert into db(user,pass,mail,phone) values (?,?,?,?)";
		insertStatement=connection.prepareStatement(insertQuery);
		insertStatement.setString(1, user);
		insertStatement.setString(2, pass);
		insertStatement.setString(3, mail);
		insertStatement.setString(4, phone);
		insertStatement.executeUpdate();
		System.out.println("added succesfully");
	}
	
	
	
	void createTable() throws SQLException {
		// TODO Auto-generated method stub
		//create a query
		table="CREATE TABLE db (\r\n"
				+ "    id varchar(10) NOT NULL PRIMARY KEY,\r\n"
				+ "    user varchar(200) NOT NULL, \r\n"
				+ "	pass varchar(200) NOT NULL, \r\n"
				+ "	mail varchar(200) NOT NULL UNIQUE, \r\n"
				+ "	phone varchar(10) NOT NULL UNIQUE\r\n"
				+ ")";
		
		function="CREATE FUNCTION increment_base36 (input VARCHAR(10))\r\n"
				+ "RETURNS VARCHAR(10)\r\n"
				+ "DETERMINISTIC\r\n"
				+ "BEGIN\r\n"
				+ "    DECLARE output VARCHAR(10);\r\n"
				+ "    SET output = CONV(CONV(input, 36, 10) + 1, 10, 36);\r\n"
				+ "    RETURN output;\r\n"
				+ "END\r\n";
		
		trigger="CREATE TRIGGER tr_db_insert BEFORE INSERT ON db\r\n"
				+ "FOR EACH ROW\r\n"
				+ "BEGIN\r\n"
				+ "    DECLARE max_id VARCHAR(10);\r\n"
				+ "    SELECT MAX(id) INTO max_id FROM db;\r\n"
				+ "    SET NEW.id = increment_base36(IFNULL(max_id, '0'));\r\n"
				+ "END\r\n";
		
		otp="CREATE TABLE otp_table (\r\n"
				+ "    user_id INT,\r\n"
				+ "    otp INT(6) NOT NULL,\r\n"
				+ "    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,\r\n"
				+ "    PRIMARY KEY (user_id, otp)\r\n"
				+ ")";
		backup="CREATE TABLE db_backup (\r\n"
				+ "    time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,\r\n"
				+ "	log varchar(200),\r\n"
				+ "	id varchar(10),\r\n"
				+ "    user varchar(200), \r\n"
				+ "	pass varchar(200), \r\n"
				+ "	mail varchar(200), \r\n"
				+ "	phone varchar(10),\r\n"
				+ "	PRIMARY KEY (time, log, id)\r\n"
				+ ")";
		log="CREATE TABLE log (\r\n"
				+ "    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,\r\n"
				+ "    logid varchar(200) NOT NULL,\r\n"
				+ "    err varchar(1000) NOT NULL,\r\n"
				+ "    PRIMARY KEY (created_at, logid)\r\n"
				+ ")";
		
		try {
			tableResponse = statement.executeUpdate(table);
			functionResponse = statement.executeUpdate(function);
			triggerResponse = statement.executeUpdate(trigger);
			otpResponse = statement.executeUpdate(otp);
			backupResponse = statement.executeUpdate(backup);
			logResponse = statement.executeUpdate(log);
		} catch(SQLSyntaxErrorException e) {
			error = e.getMessage();
			System.out.println(error);
			if(error.equals("Table 'db' already exists")) {
				try {
					functionResponse = statement.executeUpdate(function);
					triggerResponse = statement.executeUpdate(trigger);
					otpResponse = statement.executeUpdate(otp);
					backupResponse = statement.executeUpdate(backup);
					logResponse = statement.executeUpdate(log);
				} catch(SQLSyntaxErrorException e1) {
					error = e1.getMessage();
					System.out.println(error);
					if(error.equals("FUNCTION increment_base36 already exists")) {
						try {
							triggerResponse = statement.executeUpdate(trigger);
							otpResponse = statement.executeUpdate(otp);
							backupResponse = statement.executeUpdate(backup);
							logResponse = statement.executeUpdate(log);
						} catch(SQLException e2) {
							error = e2.getMessage();
							System.out.println(error);
							if(error.equals("Trigger already exists")) {
								try {
									otpResponse = statement.executeUpdate(otp);
									backupResponse = statement.executeUpdate(backup);
									logResponse = statement.executeUpdate(log);
								} catch(SQLSyntaxErrorException e3) {
									error = e3.getMessage();
									System.out.println(error);
									if(error.equals("Table 'otp_table' already exists")) {
										try {
											backupResponse = statement.executeUpdate(backup);
											logResponse = statement.executeUpdate(log);
										} catch(SQLSyntaxErrorException e4) {
											error = e4.getMessage();
											System.out.println(error);
											if(error.equals("Table 'db_backup' already exists")) {
												logResponse = statement.executeUpdate(log);
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		if(tableResponse==0 & functionResponse==0 & triggerResponse==0 & otpResponse==0 & backupResponse==0 & logResponse==0) {
			System.out.println("database initialized successfully...");
		}
	}


	ResultSet fetchData(String user) throws SQLException {
		// TODO Auto-generated method stub
		selectQuery="select user, pass, mail from db where user = ? or mail = ?";
		selectStatement=connection.prepareStatement(selectQuery);
		selectStatement.setString(1, user);
		selectStatement.setString(2, user);
		return selectStatement.executeQuery();
	}


	void setOtp(int userid, int otp) throws SQLException {
		// TODO Auto-generated method stub
		insertQuery ="insert into otp_table(user_id,otp) values (?,?)";
		insertStatement=connection.prepareStatement(insertQuery);
		insertStatement.setInt(1, userid);
		insertStatement.setInt(2, otp);
		insertStatement.executeUpdate();
		System.out.println("otp generated succesfully");
	}


	String getOtp(int userid) {
		// TODO Auto-generated method stub
		selectQuery = "SELECT otp, created_at FROM otp_table WHERE user_id = ?";
        deleteQuery = "DELETE FROM otp_table WHERE user_id = ?";
        String otp = null;
        
    	try {
			selectStatement = connection.prepareStatement(selectQuery);
			deleteStatement = connection.prepareStatement(deleteQuery);
	        selectStatement.setInt(1, userid);

	        resultset = selectStatement.executeQuery();
            if (resultset.next()) {
                otp = Integer.toString(resultset.getInt("otp"));
                Timestamp createdAt = resultset.getTimestamp("created_at");

                // Calculate the difference between the current time and the OTP creation time
                long currentTimeMillis = System.currentTimeMillis();
                long otpTimeMillis = createdAt.getTime();
                long timeDifferenceMinutes = (currentTimeMillis - otpTimeMillis) / (1000 * 60);

                // Check if the OTP is valid (created within the last 5 minutes)
                if (timeDifferenceMinutes <= 5) {
                    System.out.println("OTP for user_id " + userid + ": " + otp);
                    // Continue with further processing using the OTP as needed
                } else if (!(timeDifferenceMinutes <= 5)) {
                    // If OTP is not valid, delete the entry from the table
                    deleteStatement.setInt(1, userid);
                    deleteStatement.executeUpdate();
                    System.out.println("OTP for user_id " + userid + " has expired and has been deleted.");
                    otp="expired";
                }
            } else if (!resultset.next()) {
                System.out.println("No OTP found for user_id " + userid);
            }
            return otp;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
        
    }


	void backup(String log) throws SQLException {
		// TODO Auto-generated method stub
		insertQuery = "INSERT INTO db_backup (log, id, user, pass, mail, phone) " +
		                	 "SELECT ?, id, user, pass, mail, phone FROM db";
		insertStatement = connection.prepareStatement(insertQuery);
		insertStatement.setString(1, log);
		
		insertStatement.executeUpdate();
		System.out.println("Backup created");
	}


	void log(String log, String err) {
		// TODO Auto-generated method stub
		insertQuery = "INSERT INTO log (logid, err) values (?,?)";
				try {
					insertStatement = connection.prepareStatement(insertQuery);

					insertStatement.setString(1, log);
					insertStatement.setString(2, err);
					
					insertStatement.executeUpdate();
					System.out.println("log generated");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("Coudn't log");
				}
	}


	boolean duplicateEntry(String phone, String email, String user) throws SQLException {
		// TODO Auto-generated method stub
		selectQuery = "SELECT mail, phone FROM db WHERE mail = ? OR phone = ? OR user = ?";
		selectStatement = connection.prepareStatement(selectQuery);

        selectStatement.setString(1, email);
        selectStatement.setString(2, phone);
        selectStatement.setString(3, user);
        
        resultset = selectStatement.executeQuery();
        
        boolean hasNext = resultset.next();
        if(hasNext) {
        	System.out.println("Duplicate entry warning");
        }
		return hasNext;
	}


	void jdbcDestroy() {
		// TODO Auto-generated method stub
		try {
            statement.close();
			connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            log(e.getClass().getName(), e.getMessage()+Do.getMethodName(e.getStackTrace()));
        }
	}


	public void changePassword(String email, String newpass) throws SQLException {
		// TODO Auto-generated method stub
		updateQuery = "UPDATE db\r\n"
				+ "SET pass = ?\r\n"
				+ "WHERE\r\n"
				+ "	mail = ?";
		
		updateStatement = connection.prepareStatement(updateQuery);

		updateStatement.setString(1, newpass);
		updateStatement.setString(2, email);
		
		updateStatement.executeUpdate();
		System.out.println("password updated to : " + newpass);
	}


}
