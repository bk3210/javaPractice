package com.kim.dec23mvc.main;
// 프로젝트
//			고객
//			PM
//			PL
//			개발자
//			디자이너			-> 여럿이서 굴러감

//조별과제 : 여럿이서 같이 한 작업해서 힘들다
//MVC : 프로그래밍 패턴의 한 종류
//				한 파일은 한 명이 책임지고 끝내자
//				한 파일은 M/V/C 중에 한 역할만 하자

// M : Model, 비즈니스 로직. 실제 계산이 이루어지는 영역. 개발자 책임 + 고객과의 커뮤니케이션이 필요
// V : View, 실제로 사용자에게 보여지는 영역. 결과를 출력하고, 입력을 받는 역할. 디자이너 책임 영역
// C : Controller, 플레이메이커. 상황을 판단해서 M/V중 필요한 곳을 꺼내어 흐름을 제어하는 역할. PL급 개발자 책임

// Java
// DB
// JDBC
// HTML + CSS : 웹 기본
// JSP : JDBC + CSS -> 이 단계에서 웹사이트 완성 가능
// JSP Model 2 : JSP에 MVC 패턴을 부여
// Spring : JSP Model 2의 발전형

// 정수 두 개를 받아서 합 출력하는 프로그램

// 프로그램 실행 -> controller가 요청받음 -> V 소환 -> V에서 입력받은 값을 C로 가져옴 
// -> M에 있는 연산프로그램 가져오기 -> C에서 연산 처리해서 결과 산출 -> V로 가서 최종결과 출력
public class C {
	public static void main(String[] args) {
		int x = V.getX();
		String r = M.judge(x);
		V.print(r);
		System.out.println("==============");
	}

}
