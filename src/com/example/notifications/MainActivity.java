package com.example.notifications;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.combiningaps.R;

public class MainActivity extends Activity {
	
	public static final String IP="com.example.Notifications.IP";
	public String ip;
	
	@SuppressWarnings("deprecation")
	public void showAlertDialog(Context context, String title, String message, Boolean status) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
 
        // Setting Dialog Title
        alertDialog.setTitle(title);
 
        // Setting Dialog Message
        alertDialog.setMessage(message);
         
        // Setting alert dialog icon
        //alertDialog.setIcon((status) ? R.drawable.success : R.drawable.fail);
 
        // Setting OK Button
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            @Override
			public void onClick(DialogInterface dialog, int which) {
            }
        });
 
        // Showing Alert Message
        alertDialog.show();
    }

	Button btnGetNotifications;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.notifications_activity_main);
		Intent intent=getIntent();
		btnGetNotifications=(Button)findViewById(R.id.button1);
		
		btnGetNotifications.setOnClickListener(new View.OnClickListener() {
			 
            @Override
            public void onClick(View view) {
            	
            	ConnectionDetector cd = new ConnectionDetector(getApplicationContext());
            	 
            	Boolean isInternetPresent = cd.isConnectingToInternet();
            	
            	if(isInternetPresent){
            	EditText edtxtIp=(EditText)findViewById(R.id.editText1);
            	String  ip=edtxtIp.getText().toString();
                Intent i = new Intent(getApplicationContext(), GetNotifications.class);
                
                i.putExtra(IP, ip);
                startActivity(i);
            	}
            	else{
            		showAlertDialog(MainActivity.this,"No internet connection","Please connect to internt",false);
            		
            		
            		
            	}
 
            }
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.layout.menu, menu);
		return true;
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item){
         
        switch (item.getItemId())
        {
        case R.id.setip:
        	return true;
 
        default:
            return super.onOptionsItemSelected(item);
        }
		
    } 

}
