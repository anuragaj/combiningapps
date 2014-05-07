package com.example.changepassccode;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.combiningaps.R;

public class MainActivity extends Activity {
	
	public static final String IP="com.example.changepasscode.IP";
	public static final String NP="com.example.changepasscode.NP";
	public static final String OP="com.example.changepasscode.OP";
	
	Button change;
	String ip,op,np,vp;

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
		setContentView(R.layout.changepasscode_activity_main);
		
		Intent intent=getIntent();
		ip= intent.getStringExtra(com.example.combiningaps.MainActivity.IP);
		
		EditText edtxtIp=(EditText)findViewById(R.id.editText4);
		edtxtIp.setText(ip);
		
		change=(Button)findViewById(R.id.button1);
		change.setOnClickListener(new View.OnClickListener() {
			 
            @Override
            public void onClick(View view) {
            	
            	ConnectionDetector cd = new ConnectionDetector(getApplicationContext());
            	 
            	Boolean isInternetPresent = cd.isConnectingToInternet();
            	
            	if(isInternetPresent){
                Intent i = new Intent(getApplicationContext(), com.example.changepassccode.Change.class);
            		//showAlertDialog(MainActivity.this,"No internet connection","Please connect to internt",false);
                
                EditText edtxtop=(EditText)findViewById(R.id.editText1);
                EditText edtxtnp=(EditText)findViewById(R.id.editText2);
                EditText edtxtvp=(EditText)findViewById(R.id.editText3);
               // EditText edtxtIp=(EditText)findViewById(R.id.editText4);
                 np=edtxtnp.getText().toString();
                 vp=edtxtvp.getText().toString();
                 op=edtxtop.getText().toString();
                 //ip=edtxtIp.getText().toString();
                 
                 if(np.equals(vp)){
                	 i.putExtra(IP, ip);
                	 i.putExtra(OP, op);
                	 i.putExtra(NP, np);
                	 startActivity(i);
                 }
                 else
                	 showAlertDialog(MainActivity.this,"NOT MATCH","NEW AND VERIFY PASSCODE DO NOT MATCH",false);
            	}
            	else
            		showAlertDialog(MainActivity.this,"No internet connection","Please connect to internt",false);
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
