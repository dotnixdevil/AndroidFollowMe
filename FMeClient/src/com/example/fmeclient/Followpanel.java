package com.example.fmeclient;

import android.os.Bundle;
import android.app.Activity;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuItem;

public class Followpanel extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followpanel);
    }

    
    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
    	// TODO Auto-generated method stub
    	if(item.getTitle().equals("Logout")){
    		SharedPreferences sp=getSharedPreferences("followme",MODE_PRIVATE);
    		sp.edit().putBoolean("isclogin", false);
    		
    	}
    	return super.onMenuItemSelected(featureId, item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
  
    	MenuItem logout=menu.add("Logout");
    	
    	
        return true;
    }
}
