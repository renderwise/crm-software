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




@Path("/findrowscheck2")
public class findrowscheck2 {
	
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
		ResultSet rs5=null;
		
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
		String checktype=j.get("checklogsselect1").toString();
		System.out.println("this is th eicyt  "+citysearch);
		String 	query="select distinct d.doctor_id,d.type,d.doctorname,d.clinicname,d.city,d.age,d.experience,d.noofchairs,d.type,e.crm_id from doctorinfo d inner join enquirytable e where d.doctor_id=e.doctor_id ";
		String query5="select doctor_id from doctorinfo ";
		
		
		
		String filterby1=j.get("filterby1").toString();
		String filterby2=j.get("filterby2").toString();
		String filterby3=j.get("filterby3").toString();
		String sign1=j.get("sign1").toString();
		String sign2=j.get("sign2").toString();
		String sign3=j.get("sign3").toString();
		String value1=j.get("value1").toString();
		String value2=j.get("value2").toString();
		String value3=j.get("value3").toString();
		String andor1=j.get("andor1").toString();
		String andor2=j.get("andor2").toString();
		
		
		
		
		System.out.println("condition is : "+condition);
		
		
		
		 ArrayList<String> arrlist = new ArrayList<String>(); 
		 ArrayList<String> arrlist1 = new ArrayList<String>(); 
			  
		 HashMap<String, String> array = new HashMap<String, String>();
		 HashMap<String, String> array1 = new HashMap<String, String>();
			
		 if(!crmname.contentEquals("")) 
			 	
		 		{
			 		
						array.put("e.crm_id=",crmname);
						array1.put("crm_id=",crmname);
						
						arrlist.add("e.crm_id=");
						arrlist1.add("crm_id=");
				}
		 if(!fromdate.contentEquals("")) 
			 	
	 		{
		 		
					array.put("enquirydate>=",fromdate);
					
					arrlist.add("enquirydate>=");
				
			}
		 if(!todate.contentEquals("")) 
			 	
	 		{
		 		
					array.put("enquirydate<=",todate);
					
					arrlist.add("enquirydate<=");
			}
		 if(!citysearch.contentEquals("")) 
			 	
	 		{
		 		
					array.put("d.city=",citysearch);
					
					arrlist.add("d.city=");
					array1.put("city=",citysearch);
					
					arrlist1.add("city=");
			}
		
		 if(!doctorname.contentEquals("")) 
			 	
	 		{
		 		
					array.put("d.doctorname=",doctorname);
					
					arrlist.add("d.doctorname=");

					array1.put("doctorname=",doctorname);
					
					arrlist1.add("doctorname=");
			}
		 
		 
		 
		 if(!checktype.contentEquals("")) 
			 	
	 		{
		 		
					array.put("d.type=",checktype);
					
					arrlist.add("d.type=");
					array1.put("type=",checktype
							);
					
					arrlist1.add("type=");
			}
		
		 for(int i=0;i<arrlist.size();i++) 
		{
			 if(i==0) {
					query=query+" and ";
				}
			query=query+" "+arrlist.get(i)+"'"+array.get(arrlist.get(i))+"'"+" ";
			if(i!=arrlist.size()-1) {
				
				query=query+"and ";
			}
		
		}
		
		for(int i=0;i<arrlist1.size();i++) 
		{
			if(i==0) {
				query5=query5+" where ";
			}
			query5=query5+" "+arrlist1.get(i)+"'"+array1.get(arrlist1.get(i))+"'"+" ";
			
			if(i!=arrlist1.size()-1) {
				
				query5=query5+"and ";
			}
			
			
		}
		query=query+" group by doctor_id";
		
		System.out.println("query5  "+query5);
		
						ArrayList<String> doctoridlist=new ArrayList<String>();
						ArrayList<String> doctoridlist1=new ArrayList<String>();
						
					//	String 	query="select distinct d.doctor_id from doctorinfo d inner join enquirytable e where e.crm_id='"+crmname+"' and enquirydate<='"+todate+"' and enquirydate>='"+fromdate+"'";
						System.out.println(query);
						
