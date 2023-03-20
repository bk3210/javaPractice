package com.kwon.dec151uc.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

// 역대 지하철 운행정보 다 csv에
// 1) 2015/11/1의 1~5데이터만 콘솔에
// 2) 2015/11/1의 1~5데이터만 subway.csv파일에
// 3) 2015/11/1일 하루치 다
// 4) 2015 ~ 2021년 다

public class UCMain7 {
	public static void main(String[] args) {
		HttpURLConnection huc = null;
		BufferedWriter bw = null;
		try {
			FileOutputStream fos = new FileOutputStream("C:/Kwon/subway.csv", true);
			OutputStreamWriter osw = new OutputStreamWriter(fos, "utf-8");
			bw = new BufferedWriter(osw);
			// CardBusStatisticsServiceNew
			for (int y = 2015; y < 2022; y++) {
				for (int m = 1; m < 13; m++) {
					for (int d = 1; d < 32; d++) {
						String when = String.format("%d%02d%02d", y, m, d);
						String addr = "http://openapi.seoul.go.kr:8088/575a4655496b636839386f58586542/json/CardSubwayStatsNew/1/700/" + when;
						URL u = new URL(addr);
						huc = (HttpURLConnection) u.openConnection();
						InputStream is = huc.getInputStream();				
						InputStreamReader isr = new InputStreamReader(is, "utf-8");		
						BufferedReader br = new BufferedReader(isr);
						JSONParser jp = new JSONParser();
						JSONObject subwayData = (JSONObject) jp.parse(br.readLine());
						JSONObject cssn = (JSONObject) subwayData.get("CardSubwayStatsNew");
						if (cssn != null) {
							JSONArray r = (JSONArray) cssn.get("row");
							JSONObject s = null;
							for (int i = 0; i < r.size(); i++) {
								s = (JSONObject) r.get(i);
								bw.write(when.substring(0, 4) + "," + when.substring(4, 6) + "," + when.substring(6, 8) + ",");
								bw.write(s.get("LINE_NUM") + "," + s.get("SUB_STA_NM") + "," + s.get("RIDE_PASGR_NUM") + "," + s.get("ALIGHT_PASGR_NUM") + "\r\n");
								bw.flush();
							}
							System.out.println(when);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		huc.disconnect();
	}
}
