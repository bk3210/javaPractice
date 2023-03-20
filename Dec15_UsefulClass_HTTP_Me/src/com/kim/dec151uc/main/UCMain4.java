package com.kim.dec151uc.main;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

public class UCMain4 {
	public static void main(String[] args) {
		HttpsURLConnection huc = null;
		try {
			String s = "https://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1132066000";
			URL u = new URL(s);
			huc = (HttpsURLConnection) u.openConnection();
			
			InputStream is = huc.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "utf-8");
			
			XmlPullParser xpp = XmlPullParserFactory.newInstance().newPullParser();
			xpp.setInput(isr);
			
			int what = xpp.getEventType();
			String tagName = null;
			while(what != XmlPullParser.END_DOCUMENT) {
				if(what == XmlPullParser.START_TAG) {
					tagName = xpp.getName();
//					System.out.println(tagName);
				} else if (what == XmlPullParser.TEXT) {
					if(tagName.equals("hour")) {
						System.out.println("~" + xpp.getText() + "시 까지");
					} else if(tagName.equals("temp")) {
						System.out.println(xpp.getText() + "℃");
					} else if(tagName.equals("wfEn")) {
						System.out.println(xpp.getText());
						System.out.println("============");
					}																										// 결과 요상하게 나온거는 우리 잘못이 아니라 xml문 자체의 문제(엔터삽입)
				} else if (what == XmlPullParser.END_TAG) {
					tagName = "";			// 넣어주면 줄바꿈 이상하게 나오는 게 정정됨
				}
				xpp.next();		// 다음 거 빨아들이기
				what = xpp.getEventType();
			}
			huc.disconnect();		
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