						System.out.println(query5);
						try {
						
						PreparedStatement ps=con.prepareStatement(query); 
					System.out.println("this is the query for execution  "+ query);
						rs=ps.executeQuery();
						rs1=rs;
						while(rs.next()) 
							
							{
								
								JSONObject addobj=new JSONObject();
								//String doctor_id=rs.getString("doctor_id");
							
								doctoridlist.add(rs.getString("doctor_id"));
								  
							}
						
						}
						catch(Exception e) {
							
							
						}
						
						//String[][] twodarray=null;
						
						try {
							PreparedStatement ps=con.prepareStatement(query5); 
						System.out.println("this is the query for execution 4  "+ query5);
							rs5=ps.executeQuery();
							//rs1=rs;
							while(rs5.next()) 
								{
									
									JSONObject addobj=new JSONObject();
									//String doctor_id=rs.getString("doctor_id");
								
									doctoridlist1.add(rs5.getString("doctor_id"));
									  
									
									
								}
							
							}
							catch(Exception e) {
								
								
							}
							
						
						for(int i=0;i<doctoridlist1.size();i++) {
							
							System.out.println("fjhdbfhudbf  "+doctoridlist1.get(i));
						}
						
						
						
							
						
						
			// getting values of crm name with crm ids
						
					HashMap<String,String> getnames=new HashMap<String,String>();
					ResultSet rs4=null;
					try {
						String query4="select id,name from login";
						
						PreparedStatement ps=con.prepareStatement(query4);
						rs4=ps.executeQuery();
						while(rs4.next()) 
							{
								
								getnames.put(rs4.getString("id"),rs4.getString("name"));
								
							}
						
						
					}
					catch(Exception e) {
						
						
					}
						
						
					ArrayList<String> dt_id=new ArrayList<String>();	
						
		StringBuilder builder = new StringBuilder();

		for( int i = 0 ; i < doctoridlist.size(); i++ ) {
		    builder.append("?,");
		}
		
