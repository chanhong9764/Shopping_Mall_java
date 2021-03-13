package sch;
import javax.swing.*;
import java.awt.event.*;
import java.util.Map;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

class trades {
	int price;
	int U_IDX;
	int PI_IDX;
}

// 거래창
public class TradeF extends JFrame implements ActionListener, WindowListener {
	private JLabel LabelSell_t;
	private JLabel LabelPurchase_t;
	private JLabel LabelBrand_t;
	private JLabel LabelModel_t;
	private JLabel LabelImage;
	private JComboBox comboBox;
	private JButton ButtonPurchase;
	private JButton ButtonSell;
	private String combo_s;
	private productBean product;
	private ImageIcon product1;
	private trades temp_p;
	private trades temp_s;
	private JCheckBox CheckBasket;
	
	public TradeF(productBean temp, Map<String, ImageIcon> map) 
	{
		setIconImage(MainF.Icon_t);
		product = temp;
		Map<String, ImageIcon> map_t = map;
		setSize(600,800);
		setTitle("거래창");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.addWindowListener(this);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setLayout(null);
		getContentPane().add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(BorderFactory.createLineBorder(Color.gray));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(0, 0, 596, 490);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		product1 = map.get(temp.getModel());
		Image original = product1.getImage();
		Image change =original.getScaledInstance(400, 300, Image.SCALE_SMOOTH);
		product1 = new ImageIcon(change);	
		
		LabelImage = new JLabel(product1);
		LabelImage.setHorizontalAlignment(SwingConstants.CENTER);
		LabelImage.setBounds(12, 10, 572, 480);
		panel_1.add(LabelImage);
		
		JLabel LabelModel = new JLabel("모델명:");
		LabelModel.setFont(new Font("a뉴굴림3", Font.PLAIN, 17));
		LabelModel.setBounds(10, 550, 65, 50);
		panel.add(LabelModel);
		
		JLabel LabelBrand = new JLabel("브랜드:");
		LabelBrand.setFont(new Font("a뉴굴림3", Font.PLAIN, 17));
		LabelBrand.setBounds(10, 600, 65, 50);
		panel.add(LabelBrand);
		
		JLabel LabelPurchase = new JLabel("즉시 구매가");
		LabelPurchase.setHorizontalAlignment(SwingConstants.CENTER);
		LabelPurchase.setFont(new Font("a뉴굴림3", Font.PLAIN, 15));
		LabelPurchase.setBounds(56, 651, 146, 35);
		panel.add(LabelPurchase);
		
		JLabel LabelSell = new JLabel("즉시 판매가");
		LabelSell.setFont(new Font("a뉴굴림3", Font.PLAIN, 15));
		LabelSell.setHorizontalAlignment(SwingConstants.CENTER);
		LabelSell.setBounds(347, 651, 146, 35);
		panel.add(LabelSell);
		
		comboBox = new JComboBox(new DefaultComboBoxModel(new String[] {"ALL-SIZE","230","235","240","245","250","255","260","265","270","275","280","285","290"}));
		comboBox.setBackground(Color.WHITE);
		comboBox.setFont(new Font("a뉴굴림1", Font.PLAIN, 15));
		comboBox.setBounds(68, 509, 117, 34);
		panel.add(comboBox);
		
		JLabel LabelSize = new JLabel("사이즈");
		LabelSize.setFont(new Font("a뉴굴림3", Font.PLAIN, 17));
		LabelSize.setBounds(10, 500, 65, 50);
		panel.add(LabelSize);
		
		ButtonPurchase = new JButton("구매");
		ButtonPurchase.setBackground(Color.WHITE);
		ButtonPurchase.setFont(new Font("a뉴굴림3", Font.PLAIN, 12));
		ButtonPurchase.setBounds(80, 703, 95, 45);
		panel.add(ButtonPurchase);
		
		ButtonSell = new JButton("판매");
		ButtonSell.setFont(new Font("a뉴굴림3", Font.PLAIN, 12));
		ButtonSell.setBackground(Color.WHITE);
		ButtonSell.setBounds(373, 703, 95, 45);
		panel.add(ButtonSell);
		
		LabelPurchase_t = new JLabel(Integer.toString(Trade.GetSell_TP(temp.getImg()))+"원");
		LabelPurchase_t.setFont(new Font("a뉴굴림3", Font.PLAIN, 12));
		LabelPurchase_t.setHorizontalAlignment(SwingConstants.CENTER);
		LabelPurchase_t.setBounds(68, 678, 117, 15);
		panel.add(LabelPurchase_t);
		
		LabelSell_t = new JLabel(Integer.toString(Trade.GetPurchase_TP(temp.getImg()))+"원");
		LabelSell_t.setFont(new Font("a뉴굴림3", Font.PLAIN, 12));
		LabelSell_t.setHorizontalAlignment(SwingConstants.CENTER);
		LabelSell_t.setBounds(347, 678, 146, 15);
		panel.add(LabelSell_t);
		
		LabelModel_t = new JLabel(temp.getModel());
		LabelModel_t.setFont(new Font("a뉴굴림3", Font.PLAIN, 12));
		LabelModel_t.setBounds(73, 555, 472, 39);
		panel.add(LabelModel_t);
		
		LabelBrand_t = new JLabel(temp.getBrand());
		LabelBrand_t.setFont(new Font("a뉴굴림3", Font.PLAIN, 12));
		LabelBrand_t.setBounds(73, 607, 280, 34);
		panel.add(LabelBrand_t);
		
		CheckBasket = new JCheckBox("\uCC1C\uD558\uAE30");
		CheckBasket.setBackground(Color.WHITE);
		CheckBasket.setBounds(494, 713, 78, 35);
		panel.add(CheckBasket);
		CheckBasket.setFont(new Font("a뉴굴림3", Font.PLAIN, 15));
		
		ButtonPurchase.addActionListener(this);
		ButtonSell.addActionListener(this);
		setVisible(true);
		
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				combo_s = comboBox.getSelectedItem().toString();
				// 모든 사이즈일 경우 전체 사이즈에서 가격 출력
				if(combo_s.equals("ALL-SIZE"))
				{
					LabelPurchase_t.setText(Integer.toString(Trade.GetSell_TP(temp.getImg()))+"원");
					LabelSell_t.setText(Integer.toString(Trade.GetPurchase_TP(temp.getImg()))+"원");
				}
				else
				{
					// 로그인 확인
					if(MainF.IsLogin)
					{
						if(Trade.CheckBasket(MainF.U_temp.getID(), product.getModel(), combo_s))
						{
							CheckBasket.setSelected(true);
						}
						else
						{
							CheckBasket.setSelected(false);
						}
					}
					// 사이즈와 모델로 해당 제품 정보 가져오는 부분
					temp_p = Trade.GetPurchase(combo_s, product.getModel());
					temp_s = Trade.GetSell(combo_s, product.getModel());
					LabelPurchase_t.setText(Integer.toString(temp_p.price)+"원");
					LabelSell_t.setText(Integer.toString(temp_s.price)+"원");
				}
			}
		});
		
		CheckBasket.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e)
			{
				// 로그인 체크
				if(!MainF.IsLogin)
				{
					JOptionPane.showMessageDialog(null, "로그인을 해 주세요.");
				}
				// 사이즈 체크
				else if(combo_s == null)
				{
					JOptionPane.showMessageDialog(null, "사이즈를 선택해주세요.");
				}
				else
				{
					// 찜 목록 체크
					if(Trade.CheckBasket(MainF.U_temp.getID(), product.getModel(), combo_s))
					{
						if(e.getStateChange() == 2)
						{
							Trade.DelBasket(MainF.U_temp.getID(), product.getModel(), combo_s);
						}
					}
					else
					{
						if(e.getStateChange() == ItemEvent.SELECTED)
						{
							Trade.SetBasket(MainF.U_temp.getID(), product.getModel(), combo_s);
						}
					}
				}
			}
		});
	}
	
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == ButtonPurchase)
			{
				// 거래창을 켰을 경우 null이므로 체크
				if(!(combo_s == null))
				{
					if(combo_s.equals("ALL-SIZE"))
					{
						JOptionPane.showMessageDialog(null, "사이즈를 선택해 주세요.");
					}
					else
					{
						// 로그인 체크
						if(MainF.IsLogin)
						{
							new Purchase_bidF(product, LabelPurchase_t.getText().substring(0,LabelPurchase_t.getText().length()-1), LabelSell_t.getText().substring(0,LabelSell_t.getText().length()-1), product1, combo_s, temp_p);
						}
						else
						{
							JOptionPane.showMessageDialog(null, "로그인을 해 주세요.");
						}
					}
				}
				// 사이즈 체크
				else
				{
					JOptionPane.showMessageDialog(null, "사이즈를 선택해 주세요.");
				}
			}
			else
			{
				// 사이즈 체크
				if(!(combo_s == null))
				{
					if(combo_s.equals("ALL-SIZE"))
					{
						JOptionPane.showMessageDialog(null, "사이즈를 선택해 주세요.");
					}
					else
					{
						// 로그인 체크
						if(MainF.IsLogin)
						{
							new Sell_bidF(product, LabelPurchase_t.getText().substring(0,LabelPurchase_t.getText().length()-1), LabelSell_t.getText().substring(0,LabelSell_t.getText().length()-1), product1, combo_s, temp_s);
						}
						else
						{
							JOptionPane.showMessageDialog(null, "로그인을 해 주세요.");
						}
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "사이즈를 선택해 주세요.");
				}
			}
		}

		public void windowOpened(WindowEvent e) {}
		public void windowClosing(WindowEvent e) {
			this.dispose();
			MainF.tradeF = null;
		}
		public void windowClosed(WindowEvent e) {}
		public void windowIconified(WindowEvent e) {}
		public void windowDeiconified(WindowEvent e) {}
		public void windowActivated(WindowEvent e) {}
		public void windowDeactivated(WindowEvent e) {	
		}
	}

