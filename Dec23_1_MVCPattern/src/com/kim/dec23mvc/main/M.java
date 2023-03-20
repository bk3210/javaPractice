package com.kim.dec23mvc.main;
//M : Model, 비즈니스 로직. 실제 계산이 이루어지는 영역. 개발자 책임 + 고객과의 커뮤니케이션이 필요


public class M {
	public static String judge (int x) {
		return (x % 2 == 0) ? "짝" : "홀";
	}
}
