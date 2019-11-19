package crmapi;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

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




@Path("/getcitiesfromdoctor")
public class getcitiesfromdoctor {
	
	@POST

	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response test(String formdata) throws IOException, ParseException {
		
		JSONParser parser=new JSONParser();
		JSONObject j = null;
		JSONArray returnjson= new JSONArray();
		
		ResultSet rs=null;
		//ResultSet rs1=null;
		Connection con=ConnectionJDBC.DBconnect();
		
	
		try {
		String query="select city from doctorinfo where doctor_id='"+formdata+"' order by doctorname asc";
		PreparedStatement ps=con.prepareStatement(query); 
		System.out.println(query);
		rs=ps.executeQuery();
		String city="";
		while(rs.next()) {
			
			JSONObject addobj=new JSONObject();
        	
        	city= rs.getString("city");
        	  
			addobj.put("city",city);
        	
        	returnjson.add(addobj);
			
			
		}
		
		
		
		
		
		
		
		}
		catch(Exception e) {
			
			
			
			
		}
		
		System.out.println("edhfhdbnfudfh  "+returnjson.toJSONString());
		return Response.status(Status.OK).entity(returnjson.toJSONString()).build();
	//return "hello";
	}


}
