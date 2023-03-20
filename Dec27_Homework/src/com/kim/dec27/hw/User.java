package com.kim.dec27.hw;

import java.util.Scanner;

public class User {
	Scanner brain;
	
	public User() {
		brain = new Scanner(System.in);
	}
	
	public int fire() {
			return brain.nextInt();
		
	}
}
