package com.kim.dec162uc.main;

import java.util.ArrayList;
import java.util.Comparator;

import com.kim.dec162uc.practice.Student;

public class UCMain2 {
	public static void main(String[] args) {
		// 홍길동, 국100, 영90, 수80인 학생
		// 정보 출력

		Student s = new Student("홍길동", 100, 90, 80);
		s.print();
		System.out.println("------------------");

		ArrayList<Student> students = new ArrayList<Student>();
		students.add(s); // ArrayList에 홍길동 입력
		students.add(new Student("김길동", 90, 50, 30)); // 객체 생성과 자료입력을 동시에 - 이해 안된다면 다형성 부분 다시 복습!
		students.add(new Student("이길동", 20, 10, 50));
		students.add(new Student("박길동", 30, 60, 20));
		students.add(new Student("최길동", 60, 70, 80));

		// 두번째 학생 수학점수
		System.out.println(students.get(1).getMat());
		System.out.println("==========");

		// 세번째 학생 정보
		students.get(2).print();
		System.out.println("==========");

		// 전부 다
		for (Student student : students) {
			student.print();
		}
		System.out.println("==========");

		// 1등 학생의 이름 => 총점 내림차순 -> 첫번째 학생 이름 출력
		students.sort(new Comparator<Student>() {
			@Override
			public int compare(Student o1, Student o2) {
				Integer o1Score = o1.getKor() + o1.getEng() + o1.getMat();
				Integer o2Score = o2.getKor() + o2.getEng() + o2.getMat();
				return o2Score.compareTo(o1Score);
			}
		});
		System.out.println(students.get(0).getName());
		System.out.println("==========");
		
		// 이름 가나다 순으로 정렬해서 출력
		students.sort(new Comparator<Student>() {
			@Override
			public int compare(Student o1, Student o2) {
				String o1Name = o1.getName();
				String o2Name = o2.getName();
				return o1Name.compareTo(o2Name);
			}
		
		});
		for (Student student : students) {
				System.out.println(student.getName());		// 이름만
		}
		System.out.println("==========");
		
//		System.out.println(students);										// 학생들 주소
//		System.out.println(students.get(0));							// 첫번째 학생 주소
//		System.out.println(students.get(0).getName());			// 첫번째 학생 이름 String
		
		}

}