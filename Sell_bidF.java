package sch;
import javax.swing.*;
import java.awt.event.*;
import java.util.regex.Pattern;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Color;

// 판매 거래창
public class Sell_bidF extends JFrame implements ActionListener {
	private JLabel LabelImage;
	private JLabel LabelSel;
	private JLabel LabelSel2;
	private JLabel LabelPurchase_i;
	private JLabel LabelAddress_i;
	private JLabel LabelPrice;
	private JLabel LabelShip_i;
	private JLabel LabelCheck_i;
	private JLabel LabelFees_i;
	private JLabel LabelSell_i;
	private JTextField textPrice;
	private boolean select;
	private boolean no_click=false;
	private JButton ButtonSell;
	private int total = 0;
	private String s_temp;
	private String m_temp;
	private trades t_temp;

	
	public Sell_bidF(productBean temp, String purchase, String Sell, ImageIcon img, String size, trades temp_p) 
	{
		setIconImage(MainF.Icon_t);
		s_temp = size;
		m_temp = temp.getModel();
		t_temp = temp_p;
		setSize(600,800);
		setTitle("판매 입찰");
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
		LabelSize_i.setBounds(314, 122, 101, 15);
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
		
		LabelPrice = new JLabel("\uD310\uB9E4 \uD76C\uB9DD\uAC00");
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
		LabelPrice_won.setBounds(546, 365, 25, 60);
		panel.add(LabelPrice_won);
		
		JLabel LabelFees = new JLabel("\uC218\uC218\uB8CC");
		LabelFees.setFont(new Font("a뉴굴림3", Font.PLAIN, 20));
		LabelFees.setHorizontalAlignment(SwingConstants.CENTER);
		LabelFees.setBounds(41, 447, 66, 36);
		panel.add(LabelFees);
		
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
		
		JLabel LabelAddress = new JLabel("\uBC18\uC1A1 \uC8FC\uC18C");
		LabelAddress.setFont(new Font("a뉴굴림3", Font.PLAIN, 20));
		LabelAddress.setHorizontalAlignment(SwingConstants.CENTER);
		LabelAddress.setBounds(41, 602, 88, 36);
		panel.add(LabelAddress);
		
		LabelAddress_i = new JLabel(MainF.U_temp.getAddress());
		LabelAddress_i.setFont(new Font("a뉴굴림3", Font.PLAIN, 20));
		LabelAddress_i.setBounds(41, 648, 473, 36);
		panel.add(LabelAddress_i);
		
		ButtonSell = new JButton("\uD310\uB9E4");
		ButtonSell.setBackground(Color.WHITE);
		ButtonSell.setFont(new Font("a뉴굴림3", Font.PLAIN, 20));
		ButtonSell.setBounds(216, 700, 150, 50);
		panel.add(ButtonSell);
		
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
		
		LabelFees_i = new JLabel("1000");
		LabelFees_i.setFont(new Font("a뉴굴림3", Font.PLAIN, 20));
		LabelFees_i.setHorizontalAlignment(SwingConstants.RIGHT);
		LabelFees_i.setBounds(421, 447, 150, 36);
		panel.add(LabelFees_i);
		
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
		
		LabelSel = new JLabel("\uD310\uB9E4 \uC785\uCC30");
		LabelSel.setFont(new Font("a뉴굴림3", Font.PLAIN, 20));
		LabelSel.setForeground(Color.BLACK);
		LabelSel.setHorizontalAlignment(SwingConstants.CENTER);
		LabelSel.setBounds(41, 293, 265, 30);
		panel.add(LabelSel);
		LabelSel.addMouseListener(m);
		
		LabelSel2 = new JLabel("\uC989\uC2DC \uD310\uB9E4");
		LabelSel2.setFont(new Font("a뉴굴림3", Font.PLAIN, 20));
		LabelSel2.setHorizontalAlignment(SwingConstants.CENTER);
		LabelSel2.setBounds(316, 293, 255, 30);
		panel.add(LabelSel2);
		LabelSel2.addMouseListener(m);
		ButtonSell.addActionListener(this);
		setVisible(true);
		
		// 즉시 판매가 0이면 입찰만 가능
		if(LabelSell_i.getText().equals("0"))
		{
			LabelSel.setOpaque(true);
			LabelSel.setBackground(Color.RED);
			select = true;
			no_click=true;
		}
		else
		{
			no_click=false; // 즉시 판매 불가
			select = false;
			LabelSel2.setOpaque(true);
			LabelSel2.setBackground(Color.RED);
			textPrice.setText(LabelSell_i.getText());
			textPrice.enable(false);
			LabelPrice.setText("즉시 판매가");
		}
	}
	MouseListener m = new MouseAdapter() {
		public void mouseClicked(MouseEvent e)
		{
			// 판매 입찰, 즉시 판매 선택 부분
			if(e.getSource() == LabelSel)
			{
				select=true;
				LabelPrice.setText("판매 희망가");
				LabelSel.setOpaque(true);
				textPrice.setText("");
				textPrice.enable(true);
				LabelSel2.setBackground(null);
				LabelSel.setBackground(Color.RED);
			}
			else if(e.getSource() == LabelSel2 && !no_click)
			{
				select=false;
				LabelPrice.setText("즉시 판매가");
				LabelSel.setBackground(null);
				LabelSel2.setOpaque(true);
				LabelSel2.setBackground(Color.RED);
				textPrice.setText(LabelSell_i.getText());
				textPrice.enable(false);
			}
		}
	};
	
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == ButtonSell)
			{
				// 로그인 체크
				if(!MainF.IsLogin)
				{
					JOptionPane.showMessageDialog(null, "로그인을 해 주세요.");
				}
				else
				{
					// 판매 희망가 유효성 체크
					if(textPrice.getText().equals(""))
					{
						JOptionPane.showMessageDialog(null, "판매 희망가를 적어주세요.");
					}
					else if(!Pattern.matches("^[0-9]*$", textPrice.getText()))
					{
						JOptionPane.showMessageDialog(null, "판매 희망가가 올바르지 않습니다.");
					}
					else
					{
						// 판매 입찰에서 즉시 판매가보다 낮을 시 즉시 판매가로 판매
						if(Integer.parseInt(textPrice.getText()) < Integer.parseInt(LabelSell_i.getText()) && !(LabelSell_i.getText().equals("0")))
						{
							int temp = JOptionPane.showConfirmDialog(null, "즉시 판매가보다 낮은 가격을 입찰하려고 합니다. 즉시 판매가로  판매 하시겠습니까?","확인",JOptionPane.YES_NO_OPTION);
							if(temp == JOptionPane.YES_OPTION)
							{
								total = Integer.parseInt(LabelSell_i.getText());
								Trade.setSell_t(total, MainF.U_temp.getID(),t_temp);
								JOptionPane.showMessageDialog(null, "거래가 성사되었습니다.");
								this.dispose();
							}
						}
						else
						{
							// 판매 입찰 
							if(select)
							{
								Trade.setSell(Integer.parseInt(textPrice.getText()),MainF.U_temp.getID(),s_temp,m_temp,MainF.U_temp.getAddress());
								JOptionPane.showMessageDialog(null, "입찰이 완료되었습니다.");
								this.dispose();
							}
							// 즉시 판매 처리 부분
							if(!select)
							{
								int temp = JOptionPane.showConfirmDialog(null, "즉시 판매하시겠습니까?","확인",JOptionPane.YES_NO_OPTION);
								if(temp == JOptionPane.YES_OPTION)
								{
									total = Integer.parseInt(LabelSell_i.getText());
									Trade.setSell_t(total, MainF.U_temp.getID(),t_temp);
									JOptionPane.showMessageDialog(null, "거래가 성사되었습니다.");
									this.dispose();
								}
							}
						}
					}
				}
			}
	}
}

