package com.example.addnotificationdifferent;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.combiningaps.R;

public class MainActivity extends Activity {

	
	public static final String IP="com.example.AddNotification.IP";
	public static final String SUB="com.example.AddNotification.SUB";
	public static final String CONTENT="com.example.AddNotification.CONTENT";
	public static final String PC="com.example.AddNotification.PC";
	Button btnAddNotification;
	public String pc;
	public String ip;
	public String sub;
	public String content;
	
	public void displayToast(String s){
		 Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
	 }
	
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
		setContentView(R.layout.add_notification_activity_main);
		Intent intent=getIntent();
		String current_ip = intent.getStringExtra(com.example.combiningaps.MainActivity.IP);
		ip=current_ip;
		

    	EditText edtxtIp=(EditText)findViewById(R.id.editText3);
    	//ip=edtxtIp.getText().toString();
    	edtxtIp.setText(ip);
btnAddNotification=(Button)findViewById(R.id.button1);
		
		btnAddNotification.setOnClickListener(new View.OnClickListener() {
			 
            @Override
            public void onClick(View view) {
            	//displayToast("Within");
            
            	ConnectionDetector cd = new ConnectionDetector(getApplicationContext());
            	 
            	Boolean isInternetPresent = cd.isConnectingToInternet();
            	
            	if(isInternetPresent){
            	
            	
            	
            	EditText edtxts=(EditText)findViewById(R.id.editText1);
            	sub=edtxts.getText().toString();
            	
            	EditText edtxtc=(EditText)findViewById(R.id.editText2);
            	content=edtxtc.getText().toString();
            	//displayToast(ip+"  "+sub+"  "+content);
            	//display input alert dialog
            	AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        		builder.setTitle("ENTER PASSCODE");

        		// Set up the input
        		final EditText input = new EditText(MainActivity.this);
        		// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        		input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        		builder.setView(input);

        		// Set up the buttons
        		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() { 
        		    @Override
        		    public void onClick(DialogInterface dialog, int which) {
        		        pc = input.getText().toString();
        		        Intent i = new Intent(getApplicationContext(),AddNotificationDifferent.class);
                        
                        i.putExtra(IP, ip);
                        i.putExtra(PC, pc);
                        i.putExtra(SUB, sub);
                        i.putExtra(CONTENT, content);
                        startActivity(i);
        	
        		    }
        		});
        		builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
        		    @Override
        		    public void onClick(DialogInterface dialog, int which) {
        		        dialog.cancel();
        		    }
        		});

        		builder.show();
            	}
            	else{
            		showAlertDialog(MainActivity.this,"No internet connection","Please connect to internt",false);
            		
            		
            		
            	}
 
            }
        });
                
	}

}
