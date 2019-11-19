

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;



/**
 * Servlet implementation class viewpreviousforcrm
 */
@WebServlet("/viewpreviousforcrm")
public class viewpreviousforcrm extends HttpServlet {


	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out=response.getWriter();
		
		String formdata=request.getParameter("x");
		JSONArray returnjson= new JSONArray();
		System.out.println(formdata);
		
		
	
		//String user=j.get("PatientName").toString();
			Connection con=ConnectionJDBC.DBconnect();
		
		try{
			
			PreparedStatement ps=con.prepareStatement("select * from mrlogs1 where crm='"+formdata+"'");
			ResultSet rs=ps.executeQuery();
			
			
			while(rs.next())
			{
		        System.out.println("enter");
		        	JSONObject addobj=new JSONObject();
		        	String date1= rs.getString("date1");
		        	String doctorname= rs.getString("DoctorName");
		        	
		        	String status= rs.getString("status");
		        	
		        	String timefrom= rs.getString("timefrom");
		        	String timeto= rs.getString("timeto");
		        
		        	
		        	
		        	
		        	
		        	addobj.put("date1",date1);
		        	addobj.put("doctorname",doctorname);
		        	addobj.put("status",status);
		        	addobj.put("timefrom",timefrom);
		        	addobj.put("timeto",timeto);
		        	//addobj.put("data", extractjson);
		        	returnjson.add(addobj);
		        	
		        	//addobj.put("Filepath", listOfFiles[i].getAbsoluteFile().toString());
		        	
		        
		      } 
		
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
			ConnectionJDBC.DBDisconnect(con);
		}
		
		    
		   out.print(returnjson.toJSONString());
	//	return Response.status(Status.OK).entity(returnjson.toJSONString()).build();
	//return "hello";
		
		
		
	}

}
