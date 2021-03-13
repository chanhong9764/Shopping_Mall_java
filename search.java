package sch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.DefaultListModel;
 
public class search {
	// 즉시 판매가순으로 제품 정보를 가져오는 부분
	public static DefaultListModel<productBean> GetSell(String keyword) {
		DefaultListModel<productBean> list = new DefaultListModel<>();
		
		Connection conn = FindID.connect();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		if(keyword.equals(""))
		{
			sql = "select DISTINCT img, Purchase_bid, brand from PURCHASE join product_info on PI_IDX=product_info.IDX join product on product_info.P_IDX=product.idx where (img, Purchase_bid) in (select img, MAX(Purchase_bid) from PURCHASE join product_info on PI_IDX=product_info.IDX join product on product_info.P_IDX=product.IDX where status=0 group by img) order by Purchase_bid DESC";
		}
		else
		{
			sql = "select DISTINCT img, Purchase_bid, brand from PURCHASE join product_info on PI_IDX=product_info.IDX join product on product_info.P_IDX=product.idx where (img, Purchase_bid) in (select img, MAX(Purchase_bid) from PURCHASE join product_info on PI_IDX=product_info.IDX join product on product_info.P_IDX=product.IDX where status=0 AND product.model REGEXP ? group by img) order by Purchase_bid DESC";
		}
		
		try {
			pstmt = conn.prepareStatement(sql);
			if(!(keyword.equals(""))) { pstmt.setString(1, keyword); }
			rs = pstmt.executeQuery(); 
			 
			while (rs.next()) {
				productBean temp = new productBean();
				String temp_t=rs.getString("img");
				temp.setImg(temp_t);
				temp.setPrice(rs.getInt("Purchase_bid"));
				temp.setBrand(rs.getString("brand"));
				temp.setModel((temp_t.substring(0,temp_t.length()-4)));
				list.addElement(temp);
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
		return list;
	}
	// 즉시 구매가순으로 제품 정보를 가져오는 부분 
	public static DefaultListModel<productBean> GetPurchase(String keyword) {
		DefaultListModel<productBean> list = new DefaultListModel<>();
		
		Connection conn = FindID.connect();
		PreparedStatement pstmt = null;		
		ResultSet rs = null;
		
		String sql;
		if(keyword.equals(""))
		{
			sql = "select DISTINCT img, Sell_bid, brand from Sell join product_info on PI_IDX=product_info.IDX join product on product_info.P_IDX=product.idx where (img, Sell_bid) in (select img, MIN(Sell_bid) FROM Sell join product_info on PI_IDX=product_info.IDX join product on product_info.P_IDX=product.IDX where status=0 group by img) order by Sell_bid";
		}
		else
		{
			sql = "select DISTINCT img, Sell_bid, brand from Sell join product_info on PI_IDX=product_info.IDX join product on product_info.P_IDX=product.idx where (img, Sell_bid) in (select img, MIN(Sell_bid) FROM Sell join product_info on PI_IDX=product_info.IDX join product on product_info.P_IDX=product.IDX where status=0 AND product.model REGEXP ? group by img) order by Sell_bid";
		}
		
		try {
			pstmt = conn.prepareStatement(sql);
			if(!(keyword.equals(""))) { pstmt.setString(1, keyword); }
			rs = pstmt.executeQuery(); 

			while (rs.next()) {
				productBean temp = new productBean();
				String temp_t=rs.getString("img");
				temp.setImg(temp_t);
				temp.setBrand(rs.getString("brand"));
				temp.setPrice(rs.getInt("Sell_bid"));
				temp.setModel((temp_t.substring(0,temp_t.length()-4)));
				list.addElement(temp);
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
		return list;
	}
	
	// 전체 제품 정보를 가져오는 부분
	public static DefaultListModel<productBean> GetTotal(String keyword) {
		DefaultListModel<productBean> list = new DefaultListModel<>();
		
		Connection conn = FindID.connect();
		PreparedStatement pstmt = null;		
		ResultSet rs = null;
		PreparedStatement pstmt2 = null;		
		ResultSet rs2 = null;
		
		String sql;
		String sql2;
		if(keyword.equals(""))
		{
			sql2 = "select img, brand from product where img NOT IN (select img FROM Sell join product_info on PI_IDX=product_info.IDX join product on product_info.P_IDX=product.IDX where status=0 group by img)";
			sql = "select DISTINCT img, Sell_bid, brand from Sell join product_info on PI_IDX=product_info.IDX join product on product_info.P_IDX=product.idx where (img, Sell_bid) in (select img, MIN(Sell_bid) FROM Sell join product_info on PI_IDX=product_info.IDX join product on product_info.P_IDX=product.IDX where status=0 group by img) order by Sell_bid";
		}
		else
		{
			sql2 = "select img, brand from product where img NOT IN (select img FROM Sell join product_info on PI_IDX=product_info.IDX join product on product_info.P_IDX=product.IDX where status=0 group by img) AND img REGEXP ?";
			sql = "select DISTINCT img, Sell_bid, brand from Sell join product_info on PI_IDX=product_info.IDX join product on product_info.P_IDX=product.idx where (img, Sell_bid) in (select img, MIN(Sell_bid) FROM Sell join product_info on PI_IDX=product_info.IDX join product on product_info.P_IDX=product.IDX where status=0 and product.model REGEXP ? group by img) order by Sell_bid";
		}
	
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt2 = conn.prepareStatement(sql2);
			if(!(keyword.equals(""))) { pstmt.setString(1, keyword); pstmt2.setString(1, keyword); }
			rs = pstmt.executeQuery(); 
			rs2 = pstmt2.executeQuery();
			while (rs.next()) {
				productBean temp = new productBean();
				String temp_t=rs.getString("img");
				temp.setImg(temp_t);
				temp.setBrand(rs.getString("brand"));
				temp.setPrice(rs.getInt("Sell_bid"));
				temp.setModel((temp_t.substring(0,temp_t.length()-4)));
				list.addElement(temp);
			}
			while (rs2.next())
			{
				productBean temp = new productBean();
				String temp_t=rs2.getString("img");
				temp.setImg(temp_t);
				temp.setBrand(rs2.getString("brand"));
				temp.setModel((temp_t.substring(0,temp_t.length()-4)));
				temp.setPrice(0);
				list.addElement(temp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if(rs2 != null)
				{
					rs2.close();
				}
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
		return list;
	}
}	
	