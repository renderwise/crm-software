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




@Path("/getcrm")
public class getcrm {
	
	@POST

	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response test(String formdata) throws IOException, ParseException {
		
		JSONParser parser=new JSONParser();
		JSONObject j = null;
		JSONArray returnjson= new JSONArray();
		
		ResultSet rs=null;
		ResultSet rs1=null;
		Connection con=ConnectionJDBC.DBconnect();
		j=(JSONObject) parser.parse(formdata) ;
		
	
		try {
		String query="select * from login where typeofuser='crm'";
		PreparedStatement ps=con.prepareStatement(query); 
		System.out.println(query);
		rs=ps.executeQuery();
		
		while(rs.next()) {
			
			JSONObject addobj=new JSONObject();
        	//String doctorname1= rs.getString("name");
        		
        	String crmname= rs.getString("username");
        	String phone= rs.getString("phone");
        	String id=rs.getString("id");
			 // System.out.println(city+" fudhfuidhbi");
        	addobj.put("crmname",crmname);
        	addobj.put("phone",phone);
        	addobj.put("id",id);
        	
        	returnjson.add(addobj);
			
			
		}
		
		
		
		
		
		
		
		
		}
		catch(Exception e) {
			
			
			
			
		}
		
		return Response.status(Status.OK).entity(returnjson.toJSONString()).build();
	//return "hello";
	}


}
