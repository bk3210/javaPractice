package com.kim.sddevice.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import bom.kim.db.manager.KimDBManager;

public class CardDAO {
	// 프로그램이 실행될 때 총 카드 갯수는 한번만 세도록 하자
	private int totalCount; // -> 그러려면 모든 메소드에 static이 빠짐, 총 갯수 세는 메소드는 생성자에

	public CardDAO() { // 생성자(총 갯수 세기)
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = KimDBManager.connect("jdbc:oracle:thin:@192.168.0.32:1521:xe", "bk", "2030");
			String sql = "select count(*) from dec23_mythic";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			totalCount = rs.getInt("count(*)");
//			System.out.println(totalCount);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("다시 확인하세요");
		} finally {
			KimDBManager.close(con, pstmt, rs);
		}
	}

	public int getPageCount() {
		double cardPerPage = 5;
		int pageCount = (int) Math.ceil(totalCount / cardPerPage); // 올림 처리해주는 메소드
		return pageCount;
	}

	public String insertCard(Card c) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = KimDBManager.connect("jdbc:oracle:thin:@192.168.0.32:1521:xe", "bk", "2030");
			String sql = "insert into dec23_mythic values (?,?,?,?,?)";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, c.getName());
			pstmt.setString(2, c.getCardName());
			pstmt.setInt(3, c.getDa());
			pstmt.setInt(4, c.getVo());
			pstmt.setInt(5, c.getPf());

			if (pstmt.executeUpdate() == 1) {
				totalCount++;
				return "성공";
			}
			return "실패";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("다시 확인하세요");
			return "실패";
		} finally {
			KimDBManager.close(con, pstmt, null);
		}
	}

	public ArrayList<Card> getCardInfo(int pageNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = KimDBManager.connect("jdbc:oracle:thin:@192.168.0.32:1521:xe", "bk", "2030");
			int cardPerPage = 5;
			int start = ((pageNo - 1) / (cardPerPage)) * (cardPerPage + 1);
			int end = pageNo * cardPerPage;

			String sql = "select * from (select rownum as rn, m_card, m_name, m_da, m_vo, m_pf from (select * from dec23_mythic order by m_card, m_name)) where rn >= ? and rn <= ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			ArrayList<Card> cards = new ArrayList<>();
			while (rs.next()) {
				cards.add(new Card(rs.getString("m_card"), rs.getString("m_name"), rs.getInt("m_da"), rs.getInt("m_vo"),
						rs.getInt("m_pf")));

			}
			return cards;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("다시 확인하세요");
			return null;
		} finally {
			KimDBManager.close(con, pstmt, rs);
		}
	}

	public String deleteCard(String result3) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = KimDBManager.connect("jdbc:oracle:thin:@192.168.0.32:1521:xe", "bk", "2030");
			String sql = "delete from dec23_mythic where m_name like '%'||?||'%'";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, result3);
			// pstmt.setString(2, result3);

			if (pstmt.executeUpdate() == 1) {
				totalCount--;
				return "삭제되었습니다";
			}
			return "실패";
		} catch (Exception e) {
			System.out.println("데이터가 존재하지 않습니다");
			return "실패";
		} finally {
			KimDBManager.close(con, pstmt, null);
		}

	}

	public ArrayList<Card> searchCard(String result3) {
				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				try {
					con = KimDBManager.connect("jdbc:oracle:thin:@192.168.0.32:1521:xe", "bk", "2030");
					String sql = "select * from dec23_mythic where m_name like '%'||?||'%'";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, result3);
					rs = pstmt.executeQuery();

					ArrayList<Card> cards = new ArrayList<Card>();
					while(rs.next()) {
							cards.add(new Card(rs.getString("m_card"), rs.getString("m_name"), rs.getInt("m_da"), rs.getInt("m_vo"), rs.getInt("m_pf")));
							}
					return cards;
				}catch(Exception e){
					e.printStackTrace();
					return null;
			}finally{
		KimDBManager.close(con, pstmt, rs);
	}
}}

//	public static ArrayList<Card> getDetail(Card c) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		Scanner sc = new Scanner(System.in);
//		try {
//			String url = "jdbc:oracle:thin:@192.168.0.32:1521:xe";
//			// 연결
//			con = KimDBManager.connect(url, "bk", "2030");
//			System.out.println("연결 성공");
//						
//			String sql = "SELECT * FROM DEC23_MYTHIC WHERE M_NAME = '%'||?||'%'";
//			// 1.DB작업 수행해주는 매니저 호출
//			pstmt = con.prepareStatement(sql);
//			pstmt.setString(1, name);
//			
//			rs = pstmt.executeQuery(); // select문 수행
//			
//			ArrayList<Card> cards = new ArrayList<Card>();	
//			while (rs.next()) {
//				cards.add(new Card(rs.getString("name"), rs.getString("cardName"), rs.getInt("da"), rs.getInt("vo"), rs.getInt("pf")));				// 만들어진 Book 객체를 ArrayList에 집어넣음
//			}
//			return cards;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;						// 제대로 삽입되지 않았을 땐 return null
//		}finally {					// 최종적으로 세 개를 닫기
//			KimDBManager.close(con, pstmt, rs);
//		}
//	}
