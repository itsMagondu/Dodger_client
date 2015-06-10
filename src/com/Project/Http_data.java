package com.Project;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.*;
import org.apache.http.client.entity.*;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.*;


import android.app.Activity;
import android.app.ListActivity;
import android.net.ParseException;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

public class Http_data extends ListActivity {

		JSONArray jArray;
		String result = null;
		InputStream is = null;
		StringBuilder sb=null;

		@Override
		public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.http);
		//ListView listview = this.getListView();		
		
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			try{
		     HttpClient httpclient = new DefaultHttpClient();
		     HttpPost httppost = new HttpPost("http://10.0.2.2/Dodger/get.php");
		     httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		     HttpResponse response = httpclient.execute(httppost);
		     HttpEntity entity = response.getEntity();
		     is = entity.getContent();
		     }catch(Exception e){
		         Log.e("log_tag", "Error in http connection"+e.toString());
		    }
		//convert response to string
		try{
		      BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
		       sb = new StringBuilder();
		       sb.append(reader.readLine() + "\n");

		       String line="0";
		       while ((line = reader.readLine()) != null) {
		                      sb.append(line + "\n");
		        }
		        is.close();
		        result=sb.toString();
		        }catch(Exception e){
		              Log.e("log_tag", "Error converting result "+e.toString());
		        }
		//paring data
		
		try{
			
			 int user_id;
			 String user_name;
		     jArray = new JSONArray(result);
		     JSONObject json_data=null;
		      for(int i=0;i<jArray.length();i++){
		             json_data = jArray.getJSONObject(i);
		            user_id = json_data.getInt("userID");
		             user_name = json_data.getString("Name");
		             Toast.makeText(getBaseContext(), "The username is ".concat(user_name) ,Toast.LENGTH_LONG).show();
		         }
		      }
		      catch(JSONException e1){
		    	  Toast.makeText(getBaseContext(), "No User Found" ,Toast.LENGTH_LONG).show();
		      } catch (ParseException e1) {
					e1.printStackTrace();
			}
		}
		
		    }



