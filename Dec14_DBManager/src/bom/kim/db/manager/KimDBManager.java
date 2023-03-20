package bom.kim.db.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class KimDBManager {
	public static Connection connect(String url, String id, String pw) throws Exception {					// 이 메서드의 결과는 Connection, 예외처리는 메인단에서
		return DriverManager.getConnection(url, id, pw);
	}
	
	public static void close(Connection con, PreparedStatement pstmt, ResultSet rs) {
		try {rs.close();} catch (Exception e) {} // .close 메서드들의 닫는 순서를 잘 지키자(항상 만들어진 역순으로, DB연결이 먼저 닫히면 안됨)
		try {pstmt.close();} catch (Exception e) {} // .close 메서드들의 닫는 순서를 잘 지키자
		try {con.close();} catch (Exception e) {} // 연결 해제, 나중에 되면 닫을 게 너무 많아져서 그냥 엔터 줄이고 이런 식으로 짧게 씀
	}

}
