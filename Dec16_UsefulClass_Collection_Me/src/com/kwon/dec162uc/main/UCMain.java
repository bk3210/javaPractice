package com.kwon.dec162uc.main;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Vector;

// []
//		객체지향언어에서 뭐하는...
//		만들때 사이즈 고정, 변경불가
//			사이즈 모르면 못만들거고...

// Collection
//		객체만
//		데이터 섞어서 - 딱히 장점이라고는...
//		가변사이즈

// List계열 : 가변사이즈 배열
// Set계열
// Map계열

// <클래스명> : generic
//		그 자료형만 넣게

public class UCMain {
	public static void main(String[] args) {
		Vector<Integer> al3;
		
		ArrayList<Integer> al2 = new ArrayList<>();
		// al2.add("ㅋ");
		al2.add(10);		al2.add(30);
		al2.add(5);
		al2.add(1, 100);	al2.set(0, 999);
		al2.remove(2);

		Comparator<Integer> c = new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1.compareTo(o2);
			}
		};
		al2.sort(c);
		
		for (Integer a : al2) {
			System.out.println(a);
		}
		System.out.println("-----");
		
		for (int i = 0; i < al2.size(); i++) {
			System.out.println(al2.get(i));
		}
		System.out.println("-----");
		ArrayList al = new ArrayList();
		al.add(10);
		al.add("ㅋ");
		System.out.println(al.get(0));
		System.out.println(al.get(1));
	}
}




