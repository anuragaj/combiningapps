package com.example.changepassccode;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.webkit.WebView;
import com.example.combiningaps.R;

public class Change extends Activity {
	
	public String ip,op,np,url_change;
	
	WebView webView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_change);
		Intent intent = getIntent();
		ip = intent.getStringExtra(com.example.changepassccode.MainActivity.IP);
		op=intent.getStringExtra(com.example.changepassccode.MainActivity.OP);
		np=intent.getStringExtra(com.example.changepassccode.MainActivity.NP);
		
		url_change = "http://"+ip+"/attendance/changePasscode.php?op="+op+"&np="+np;
		
		webView = (WebView) findViewById(R.id.webViewC);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.loadUrl(url_change);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.change, menu);
		return true;
	}

}
