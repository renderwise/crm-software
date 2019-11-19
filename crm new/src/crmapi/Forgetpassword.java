package crmapi;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

@javax.ws.rs.Path("forgetPassword")
public class Forgetpassword {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response test(String userform) throws IOException, ParseException, ParserConfigurationException, SAXException, TransformerException {
		/*forgetPasswordJson.username
		forgetPasswordJson.newpassword
		forgetPasswordJson.confirmnewpassword
		forgetPasswordJson.phoneNumber*/
		JSONParser parser= new JSONParser();
		JSONObject changepasswordjson=(JSONObject)parser.parse(userform);
	    
	    String username=changepasswordjson.get("username").toString();
	    String phoneNumber=changepasswordjson.get("phoneNumber").toString();
	    String newpassword=changepasswordjson.get("newpassword").toString();
	    String confirmnewpassword=changepasswordjson.get("confirmnewpassword").toString();
	    System.out.println(confirmnewpassword);
	    System.out.println(newpassword);
	    

		Connection con=ConnectionJDBC.DBconnect();
		
		PreparedStatement ps;
		try {
			if(newpassword.equals(confirmnewpassword)){
				ps = con.prepareStatement("select phone from login where username='"+username+"'");	
				ResultSet rs=ps.executeQuery();
				String phoneNumberResult=null;
				int count=0;
				while(rs.next()){
					count++;
					phoneNumberResult= rs.getString("phone"); 
				}
				if(count==0){
					throw new Exception("User does not Exist");
				}
				if(phoneNumberResult.equals(phoneNumber)){
				ps = con.prepareStatement("update login set password='"+newpassword+"' where username='"+username+"' and phone='"+phoneNumber+"'");	
				ps.execute();	
				System.out.println("Password Changed");
				}
				else {
					throw new Exception("Mobile Number does not match");
				}
				
			}
			else{throw new Exception("password does not match");}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			return Response.status(Status.UNAUTHORIZED).entity(changepasswordjson.toJSONString()).build();
			
		}finally {
			ConnectionJDBC.DBDisconnect(con);	
			
		}
		
		return Response.status(Status.OK).entity(changepasswordjson.toJSONString()).build();
	   
	
	  	
	    
	  
	}
	
}
