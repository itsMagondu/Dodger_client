package com.Project;

import android.app.Activity;
import android.app.Dialog;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class Sign_up extends Activity implements OnClickListener {
	protected static final SQLiteDatabase db = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sign_up);
		
		Button register;
		register = (Button) findViewById(R.id.butSign);
		final EditText name = (EditText) findViewById(R.id.etName);
		final EditText email = (EditText) findViewById(R.id.etEmail);
		final EditText username = (EditText) findViewById(R.id.etUsername);
		final EditText pass1 = (EditText) findViewById(R.id.etPassword1);
		final EditText pass2 = (EditText) findViewById(R.id.etPassword2);
		
		register.setOnClickListener(new View.OnClickListener() 
		{
			
		@Override
			public void onClick(View v) {
			
			String person_password1 = pass1.getText().toString();
			String person_password = pass2.getText().toString();
			
			if(person_password.equals(person_password1))
			{
			
			String person_name = name.getText().toString();
			String person_email = email.getText().toString();	
			String person_username = username.getText().toString();
			
			
			 Dodger_Database entry = new Dodger_Database(Sign_up.this);
				entry.open();	
				entry.createEntry(person_name, person_email, person_username, person_password);
				entry.close();
			}
			else 
				{
				pass2.setText("");
				pass1.setText("");
				Dialog d = new Dialog(Sign_up.this);
				d.setTitle("ERROR!!");
				TextView tv = new TextView(Sign_up.this);
				tv.setText("Passwords do not match");
				d.setContentView(tv);
				d.show();
				
				} 
			}
		});
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
	

}
