package com.kim.dec161uc.main;

import java.util.Iterator;

import practice.MyThread;
import practice.MyThread2;

// 이렇게 돌리면 두 개의 작업이 동시에 이뤄지지 않고 ㅋ 찍힌 다음에 ㅎ가 찍힘
// 둘이 같이 동시에 하면 안됨??
// 우리가 프로그램을 실행할 때는 JVM이 Thread라는 걸 만들어서
// UCMain.main(...)으로 보냄 -> Thread가 프로그램 동작 절차를 제어함
// 즉 여러 작업을 동시에 작업하려면 여러 개의 Thread를 만들어야 함

// MultiThread
// JVM이 만들어 준 redThread : mainThread
// 개발자가 만들어 쓰는 blueThread : subThread
public class UCMain {
	public static void main(String[] args) {
		MyThread mt = new MyThread();
		mt.start();									// 새 thread 만들어서 run 실행
		
		Thread t = new Thread(new MyThread2());
		t.start();			// 위 thread와 더불어 작업 3개가 동시에
		
		for (int i = 0; i < 10; i++) {
			System.out.println("ㅋ");
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		for (int i = 0; i < 10; i++) {
			System.out.println("ㅎ");
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
