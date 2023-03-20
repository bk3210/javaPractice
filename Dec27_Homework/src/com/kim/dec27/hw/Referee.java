package com.kim.dec27.hw;

import java.util.ArrayList;

public class Referee {
	static String[] ruleBook = { null, "가위", "바위", "보" };
	ArrayList<Integer> record = new ArrayList<>();
	public int[] score;
	int userWin;
	int count;

	public Referee() {
	}

	public void readRuleBook() {
		for (int i = 1; i < ruleBook.length; i++) {
			System.out.printf("%d. %s\n", i, ruleBook[i]);
		}
		System.out.println("-------------");
	}

	public int userFire(User u) {
		System.out.println("무엇을 내실 건가요?");
		int answer = u.fire();
		return (answer < 1 || answer > 3) ? userFire(u) : answer;
	}

	public int enemyFire(Enemy e) {
		return e.eFire();
	}

	public void tellFire(int userHand, int enemyHand) {
		System.out.printf("컴퓨터 : %s\n", ruleBook[enemyHand]);
		System.out.printf("나 : %s\n", ruleBook[userHand]);
	}

	public int judge(int userHand, int enemyHand) {
		int t = userHand - enemyHand;
		if (t == 0) {
			System.out.println("무");
			count++;
		} else if (t == -1 || t == 2) {
			System.out.println("패");
			count++;
		} else {
			System.out.println("승");
			userWin++;
			count++;
		}
		return -999;
	}

	public void bye() {
		System.out.println();
	}

	public void start(User u, Enemy e) {
		readRuleBook();
		int uh;
		int eh;
		while (true) {
			uh = userFire(u);
			eh = enemyFire(e);
			tellFire(uh, eh);

				break;
			}
		}
	}