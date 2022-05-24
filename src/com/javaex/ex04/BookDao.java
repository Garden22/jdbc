package com.javaex.ex04;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class BookDao {
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private String id = "webdb";
	private String pw = "webdb";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";

	
	public void BookInsert(BookVo bookVo) {
		int count = -1;
		getConnection();

		try {
			String query = "";
			query += "insert into book\nvalues(seq_book_id.nextval, ?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bookVo.getTitle());
			pstmt.setString(2, bookVo.getPubs());
			pstmt.setString(3, bookVo.getPubDate());
			pstmt.setInt(4, bookVo.getAuthorId());

			count = pstmt.executeUpdate();		
			
		} catch (SQLException e) {
			System.out.println("error: " + e);
		} 
		
		close();
		
		if (count > 0) {
			System.out.println(count + "건 등록되었습니다.");
		} else {
			System.out.println("정보가 등록되지 않았습니다.");
		}
	}
	
	
	
	
	public void BookDelete(int bookId) {
		getConnection();
		int count = -1;
		
		try {
			String query = "delete from book\nwhere book_id = ? ";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bookId);

			count = pstmt.executeUpdate();		
			
		} catch (SQLException e) {
			System.out.println("error: " + e);
		} 
		
		close();
		
		if (count > 0) {
			System.out.println(count + "건 삭제되었습니다.");
		} else {
			System.out.println("정보가 삭제되지 않았습니다.");
		}
	}		
	
	
	
	
	public void BookUpdate(BookVo bookVo) {
		
		getConnection();
		int count = -1;

		try {
			String query = "update book\nset title = ?, pubs = ?, pub_date = ?, author_id = ?\nwhere book_id = ? ";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bookVo.getTitle());
			pstmt.setString(2, bookVo.getPubs());
			pstmt.setString(3, bookVo.getPubDate());
			pstmt.setInt(4, bookVo.getAuthorId());
			pstmt.setInt(5, bookVo.getBookId());

			count = pstmt.executeUpdate();		
			
		} catch (SQLException e) {
			System.out.println("error: " + e);
		}
		
		close();
		
		if (count > 0) {
			System.out.println(count + "건 업데이트되었습니다.");
		} else {
			System.out.println("정보가 업데이트되지 않았습니다.");
		}
	}
	
	
	
	
	public List<BookVo> BookSelect() {
		
		List<BookVo> bookList = new ArrayList<>();
		getConnection();
		
		try {
			String query = "";
			query += "select b.book_id, b.title, b.pubs, to_char(b.pub_date, 'YYYY-MM-DD'), a.author_id, a.author_name, a.author_desc "; 
			query += "from book b, author a ";
			query += "where b.author_id = a.author_id";
			
			pstmt = conn.prepareStatement(query);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				bookList.add(new BookVo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5)));
			}
			
		} catch (SQLException e) {
			System.out.println("error: " + e);
		} 
		
		close();
		
		for (BookVo b: bookList) {
			b.print();
		}
		System.out.println(bookList.size() + "건 조회되었습니다.");
		
		return bookList;
	}
	
	
	
	
	public List<BookVo> BookSearch() {
		Scanner sc = new Scanner(System.in);
		
		getConnection();
		List<BookVo> bookList = new ArrayList<>();
				
		System.out.println("검색할 키워드를 입력하세요.");
		String find = sc.next();
		
		try {
			String query = "";
			query += "select b.book_id, b.title, b.pubs, to_char(b.pub_date, 'YYYY-MM-DD'), a.author_id, a.author_name, a.author_desc "; 
			query += "from book b, author a ";
			query += "where b.author_id = a.author_id ";
			query += "and (b.title like ? or b.pubs like ? or a.author_name like ? or a.author_desc like ?) ";
			
			pstmt = conn.prepareStatement(query);
			
			find = '%' + find + '%';
			pstmt.setString(1, find);
			pstmt.setString(2, find);
			pstmt.setString(3, find);
			pstmt.setString(4, find);
						
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				bookList.add(new BookVo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5)));
			}
			
		} catch (SQLException e) {
			System.out.println("error: " + e);
		} 

		close();
		
		for (BookVo b: bookList) {
			b.print();
		}
		
		if (bookList.size() > 0) {
			System.out.println(bookList.size() + "건 조회되었습니다.");
		} else {
			System.out.println("조회된 데이터가 없습니다.");
		}
		
		sc.close();
		return bookList;
	}
	
	
	
	
	private void getConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, id, pw);
			
		} catch (ClassNotFoundException e) {
			System.out.println("errer: 드라이버 로딩 실패 - " + e);
			
		} catch (SQLException e) {
			System.out.println("error: " + e);
		}
	}
	
	
	
	private void close() {
		try {
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}
}
