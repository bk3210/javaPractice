package com.kim.dec151uc.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

// 조건

// => http통신

// http 통신 기능이 자바에 없다고???
// 이것이 자바다

// 통신
//			HTTP 통신(인터넷) : 요청 -> 응답 통신
//			Socket 통신(카카오톡) : 실시간 통신

// 서버 : 서비스를 제공하는 사람(컴퓨터)
// 클라이언트 : 서비스를 제공받는 손님(컴퓨터)

// 자바에 HTTP 통신 기능이 없음
//			자바 개발자가 해야할 일 : 다른 개발자가 만들어놓은 기능을 잘 찾아 쓰는거
public class UCMain {
	public static void main(String[] args) {
		// 객체 생성 -> GET방식 요청 -> 요청수행
		
		// 요청
		try {
			// 기본 HTTP클라이언트 객체 생성
			DefaultHttpClient test = new DefaultHttpClient();			// deprecation warning
			
			// GET방식 요청
			HttpGet hg = new HttpGet("https://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1132066000");			// 기상청 rss 주소
			
			//DDoS
			while(true) {
				HttpResponse hr = test.execute(hg);
				// 응답내용(utf-8로 줌)
				HttpEntity he = hr.getEntity();
				InputStream is = he.getContent();
				InputStreamReader isr = new InputStreamReader(is, "utf-8");		// 한 글자씩 정보를 가져올 수 있는 빨대
				BufferedReader br = new BufferedReader(isr);
				
				System.out.println(br.readLine()); 
				System.out.println(br.readLine()); 
				System.out.println(br.readLine()); 
				System.out.println(br.readLine()); 
				System.out.println(br.readLine()); 
				System.out.println(br.readLine()); 
				System.out.println(br.readLine()); 
				System.out.println(br.readLine()); 
			}
			
					
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
