package com.javaex.ex03;

public class AuthorVo {
	
	protected int authorId;
	protected String authorName;
	protected String authorDesc;
	
	public AuthorVo() {
		
	}
	
	public AuthorVo(int authorId, String authorName, String authorDesc) {
		this.authorId = authorId;
		this.authorName = authorName;
		this.authorDesc = authorDesc;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getAuthorDesc() {
		return authorDesc;
	}

	public void setAuthorDesc(String authorDesc) {
		this.authorDesc = authorDesc;
	}

	public void print() {
		System.out.println(authorId + ": " + authorName + ", " + authorDesc);
	}
	
}
