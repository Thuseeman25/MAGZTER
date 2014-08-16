package com.magzter.models;

public class Magazine {

	private int id;
	private int publisherId;
	private String title;
	private float _6MonthsSubscription;
	private float _12MonthsSubscription;
	private String state;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPublisherId() {
		return publisherId;
	}
	public void setPublisherId(int publisherId) {
		this.publisherId = publisherId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public float get_6MonthsSubscription() {
		return _6MonthsSubscription;
	}
	public void set_6MonthsSubscription(float _6MonthsSubscription) {
		this._6MonthsSubscription = _6MonthsSubscription;
	}
	public float get_12MonthsSubscription() {
		return _12MonthsSubscription;
	}
	public void set_12MonthsSubscription(float _12MonthsSubscription) {
		this._12MonthsSubscription = _12MonthsSubscription;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
