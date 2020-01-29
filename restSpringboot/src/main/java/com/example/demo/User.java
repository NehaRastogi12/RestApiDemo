package com.example.demo;

public class User {
	private String name;
	private String rollNo;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRollNo() {
		return rollNo;
	}
	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}
	
	public User(String name, String rollNo) {
		this.name=name;
		this.rollNo=rollNo;
	}
	
}
