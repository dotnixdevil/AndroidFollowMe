package com.followme;

import java.io.IOException;
import java.io.PrintWriter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.*;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.google.apphosting.utils.remoteapi.RemoteApiPb.Request;

@SuppressWarnings("serial")
public class FollowmetrialServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		//JSONObject jo=new JSONObject();
		
		PrintWriter out=resp.getWriter();
		
		JPAsevice js=new JPAsevice();

		String action=req.getParameter("action");
		//out.println("hello");
		
		try{
		if(action.equals("adddevice")){
			String dname=req.getParameter("dname");
			String username=req.getParameter("username");
			String cname=req.getParameter("cname");
			if(!js.isnull(dname,username,cname)){
				
				if(js.addDevice(dname, username,cname)){
					if(js.containUser(username)){
					js.printJSONmessage(out, "device added successfully  ","1");
					}else{
						js.printJSONmessage(out, " no user name like your boss name", "4");
						
					}
					
				}else{
					js.printJSONmessage(out, "login successfully  ","1");
				}
				
				
			}
			else{
				out.println("Error 1222");
				
			}
			
			
			
		}else if(action.equals("login")){
			String uname=req.getParameter("uname");
			String pass=req.getParameter("pass");
			//String cname=req.getParameter("cname");
			if(!js.isnull(uname,pass)){
				
				if(js.Login(uname, pass)){
					
					js.printJSONmessage(out, "Login success ","1");
					
				}else{
					js.printJSONmessage(out, "username or passwor error ","2");
				}
				
				
			}
			else{
				out.println("Error 1222");
				
			}
			
			
			
		}else if(action.equals("adduser")){
			String uname=req.getParameter("uname");
			String email=req.getParameter("email");
			String pass=req.getParameter("pass");
			String did=req.getParameter("did");
			
			if(!js.isnull(uname,email,pass,did)){
				
				if(js.addUser(uname, email, pass,did)){
					js.printJSONmessage(out, "User added successfully  ","1");
					
				}else{
					js.printJSONmessage(out, "User already exist ","2");
				}
				
				
			}
			else{
				out.println("Error 1222");
				
			}
			
		}
		else{
			js.printJSONmessage(out, "Error 2233","3");
			
			
		}
		
		}catch(Exception e){
			//out.println(e.getMessage());
			out.println("Error 1212");
			
		}
		
	
	}
}


