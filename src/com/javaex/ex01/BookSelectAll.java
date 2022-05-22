package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.*;

public class BookSelectAll {

	public static void main(String[] args) throws IOException {

		// 0.import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			// 1. JDBC 드라이버(ORACLE) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			// 3. SQL문 준비 / 바인딩 / 실행
			// insert하기
			String query = "";
			query += "select b.book_id, b.title, b.pubs, to_char(b.pub_date, 'YYYY-MM-DD'), a.author_id, a.author_name, a.author_desc "; 
			query += "from book b, author a ";
			query += "where b.author_id = a.author_id";
			System.out.println(query);
			
			//바인딩
			pstmt = conn.prepareStatement(query); // 문자열 쿼리로 만들기
			
			rs = pstmt.executeQuery();
			
			// 4. 결과처리
			while (rs.next()) {
				System.out.println(rs.getInt(1) + ": " + rs.getString(2) + ", " + rs.getString(3) + ", " + rs.getString(4) + ", " + rs.getInt(5) + ", " + rs.getString(6) + ", " + rs.getString(7));
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

	}

}
