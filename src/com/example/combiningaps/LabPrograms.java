package com.example.combiningaps;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.webkit.WebView;
import android.widget.Toast;

public class LabPrograms extends Activity {
	
	private WebView webView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lab_programs);
		Intent i=getIntent();
		
		final CharSequence subjects[] = new CharSequence[10];
		subjects[0]="DSC WITH C/C++";
		subjects[1]="EC AND LD";
		subjects[2]="ADA";
		subjects[3]="MP";
		subjects[4]="DBA";
		subjects[5]="SS AND OS";
		subjects[6]="CG";
		subjects[7]="USP AND CD";
		subjects[8]="NL";
		subjects[9]="WPL";
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	builder.setTitle("Choose the lab");
    	builder.setItems(subjects, new DialogInterface.OnClickListener() {
    	    @Override
    	    public void onClick(DialogInterface dialog, int which) {
    	    	Toast.makeText(LabPrograms.this, subjects[which], Toast.LENGTH_LONG).show();

    	    	/*webView = (WebView) findViewById(R.id.webViewlabprograms);
    			webView.getSettings().setJavaScriptEnabled(true);
    			webView.loadUrl(file:///labprograms);*/
    	       
    	    }
    	});
    	builder.show();
		
		
	}


}
