package sch;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
 
class Account{
	boolean existID;
	boolean existAccount;
}

public class Register 
{
	// ID 중복 체크
	public boolean CheckID(UserBean db){
		boolean existID = true;
		
		Connection conn = FindID.connect();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from USER where UID=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, db.getID());
			rs=pstmt.executeQuery(); 
		
			if (rs.next()) 
			{
				existID = true;
			}
			else
			{
				existID = false;
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
		return existID;
	}
	// email, phone은 유일한 정보이므로 db에 존재하는지 체크 부분
	public boolean CheckInfo(UserBean db){
		boolean existInfo = true;
		
		Connection conn = FindID.connect();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from USER where Email=? OR Phone=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, db.getEmail());
			pstmt.setString(2, db.getPhone());
			rs=pstmt.executeQuery(); 
			if (rs.next()) 
			{
				existInfo = true;
			}
			else
			{
				existInfo = false;
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
		return existInfo;
	}
	// 회원 정보 입력 부분
	public void register(UserBean db) 
	{
		Connection conn = FindID.connect();
		PreparedStatement pstmt = null;
		
		String sql = "INSERT INTO USER(uid, pwd, name, address, Email, Phone) VALUES(?, ?, ?, ?, ?, ?)";

		try 
		{
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, db.getID());
			pstmt.setString(2, db.getPW());
			pstmt.setString(3, db.getName());
			pstmt.setString(4, db.getAddress());
			pstmt.setString(5, db.getEmail());
			pstmt.setString(6, db.getPhone());
			pstmt.executeUpdate();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if (pstmt != null) 
					pstmt.close();
				if (conn != null) 
					conn.close();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}	