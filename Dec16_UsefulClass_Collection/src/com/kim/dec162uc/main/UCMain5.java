package com.kim.dec162uc.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

// Map 계열(Python : dict, obj-c : NSdictionary) : 순서 개념이 없음
//							키와 값이 쌍으로 되어 있음(찾는 기준을 지정)
public class UCMain5 {
	public static void main(String[] args) {
		HashMap<String, Double> hm = new HashMap<>();
		hm.put("서울", -10.5);		//   String으로 찾으면 Double값을 가져옴
		hm.put("수원", -9.5);
		hm.put("인천", -8.5);
		hm.put("서울", -7.5);
		System.out.println(hm.get("서울"));
		// 즉, 인덱스값이 아니라 찾는 기준(키)으로 데이터를 가져오고 싶을 때는 HashMap을 이용한다
		// 중복 키값이 입력되면 뒤에 삽입된 값이 도출된다
		// 그래서 add가 아니라 put
		System.out.println(hm.containsKey("인천"));			// 중복 키값 있는지 검색
		System.out.println("=============");

		// Map에는 순서 개념이 없어서 반복문과 어울리지 않음, 그래도 Map을 반복문으로 쓰려면
		Set<String> s =hm.keySet();				// Map에서 Key만 추출해서 Set으로
		ArrayList<String> al = new ArrayList<>(s);		// 정렬
		for (String string : al) {
			System.out.println(string);
			System.out.println(hm.get(string));
		}
	}

}
