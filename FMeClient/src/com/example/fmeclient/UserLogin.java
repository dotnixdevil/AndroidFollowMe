package com.example.fmeclient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.concurrent.Callable;

public class UserLogin implements Callable<String>{
	private  String uname;
	private String pass;

	
	public UserLogin(String uname,String pass){
		this.uname=uname;
		
		this.pass=pass;
		
		
		
	}
	public String call() throws Exception {
		// TODO Auto-generated method stub
    String json=null;
	try{
		
  
		//System.out.println(absoulateUrl+"followmetrial?action=adduser&uname="+uname+"&pass="+pass+"&email="+email);
		
		URL url=new URL(WebserviceCaller.absoulateUrl+"followmetrial?action=login&uname="+uname+"&pass="+pass);
		BufferedReader br=new BufferedReader(new InputStreamReader(url.openStream()));
		json=br.readLine();
			
			
			
			
			;
		
	//	startActivity(new Intent("followme.cpanel"));
		
		
		br.close();
	}catch(Exception e){
		
		e.printStackTrace();
		
	}
  
		return json;
		
	}
	
	

}
