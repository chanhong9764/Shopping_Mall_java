package sch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

class resultID {
	private boolean existID;
	private String getpass;
	
	public boolean getexistID() {
		return existID;
	}
	public void setexistID(boolean existid) {
		existID = existid;
	}
	public String getgetpass() {
		return getpass;
	}
	public void setgetpass(String Getpass) {
		getpass = Getpass;
	}
}
public class FindID {
	// db연동 부분
	public static Connection connect()
	{
		Connection conn = null;
		final String url = "jdbc:mysql://3.35.26.136:54065/shoping?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8";                                                                                                                                                                                                                                                        // 주소
		final String uid = "root";
		final String upw = "1234";
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, uid, upw);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return conn;
	}
	// 계정 존재 확인 함수
	public static resultID findID(UserBean db) {
		Connection conn = connect();
		resultID t = new resultID();
		t.setexistID(false);
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select UID from USER where Name=? AND Email=?";
		t.setgetpass(null);
		
		try {
			// 쿼리 실행
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, db.getName());
			pstmt.setString(2, db.getEmail());
			rs = pstmt.executeQuery(); 
			
			if (rs.next()) {
				t.setgetpass(rs.getString("UID"));
				t.setexistID(true);
			}
		} catch (Exception e) {
			t.setexistID(false);
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
		return t;
	}	
}	
