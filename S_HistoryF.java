package sch;
import javax.swing.*;
import java.awt.event.*;
import java.util.Map;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;


// 판매 내역 거래창
public class S_HistoryF extends JFrame implements ActionListener, WindowListener {
	private JLabel LabelPrice_t;
	private JLabel LabelModel_t;
	private JLabel LabelImage;
	private detail product;
	private ImageIcon product1;
	private trades temp_p;
	private trades temp_s;
	private JLabel LabelSize_t;
	private JLabel LabelConfirm_t;
	private JLabel LabelShip_t;
	private JButton ButtonCancel;
	private JButton ButtonConfirm;
	private String confirm_s;
	private String status;
	private JButton ButtonDeposit;
	private JButton ButtonShip_s;
	private JButton ButtonCancel_a;
	private int check;
	
	public S_HistoryF(detail temp, Map<String, ImageIcon> map) 
	{
		setIconImage(MainF.Icon_t);
		product = temp;
		Map<String, ImageIcon> map_t = map;
		setSize(600,800);
		setTitle("\uAC70\uB798 \uB0B4\uC5ED");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.addWindowListener(this);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setLayout(null);
		getContentPane().add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBorder(BorderFactory.createLineBorder(Color.gray));
		panel_1.setBounds(0, 0, 596, 401);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		product1 = map.get(temp.getModel());
		Image original = product1.getImage();
		Image change =original.getScaledInstance(400, 300, Image.SCALE_SMOOTH);
		product1 = new ImageIcon(change);	
		
		LabelImage = new JLabel(product1);
		LabelImage.setHorizontalAlignment(SwingConstants.CENTER);
		LabelImage.setBounds(12, 10, 572, 381);
		panel_1.add(LabelImage);
		
		JLabel LabelModel = new JLabel("모델명:");
		LabelModel.setFont(new Font("a뉴굴림3", Font.PLAIN, 17));
		LabelModel.setBounds(10, 411, 65, 50);
		panel.add(LabelModel);
		
		JLabel LabelPrice = new JLabel("\uAC00\uACA9:");
		LabelPrice.setFont(new Font("a뉴굴림3", Font.PLAIN, 17));
		LabelPrice.setBounds(10, 490, 65, 50);
		panel.add(LabelPrice);
		
		JLabel LabelSize = new JLabel("\uC0AC\uC774\uC988:");
		LabelSize.setFont(new Font("a뉴굴림3", Font.PLAIN, 17));
		LabelSize.setBounds(10, 448, 65, 50);
		panel.add(LabelSize);
		
		LabelModel_t = new JLabel(temp.getModel());
		LabelModel_t.setFont(new Font("a뉴굴림3", Font.PLAIN, 12));
		LabelModel_t.setBounds(73, 417, 472, 39);
		panel.add(LabelModel_t);
		
		LabelPrice_t = new JLabel(Integer.toString(temp.getR_Price()));
		LabelPrice_t.setFont(new Font("a뉴굴림3", Font.PLAIN, 12));
		LabelPrice_t.setBounds(55, 497, 280, 34);
		panel.add(LabelPrice_t);
		
		LabelSize_t = new JLabel(Integer.toString(temp.getSize()));
		LabelSize_t.setFont(new Font("a뉴굴림3", Font.PLAIN, 12));
		LabelSize_t.setBounds(73, 453, 100, 39);
		panel.add(LabelSize_t);
		
		JLabel LabelConfirm = new JLabel("\uAC80\uC218:");
		LabelConfirm.setFont(new Font("a뉴굴림3", Font.PLAIN, 17));
		LabelConfirm.setBounds(10, 530, 65, 50);
		panel.add(LabelConfirm);
		
		if(temp.getConfirm())
			confirm_s = "완료";
		else
			confirm_s = "진행중";
		
		LabelConfirm_t = new JLabel(confirm_s);
		LabelConfirm_t.setFont(new Font("a뉴굴림3", Font.PLAIN, 12));
		LabelConfirm_t.setBounds(55, 535, 100, 39);
		panel.add(LabelConfirm_t);
		
		JLabel LabelShip = new JLabel("\uC9C4\uD589 \uC0C1\uD0DC:");
		LabelShip.setFont(new Font("a뉴굴림3", Font.PLAIN, 17));
		LabelShip.setBounds(10, 570, 88, 50);
		panel.add(LabelShip);
		
		if(temp.getStatus() == 0) { status = "입금 요망"; }
		else if(temp.getStatus() == 1) { status = "배송 준비"; }
		else if(temp.getStatus() == 2) { status = "배송 출발"; }
		else if(temp.getStatus() == 3) { status = "배송 완료"; }
		else if(temp.getStatus() == 4) { status = "취소 요청"; }
		else { status = "취소 완료"; }
		
		LabelShip_t = new JLabel(status);
		LabelShip_t.setFont(new Font("a뉴굴림3", Font.PLAIN, 12));
		LabelShip_t.setBounds(95, 575, 100, 39);
		panel.add(LabelShip_t);
		
		ButtonCancel = new JButton("\uAC70\uB798 \uCDE8\uC18C");
		ButtonCancel.setBackground(Color.WHITE);
		ButtonCancel.setFont(new Font("a뉴굴림3", Font.PLAIN, 12));
		ButtonCancel.setBounds(209, 681, 182, 71);
		panel.add(ButtonCancel);
		
		ButtonConfirm = new JButton("\uAC80\uC218 \uC644\uB8CC");
		ButtonConfirm.setFont(new Font("a뉴굴림3", Font.PLAIN, 12));
		ButtonConfirm.setBackground(Color.WHITE);
		ButtonConfirm.setBounds(480, 680, 88, 71);
		panel.add(ButtonConfirm);
		
		ButtonDeposit = new JButton("\uC785\uAE08 \uC644\uB8CC");
		ButtonDeposit.setFont(new Font("a뉴굴림3", Font.PLAIN, 12));
		ButtonDeposit.setBackground(Color.WHITE);
		ButtonDeposit.setBounds(480, 600, 88, 71);
		panel.add(ButtonDeposit);
		
		ButtonShip_s = new JButton("\uBC30\uC1A1 \uCD9C\uBC1C");
		ButtonShip_s.setFont(new Font("a뉴굴림3", Font.PLAIN, 12));
		ButtonShip_s.setBackground(Color.WHITE);
		ButtonShip_s.setBounds(480, 520, 88, 71);
		panel.add(ButtonShip_s);
		
		ButtonCancel_a = new JButton("\uCDE8\uC18C \uC2B9\uC778");
		ButtonCancel_a.setBackground(Color.WHITE);
		ButtonCancel_a.setFont(new Font("a뉴굴림3", Font.PLAIN, 12));
		ButtonCancel_a.setBounds(480, 440, 88, 71);
		panel.add(ButtonCancel_a);
		
		// 관리자 확인 부분
		if(MainF.U_temp.getGrade().equals("관리자"))
		{
			ButtonCancel_a.setVisible(true);
			ButtonShip_s.setVisible(true);
			ButtonDeposit.setVisible(true);
			ButtonConfirm.setVisible(true);
		}
		else
		{
			ButtonCancel_a.setVisible(false);
			ButtonShip_s.setVisible(false);
			ButtonDeposit.setVisible(false);
			ButtonConfirm.setVisible(false);
		}
		
		ButtonCancel_a.addActionListener(this);
		ButtonShip_s.addActionListener(this);
		ButtonDeposit.addActionListener(this);
		ButtonConfirm.addActionListener(this);
		ButtonCancel.addActionListener(this);
		setVisible(true);
	}
	
