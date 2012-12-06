package com.example.fmeclient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.concurrent.Callable;

public class AddDevice implements Callable<String>{
	private  String dname;
	private String uname;
	private String cname;
	
	public AddDevice(String dname,String uname,String cname){
		this.dname=dname;
		
		this.uname=uname;
		this.cname=cname;
		
		
	}
	public String call() throws Exception {
		// TODO Auto-generated method stub
    String json=null;
	try{
		
  
		//System.out.println(absoulateUrl+"followmetrial?action=adduser&uname="+uname+"&pass="+pass+"&email="+email);
		
		URL url=new URL(WebserviceCaller.absoulateUrl+"followmetrial?action=adddevice&dname="+dname+"&username="+uname+"&cname="+cname);
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
