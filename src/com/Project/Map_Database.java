package com.Project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class Map_Database {
		
	public final static String  KEY_LOCID = "_id";
	public final static String  KEY_LAT = "Latitude";
	public final static String  KEY_LON = "Longitude";
	public final static String  KEY_TIME = "Time";
	
	
	private final static String  DATABASE_USER = "traffic_dodger";
	private final static String  TABLE_LOCATION = "Location_details";
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
					"CREATE table " +TABLE_LOCATION + "( " 
					+KEY_LOCID+ " INTEGER PRIMARY KEY AUTOINCREMENT," 
					+KEY_LAT+ " TEXT NOT NULL," 
					+KEY_LON+ " TEXT NOT NULL,"
					+KEY_TIME+ " TEXT )"
						
					);
					}

		@Override
		public void onUpgrade(SQLiteDatabase db, int old_version, int new_version) {
			
			db.execSQL("DROP TABLE IF EXISTS" +TABLE_LOCATION);
			onCreate(db);
		}
		
	}
	public Map_Database(Context c)
	{
		myContext = c;
	}
	public Map_Database open()
	{
		myHelper = new DbHelper(myContext);
		mydatabase = myHelper.getWritableDatabase();
		return this;
	}
	 
	public Map_Database close()
	{
		myHelper.close();
		return this;
	}
	
public long createMapEntry(String user_ID, double lat, double lon) {
		
		ContentValues cv = new ContentValues();
		cv.put(KEY_LOCID, user_ID);
		cv.put(KEY_LAT, lat );
		cv.put( KEY_LON, lon);
		return  mydatabase.insert(TABLE_LOCATION, null, cv)	;
		
	}
	
	public String getMapData()
	{
		
		String[] collumns =  new String[]{KEY_LOCID, KEY_LAT, KEY_LON};
		Cursor c = mydatabase.query(TABLE_LOCATION, collumns, null, null,null, null, null);
		String result = null;
		//int ID = c.getColumnIndex(KEY_LOCID);
		int lat = c.getColumnIndex(KEY_LAT);
		int lon = c.getColumnIndex(KEY_LON);
		//int userResult = c.getColumnIndex(KEY_USERNAME);
		//int passResult = c.getColumnIndex(KEY_PASSWORD);
		c.moveToLast();
		result = c.getString(lat) +"*"+ c.getString(lon);		
		 
		return result;
	}
	

}
