package com.kim.dec162uc.practice;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.text.SimpleAttributeSet;

public class StationInfo {
	private String when;
	private String no;
	private String name;
	private double inPassenger;
	private double outPassenger;

	public StationInfo() {
		// TODO Auto-generated constructor stub
	}
	
	// 2017,12,27, 이런 식의 날짜를 20171227로
	public StationInfo(String line) throws ParseException {
		String[] line2 = line.split(",");
		String when = line2[0] + line2[1] + line2[3];				// 날짜 합치기
		new SimpleDateFormat("yyMMdd").parse(when);			// 날짜 포맷으로 변환 + 예외처리(throws)
		no = line2[3];
		name= line2[4];
		inPassenger = Double.parseDouble(line2[5]);
		outPassenger = Double.parseDouble(line2[6]);
	}

	public StationInfo(String when, String no, String station, double inPassenger, double outPassenger) {
		super();
		this.when = when;
		this.no = no;
		this.name = station;
		this.inPassenger = inPassenger;
		this.outPassenger = outPassenger;
	}

	public String getDate() {
		return when;
	}

	public void setDate(String when) {
		this.when = when;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getStation() {
		return name;
	}

	public void setStation(String station) {
		this.name = station;
	}

	public double getInPassenger() {
		return inPassenger;
	}

	public void setInPassenger(double inPassenger) {
		this.inPassenger = inPassenger;
	}

	public double getOutPassenger() {
		return outPassenger;
	}

	public void setOutPassenger(double outPassenger) {
		this.outPassenger = outPassenger;
	}

	public void print() {
		String w = new SimpleDateFormat("yyyy/MM/dd(E)").format(when);
		System.out.println(w);
		System.out.println(no);
		System.out.println(name);
		System.out.println(inPassenger);
		System.out.println(outPassenger);
	}

}