		try {
			
			if(doctoridlist.size()>0) {
				String query2="select distinct doctor_id,count(*),COUNT(CASE WHEN caseconverted like 'true' then 1 end) AS count1,enquirydate from enquirytable  where doctor_id in (" 
		               + builder.deleteCharAt( builder.length() -1 ).toString() + ") ";
		//	System.out.println(array.get("enquirydate>=")!=null);
				
				
				
				if(!fromdate.contentEquals("")) {
					
					query2=query2+" and enquirydate>='"+fromdate+"'" +" and enquirydate<='"+todate+"'";
					
				}
				System.out.println("befor execution "+query2);
				
				if(!crmname.contentEquals("")) {
					query2=query2+" and crm_id='"+crmname+"' ";
				}
				query2=query2+" group by doctor_id";
				
				PreparedStatement ps=con.prepareStatement(query2); 
				//int index = 1;
				for(int i=0;i<doctoridlist.size();i++ ) {
					System.out.println(doctoridlist.get(i));
					ps.setString(i+1,doctoridlist.get(i));
				  // ps.setString(  index++, o ); // or whatever it applies 
				}
				
				System.out.println("this is the query for executionhvifg  "+ query2);
				HashMap<String,String> doctors=new HashMap<String,String>();
				HashMap<String,String> doctors1=new HashMap<String,String>();
				
				rs3=ps.executeQuery();
				while(rs3.next()) {
					
					System.out.println(rs3.getString("doctor_id")+" "+rs3.getString("count(*)"));
					
					System.out.println("counting starts here   \n\n");
					System.out.println(rs3.getString("count1"));
					
					doctors.put(rs3.getString("doctor_id"), rs3.getString("count(*)"));
					doctors1.put(rs3.getString("doctor_id"), rs3.getString("count1"));
					
				}
				
				rs3.beforeFirst();
				
				
				while(rs3.next()) {
					
					dt_id.add(rs3.getString("doctor_id"));
					
				}
			}
				
				System.out.println("this is the size  "+dt_id.size());
				for(int i=0;i<dt_id.size();i++) {
					System.out.println(doctoridlist1.size());
					
					if(doctoridlist1.contains(dt_id.get(i))) {
						System.out.println(dt_id.get(i)+" end");
						doctoridlist1.remove(dt_id.get(i));
						
					}
					
				}
				
				System.out.println("final arraylist is : "+doctoridlist1.size());
				
				for(int i=0;i<doctoridlist1.size();i++) {
					
					System.out.println(doctoridlist1.get(i));
					
				}
				System.out.println("end");
				
				if(doctoridlist.size()>0) {
				rs3.beforeFirst();}
				rs.beforeFirst();
				rs5.beforeFirst();
				System.out.println("end2");
				
				
				
			
				
				
				
				
				
				if(doctoridlist.size()>0) {
				while(rs3.next()) 
				{
					rs.next();
					JSONObject addobj=new JSONObject();
					//JSONObject addobj=new JSONObject();
					//String doctor_id=rs.getString("doctor_id");
				System.out.println(rs.getString("doctor_id"));
					//doctoridlist.add(rs.getString("doctor_id"));
				System.out.println(rs3.getString("count(*)"));
				addobj.put("doctor_id",rs.getString("doctor_id"));
				addobj.put("noofenquiries",rs3.getString("count(*)"));
				addobj.put("lastenquirydate",rs3.getString("enquirydate"));
				addobj.put("noofcases",rs3.getString("count1"));
				
				addobj.put("crmname",getnames.get(rs.getString("crm_id")));
				
				addobj.put("doctorname",rs.getString("doctorname"));
				addobj.put("age",rs.getString("age"));
				addobj.put("clinicname",rs.getString("clinicname"));
				addobj.put("city",rs.getString("city"));
				addobj.put("experience",rs.getString("experience"));
				addobj.put("noofchairs",rs.getString("noofchairs"));
				addobj.put("type",rs.getString("type"));
				//addobj.put("noofenquiries",rs3.getString("count(*)"));
				
				//addobj.put("type",rs.getString("type"));
				
				
				
				boolean c=false;
				if(!filterby1.contentEquals("")) {
					int x;
					//int enquirynumber=rs3.getInt("count(*)");
					if(!filterby1.contentEquals("noofenquiries")&&!filterby1.contentEquals("noofcases")) {
					 x=rs.getInt(filterby1);
					 }
					else if(filterby1.contentEquals("noofcases")) {
						 x=rs3.getInt("count1");
					}
					
					else  {
						 x=rs3.getInt("count(*)");
					}
					condition=condition+x+sign1+value1;
					
					if(!filterby2.contentEquals("")) {
						
						if(!filterby2.contentEquals("noofenquiries")&&!filterby2.contentEquals("noofcases")) {
						 x=rs.getInt(filterby2);
						}
						else if(filterby2.contentEquals("noofcases")) {
							 x=rs3.getInt("count1");
						}
						else {
							x=rs3.getInt("count(*)");
						}
						condition=condition+andor1+x+sign2+value2;
						
					}
					if(!filterby3.contentEquals("")) {
						if(!filterby3.contentEquals("noofenquiries")&&!filterby3.contentEquals("noofcases")) {
						 x=rs.getInt(filterby3);
					}
						else if(filterby3.contentEquals("noofcases")) {
							 x=rs3.getInt("count1");
						}
					else {
						x=rs3.getInt("count(*)");
					}
						condition=condition+andor2+x+sign3+value3;
						
					}
					System.out.println("conition is    :  "+condition);
					
					  ScriptEngineManager mgr = new ScriptEngineManager();
					    ScriptEngine engine = mgr.getEngineByName("JavaScript");
					c=(boolean)engine.eval(condition);
				
					
				}
				
				
				
				if(filterby1.contentEquals("")&&filterby2.contentEquals("")&&filterby3.contentEquals(""))
				{
					System.out.println("here");
					returnjson.add(addobj); 
				}
				else 
				{
						if(c) {
							
							
							System.out.println("theer");
						
							returnjson.add(addobj); 
							
						}
				
				}
				condition="";
				
				//returnjson.add(addobj); 
			
				}
				}
				//System.out.println("final size is  :  "+doctoridlist.size()+"   "+doctoridlist.get(0));
				if(doctoridlist1.size()>0) {
				StringBuilder builder1 = new StringBuilder();

				for( int i = 0 ; i < doctoridlist1.size(); i++ ) {
				    builder1.append("?,");
				}
				
				ResultSet rs6=null;
				try {
					String query6="select doctor_id,type,doctorname,clinicname,city,age,experience,noofchairs,type,crm_id from doctorinfo  where doctor_id in (" 
				               + builder1.deleteCharAt( builder1.length() -1 ).toString() + ") ";
			
					PreparedStatement ps=con.prepareStatement(query6); 
					for(int i=0;i<doctoridlist1.size();i++ ) {
						
						System.out.println("check from heres");
						
						System.out.println(doctoridlist1.get(i));
						ps.setString(i+1,doctoridlist1.get(i));
					  // ps.setString(  index++, o ); // or whatever it applies 
					}
					rs6=ps.executeQuery();
				
				}
				catch(Exception e) {
					
					e.printStackTrace();
					
				}
				for(int i=0;i<doctoridlist1.size();i++) {
									rs6.next();
									JSONObject addobj=new JSONObject();
									addobj.put("doctor_id",doctoridlist1.get(i));
									addobj.put("noofenquiries",0);
									addobj.put("noofcases",0);
									
									
									int enquiries=0;
									int casesnumber=0;
									addobj.put("doctorname",rs6.getString("doctorname"));
									addobj.put("age",rs6.getString("age"));
									addobj.put("clinicname",rs6.getString("clinicname"));
									addobj.put("city",rs6.getString("city"));
									addobj.put("crmname",getnames.get(rs6.getString("crm_id")));
									
									addobj.put("experience",rs6.getString("experience"));
									addobj.put("noofchairs",rs6.getString("noofchairs"));
									addobj.put("type",rs6.getString("type"));
									
										
									boolean c=false;
									if(!filterby1.contentEquals("")) {
										int x;
										if(!filterby1.contentEquals("noofenquiries")&&!filterby1.contentEquals("noofcases")) {
										 x=rs6.getInt(filterby1);
										}
										else if(filterby1.contentEquals("noofcases")) {
											
											x=casesnumber;
										}
										
										else {
											x=enquiries;
										}
										condition=condition+x+sign1+value1;
										
										if(!filterby2.contentEquals("")) {
											if(!filterby2.contentEquals("noofenquiries")&&!filterby2.contentEquals("noofcases")) {
											 x=rs6.getInt(filterby2);
											}
											else if(filterby2.contentEquals("noofcases")) {
												
												x=casesnumber;
											}
											else {
												x=enquiries;
											}
											condition=condition+andor1+x+sign2+value2;
											
										}
										if(!filterby3.contentEquals("")) {
											if(!filterby3.contentEquals("noofenquiries")&&!filterby3.contentEquals("noofcases")) {
												 x=rs6.getInt(filterby3);
												}
											else if(filterby3.contentEquals("noofcases")) {
												
												x=casesnumber;
											}
											
											else {
												x=enquiries;
											}
											condition=condition+andor2+x+sign3+value3;
											
										}
										System.out.println("conition is    :  "+condition);
										
										  ScriptEngineManager mgr = new ScriptEngineManager();
										    ScriptEngine engine = mgr.getEngineByName("JavaScript");
										c=(boolean)engine.eval(condition);
										
									}
									
									
									
									if(filterby1.contentEquals("")&&filterby2.contentEquals("")&&filterby3.contentEquals(""))
									{
										System.out.println("here");
										returnjson.add(addobj); 
									}
									else 
									{
											if(c) {
												
												
												System.out.println("theer");
											
												returnjson.add(addobj); 
												
											}
									
									}
									condition="";
							
									
								}
				
				}	
			}
			catch(Exception e)
			{
				e.printStackTrace();
				
				
			}
		
		
		
		System.out.println("jhijhiiuh"+returnjson.toJSONString());
			
		
		return Response.status(Status.OK).entity(returnjson.toJSONString()).build();
	//return "hello";
	}


}
