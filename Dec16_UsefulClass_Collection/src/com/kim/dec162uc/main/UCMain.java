package com.kim.dec162uc.main;

import java.util.ArrayList;
import java.util.Comparator;


// 정렬 : Java에서 정렬을 어떻게 하는가? 쉬움
//			[] + for로 해내는건 어려움

public class UCMain {
	public static void main(String[] args) {
		ArrayList<Integer> al2 = new ArrayList<>();
		al2.add(null);
//		int a = 10;
//		Integer aa = new Integer(a);
		al2.add(10);
		al2.add(30);
		al2.add(5);
		al2.add(1, null);				// 1번 위치에 null을 넣어라 -> 뒤 인덱스들은 한칸씩 밀림
		al2.set(1, 999);				// 1번 위치를 999로 바꿔라 -> 뒤 인덱스 위치변화x
		al2.remove(2);				// 2번 인덱스 데이터를 지워라
		
		// al2 list를 정렬하는 법 : 숫자 뿐만아니라 글자도 정렬 가능
		// 정렬하고자 하는 list의 자료형을 맞춰서 comparator 객체를 생성
		Comparator<Integer>c  = new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1.compareTo(o2);	// 오름차순
//				return o2.compareTo(o1);	// 내림차순
			}
			
		};
 		al2.sort(c);
 		// ArrayList 대신에 Vector를 쓸 수도 있음(속도는 느리지만 순차적으로 처리하므로 multiThread 문제로부터 자유로울 수 있음)
		
		for (int i = 0; i < al2.size(); i++) {				// 갯수 셀 때 size, 접근할 때 get
			System.out.println(al2.get(i));
		}
		System.out.println("=============");		
		for (Integer a : al2) {
			System.out.println(a);
		}
		
		
		
		
//		System.out.println("=============");
//		ArrayList al = new ArrayList();
//		al.add(10);						// 리스트에 정보 추가
//		al.add("ㅋㅋ");					// 데이터 섞어 넣는 것도 가능(비추천)
//		System.out.println(al.get(0));		// 대괄호 대신 get()
//		System.out.println(al.get(1));
	}

}
