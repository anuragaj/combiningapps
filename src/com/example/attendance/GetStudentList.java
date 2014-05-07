package com.example.attendance;

import java.util.ArrayList;
import com.example.combiningaps.R;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class GetStudentList extends ListActivity {
	
	class Student{
		String name;
		String usn;
		String section;
		String semester;
		String rollno;
		boolean attendance;
		
	 Student(){
			attendance=false;
		}
	}
	
	 class Subject{
		String subjectName;
		String subjectCode;
	}
	
	 class Staff{
		String name;
		String passcode;
	}
	
	 Student[] peers;
	 Subject[] subs;
	 Staff[] lecturers;
	 int code=0;
	 int subCode;
	
	int numberOfStudents=0;
	int numberOfSubjects=0;
	int numberOfStaff=0;
	
	int currentStudent=0;
	public String current_ip;
	public String sc;
	String passcode;
	
	private ProgressDialog pDialog;
	 
    JSONParser jParser = new JSONParser();
    JSONParser jp=new JSONParser();
 
    ArrayList<HashMap<String, String>> studentList;
  
    public String url_all_students;
    public String url_verify_passcode;
    public String url_update;
    public String sem,sec;

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_STUDENTS = "students";
    private static final String TAG_USN= "usn";
    private static final String TAG_NAME = "name";
    private static final String TAG_ROLLNO="rollno";
    private static final String TAG_SUBJECTS="subjects";
    private static final String TAG_SUBJECTNAME="subjectName";
    private static final String TAG_SUBJECTCODE="subjectCode";
    private static final String TAG_STAFF="lecturers";
    private static final String TAG_PASSCODE="passcode";
    
    public static final String URL="com.example.Attendance.URL";
	
    
    private WebView webView;
    
 
    // products JSONArray
    JSONArray students = null;
    JSONArray subjects = null;
    JSONArray staff = null;
    
    public void displayToast(String s){
		 Toast.makeText(this, s, Toast.LENGTH_LONG).show();
	 }
    public void displayToast(int s){
		 Toast.makeText(GetStudentList.this, s, Toast.LENGTH_LONG).show();
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
		setContentView(R.layout.activity_get_student_list);
		
		Intent intent = getIntent();
		current_ip = intent.getStringExtra(com.example.attendance.MainActivity.IP);
		sem=intent.getStringExtra(com.example.attendance.MainActivity.SEM);
		sec=intent.getStringExtra(com.example.attendance.MainActivity.SEC);
		
		url_all_students = "http://"+current_ip+"/attendance/getStudentList.php?sem="+sem+"&sec="+sec;
		
		studentList = new ArrayList<HashMap<String, String>>();

        new LoadAllStudents().execute();
		
		 ListView lv = getListView();
		 
		 lv.setOnItemClickListener(new OnItemClickListener() {
		 @Override
         public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
			 
			 //TextView p=(TextView)view.findViewById(R.id.present);
			 TextView t=(TextView)view.findViewById(R.id.rollno);
			 String s=(String) t.getText();
			 int found=1;
			 for(int j=0;j<numberOfStudents && found==1;j++){
				 if(peers[j].rollno.equals(s)){
					 currentStudent=j;
					 found=0;
					 }
					 
			 }
			 if(found==0){
				//displayToast(""+currentStudent);
			 if(peers[currentStudent].attendance){
				 
				 //p.setText("Absent "+currentStudent);
				 peers[currentStudent].attendance=false;
				 
			 }
			 else{
				 //p.setText("Present "+currentStudent);
				 peers[currentStudent].attendance=true;
				 //t.setTextColor(color.holo_green_dark);
				 
				 
			 }
		 }
			 
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
	class LoadAllStudents extends AsyncTask<String, String, String> {
		 
        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(GetStudentList.this);
            pDialog.setMessage("Loading students. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }
 
        /**
         * getting All products from url
         * */
        @Override
		protected String doInBackground(String... args) {
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            JSONObject json = jParser.makeHttpRequest(url_all_students,"POST",params);

            Log.d("All Students: ", json.toString());
 
            try {
                int success = json.getInt(TAG_SUCCESS);
               
                if (success == 1) {
                    students = json.getJSONArray(TAG_STUDENTS);
                    subjects = json.getJSONArray(TAG_SUBJECTS);
                    staff=json.getJSONArray(TAG_STAFF);
                    numberOfSubjects=subjects.length();
                    numberOfStudents=students.length();
                    numberOfStaff=staff.length();
                    
                    peers=new Student[numberOfStudents];
 
                    for (int i = 0; i < students.length(); i++) {
                    	
                    	peers[i]=new Student();
                        JSONObject c = students.getJSONObject(i);
 
                        // Storing each json item in variable
                        String usn = c.getString(TAG_USN);
                        String name = c.getString(TAG_NAME);
                        String rollno = c.getString(TAG_ROLLNO);
 
                        // creating new HashMap
                        HashMap<String, String> map = new HashMap<String, String>();
 
                        // adding each child node to HashMap key => value
                        map.put(TAG_USN, usn);
                        map.put(TAG_NAME,name);
                        map.put(TAG_ROLLNO,rollno);
                       
                        studentList.add(map);
                        
                        peers[i].name=name;
                        peers[i].semester=sem;
                        peers[i].section=sec;
                        peers[i].rollno=rollno;
                        peers[i].usn=usn;
                    }
                    subs=new Subject[numberOfSubjects];
                    
                    for (int i = 0; i < subjects.length(); i++) {
                    	
                    	subs[i]=new Subject();
                        JSONObject c = subjects.getJSONObject(i);
 
                        // Storing each json item in variable
                        subs[i].subjectCode= c.getString(TAG_SUBJECTCODE);
                        subs[i].subjectName = c.getString(TAG_SUBJECTNAME);
                    }
                    
                    lecturers=new Staff[numberOfStaff];
                    
                    for (int i = 0; i < staff.length(); i++) {
                    	
                    	lecturers[i]=new Staff();
                        JSONObject c = staff.getJSONObject(i);
 
                        // Storing each json item in variable
                        lecturers[i].name= c.getString(TAG_NAME);
                        lecturers[i].passcode = c.getString(TAG_PASSCODE);
                    }
                    
                }
                else{
                	displayToast(success);
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
                     */
                    ListAdapter adapter = new SimpleAdapter(
                            GetStudentList.this, studentList,
                            R.layout.a_list_item, new String[] { TAG_USN,
                                    TAG_NAME,TAG_ROLLNO},
                            new int[] { R.id.usn, R.id.name,R.id.rollno });
                    // updating listview
                    setListAdapter(adapter);
                }
            });
        }
 
    }
	class Update{
		 
public void execute(){
			displayToast("updated");


        }
 
    }
	@Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.layout.menu1, menu);
        return true;
    }
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item){
         
        switch (item.getItemId())
        {
        case R.id.finalSubmit:		
        	final CharSequence subjects[] = new CharSequence[numberOfSubjects];
        	
        	for(int i=0;i<numberOfSubjects;i++)
        		subjects[i]=subs[i].subjectCode+"\t"+subs[i].subjectName;
        	AlertDialog.Builder builder = new AlertDialog.Builder(this);
        	builder.setTitle("Choose the suject");
        	builder.setItems(subjects, new DialogInterface.OnClickListener() {
        	    @Override
        	    public void onClick(DialogInterface dialog, int which) {
        	        sc=subs[which].subjectCode;
        	    	submit();
        	        //subCode=which;
        	        //displayToast(subs[which].subjectCode);
        	       
        	    }
        	});
        	builder.show();
        	return true;
        
        case R.id.showSelection:
        	String main="";
        	for(int i=0;i<numberOfStudents;i++){
        		main+=peers[i].name+" "+peers[i].attendance+"\n";
        	}
        	showAlertDialog(GetStudentList.this,"attendance",main,false);
        	return true;
        	
        /*case R.id.showStaff:
        	String m="";
        	for(int i=0;i<numberOfStaff;i++){
        		m+=lecturers[i].name+" "+lecturers[i].passcode+"\n";
        	}
        	
        	showAlertDialog(GetStudentList.this,"staff",m,false);
        	return true;
        	*/
        default:
            return super.onOptionsItemSelected(item);
        }
		
    }
	protected void submit() {
		passcode=null;
	
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("ENTER PASSCODE");

		// Set up the input
		final EditText input = new EditText(this);
		// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
		input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
		builder.setView(input);

		// Set up the buttons
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() { 
		    @SuppressLint("SetJavaScriptEnabled")
			@Override
		    public void onClick(DialogInterface dialog, int which) {
		        passcode = input.getText().toString();
		        int found=0;
		        for(int i=0;i<staff.length() && found==0;i++){
		        	if(lecturers[i].passcode.equals(passcode))
		        		found=1;
		        }
		        if(found==1){
		        	//displayAlert("valid passcode");
		        	
		        	String url_update = null;
		        	
		        	
		    		//process the URL
		    		// add address, students,number of students, increase the count 
		    		url_update="http://"+current_ip+"/attendance/addStudentsDifferent.php?";
		    		for(int i=0;i<numberOfStudents;i++){
		    			url_update+="&sn"+i+"="+peers[i].name+"&sr"+i+"="+peers[i].rollno+
		    					"&su"+i+"="+peers[i].usn+"&sa"+i+"="+peers[i].attendance;
		    		}			
		    		
		    		url_update+="&section"+"="+peers[0].section+"&semester"+"="+peers[0].semester;
		    		url_update+="&numberOfStudents="+numberOfStudents;
		    		url_update+="&subjectCode="+sc;
		    	
		    		url_update=url_update.replaceAll("\\s", "+");
		    		
		    		//displayAlert(url_update);
		    		Intent i = new Intent(getApplicationContext(), ShowResult.class);
		    		i.putExtra(URL,url_update);
		    		startActivity(i);
		    		}
		        else
		        	displayAlert("Invalid passcode");
	
		    }
		});
		builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
		    @Override
		    public void onClick(DialogInterface dialog, int which) {
		        dialog.cancel();
		    }
		});

		builder.show();
		

		//increment the student list in database
		
	}
}

