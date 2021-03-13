package sch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.DefaultListModel;

// DB로부터 제품 정보 받아오는 부분
class detail
{
	private int IDX;
	private boolean confirm;
	private int status;
	private int R_price;
	private int S_IDX;
	private int U_TIDX;
	private int size;
	private String model;
	private String img;
	private int classification;
	
	void setClassification(int cf)
	{
		this.classification = cf;
	}
	int getClassification()
	{
		return classification;
	}
	
	void setIDX(int idx)
	{
		this.IDX = idx;
	}
	int getIDX()
	{
		return IDX;
	}
	void setConfirm(boolean confirm)
	{
		this.confirm = confirm;
	}
	boolean getConfirm()
	{
		return confirm;
	}
	void setStatus(int status)
	{
		this.status = status;
	}
	int getStatus()
	{
		return status;
	}
	void setR_Price(int R_price)
	{
		this.R_price = R_price;
	}
	int getR_Price()
	{
		return R_price;
	}
	void setS_IDX(int S_IDX)
	{
		this.S_IDX = S_IDX;
	}
	int getS_IDX()
	{
		return S_IDX;
	}
	void setU_TIDX(int U_TIDX)
	{
		this.U_TIDX = U_TIDX;
	}
	int getU_TIDX()
	{
		return U_TIDX;
	}
	void setSize(int size)
	{
		this.size = size;
	}
	int getSize()
	{
		return size;
	}
	void setModel(String model)
	{
		this.model = model;
	}
	String getModel()
	{
		return model;
	}
	void setImg(String img)
	{
		this.img = img;
	}
	String getImg()
	{
		return img;
	}
}
 
