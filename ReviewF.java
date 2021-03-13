package sch;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.*;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Font;
import java.awt.Color;

// 리뷰 창
public class ReviewF extends JFrame implements ActionListener, WindowListener {
	private JTextField textLocation;
	private JButton ButtonImg;
	private JButton ButtonRegister;
	private File temp;
	private JTextArea textArea;
	private String model_t;
	private int size_t;
	private detail product_t;
	private int check=1;
	
	public ReviewF(detail p, ImageIcon product) 
	{
		product_t = p;
		model_t = product_t.getModel();
		size_t = product_t.getSize();
		setIconImage(MainF.Icon_t);
		setSize(600,800);
		setTitle("리뷰 작성");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.addWindowListener(this);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel LabelModel = new JLabel("\uBAA8\uB378\uBA85");
		LabelModel.setFont(new Font("a뉴굴림3", Font.PLAIN, 12));
		LabelModel.setBounds(389, 10, 52, 15);
		panel.add(LabelModel);
		
		JLabel LabelSize = new JLabel("Size");
		LabelSize.setFont(new Font("a뉴굴림3", Font.PLAIN, 12));
		LabelSize.setBounds(389, 69, 52, 15);
		panel.add(LabelSize);
		
		textLocation = new JTextField();
		textLocation.setBounds(12, 194, 429, 21);
		panel.add(textLocation);
		textLocation.setColumns(10);
		textLocation.setEnabled(false);
		
		ButtonImg = new JButton("\uC774\uBBF8\uC9C0 \uBD88\uB7EC\uC624\uAE30");
		ButtonImg.setBackground(Color.WHITE);
		ButtonImg.setFont(new Font("a뉴굴림3", Font.PLAIN, 12));
		ButtonImg.setBounds(453, 193, 125, 23);
		panel.add(ButtonImg);
		
		Image orignal = product.getImage();
		Image change = orignal.getScaledInstance(360, 170, Image.SCALE_SMOOTH);
		product = new ImageIcon(change);
		JLabel LabelModel_I = new JLabel(product);
		LabelModel_I.setHorizontalAlignment(SwingConstants.CENTER);
		LabelModel_I.setBounds(12, 10, 360, 170);
		panel.add(LabelModel_I);
		
		JLabel LabelModel_i = new JLabel(model_t);
		LabelModel_i.setFont(new Font("a뉴굴림3", Font.PLAIN, 12));
		LabelModel_i.setBounds(389, 35, 174, 15);
		panel.add(LabelModel_i);
		
		JLabel LabelSize_i = new JLabel(Integer.toString(size_t));
		LabelSize_i.setFont(new Font("a뉴굴림3", Font.PLAIN, 12));
		LabelSize_i.setBounds(389, 90, 67, 15);
		panel.add(LabelSize_i);
		
		ButtonRegister = new JButton("\uB4F1\uB85D");
		ButtonRegister.setBackground(Color.WHITE);
		ButtonRegister.setFont(new Font("a뉴굴림3", Font.PLAIN, 12));
		ButtonRegister.setBounds(250, 715, 100, 40);
		panel.add(ButtonRegister);
		
		textArea = new JTextArea();
		textArea.setBounds(12, 225, 572, 480);
		textArea.setLineWrap(true);
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(12, 225, 566, 473);
		panel.add(scrollPane);
		
		setVisible(true);
		
		ButtonImg.addActionListener(this);
		ButtonRegister.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		// 리뷰 이미지 가져오기
		if(e.getSource() == ButtonImg)
		{
			temp=searchFile();
			if(check == 0)
			{
				textLocation.setText(temp.getName());
			}
		}
		// 리뷰 등록 버튼
		else if(e.getSource() == ButtonRegister)
		{
			GetImage.ConSSH();
			GetImage.upload(temp);
			GetImage.disconnection();
			if(product_t.getClassification() == 0)
			{
				review_d(model_t,size_t,temp.getName(),textArea.getText(),product_t.getIDX());
			}
			else
			{
				review_b(model_t,size_t,temp.getName(),textArea.getText(),product_t.getIDX());
			}
			JOptionPane.showMessageDialog(null, "리뷰가 등록되었습니다!");
			this.dispose();
			MainF.reviewF = null;
		}
	}
	public void windowOpened(WindowEvent e) {}
	public void windowClosing(WindowEvent e) {
		this.dispose();
		MainF.reviewF = null;
	}
	public void windowClosed(WindowEvent e) {}
	public void windowIconified(WindowEvent e) {}
	public void windowDeiconified(WindowEvent e) {}
	public void windowActivated(WindowEvent e) {}
	public void windowDeactivated(WindowEvent e) {	
	}
	
	// 리뷰 업로드 파일 찾기
	public File searchFile() {
		File file = null;
		JFileChooser jfc = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF & PNG Images", "jpg", "gif","png");
		jfc.setFileFilter(filter);
		int returnVal = jfc.showSaveDialog(null);
		if(returnVal == 0) {
			file = jfc.getSelectedFile(); 
			check=0;
		}
		else
		{
			check=1;
		}
		return file;
	}
	// 리뷰 작성 
	public void review_d(String m, int s, String i, String c, int S_IDX) {
		Connection conn = FindID.connect();
		
		PreparedStatement pstmt = null;
		String sql = "insert into Review(model,size,img,uid,content,S_IDX) values(?,?,?,?,?,?)";
		c = c.replaceAll("\r\n", "<br>");
		try {
			// 쿼리 실행
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m);
			pstmt.setInt(2, s);
			pstmt.setString(3, i);
			pstmt.setString(4, MainF.U_temp.getID());
			pstmt.setString(5, c);
			pstmt.setInt(6, S_IDX);
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
	// 리뷰 작성 
	public void review_b(String m, int s, String i, String c, int P_IDX) {
		Connection conn = FindID.connect();
		
		PreparedStatement pstmt = null;
		String sql = "insert into Review(model,size,img,uid,content,P_IDX) values(?,?,?,?,?,?)";
		c = c.replaceAll("\r\n", "<br>");
		
		try {
			// 쿼리 실행
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m);
			pstmt.setInt(2, s);
			pstmt.setString(3, i);
			pstmt.setString(4, MainF.U_temp.getID());
			pstmt.setString(5, c);
			pstmt.setInt(6, P_IDX);
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
	// 포인트 증가 및 지급 완료
	public static void review_p(String UID, String img) {
		Connection conn = FindID.connect();
		
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		String sql = "update USER set point=point+3000 where UID=?";
		String sql2 = "update Review set confirm=1 where img=?";
		
		try {
			// 쿼리 실행
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, UID);
			pstmt.executeUpdate();
			pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setString(1, img);
			pstmt2.executeUpdate();
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
}