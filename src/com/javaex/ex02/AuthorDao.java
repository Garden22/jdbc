package com.javaex.ex02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class AuthorDao {

	public int authorInsert(String authorName, String authorDesc) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = -1;
				
		try {
			// 1. JDBC 드라이버(ORACLE) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
					
			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
					
			// 3. SQL문 준비 / 바인딩 / 실행
			// insert하기
			String query = "insert into author\nvalues(seq_author_id.nextval, ?, ?)";
			System.out.println(query);
					
			//바인딩
			pstmt = conn.prepareStatement(query); // 문자열 쿼리로 만들기
			pstmt.setString(1, authorName);
			pstmt.setString(2, authorDesc);

			//실행
			count = pstmt.executeUpdate();
					
			// 4. 결과처리
			System.out.println(count + "건이 등록되었습니다.");
					
					
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
		return count;
	}
	
	
	
	public int authorDelete(int authorId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = -1;
		
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "delete from author\nwhere author_id = ?";
			
			System.out.println(query);
			
			pstmt = conn.prepareStatement(query); // 문자열 쿼리로 만들기
			pstmt.setInt(1, authorId);
			
			count = pstmt.executeUpdate();
			
			// 4.결과처리
			System.out.println(count + "건이 등록되었습니다.");

			
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
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
		return count;
	}
	
	
	
	public int authorUpdate(String authorName, String authorDesc, int authorId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = -1;
		
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "update author\nset author_name = ?, author_desc = ?\nwhere author_id = ?";
			
			System.out.println(query);
			
			pstmt = conn.prepareStatement(query); // 문자열 쿼리로 만들기
			pstmt.setString(1, authorName);
			pstmt.setString(2, authorDesc);
			pstmt.setInt(3, authorId);
			
			count = pstmt.executeUpdate();
			
			// 4.결과처리
			System.out.println(count + "건이 등록되었습니다.");

			
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
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
		return count;
	}
	
	
	public List<AuthorVo> authorSelect() {
		List<AuthorVo> authorList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "select author_id, author_name, author_desc\nfrom author "; 

			System.out.println(query);
			
			pstmt = conn.prepareStatement(query); // 문자열 쿼리로 만들기
			
			rs = pstmt.executeQuery();
					
			// 4.결과처리		
			while(rs.next()) {
				int authorID = rs.getInt(1);
				String authorName = rs.getString(2);
				String authorDesc = rs.getString(3);
				
				authorList.add(new AuthorVo(authorID, authorName, authorDesc));
				System.out.println(authorID + ": " + authorName + ", " + authorDesc);
			}
			
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
			
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
		return authorList;
	}
	
}


