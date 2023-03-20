package com.kim.dec23_daodtomain;

import java.util.ArrayList;

public class HomeController {
	public static void main(String[] args) {
		ArrayList<Book> books = BookDAO.getBook();
		ConsoleViewer.printResult(books);	
	}
}
