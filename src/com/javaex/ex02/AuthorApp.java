package com.javaex.ex02;

import java.util.*;

public class AuthorApp {
	
	public static void main(String[] args) {
		
		AuthorDao authorDao = new AuthorDao();
		
		authorDao.authorInsert("이문열", "경북영양");
		authorDao.authorInsert("박경리", "경남통영");
		authorDao.authorInsert("유시민", "국회의원");
		authorDao.authorInsert("기안84", "웹툰작가");
		authorDao.authorInsert("강풀", "서울특별시");
		authorDao.authorInsert("이영하", "알쓸신잡");

			
		List<AuthorVo> authorList = authorDao.authorSelect();

		// 리스트 내용물 찍어보기
		for (AuthorVo a: authorList) {
			a.print();
		}
	
		
	}

}
