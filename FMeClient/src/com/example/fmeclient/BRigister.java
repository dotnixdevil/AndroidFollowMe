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
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

public class BRigister extends Activity implements OnClickListener{
	EditText uname;
	EditText pass;
	EditText repass;
	EditText email;
	Button register;
	SharedPreferences sp;
	
	public void onClick(View v) {
		// TODO Auto-generated method stub
		try{
		if(!uname.getText().toString().equals("")&&!pass.getText().toString().equals("")&&!email.getText().toString().equals("")&&!repass.getText().toString().equals("")){
			if(pass.getText().toString().equals(repass.getText().toString())){
		   
				WebserviceCaller wcc=new WebserviceCaller();
			    GCMRegistrar.checkDevice(this);
			    GCMRegistrar.checkManifest(this);
			     String regId = GCMRegistrar.getRegistrationId(this);
			    if (regId.equals("")) {
			      GCMRegistrar.register(this, "370976257728");
			      regId = GCMRegistrar.getRegistrationId(this);
			      sp.edit().putString("brid", regId).commit();
			      
			    } else {
			      Log.v("info", "Already registered");
			      sp.edit().putString("brid", regId).commit();
			    }
				String json=wcc.addNewUser(uname.getText().toString(), pass.getText().toString(), email.getText().toString(),regId);
				
				JSONObject jo=new JSONObject(json);
				if(jo.getString("code").equals("1")){
				
			    Toast.makeText(this,jo.getString("msg"),Toast.LENGTH_SHORT).show();
			    sp.edit().putBoolean("isblogin", true).commit();
			    sp.edit().putString("uname", uname.getText().toString()).commit();
			    
			    
			    startActivity(new Intent(this,Cpanel.class));
			    
			
				}else{
					Toast.makeText(this, jo.getString("msg"), Toast.LENGTH_SHORT).show();
					
					
					
				}
			}else{
				Toast.makeText(this, "please enter the same password", Toast.LENGTH_SHORT).show();
				
			}
		}else{
			Toast.makeText(this, "please fill all the fields", Toast.LENGTH_SHORT).show();
			
		}}catch(Exception e){
			e.printStackTrace();
			
		}
	}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brigister);
        
        uname =(EditText)findViewById(R.id.editText1);
        pass =(EditText)findViewById(R.id.editText2);
        repass=(EditText)findViewById(R.id.editText3);
        email=(EditText)findViewById(R.id.editText4);
        register=(Button)findViewById(R.id.button1);
        sp=getSharedPreferences("followme",MODE_PRIVATE);;
        register.setOnClickListener(this);
       
       
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_brigister, menu);
        return true;
    }
}
