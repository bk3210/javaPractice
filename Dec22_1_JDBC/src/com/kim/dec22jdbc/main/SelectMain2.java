package com.kim.dec22jdbc.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Scanner;

// 지점명 입력하면 카테고리별 평균가가 뜨도록
public class SelectMain2 {
	public static void main(String[] args) {
		Connection con = null;
		Scanner scanner = new Scanner(System.in);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// 연결시 사용할 Driver를 지정하자 => url주소에 oracle이라고 적혀 있으니까 Java가 주소만 봐도 오라클 건줄 다 알아서
			// 눈치껏 지정해줌
			// Class.forName("oracle.jdbc.driver.OracleDriver"); 이거 쓸 필요 없음
			// 근데 쌩 Java는 알아서 찾아주는데 JSP, Spring에서는 이 방식(JDBC방식)으로 연결할 생각이면 써야 함

			// 연결할 DB서버 주소(DB 브랜드별로 양식이 다름 - 확인은 Database Connection -> properties)
			String url = "jdbc:oracle:thin:@192.168.0.32:1521:xe";

			// 연결
			con = DriverManager.getConnection(url, "bk", "2030");
			System.out.println("연결 성공");
			
			System.out.print("찾는 지점명 : ");
			String storeName = scanner.next();

			String sql = "SELECT B_CATEGORY, avg(b_price) FROM DEC22_BOOK WHERE B_NO IN (SELECT O_B_NO FROM DEC22_ORDER WHERE O_BS_NAME IN (SELECT BS_NAME FROM DEC22_BOOKSTORE WHERE BS_NAME LIKE '%'||?||'%')) group by b_category order by b_category";

			// 1.DB작업 수행해주는 매니저 호출
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, storeName);
			// 2. R : 
			rs = pstmt.executeQuery();			// 얘도 나중에 닫아줘야 함
			// C/U/D :
			// int row = pstmt.executeUpdate();
			
			// 날짜형이 java.sql이랑 java.util 서로 달라서 바꿔주는 메서드
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd (E)");
			// rs.next() : 커서를 다음 데이터로 보내는 기능 + 다음 데이터가 없으면 false 찍어주는 기능
			while(rs.next()) {
				// rs.getXXX("필드명")
				System.out.println(rs.getString("b_category"));
				System.out.println(rs.getDouble("avg(b_price)"));
				System.out.println("==============");
			}
		} catch (Exception e) {
			e.printStackTrace();
			}
		try {rs.close();} catch (Exception e) {} // .close 메서드들의 닫는 순서를 잘 지키자(항상 만들어진 역순으로, DB연결이 먼저 닫히면 안됨)
		try {pstmt.close();} catch (Exception e) {} // .close 메서드들의 닫는 순서를 잘 지키자
		try {con.close();} catch (Exception e) {} // 연결 해제, 나중에 되면 닫을 게 너무 많아져서 그냥 엔터 줄이고 이런 식으로 짧게 씀
		scanner.close(); // 얜 그냥 try-catch 안해도 뭐라고 안함
	}

}
