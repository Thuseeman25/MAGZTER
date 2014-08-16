package com.magzter.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHandler extends SQLiteOpenHelper{

	private static final String DATABASE_NAME = "magzter.db";
	private static final int DATABASE_VERSION = 1;
	
	//Category table columns..........
	public static final String TABLE_CATEGORY = "category";
	public static final String COLUMN_ID = "id";
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_NO_OF_MAGAZINES = "no_of_magazines";
	public static final String COLUMN_STATE = "state";
	
	//Category table columns..........
	public static final String TABLE_MAGAZINE = "magazine";
	public static final String COLUMN_PUBLISHER_ID = "publisher_id";
	public static final String COLUMN_TITLE = "title";
	public static final String COLUMN_6MONTHS_SUBSCRIPTION = "six_months_subscription";
	public static final String COLUMN_12MONTHS_SUBSCRIPTION = "twelve_months_subscription";
		
	// Database creation sql statement
	private static final String TABLE_CREATE_CATEGORY = "create table if not exists "
			+ TABLE_CATEGORY + "(" + COLUMN_ID
			+ " integer primary key, " + COLUMN_NAME
			+ " text not null," + COLUMN_NO_OF_MAGAZINES + " integer,"+ COLUMN_STATE + " text)";
	
	// Database creation sql statement
	private static final String TABLE_CREATE_MAGAZINE= "create table if not exists "
				+ TABLE_MAGAZINE + "(" + COLUMN_ID
				+ " integer primary key, " + COLUMN_PUBLISHER_ID
				+ " integer," + COLUMN_TITLE + " text not null," + COLUMN_6MONTHS_SUBSCRIPTION + " real,"
				+ COLUMN_12MONTHS_SUBSCRIPTION + " real," + COLUMN_STATE + " text)";
		
	public DataBaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(TABLE_CREATE_CATEGORY);
		database.execSQL(TABLE_CREATE_MAGAZINE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(DataBaseHandler.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORY);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_MAGAZINE);
	} 
	
}
