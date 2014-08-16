package com.magzter.models;

public class Issue {
	
	private int id;
	private String magazineId;
	private String date;
	private String dateTitle;
	private String description;
	private String numberOfSale;
	private String price;
	private String isComplementary;
	private String chunkAmount;
	private String chunkAmountPreview;
	private String state;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMagazineId() {
		return magazineId;
	}
	public void setMagazineId(String magazineId) {
		this.magazineId = magazineId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDateTitle() {
		return dateTitle;
	}
	public void setDateTitle(String dateTitle) {
		this.dateTitle = dateTitle;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getNumberOfSale() {
		return numberOfSale;
	}
	public void setNumberOfSale(String numberOfSale) {
		this.numberOfSale = numberOfSale;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getIsComplementary() {
		return isComplementary;
	}
	public void setIsComplementary(String isComplementary) {
		this.isComplementary = isComplementary;
	}
	public String getChunkAmount() {
		return chunkAmount;
	}
	public void setChunkAmount(String chunkAmount) {
		this.chunkAmount = chunkAmount;
	}
	public String getChunkAmountPreview() {
		return chunkAmountPreview;
	}
	public void setChunkAmountPreview(String chunkAmountPreview) {
		this.chunkAmountPreview = chunkAmountPreview;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
