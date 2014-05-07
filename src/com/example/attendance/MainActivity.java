package com.example.attendance;

import android.app.Activity;
import com.example.combiningaps.R;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends Activity {
	Button sem3a;
	Button sem3b;
	Button sem4a;
	Button sem4b;
	Button sem5a;
	Button sem5b;
	Button sem6a;
	Button sem6b;
	Button sem7a;
	Button sem7b;
	Button sem8a;
	Button sem8b;
	
	public static final String IP="com.example.Attendance.IP";
	public String ip;
	
	public static final String SEM="com.example.Notifications.SEM";
	public String sem;
	
	public static final String SEC="com.example.Notifications.SEC";
	public String sec;
	
	
	
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.attendance_activity_main);
		
		Intent intent=getIntent();
		ip= intent.getStringExtra(com.example.combiningaps.MainActivity.IP);
		EditText edtxtIp=(EditText)findViewById(R.id.editText1);
        edtxtIp.setText(ip);
		
		
		sem3a=(Button)findViewById(R.id.sem3a);
		sem3a.setOnClickListener(new View.OnClickListener() {
			 
            @Override
            public void onClick(View view) {
            	
            	ConnectionDetector cd = new ConnectionDetector(getApplicationContext());
            	 
            	Boolean isInternetPresent = cd.isConnectingToInternet();
            	
            	if(isInternetPresent){
                Intent i = new Intent(getApplicationContext(), GetStudentList.class);
            		//showAlertDialog(MainActivity.this,"No internet connection","Please connect to internt",false);
                
                //EditText edtxtIp=(EditText)findViewById(R.id.editText1);
                //edtxtIp.setText(ip);
            	//ip=edtxtIp.getText().toString();
            	
                sem="3";
                sec="A";
                i.putExtra(IP, ip);
                i.putExtra(SEM, sem);
                i.putExtra(SEC,sec);
                startActivity(i);
            	}
            	else{
            		showAlertDialog(MainActivity.this,"No internet connection","Please connect to internt",false);
            		
            		
            		
            	}
 
            }
        });
		
		sem3b=(Button)findViewById(R.id.sem3b);
		sem3b.setOnClickListener(new View.OnClickListener() {
			 
            @Override
            public void onClick(View view) {
            	
            	ConnectionDetector cd = new ConnectionDetector(getApplicationContext());
            	 
            	Boolean isInternetPresent = cd.isConnectingToInternet();
            	
            	if(isInternetPresent){
            		Intent i = new Intent(getApplicationContext(), GetStudentList.class);
            		//showAlertDialog(MainActivity.this,"No internet connection","Please connect to internt",false);
                
                //EditText edtxtIp=(EditText)findViewById(R.id.editText1);
                //edtxtIp.setText(ip);
            	//ip=edtxtIp.getText().toString();
            	
                sem="3";
                sec="B";
                i.putExtra(IP, ip);
                i.putExtra(SEM, sem);
                i.putExtra(SEC,sec);
                startActivity(i);
            	}
            	else{
            		showAlertDialog(MainActivity.this,"No internet connection","Please connect to internt",false);
            		
            		
            		
            	}
 
            }
        });
		
		sem4a=(Button)findViewById(R.id.sem4a);
		sem4a.setOnClickListener(new View.OnClickListener() {
			 
            @Override
            public void onClick(View view) {
            	
            	ConnectionDetector cd = new ConnectionDetector(getApplicationContext());
            	 
            	Boolean isInternetPresent = cd.isConnectingToInternet();
            	
            	if(isInternetPresent){
            		Intent i = new Intent(getApplicationContext(), GetStudentList.class);
            		//showAlertDialog(MainActivity.this,"No internet connection","Please connect to internt",false);
                
               // EditText edtxtIp=(EditText)findViewById(R.id.editText1);
                //edtxtIp.setText(ip);
            	//ip=edtxtIp.getText().toString();
            	
                sem="4";
                sec="A";
                i.putExtra(IP, ip);
                i.putExtra(SEM, sem);
                i.putExtra(SEC,sec);
                startActivity(i);
            	}
            	else{
            		showAlertDialog(MainActivity.this,"No internet connection","Please connect to internt",false);
            		
            		
            		
            	}
 
            }
        });

		sem4b=(Button)findViewById(R.id.sem4b);
		sem4b.setOnClickListener(new View.OnClickListener() {
			 
            @Override
            public void onClick(View view) {
            	
            	ConnectionDetector cd = new ConnectionDetector(getApplicationContext());
            	 
            	Boolean isInternetPresent = cd.isConnectingToInternet();
            	
            	if(isInternetPresent){
            		Intent i = new Intent(getApplicationContext(), GetStudentList.class);
            		//showAlertDialog(MainActivity.this,"No internet connection","Please connect to internt",false);
                
                //EditText edtxtIp=(EditText)findViewById(R.id.editText1);
                //edtxtIp.setText(ip);
            	//ip=edtxtIp.getText().toString();
            	
                sem="4";
                sec="B";
                i.putExtra(IP, ip);
                i.putExtra(SEM, sem);
                i.putExtra(SEC,sec);
                startActivity(i);
            	}
            	else{
            		showAlertDialog(MainActivity.this,"No internet connection","Please connect to internt",false);
            		
            		
            		
            	}
 
            }
        });

		sem5a=(Button)findViewById(R.id.sem5a);
		sem5a.setOnClickListener(new View.OnClickListener() {
			 
            @Override
            public void onClick(View view) {
            	
            	ConnectionDetector cd = new ConnectionDetector(getApplicationContext());
            	 
            	Boolean isInternetPresent = cd.isConnectingToInternet();
            	
            	if(isInternetPresent){
            		Intent i = new Intent(getApplicationContext(), GetStudentList.class);
            		//showAlertDialog(MainActivity.this,"No internet connection","Please connect to internt",false);
                
                //EditText edtxtIp=(EditText)findViewById(R.id.editText1);
                //edtxtIp.setText(ip);
            	//ip=edtxtIp.getText().toString();
            	
                sem="5";
                sec="A";
                i.putExtra(IP, ip);
                i.putExtra(SEM, sem);
                i.putExtra(SEC,sec);
                startActivity(i);
            	}
            	else{
            		showAlertDialog(MainActivity.this,"No internet connection","Please connect to internt",false);
            		
            		
            		
            	}
 
            }
        });

		sem5b=(Button)findViewById(R.id.sem5b);
		sem5b.setOnClickListener(new View.OnClickListener() {
			 
            @Override
            public void onClick(View view) {
            	
            	ConnectionDetector cd = new ConnectionDetector(getApplicationContext());
            	 
            	Boolean isInternetPresent = cd.isConnectingToInternet();
            	
            	if(isInternetPresent){
            		Intent i = new Intent(getApplicationContext(), GetStudentList.class);
            		//showAlertDialog(MainActivity.this,"No internet connection","Please connect to internt",false);
                
                //EditText edtxtIp=(EditText)findViewById(R.id.editText1);
                //edtxtIp.setText(ip);
            	//ip=edtxtIp.getText().toString();
            	
                sem="5";
                sec="B";
                i.putExtra(IP, ip);
                i.putExtra(SEM, sem);
                i.putExtra(SEC,sec);
                startActivity(i);
            	}
            	else{
            		showAlertDialog(MainActivity.this,"No internet connection","Please connect to internt",false);
            		
            		
            		
            	}
 
            }
        });
	
		sem6a=(Button)findViewById(R.id.sem6a);
		sem6a.setOnClickListener(new View.OnClickListener() {
			 
            @Override
            public void onClick(View view) {
            	
            	ConnectionDetector cd = new ConnectionDetector(getApplicationContext());
            	 
            	Boolean isInternetPresent = cd.isConnectingToInternet();
            	
            	if(isInternetPresent){
            		Intent i = new Intent(getApplicationContext(), GetStudentList.class);
            		//showAlertDialog(MainActivity.this,"No internet connection","Please connect to internt",false);
                
               // EditText edtxtIp=(EditText)findViewById(R.id.editText1);
               // edtxtIp.setText(ip);
            	//ip=edtxtIp.getText().toString();
            	
                sem="6";
                sec="A";
                i.putExtra(IP, ip);
                i.putExtra(SEM, sem);
                i.putExtra(SEC,sec);
                startActivity(i);
            	}
            	else{
            		showAlertDialog(MainActivity.this,"No internet connection","Please connect to internt",false);
            		
            		
            		
            	}
 
            }
        });
		
		sem6b=(Button)findViewById(R.id.sem6b);
		sem6b.setOnClickListener(new View.OnClickListener() {
			 
            @Override
            public void onClick(View view) {
            	
            	ConnectionDetector cd = new ConnectionDetector(getApplicationContext());
            	 
            	Boolean isInternetPresent = cd.isConnectingToInternet();
            	
            	if(isInternetPresent){
            		Intent i = new Intent(getApplicationContext(), GetStudentList.class);
            		//showAlertDialog(MainActivity.this,"No internet connection","Please connect to internt",false);
                
               // EditText edtxtIp=(EditText)findViewById(R.id.editText1);
                //edtxtIp.setText(ip);
            	//ip=edtxtIp.getText().toString();
            	
                sem="6";
                sec="B";
                i.putExtra(IP, ip);
                i.putExtra(SEM, sem);
                i.putExtra(SEC,sec);
                startActivity(i);
            	}
            	else{
            		showAlertDialog(MainActivity.this,"No internet connection","Please connect to internt",false);
            		
            		
            		
            	}
 
            }
        });
		
		sem7a=(Button)findViewById(R.id.sem7a);
		sem7a.setOnClickListener(new View.OnClickListener() {
			 
            @Override
            public void onClick(View view) {
            	
            	ConnectionDetector cd = new ConnectionDetector(getApplicationContext());
            	 
            	Boolean isInternetPresent = cd.isConnectingToInternet();
            	
            	if(isInternetPresent){
            		Intent i = new Intent(getApplicationContext(), GetStudentList.class);
            		//showAlertDialog(MainActivity.this,"No internet connection","Please connect to internt",false);
                
               // EditText edtxtIp=(EditText)findViewById(R.id.editText1);
               // edtxtIp.setText(ip);
            	//ip=edtxtIp.getText().toString();
            	
                sem="7";
                sec="A";
                i.putExtra(IP, ip);
                i.putExtra(SEM, sem);
                i.putExtra(SEC,sec);
                startActivity(i);
            	}
            	else{
            		showAlertDialog(MainActivity.this,"No internet connection","Please connect to internt",false);
            		
            		
            		
            	}
 
            }
        });
		
		sem7b=(Button)findViewById(R.id.sem7b);
		sem7b.setOnClickListener(new View.OnClickListener() {
			 
            @Override
            public void onClick(View view) {
            	
            	ConnectionDetector cd = new ConnectionDetector(getApplicationContext());
            	 
            	Boolean isInternetPresent = cd.isConnectingToInternet();
            	
            	if(isInternetPresent){
            		Intent i = new Intent(getApplicationContext(), GetStudentList.class);
            		//showAlertDialog(MainActivity.this,"No internet connection","Please connect to internt",false);
                
               // EditText edtxtIp=(EditText)findViewById(R.id.editText1);
               // edtxtIp.setText(ip);
            	//ip=edtxtIp.getText().toString();
            	
                sem="7";
                sec="B";
                i.putExtra(IP, ip);
                i.putExtra(SEM, sem);
                i.putExtra(SEC,sec);
                startActivity(i);
            	}
            	else{
            		showAlertDialog(MainActivity.this,"No internet connection","Please connect to internt",false);
            		
            		
            		
            	}
 
            }
        });
		
		sem8a=(Button)findViewById(R.id.sem8a);
		sem8a.setOnClickListener(new View.OnClickListener() {
			 
            @Override
            public void onClick(View view) {
            	
            	ConnectionDetector cd = new ConnectionDetector(getApplicationContext());
            	 
            	Boolean isInternetPresent = cd.isConnectingToInternet();
            	
            	if(isInternetPresent){
            		Intent i = new Intent(getApplicationContext(), GetStudentList.class);
            		//showAlertDialog(MainActivity.this,"No internet connection","Please connect to internt",false);
                
                //EditText edtxtIp=(EditText)findViewById(R.id.editText1);
                //edtxtIp.setText(ip);
            	//ip=edtxtIp.getText().toString();
            	
                sem="8";
                sec="A";
                i.putExtra(IP, ip);
                i.putExtra(SEM, sem);
                i.putExtra(SEC,sec);
                startActivity(i);
            	}
            	else{
            		showAlertDialog(MainActivity.this,"No internet connection","Please connect to internt",false);
            		
            		
            		
            	}
 
            }
        });
		
		sem8b=(Button)findViewById(R.id.sem8b);
		sem8b.setOnClickListener(new View.OnClickListener() {
			 
            @Override
            public void onClick(View view) {
            	
            	ConnectionDetector cd = new ConnectionDetector(getApplicationContext());
            	 
            	Boolean isInternetPresent = cd.isConnectingToInternet();
            	
            	if(isInternetPresent){
            		Intent i = new Intent(getApplicationContext(), GetStudentList.class);
            		//showAlertDialog(MainActivity.this,"No internet connection","Please connect to internt",false);
                
                //EditText edtxtIp=(EditText)findViewById(R.id.editText1);
                //edtxtIp.setText(ip);
            	//ip=edtxtIp.getText().toString();
            	
                sem="8";
                sec="B";
                i.putExtra(IP, ip);
                i.putExtra(SEM, sem);
                i.putExtra(SEC,sec);
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
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
