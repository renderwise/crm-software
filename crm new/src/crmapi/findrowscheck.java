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




@Path("/findrowscheck")
public class findrowscheck {
	
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
		ResultSet rs1=null;
		ResultSet rs3=null;
		j=(JSONObject) parser.parse(formdata) ;
		
		ArrayList<String> searchby1 = new ArrayList<String>();     
		JSONArray jsonArray = (JSONArray)j.get("searchby1");; 
		if (jsonArray != null) { 
		   int len = jsonArray.size();
		   for (int i=0;i<len;i++){ 
			   searchby1.add(jsonArray.get(i).toString());
		   } 
		} 
		ArrayList<String> searchby2 = new ArrayList<String>();     
	 jsonArray = (JSONArray)j.get("searchby2");; 
		if (jsonArray != null) { 
		   int len = jsonArray.size();
		   for (int i=0;i<len;i++){ 
			   searchby2.add(jsonArray.get(i).toString());
		   } 
		} 
		
		String querymain=j.get("query").toString();
		System.out.println("query main  "+querymain);
		ArrayList<ArrayList> finallist=new ArrayList<ArrayList>();
		finallist.add(searchby1);
		finallist.add(searchby2);
	//	finallist.add(e)
		System.out.println("dhjfnfgvj   "+searchby1.get(0)  +"  fdj  "+((ArrayList<String>)finallist.get(0)).get(0));
		
		String fromdate=j.get("fromdate").toString();
		String todate=j.get("todate").toString();
		String crmname=j.get("crmname").toString();
		String doctorname=j.get("doctorname").toString();
		String citysearch=j.get("city").toString();
		
		String searchbytype=j.get("searchbytype").toString();
	//	System.out.println("no of chairs  "+noofchairs);
	    ArrayList<String> arrlist = new ArrayList<String>(); 
	    
	    HashMap<String, String> array = new HashMap<String, String>();
	    //array.pu
		
		if(!crmname.contentEquals("")) {
			
			try {
				String query4="select username from crmlogin where name='"+crmname+"'";
				System.out.println(query4);
			PreparedStatement ps=con.prepareStatement(query4); 
		//	System.out.println(query);
			rs3=ps.executeQuery();
			while(rs3.next()) {
				
				crmname=rs3.getString("username");
			}
			
			}
			catch(Exception e) {
				
			}
			
			
			
			
			array.put("crm",crmname);
			
			arrlist.add("crm");
		}
		if(!doctorname.contentEquals("")) {
			array.put("doctorname",doctorname);
			
			arrlist.add("doctorname");
		}
		if(!searchbytype.contentEquals("")) {
			array.put("type",searchbytype);
			
			arrlist.add("type");
		}
		
		if(!citysearch.contentEquals("")) {
			array.put("city",citysearch);
			
			arrlist.add("city");
		}
		
		System.out.println(array.size()+"  "+arrlist.size());
		
		
		String query="select * from mrlogs where ";
		//Set<String> arr=array.keySet();
		for(int i=0;i<array.size();i++) {
			String check="'"+array.get(arrlist.get(i))+"%'";
			String like=" like ";
			
				
				
				
			
			query=query+arrlist.get(i)+like+check;
			
			if(!(i==array.size()-1)) {
				
				
				query=query+" and ";
				
			}
			
		}
		
		System.out.println("this is the final query  "+query);
		
		if(!crmname.contentEquals("")||!citysearch.contentEquals("")) {
		if(!fromdate.contentEquals("")) {
			if(!doctorname.contentEquals("")) {
				
				query=query.substring(0,query.length());
				
			}
			
		query=query+" and date1>='"+fromdate+"' and date1<='"+todate+"'";
			
		
			
		}
		}
		else {
			
			if(!fromdate.contentEquals("")) {
				if(!doctorname.contentEquals("")) {
					
					query=query.substring(0,query.length());
					
				}
				
			query=query+" date1>='"+fromdate+"' and date1<='"+todate+"'";
				
			
				
			}
			
		}
		
		System.out.println("so finally weget  "+query);
		
		
			int count=0;
			if(query.contentEquals("select * from mrlogs where ")) {
				count=1;
			}
			
		
		
		if(fromdate.contentEquals("")&&todate.contentEquals("")&&crmname.contentEquals("")&&doctorname.contentEquals("")&&citysearch.contentEquals("")&&searchbytype.contentEquals("")&&searchby1.get(0).contentEquals("")&&searchby1.get(0).contentEquals("")&&searchby2.get(0).contentEquals(""))
		
		{
			
			query="select * from mrlogs order by date1 desc";
			
		}
		else if(true) {
			System.out.println("query before loop   "+query);
			
			
		
			
			
		}
		
		System.out.println("query main nfjkng   "+querymain.length());
		
		if(!querymain.contentEquals("")) {
			if(!(fromdate.contentEquals("")&&todate.contentEquals("")&&crmname.contentEquals("")&&doctorname.contentEquals("")&&citysearch.contentEquals("")&&searchbytype.contentEquals(""))) {
			
			
			query=query+" and "+querymain;
			}
			else {
				
				query=query+ " "+querymain;
			}
		}
		
		try {
		
		PreparedStatement ps=con.prepareStatement(query); 
	System.out.println("this is the query for execution  "+ query);
		rs=ps.executeQuery();
		
		while(rs.next()) {
			
			JSONObject addobj=new JSONObject();
        	String date1= rs.getString("date1");
        	String doctorname1= rs.getString("DoctorName");
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
			String city=rs.getString("city");
			String mrlogid=rs.getString("id");
			
			String type=rs.getString("type");
			String crmname1="";
				String query1="select * from crmlogin where username='"+crm+"'";
				PreparedStatement ps1=con.prepareStatement(query1); 
				System.out.println(query1);
				rs1=ps1.executeQuery();
				if(rs1.next()) {
					
					crmname1=rs1.getString("name");
				}
			  
			  
        	addobj.put("crmname",crmname1);
        	addobj.put("date1",date1);
        	addobj.put("doctorname",doctorname1);
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
        	addobj.put("city",city);
        	addobj.put("mrlogid",mrlogid);
                	addobj.put("type",type);
        	returnjson.add(addobj);
			
			
		}
		
		}
		catch(Exception e) {
			
			
			
			
		}
		System.out.println(returnjson.toJSONString());
		
		return Response.status(Status.OK).entity(returnjson.toJSONString()).build();
	//return "hello";
	}


}
