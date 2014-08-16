package com.magzter.webservices;

import java.net.URI;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.magzter.models.Category;
import com.magzter.models.Issue;
import com.magzter.models.Magazine;
import com.magzter.models.Publisher;

public class WebClient {

	public static ArrayList<Magazine> getMagazineList(String url){
		
		URI uri = URI.create(url);
		JSONArray jsonArray = HTTPClient.getJson(uri, null);
		ArrayList<Magazine> magazineList = new ArrayList<Magazine>();
		if(jsonArray!= null){
			JSONObject jsonObject = null;
			for(int i = 0; i < jsonArray.length(); i++){
				Magazine magazine = new Magazine();
				try {
					jsonObject = (JSONObject) jsonArray.get(i);
					if(jsonObject.has("Magazine_Id")){
						magazine.setId(Integer.parseInt(jsonObject.get("Magazine_Id").toString()));
					}
					if(jsonObject.has("Magazine_PublisherId")){
						magazine.setPublisherId(Integer.parseInt(jsonObject.get("Magazine_PublisherId").toString()));
					}
					if(jsonObject.has("Magazine_Title")){
						magazine.setTitle(jsonObject.get("Magazine_Title").toString());
					}
					if(jsonObject.has("Magazine_6MonthsSubscription")){
						magazine.set_6MonthsSubscription(Float.parseFloat(jsonObject.get("Magazine_6MonthsSubscription").toString()));
					}
					if(jsonObject.has("Magazine_12MonthsSubscription")){
						magazine.set_12MonthsSubscription(Float.parseFloat(jsonObject.get("Magazine_12MonthsSubscription").toString()));
					}
					if(jsonObject.has("Magazine_State")){
						magazine.setState(jsonObject.get("Magazine_State").toString());
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
				magazineList.add(magazine);
			}
		}
		return magazineList;
	}
	
public static ArrayList<Issue> getIssueList(String url){
		
		URI uri = URI.create(url);
		JSONArray jsonArray = HTTPClient.getJson(uri, null);
		ArrayList<Issue> issueList = new ArrayList<Issue>();
		if(jsonArray!= null){
			JSONObject jsonObject = null;
			for(int i = 0; i < jsonArray.length(); i++){
				Issue issue = new Issue();
				try {
					jsonObject = (JSONObject) jsonArray.get(i);
					if(jsonObject.has("Issue_Id")){
						issue.setId(Integer.parseInt(jsonObject.get("Issue_Id").toString()));
					}
					if(jsonObject.has("Issue_MagazineId")){
						issue.setMagazineId(jsonObject.get("Issue_MagazineId").toString());
					}
					if(jsonObject.has("Issue_Date")){
						issue.setDate(jsonObject.get("Issue_Date").toString());
					}
					if(jsonObject.has("Issue_DateTitle")){
						issue.setDateTitle(jsonObject.get("Issue_DateTitle").toString());
					}
					if(jsonObject.has("Issue_Description")){
						issue.setDescription(jsonObject.get("Issue_Description").toString());
					}
					if(jsonObject.has("Issue_NumberOfSale")){
						issue.setNumberOfSale(jsonObject.get("Issue_NumberOfSale").toString());
					}
					if(jsonObject.has("Issue_Price")){
						issue.setPrice(jsonObject.get("Issue_Price").toString());
					}
					if(jsonObject.has("Issue_IsComplementary")){
						issue.setIsComplementary(jsonObject.get("Issue_IsComplementary").toString());
					}
					if(jsonObject.has("Issue_ChunkAmount")){
						issue.setChunkAmount(jsonObject.get("Issue_ChunkAmount").toString());
					}
					if(jsonObject.has("Issue_ChunkAmountPreview")){
						issue.setChunkAmountPreview(jsonObject.get("Issue_ChunkAmountPreview").toString());
					}
					if(jsonObject.has("Issue_State")){
						issue.setState(jsonObject.get("Issue_State").toString());
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
				issueList.add(issue);
			}
		}
		return issueList;
	}

	public static ArrayList<Category> getCategoryList(String url){
		
		URI uri = URI.create(url);
		JSONArray jsonArray = HTTPClient.getJson(uri, null);
		ArrayList<Category> categoryList = new ArrayList<Category>();
		if(jsonArray!= null){
			JSONObject jsonObject = null;
			for(int i = 0; i < jsonArray.length(); i++){
				Category category = new Category();
				try {
					jsonObject = (JSONObject) jsonArray.get(i);
					if(jsonObject.has("Category_Id")){
						category.setId(Integer.parseInt(jsonObject.get("Category_Id").toString()));
					}
					if(jsonObject.has("Category_Name")){
						category.setName(jsonObject.get("Category_Name").toString());
					}
					if(jsonObject.has("Category_NumberOfMagazine")){
						category.setNoOfMagazine(Integer.parseInt(jsonObject.get("Category_NumberOfMagazine").toString()));
					}
					if(jsonObject.has("Category_State")){
						category.setState(jsonObject.get("Category_State").toString());
					}
					
				} catch (JSONException e) {
					e.printStackTrace();
				}
				categoryList.add(category);
			}
		}
		return categoryList;
	}

	public static ArrayList<Publisher> getPublisherList(String url){
		
		URI uri = URI.create(url);
		JSONArray jsonArray = HTTPClient.getJson(uri, null);
		ArrayList<Publisher> publisherList = new ArrayList<Publisher>();
		if(jsonArray!= null){
			JSONObject jsonObject = null;
			for(int i = 0; i < jsonArray.length(); i++){
				Publisher publisher = new Publisher();
				try {
					jsonObject = (JSONObject) jsonArray.get(i);
					if(jsonObject.has("Publisher_Id")){
						publisher.setId(Integer.parseInt(jsonObject.get("Publisher_Id").toString()));
					}
					if(jsonObject.has("Publisher_Name")){
						publisher.setName(jsonObject.get("Publisher_Name").toString());
					}
					if(jsonObject.has("Publisher_State")){
						publisher.setState(jsonObject.get("Publisher_State").toString());
					}
					
				} catch (JSONException e) {
					e.printStackTrace();
				}
				publisherList.add(publisher);
			}
		}
		return publisherList;
	}
}
