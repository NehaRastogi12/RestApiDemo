package com.example.demo;

public class Product {
	
	private int productId=0;
	private 	int price=0;
	private String benefits="";
	private String location="";
	
	public Product(int productId, int price, String ben, String loc) {
		this.productId=productId;
		this.price=price;
		this.benefits=ben;
		this.location=loc;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getBenefits() {
		return benefits;
	}
	public void setBenefits(String benefits) {
		this.benefits = benefits;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
}
