package com.javaex.ex03;

import java.util.*;

public class BookApp {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		BookDao bookDao = new BookDao();
		
		System.out.println("검색할 키워드를 입력하세요.");
		String find = sc.next();
		bookDao.BookSearch(find);
		sc.close();
	}

}
