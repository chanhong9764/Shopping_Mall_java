package sch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
 
public class UserInfo {
	// 유저 정보 가져오기
	public static UserBean GetuserInfo(String id) {
		UserBean temp = new UserBean();
		
		Connection conn = FindID.connect();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from USER where uid=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery(); 
 
			if (rs.next()) {
				temp.setID(rs.getString("UID"));
				temp.setEmail(rs.getString("EMAIL"));
				String G_temp;
				if(rs.getInt("grade") == 0)
					G_temp = "일반 회원";
				else
					G_temp = "관리자";
				temp.setGrade(G_temp);
				temp.setPoint(rs.getInt("point"));
				temp.setAddress(rs.getString("ADDRESS"));
				temp.setName(rs.getString("NAME"));
			}
		} catch (Exception e) {
			e.printStackTrace();
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
		return temp;
	}
	// 유저 포인트 셋팅
	public static void SetUserPoint(int point, String UID) {
		int idx = 0;
		
		Connection conn = FindID.connect();
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		String sql = "select IDX from USER where uid=?";
		String sql2 = "update USER set point=? where IDX=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt2 = conn.prepareStatement(sql2);
			pstmt.setString(1, UID);
			rs = pstmt.executeQuery(); 
			
			if (rs.next()) {
				idx = rs.getInt("IDX");
			}
			pstmt2.setInt(1, point);
			pstmt2.setInt(2, idx);
			pstmt2.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (pstmt2 != null) {
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
}	
	