package com.kim.dec151uc.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

// 2015년 11월 1일 지하철 운행정보
// 2015/11/1의 1~5 데이터만 subwa.csv 파일에 저장
// 2015/11/1의 하루치 전부
// 2015/11월 한달치
// 2015년 1년치
// 2015~2021년 전부
public class UCMain7 {
	public static void main(String[] args) {
		HttpURLConnection huc = null;
		BufferedWriter bw = null;
		try {
			// 이번에는 DDoS때문에 일단 입력하는 메소드가 위로 가게
			FileOutputStream fos = new FileOutputStream("C:/Users/user/Desktop/밀화부리/subway.csv", true);
			OutputStreamWriter ows = new OutputStreamWriter(fos, "utf-8");
			bw = new BufferedWriter(ows);

			// 20150199 20150200 201519999 20160000 <- 이런거 걸러야함
			for (int year = 2015; year < 2022; year++) {
				for (int month = 1; month < 13; month++) {
					for (int day = 1; day < 32; day++) {
						String when = String.format("%d%02d%02d", year, month, day);
						String addr = "http://openapi.seoul.go.kr:8088/575a4655496b636839386f58586542/json/CardSubwayStatsNew/1/700/"
								+ when;
						URL u = new URL(addr);
						huc = (HttpURLConnection) u.openConnection();
						InputStream is = huc.getInputStream();
						InputStreamReader isr = new InputStreamReader(is, "utf-8");
						BufferedReader br = new BufferedReader(isr);

						// 2015,11,01,1호선,서울역,10,50
						JSONParser jp = new JSONParser();
						JSONObject subwayData = (JSONObject) jp.parse(br.readLine());
						// readLine()은 줄단위로 자료를 통째로 들고옴 + 형변환
						JSONObject cssn = (JSONObject) subwayData.get("CardSubwayStatsNew");
						// 없는 날짜면 걸러라
						if (cssn != null) {
							JSONArray row = (JSONArray) cssn.get("row"); // row는 cssn의 Has A 관계라 한번 더 접근 + 배열이라 object로
																			// 형변환
							JSONObject s = null;
							for (int i = 0; i < row.size(); i++) { // JSONArray는 .length로 못 쓰니까 size
								s = (JSONObject) row.get(i);
								bw.write(when.substring(0, 4) + "," + when.substring(4, 6) + "," + when.substring(6, 8)
										+ ",");
								bw.write(s.get("SUB_STA_NM") + ",");
								bw.write(s.get("LINE_NUM") + ",");
								bw.write(s.get("RIDE_PASGR_NUM") + ",");
								bw.write(s.get("ALIGHT_PASGR_NUM") + "\r\n");
								bw.flush(); // 용량 다 안차도 보내기
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
