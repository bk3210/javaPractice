package com.kim.dec162uc.main;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.StringTokenizer;

// 1. 이문열 삼국지 10권 다 콘솔에 출력
// 2. 유비, 조조, 손권이 몇번 나왔는지 -> 그 단어에 '유비' '조조' '손권'이 포함되어 있으면 걸리도록
public class UCMain6 {
	public static void main(String[] args) {
		HashMap<String, Integer> hm = new HashMap<>();

		String line = null;
//		int YCount = 0;
//		int JCount = 0;
//		int SCount = 0;
		for (int i = 1; i < 11; i++) {
			String fileName = String.format("[이문열]삼국지 %02d.txt", i);
			BufferedReader br = null;
			try {
				FileInputStream fis = new FileInputStream("C:\\Users\\user\\Desktop\\이문열 삼국지\\" + fileName);
				InputStreamReader isr = new InputStreamReader(fis, "euc-kr");
				br = new BufferedReader(isr);
				while ((line = br.readLine()) != null) {
					StringTokenizer st = new StringTokenizer(line, " "); // 단어 단위로 모두 줄나눔
					while (st.hasMoreTokens()) {
						String word = st.nextToken();
						String name = null;
						if (word.contains("유비") || word.contains("황숙") || word.contains("현덕")) {
//					YCount ++;
							name = "유비";
						} else if (word.contains("조조")) {
//					JCount ++;
							name = "조조";
						} else if (word.contains("손권")) {
//					SCount ++;
							name = "손권";
						}
						if (name != null) {
							if (hm.containsKey(name)) {
								int a = hm.get(name);
								a += 1;
								hm.put(name, a); // map reduce / wordcount
							} else {
								hm.put(name, 1);
							}
						}
					}
				}
//			System.out.println ("유비 : " + YCount);
//			System.out.println("조조 : " +JCount);
//			System.out.println("손권 : " + SCount);
			} catch (Exception e) {
				e.printStackTrace();
				try {
					br.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		Set<String> s = hm.keySet();
		ArrayList<String> al = new ArrayList<>(s);
		for (String string : al) {
			System.out.println(string);
			System.out.println(hm.get(string));
		}
	}
}
