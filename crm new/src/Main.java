

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

/**
 * Servlet implementation class Main
 */
@WebServlet("/Main")
public class Main extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Date chosenDate = null;
		Connection con=null;
		
		PreparedStatement ps1=null;

		 //Date Date = request.getParameter("picker");
	    
	    String date = request.getParameter("availableDate");
	    String date1 = request.getParameter("availableDate1");
		  
	    System.out.println(date);
	    
	    //java.text.DateFormat df = new java.text.SimpleDateFormat("MM/dd/yyyy");
	    java.text.DateFormat format = new java.text.SimpleDateFormat("MM/dd/yyyy");
	   
	
		
	
        try{
        	
            Class.forName("com.mysql.jdbc.Driver");    
        	//Connection con=DriverManager.getConnection("jdbc:oracle:thin:@192.168.55.243:1521:orcl","uw_app","uw_app");  
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/datetest","root","root");
		    //	System.out.println(abc);
        	
        	
        	ps1=con.prepareStatement("SELECT * FROM test WHERE date1 >= '"+date+"' AND date1 <= '"+date1+"';");
        	ResultSet rs = ps1.executeQuery();
        	
        	while(rs.next()) {
        		
        		System.out.println(rs.getDate("date1"));
        		
        	}
    		System.out.println("hehe");
    		
        }
        catch(Exception e) {
        	
        	}
       }
       
        	
        	
        		
	        	
	        	
	        }
		
		
	


