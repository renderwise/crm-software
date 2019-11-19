

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Servlet implementation class sample
 */
@WebServlet("/sample")
public class sample extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		System.out.println("dsbjfnsfhbsj");
		
		
		JSONArray returnjson= new JSONArray();
		JSONObject addobj=new JSONObject();
		JSONObject addobj1=new JSONObject();
	
			addobj.put("name","amit");
			addobj.put("class","btech");
			addobj.put("rollno","1563");
			returnjson.add(addobj);
			addobj1.put("name","amit1");
			addobj1.put("class","btech1");
			addobj1.put("rollno","15631");
			returnjson.add(addobj1);
			
    response.setContentType("application/json");
    		response.getWriter().write(returnjson.toJSONString());
	}

}
