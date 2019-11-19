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




@Path("/getallenquiry")
public class getallenquiry {
	
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
	int crm_id=Integer.parseInt(j.get("loginid").toString());
		Connection con=ConnectionJDBC.DBconnect();
		ResultSet rs=null;
		try {
			
			
			String query1="select * from doctorinfo";
	    	PreparedStatement ps1=con.prepareStatement(query1);
	    	HashMap<String,String> hs= new HashMap<String,String>();
	    	HashMap<String,String> hs1= new HashMap<String,String>();
	    	HashMap<String,String> hs2= new HashMap<String,String>();
	    	ResultSet rs1=ps1.executeQuery();
	    	
	    	while(rs1.next()) {
	    		
	    		hs.put(rs1.getString("doctor_id"),rs1.getString("clinicname"));
	    		hs1.put(rs1.getString("doctor_id"),rs1.getString("type"));
	    		hs2.put(rs1.getString("doctor_id"),rs1.getString("doctorname"));
	    		
	    	}
	    	
			
			String query="Select * from enquirytable where crm_id='"+crm_id+"' and caseconverted='false' order by enquirydate desc";
	    	
			System.out.println(query);
			PreparedStatement ps=con.prepareStatement(query);
	    	rs=ps.executeQuery();
	    	while(rs.next()) {
	    		
	    		JSONObject addobj=new JSONObject();
	    		addobj.put("enquiryid",rs.getString("enquiry_id"));
	    		addobj.put("doctorname",hs2.get(rs.getString("doctor_id")));
	    		addobj.put("enquirydate", rs.getString("enquirydate"));
	    		addobj.put("enquirydetails",rs.getString("enquirydetails"));
	    		
	    		
	    		System.out.println(hs1.get(rs.getString("doctor_id")));
	    		addobj.put("clinicname",hs.get(rs.getString("doctor_id")));
	    		addobj.put("type",hs1.get(rs.getString("doctor_id")));
	    		addobj.put("enquirydetails",rs.getString("enquirydetails"));
	    		
	    		
	    		System.out.println("this is the enquiry : "+rs.getString("enquirydetails"));
	    		
	    		
	    		returnjson.add(addobj);
	    	}
	    	
	    	
	    
	    	
	    	
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
