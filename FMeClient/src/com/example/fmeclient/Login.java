package com.example.fmeclient;

import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity implements OnClickListener{
	EditText uname;
	EditText pass;
    Button login;
    SharedPreferences sp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        uname=(EditText)findViewById(R.id.editText1);
        pass=(EditText)findViewById(R.id.editText2);
        login=(Button)findViewById(R.id.button1);
        login.setOnClickListener(this);
        
        sp=getSharedPreferences("followme",MODE_PRIVATE);
    }
    public void onClick(View v) {
    	WebserviceCaller ws=new WebserviceCaller();
    	try{
    	if(uname.getText().toString().equals("")||pass.getText().equals("")){
    		Toast.makeText(this, "please fill all the fields ", Toast.LENGTH_SHORT).show();
    		
    	}else{
			String json=ws.Login(uname.getText().toString(), pass.getText().toString());
			
			JSONObject jo=new JSONObject(json);
			if(jo.getString("code").equals("1")){
			
		    Toast.makeText(this,jo.getString("msg"),Toast.LENGTH_SHORT).show();
		    sp.edit().putBoolean("isblogin", true).commit();
		    startActivity(new Intent(this,Cpanel.class));
		    
		
			}else{
				Toast.makeText(this, jo.getString("msg"), Toast.LENGTH_SHORT).show();
				
				
				
			}
    		
    		
    	}
    }catch(Exception e){
    	e.printStackTrace();
    	
    }
    	// TODO Auto-generated method stub
    	
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_login, menu);
        return true;
    }
}
