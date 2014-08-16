package com.magzter;

import java.util.ArrayList;
import java.util.List;

import com.magzter.dao.CategoryDataSource;
import com.magzter.models.Category;
import com.magzter.webservices.WebClient;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.Window;

public class LoadingActivity extends Activity {

	ArrayList<Category> categoryList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_loading);
		
		loadData();
	}

	private void loadData(){
		
		final Handler handler = new Handler(){
			ProgressDialog progresDialog ; 
			public void handleMessage(Message msg) {
				if(msg.what == 0){
					progresDialog = ProgressDialog.show(LoadingActivity.this, "", "Loading Data..");
					
				}else if(msg.what == 1){
					progresDialog.dismiss();
					Log.i("Magazinr details", categoryList.get(0).getName());	
					finish();
					startActivity(new Intent(LoadingActivity.this, HomeActivity.class));
				}
			}
		};
			
		new Thread(){
			
			public void run(){
				handler.sendEmptyMessage(0);
				//Call API to get Categories............................
				categoryList = WebClient.getCategoryList(getString(R.string.get_category_list_uri));
				
				//delete previous recodes of the category table..........
				deleteCategoryDB();
				
				//Add got categories into DB...........................
				putCategoriesDB(categoryList);
				
				//retrieve categories to show again..........................
//				categoryList = (ArrayList<Category>) getCategoriesDB();
				handler.sendEmptyMessage(1);
			}
		}.start();
	}
	
	private void putCategoriesDB(ArrayList<Category> categories){
		
		CategoryDataSource categoryDatasource = new CategoryDataSource(this);
		categoryDatasource.open();
		for(int i = 0; i < categories.size(); i++){
			categoryDatasource.createCategory(categories.get(i));
		}
		categoryDatasource.close();
		
	}
	
	private List<Category> getCategoriesDB(){
		
		CategoryDataSource categoryDataSource = new CategoryDataSource(this);
		categoryDataSource.openReadable();
		List<Category> categories = categoryDataSource.getAllCategories();
		categoryDataSource.close();
		return categories;
	}
	
	private void deleteCategoryDB(){
		CategoryDataSource categoryDataSource = new CategoryDataSource(this);
		categoryDataSource.open();
		List<Category> categories = categoryDataSource.getAllCategories();
		for(int i = 0; i < categories.size(); i++){
			categoryDataSource.deleteCategory(categories.get(i).getId());
		}
		categoryDataSource.close();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.loading, menu);
		return true;
	}

}
