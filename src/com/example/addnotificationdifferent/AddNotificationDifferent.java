package com.example.addnotificationdifferent;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import com.example.combiningaps.R;
public class AddNotificationDifferent extends Activity {

	public String current_ip;
	public String passcode;
	public String url_all_students;
    public String s,c,pc;
    
    private WebView webView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_notification_different);
		
		Intent intent = getIntent();
		current_ip = intent.getStringExtra(MainActivity.IP);
		s=intent.getStringExtra(MainActivity.SUB);
		c=intent.getStringExtra(MainActivity.CONTENT);
		pc=intent.getStringExtra(MainActivity.PC);
		
		url_all_students = "http://"+current_ip+"/attendance/addNotificationMobile.php?s="+s+"&c="+c+"&pc="+pc;
		
		webView = (WebView) findViewById(R.id.webView1);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.loadUrl(url_all_students);
	}

}
