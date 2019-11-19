


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.xml.parsers.ParserConfigurationException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


@Path("/addcrmdata")
public class addcrmdata {
	
	
	
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response test(String formdata) throws IOException, ParserConfigurationException {
		JSONParser parser=new JSONParser();
		JSONObject j = null;
		try {
			
			System.out.println("clicked "+formdata );
			j=(JSONObject) parser.parse(formdata) ;
			
			String crm=j.get("crmname").toString();
			String date=j.get("date").toString();
			System.out.println(date);
			String timefrom=j.get("timefrom").toString();
			String timeto=j.get("timeto").toString();
			String doctorname=j.get("doctorname").toString();
			String status=j.get("status").toString();
			String discussion=j.get("discussion").toString();
			String ordervalue=j.get("ordervalue").toString();
			String orderquantity=j.get("orderquantity").toString();
			String orderbooked=j.get("orderbooked").toString();
			
			String remarks=j.get("rem").toString();
			
			Connection con=ConnectionJDBC.DBconnect();
			ResultSet rs;
			try{
			
				if(j.containsKey("remarks")) {
					
					remarks=j.get("remarks").toString();
					
				}
				
				PreparedStatement ps=con.prepareStatement("insert into mrlogs values('"+crm+"','"+date+"','"+timefrom+"','"+timeto+"','"+doctorname+"','','','"+status+"','"+discussion+"','"+ordervalue+"','"+orderquantity+"','"+orderbooked+"','','','"+remarks+"')");
				
				
				//PreparedStatement ps=con.prepareStatement("insert into alignwise_cases values('"+j.get("serialnumber")+"',?,'No link','No link',?,'"+j.get("user")+"','"+j.get("remarks")+"')");
				
			ps.execute();
				
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
		
		return Response.status(Status.OK).entity(j.toJSONString()).build();
	//return "hello";
	}

	

}
