package com.Project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class DodgerActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        setContentView(R.layout.main);
        Thread timer = new Thread()
        {
        	public void run(){
        	try
        	{
        		sleep(5000);
        	}
        	catch (Exception e) 
        	{
				e.printStackTrace();
			}
        	finally 
        			{
        				Intent loadHome = new Intent("com.Project.Home");
        				startActivity(loadHome);
        			}
        	}
        };
        timer.start();

    }
}