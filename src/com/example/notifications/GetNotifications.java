package com.example.notifications;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.combiningaps.R;

public class GetNotifications extends ListActivity {
	
	private ProgressDialog pDialog;
	 
    JSONParser jParser = new JSONParser();
 
    ArrayList<HashMap<String, String>> notificationList;
  
    public String url_all_notifications;
    
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_NOTIFICATIONS = "notifications";
    private static final String TAG_SUBJECT= "subject";
    private static final String TAG_CONTENT = "content";
    private static final String TAG_TIME="time";
 
    JSONArray notifications = null;
    
    public void displayToast(String s){
		 Toast.makeText(this, s, Toast.LENGTH_LONG).show();
	 }

    public void displayAlert(String s){
    	new AlertDialog.Builder(this)
        .setTitle("CONTENT")
        .setMessage(s)
        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
			public void onClick(DialogInterface dialog, int which) { 
                // continue with delete
            }
         })
         .show();
    	
    }
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_get_notifications);
		
		Intent intent = getIntent();
		String current_ip = intent.getStringExtra(com.example.combiningaps.MainActivity.IP);
		
		url_all_notifications = "http://"+current_ip+"/mysqlTest/getNotifications.php";
		
		notificationList = new ArrayList<HashMap<String, String>>();
		
        new LoadAllNotifications().execute();
		
		 ListView lv = getListView();
		 
		 lv.setOnItemClickListener(new OnItemClickListener() {
		 @Override
         public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
       
             String su = ((TextView) view.findViewById(R.id.content)).getText()
                     .toString();
             
             displayAlert(su);
		 }
     });
		 }
	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // if result code 100
        if (resultCode == 100) {
            // if result code 100 is received
            // means user edited/deleted product
            // reload this screen again
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }
 
    }
	class LoadAllNotifications extends AsyncTask<String, String, String> {
		 
        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(GetNotifications.this);
            pDialog.setMessage("Loading notifications. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }
        
        @Override
		protected String doInBackground(String... args) {
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            // getting JSON string from URL
            JSONObject json = jParser.makeHttpRequest(url_all_notifications, "GET", params);
 
            // Check your log cat for JSON reponse
            Log.d("All Notifications: ", json.toString());
 
            try {
                // Checking for SUCCESS TAG
                int success = json.getInt(TAG_SUCCESS);
 
                if (success == 1) {
                    notifications = json.getJSONArray(TAG_NOTIFICATIONS);
 
                    // looping through All Products
                    for (int i = 0; i < notifications.length(); i++) {
                        JSONObject c =notifications.getJSONObject(i);
 
                        // Storing each json item in variable
                        String subject = c.getString(TAG_SUBJECT);
                        String content = c.getString(TAG_CONTENT);
                        String time = c.getString(TAG_TIME);
 
                        // creating new HashMap
                        HashMap<String, String> map = new HashMap<String, String>();
 
                        // adding each child node to HashMap key => value
                        map.put(TAG_SUBJECT, subject);
                        map.put(TAG_CONTENT,content);
                        map.put(TAG_TIME, time);
 
                        // adding HashList to ArrayList
                        notificationList.add(map);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
 
            return null;
        }
 
        /**
         * After completing background task Dismiss the progress dialog
         * **/
        @Override
		protected void onPostExecute(String file_url) {
            // dismiss the dialog after getting all products
            pDialog.dismiss();
            // updating UI from Background Thread
            runOnUiThread(new Runnable() {
                @Override
				public void run() {
                    /**
                     * Updating parsed JSON data into ListView
                     * */
                    ListAdapter adapter = new SimpleAdapter(
                            GetNotifications.this,notificationList,
                            R.layout.list_item, new String[] { TAG_SUBJECT,
                                    TAG_TIME,TAG_CONTENT},
                            new int[] { R.id.subject, R.id.time,R.id.content });
                    // updating listview
                    setListAdapter(adapter);
                }
            });
 
        }
 
    }
}

