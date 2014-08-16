package com.magzter.dao;

import java.util.ArrayList;
import java.util.List;

import com.magzter.database.DataBaseHandler;
import com.magzter.models.Category;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class CategoryDataSource {

	private SQLiteDatabase database;
	private DataBaseHandler dbHandler;
	private String[] allColumns = { DataBaseHandler.COLUMN_ID, DataBaseHandler.COLUMN_NAME,
			DataBaseHandler.COLUMN_NO_OF_MAGAZINES, DataBaseHandler.COLUMN_STATE};
	
	public CategoryDataSource(Context context) {
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
	public Category createCategory(int categoryId, String name, int noOfMagazines, String state) {
		ContentValues values = new ContentValues();
		values.put(DataBaseHandler.COLUMN_ID, categoryId);
		values.put(DataBaseHandler.COLUMN_NAME, name);
		values.put(DataBaseHandler.COLUMN_NO_OF_MAGAZINES, noOfMagazines);
		values.put(DataBaseHandler.COLUMN_STATE, state);
		
		long insertId = database.insert(DataBaseHandler.TABLE_CATEGORY, null, values);
		Log.i("insert id",""+insertId);
		Cursor cursor = database.query(DataBaseHandler.TABLE_CATEGORY,
				allColumns, DataBaseHandler.COLUMN_ID + " = " + insertId, null, null, null, null);
		cursor.moveToFirst();
		Category newCategory = cursorToCategory(cursor);
		cursor.close();
		return newCategory;
	}
	
	//insert location record into the table using Category model...................................
	public Category createCategory(Category category) {
		ContentValues values = new ContentValues();
		values.put(DataBaseHandler.COLUMN_ID, category.getId());
		values.put(DataBaseHandler.COLUMN_NAME, category.getName());
		values.put(DataBaseHandler.COLUMN_NO_OF_MAGAZINES, category.getNoOfMagazine());
		values.put(DataBaseHandler.COLUMN_STATE, category.getState());
	
		long insertId = database.insert(DataBaseHandler.TABLE_CATEGORY, null, values);
		Log.i("insert id",""+insertId);
		Cursor cursor = database.query(DataBaseHandler.TABLE_CATEGORY,
				allColumns, DataBaseHandler.COLUMN_ID + " = " + insertId, null, null, null, null);
		cursor.moveToFirst();
		Category newCategory = cursorToCategory(cursor);
		cursor.close();
		return newCategory;
	}
		
	public void deleteCategory(int categoryId) {
		//int locationId = location.getLocationId();
		//System.out.println("Time Location deleted with id: " + locationId);
//		String[] columns = {DataBaseHandler.COLUMN_ID};

		database.delete(DataBaseHandler.TABLE_CATEGORY, DataBaseHandler.COLUMN_ID
				+ " = " + categoryId, null);
	}
	
	//get the all location records from table.....................
	public List<Category> getAllCategories() {
		List<Category> categories = new ArrayList<Category>();

		Cursor cursor = database.query(DataBaseHandler.TABLE_CATEGORY,
				allColumns, null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Category category = cursorToCategory(cursor);
			categories.add(category);
			cursor.moveToNext();
		}
		// Make sure to close the cursor
		cursor.close();
		return categories;
	}
	
	private Category cursorToCategory(Cursor cursor) {
		Category category = new Category();
		category.setId(cursor.getInt(0));
		category.setName(cursor.getString(1));
		category.setNoOfMagazine(cursor.getInt(2));
		category.setState(cursor.getString(3));
		return category;
	}
}
