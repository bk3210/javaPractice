package com.kwon.dec151uc.main;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

// 실시간 미세먼지
// 		http://openapi.seoul.go.kr:8088/575a4655496b636839386f58586542/xml/RealtimeCityAir/1/25/
//		도심권
//		중구
//		34
//		23
//		보통
//		------------
public class UCMain4 {
	public static void main(String[] args) {
		HttpURLConnection huc = null;
		try {
			URL u = new URL("http://openapi.seoul.go.kr:8088/575a4655496b636839386f58586542/xml/RealtimeCityAir/1/25/");
			huc = (HttpURLConnection) u.openConnection();
			InputStream is = huc.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "utf-8");

			XmlPullParser xpp = XmlPullParserFactory.newInstance().newPullParser();
			xpp.setInput(isr);
			int type = xpp.getEventType();
			String tn = null;
			while (type != XmlPullParser.END_DOCUMENT) {
				if (type == XmlPullParser.START_TAG) {
					tn = xpp.getName();
				} else if (type == XmlPullParser.TEXT) {
					if (tn.equals("MSRRGN_NM")) {
						System.out.println(xpp.getText());
					} else if (tn.equals("MSRSTE_NM")) {
						System.out.println(xpp.getText());
					}
				} else if (type == XmlPullParser.END_TAG) {
					tn = "";
				}
				xpp.next();
				type = xpp.getEventType();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		huc.disconnect();

	}
}
