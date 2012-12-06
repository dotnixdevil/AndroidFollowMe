package com.followme;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
@SuppressWarnings("serial")
public class Locations extends HttpServlet {



	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	
	    PrintWriter out=resp.getWriter();
		
		JPAsevice js=new JPAsevice();

		String action=req.getParameter("action");
	  
		try{
		if(action.equals("addlocation")){
			String lat=req.getParameter("lat");
			String longt=req.getParameter("lont");
			Date date=new Date();
			
			String username=req.getParameter("username");
			String cname=req.getParameter("cname");
			if(!js.isnull(username,cname,lat,longt)&&date!=null){
				
				if(js.addNewlocation(username, cname, lat, longt, date)){
					js.printJSONmessage(out, "Location added successfully  ","1");
					
				}
				
				
			}
			else{
				out.println("Error 1222");
				
			}
			
			
			
		}else if(action.equals("getlocations")){
			out.println("hello");
			
			String username=req.getParameter("username");
			out.println(js.getLocations(username));
			List<Location> ll=js.getLocations(username);
			for(Location lo:ll){
				out.println(lo.getCname());
				
				
			}
			/*List<Location> l=js.getLocations(username);
			JSONArray ja=new JSONArray();
			for(Location ll:l){
				out.println("hellowrold");
				
				JSONObject joo=new JSONObject();
				joo.put("cname", ll.getCname());
				joo.put("long",ll.getLongt() );
				joo.put("lat", ll.getLat());
				joo.put("date", ll.getDate());
				
				
			   ja.put(joo);
			   
			}
		
			JSONObject jo=new JSONObject();
			jo.put("locations", ja);
			out.println("hello");
			
			out.println(jo.toString());
			
			*/
			
			
				
		
			
				
				
			}
		
		else{
			js.printJSONmessage(out, "Error 2233","3");
			
			
		}
		
		}catch(Exception e){
			out.println(e.getMessage());
			out.println("Error 1212");
			
		
	  
		//super.doGet(req, resp);
	}
		}
	

}
