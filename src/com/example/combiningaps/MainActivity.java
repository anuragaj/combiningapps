package com.example.combiningaps;

import com.example.attendance.ConnectionDetector;
import com.example.attendance.GetStudentList;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends ListActivity {
	
	public static final String IP="com.example.combiningapps.IP";
	public String ip=null;
	
	static final String[] OPTIONS = 
            new String[] { "GET NOTIFICATIONS", "ADD NOTIFICATIONS", "TAKE ATTENDANCE", "LAB PROGRAMS","PREVIOUS YEAR QUESTIONS","CHANGE PASSCODE"};


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
		//setContentView(R.layout.activity_main);
		
		setListAdapter(new LoadOptions(this, OPTIONS));
		
	}
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
 
		//get selected items
		//String selectedValue = (String) getListAdapter().getItem(position);
		//Toast.makeText(this, position+"", Toast.LENGTH_SHORT).show();//startActivity(i);
		com.example.attendance.ConnectionDetector cd = new ConnectionDetector(getApplicationContext());
   	 
    	Boolean isInternetPresent = cd.isConnectingToInternet();
    	
    	if(isInternetPresent || position == 3 || position == 4){
    		Intent i=null;
    		if(ip != null && ! ip.isEmpty() ||  position == 3 || position == 4 ){
    		switch(position){
    		case 0:i=new Intent(this, com.example.notifications.GetNotifications.class);
    			break;
    		case 1:i=new Intent(this, com.example.addnotificationdifferent.MainActivity.class);
    			break;
    		case 2:i=new Intent(this,com.example.attendance.MainActivity.class);
    			break;
    		case 3:i=new Intent(this,com.example.combiningaps.LabPrograms.class);
    			break;
    		case 4:i=new Intent(this,com.example.combiningaps.Questions.class);
    			break;
    		case 5:i=new Intent(this,com.example.changepassccode.MainActivity.class);
    			break;
    			default:
    				break;
    				//nothing
    			
    		}
    		 i.putExtra(IP, ip);
    		startActivity(i);
    		}
    		else
    			showAlertDialog(MainActivity.this,"SET IP","Please set the ip first",false);	
    	}
    	else
    		showAlertDialog(MainActivity.this,"No internet connection","Please connect to internt",false);
    	

 
	}
	@Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.layout.menu, menu);
        return true;
    }
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item){
         
        switch (item.getItemId())
        {
        case R.id.setip:AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("IP");

     // Set up the input
     final EditText input = new EditText(this);
     // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
     input.setInputType(InputType.TYPE_CLASS_TEXT);
     builder.setView(input);

     // Set up the buttons
     builder.setPositiveButton("OK", new DialogInterface.OnClickListener() { 
         @Override
         public void onClick(DialogInterface dialog, int which) {
             ip = input.getText().toString();
         }
     });
     builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
         @Override
         public void onClick(DialogInterface dialog, int which) {
             dialog.cancel();
         }
     });

     builder.show();
        	return true;
        	default:
            return super.onOptionsItemSelected(item);
        }
		
    }

}
