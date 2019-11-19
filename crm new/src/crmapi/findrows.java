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




@Path("/findrows")
public class findrows {
	
	@POST

	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response test(String formdata) throws IOException, ParseException {
		
		JSONParser parser=new JSONParser();
		JSONObject j = null;
		JSONArray returnjson= new JSONArray();
		
		ResultSet rs=null;
		
		ResultSet rs1=null;
		
			System.out.println("clicked "+formdata );
			j=(JSONObject) parser.parse(formdata) ;
			
			String fromdate=j.get("fromdate").toString();
			String todate=j.get("todate").toString();
			
			System.out.println("thisbis thelogin user "+j.get("typeofuser").toString());
		
				Connection con=ConnectionJDBC.DBconnect();
				PreparedStatement ps=null;
			try{
			
				if(j.get("typeofuser").toString().contentEquals("crm")) {
				String query="select * from mrlogs where date1<='"+todate+"' and date1>='"+fromdate+"' and crm='"+j.get("loginuser").toString()+"' order by date1 desc";
				System.out.println(query);
				ps=con.prepareStatement(query);
				}
				else {
					
					String query="select * from mrlogs where date1<='"+todate+"' and date1>='"+fromdate+"' order by date1 desc";
					System.out.println(query);
					ps=con.prepareStatement(query);
				
				}
				
				//PreparedStatement ps=con.prepareStatement("insert into alignwise_cases values('"+j.get("serialnumber")+"',?,'No link','No link',?,'"+j.get("user")+"','"+j.get("remarks")+"')");
				rs=ps.executeQuery();
				while(rs.next()) {
					
					
					
					
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
					String crmname="";
						String query1="select * from crmlogin where username='"+crm+"'";
						PreparedStatement ps1=con.prepareStatement(query1); 
						System.out.println(query1);
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
		        	addobj.put("timefrom",rs.getString("timefrom"));
		        	addobj.put("timeto",rs.getString("timeto"));
		        	returnjson.add(addobj);
					
					
					
					
					
				}
			
			
				
			}
			catch(Exception e){
				e.printStackTrace();
			}
			finally {
				ConnectionJDBC.DBDisconnect(con);
			}
			
		
		
		
		
		// System.out.println(returnjson.toJSONString());
		return Response.status(Status.OK).entity(returnjson.toJSONString()).build();
	//return "hello";
	}


}
