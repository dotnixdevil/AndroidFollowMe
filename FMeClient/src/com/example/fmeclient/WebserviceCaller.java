package com.example.fmeclient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

public class WebserviceCaller   {
	   ExecutorService service;
       Future<String>  task;
	public static  String absoulateUrl="http://2.followmetrial.appspot.com/";
    //	static String  json="";
	
	private     String json=null;
	public  String  addNewUser(final String uname, final String pass, final String email,String regid){

		
      
        service = Executors.newFixedThreadPool(1);    
       task    = service.submit(new AddUser(uname,pass,email,regid));
       try
       {
       json=task.get();
       }catch(Exception e)
       {
    	   e.printStackTrace();
       }
		return json;
		
	
		
		
	}
	public  String addNewDeice(final String dname,final String uname,final String cname){
		
		 
        service = Executors.newFixedThreadPool(1);    
       task    = service.submit(new AddDevice(dname, uname, cname));
       try
       {
       json=task.get();
       }catch(Exception e)
       {
    	   e.printStackTrace();
       }
		return json;
		
		
	}
	
	public  String Login(final String uname,final String pass){
		
		 
        service = Executors.newFixedThreadPool(1);    
       task    = service.submit(new UserLogin(uname, pass));
       try
       {
       json=task.get();
       }catch(Exception e)
       {
    	   e.printStackTrace();
       }
		return json;
		
		
	}
	public  String addNewLocation(final String uname,final String cname,final String longt,final  String lat,final Date d){
		

		try{
			
			
			URL url=new URL(absoulateUrl+"locations?action=addlocation&lat="+lat+"&lont="+longt+"&username="+uname+"&cname="+cname);
			BufferedReader br=new BufferedReader(new InputStreamReader(url.openStream()));
			json=br.readLine();
			
			
		}catch(Exception e){
			
			e.printStackTrace();
			
		}
		return json;
	}

	
	
	

}
