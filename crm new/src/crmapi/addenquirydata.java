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




@Path("/addenquirydata")
public class addenquirydata {
	
	@POST

	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response test(String formdata) throws IOException, ParseException {
		
		//PrintWriter out = response.getWriter();
	
		JSONParser parser=new JSONParser();
		JSONObject j = null;
		JSONArray returnjson= new JSONArray();
		
		try {
			
			System.out.println("clicked "+formdata );
			j=(JSONObject) parser.parse(formdata) ;
			
		//	String doctorname=j.get("adddoctorname").toString();
		String enquirydetails=j.get("enquirydetails").toString();
		int loginid=Integer.parseInt(j.get("loginid").toString());
		int doctorid=Integer.parseInt(j.get("edoctorid").toString());
		
			Connection con=ConnectionJDBC.DBconnect();
			ResultSet rs;
			try{
			
				
			String query="insert into enquirytable(doctor_id,crm_id,enquirydetails,caseconverted,enquirydate) values('"+doctorid+"','"+loginid+"','"+enquirydetails+"','false',sysdate())";
			System.out.println("query  "+query);
				PreparedStatement ps=con.prepareStatement(query);
				
				ps.execute();
				
						
		//	ps.execute();
				
			}
			catch(Exception e){
				e.printStackTrace();
			}
			finally {
				ConnectionJDBC.DBDisconnect(con);
			}
			
		
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		returnjson.add("fhdf");
		
		
		
		// System.out.println(returnjson.toJSONString());
		return Response.status(Status.OK).entity(returnjson.toJSONString()).build();
	//return "hello";
	}


}
