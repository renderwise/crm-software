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




@Path("/getdoctorbytype")
public class getdoctorbytype {
	
	@POST

	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response test(String formdata) throws IOException, ParseException {
		
		JSONArray returnjson= new JSONArray();
		
		ResultSet rs=null;
		ResultSet rs1=null;
		Connection con=ConnectionJDBC.DBconnect();
		String type=formdata;
	
		try {
		String query="select doctor_id,doctorname,typename from doctorinfo where type='"+type+"'";
		PreparedStatement ps=con.prepareStatement(query); 
		System.out.println(query);
		rs=ps.executeQuery();
		
		while(rs.next()) {
			
			JSONObject addobj=new JSONObject();
        	//String doctorname1= rs.getString("name");
        		
        	String typename= rs.getString("typename");
        	String dname=rs.getString("doctorname").toString();
			String doctor_id=rs.getString("doctor_id");
        	// System.out.println(city+" fudhfuidhbi");
        	addobj.put("typename",typename);
        	addobj.put("dname",dname);
            addobj.put("doctorid",doctor_id);
        //	addobj.put("phone",phone);
        	
        	returnjson.add(addobj);
			
			
		}
		
		}
		catch(Exception e) {
			
			
		e.printStackTrace();
			
		}
		
		return Response.status(Status.OK).entity(returnjson.toJSONString()).build();
	//return "hello";
	}


}
