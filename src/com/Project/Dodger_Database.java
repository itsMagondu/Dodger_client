package com.Project;


import android.content.ContentValues;
import android.content.Context;

import android.database.Cursor;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Dodger_Database {
	
	public final static String  KEY_ROWID = "_id";
	public final static String  KEY_NAME = "person_name";
	public final static String  KEY_EMAIL = "email_adress";
	public final static String  KEY_USERNAME = "username";
	public final static String  KEY_PASSWORD = "password";
	

	
	
	private final static String  DATABASE_USER = "traffic_dodger";
	private final static String  TABLE_USERS = "user_details";
	private final static int  DATABASE_VERSION = 1;
	
	
	
	
	private DbHelper myHelper;
	private final Context myContext;
	private SQLiteDatabase mydatabase;
	private static class DbHelper extends SQLiteOpenHelper{

		public DbHelper(Context context) {
			super(context, DATABASE_USER, null, DATABASE_VERSION);
			
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			
			db.execSQL(
			"CREATE table " +TABLE_USERS + "( " 
			+KEY_ROWID+ " INTEGER PRIMARY KEY AUTOINCREMENT," 
			+KEY_NAME+ " TEXT NOT NULL," 
			+KEY_EMAIL+ " TEXT NOT NULL,"
			+KEY_USERNAME+ " TEXT NOT NULL,"
			+KEY_PASSWORD+ " TEXT NOT NULL )"			
			);
			
					}

		@Override
		public void onUpgrade(SQLiteDatabase db, int old_version, int new_version) {
			db.execSQL("DROP TABLE IF EXISTS" +TABLE_USERS);
			
			onCreate(db);
		}
		
	}
	public Dodger_Database(Context c)
	{
		myContext = c;
	}
	public Dodger_Database open()
	{
		myHelper = new DbHelper(myContext);
		mydatabase = myHelper.getWritableDatabase();
		return this;
	}
	 
	public Dodger_Database close()
	{
		myHelper.close();
		return this;
	}
	public long createEntry(String person_name, String person_email, String person_username, String person_password) {
		
		ContentValues cv = new ContentValues();
		cv.put(KEY_NAME, person_name);
		cv.put(KEY_EMAIL, person_email );
		cv.put( KEY_USERNAME, person_username);
		cv.put(KEY_PASSWORD, person_password );
		return  mydatabase.insert(TABLE_USERS, null, cv)	;
		
	}

	public String getData()
	{
		
		String[] collumns =  new String[]{KEY_ROWID, KEY_NAME, KEY_EMAIL, KEY_USERNAME,KEY_PASSWORD};
		Cursor c = mydatabase.query(TABLE_USERS, collumns, null, null,null, null, null);
		String result = null;
		int IDresult = c.getColumnIndex(KEY_ROWID);
		int nameResult = c.getColumnIndex(KEY_NAME);
		int emailResult = c.getColumnIndex(KEY_EMAIL);
		//int userResult = c.getColumnIndex(KEY_USERNAME);
		//int passResult = c.getColumnIndex(KEY_PASSWORD);
		
		 for(c.moveToFirst() ; !c.isAfterLast(); c.moveToNext())
		 {
			result = result + c.getString(IDresult) + " " + c.getString(nameResult) + " " + c.getString(emailResult)+ " \n"; 
		 }
		return result;
	}
	
	
}
