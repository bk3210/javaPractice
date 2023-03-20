package com.kwon.dec151uc.main;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

// 데이터를 HTML모양으로 표현 : XML(eXtended Markup Language)
// 데이터를 JavaScript모양으로 표현 : 
//		JSON(JavaScript Object Notation)
//		var ar = [1, 3, 5];
//		var d = {name : "후추", age : 3};
//		int[] ar = {1, 3, 5};
//		Dog d = new Dog("후추", 3);

// JSON Parsing - 없음
//		json-simple : android에 내장

public class UCMain6 {
	public static void main(String[] args) {
		HttpsURLConnection huc = null;
		try {
			URL u = new URL("https://api.openweathermap.org/data/2.5/weather?q=seoul&appid=baff8f3c6cbc28a4024e336599de28c4&units=metric&lang=kr");
			huc = (HttpsURLConnection) u.openConnection();
			InputStream is = huc.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "utf-8");
			BufferedReader br = new BufferedReader(isr);
			
			JSONParser jp = new JSONParser();
			// [로 시작
			// JSONArray ja =
			
			// {로 시작
			JSONObject jo = (JSONObject) jp.parse(br.readLine()); // jp.parse(isr)
			JSONArray ja = (JSONArray) jo.get("weather");
			JSONObject w = (JSONObject) ja.get(0);
			System.out.println(w.get("description"));
			
			JSONObject main = (JSONObject) jo.get("main");
			System.out.println(main.get("temp"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		huc.disconnect();
	}
}
