package com.kim.dec23mvc.main;

// 두 값을 입력받아 사칙연산을 하는 프로그램
public class HomeController {
	public static void main(String[] args) {
		ConsoleScreen cs = new ConsoleScreen();
		int x = cs.getX();
		int y = cs.getY();
		System.out.println("==============");
		CalcResult cr = Calculator.calculate(x, y);
		cs.printResult(cr);
		cs.close();
	}

}
