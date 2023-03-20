package com.kwon.dec151uc.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

// HTTP통신 기능이 자바에 없다
//		자바 버전 올라가다가 추가

// HTTP통신 과정
// 이것이 자바다
public class UCMain {
	public static void main(String[] args) {
		try {
			DefaultHttpClient dhc = new DefaultHttpClient();
			
			// GET방식 요청
			HttpGet hg = new HttpGet("https://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1168052100");

			HttpResponse hr = dhc.execute(hg);
			
			// 응답내용
			HttpEntity he = hr.getEntity();
			
			InputStream is = he.getContent();
			InputStreamReader isr = new InputStreamReader(is, "utf-8");
			BufferedReader br = new BufferedReader(isr);
			
			System.out.println(br.readLine());
			System.out.println(br.readLine());
			System.out.println(br.readLine());
			System.out.println(br.readLine());
			System.out.println(br.readLine());
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}
}




