package sch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.DefaultListModel;

public class History {
	// 리뷰 지급 여부 확인
	public static boolean Review_p(String img) {
		Connection conn = FindID.connect();
		boolean check = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select confirm from Review where img=?"; 
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, img);
			rs = pstmt.executeQuery(); 
			if(rs.next())
			{
				check = rs.getBoolean("confirm");
			}
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
		return check;
	}
	// 판매 내역에서 진행 상태 처리 부분
	public static void SetStatus_s(int status, int idx) {
		Connection conn = FindID.connect();
		PreparedStatement pstmt = null;
		String sql = "update P_Trade set status=? where IDX=?"; 
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, status);
			pstmt.setInt(2, idx);
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
	// 구매 내역에서 진행 상태 처리 부분
	public static void SetStatus_p(int status, int idx) {
		Connection conn = FindID.connect();
		PreparedStatement pstmt = null;
		String sql = "update S_Trade set status=? where IDX=?"; 
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, status);
			pstmt.setInt(2, idx);
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
	// 판매 내역에서 컨펌 처리 부분
	public static void SetConfirm_s(int idx) {
		Connection conn = FindID.connect();
		PreparedStatement pstmt = null;
		String sql = "update P_Trade set confirm=1 where IDX=?"; 
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
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
	// 구매 내역에서 컨펌 처리 부분
	public static void SetConfirm_p(int idx) {
		Connection conn = FindID.connect();
		PreparedStatement pstmt = null;
		String sql = "update S_Trade set confirm=1 where IDX=?"; 
		
		try {
			// 쿼리 실행
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
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
	// 구매 내역 취소 처리 부분
	public static void DelTrade_P(int idx) {
		Connection conn = FindID.connect();
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		String sql = "delete from S_Trade where IDX=?";
		String sql2 = "update Sell SET status=0 where IDX IN (SELECT S_IDX FROM S_Trade where IDX=?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt2 = conn.prepareStatement(sql2);
			pstmt.setInt(1, idx);
			pstmt2.setInt(1, idx);
			pstmt2.executeUpdate();
			pstmt.executeUpdate(); 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
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
	// 판매 내역 취소 처리 부분
	public static void DelTrade_S(int idx) {
		Connection conn = FindID.connect();
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		String sql = "delete from P_Trade where IDX=?";
		String sql2 = "update PURCHASE SET status=0 where IDX IN (SELECT PC_IDX FROM P_Trade where IDX=?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt2 = conn.prepareStatement(sql2);
			pstmt.setInt(1, idx);
			pstmt2.setInt(1, idx);
			pstmt2.executeUpdate();
			pstmt.executeUpdate(); 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (pstmt2 != null) {
					pstmt2.close();
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
