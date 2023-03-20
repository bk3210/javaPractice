package com.kosa.project2;

public class ProductDTO {
	private String prdNum;
	private String prdName;
	private int prdPrice;
	private String prdDate;
	private String prdMaker;
	
	public ProductDTO() {
		// TODO Auto-generated constructor stub
	}

	public ProductDTO(String prdNum, String prdName, int prdPrice, String prdDate, String prdMaker) {
		super();
		this.prdNum = prdNum;
		this.prdName = prdName;
		this.prdPrice = prdPrice;
		this.prdDate = prdDate;
		this.prdMaker = prdMaker;
	}

	public String getPrdNum() {
		return prdNum;
	}

	public void setPrdNum(String prdNum) {
		this.prdNum = prdNum;
	}

	public String getPrdName() {
		return prdName;
	}

	public void setPrdName(String prdName) {
		this.prdName = prdName;
	}

	public int getPrdPrice() {
		return prdPrice;
	}

	public void setPrdPrice(int prdPrice) {
		this.prdPrice = prdPrice;
	}

	public String getPrdDate() {
		return prdDate;
	}

	public void setPrdDate(String prdDate) {
		this.prdDate = prdDate;
	}

	public String getPrdMaker() {
		return prdMaker;
	}

	public void setPrdMaker(String prdMaker) {
		this.prdMaker = prdMaker;
	}

	@Override
	public String toString() {
		return prdNum + "	" + prdName + "	" + prdPrice + " 	"
				+ prdDate + " 	" + prdMaker;
	}
	


}
