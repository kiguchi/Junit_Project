package testParts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public   class FactoryDs {
	     public static  Connection getConnection(){
	    	DataSource ds = null;
	    	InitialContext context = null;
	    	Connection con = null;
	    	try {
				context = new InitialContext();
			} catch (NamingException e) {
				// TODO �����������ꂽ catch �u���b�N
				e.printStackTrace();
			}
	    	try {
				ds = (DataSource)context.lookup("java:comp/env/jdbc/Task");
			} catch (NamingException e) {
				e.printStackTrace();
			} 
	    	try {
				con =ds.getConnection();
			} catch (SQLException e) {
				// TODO �����������ꂽ catch �u���b�N
				e.printStackTrace();
			}
	
			return con;
			
	    	 
	     }
}
