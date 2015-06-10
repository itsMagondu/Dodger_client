package com.Project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends Activity{


	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		
		//final EditText Username = (EditText) findViewById(R.id.etUsername);
		final Button enter = (Button) findViewById(R.id.enter);
		final Button signUp = (Button) findViewById(R.id.signup);	
		final Button view = (Button) findViewById(R.id.bView);
		//final TextView display = (TextView) findViewById(R.id.tvHeader);
		

		enter.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
//				String inputS = Username.getText().toString();
//				display.setText("Welcome " +inputS);
//				Username.setText("");
				
				Intent loadHome = new Intent("com.Project.Main");
				startActivity(loadHome);
				
				}
		});
		
		signUp.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//Class sign = Class.forName("com.myactivity.trafficDodger.Sign_up");
				Intent loadHo = new Intent("com.Project.Sign_up");
				startActivity(loadHo);
				
				}
		});
		
		view.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent loadHo = new Intent("com.Project.Display_Data");
				startActivity(loadHo);	
				
			}
		});
		
		}
	
	
  }

