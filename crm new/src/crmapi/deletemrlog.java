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




@Path("/deletemrlog")
public class deletemrlog {
	
	@POST

	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response test(String id) throws IOException, ParseException {


		JSONArray returnjson= new JSONArray();
		
		
		
Connection con=ConnectionJDBC.DBconnect();
ResultSet rs=null;
ResultSet rs1=null;
PreparedStatement ps=null;
try {
			String query="delete from mrlogs where id='"+id+"'";
			System.out.println(query);
			ps=con.prepareStatement(query);
			ps.executeUpdate();
			
}
catch(Exception e) {
	
}
		
		
		 System.out.println(returnjson.toJSONString());
		return Response.status(Status.OK).entity(returnjson.toJSONString()).build();
	//return "hello";
	}


}
