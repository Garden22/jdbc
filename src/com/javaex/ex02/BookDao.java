package com.javaex.ex02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class BookDao {
	
	
	public void BookInsert(String title, String pubs, String pubDate, int authorId) {
		
		int count = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			

			String query = "";
			query += "insert into book\nvalues(seq_book_id.nextval, ?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, title);
			pstmt.setString(2, pubs);
			pstmt.setString(3, pubDate);
			pstmt.setInt(4, authorId);

			count = pstmt.executeUpdate();		
			
		} catch (ClassNotFoundException e) {
			System.out.println("errer: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error: " + e);
		} finally {
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
		
		if (count > 0) {
			System.out.println(count + "건 등록되었습니다.");
		} else {
			System.out.println("정보가 등록되지 않았습니다.");
		}
	}
	
	
	
	
	public void BookDelete(int bookId) {
		
		int count = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			

			String query = "delete from book\nwhere book_id = ? ";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bookId);

			count = pstmt.executeUpdate();		
			
		} catch (ClassNotFoundException e) {
			System.out.println("errer: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error: " + e);
		} finally {
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
		
		if (count > 0) {
			System.out.println(count + "건 삭제되었습니다.");
		} else {
			System.out.println("정보가 삭제되지 않았습니다.");
		}
	}		
	
	
	
	
	public void BookUpdate(String title, String pubs, String pubDate, int authorId, int bookId) {
		
		int count = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			

			String query = "";
			query += "update book\nset title = ?, pubs = ?, pub_date = ?, author_id = ?\nwhere book_id = ? ";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, title);
			pstmt.setString(2, pubs);
			pstmt.setString(3, pubDate);
			pstmt.setInt(4, authorId);
			pstmt.setInt(5, bookId);

			count = pstmt.executeUpdate();		
			
		} catch (ClassNotFoundException e) {
			System.out.println("errer: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error: " + e);
		} finally {
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
		
		if (count > 0) {
			System.out.println(count + "건 업데이트되었습니다.");
		} else {
			System.out.println("정보가 업데이트되지 않았습니다.");
		}
	}
	
	
	
	
	public List<BookVo> BookSelect() {
		
		List<BookVo> bookList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			String query = "";
			query += "select b.book_id, b.title, b.pubs, to_char(b.pub_date, 'YYYY-MM-DD'), a.author_id, a.author_name, a.author_desc "; 
			query += "from book b, author a ";
			query += "where b.author_id = a.author_id";
			
			pstmt = conn.prepareStatement(query);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				bookList.add(new BookVo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5)));
			}
			
		} catch (ClassNotFoundException e) {
			System.out.println("errer: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error: " + e);
		} finally {
			// 5. 자원정리
			try {
				if (rs != null) {
					rs.close();
				} 
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
		for (BookVo b: bookList) {
			b.print();
		}
		System.out.println(bookList.size() + "건 조회되었습니다.");
		
		return bookList;
	}
	
	
	
	
	public List<BookVo> BookSearch() {
		Scanner sc = new Scanner(System.in);
		
		List<BookVo> bookList = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		System.out.println("검색할 키워드를 입력하세요.");
		String find = sc.next();
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
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
			
		} catch (ClassNotFoundException e) {
			System.out.println("errer: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error: " + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				} 
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
}
