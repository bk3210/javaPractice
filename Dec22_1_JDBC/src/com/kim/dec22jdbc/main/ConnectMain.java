package com.kim.dec22jdbc.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import oracle.jdbc.driver.OracleDriver;

//	DB서버와 통신
// Java에서 수많은 브랜드의 제각각 다른 DB들과 통신하려면 그만큼 다양한 어댑터가 필요함
//			통신은 가능하지만, 그 서버에 연결할 때 쓸 Driver를 만들어줄 수가 없음 -> 만들어야 함
// Driver는 콘센트 어댑터 개념이라고 생각하자(110V, 220V 모양 다르듯이)
// 그냥 만들지 말고 ojdbc8.jar 찾아서 쓰자(다른 DB회사들 것도 자기들이 다 만들어줬음)

// dec19~21까지의 수업내용은 OracleDB를 원격제어(정품 프로그램(sqlplus)은 다소 불편해서 다른 대안책으로 선택한 것이 eclipse라는 툴)
// 지금은 OracleDB랑 연동되는 Java 프로그램을 만들고자 하는 것 - 착각하지 말자!
// (여기에 필요한 Driver 역할은 .jar 파일로 가져온 것 = 필수적으로 필요)
public class ConnectMain {
	public static void main(String[] args) {
		Connection con = null;
		try {
			// 연결시 사용할 Driver를 지정하자 => url주소에 oracle이라고 적혀 있으니까 Java가 주소만 봐도 오라클 건줄 다 알아서 눈치껏 지정해줌
			// Class.forName("oracle.jdbc.driver.OracleDriver");				이거 쓸 필요 없음
			// 근데 쌩 Java는 알아서 찾아주는데 JSP, Spring에서는 이 방식(JDBC방식)으로 연결할 생각이면 써야 함
			
			// 연결할 DB서버 주소(DB 브랜드별로 양식이 다름 - 확인은 Database Connection -> properties)
			String url = "jdbc:oracle:thin:@192.168.0.32:1521:xe"; 
			
			// 연결
			con = DriverManager.getConnection(url, "bk", "2030");
			System.out.println("연결 성공");
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {con.close();} catch (Exception e) {}				// 연결 해제, 나중에 되면 닫을 게 너무 많아져서 그냥 엔터 줄이고 이런 식으로 짧게 씀
	}
}
