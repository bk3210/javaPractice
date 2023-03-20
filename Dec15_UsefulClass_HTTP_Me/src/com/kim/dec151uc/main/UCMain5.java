package com.kim.dec151uc.main;


import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

// 		2022, 12, 15, 15, 15, 도심권, 중구, 34, 23, 보통
// 실시간 미세먼지를 csv에 저장하는 프로그램
public class UCMain5 {
	public static void main(String[] args) {
		BufferedWriter bw = null;
		HttpURLConnection huc = null;
		try {
			// 인터넷 객체 생성
			URL u =new URL("http://openapi.seoul.go.kr:8088/575a4655496b636839386f58586542/xml/RealtimeCityAir/1/25/");
			// api 사이트와 연결
			huc = (HttpURLConnection) u.openConnection();
			InputStream is = huc.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "utf-8");
			// BufferedReader br = new BufferedReader(isr);

			FileOutputStream fos = new FileOutputStream("C:/Users/user/Desktop/밀화부리/dust.cvs", true);
			OutputStreamWriter ows = new OutputStreamWriter(fos, "utf-8");
			bw = new BufferedWriter(ows);

			// 파싱할 준비
			XmlPullParser xpp = XmlPullParserFactory.newInstance().newPullParser();
			// XmlPullParser xpp = xppf.newPullParser(); // parsing 객체 생성
			xpp.setInput(isr);

			int what = xpp.getEventType();
//			System.out.println(what);
			String tagName = null;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy,MM,dd,HH,");
			String now = sdf.format(new Date());
			// dust.csv에 저장
			while (what != XmlPullParser.END_DOCUMENT) {
				if (what == XmlPullParser.START_TAG) {
					tagName = xpp.getName();
				} else if (what == XmlPullParser.TEXT) {
					if (tagName.equals("MSRRGN_NM")) {
						// 2022,12,15,15,
						bw.write(now);
						bw.write(xpp.getText() + ",");
					} else if (tagName.equals("MSRSTE_NM") || tagName.equals("PM10") || tagName.equals("PM25")) {
						bw.write(xpp.getText()+ ",");
					}else if (tagName.equals("IDEX_NM"))
						bw.write(xpp.getText()+ "\r\n");
						bw.flush();
				} else if (what == XmlPullParser.END_TAG) {
					tagName = "";
				}
				xpp.next(); // 다음 거 빨아들이기
				what = xpp.getEventType();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		huc.disconnect();
	}
}
