package com.kim.dec151uc.main;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

// 데이터를 HTML 모양으로 표현 : XML(eXtended Markup Language)
// 데이터를 JavaScript 모양으로 표현 : JSON(JavaScript Object Notation)

// var ar = [1, 3, 5];		// JavaScriot
// var d = {name : "후추", age : 3};		// name, age는 멤버변수

// int[] ar {1, 2, 3};			// Java
// Dog d = new Dog("후추", 3);
// https://api.openweathermap.org/data/2.5/weather?q=london&appid=baff8f3c6cbc28a4024e336599de28c4&units=metric&lang=kr

// JSON을 parsing해 보자
// JSON parsing기능 없음 -> .jar 파일 추가(mvnrepository->json-simple 검색->첫번째거 최신버전 다운로드)
public class UCMain6 {
	public static void main(String[] args) {
		HttpsURLConnection huc = null;
		try {
			URL u = new URL("https://api.openweathermap.org/data/2.5/weather?q=london&appid=baff8f3c6cbc28a4024e336599de28c4&units=metric&lang=kr");
			huc = (HttpsURLConnection) u.openConnection();
			InputStream is = huc.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "utf-8");
			BufferedReader br = new BufferedReader(isr);
			
//			System.out.println(br.readLine());
			
			JSONParser jp = new JSONParser();
			
			// {로 시작할 때
			JSONObject jo = (JSONObject) jp.parse(br.readLine());	// 빨대를 주든지(주려면 BufferedReader 없어야함!!! 읽어내야 할 자료가 없어지니까?) 아니면 그냥 읽어낸 데이터를 주든지
																											// readLine()은 줄단위로 자료를 통째로 들고옴 + 형변환
			JSONArray ja = (JSONArray)jo.get("weather");		// weather 정보에 접근 + 형변환
			JSONObject w = (JSONObject)ja.get(0);		// 형변환
			System.out.println(w.get("description"));
			
			JSONObject main =(JSONObject) jo.get("main");
			System.out.println(main.get("temp"));
			
			// [로 시작할 땐 : JSONArray ja =;
		} catch (Exception e) {
			// TODO: handle exception
		}
		huc.disconnect();
	}

}
