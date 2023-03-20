package com.kim.dec23_daodtomain;

// DTO
public class Book {
	private String name;				// 멤버변수, 속성, 필드
	private int price;
	
	public Book() {
		// TODO Auto-generated constructor stub
	}
	
	public Book(String name, int price) {
		super();
		this.name = name;
		this.price = price;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}

}
