package com.kim.dec23mvc.main;

import java.util.Scanner;

// .close는 한번 닫으면 다시는 쓸 수 없음
// System.in : 콘솔창->프로그램쪽으로 붙어있는 빨대
// Scanner : 빨대 보조역할

public class ConsoleScreen {
	private Scanner strawSystem = new Scanner(System.in);

	public ConsoleScreen() {
		strawSystem = new Scanner(System.in);
	}
	
	public int getX() {
		System.out.print("x : ");
		return strawSystem.nextInt();
	}
	
	public int getY() {
		System.out.print("y : ");
		return strawSystem.nextInt();
	}
	
	public void close() {
		strawSystem.close();							// 사용 완료된 scanner는 닫아야
	}
	
	public void printResult(CalcResult cr) {
		System.out.println(cr.getPlus());
		System.out.println(cr.getMinus());
		System.out.println(cr.getMulti());
		System.out.println(cr.getDivise());
	}
}
