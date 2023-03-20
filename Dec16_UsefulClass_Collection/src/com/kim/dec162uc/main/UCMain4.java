package com.kim.dec162uc.main;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import com.kim.dec162uc.practice.Student;

// []
// List 계열 : 가변사이즈 배열
// Set 계열 : 집합. 중복값이 입력되면 알아서 중복을 제거해줌. HashSet
// Map 계열 :
public class UCMain4 {
	public static void main(String[] args) {
		HashSet<String> hs = new HashSet<>();
		hs.add("집");
		hs.add("가");
		hs.add("고");
		hs.add("싶");
		hs.add("다");
		hs.add("집");
		System.out.println(hs.size());		// 배열 사이즈
		// Set은 바로 쓸 수 있는 녀석이 아니라 List로 바꿔줌
		ArrayList<String> al = new ArrayList<>(hs);	// List로 바꿀 땐 생성자에 집어넣으면 됨
		for (String string : al) {
			System.out.println(al);
		}
		// 입력된 순서대로 값이 저장되는 게 아니라 랜덤으로 들어가기 때문에 써먹기 애매함
		System.out.println("=============");
		HashSet<Student> students = new HashSet<>();
		Student s = new Student("박길동", 0, 0, 0);
		students.add(s);
		students.add(s);
		students.add(s);												// 중복
		students.add(new Student("홍길동", 100, 100, 100));
		students.add(new Student("김길동", 99, 99, 99));
		students.add(new Student("김길동", 99, 99, 99));			// 중복x - 참조형의 특성을 잘 생각해 보자
		students.add(new Student("이길동", 98, 98, 98));
		System.out.println(students.size());
		
		System.out.println("=============");
		ArrayList<Integer> al2 = new ArrayList<>();
		al2.add(10);
		al2.add(50);
		al2.add(40);
		al2.add(20);
		al2.add(20);
		al2.add(20);
		al2.add(70);
		al2.add(70);
		al2.add(70);
		
		// 기본형급의 중복을 제거해줄 때 HashSet을 활용한다
		HashSet<Integer> hs2 = new HashSet<>(al2);
		al2 = new ArrayList<>(hs2);		// 다시 ArrayList로
		for(Integer integer : hs2) {
			System.out.println(hs2);
		}
	}

}
