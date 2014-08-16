package com.magzter.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Category implements Parcelable{

	private int id;
	private String name;
	private int noOfMagazine;
	private String state; 
	
	public Category(){
		
	}
	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNoOfMagazine() {
		return noOfMagazine;
	}

	public void setNoOfMagazine(int noOfMagazine) {
		this.noOfMagazine = noOfMagazine;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	
}
