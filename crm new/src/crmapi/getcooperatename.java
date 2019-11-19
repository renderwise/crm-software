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




@Path("/getcooperatename")
public class getcooperatename {
	
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
		String query="select * from corporates";
		PreparedStatement ps=con.prepareStatement(query); 
		System.out.println(query);
		rs=ps.executeQuery();
		
		while(rs.next()) {
			
			JSONObject addobj=new JSONObject();
        	//String doctorname1= rs.getString("name");
        		
        	String cooperatename= rs.getString("name");
        	String city=rs.getString("city");
			 // System.out.println(city+" fudhfuidhbi");
        	addobj.put("cooperatename",cooperatename);
        	addobj.put("city",city);
        	
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
