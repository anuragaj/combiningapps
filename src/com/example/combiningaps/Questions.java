package com.example.combiningaps;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Toast;

public class Questions extends Activity {
	
	private WebView webView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_questions);
		Intent i=getIntent();
		
		final CharSequence sem[] = new CharSequence[6];
		sem[0]="3";
		sem[1]="4";
		sem[2]="5";
		sem[3]="6";
		sem[4]="7";
		sem[5]="8";
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	builder.setTitle("Choose the lab");
    	builder.setItems(sem, new DialogInterface.OnClickListener() {
    	    @Override
    	    public void onClick(DialogInterface dialog, int which) {
    	    	Toast.makeText(Questions.this, sem[which], Toast.LENGTH_LONG).show();
    	    		
    	    	/*webView = (WebView) findViewById(R.id.webViewquestions);
    			webView.getSettings().setJavaScriptEnabled(true);
    			webView.loadUrl(file:///labprograms);*/
    	       
    	    }
    	});
    	builder.show();
    	
    	
	}


}
