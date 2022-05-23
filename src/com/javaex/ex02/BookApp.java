package com.javaex.ex02;

public class BookApp {

	public static void main(String[] args) {
		
		BookDao bookDao = new BookDao();
		
		bookDao.BookInsert("우리들의 일그러진 영웅", "다림", "1998-02-22", 1);
		bookDao.BookInsert("삼국지", "민음사", "2002-03-01", 1);
		bookDao.BookInsert("토지", "마로니에북스", "2012-08-15", 2);
		bookDao.BookInsert("유시민의 글쓰기 특강", "생각의길", "2015-04-11", 3);
		bookDao.BookInsert("패션왕", "중앙북스(books)", "2012-02-22", 4);
		bookDao.BookInsert("순정만화", "재미주의", "2011-08-03", 5);
		bookDao.BookInsert("오직두사람", "문학동네", "2017-05-04", 6);
		bookDao.BookInsert("26년", "재미주의", "2012-02-04", 5);
		
		bookDao.BookUpdate("테스트", "테스트", "2011-01-01", 1, 4);
		
		bookDao.BookDelete(4);
		
		bookDao.BookSelect();
		
		bookDao.BookSearch();

	}

}
