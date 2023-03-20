package com.kim.dec22jdbc.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

// 입력받기
// DB서버 연결 - 실패할 가능성이 높음(서버가 구려서...)

// DB 연결 패턴(순서) : 일단 연결 -> 데이터 확보 
public class InsertMain {
	public static void main(String[] args) {
			Connection con = null;
			Scanner scanner = new Scanner(System.in);
			PreparedStatement pstmt = null;
			try {
				// 연결시 사용할 Driver를 지정하자 => url주소에 oracle이라고 적혀 있으니까 Java가 주소만 봐도 오라클 건줄 다 알아서 눈치껏 지정해줌
				// Class.forName("oracle.jdbc.driver.OracleDriver");				이거 쓸 필요 없음
				// 근데 쌩 Java는 알아서 찾아주는데 JSP, Spring에서는 이 방식(JDBC방식)으로 연결할 생각이면 써야 함
				
				// 연결할 DB서버 주소(DB 브랜드별로 양식이 다름 - 확인은 Database Connection -> properties)
				String url = "jdbc:oracle:thin:@192.168.0.32:1521:xe"; 
				
				// 연결
				con = DriverManager.getConnection(url, "bk", "2030");
				System.out.println("연결 성공");				
				
				// 데이터 확보
				System.out.print("지점명 : ");
				String storeName = scanner.next();
				System.out.print("위치 : ");
				String storeLocation = scanner.next();
				System.out.print("크기 : ");
				int size = scanner.nextInt();
				
				// SQL문 작성(값 들어간 부분은 ?로 처리해서 일단 미완성 상태로, 원문의 ;은 빼고)
				String sql = "INSERT INTO DEC22_BOOKSTORE VALUES(?, ?, ?)";
				
				// 1. SQL 완성
				// 2. DB서버로 완성된 SQL 전송
				// 3. 원격실행
				// 4. 결과 받아와
				// 	-> 2~4번의 이 DB 관련 작업을 다 해주는 매니저가 있음 - 1회용
				pstmt = con.prepareStatement(sql);
				
				
				// 1. SQL 완성
				pstmt.setString(1, storeName);
				pstmt.setString(2, storeLocation);
				pstmt.setInt(3, size);
				
				// 2. DB서버로 완성된 SQL 전송
				// 3. 원격실행
				// 4. 결과 받아와
				int row = pstmt.executeUpdate();
				
				// 5. 결과 확인
				if(row == 1) {
					System.out.println("등록 성공");
				} else {
					System.out.println("등록 실패");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {pstmt.close();} catch (Exception e) {}				// .close 메서드들의 닫는 순서를 잘 지키자(항상 역순으로, DB연결이 먼저 닫히면 안됨)
			try {con.close();} catch (Exception e) {}				// 연결 해제, 나중에 되면 닫을 게 너무 많아져서 그냥 엔터 줄이고 이런 식으로 짧게 씀
			scanner.close();  				// 얜 그냥 try-catch 안해도 뭐라고 안함
		}
}
