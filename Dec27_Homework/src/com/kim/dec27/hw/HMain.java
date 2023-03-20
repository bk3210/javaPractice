package com.kim.dec27.hw;

public class HMain {
	public static void main(String[] args) {
		Referee r = new Referee();
		User u = new User();
		Enemy e = new Enemy();			// 객체 생성
		r.start(u, e);						// start 메서드를 실행하기 위해 필요한 재료들(두 선수) 파라메터로 넣어주기
}
}