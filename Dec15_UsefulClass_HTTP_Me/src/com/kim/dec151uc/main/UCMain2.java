package com.kim.dec151uc.main;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

// http : 일반
// https : 보안
// 


// 최근 추가된 신기술
public class UCMain2 {
	public static void main(String[] args) {
		try {
			String s = "https://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1132066000";
			URL u = new URL(s);		// 실제 인터넷 객체로 반환해줌
			// http or ttps
			// httpURLConnection huc;
			
			// 기상청과 연결
			HttpsURLConnection huc = (HttpsURLConnection) u.openConnection();			// 형변환해서 사용
			
			InputStream is = huc.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "utf-8");		// 한 글자씩 정보를 가져올 수 있는 빨대
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
			huc.disconnect();							// 연결된 사이트와의 연결을 끊어줌
																	// 단, 서버에는 세션 개념이 있어서 일정시간 뒤 자동으로 연결이 끊어지게 함
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	

}
