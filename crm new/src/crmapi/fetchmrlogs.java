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




@Path("/fetchmrlogs")
public class fetchmrlogs {
	
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
		
	//System.out.println("filter byy   "+filterby);
		ResultSet rs=null;
		Connection con=ConnectionJDBC.DBconnect();
		
	// str1="noofchairs";
		String condition="";
		
		
		j=(JSONObject) parser.parse(formdata) ;
		
	//	String arr= j.get("arraysearch").toString();
		
	//	System.out.println("first   :"+arr);
		
		String crmname=j.get("crmname").toString();
		String fromdate=j.get("fromdate").toString();
		String todate=j.get("todate").toString();
		String citysearch=j.get("cityforsearch").toString();
		String doctorname=j.get("doctornameforsearch1").toString();
		//String checktype=j.get("checklogsselect1").toString();
		
		
		 ArrayList<String> arrlist = new ArrayList<String>(); 
		 ArrayList<String> arrlist1 = new ArrayList<String>(); 
			  
		 HashMap<String, String> array = new HashMap<String, String>();
		 HashMap<String, String> array1 = new HashMap<String, String>();
			
		 String query="select * from mrlogs1";
		 if(!crmname.contentEquals("")) 
			 	
		 		{
			 		
						array.put("crm=",crmname);
						
						arrlist.add("crm=");
						
				}
		 if(!fromdate.contentEquals("")) 
			 	
	 		{
		 		
					array.put("mrlogdate>=",fromdate);
					
					arrlist.add("mrlogdate>=");
				
			}
		 if(!todate.contentEquals("")) 
			 	
	 		{
		 		
					array.put("mrlogdate<=",todate);
					
					arrlist.add("mrlogdate<=");
			}
		 if(!citysearch.contentEquals("")) 
			 	
	 		{
		 		
					array.put("city=",citysearch);
					
					arrlist.add("city=");
				
			}
		
		 if(!doctorname.contentEquals("")) 
			 	
	 		{
		 		
					array.put("doctorname=",doctorname);
					
					arrlist.add("doctorname=");

					
					
			}
		 
		 
		
		 for(int i=0;i<arrlist.size();i++) 
		{
			 if(i==0) {
					query=query+" where ";
				}
			query=query+" "+arrlist.get(i)+"'"+array.get(arrlist.get(i))+"'"+" ";
			if(i!=arrlist.size()-1) {
				
				query=query+"and ";
			}
		
		}
	
		 query=query+" order by mrlogdate desc";
		 
		 System.out.println(query);
		
		
		 
		 
		 
		 try {
			 
			PreparedStatement ps=con.prepareStatement(query);
			rs=ps.executeQuery();
			while(rs.next()) {
				
				System.out.println("first");
				JSONObject addobj=new JSONObject();
				addobj.put("crm",rs.getString("crm"));
				addobj.put("date1",rs.getString("mrlogdate"));
				addobj.put("timefrom",rs.getString("timefrom"));
				addobj.put("timeto",rs.getString("timeto"));
				addobj.put("DoctorName",rs.getString("DoctorName"));
				addobj.put("Status",rs.getString("status"));
				addobj.put("discussion",rs.getString("discussion"));
				addobj.put("orderbooked",rs.getString("orderbooked"));
				addobj.put("expecteddate",rs.getString("expecteddate"));
				addobj.put("actualdate",rs.getString("actualdate"));
				addobj.put("remarks",rs.getString("remarks"));
				addobj.put("city",rs.getString("city"));
				addobj.put("actualvisitdate",rs.getString("date1"));
				addobj.put("address",rs.getString("address"));
				addobj.put("contact",rs.getString("contact"));
				String a=rs.getString("address");
				

				returnjson.add(addobj);
			}
			 
			 
		 }
		catch(Exception e) {
			
			
			System.out.println(e);
			
			
		}
		 finally {
			 
			 
			 
		 }
		System.out.println(returnjson.toJSONString());
			
		
		return Response.status(Status.OK).entity(returnjson.toJSONString()).build();
	//return "hello";
	}


}
