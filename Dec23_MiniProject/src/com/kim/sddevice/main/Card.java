package com.kim.sddevice.main;

// 멤버변수명이랑 DB 필드명이 꼭 같을 필요는 없음!!

// framework : 시키는 대로 하면 자동으로 처리해주는 시스템
//					=> 그러니까 멤버변수명, DB필드명 통일시켜라
//					=> 자동으로 DB에 있는 데이터 -> 자바 객체로
public class Card {
	private String name;
	private String cardName;
	private int da;
	private int vo;
	private int pf;
	
	public Card() {
	}

	public Card(String name, String cardName, int da, int vo, int pf) {
		super();
		this.name = name;
		this.cardName = cardName;
		this.da = da;
		this.vo = vo;
		this.pf = pf;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public int getDa() {
		return da;
	}

	public void setDa(int da) {
		this.da = da;
	}

	public int getVo() {
		return vo;
	}

	public void setVo(int vo) {
		this.vo = vo;
	}

	public int getPf() {
		return pf;
	}

	public void setPf(int pf) {
		this.pf = pf;
	}
	
	

}
