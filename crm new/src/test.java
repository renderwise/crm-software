

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.*;

@Path("product")
public class test {

	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/findall")
	public Response find() {
		JSONParser parser=new JSONParser();
		JSONObject j = null;
		String formdata="";
	//	j= (JSONObject) parser.parse(formdata);
		JSONArray returnjson= new JSONArray();
		
		
		
	System.out.println("amit found");
	
	return Response.status(Status.OK).entity(returnjson.toJSONString()).build();

	}
	
	
	
}
