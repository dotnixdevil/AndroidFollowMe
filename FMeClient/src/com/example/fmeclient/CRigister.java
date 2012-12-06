package com.example.fmeclient;

import org.json.JSONObject;

import com.google.android.gcm.GCMRegistrar;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CRigister extends Activity implements OnClickListener{
	
	
	EditText bossname;
	EditText clientname;
	Button Register;
	
	SharedPreferences sp;
	

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crigister);
        bossname=(EditText)findViewById(R.id.editText1);
        clientname=(EditText)findViewById(R.id.editText2);
        Register=(Button)findViewById(R.id.button1);
        sp=getSharedPreferences("followme",MODE_PRIVATE);
        Register.setOnClickListener(this);
        
        
    }


    
    public void onClick(View v) {
    	
    	
    	
		try{
		if(!bossname.getText().toString().equals("")&&!clientname.getText().toString().equals("")){
			
		   
				WebserviceCaller wcc=new WebserviceCaller();
			    GCMRegistrar.checkDevice(this);
			    GCMRegistrar.checkManifest(this);
			     String regId = GCMRegistrar.getRegistrationId(this);
			    if (regId.equals("")) {
			      GCMRegistrar.register(this, "370976257728");
			      regId = GCMRegistrar.getRegistrationId(this);
			      sp.edit().putString("crid", regId).commit();
			      
			    } else {
			      Log.v("info", "Already registered");
			      sp.edit().putString("crid", regId).commit();
			    }
			   // regId = GCMRegistrar.getRegistrationId(this);
			    //Toast.makeText(this, regId, Toast.LENGTH_SHORT);
			    
				String json=wcc.addNewDeice(regId, bossname.getText().toString(), clientname.getText().toString());
				
				JSONObject jo=new JSONObject(json);
				if(jo.getString("code").equals("1")){
				
			    Toast.makeText(this,jo.getString("msg"),Toast.LENGTH_SHORT).show();

			    sp.edit().putBoolean("isclogin", true).commit();
			    startActivity(new Intent(this,Followpanel.class));
			    
			
				}else{
					Toast.makeText(this, jo.getString("msg"), Toast.LENGTH_SHORT).show();
					
					
					
				}
			
				
			
		}else{
			Toast.makeText(this, "please fill all the fields", Toast.LENGTH_SHORT).show();
			
		}}catch(Exception e){
			e.printStackTrace();
			
		}
    	
    }
}
