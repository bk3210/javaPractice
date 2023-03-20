package com.kwon.dec151uc.main;

import java.io.InputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

// 데이터 parsing
//		데이터 가공(쓸데없는거 떼고...해서 원하는 형태로)

// IT바닥
//		제3자한테 데이터를 주고 싶었는데
//		정해진 형식으로 줘야, 사용자가 parsing을 할 수 있을것
//		데이터를 HTML모양으로 표현 : XML
//			DOM(Document Object Model)객체
//				<이름> : 시작태그
//				홍길동 : 텍스트
//				</이름> : 종료태그
//		<학생들>
//			<학생>
//				<이름>홍길동</이름>
//				<나이>20</나이>
//			</학생>
//			<학생>
//				<이름>김길동</이름>
//				<나이>30</나이>
//			</학생>
//		</학생들>
//		데이터를 JavaScript모양으로 표현 : JSON

// XML parsing : 관련기능이 자바에 없음
//				kxml -> android에 내장되어있어서

public class UCMain3 {
	public static void main(String[] args) {
		HttpsURLConnection huc = null;
		try {
			String s = "https://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1168052100";
			URL u = new URL(s);
			huc = (HttpsURLConnection) u.openConnection();
			InputStream is = huc.getInputStream();

			XmlPullParserFactory xppf = XmlPullParserFactory.newInstance();
			XmlPullParser xpp = xppf.newPullParser();
			xpp.setInput(is, "utf-8"); // xpp.setInput(isr);

			// 지금 빨아낸게 그 셋중에 뭐냐
			int what = xpp.getEventType();
			String tagName = null;
			while (what != XmlPullParser.END_DOCUMENT) {
				if (what == XmlPullParser.START_TAG) {
					tagName = xpp.getName();
				} else if (what == XmlPullParser.TEXT) {
					if (tagName.equals("hour")) {
						System.out.println("~" + xpp.getText() + "시 까지");
					} else if (tagName.equals("temp")) {
						System.out.println(xpp.getText() + "℃");
					} else if (tagName.equals("wfKor")) {
						System.out.println(xpp.getText());
						System.out.println("-----");
					}
				} else if (what == XmlPullParser.END_TAG) {
					tagName = "";
				}
				xpp.next(); // 다음거 빨기
				what = xpp.getEventType(); // 그건 뭐냐
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		huc.disconnect();
	}
}
