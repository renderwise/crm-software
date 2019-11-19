

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

public class ConnectionJDBC {


	public static Connection DBconnect()
	 {
		Connection con=null;
	     try
	     { 
	    	
	    		/*Class.forName ("oracle.jdbc.driver.OracleDriver");
	        	String url = "jdbc:oracle:thin:@//192.168.55.243:1521/orcl";
		    	 con = DriverManager.getConnection(url, "janashakthi", "janashakthi");
		    	 */
	    	 	Class.forName("com.mysql.jdbc.Driver");  

		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/crmsoftware","root","toor"); 
		    	/* 
		    	 Class.forName("com.mysql.jdbc.Driver");  
		    	 con=DriverManager.getConnection("jdbc:mysql://localhost:3306/alignwise","root","");  
		    	
		    	 
		    	*/
		
			 System.out.println("Connection Successful");
		  	
	     }
	  	 catch(Exception e)
	  	 {  
	  		System.out.println("Error in DB Connection \n"+e);
	   	 }
			 
	     return con;
	 }
	
	public static void DBDisconnect(Connection con)
	 {
	     try
	     { 
			
	    	 con.close();
	    	 System.out.println("disconnected Successful");
	     }
	  	 catch(Exception e)
	  	 {  
	  		System.out.println("Error in DB Connection \n"+e);
	   	 }

	 }
	
	public static void main(String[] args) {
		Connection con=ConnectionJDBC.DBconnect();
		ConnectionJDBC.DBDisconnect(con);
	}
	
}
