package sch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FindPW {
	// 패스워드 바꾸는 부분
	public static void ChangePW (String ID, String PW)
	{
		Connection conn = FindID.connect();
		PreparedStatement pstmt = null;
		String sql = "UPDATE USER SET PWD=? WHERE UID=?";
		
		try {
			// 쿼리 실행
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, PW);
			pstmt.setString(2, ID);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}		
	}
	// 계정 찾는 부분
	public static boolean findPW(UserBean db) {
		boolean existID=false;
		Connection conn = FindID.connect();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from USER where UID=? AND Email=?";
		
		try {
			// 쿼리 실행
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, db.getID());
			pstmt.setString(2, db.getEmail());
			rs = pstmt.executeQuery(); 

			if (rs.next()) {
				existID=true;
			}
		} catch (Exception e) {
			existID=false;
			e.printStackTrace();
			
			// 쿼리 실행 후 모두 닫아줌
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
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}		
		return existID;
	}	
}	
