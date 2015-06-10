package com.Project;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Display_Data extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sqlview);
		TextView tv = (TextView) findViewById(R.id.tvName);
		Dodger_Database result = new Dodger_Database(Display_Data.this);
		result.open();
		String data = result.getData();
		result.close();
		tv.setText(data);
	}
	
	

}
