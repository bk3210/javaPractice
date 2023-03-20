package com.kwon.dec162uc.main;

import java.util.ArrayList;
import java.util.Comparator;

import com.kwon.dec162uc.student.Student;

public class UCMain2 {
	public static void main(String[] args) {
		Student s = new Student("홍길동", 100, 90, 80);
		s.print();
		System.out.println("-----");
		ArrayList<Student> students = new ArrayList<>();
		students.add(s);
		students.add(new Student("김길동", 90, 50, 30));
		students.add(new Student("이길동", 9, 15, 99));
		students.add(new Student("박길동", 50, 75, 80));
		System.out.println("-----");
		
		// 두번째학생 수학점수 : 의미 알기 편함
		System.out.println(students.get(1).getMath());
		System.out.println("-----");

		// 세번째학생 정보 : 메소드 활용
		students.get(2).print();
		System.out.println("-----");
		
		// 전부 다
		for (Student student : students) {
			student.print();
		}
		System.out.println("-----");
		
		// 1등 학생 이름 => 총점 내림차순 -> 첫번째 학생
		students.sort(new Comparator<Student>() {
			@Override
			public int compare(Student o1, Student o2) {
				Integer o1Score = o1.getKor() + o1.getEng() + o1.getMath();
				Integer o2Score = o2.getKor() + o2.getEng() + o2.getMath();
				return o2Score.compareTo(o1Score);
			}
		});
		System.out.println(students.get(0).getName());
		System.out.println("-----");
		
		students.sort(new Comparator<Student>() {
			@Override
			public int compare(Student o1, Student o2) {
				String o1Name = o1.getName();
				String o2Name = o2.getName();
				return o1Name.compareTo(o2Name);
			}
		});
		for (Student student : students) {
			student.print();
		}
	}
}