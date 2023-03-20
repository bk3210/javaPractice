package com.kim.dec161uc.main;

import practice.MyT;

// JVM이 만들어준 것 : mainThread
// 개발자가 만든 것 : subThrea
// main : 0.3초마다 ㅋ 출력 10번 -> 종료
// sub : 0.3초마다 ㅎ 출력 10번 -> 무한반복

// main이 동작 끝나도 sub는 혼자서 무한반복 수행함 -> multiThread 쓸 때 주의해야할 점
public class UCMain2 {
	public static void main(String[] args) {
		Thread t = new Thread(new MyT());
		t.start();
		
		for (int i = 1; i < 5; i++) {
			System.out.println("ㅋ");
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
