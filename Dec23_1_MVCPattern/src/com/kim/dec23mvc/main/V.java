package com.kim.dec23mvc.main;
//V : View, 실제로 사용자에게 보여지는 영역. 결과를 출력하고, 입력을 받는 역할. front-end 디자이너 책임 영역

import java.util.Scanner;

public class V {
	public static int getX() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("x : ");
		int x = scanner.nextInt();
		return x;
	}
	
	public static void print(String r) {
		System.out.println(r);
	}
}
