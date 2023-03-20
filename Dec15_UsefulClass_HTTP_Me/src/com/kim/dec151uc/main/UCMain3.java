package com.kim.dec151uc.main;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

// http : 일반
// https : 보안

// 서버 : 	우리한테 데이터를 주고 싶은데
//				정해진 형식으로 줘서 사용자가 split(->parsing)을 할 수 있게
//				데이터를 HTML 모양으로 표현 : XML							기상청 rss 화면에 뜨는 것도 이 형식
//		<학생들> : 시작태그
//				<학생>
//						<이름>홍길동</이름>		DOM(Document Object Model) 객체 : 시작태그-텍스트(정보입력)-종료태그(슬래시 달린거)	<- 가 한 세트
//						<나이>18</나이>
//				</학생>
//		</학생들>
//				데이터를 JavaScript 모양으로 표현한 것 : JSON

// XML을 parsing : 관련기능이 Java에는 존재하지 않음
// 데이터 parsing : 데이터를 가공해서 원하는 형태로 만드는 과정
public class UCMain3 {
	public static void main(String[] args) {
		try {
			String s = "https://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1132066000";
			URL u = new URL(s);		// 실제 인터넷 객체로 반환해줌
			// http or ttps 어느거 쓸지 선택
			// 여기선 httpURLConnection huc;
			
			// 기상청과 연결
			HttpsURLConnection huc = (HttpsURLConnection) u.openConnection();			// 형변환해서 사용
			
			InputStream is = huc.getInputStream();
//			InputStreamReader isr = new InputStreamReader(is, "utf-8");		// 한 글자씩 정보를 가져올 수 있는 빨대
//			BufferedReader br = new BufferedReader(isr);
//			String line = null;
//			while ((line = br.readLine()) != null) {
//				try{System.out.println(line);
//				
			// 객체
			//		필요없으면 - static
			//		필요하면 - 평소대로
			//				하나만 - singleton
			//				대량생산 규칙성 : factory 패턴
						
			// factory pattern : 객체를 막 만드는 게 아니라, 오직 한 공장을 통해서만 만들어낼 수 있는 패턴
			XmlPullParserFactory xppf = XmlPullParserFactory.newInstance();
			XmlPullParser xpp = xppf.newPullParser();		// parsing할 객체 생성
			xpp.setInput(is, "utf-8");									// 리더 빨대 준비
			
			// 지금 빨아낸 게 돔 객체 셋 중 무엇인지
			int what = xpp.getEventType();		// 여기선 시작태그
			
			// python : 전체를 통째로 처리하는 방식
			// java : 빨대로 하나씩 뽑아내는 방식
			
			while(what != XmlPullParser.END_DOCUMENT) {		// 문서 끝까지 빨아낼 때까지 계속 돌아감
				// 빨아낸 자료 parsing
				if(what == XmlPullParser.START_TAG) {		// 만약 빨아낸 게 시작태그면
					System.out.println(xpp.getName());
				} else if (what == XmlPullParser.TEXT) {		// 만약 빨아낸 게 텍스트면
					System.out.println(xpp.getText());
				} else if (what == XmlPullParser.END_TAG) {		// 만약 빨아낸 게 종료태그면
					System.out.println(xpp.getName());
				}
				xpp.next();		// 다음 거 빨아들이기
				what = xpp.getEventType();
			
				}
			 huc.disconnect();							// 연결된 사이트와의 연결을 끊어줌
		}catch (Exception e) {
			// TODO: handle exception
					e.printStackTrace();
		}
		}
	}
