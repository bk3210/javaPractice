package com.kim.dec222a.practice;

public class Human {
	private String name;
	private int age;
	
	public Human() {
	}
	
	public Human(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	
	private void ready() {
		System.out.println("씻기");
		System.out.println("옷입기");
		System.out.println("집밖으로 나가기");
		System.out.println("엘리베이터 타기");
		System.out.println("걷기");
	}

	public void goPark() {
		ready();
		System.out.println("도착");		
	}
	
	public void goMarket() {
		ready();
		System.out.println("버스타기");
		System.out.println("도착");
	}
	
	public void goSchool() {
		ready();
		System.out.println("버스타기");
		System.out.println("지하철타기");
		System.out.println("도착");
	}
}
