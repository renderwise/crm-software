package crmapi;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;




@Path("/test")
public class apitest {
	
	@POST

	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response test(String username) throws IOException, ParseException {
		String typeofuser=username;
		//PrintWriter out = response.getWriter();
		System.out.println("dsbjfnsfhbsj   "+typeofuser+"fgvjh");
		//String typeofuser=null;
	
		
		JSONArray returnjson= new JSONArray();
		
		
		
Connection con=ConnectionJDBC.DBconnect();
ResultSet rs=null;
ResultSet rs1=null;
		try{
			
			if(username.contentEquals("crm")) {
			String query="select * from mrlogs where crm='"+typeofuser+"' order by mrlogdate desc";
			PreparedStatement ps=con.prepareStatement(query); 
			System.out.println(query);
			rs=ps.executeQuery();
			}
			else {
				
				String query="select * from mrlogs order by date1 desc";
				PreparedStatement ps=con.prepareStatement(query); 
				System.out.println(query);
				rs=ps.executeQuery();
				
				
			}
			while(rs.next())
			{
		        
		        	JSONObject addobj=new JSONObject();
		        	String date1= rs.getString("date1");
		        	String doctorname= rs.getString("DoctorName");
		        	String status= rs.getString("status");
		        	String discussion= rs.getString("Discussion");
		        	String ordervalue= rs.getString("ordervalue");
		        	String orderquantity= rs.getString("orderquantity");
		        	String orderbooked= rs.getString("orderbooked");
		        	String expecteddate= rs.getString("expecteddate");
		        	String actualdate= rs.getString("actualdate");
					String remarks=rs.getString("remarks");
					String mrlogdate=rs.getString("mrlogdate");
					String crm=rs.getString("crm");
					String city=rs.getString("city");
					String mrlogid=rs.getString("id");
					String noofchairs=rs.getString("noofchairs").toString();
					String type=rs.getString("type");
					String crmname="";
						String query="select * from crmlogin where username='"+crm+"'";
						PreparedStatement ps1=con.prepareStatement(query); 
						System.out.println(query);
						rs1=ps1.executeQuery();
						if(rs1.next()) {
							
							crmname=rs1.getString("name");
						}
					  
					  
		        	addobj.put("crmname",crmname);
		        	addobj.put("date1",date1);
		        	addobj.put("doctorname",doctorname);
		        	addobj.put("status",status);
		        	addobj.put("discussion",discussion);
		        	addobj.put("ordervalue",ordervalue);
		        	addobj.put("orderquantity",orderquantity);
		        	addobj.put("orderbooked",orderbooked);
		        	addobj.put("expecteddate",expecteddate);
		        	addobj.put("actualdate",actualdate);
		        	addobj.put("remarks",remarks);
		        	addobj.put("mrlogdate",mrlogdate);
		        	addobj.put("noofchairs",noofchairs);
		        	addobj.put("type",type);
		        	addobj.put("timefrom",rs.getString("timefrom"));
		        	addobj.put("timeto",rs.getString("timeto"));
		        	addobj.put("city",city);
		        	addobj.put("mrlogid",mrlogid);
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
		
		
		
		 System.out.println(returnjson.toJSONString());
		return Response.status(Status.OK).entity(returnjson.toJSONString()).build();
	//return "hello";
	}


}
