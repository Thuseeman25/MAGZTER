package com.magzter.dao;

import java.util.ArrayList;
import java.util.List;

import com.magzter.database.DataBaseHandler;
import com.magzter.models.Magazine;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class MagazineDataSource {

	private SQLiteDatabase database;
	private DataBaseHandler dbHandler;
	private String[] allColumns = { DataBaseHandler.COLUMN_ID, DataBaseHandler.COLUMN_PUBLISHER_ID,
			DataBaseHandler.COLUMN_TITLE, DataBaseHandler.COLUMN_6MONTHS_SUBSCRIPTION, 
			DataBaseHandler.COLUMN_12MONTHS_SUBSCRIPTION, DataBaseHandler.COLUMN_STATE};
	
	public MagazineDataSource(Context context) {
		dbHandler = new DataBaseHandler(context);
	}
		
	public void open() throws SQLException {
		database = dbHandler.getWritableDatabase();
	}
	
	public void openReadable() throws SQLException{
		database = dbHandler.getReadableDatabase();
	}

	public void close() {
		dbHandler.close();
	}
	
	//insert location record into the table using direct parameters...................................
	public Magazine createMagazine(int magazineId, int publisherId, String title, 
			float _6month_subscription, float _12month_subscription, String state) {
		ContentValues values = new ContentValues();
		values.put(DataBaseHandler.COLUMN_ID, magazineId);
		values.put(DataBaseHandler.COLUMN_PUBLISHER_ID, publisherId);
		values.put(DataBaseHandler.COLUMN_TITLE, title);
		values.put(DataBaseHandler.COLUMN_6MONTHS_SUBSCRIPTION, _6month_subscription);
		values.put(DataBaseHandler.COLUMN_12MONTHS_SUBSCRIPTION, _12month_subscription);
		values.put(DataBaseHandler.COLUMN_STATE, state);
		
		long insertId = database.insert(DataBaseHandler.TABLE_MAGAZINE, null, values);
		Log.i("insert id",""+insertId);
		Cursor cursor = database.query(DataBaseHandler.TABLE_MAGAZINE,
				allColumns, DataBaseHandler.COLUMN_ID + " = " + insertId, null, null, null, null);
		cursor.moveToFirst();
		Magazine newMagazine = cursorToMagazine(cursor);
		cursor.close();
		return newMagazine;
	}
	
	//insert location record into the table using Category model...................................
	public Magazine createMagazine(Magazine magazine) {
		ContentValues values = new ContentValues();
		values.put(DataBaseHandler.COLUMN_ID, magazine.getId());
		values.put(DataBaseHandler.COLUMN_PUBLISHER_ID, magazine.getPublisherId());
		values.put(DataBaseHandler.COLUMN_TITLE, magazine.getTitle());
		values.put(DataBaseHandler.COLUMN_6MONTHS_SUBSCRIPTION, magazine.get_6MonthsSubscription());
		values.put(DataBaseHandler.COLUMN_12MONTHS_SUBSCRIPTION, magazine.get_12MonthsSubscription());
		values.put(DataBaseHandler.COLUMN_STATE, magazine.getState());
	
		long insertId = database.insert(DataBaseHandler.TABLE_MAGAZINE, null, values);
		Log.i("insert id",""+insertId);
		Cursor cursor = database.query(DataBaseHandler.TABLE_MAGAZINE,
				allColumns, DataBaseHandler.COLUMN_ID + " = " + insertId, null, null, null, null);
		cursor.moveToFirst();
		Magazine newMagazine = cursorToMagazine(cursor);
		cursor.close();
		return newMagazine;
	}
		
	public void deleteMagazine(int magazineId) {
		//int locationId = location.getLocationId();
		//System.out.println("Time Location deleted with id: " + locationId);
//		String[] columns = {DataBaseHandler.COLUMN_ID};

		database.delete(DataBaseHandler.TABLE_MAGAZINE, DataBaseHandler.COLUMN_ID
				+ " = " + magazineId, null);
	}
	
	//get the all location records from table.....................
	public List<Magazine> getAllMagazines() {
		List<Magazine> magazines = new ArrayList<Magazine>();

		Cursor cursor = database.query(DataBaseHandler.TABLE_MAGAZINE,
				allColumns, null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Magazine magazine = cursorToMagazine(cursor);
			magazines.add(magazine);
			cursor.moveToNext();
		}
		// Make sure to close the cursor
		cursor.close();
		return magazines;
	}
	
	private Magazine cursorToMagazine(Cursor cursor) {
		Magazine magazine = new Magazine();
		magazine.setId(cursor.getInt(0));
		magazine.setPublisherId(cursor.getInt(1));
		magazine.setTitle(cursor.getString(2));
		magazine.set_6MonthsSubscription(cursor.getFloat(3));
		magazine.set_12MonthsSubscription(cursor.getFloat(4));
		magazine.setState(cursor.getString(5));
		return magazine;
	}
}
