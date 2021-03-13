package sch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.DefaultListModel;
 
public class Trade {
	// 찜 목록 확인
	public static boolean CheckBasket(String UID, String model, String size) {
		int U_IDX = 0;
		int P_IDX = 0;
		boolean check = false;
		String img=model+".jpg";
		Connection conn = FindID.connect();
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		
		String sql, sql2, sql3;
		sql = "select IDX from USER where UID=?";
		sql2 = "select IDX from product_info where P_IDX in (select IDX from product where img=?) and size=?";
		sql3 = "select * from Basket where U_IDX=? and PI_IDX=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt2 = conn.prepareStatement(sql2);
			pstmt3 = conn.prepareStatement(sql3);
			pstmt.setString(1, UID);
			pstmt2.setString(1, img);
			pstmt2.setInt(2, Integer.parseInt(size));
			rs = pstmt.executeQuery(); 
			rs2 = pstmt2.executeQuery();
 
			while (rs.next()) {
				U_IDX = rs.getInt("IDX");
			}
			while(rs2.next())
			{
				P_IDX = rs2.getInt("IDX");
			}
			pstmt3.setInt(1, U_IDX);
			pstmt3.setInt(2, P_IDX);
			rs3 = pstmt3.executeQuery();
			if(rs3.next())
			{
				check = true;
			}
			else
			{
				check = false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (rs2 != null) {
					rs.close();
				}
				if (rs3 != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (pstmt2 != null) {
					pstmt.close();
				}
				if (pstmt3 != null) {
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
	// 제품 유저 찜 목록으로 삽입 
	public static void SetBasket(String UID, String model, String size) {
		int U_IDX = 0;
		int P_IDX = 0;
		String img=model+".jpg";
		Connection conn = FindID.connect();
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		
		String sql, sql2, sql3;
		sql = "select IDX from USER where UID=?";
		sql2 = "select IDX from product_info where P_IDX in (select IDX from product where img=?) and size=?";
		sql3 = "insert into Basket(U_IDX,PI_IDX) values(?,?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt2 = conn.prepareStatement(sql2);
			pstmt3 = conn.prepareStatement(sql3);
			pstmt.setString(1, UID);
			pstmt2.setString(1, img);
			pstmt2.setInt(2, Integer.parseInt(size));
			rs = pstmt.executeQuery(); 
			rs2 = pstmt2.executeQuery();
			while (rs.next()) {
				U_IDX = rs.getInt("IDX");
			}
			while(rs2.next())
			{
				P_IDX = rs2.getInt("IDX");
			}
			pstmt3.setInt(1, U_IDX);
			pstmt3.setInt(2, P_IDX);
			pstmt3.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (rs2 != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (pstmt2 != null) {
					pstmt.close();
				}
				if (pstmt3 != null) {
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
	// 유저 찜 목록 삭제
	public static void DelBasket(String UID, String model, String size) {
		int U_IDX = 0;
		int P_IDX = 0;
		String img=model+".jpg";
		Connection conn = FindID.connect();
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		
		String sql, sql2, sql3;
		sql = "select IDX from USER where UID=?";
		sql2 = "select IDX from product_info where P_IDX in (select IDX from product where img=?) and size=?";
		sql3 = "delete from Basket where U_IDX=? AND PI_IDX=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt2 = conn.prepareStatement(sql2);
			pstmt3 = conn.prepareStatement(sql3);
			pstmt.setString(1, UID);
			pstmt2.setString(1, img);
			pstmt2.setInt(2, Integer.parseInt(size));
			rs = pstmt.executeQuery(); 
			rs2 = pstmt2.executeQuery();

			while (rs.next()) {
				U_IDX = rs.getInt("IDX");
			}
			while(rs2.next())
			{
				P_IDX = rs2.getInt("IDX");
			}
			pstmt3.setInt(1, U_IDX);
			pstmt3.setInt(2, P_IDX);
			pstmt3.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (rs2 != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (pstmt2 != null) {
					pstmt.close();
				}
				if (pstmt3 != null) {
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
	// 제품의 모든 사이즈 중 가장 높은 즉시 판매가 
	public static int GetPurchase_TP(String keyword) {
		int price = 0;
		
		Connection conn = FindID.connect();
		PreparedStatement pstmt = null;		
		ResultSet rs = null;
		
		String sql;
		sql = "select MAX(Purchase_bid), status from PURCHASE join product_info on PI_IDX=product_info.IDX join product on product_info.P_IDX=product.IDX group by img, status having img=? AND status=0";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, keyword);
			rs = pstmt.executeQuery(); 
			
			while (rs.next()) {
				price = rs.getInt("MAX(Purchase_bid)");
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
		return price;
	}
	// 제품의 모든 사이즈 중 가장 낮은 즉시 구매가
	public static int GetSell_TP(String keyword) {
		int price = 0;
		
		Connection conn = FindID.connect();
		PreparedStatement pstmt = null;		
		ResultSet rs = null;
		
		String sql;
		sql = "select MIN(Sell_bid), status FROM Sell join product_info on PI_IDX=product_info.IDX join product on product_info.P_IDX=product.IDX group by img, status having img=? and status=0";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, keyword);
			rs = pstmt.executeQuery(); 
			while (rs.next()) {
				price = rs.getInt("MIN(Sell_bid)");
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
		return price;
	}
	// 선택한 사이즈의 제품에서 가장 높은 즉시 판매가
	public static trades GetSell(String size, String model) {
		Connection conn = FindID.connect();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		trades temp = new trades();

		String sql = "select MAX(Purchase_bid), PURCHASE.U_IDX, PURCHASE.PI_IDX from PURCHASE where PI_IDX in (select product_info.IDX from product_info join product on P_IDX=product.IDX where size=? AND model=? AND status=0)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, size);
			pstmt.setString(2, model);
			rs = pstmt.executeQuery(); 
			 
			while (rs.next()) {
				temp.price = rs.getInt("MAX(Purchase_bid)");
				temp.U_IDX = rs.getInt("U_IDX");
				temp.PI_IDX = rs.getInt("PI_IDX");
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
	// 선택한 사이즈의 제품에서 가장 낮은 즉시 구매가
	public static trades GetPurchase(String size, String model) {
		Connection conn = FindID.connect();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		trades temp = new trades();
		String sql = "select Min(Sell_bid), Sell.U_IDX, Sell.PI_IDX from Sell where PI_IDX in (select product_info.IDX from product_info join product on P_IDX=product.IDX where size=? AND model=?) AND STATUS=0";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, size);
			pstmt.setString(2, model);
			rs = pstmt.executeQuery(); 
			while (rs.next()) {
				temp.price = rs.getInt("Min(Sell_bid)");
				temp.U_IDX = rs.getInt("U_IDX");
				temp.PI_IDX = rs.getInt("PI_IDX");
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
	// 판매 입찰 
	public static void setSell(int total, String UID, String size, String model, String address) {
		Connection conn = FindID.connect();
		int PI_idx = 0, U_idx = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs2 = null;
		PreparedStatement pstmt3 = null;
		String sql = "select product_info.IDX from product_info join product on P_IDX=product.IDX where P_IDX in (select IDX from product where model=?) AND product_info.size=?";
		String sql2 = "select IDX from USER where UID=?";
		String sql3 = "insert into Sell(Sell_bid,U_IDX,PI_IDX,ShipAddress) values (?,?,?,?)";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt2 = conn.prepareStatement(sql2);
			pstmt3 = conn.prepareStatement(sql3);
			pstmt.setString(1, model);
			pstmt.setInt(2, Integer.parseInt(size));
			pstmt2.setString(1, UID);
			rs = pstmt.executeQuery(); 
			rs2 = pstmt2.executeQuery(); 
 
			while (rs.next()) {
				PI_idx = rs.getInt("IDX");
			}
			while (rs2.next()) {
				U_idx = rs2.getInt("IDX");
			}
			pstmt3.setInt(1, total);
			pstmt3.setInt(2, U_idx);
			pstmt3.setInt(3, PI_idx);
			pstmt3.setString(4, address);
			pstmt3.executeUpdate();
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
				if (rs2 != null) {
					rs2.close();
				}
				if (pstmt2 != null) {
					pstmt2.close();
				}
				if (pstmt3 != null) {
					pstmt3.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}	
	}
	// 판매 내역 제작
	public static void setSell_t(int total, String UID, trades temp) {
		Connection conn = FindID.connect();
		int U_IDX=0;
		int PC_IDX=0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		PreparedStatement pstmt4 = null;
		ResultSet rs3 = null;
		ResultSet rs4 = null;
		String sql = "select IDX from USER where UID=?";
		String sql2 = "INSERT INTO P_Trade(confirm, status, R_Price, PC_IDX, U_TIDX) values (0,0,?,?,?)";
		String sql3 = "UPDATE PURCHASE SET status=1 where IDX=?";
		String sql4 = "select IDX from PURCHASE where PURCHASE_bid=? AND PI_IDX=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt2 = conn.prepareStatement(sql2);
			pstmt3 = conn.prepareStatement(sql3);
			pstmt4 = conn.prepareStatement(sql4);
			
			pstmt.setString(1, UID);
			pstmt4.setInt(1,temp.price);
			pstmt4.setInt(2, temp.PI_IDX);
			
			rs = pstmt.executeQuery(); 
			rs4 = pstmt4.executeQuery();
			while (rs.next()) {
				U_IDX = rs.getInt("IDX");
			}
			while (rs4.next()) {
				PC_IDX = rs4.getInt("IDX");
			}
			pstmt3.setInt(1, PC_IDX);
			pstmt3.executeUpdate();
			
			pstmt2.setInt(1, total);
			pstmt2.setInt(2, PC_IDX);
			pstmt2.setInt(3, U_IDX);
			pstmt2.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (rs3 != null) {
					rs.close();
				}
				if (rs4 != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (pstmt2 != null) {
					pstmt2.close();
				}
				if (pstmt3 != null) {
					pstmt.close();
				}
				if (pstmt4 != null) {
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
	// 구매 내역 셋팅
	public static void setPurchase_t(int total, String UID, trades temp) {
		Connection conn = FindID.connect();
		int U_IDX=0;
		int S_IDX=0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		PreparedStatement pstmt4 = null;
		ResultSet rs3 = null;
		ResultSet rs4 = null;
		String sql = "select IDX from USER where UID=?";
		String sql2 = "INSERT INTO S_Trade(confirm, status, R_Price, S_IDX, U_TIDX) values (0,0,?,?,?);";
		String sql3 = "UPDATE Sell SET status=1 where IDX=?";
		String sql4 = "select IDX from Sell where Sell_bid=? AND PI_IDX=?";
		
		try {
			// 쿼리 실행
			pstmt = conn.prepareStatement(sql);
			pstmt2 = conn.prepareStatement(sql2);
			pstmt3 = conn.prepareStatement(sql3);
			pstmt4 = conn.prepareStatement(sql4);
			
			pstmt.setString(1, UID);
			pstmt4.setInt(1,temp.price);
			pstmt4.setInt(2, temp.PI_IDX);
			
			rs = pstmt.executeQuery(); 
			rs4 = pstmt4.executeQuery();
			while (rs.next()) {
				U_IDX = rs.getInt("IDX");
			}
			while (rs4.next()) {
				S_IDX = rs4.getInt("IDX");
			}
			pstmt3.setInt(1, S_IDX);
			pstmt3.executeUpdate();
			
			pstmt2.setInt(1, total);
			pstmt2.setInt(2, S_IDX);
			pstmt2.setInt(3, U_IDX);
			pstmt2.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (rs3 != null) {
					rs.close();
				}
				if (rs4 != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (pstmt2 != null) {
					pstmt2.close();
				}
				if (pstmt3 != null) {
					pstmt.close();
				}
				if (pstmt4 != null) {
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
	// 구매 입찰
	public static void setPurchase(int total, String UID, String size, String model, String address) {
		Connection conn = FindID.connect();
		int PI_idx = 0, U_idx = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs2 = null;
		PreparedStatement pstmt3 = null;
		String sql = "select product_info.IDX from product_info join product on P_IDX=product.IDX where P_IDX in (select IDX from product where model=?) AND product_info.size=?";
		String sql2 = "select IDX from USER where UID=?";
		String sql3 = "insert into PURCHASE(Purchase_bid,U_IDX,PI_IDX,ShipAddress) values (?,?,?,?)";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt2 = conn.prepareStatement(sql2);
			pstmt3 = conn.prepareStatement(sql3);
			pstmt.setString(1, model);
			pstmt.setInt(2, Integer.parseInt(size));
			pstmt2.setString(1, UID);
			rs = pstmt.executeQuery(); 
			rs2 = pstmt2.executeQuery(); 

			while (rs.next()) {
				PI_idx = rs.getInt("IDX");
			}
			while (rs2.next()) {
				U_idx = rs2.getInt("IDX");
			}
			pstmt3.setInt(1, total);
			pstmt3.setInt(2, U_idx);
			pstmt3.setInt(3, PI_idx);
			pstmt3.setString(4, address);
			pstmt3.executeUpdate();
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
				if (rs2 != null) {
					rs2.close();
				}
				if (pstmt2 != null) {
					pstmt2.close();
				}
				if (pstmt3 != null) {
					pstmt3.close();
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
	