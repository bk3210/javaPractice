package com.kim.dec23mvc.main;

// JavaBean Class
// DTO(Data Temp/Transfer Object) : 데이터를 저장하기 위해 임시로 저장하는 객체(합, 차, 곱, 몫의 값)
// VO(Value Object)
// POJO(Plain Old Java Object)

// 얘도 분류상 M쪽 - 데이터 표현을 위해 사용되니까
public class CalcResult {
	private int plus;
	private int minus;
	private int multi;
	private int divise;

	public CalcResult() {
		
}

	public CalcResult(int plus, int minus, int multi, int divise) {
		super();
		this.plus = plus;
		this.minus = minus;
		this.multi = multi;
		this.divise = divise;
	}

	public int getPlus() {
		return plus;
	}

	public void setPlus(int plus) {
		this.plus = plus;
	}

	public int getMinus() {
		return minus;
	}

	public void setMinus(int minus) {
		this.minus = minus;
	}

	public int getMulti() {
		return multi;
	}

	public void setMulti(int multi) {
		this.multi = multi;
	}

	public int getDivise() {
		return divise;
	}

	public void setDivise(int divise) {
		this.divise = divise;
	}
	
	
	
}
