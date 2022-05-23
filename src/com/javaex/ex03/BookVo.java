package com.javaex.ex03;

public class BookVo extends AuthorVo {
	
	private int bookId;
	private String title;
	private String pubs;
	private String pubDate;
	
	
	public BookVo() {
		super();
	}
	
	public BookVo(int bookId, String title, String pubs, String pubDate, int authorId, String authorName, String authorDesc) {
		super(authorId, authorName, authorDesc);
		this.bookId = bookId;
		this.title = title;
		this.pubs = pubs;
		this.pubDate = pubDate;
	}
		
	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPubs() {
		return pubs;
	}

	public void setPubs(String pubs) {
		this.pubs = pubs;
	}

	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	public void print() {
		System.out.println(bookId + ": " + title + ", " + pubs + ", " + pubDate + ", " + authorId + ", " + authorName + ", " + authorDesc);
	}

}
