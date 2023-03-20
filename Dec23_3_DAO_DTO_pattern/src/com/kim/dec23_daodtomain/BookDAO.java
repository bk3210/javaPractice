package com.kim.dec23_daodtomain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import bom.kim.db.manager.KimDBManager;

// DAO/DTO 패턴 : MVC 패턴 + DB 이야기 추가
// DAO(Data Access Object) : DB 관련 작업을 하는 M
// DTO(Data Temp/Transfer Object) : 데이터를 저장하기 위해 임시로 저장하는 객체

// 메소드명은 동사스럽게 지어야
// 작업 끝나면 abcd 순서로 정렬 - 그래야 같은 기능들끼리 같이 볼 수 있음

// 여기서 Data 가져오는 작업을 수행해야됨
public class BookDAO {
	public static ArrayList<Book> getBook() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String url = "jdbc:oracle:thin:@192.168.0.32:1521:xe";
			// 연결
			con = KimDBManager.connect(url, "bk", "2030");
			System.out.println("연결 성공");
			String sql = "SELECT B_NAME, B_PRICE FROM DEC22_BOOK";
			// 1.DB작업 수행해주는 매니저 호출
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery(); // select문 수행
			
			ArrayList<Book> books = new ArrayList<>();	
			while (rs.next()) {
				books.add(new Book(rs.getString("b_name"), rs.getInt("b_price")));				// 만들어진 Book 객체를 ArrayList에 집어넣음
			}
			return books;
		} catch (Exception e) {
			e.printStackTrace();
			return null;						// 제대로 삽입되지 않았을 땐 return null
		}finally {					// 최종적으로 세 개를 닫기
			KimDBManager.close(con, pstmt, rs);
		}
	}
}