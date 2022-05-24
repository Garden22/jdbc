package com.javaex.ex04;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class AuthorDao {
	
	private String id = "webdb";
	private String pw = "webdb";
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	
	public int authorInsert(AuthorVo authorVo) {
		
		int count = -1;
				
		getConnection();
		
		try {
			String query = "insert into author\nvalues(seq_author_id.nextval, ?, ?)";
			System.out.println(query);
					
			pstmt = conn.prepareStatement(query); 
			pstmt.setString(1, authorVo.getAuthorName());
			pstmt.setString(2, authorVo.getAuthorDesc());

			count = pstmt.executeUpdate();
									
			System.out.println(count + "건이 등록되었습니다.");		
		
		} catch (SQLException e) {
			System.out.println("errer: " + e);
		}
		
		close();

		return count;
	}
	
	
	
	public int authorDelete(int authorId) {

		int count = -1;
		
		getConnection();
		
		try {
			String query = "delete from author\nwhere author_id = ?";
			
			System.out.println(query);
			
			pstmt = conn.prepareStatement(query); 
			pstmt.setInt(1, authorId);
			
			count = pstmt.executeUpdate();
		
			System.out.println(count + "건이 등록되었습니다.");
		
		} catch (SQLException e) {
			System.out.println("errer: " + e);
		}
		
		close();
		
		return count;
	}
	
	
	
	public int authorUpdate(AuthorVo authorVo) {

		int count = -1;
		
		getConnection();
		
		try {
			String query = "update author\nset author_name = ?, author_desc = ?\nwhere author_id = ?";
			
			System.out.println(query);
			
			pstmt = conn.prepareStatement(query); 
			pstmt.setString(1, authorVo.getAuthorName());
			pstmt.setString(2, authorVo.getAuthorDesc());
			pstmt.setInt(3, authorVo.getAuthorId());
			
			count = pstmt.executeUpdate();
			
			System.out.println(count + "건이 등록되었습니다.");
		
		} catch (SQLException e) {
			System.out.println("errer: " + e);
		}
		
		close();
		
		return count;
	}
	
	
	public List<AuthorVo> authorSelect() {
		
		List<AuthorVo> authorList = new ArrayList<>();
		
		getConnection();
		
		try {
			String query = "select author_id, author_name, author_desc\nfrom author "; 

			System.out.println(query);
			
			pstmt = conn.prepareStatement(query); 
		
			rs = pstmt.executeQuery();
					
			while(rs.next()) {
				int authorID = rs.getInt(1);
				String authorName = rs.getString(2);
				String authorDesc = rs.getString(3);
				
				AuthorVo curr = new AuthorVo(authorID, authorName, authorDesc);
				authorList.add(curr);
				curr.print();
				
			}
		
		} catch (SQLException e) {
			System.out.println("errer: " + e);
		}	
		close();
		
		return authorList;
	}
	
	
	
	private void getConnection() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, id, pw);
			
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}
	
	
	
	private void close() {
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
	
}

