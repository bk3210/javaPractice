package com.kim.sddevice.main;

import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleScreen {
	private Scanner inputSystem;

	public ConsoleScreen() {
		inputSystem = new Scanner(System.in);
	}

	public int showMain() throws Exception {
		System.out.println("카드뽑기 프로토타입");
		System.out.println("===============");
		System.out.println("1. 카드 등록");
		System.out.println("2. 카드 조회");
		System.out.println("3. 카드 삭제");
		System.out.println("4. 카드 검색");
		System.out.println("10. 프로그램 종료");
		System.out.println("===============");
		return inputSystem.nextInt();
	}

	public String searchCard() throws Exception {
		System.out.println("조회하실 카드의 캐릭터명을 입력하세요");
		return inputSystem.next();
		}

	public Card insertCardInfo() throws Exception {
		System.out.print("캐릭터명 : ");
		String name = inputSystem.next();
		System.out.print("카드명 : ");
		String cardName = inputSystem.next();
		System.out.print("댄스 스탯 : ");
		int da = inputSystem.nextInt();
		System.out.print("보컬 스탯 : ");
		int vo = inputSystem.nextInt();
		System.out.print("퍼포먼스 스탯 : ");
		int pf = inputSystem.nextInt();
		return new Card(name, cardName, da, vo, pf);
	}

	public void showResult(String result) {
		System.out.println(result);
	}

	public int showMainMenu(int page) {
		System.out.printf("몇 페이지[1~%d] : ", page);
		return inputSystem.nextInt();
	}

	public void getCardInfo(ArrayList<Card> cards) {
		for (Card card : cards) {
			System.out.println(card.getCardName());
			System.out.println(card.getName());
			System.out.println(card.getDa());
			System.out.println(card.getVo());
			System.out.println(card.getPf());
		}
	}

	public String deleteCard() {
		System.out.print("삭제하실 캐릭터명 혹은 카드명을 입력하세요 : ");
		return inputSystem.next();
	}

	public void bye() {
		inputSystem.close();
	}

	public void showResult(ArrayList<Card> result2) {
		for (Card card : result2) {
			System.out.println(card.getName());
			System.out.println(card.getCardName());			
		}
	}
}
