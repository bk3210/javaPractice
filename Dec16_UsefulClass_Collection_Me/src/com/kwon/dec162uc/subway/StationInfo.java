package com.kwon.dec162uc.subway;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StationInfo {
	private Date when;
	private String no;
	private String name;
	private double ride;
	private double alight;

	public StationInfo() {
		// TODO Auto-generated constructor stub
	}

	// 2017,12,27,경인선,간석,7177.0,6731.0
	public StationInfo(String line) throws ParseException {
		String[] line2 = line.split(",");
		String w = line2[0] + line2[1] + line2[2];
		when = new SimpleDateFormat("yyyyMMdd").parse(w);
		no = line2[3];
		name = line2[4];
		ride = Double.parseDouble(line2[5]);
		alight = Double.parseDouble(line2[6]);
	}

	public Date getWhen() {
		return when;
	}

	public void setWhen(Date when) {
		this.when = when;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getRide() {
		return ride;
	}

	public void setRide(double ride) {
		this.ride = ride;
	}

	public double getAlight() {
		return alight;
	}

	public void setAlight(double alight) {
		this.alight = alight;
	}

	public void print() {
		String w = new SimpleDateFormat("yyyy/MM/dd(E)").format(when);
		System.out.println(w);
		System.out.println(no);
		System.out.println(name);
		System.out.println(ride);
		System.out.println(alight);
	}

}
