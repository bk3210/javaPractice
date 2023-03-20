package com.kim.sddevice.main;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

// C : PL급 백엔드 개발자
public class HomeController {
	public static void main(String[] args) {
		
//		Date d = new Date();		// java.util.Date
//												// sql에서 import하는 건 java.sql.Date
//		
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//		String sql = "to_date(?, 'YYYYMMDD')";
//		pstmt.setString(1, sdf.format(d));
		
//		// java.util.Date -> java.sql.Date
//		java.sql.Date dd = new java.sql.Date(d, getTime());
//		String sql2 = "?";
//		pstmt.setDate(1, dd);
		
		ConsoleScreen cs = new ConsoleScreen();
		CardDAO cc = new CardDAO();
		Card c = null;
		int page = 0;
		int menu = 0;
		String result = null;
		ArrayList<Card> result2 = null;
		String result3 = null;
		// String result3 = null;
		try {												// 얘가 while 안으로 들어가면 무한루프가 돌아감 - 밖으로 빼주기
			while (true) {
				menu = cs.showMain();
				if (menu == 10) {
					break;
				} else if (menu == 1) {
					c = cs.insertCardInfo();
					result = cc.insertCard(c);
					cs.showResult(result);
				} else if (menu == 2) {
					page = cc.getPageCount();
					page = cs.showMainMenu(page);
					result2 = cc.getCardInfo(page);
					cs.getCardInfo(result2);
				} else if (menu == 3) {
					result3 = cs.deleteCard();
					result = cc.deleteCard(result3);
					cs.showResult(result);
				} else if(menu == 4) {
					result3 = cs.searchCard();
					result2 = cc.searchCard(result3);
					cs.showResult(result2);
				}
			}
			} catch (Exception e) {
				System.out.println("다시 입력하세요");
			}
			cs.bye();
			}
	}