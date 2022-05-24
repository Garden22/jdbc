package com.javaex.ex04;

import java.util.*;

public class AuthorApp {
	
	public static void main(String[] args) {
		
		AuthorDao authorDao = new AuthorDao();
		
		AuthorVo vo = new AuthorVo("테스트3", "테스트3");
		authorDao.authorInsert(vo);
		
		//authorDao.authorDelete(9);
			
		List<AuthorVo> authorList = authorDao.authorSelect();
	
	}

}
