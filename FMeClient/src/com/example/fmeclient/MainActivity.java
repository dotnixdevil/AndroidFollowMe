package com.example.fmeclient;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.DataSetObserver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.SpinnerAdapter;
import android.widget.Toast;


@SuppressLint("NewApi")
public class MainActivity extends Activity  {

	@Override
	@Deprecated
	protected Dialog onCreateDialog(int id) {
		AlertDialog.Builder b=new AlertDialog.Builder(this);
	    
	
		String n[]=new String[]{"Create New User "	,"Login"};
		
		android.content.DialogInterface.OnClickListener docl=new android.content.DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				switch (which) {
				case 0:
					startActivity(new Intent(getBaseContext(),BRigister.class));
					break;
					case 1:
						startActivity(new Intent(getBaseContext(),Login.class));
						
						break;
						

				default:
					break;
				}
				// TODO Auto-generated method stub
				
			}
		};
		b.setItems(n, docl);
		
		
	    
		// TODO Auto-generated method stub
		return b.create();
				}

	
	
	
	public boolean isNetworkAvailable() {
	    ConnectivityManager cm = (ConnectivityManager) 
	      getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo networkInfo = cm.getActiveNetworkInfo();
	    // if no network is available networkInfo will be null
	    // otherwise check if we are connected
	    if (networkInfo != null && networkInfo.isConnected()) {
	        return true;
	    }
	    return false;
	}
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final  SharedPreferences sp=getSharedPreferences("followme",MODE_PRIVATE);
       
     //   sp.edit().putBoolean("isclogin", false).commit();
     //   sp.edit().putBoolean("isblogin", false).commit();
        
   //     Bitmap b=(Bitmap)getResources().getDrawable(R.drawable.ic_boss);
     //   setWallpaper(b);
        
        
     //   
       //setWallpaper(new BitmapDrawable(getResources().getDrawable(R.drawable.ic_emp)));
      Toast.makeText(this, ""+isNetworkAvailable(), Toast.LENGTH_SHORT).show();
       
        Button boss=(Button)findViewById(R.id.button1);
        Button client=(Button)findViewById(R.id.button2);
        boss.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(sp.getBoolean("isblogin", false)){
					
					startActivity(new Intent(getBaseContext(),Cpanel.class));
					
					
				}else{


					showDialog(0);
					
				}
				
			}
		});
        client.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
	         if(sp.getBoolean("isclogin", false)){
					
					startActivity(new Intent(getBaseContext(),Cpanel.class));
					
					
				}else{
					startActivity(new Intent(getBaseContext(),CRigister.class));
					
					
				}
				// TODO Auto-generated method stub
				
			}
		});
        
        
        

        
        
        

        
        
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	// TODO Auto-generated method stub
    	
    	MenuItem mi=menu.add("About");
    	mi.setIcon(getResources().getDrawable(R.drawable.ic_info));
    	return super.onCreateOptionsMenu(menu);
    }


}
