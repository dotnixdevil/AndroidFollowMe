package com.example.fmeclient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import java.util.concurrent.Future;

public class AddUser implements Callable<String>{
	private  String uname;
	private String pass;
	private String email;
	private String regid;
	
	public AddUser(String uname,String pass,String email,String regid){
		this.uname=uname;
		this.pass=pass;
		this.email=email;
		this.regid=regid;
		
		
	}
	public String call() throws Exception {
		// TODO Auto-generated method stub
    String json=null;
	try{
		
  
		//System.out.println(absoulateUrl+"followmetrial?action=adduser&uname="+uname+"&pass="+pass+"&email="+email);
		
		
		URL url=new URL(WebserviceCaller.absoulateUrl+"followmetrial?action=adduser&uname="+uname+"&pass="+pass+"&email="+email+"&did="+regid);
	
		
		BufferedReader br=new BufferedReader(new InputStreamReader(url.openStream()));
		String temp=null;
		while((temp=br.readLine())!=null){
			
			json=temp;
		
			System.out.println(temp);
			
			
			
			
			;
		}
	//	startActivity(new Intent("followme.cpanel"));
		
		
		br.close();
	}catch(Exception e){
		
		e.printStackTrace();
		
	}
  
		return json;
		
	}

}
