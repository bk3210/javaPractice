package com.kim.dec162uc.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.kim.dec162uc.practice.StationInfo;

// 가장 이용객수(탄사람 수 + 내린사람 수)가 많았던 지하철 구하기
// 언제 몇호선 무슨역

// 2015/01/01(수)
// 1호선
//	서울역
// 30000
// 20000
public class UCMain3 {
	public static void main(String[] args) {
		BufferedReader br = null;
		try {
			FileInputStream fis = new FileInputStream("C:\\Users\\user\\Desktop\\밀화부리\\subway.csv");
			InputStreamReader isr = new InputStreamReader(fis, "utf-8");
			br = new BufferedReader(isr);
			String line = null;
			ArrayList<StationInfo> al = new ArrayList<StationInfo>();
			while ((line = br.readLine()) != null) {
//				if(line.startsWith("2015,04")) {							// 3개월치만 일단 받아내기로
//					break;
//				}
				al.add(new StationInfo(line));
			}
//				StationInfo si = new StationInfo(line);
//				si.print();
				// 클래스 작업
				// split해서 객체 생성 -> split 작업을 클래스쪽 생성자에
				// System.out.println(line);
			
			// 이렇게 받아온 데이터를 정렬해서 ArrayList<객체>로
					
			al.sort(new Comparator<StationInfo>() {
				@Override
				public int compare(StationInfo o1, StationInfo o2) {
					Double o1Passenger = o1.getInPassenger() + o1.getOutPassenger();
					Double o2Passenger = o2.getInPassenger() + o2.getOutPassenger(); 
					return o2Passenger.compareTo(o1Passenger);					
				}
			});
			
			al.get(0).print();

		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			br.close();
		} catch (IOException e) {

		}
	}
	
}