		public void actionPerformed(ActionEvent e) {
			// 취소 요청
			if(e.getSource() == ButtonCancel)
			{
				int temp = JOptionPane.showConfirmDialog(null, "거래를 취소 하시겠습니까?","확인",JOptionPane.YES_NO_OPTION);
				if(temp == JOptionPane.YES_OPTION)
				{
					check = 4;
					if(product.getClassification() == 0)
					{
						History.SetStatus_s(check, product.getIDX());
					}
					else
					{
						History.SetStatus_p(check, product.getIDX());
					}
					LabelShip_t.setText("취소 요청");
					JOptionPane.showMessageDialog(null, "거래 취소 요청이 완료되었습니다.");
					this.dispose();
					MainF.S_historyF=null;
				}
			}
			// 검수 확인 부분
			else if(e.getSource() == ButtonConfirm)
			{
				LabelConfirm_t.setText("완료");
				if(product.getClassification() == 0)
				{
					History.SetConfirm_s(product.getIDX());
				}
				else
				{
					History.SetConfirm_p(product.getIDX());
				}
				JOptionPane.showMessageDialog(null, "검수가 완료되었습니다.");
			}
			// 취소 승인 부분
			else if(e.getSource() == ButtonCancel_a)
			{
				check=5;
				if(product.getClassification() == 0)
				{
					History.SetStatus_s(check, product.getIDX());
					History.DelTrade_S(product.getIDX());
				}
				else
				{
					History.SetStatus_p(check, product.getIDX());
					History.DelTrade_P(product.getIDX());
				}
				LabelShip_t.setText("취소 승인");
				JOptionPane.showMessageDialog(null, "취소 되었습니다.");
			}
			// 배송 출발 부분
			else if(e.getSource() == ButtonShip_s)
			{
				check=2;
				if(product.getClassification() == 0)
				{
					History.SetStatus_s(check, product.getIDX());
				}
				else
				{
					History.SetStatus_p(check, product.getIDX());
				}
				LabelShip_t.setText("배송 출발");
				JOptionPane.showMessageDialog(null, "배송 출발");
			}
			// 입금 확인 후 배송 준비 부분
			else if(e.getSource() == ButtonDeposit)
			{
				check=1;
				if(product.getClassification() == 0)
				{
					History.SetStatus_s(check, product.getIDX());
				}
				else
				{
					History.SetStatus_p(check, product.getIDX());
				}
				LabelShip_t.setText("배송 준비");
				JOptionPane.showMessageDialog(null, "입금 확인 완료");
			}
		}

		public void windowOpened(WindowEvent e) {}
		public void windowClosing(WindowEvent e) {
			this.dispose();
			MainF.S_historyF = null;
		}
		public void windowClosed(WindowEvent e) {}
		public void windowIconified(WindowEvent e) {}
		public void windowDeiconified(WindowEvent e) {}
		public void windowActivated(WindowEvent e) {}
		public void windowDeactivated(WindowEvent e) {	
		}
	}

