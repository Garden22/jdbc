package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Update {

	public static void main(String[] args) {

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
			String query = "";
			query += "update author "; 
			query += "set author_name = ?, ";
			query += "author_desc = ? ";
			query += "where author_id = ?";
			System.out.println(query);
			
			pstmt = conn.prepareStatement(query); // 문자열 쿼리로 만들기
			pstmt.setString(1, "최문열");
			pstmt.setString(2, "서울시");
			pstmt.setInt(3, 1);
			
			int count = pstmt.executeUpdate();
			
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

	}

}
