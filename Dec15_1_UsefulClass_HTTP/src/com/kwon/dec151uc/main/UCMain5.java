package com.kwon.dec151uc.main;

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

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

//	2022,12,15,15,도심권,중구,34,23,보통
//	2022,12,15,15,도심권,종로구,44,53,드럽
//	...
public class UCMain5 {
	public static void main(String[] args) {
		BufferedWriter bw = null;
		HttpURLConnection huc = null;
		try {
			URL u = new URL("http://openapi.seoul.go.kr:8088/575a4655496b636839386f58586542/xml/RealtimeCityAir/1/25/");
			huc = (HttpURLConnection) u.openConnection();
			InputStream is = huc.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "utf-8");
			
			FileOutputStream fos = new FileOutputStream("C:/Kwon/dust.csv", true);
			OutputStreamWriter osw = new OutputStreamWriter(fos, "utf-8");
			bw = new BufferedWriter(osw);

			XmlPullParser xpp = XmlPullParserFactory.newInstance().newPullParser();
			xpp.setInput(isr);
			int type = xpp.getEventType();
			String tn = null;
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy,MM,dd,HH,");
			String now = sdf.format(new Date());
			while (type != XmlPullParser.END_DOCUMENT) {
				if (type == XmlPullParser.START_TAG) {
					tn = xpp.getName();
				} else if (type == XmlPullParser.TEXT) {
					if (tn.equals("MSRRGN_NM")) {
						bw.write(now);
						bw.write(xpp.getText() + ",");
					} else if (tn.equals("MSRSTE_NM") || tn.equals("PM10") || tn.equals("PM25")) {
						bw.write(xpp.getText() + ",");
					} else if (tn.equals("IDEX_NM")) {
						bw.write(xpp.getText() + "\r\n");
						bw.flush();
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
		try {
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		huc.disconnect();

	}
}
