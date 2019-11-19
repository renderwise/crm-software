

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Servlet implementation class addcrmdata1
 */
@WebServlet("/addcrmdata1")
public class addcrmdata1 extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		String formdata=request.getParameter("x");
		
		
		JSONParser parser=new JSONParser();
		JSONObject j = null;
		try {
			
			System.out.println("clicked "+formdata );
			j=(JSONObject) parser.parse(formdata) ;
			String mrlogdate=j.get("date").toString();
		//	String 
			String crm=j.get("user").toString();
			String maindate=j.get("maindate").toString();
			System.out.println(maindate);
			String timefrom=j.get("timefrom").toString();
			String type=j.get("typeofdoctor").toString();
			System.out.println("this is the type  "+type);
			String timeto=j.get("timeto").toString();
			int doctorid=Integer.parseInt(j.get("doctorname").toString());
			String status=j.get("status").toString();
			String discussion=j.get("discussion").toString();
			String ordervalue=j.get("ordervalue").toString();
			String orderquantity=j.get("orderquantity").toString();
			String orderbooked=j.get("orderbooked").toString();
			String expecteddate=j.get("expecteddate").toString();
			String actualdate=j.get("actualdate").toString();
			String city=j.get("city").toString();
			String remarks=j.get("rem").toString();
			
		System.out.println("doctorid "+doctorid);
		
			Connection con=ConnectionJDBC.DBconnect();
			ResultSet rs;
			ResultSet rs1=null;
			
			HashMap<Integer,String> hs=new HashMap<Integer,String>();
			HashMap<Integer,String> hs1=new HashMap<Integer,String>();
			HashMap<Integer,String> hs2=new HashMap<Integer,String>();
			
		try {
			
			
			PreparedStatement ps1=con.prepareStatement("select doctor_id,doctorname,address,phonenumber from doctorinfo");
			rs1=ps1.executeQuery();
			while(rs1.next()) {
				
				hs.put(rs1.getInt("doctor_id"),rs1.getString("doctorname"));
				hs1.put(rs1.getInt("doctor_id"),rs1.getString("address"));
				hs2.put(rs1.getInt("doctor_id"),rs1.getString("phonenumber"));
				
				
				
			}
			
		}
		catch(Exception e) {
			
			e.printStackTrace();
			
		}
			
			String doctorname=(String)hs.get(doctorid);
			String address=hs1.get(doctorid);
			String phonenumber=hs2.get(doctorid);
			System.out.println("this sis the docotrname  = "+doctorname);
			try{
			
				if(j.containsKey("remarks")) {
					
					remarks=j.get("remarks").toString();
					
				}
				System.out.println("doctorname  "+doctorname);
				System.out.println("type   "+type);
				
				
				
			PreparedStatement ps=con.prepareStatement("insert into mrlogs1(crm,date1,timefrom,timeto,DoctorName,Address,contact,status,Discussion,ordervalue,orderquantity,orderbooked,expecteddate,actualdate,remarks,mrlogdate,city) values('"+crm+"','"+maindate+"','"+timefrom+"','"+timeto+"','"+doctorname+"','"+address+"','"+phonenumber+"','"+status+"','"+discussion+"','"+ordervalue+"','"+orderquantity+"','"+orderbooked+"','"+expecteddate+"','"+actualdate+"','"+remarks+"','"+mrlogdate+"','"+city+"')");
			
			
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
		
		
		out.print("LOG Added");
		
		
		
		
	}

}