public class getProduct {
	// 모든 구매 내역 제품 정보 받는 부분
	public static DefaultListModel<detail> GetTrade_list_p() {
		DefaultListModel<detail> list = new DefaultListModel<>();
		
		Connection conn = FindID.connect();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// S_Trade 구매 내역
		String sql = "select product.img, product.model, product_info.size, S_Trade.IDX, S_Trade.confirm, S_Trade.status, S_Trade.R_price, S_Trade.S_IDX, S_Trade.U_TIDX from product join product_info on product_info.P_IDX=product.IDX join Sell on Sell.PI_IDX=product_info.IDX join S_Trade on S_Trade.S_IDX=Sell.IDX";
		
		try {
			// 쿼리 실행
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery(); 
			while (rs.next())
			{
				detail temp = new detail();
				temp.setIDX(rs.getInt("IDX"));
				temp.setConfirm(rs.getBoolean("confirm"));
				temp.setStatus(rs.getInt("status"));
				temp.setR_Price(rs.getInt("R_price"));
				temp.setS_IDX(rs.getInt("S_IDX"));
				temp.setU_TIDX(rs.getInt("U_TIDX"));
				temp.setSize(rs.getInt("size"));
				temp.setModel(rs.getString("model"));
				temp.setImg(rs.getString("img"));
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
	// 모든 판매 내역  제품 정보 받아오는 부분
	public static DefaultListModel<detail> GetTrade_list_s() {
		DefaultListModel<detail> list = new DefaultListModel<>();
		
		Connection conn = FindID.connect();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// P_Trade 판매 내역
		String sql = "select product.img, product.model, product_info.size, P_Trade.IDX, P_Trade.confirm, P_Trade.status, P_Trade.R_price, P_Trade.PC_IDX, P_Trade.U_TIDX  from product join product_info on product_info.P_IDX=product.IDX join PURCHASE on PURCHASE.PI_IDX=product_info.IDX join P_Trade on P_Trade.PC_IDX=PURCHASE.IDX";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();  
			while (rs.next())
			{
				detail temp = new detail();
				temp.setIDX(rs.getInt("IDX"));
				temp.setConfirm(rs.getBoolean("confirm"));
				temp.setStatus(rs.getInt("status"));
				temp.setR_Price(rs.getInt("R_price"));
				temp.setS_IDX(rs.getInt("PC_IDX"));
				temp.setU_TIDX(rs.getInt("U_TIDX"));
				temp.setSize(rs.getInt("size"));
				temp.setModel(rs.getString("model"));
				temp.setImg(rs.getString("img"));
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
	// 해당 유저의 판매 내역 제품 정보 받아오는 부분
	public static DefaultListModel<detail> GetSell_list(String ID) {
		DefaultListModel<detail> list = new DefaultListModel<>();
		
		Connection conn = FindID.connect();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs2 = null;
		String sql = "select product.img, product.model, product_info.size, P_Trade.IDX, P_Trade.confirm, P_Trade.status, P_Trade.R_price, P_Trade.PC_IDX, P_Trade.U_TIDX  from product join product_info on product_info.P_IDX=product.IDX join PURCHASE on PURCHASE.PI_IDX=product_info.IDX join P_Trade on P_Trade.PC_IDX=PURCHASE.IDX where P_Trade.U_TIDX=(select IDX FROM USER where UID=?)";
		String sql2 = "select product.img, product.model, product_info.size, S_Trade.IDX, S_Trade.confirm, S_Trade.status, S_Trade.R_price, S_Trade.S_IDX, S_Trade.U_TIDX from product join product_info on product_info.P_IDX=product.IDX join Sell on Sell.PI_IDX=product_info.IDX join S_Trade on S_Trade.S_IDX=Sell.IDX where S_Trade.S_IDX in (select IDX from Sell where status=1 and U_IDX in (select IDX from USER WHERE UID=?))";
		
		try {
			// 쿼리 실행
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ID);
			rs = pstmt.executeQuery(); 
			pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setString(1, ID);
			rs2 = pstmt2.executeQuery(); 
			while (rs.next())
			{
				detail temp = new detail();
				temp.setIDX(rs.getInt("IDX"));
				temp.setConfirm(rs.getBoolean("confirm"));
				temp.setStatus(rs.getInt("status"));
				temp.setR_Price(rs.getInt("R_price"));
				temp.setS_IDX(rs.getInt("PC_IDX"));
				temp.setU_TIDX(rs.getInt("U_TIDX"));
				temp.setSize(rs.getInt("size"));
				temp.setModel(rs.getString("model"));
				temp.setImg(rs.getString("img"));
				temp.setClassification(0);
				list.addElement(temp);
			}
			while(rs2.next())
			{
				detail temp = new detail();
				temp.setIDX(rs2.getInt("IDX"));
				temp.setConfirm(rs2.getBoolean("confirm"));
				temp.setStatus(rs2.getInt("status"));
				temp.setR_Price(rs2.getInt("R_price"));
				temp.setS_IDX(rs2.getInt("S_IDX"));
				temp.setU_TIDX(rs2.getInt("U_TIDX"));
				temp.setSize(rs2.getInt("size"));
				temp.setModel(rs2.getString("model"));
				temp.setImg(rs2.getString("img"));
				temp.setClassification(1);
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
	// 해당 유저의 구매 내역 제품 정보 받는 부분
	public static DefaultListModel<detail> GetPurchase_list(String ID) {
		DefaultListModel<detail> list = new DefaultListModel<>();
		
		Connection conn = FindID.connect();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs2 = null;
		String sql = "select product.img, product.model, product_info.size, S_Trade.IDX, S_Trade.confirm, S_Trade.status, S_Trade.R_price, S_Trade.S_IDX, S_Trade.U_TIDX from product join product_info on product_info.P_IDX=product.IDX join Sell on Sell.PI_IDX=product_info.IDX join S_Trade on S_Trade.S_IDX=Sell.IDX where S_Trade.U_TIDX=(select IDX FROM USER where UID=?)";
		String sql2 = "select product.img, product.model, product_info.size, P_Trade.IDX, P_Trade.confirm, P_Trade.status, P_Trade.R_price, P_Trade.PC_IDX, P_Trade.U_TIDX from product join product_info on product_info.P_IDX=product.IDX join PURCHASE on PURCHASE.PI_IDX=product_info.IDX join P_Trade on P_Trade.PC_IDX=PURCHASE.IDX where P_Trade.PC_IDX in (select IDX from PURCHASE where status=1 and U_IDX in (select IDX from USER WHERE UID=?))";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ID);
			rs = pstmt.executeQuery(); 
			pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setString(1, ID);
			rs2 = pstmt2.executeQuery(); 
			while (rs.next())
			{
				detail temp = new detail();
				temp.setIDX(rs.getInt("IDX"));
				temp.setConfirm(rs.getBoolean("confirm"));
				temp.setStatus(rs.getInt("status"));
				temp.setR_Price(rs.getInt("R_price"));
				temp.setS_IDX(rs.getInt("S_IDX"));
				temp.setU_TIDX(rs.getInt("U_TIDX"));
				temp.setSize(rs.getInt("size"));
				temp.setModel(rs.getString("model"));
				temp.setImg(rs.getString("img"));
				temp.setClassification(0);
				list.addElement(temp);
			}
			while (rs2.next())
			{
				detail temp = new detail();
				temp.setIDX(rs2.getInt("IDX"));
				temp.setConfirm(rs2.getBoolean("confirm"));
				temp.setStatus(rs2.getInt("status"));
				temp.setR_Price(rs2.getInt("R_price"));
				temp.setS_IDX(rs2.getInt("PC_IDX"));
				temp.setU_TIDX(rs2.getInt("U_TIDX"));
				temp.setSize(rs2.getInt("size"));
				temp.setModel(rs2.getString("model"));
				temp.setImg(rs2.getString("img"));
				temp.setClassification(1);
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
				if (rs2 != null) {
					rs.close();
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
		return list;
	}
	// 해당 유저의 찜 목록 제품 정보 받는 부분
	public static DefaultListModel<productBean> GetBasket(String ID) {
		DefaultListModel<productBean> list = new DefaultListModel<>();
		
		Connection conn = FindID.connect();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select model, brand, img from product where IDX in (select P_IDX from product_info where IDX in (select PI_IDX from Basket where U_IDX in (select IDX from USER WHERE UID=?)) group by P_IDX)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ID);
			rs = pstmt.executeQuery(); 
			
			while(rs.next())
			{
				productBean temp = new productBean();
				temp.setModel(rs.getString("model"));
				temp.setBrand(rs.getString("brand"));
				temp.setImg(rs.getString("img"));
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
	// 공지 정보를 받는 부분
	public static DefaultListModel<String> GetNotice() {
		DefaultListModel<String> list = new DefaultListModel<>();
		Connection conn = FindID.connect();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select img from Notice";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery(); 

			while (rs.next()) {
				list.addElement(rs.getString("img"));
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
	// 발매 제품 정보를 받는 부분
	public static DefaultListModel<productBean> Release_info() {
		DefaultListModel<productBean> list = new DefaultListModel<>();
		Connection conn = FindID.connect();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from Release_info";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery(); 
			
			while (rs.next()) {
				productBean temp = new productBean();
				temp.setModel(rs.getString("model"));
				temp.setBrand(rs.getString("brand"));
				temp.setImg(rs.getString("img"));
				temp.setDate(rs.getString("date"));
				temp.setPrice(rs.getInt("price"));
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
	public static DefaultListModel<reviewBean> GetReview() {
		DefaultListModel<reviewBean> list = new DefaultListModel<>();
		
		Connection conn = FindID.connect();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// Review 내역
		String sql = "select * from Review";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();  
			while (rs.next())
			{
				reviewBean temp = new reviewBean();
				temp.setModel(rs.getString("model"));
				temp.setSize(rs.getInt("size"));
				temp.setImg(rs.getString("img"));
				temp.setUid(rs.getString("uid"));
				temp.setContent(rs.getString("content"));
				temp.setS_IDX(rs.getInt("S_IDX"));
				temp.setP_IDX(rs.getInt("P_IDX"));
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
}	
