package com.followme;

import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.google.appengine.labs.repackaged.org.json.JSONObject;

public class JPAsevice {
	EntityManagerFactory emf=null;
	EntityManager em=null;
	
	public JPAsevice(){
		emf=Persistence.createEntityManagerFactory("transactions-optional");
		em=emf.createEntityManager();
	}
	
	public boolean isnull(String...args){
		boolean isnull=false;
		
		for(String n:args){
			if(n==null){
				isnull=true;
			}
			
		}
		return isnull;
		
	}
	
	public boolean addUser(String uname,String email,String pass,String did){
		
		if(!containUser(uname)){
			em.getTransaction().begin();
			User u=new User();
			u.setUname(uname);
			u.setEmail(email);
			u.setPass(pass);
			u.setDid(did);
			em.persist(u);
			em.getTransaction().commit()
			;
			
			return true;
			
		}else{
			return false;
			
		}
		
	}
	
	public boolean addNewlocation(String uname,String cname,String lat,String longt,Date date){
		
		
			em.getTransaction().begin();
			Location l=new Location();
			l.setUsername(uname);
			l.setCname(cname);
			l.setLat(lat);
			l.setLongt(longt);
			l.setDate(date);
			em.persist(l);
			
			em.getTransaction().commit();
			return true;
		
		
	     
	}
	
	
	public boolean addDevice(String dname,String uname,String cname){
		if(!containDevice(dname)){
		em.getTransaction().begin();
		Device d=new Device();
		d.setDname(dname);
		d.setUsername(uname);
		d.setCname(cname);
		
		em.persist(d);
		em.getTransaction().commit();
		return true;
		}else{
			return false;
		}

	}
	
	
	
	public List<Location>	 getLocations(String uname){
		
		//Query q=em.createQuery("SELECT e FROM Location e  ", Location.class);
		em.getTransaction().begin();
		
		TypedQuery<Location> tq=em.createQuery("SELECT e FROM Location e ", Location.class);
		em.getTransaction().commit();
		
		//Query q=em.createQuery("SELECT d FROM Location d WHERE d.username='"+uname+"'");
		return tq.getResultList();
		
		


	}
	public void printJSONmessage(PrintWriter out,String message,String code){
		
		try{
		JSONObject jo=new JSONObject();
		jo.put("msg", message);
		jo.put("code",code);
		
		out.println(jo.toString());
		}catch(Exception e){
			out.println("Error 1177");
			
		}
		
	}
	public boolean containDevice(String dname){
		//boolean exits=false;
	
		
		Query q=em.createQuery("SELECT e FROM Device e WHERE e.dname='"+dname+"'");
		if(q.getResultList().size()>0){
			return true;
			
		}else{
			return false;
			
		}
		
		
	}

	
	public boolean containUser(String uname){
		//boolean exits=false;

		Query q=em.createQuery("SELECT e FROM User e WHERE e.uname='"+uname+"'");
		if(q.getResultList().size()>0){
			return true;
			
		}else{
			return false;
			
		}
		
		
	}
	
	
	public boolean Login(String uname,String pass){
		//boolean exits=false;

		Query q=em.createQuery("SELECT e FROM User e WHERE e.uname='"+uname+"' AND e.pass='"+pass+"'");
		if(q.getResultList().size()>0){
			return true;
			
		}else{
			return false;
			
		}
		
		
	}
	
	

}
