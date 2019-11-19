package crmapi;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
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




@Path("/converttocase")
public class converttocase {
	
	@POST

	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response test(String formdata) throws IOException, ParseException {
	
		System.out.println(formdata);
		
		JSONParser parser=new JSONParser();
		JSONObject j = null;
		JSONArray returnjson= new JSONArray();
	//	JSONObject j1=null;
		System.out.println("filter byy   "+formdata);
		j=(JSONObject) parser.parse(formdata) ;
		System.out.println(j.get("loginid").toString());
		Connection con=ConnectionJDBC.DBconnect();
		
		int enquiryid=Integer.parseInt(j.get("convertcaseid").toString());
		try {
			
			String query="update enquirytable set caseconverted='true' where enquiry_id='"+enquiryid+"'";
			System.out.println(query);
	    		
			PreparedStatement ps =con.prepareStatement(query);
			ps.executeUpdate();
			
		}
		catch(Exception e) {
			
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
