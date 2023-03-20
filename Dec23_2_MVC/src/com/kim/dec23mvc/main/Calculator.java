package com.kim.dec23mvc.main;

// 메소드 결과가 여러 개 나와야 할 때 : 배열, List, Set, Map
public class Calculator {
	public static CalcResult calculate (int x, int y) {			// 계산하면 calcresult 객체가 나옴
		int a = x + y;
		int b = x - y;
		int c = x * y;
		int d = x / y;
		return new CalcResult(a, b, c, d);
	}

}
