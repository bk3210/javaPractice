package com.kwon.dec162uc.main;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;

import com.kwon.dec162uc.subway.StationInfo;

// 가장 이용객수(탄+내린)가 많았던
// 언제 몇호선 무슨역

// 운행정보들
// [], List, Set, Map

public class UCMain3 {
	public static void main(String[] args) {
		BufferedReader br = null;
		try {
			FileInputStream fis = new FileInputStream("C:/Kwon/subway.csv");
			InputStreamReader isr = new InputStreamReader(fis, "utf-8");
			br = new BufferedReader(isr);
			String line = null;
			ArrayList<StationInfo> al = new ArrayList<>();
			while ((line = br.readLine()) != null) {
				al.add(new StationInfo(line));
			}
			
			al.sort(new Comparator<StationInfo>() {
				@Override
				public int compare(StationInfo o1, StationInfo o2) {
					Double o1Sum = o1.getRide() + o1.getAlight();
					Double o2Sum = o2.getRide() + o2.getAlight();
					return o2Sum.compareTo(o1Sum);
				}
			});
			
			al.get(0).print();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
