package sch;
import javax.swing.*;
import java.awt.event.*;
import java.util.regex.Pattern;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Color;

// 구매 창
public class Purchase_bidF extends JFrame implements ActionListener {
	private JLabel LabelImage;
	private JLabel LabelSel;
	private JLabel LabelSel2;
	private JLabel LabelPurchase_i;
	private JLabel LabelAddress_i;
	private JLabel LabelPrice;
	private JLabel LabelShip_i;
	private JLabel LabelCheck_i;
	private JLabel LabelPoint_i;
	private JLabel LabelSell_i;
	private JTextField textPrice;
	private JTextField textPoint_i;
	private boolean select;
	private boolean point = false;
	private boolean no_click=false;
	private JButton ButtonPoint;
	private JButton ButtonPurchase;
	private int total = 0;
	private String s_temp;
	private String m_temp;
	private trades t_temp;

	
	public Purchase_bidF(productBean temp, String purchase, String Sell, ImageIcon img, String size, trades temp_t) 
	{
		setIconImage(MainF.Icon_t);
		s_temp = size;
		m_temp = temp.getModel();
		t_temp = temp_t;
		setSize(600,800);
		setTitle("구매 입찰");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setLayout(null);
		getContentPane().add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(0, 0, 596, 202);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		ImageIcon product1 = img;
		Image original = product1.getImage();
		Image change =original.getScaledInstance(290, 180, Image.SCALE_SMOOTH);
		product1 = new ImageIcon(change);	
		
		LabelImage = new JLabel(product1);
		LabelImage.setHorizontalAlignment(SwingConstants.CENTER);
		LabelImage.setBounds(12, 10, 290, 180);
		panel_1.add(LabelImage);
		
		JLabel LabelModel = new JLabel("\uBAA8\uB378\uBA85");
		LabelModel.setFont(new Font("a뉴굴림3", Font.PLAIN, 20));
		LabelModel.setBounds(311, 22, 66, 26);
		panel_1.add(LabelModel);
		
		JLabel LabelModel_i = new JLabel(temp.getModel());
		LabelModel_i.setFont(new Font("a뉴굴림3", Font.PLAIN, 12));
		LabelModel_i.setBounds(314, 58, 260, 15);
		panel_1.add(LabelModel_i);
		
		JLabel LabelSize = new JLabel("\uC0AC\uC774\uC988");
		LabelSize.setFont(new Font("a뉴굴림3", Font.PLAIN, 20));
		LabelSize.setBounds(311, 83, 66, 33);
		panel_1.add(LabelSize);
		
		JLabel LabelSize_i = new JLabel(size);
		LabelSize_i.setFont(new Font("a뉴굴림3", Font.PLAIN, 12));
		LabelSize_i.setBounds(314, 120, 101, 15);
		panel_1.add(LabelSize_i);
		
		JLabel LabelPurchase = new JLabel("\uC989\uC2DC \uAD6C\uB9E4\uAC00");
		LabelPurchase.setFont(new Font("a뉴굴림3", Font.PLAIN, 20));
		LabelPurchase.setHorizontalAlignment(SwingConstants.CENTER);
		LabelPurchase.setBounds(100, 212, 150, 47);
		panel.add(LabelPurchase);
		
		JLabel LabelSell = new JLabel("\uC989\uC2DC \uD310\uB9E4\uAC00");
		LabelSell.setHorizontalAlignment(SwingConstants.CENTER);
		LabelSell.setFont(new Font("a뉴굴림3", Font.PLAIN, 20));
		LabelSell.setBounds(350, 212, 178, 54);
		panel.add(LabelSell);
		
		LabelPrice = new JLabel("\uAD6C\uB9E4 \uD76C\uB9DD\uAC00");
		LabelPrice.setFont(new Font("a뉴굴림3", Font.PLAIN, 15));
		LabelPrice.setBounds(41, 333, 106, 23);
		panel.add(LabelPrice);
		
		textPrice = new JTextField();
		textPrice.setFont(new Font("굴림", Font.PLAIN, 20));
		textPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		textPrice.setBounds(41, 366, 497, 60);
		panel.add(textPrice);
		textPrice.setColumns(10);
		
		JLabel LabelPrice_won = new JLabel("\uC6D0");
		LabelPrice_won.setFont(new Font("a뉴굴림3", Font.PLAIN, 25));
		LabelPrice_won.setBounds(546, 365, 38, 60);
		panel.add(LabelPrice_won);
		
		JLabel LabelPoint = new JLabel("\uD3EC\uC778\uD2B8");
		LabelPoint.setFont(new Font("a뉴굴림3", Font.PLAIN, 20));
		LabelPoint.setHorizontalAlignment(SwingConstants.CENTER);
		LabelPoint.setBounds(41, 447, 66, 36);
		panel.add(LabelPoint);
		
		JLabel LabelCheck = new JLabel("\uAC80\uC218\uBE44");
		LabelCheck.setHorizontalAlignment(SwingConstants.CENTER);
		LabelCheck.setFont(new Font("a뉴굴림3", Font.PLAIN, 20));
		LabelCheck.setBounds(41, 493, 66, 36);
		panel.add(LabelCheck);
		
		JLabel LabelShip = new JLabel("\uBC30\uC1A1\uBE44");
		LabelShip.setFont(new Font("a뉴굴림3", Font.PLAIN, 20));
		LabelShip.setHorizontalAlignment(SwingConstants.CENTER);
		LabelShip.setBounds(41, 539, 66, 36);
		panel.add(LabelShip);
		
		JLabel LabelAddress = new JLabel("\uBC30\uC1A1 \uC8FC\uC18C");
		LabelAddress.setFont(new Font("a뉴굴림3", Font.PLAIN, 20));
		LabelAddress.setHorizontalAlignment(SwingConstants.CENTER);
		LabelAddress.setBounds(41, 602, 88, 36);
		panel.add(LabelAddress);
		
		LabelAddress_i = new JLabel(MainF.U_temp.getAddress());
		LabelAddress_i.setFont(new Font("a뉴굴림3", Font.PLAIN, 20));
		LabelAddress_i.setBounds(41, 648, 473, 36);
		panel.add(LabelAddress_i);
		
		ButtonPurchase = new JButton("\uAD6C\uB9E4");
		ButtonPurchase.setBackground(Color.WHITE);
		ButtonPurchase.setFont(new Font("a뉴굴림3", Font.PLAIN, 20));
		ButtonPurchase.setBounds(216, 700, 150, 50);
		panel.add(ButtonPurchase);
		
		LabelPurchase_i = new JLabel(purchase);
		LabelPurchase_i.setFont(new Font("a뉴굴림3", Font.PLAIN, 20));
		LabelPurchase_i.setHorizontalAlignment(SwingConstants.CENTER);
		LabelPurchase_i.setBounds(100, 258, 166, 25);
		panel.add(LabelPurchase_i);
		
		LabelSell_i = new JLabel(Sell);
		LabelSell_i.setFont(new Font("a뉴굴림3", Font.PLAIN, 20));
		LabelSell_i.setHorizontalAlignment(SwingConstants.CENTER);
		LabelSell_i.setBounds(350, 258, 178, 25);
		panel.add(LabelSell_i);
		
		LabelPoint_i = new JLabel(Integer.toString(MainF.U_temp.getPoint()));
		LabelPoint_i.setFont(new Font("a뉴굴림3", Font.PLAIN, 15));
		LabelPoint_i.setHorizontalAlignment(SwingConstants.RIGHT);
		LabelPoint_i.setBounds(396, 460, 106, 23);
		panel.add(LabelPoint_i);
		
		LabelCheck_i = new JLabel("3000");
		LabelCheck_i.setFont(new Font("a뉴굴림3", Font.PLAIN, 20));
		LabelCheck_i.setHorizontalAlignment(SwingConstants.RIGHT);
		LabelCheck_i.setBounds(421, 492, 150, 39);
		panel.add(LabelCheck_i);
		
		LabelShip_i = new JLabel("3000");
		LabelShip_i.setFont(new Font("a뉴굴림3", Font.PLAIN, 20));
		LabelShip_i.setHorizontalAlignment(SwingConstants.RIGHT);
		LabelShip_i.setBounds(421, 539, 150, 36);
		panel.add(LabelShip_i);
		
		LabelSel = new JLabel("\uAD6C\uB9E4 \uC785\uCC30");
		LabelSel.setFont(new Font("a뉴굴림3", Font.PLAIN, 20));
		LabelSel.setForeground(Color.BLACK);
		LabelSel.setHorizontalAlignment(SwingConstants.CENTER);
		LabelSel.setBounds(41, 293, 265, 30);
		panel.add(LabelSel);
		LabelSel.addMouseListener(m);
		
		LabelSel2 = new JLabel("\uC989\uC2DC \uAD6C\uB9E4");
		LabelSel2.setFont(new Font("a뉴굴림3", Font.PLAIN, 20));
		LabelSel2.setHorizontalAlignment(SwingConstants.CENTER);
		LabelSel2.setBounds(316, 293, 255, 30);
		panel.add(LabelSel2);
		LabelSel2.addMouseListener(m);
		
		ButtonPoint = new JButton("\uC0AC\uC6A9");
		ButtonPoint.setBackground(Color.WHITE);
		ButtonPoint.setFont(new Font("a뉴굴림3", Font.PLAIN, 11));
		ButtonPoint.setBounds(514, 443, 57, 40);
		panel.add(ButtonPoint);
		
		textPoint_i = new JTextField();
		textPoint_i.setHorizontalAlignment(SwingConstants.RIGHT);
		textPoint_i.setBounds(397, 443, 106, 21);
		panel.add(textPoint_i);
		textPoint_i.setColumns(10);
		
		ButtonPoint.addActionListener(this);
		ButtonPurchase.addActionListener(this);
		setVisible(true);
		// 즉시 구매가 0이면 판매 제품이 없으므로 입찰만 가능 
		if(LabelPurchase_i.getText().equals("0"))
		{
			LabelSel.setOpaque(true);
			LabelSel.setBackground(Color.RED);
			LabelPoint_i.enable(false);
			select = true;
			no_click=true;
		}
		// 판매 제품이 있으므로 즉시 구매 가능
		else
		{
			no_click=false; // 즉시 판매가 없으면 클릭 안되게
			select = false;
			LabelSel2.setOpaque(true);
			LabelSel2.setBackground(Color.RED);
			textPrice.setText(LabelPurchase_i.getText());
			textPrice.enable(false);
			LabelPrice.setText("즉시 구매가");
		}
	}
	MouseListener m = new MouseAdapter() {
		public void mouseClicked(MouseEvent e)
		{
			// 입찰 라벨 선택
			if(e.getSource() == LabelSel)
			{
				select = true;
				LabelPrice.setText("구매 희망가");
				LabelSel.setOpaque(true);
				textPrice.setText("");
				textPrice.enable(true);
				LabelSel2.setBackground(null);
				LabelSel.setBackground(Color.RED);
			}
			// 즉시 구매 라벨 선택
			else if(e.getSource() == LabelSel2 && select && !no_click)
			{
				select = false;
				LabelPrice.setText("즉시 구매가");
				LabelSel.setBackground(null);
				LabelSel2.setOpaque(true);
				LabelSel2.setBackground(Color.RED);
				textPrice.setText(LabelPurchase_i.getText());
				textPrice.enable(select);
			}
		}
	};
	
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == ButtonPoint && !select)
			{
				// 포인트 확인 부분
				if(textPoint_i.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "포인트를 입력해주세요.");
				}
				else if(Integer.parseInt(textPoint_i.getText()) > Integer.parseInt(LabelPoint_i.getText()))
				{
					JOptionPane.showMessageDialog(null, "포인트를 다시 입력해주세요.");
				}
				else
				{
					JOptionPane.showMessageDialog(null, "포인트가 적용되었습니다.");
					textPoint_i.enable(false);
					point = true;
				}
			}
			// 입찰일 경우 포인트 사용 불가
			else if(e.getSource() == ButtonPoint && select)
			{
				JOptionPane.showMessageDialog(null, "현재 입찰 상태이므로 포인트 사용이 불가합니다. ");
			}
			// 구매 버튼 
			if(e.getSource() == ButtonPurchase)
			{
				// 로그인 체크 부분
				if(!MainF.IsLogin)
				{
					JOptionPane.showMessageDialog(null, "로그인을 해 주세요.");
				}
				else
				{
					// 가격 유효성 체크
					if(textPrice.getText().equals(""))
					{
						JOptionPane.showMessageDialog(null, "구매 희망가를 적어주세요.");
					}
					else if(!Pattern.matches("^[0-9]*$", textPrice.getText()))
					{
						JOptionPane.showMessageDialog(null, "구매 희망가가 올바르지 않습니다.");
					}
					else
					{
						// 즉시 구매가보다 높을 시 즉시 구매 
						if(Integer.parseInt(textPrice.getText()) > Integer.parseInt(LabelPurchase_i.getText()) && !(LabelPurchase_i.getText().equals("0")))
						{
							int temp = JOptionPane.showConfirmDialog(null, "즉시 구매가보다 높은 가격을 입찰하려고 합니다. 즉시 구매가로 구매 하시겠습니까?","확인",JOptionPane.YES_NO_OPTION);
							if(temp == JOptionPane.YES_OPTION)
							{
								total = Integer.parseInt(LabelPurchase_i.getText()) + Integer.parseInt(LabelCheck_i.getText())+Integer.parseInt(LabelShip_i.getText());
								// 포인트 사용 눌렀을 시 처리 부분
								if(point)
								{
									total -= Integer.parseInt(textPoint_i.getText());
									if(total < 0)
									{
										UserInfo.SetUserPoint(total *= -1, MainF.U_temp.getID());
										total=0;
									}
									else
									{
										UserInfo.SetUserPoint(MainF.U_temp.getPoint()-Integer.parseInt(textPoint_i.getText()), MainF.U_temp.getID());
									}
									Trade.setPurchase_t(total, MainF.U_temp.getID(),t_temp);
									JOptionPane.showMessageDialog(null, "<html>"+"구매가 완료되었습니다."+"<br>"+"카카오뱅크:3333-01-5988475 박찬홍"+"<br>"+"총 금액:"+total+"원"+"<br>"+"입금 부탁드립니다."+"</html>");
									this.dispose();
								}
								else
								{
									Trade.setPurchase_t(total, MainF.U_temp.getID(),t_temp);
									JOptionPane.showMessageDialog(null, "<html>"+"구매가 완료되었습니다."+"<br>"+"카카오뱅크:3333-01-5988475 박찬홍"+"<br>"+"총 금액:"+total+"원"+"<br>"+"입금 부탁드립니다."+"</html>");
									this.dispose();
								}
							}
						}
						else
						{
							// 입찰 처리 부분
							if(select)
							{
								Trade.setPurchase(Integer.parseInt(textPrice.getText()),MainF.U_temp.getID(),s_temp,m_temp,MainF.U_temp.getAddress());
								JOptionPane.showMessageDialog(null, "입찰이 완료되었습니다.");
								this.dispose();
							}
							else
							{
								// 즉시 구매 부분
								int temp = JOptionPane.showConfirmDialog(null, "즉시 구매하시겠습니까?","확인",JOptionPane.YES_NO_OPTION);
								if (temp == JOptionPane.YES_OPTION)
								{
									total = Integer.parseInt(LabelPurchase_i.getText()) + Integer.parseInt(LabelCheck_i.getText())+Integer.parseInt(LabelShip_i.getText());
									if(point)
									{
										total -= Integer.parseInt(textPoint_i.getText());
										if(total < 0)
										{
											UserInfo.SetUserPoint(total *= -1, MainF.U_temp.getID());
											total=0;
										}
										else
										{
											UserInfo.SetUserPoint(MainF.U_temp.getPoint()-Integer.parseInt(textPoint_i.getText()), MainF.U_temp.getID());
										}
										Trade.setPurchase_t(total, MainF.U_temp.getID(),t_temp);
										JOptionPane.showMessageDialog(null, "<html>"+"구매가 완료되었습니다."+"<br>"+"카카오뱅크:3333-01-5988475 박찬홍"+"<br>"+"총 금액:"+total+"원"+"<br>"+"입금 부탁드립니다."+"</html>");
										this.dispose();
									}
									else
									{
										Trade.setPurchase_t(total, MainF.U_temp.getID(),t_temp);
										JOptionPane.showMessageDialog(null, "<html>"+"구매가 완료되었습니다."+"<br>"+"카카오뱅크:3333-01-5988475 박찬홍"+"<br>"+"총 금액:"+total+"원"+"<br>"+"입금 부탁드립니다."+"</html>");
										this.dispose();
									}
								}
							}
						}
					}
				}
			}
	}
}

