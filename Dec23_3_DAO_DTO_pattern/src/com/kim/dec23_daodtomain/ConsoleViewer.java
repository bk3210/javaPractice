package com.kim.dec23_daodtomain;

import java.util.ArrayList;

public class ConsoleViewer {
	public static void printResult(ArrayList<Book> books) {
		for (Book book : books) {
			System.out.println(book.getName());
			System.out.println(book.getPrice());
			
		}
	}
}
