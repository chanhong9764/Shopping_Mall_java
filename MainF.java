package sch;
import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.BorderLayout;
import java.awt.Color;

public class MainF extends JFrame implements ActionListener {
	static boolean IsLogin=false;
	private String ID;
	private JTextField textID;
	private JTextField textSearch;
	private JPasswordField textPW;
	private JButton ButtonLogin;
	private JButton ButtonFindID;
	private JButton ButtonFindPW;
	private JButton ButtonRegister;
	private JButton ButtonSearch;
	private JButton ButtonPoint;
	private ImageIcon SCH;
	private ImageIcon userImg;
	private ImageIcon product1;
	private ImageIcon Notice;
	private ImageIcon menu_h;
	private ImageIcon menu_h2;
	private ImageIcon menu_s;
	private ImageIcon menu_s2;
	private ImageIcon menu_r;
	private ImageIcon menu_r2;
	private ImageIcon menu_u;
	private ImageIcon menu_u2;
	private JLabel LabelImage;
	private JPanel panel_1;
	private JPanel panel_3;
	private JPanel panel_4;
	private JPanel panel_5;
	private JPanel panel_6;
	private JPanel panel_7;
	private JPanel panel_8;
	private JPanel panel_9;
	private JPanel panel_10;
	private JPanel panel_13;
	private JPanel panel_14;
	private JPanel panel_2;
	private JLabel LabelName;
	private JLabel LabelEmail;
	private JLabel LabelGradeU;
	private JLabel LabelPointU;
	private JLabel LabelProduct1;
	private JLabel Labelimg;
	private JLabel LabelLogout;
	private JLabel LabelR_model_i;
	private JLabel LabelR_size_i;
	private JLabel LabelR_img;
	private JLabel LabelBack;
	private JLabel LabelHome;
	private JLabel LabelReview;
	private JLabel LabelSearch;
	private JLabel LabelAccount;
	private JList B_list;
	private JList S_list;
	private JList listMain;
	private JList P_list;
	private JList Sell_list;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JScrollPane scrollPaneMain;
	private JScrollPane scrollPurchase;
	private JScrollPane scrollSell;
	private JScrollPane scrollPaneReview;
	private Map<String, ImageIcon> ImageMap;
	private Map<String, ImageIcon> ImageMap2;
	private Map<String, ImageIcon> ImageMap3;
	private Map<String, ImageIcon> ImageMap4;
	private Map<String, ImageIcon> ImageMap5;
	private Map<String, ImageIcon> ImageMap6;
	private Map<String, ImageIcon> ImageMap7;
	private JComboBox comboBox;
	private String combo_s = "전체 품목";
	private Timer t;
	private int count;
	static S_HistoryF S_historyF;
	static P_HistoryF P_historyF;
	static TradeF tradeF;
	static FindIDF findIDF;
	static FindPWF findPWF;
	static RegisterF registerF;
	static ReviewF reviewF;
	static UserBean U_temp;
	private JPanel panel_16;
	private JPanel panel_17;
	private JTable table;
	private String R_UID;
	private String R_img;
	private JTextArea textArea;
	static Image Icon_t;
	
	public static void main(String[] args) {
		GetImage.ConSSH();
		new MainF();
	}
	
	public MainF() 
	{
		// 룩앤필 라이브러리 적용
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		}
		catch (Exception e) {
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}
		
		ImageIcon Icon = new ImageIcon(GetImage.download("icon.png"));
		Icon_t = Icon.getImage();
		setIconImage(Icon_t);
		setSize(600,800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Login");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setLayout(null);
		getContentPane().add(panel);
		
		panel_5 = new JPanel();
		panel_5.setBackground(Color.WHITE);
		panel_5.setBorder(BorderFactory.createLineBorder(Color.gray));
		panel_5.setBounds(-20, 0, 635, 676);
		panel.add(panel_5);
		panel_5.setLayout(null);
		
		
		textID = new JTextField();
		textID.setBounds(120, 352, 400, 45);
		panel_5.add(textID);
		textID.setColumns(10);
		
		JLabel LabelID = new JLabel("아이디");
		LabelID.setFont(new Font("a뉴굴림3", Font.PLAIN, 12));
		LabelID.setBounds(120, 327, 40, 20);
		panel_5.add(LabelID);
		
		JLabel LabelPW = new JLabel("비밀번호");
		LabelPW.setFont(new Font("a뉴굴림3", Font.PLAIN, 12));
		LabelPW.setBounds(120, 407, 48, 15);
		panel_5.add(LabelPW);
		
		ButtonLogin = new JButton("로그인");
		ButtonLogin.setBackground(Color.WHITE);
		ButtonLogin.setFont(new Font("a뉴굴림3", Font.PLAIN, 12));
		ButtonLogin.setBounds(120, 487, 400, 40);
		panel_5.add(ButtonLogin);
		
		ButtonFindID = new JButton("ID 찾기");
		ButtonFindID.setBackground(Color.WHITE);
		ButtonFindID.setFont(new Font("a뉴굴림3", Font.PLAIN, 12));
		ButtonFindID.setBounds(257, 537, 125, 25);
		panel_5.add(ButtonFindID);
		
		ButtonRegister = new JButton("회원가입");
		ButtonRegister.setBackground(Color.WHITE);
		ButtonRegister.setForeground(Color.BLACK);
		ButtonRegister.setFont(new Font("a뉴굴림3", Font.PLAIN, 12));
		ButtonRegister.setBounds(120, 537, 130, 25);
		panel_5.add(ButtonRegister);
		
		textPW = new JPasswordField();
		textPW.setBounds(120, 432, 400, 45);
		panel_5.add(textPW);
		
		panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(0, 683, 150, 90);
		panel.add(panel_1);
		panel_1.setLayout(null);
		panel_1.addMouseListener(m);
		
		menu_h2 = new ImageIcon(GetImage.download("home1.png"));
		menu_h = new ImageIcon(GetImage.download("home.png"));
		Image original_h = menu_h.getImage();
		Image change_h = original_h.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		menu_h = new ImageIcon(change_h);
		original_h = menu_h2.getImage();
		change_h = original_h.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		menu_h2 = new ImageIcon(change_h);
		LabelHome = new JLabel(menu_h);
		LabelHome.setBounds(0, 0, 150, 80);
		panel_1.add(LabelHome);
		
		panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(297, 683, 150, 90);
		panel.add(panel_3);
		panel_3.setLayout(null);
		panel_3.addMouseListener(m);
		
		menu_s2 =new ImageIcon(GetImage.download("search1.png"));
		menu_s =new ImageIcon(GetImage.download("search.png"));
		Image original_s = menu_s.getImage();
		Image change_s = original_s.getScaledInstance(70, 45, Image.SCALE_SMOOTH);
		menu_s = new ImageIcon(change_s);
		original_s = menu_s2.getImage();
		change_s = original_s.getScaledInstance(70, 45, Image.SCALE_SMOOTH);
		menu_s2 = new ImageIcon(change_s);
		LabelSearch = new JLabel(menu_s);
		LabelSearch.setHorizontalAlignment(SwingConstants.CENTER);
		LabelSearch.setBounds(0, 0, 150, 80);
		panel_3.add(LabelSearch);
		
		panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		panel_4.setBounds(446, 683, 150, 90);
		panel.add(panel_4);
		panel_4.setLayout(null);
		panel_4.addMouseListener(m);
		
		menu_u2 =new ImageIcon(GetImage.download("user1.png"));
		menu_u =new ImageIcon(GetImage.download("user.png"));
		Image original_u = menu_u.getImage();
		Image change_u = original_u.getScaledInstance(50, 45, Image.SCALE_SMOOTH);
		menu_u = new ImageIcon(change_u);
		original_u = menu_u2.getImage();
		change_u = original_u.getScaledInstance(50, 45, Image.SCALE_SMOOTH);
		menu_u2 = new ImageIcon(change_u);
		LabelAccount = new JLabel(menu_u2);
		LabelAccount.setHorizontalAlignment(SwingConstants.CENTER);
		LabelAccount.setBounds(0, 0, 150, 80);
		panel_4.add(LabelAccount);
		
		SCH = new ImageIcon(GetImage.download("sch.PNG"));
		Image original_b= SCH.getImage();
		Image change_b = original_b.getScaledInstance(390, 150, Image.SCALE_SMOOTH);
		SCH = new ImageIcon(change_b);
		LabelImage = new JLabel(SCH);
		LabelImage.setSize(400, 150);
		LabelImage.setLocation(120, 122);
		panel_5.add(LabelImage);
		
		ButtonFindPW = new JButton("PW 찾기");
		ButtonFindPW.setBackground(Color.WHITE);
		ButtonFindPW.setFont(new Font("a뉴굴림3", Font.PLAIN, 12));
		ButtonFindPW.setBounds(390, 537, 130, 25);
		panel_5.add(ButtonFindPW);
		
		panel_6 = new JPanel();
		panel_6.setBackground(Color.WHITE);
		panel_6.setBounds(0, 0, 596, 684);
		panel.add(panel_6);
		panel_6.setLayout(null);
		
		userImg = new ImageIcon(GetImage.download("user_p.JPG"));
		Image original_up = userImg.getImage();
		Image change_up = original_up.getScaledInstance(130, 120, Image.SCALE_SMOOTH);
		userImg = new ImageIcon(change_up);
		JLabel LabelUserImg = new JLabel(userImg);
		panel_6.add(LabelUserImg);
		LabelUserImg.setHorizontalAlignment(SwingConstants.CENTER);
		LabelUserImg.setBounds(250, 50, 100, 100);
		
		LabelName = new JLabel("");
		LabelName.setFont(new Font("a뉴굴림3", Font.PLAIN, 35));
		LabelName.setHorizontalAlignment(SwingConstants.CENTER);
		LabelName.setBounds(12, 160, 572, 41);
		panel_6.add(LabelName);
		
		LabelEmail = new JLabel("");
		LabelEmail.setFont(new Font("a뉴굴림3", Font.PLAIN, 25));
		LabelEmail.setHorizontalAlignment(SwingConstants.CENTER);
		LabelEmail.setBounds(12, 211, 572, 49);
		panel_6.add(LabelEmail);
		
		JLabel LabelGrade = new JLabel("회원 등급");
		LabelGrade.setFont(new Font("a뉴굴림3", Font.PLAIN, 15));
		LabelGrade.setBounds(129, 306, 72, 31);
		panel_6.add(LabelGrade);
		
		LabelGradeU = new JLabel("");
		LabelGradeU.setHorizontalAlignment(SwingConstants.CENTER);
		LabelGradeU.setFont(new Font("a뉴굴림3", Font.PLAIN, 20));
		LabelGradeU.setBounds(72, 270, 176, 31);
		panel_6.add(LabelGradeU);
		
		JLabel LabelPoint = new JLabel("포인트");
		LabelPoint.setFont(new Font("a뉴굴림3", Font.PLAIN, 15));
		LabelPoint.setBounds(410, 306, 46, 23);
		panel_6.add(LabelPoint);
		
		LabelPointU = new JLabel("");
		LabelPointU.setHorizontalAlignment(SwingConstants.CENTER);
		LabelPointU.setFont(new Font("a뉴굴림3", Font.PLAIN, 20));
		LabelPointU.setBounds(349, 270, 169, 31);
		panel_6.add(LabelPointU);
		
		panel_7 = new JPanel();
		panel_7.setBackground(Color.WHITE);
		panel_7.setBorder(BorderFactory.createLineBorder(Color.gray));
		panel_7.setBounds(0, 354, 202, 66);
		panel_6.add(panel_7);
		panel_7.setLayout(null);
		panel_7.addMouseListener(m);
		
		JLabel LabelPurchase = new JLabel("구매 내역");
		LabelPurchase.setFont(new Font("a뉴굴림3", Font.PLAIN, 15));
		LabelPurchase.setBounds(70, 20, 71, 31);
		panel_7.add(LabelPurchase);
		
		panel_8 = new JPanel();
		panel_8.setBorder(BorderFactory.createLineBorder(Color.gray));
		panel_8.setBackground(Color.WHITE);
		panel_8.setBounds(200, 354, 200, 66);
		panel_6.add(panel_8);
		panel_8.setLayout(null);
		panel_8.addMouseListener(m);
		
		JLabel LabelSell = new JLabel("판매 내역");
		LabelSell.setFont(new Font("a뉴굴림3", Font.PLAIN, 15));
		LabelSell.setBounds(70, 20, 71, 31);
		panel_8.add(LabelSell);
		
		panel_9 = new JPanel();
		panel_9.setBorder(BorderFactory.createLineBorder(Color.gray));
		panel_9.setBackground(Color.WHITE);
		panel_9.setBounds(395, 354, 200, 66);
		panel_6.add(panel_9);
		panel_9.setLayout(null);
		panel_9.addMouseListener(m);
		
		JLabel Labelbasket = new JLabel("찜 상품");
		Labelbasket.setFont(new Font("a뉴굴림3", Font.PLAIN, 15));
		Labelbasket.setBounds(70, 20, 71, 31);
		panel_9.add(Labelbasket);
		
		JPanel panel_12 = new JPanel();
		panel_12.setBackground(Color.WHITE);
		panel_12.setBounds(0, 416, 596, 271);
		panel_6.add(panel_12);
		panel_12.setLayout(null);
		
		B_list = new JList();
		B_list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		B_list.setBounds(0, 1, 596, 263);
		B_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		B_list.setVisibleRowCount(1);
		scrollPane = new JScrollPane(B_list);
		scrollPane.setBounds(-2, 1, 611, 254);
		scrollPane.setPreferredSize(new Dimension(590, 263));
		panel_12.add(scrollPane);
		
		panel_10 = new JPanel();
		panel_10.setBackground(Color.WHITE);
		panel_10.setBounds(0, 0, 596, 684);
		panel.add(panel_10);
		panel_10.setLayout(null);
		
		
		textSearch = new JTextField();
		textSearch.setBounds(27, 10, 437, 51);
		panel_10.add(textSearch);
		textSearch.setColumns(10);
		
		ButtonSearch = new JButton("검색");
		ButtonSearch.setFont(new Font("a뉴굴림3", Font.PLAIN, 12));
		ButtonSearch.setBackground(Color.WHITE);
		ButtonSearch.setBounds(476, 10, 95, 51);
		panel_10.add(ButtonSearch);
		
		JPanel panel_11 = new JPanel();
		panel_11.setBackground(Color.WHITE);
		panel_11.setBounds(27, 62, 544, 622);
		panel_10.add(panel_11);
		panel_11.setLayout(null);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 62, 544, 560);
		panel_11.add(scrollPane_1);
		
		S_list = new JList();
		S_list.setFont(new Font("a뉴굴림3", Font.PLAIN, 12));
		S_list.setBackground(Color.WHITE);
		scrollPane_1.setViewportView(S_list);
		
		comboBox = new JComboBox(new DefaultComboBoxModel(new String[] {"전체 품목","즉시 구매가순","즉시 판매가순"}));
		comboBox.setFont(new Font("a뉴굴림2", Font.PLAIN, 12));
		comboBox.setBackground(Color.WHITE);
		comboBox.setBounds(425, 10, 119, 23);
		panel_11.add(comboBox);
		
		JLabel LabelProductI = new JLabel("상품 이미지");
		LabelProductI.setFont(new Font("a뉴굴림3", Font.PLAIN, 12));
		LabelProductI.setBounds(50, 39, 73, 23);
		panel_11.add(LabelProductI);
		
		JLabel LabelProductN = new JLabel("모델명");
		LabelProductN.setFont(new Font("a뉴굴림3", Font.PLAIN, 12));
		LabelProductN.setBounds(254, 43, 52, 15);
		panel_11.add(LabelProductN);
		
		JLabel LabelProductP = new JLabel("가격");
		LabelProductP.setFont(new Font("a뉴굴림3", Font.PLAIN, 12));
		LabelProductP.setBounds(480, 43, 52, 15);
		panel_11.add(LabelProductP);
		
		panel_13 = new JPanel();
		panel_13.setBackground(Color.WHITE);
		panel_13.setBounds(0, 0, 596, 684);
		panel.add(panel_13);
		panel_13.setLayout(null);
		
		panel_14 = new JPanel();
		panel_14.setBackground(Color.WHITE);
		panel_14.setBounds(12, 10, 565, 370);
		panel_13.add(panel_14);
		panel_14.setLayout(null);
		
		Labelimg = new JLabel("\uACF5\uC9C0");
		Labelimg.setHorizontalAlignment(SwingConstants.CENTER);
		Labelimg.setBounds(0, 0, 565, 370);
		panel_14.add(Labelimg);
		
		JPanel panel_15 = new JPanel();
		panel_15.setBackground(Color.WHITE);
		panel_15.setBounds(0, 383, 596, 301);
		panel_13.add(panel_15);
		panel_15.setLayout(null);
		
		JLabel Labelinfo = new JLabel("\uBC1C\uB9E4 \uC815\uBCF4");
		Labelinfo.setHorizontalAlignment(SwingConstants.CENTER);
		Labelinfo.setBounds(12, 10, 76, 29);
		panel_15.add(Labelinfo);
		Labelinfo.setFont(new Font("a뉴굴림3", Font.PLAIN, 17));
		
		listMain = new JList();
		listMain.setFont(new Font("a뉴굴림3", Font.PLAIN, 12));
		listMain.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		listMain.setBounds(0, 0, 565, 266);
		listMain.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);		
		listMain.setVisibleRowCount(1);
		scrollPaneMain = new JScrollPane(listMain);
		scrollPaneMain.setBounds(12, 39, 565, 252);
		scrollPaneMain.setPreferredSize(new Dimension(565, 252));
		panel_15.add(scrollPaneMain);
		
		P_list = new JList();
		P_list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		P_list.setBounds(0, 1, 596, 263);
		P_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		P_list.setVisibleRowCount(1);
		scrollPurchase = new JScrollPane(P_list);
		scrollPurchase.setBounds(-2, 1, 590, 254);
		scrollPane.setPreferredSize(new Dimension(590, 263));
		panel_12.add(scrollPurchase);
		
		Sell_list = new JList();
		Sell_list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		Sell_list.setBounds(0, 1, 596, 263);
		Sell_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		Sell_list.setVisibleRowCount(1);
		scrollSell = new JScrollPane(Sell_list);
		scrollSell.setBounds(-2, 1, 590, 254);
		scrollPane.setPreferredSize(new Dimension(590, 263));
		panel_12.add(scrollSell);
		
		LabelLogout = new JLabel("\uB85C\uADF8\uC544\uC6C3");
		LabelLogout.setFont(new Font("a뉴굴림3", Font.PLAIN, 12));
		LabelLogout.setHorizontalAlignment(SwingConstants.CENTER);
		LabelLogout.setBounds(497, 10, 87, 22);
		panel_6.add(LabelLogout);
		
		panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(150, 683, 150, 90);
		panel.add(panel_2);
		panel_2.setLayout(null);
		panel_2.addMouseListener(m);
		
		menu_r2 =new ImageIcon(GetImage.download("review1.png"));
		menu_r =new ImageIcon(GetImage.download("review.png"));
		Image original_R = menu_r.getImage();
		Image change_R = original_R.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		menu_r = new ImageIcon(change_R);
		original_R = menu_r2.getImage();
		change_R = original_R.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		menu_r2 = new ImageIcon(change_R);
		LabelReview = new JLabel(menu_r);
		LabelReview.setBounds(0, 0, 150, 80);
		panel_2.add(LabelReview);
		
		panel_16 = new JPanel();
		panel_16.setBackground(Color.WHITE);
		panel_16.setBounds(0, 0, 596, 684);
		panel.add(panel_16);
		panel_16.setLayout(null);

		table = new JTable();
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		table.setTableHeader(null);
		table.setFont(new Font("a뉴굴림3", Font.PLAIN, 12));
		table.setBackground(Color.WHITE);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPaneReview = new JScrollPane(table);
		scrollPaneReview.setBounds(0, 0, 625, 674);
		scrollPaneReview.setPreferredSize(new Dimension(590, 263));
		panel_16.add(scrollPaneReview);
		
		panel_17 = new JPanel();
		panel_17.setBackground(Color.WHITE);
		panel_17.setBounds(0, 0, 596, 684);
		panel.add(panel_17);
		panel_17.setLayout(null);
		
		LabelBack = new JLabel("\uB4A4\uB85C\uAC00\uAE30");
		LabelBack.setFont(new Font("a뉴굴림3", Font.PLAIN, 12));
		LabelBack.setBounds(12, 10, 52, 15);
		panel_17.add(LabelBack);
		
		JPanel panel_18 = new JPanel();
		panel_18.setBorder(BorderFactory.createLineBorder(Color.gray));
		panel_18.setBackground(Color.WHITE);
		panel_18.setBounds(12, 35, 560, 254);
		panel_17.add(panel_18);
		panel_18.setLayout(null);
		
		ButtonPoint = new JButton("\uC801\uB9BD \uC644\uB8CC");
		ButtonPoint.setBounds(434, 193, 114, 51);
		panel_18.add(ButtonPoint);
		ButtonPoint.setBackground(Color.WHITE);
		ButtonPoint.setFont(new Font("a뉴굴림3", Font.PLAIN, 12));
		
		LabelR_size_i = new JLabel("New label");
		LabelR_size_i.setBounds(348, 89, 161, 15);
		panel_18.add(LabelR_size_i);
		LabelR_size_i.setFont(new Font("a뉴굴림3", Font.PLAIN, 12));
		
		LabelR_img = new JLabel("");
		LabelR_img.setBounds(12, 0, 323, 254);
		panel_18.add(LabelR_img);
		LabelR_img.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel LabelR_size = new JLabel("\uC0AC\uC774\uC988");
		LabelR_size.setBounds(348, 64, 52, 15);
		panel_18.add(LabelR_size);
		LabelR_size.setFont(new Font("a뉴굴림3", Font.PLAIN, 12));
		
		LabelR_model_i = new JLabel("New label");
		LabelR_model_i.setBounds(348, 39, 196, 15);
		panel_18.add(LabelR_model_i);
		LabelR_model_i.setFont(new Font("a뉴굴림3", Font.PLAIN, 12));
		
		JLabel LabelR_model = new JLabel("\uBAA8\uB378\uBA85");
		LabelR_model.setBounds(348, 14, 52, 15);
		panel_18.add(LabelR_model);
		LabelR_model.setFont(new Font("a뉴굴림3", Font.PLAIN, 12));
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBackground(Color.WHITE);
		textArea.setBounds(12, 299, 560, 375);
		panel_17.add(textArea);
		ButtonPoint.setVisible(false);
		ButtonPoint.addActionListener(this);
		
		ButtonLogin.addActionListener(this);
		ButtonFindID.addActionListener(this);
		ButtonRegister.addActionListener(this);
		ButtonFindPW.addActionListener(this);
		ButtonSearch.addActionListener(this);
		setVisible(true);
		
		panel_6.setVisible(false);
		panel_10.setVisible(false);
		panel_13.setVisible(false);
		panel_16.setVisible(false);
		panel_17.setVisible(false);
		GetImage.disconnection();
		// 검색 종류 클릭 부분
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				combo_s = comboBox.getSelectedItem().toString();
			}
		});
		// 로그아웃 라벨 클릭 부분
		LabelLogout.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt)
			{
				if(evt.getClickCount() == 1)
				{
					IsLogin = false;
					textID.setText("");
					textPW.setText("");
					scrollPurchase.setVisible(false);
					scrollSell.setVisible(false);
					scrollPane.setVisible(false);
					panel_6.setVisible(false);
					panel_5.setVisible(true);
				}
			}
		});
		// 발매 정보에서 제품 클릭 시 거래창 생성
		listMain.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				if (evt.getClickCount() == 2) {
					if(tradeF == null)
					{
						tradeF = new TradeF((productBean)listMain.getSelectedValue(),ImageMap3);
					}
				}
			}
		});
		// 검색에서 제품 클릭 시 거래창 생성
		S_list.addMouseListener(new MouseAdapter( ) {
			public void mouseClicked(MouseEvent evt) {
				if (evt.getClickCount() == 2) {
					if(tradeF == null)
					{
						tradeF = new TradeF((productBean)S_list.getSelectedValue(),ImageMap2);
					}
				}
			}
		});
		// 찜 상품에서 제품 클릭 시 거래창 생성
		B_list.addMouseListener(new MouseAdapter( ) {
			public void mouseClicked(MouseEvent evt) {
				if (evt.getClickCount() == 2) {
					if(tradeF == null)
					{
						tradeF = new TradeF((productBean)B_list.getSelectedValue(),ImageMap);
					}
				}
			}
		});
		// 구매 내역에서 제품 클릭 시 거래창 생성
		P_list.addMouseListener(new MouseAdapter( ) {
			public void mouseClicked(MouseEvent evt) {
				if (evt.getClickCount() == 2) {
					if(P_historyF == null)
					{
						P_historyF = new P_HistoryF((detail)P_list.getSelectedValue(),ImageMap5);
					}
				}
			}
		});
		// 판매 내역에서 제품 클릭 시 거래창 생성
		Sell_list.addMouseListener(new MouseAdapter( ) {
			public void mouseClicked(MouseEvent evt) {
				if (evt.getClickCount() == 2) {
					if(S_historyF == null)
					{
						S_historyF = new S_HistoryF((detail)Sell_list.getSelectedValue(),ImageMap6);
					}
				}
			}
		});
		// 리뷰 내역 제품 클릭 시 리뷰 창 생성
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				if(evt.getClickCount() == 2)
				{
					panel_16.setVisible(false);
					panel_17.setVisible(true);
					int row = table.getSelectedRow();
					int col = table.getSelectedColumn();
					reviewBean temp = (reviewBean) table.getModel().getValueAt(row, col);
					LabelR_model_i.setText(temp.getModel());
					LabelR_size_i.setText(Integer.toString(temp.getSize()));
					LabelR_img.setIcon(ImageMap7.get(temp.getImg())); 
					textArea.setLineWrap(true);
					textArea.setText(temp.getContent());
					R_img = temp.getImg();
					R_UID=temp.getUid();
					if(IsLogin)
					{
						if(U_temp.getGrade().equals("관리자"))
						{
							ButtonPoint.setVisible(true);
						}
					}
				}
			}
		});
		// 리뷰 뒤로가기
		LabelBack.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				if(evt.getClickCount() == 1)
				{
					panel_17.setVisible(false);
					panel_16.setVisible(true);
				}
			}
		});
}
	// 마우스 라벨 클릭 시 이벤트 발생
	MouseListener m = new MouseAdapter() {
		public void mouseClicked(MouseEvent e)
		{
			JPanel temp = (JPanel)e.getSource();
			// 홈 화면 
			if(temp == panel_1)
			{
				LabelHome.setIcon(menu_h2);
				LabelReview.setIcon(menu_r);
				LabelSearch.setIcon(menu_s);
				LabelAccount.setIcon(menu_u);
				// 타이머 이용하여 계속해서 공지 변경
				if(!(t==null)){ t.stop();}
				setTitle("Home");
				panel_5.setVisible(false);
				panel_6.setVisible(false);
				panel_10.setVisible(false);
				panel_16.setVisible(false);
				panel_17.setVisible(false);
				panel_13.setVisible(true);
				t = new Timer(3000, new action());
				count=0;
				t.start();
				// 공지 이미지 및 리스트를 서버로부터 얻어오는 부분
				DefaultListModel<String> list_n = new DefaultListModel<>();
				list_n=getProduct.GetNotice();
				ImageMap4 = createImageMap4(list_n);
				Labelimg.setIcon(ImageMap4.get("notice1"));
				// 발매 상품 리스트를 서버로부터 얻어오는 부분
				DefaultListModel<productBean> list = new DefaultListModel<>();
				list=getProduct.Release_info();
				ImageMap3 = createImageMap3(list);
				listMain.setModel(list);
				listMain.setCellRenderer(new ReleaseListRenderer());
				listMain.setLayoutOrientation(JList.HORIZONTAL_WRAP);
				scrollPane.setVisible(true);
			}
			// 검색 탭으로 화면 출력
			else if(temp == panel_3)
			{
				LabelHome.setIcon(menu_h);
				LabelReview.setIcon(menu_r);
				LabelSearch.setIcon(menu_s2);
				LabelAccount.setIcon(menu_u);
				if(!(t==null)){ t.stop();}
				setTitle("Search");
				panel_5.setVisible(false);
				panel_6.setVisible(false);
				panel_16.setVisible(false);
				panel_13.setVisible(false);
				panel_17.setVisible(false);
				panel_10.setVisible(true);
			}
			// 로그인 탭으로 화면 출력
			else if(temp == panel_4)
			{
				LabelHome.setIcon(menu_h);
				LabelReview.setIcon(menu_r);
				LabelSearch.setIcon(menu_s);
				LabelAccount.setIcon(menu_u2);
				if(!(t==null)){ t.stop();}
				setTitle("Login");
				panel_10.setVisible(false);
				panel_13.setVisible(false);
				panel_16.setVisible(false);
				panel_17.setVisible(false);
				// 로그인이 안되있을 시 로그인 화면 출력
				if(IsLogin)
				{
					panel_5.setVisible(false);
					panel_6.setVisible(true);
				}
				else
				{
					panel_6.setVisible(false);
					panel_5.setVisible(true);
				}
			}
			// 구매 내역을 서버로부터 받아오는 부분
			else if(temp == panel_7)
			{
				DefaultListModel<detail> list = new DefaultListModel<>();
				// 등급이 관리자일 경우 모든 유저의 구매 내역을 받음
				if(U_temp.getGrade().equals("관리자"))
				{
					list=getProduct.GetTrade_list_p();
				}
				else
				{
					list=getProduct.GetPurchase_list(ID);
				}
				ImageMap5 = createImageMap5(list);
				P_list.setModel(list);
				P_list.setCellRenderer(new PurchaseListRenderer());
				scrollSell.setVisible(false);
				scrollPane.setVisible(false);
				scrollPurchase.setVisible(true);
			}
			// 판매 내역을 서버로부터 받아오는 부분
			else if (temp == panel_8)
			{
				DefaultListModel<detail> list = new DefaultListModel<>();
				// 등급이 관리자일 경우 모든 유저의 판매 내역을 받음
				if(U_temp.getGrade().equals("관리자"))
				{
					list=getProduct.GetTrade_list_s();
				}
				else
				{
					list=getProduct.GetSell_list(ID);
				}
				ImageMap6 = createImageMap6(list);
				Sell_list.setModel(list);
				Sell_list.setCellRenderer(new SellListRenderer());
				scrollPane.setVisible(false);
				scrollPurchase.setVisible(false);
				scrollSell.setVisible(true);
			}
			// 리뷰 탭
			else if(temp == panel_2)
			{
				LabelHome.setIcon(menu_h);
				LabelReview.setIcon(menu_r2);
				LabelSearch.setIcon(menu_s);
				LabelAccount.setIcon(menu_u);
				int size_t = 0;
				if(!(t==null)){ t.stop();}
				setTitle("Review");
				DefaultListModel<reviewBean> list = new DefaultListModel<>();
				list=getProduct.GetReview();
				ImageMap7 = createImageMap7(list);
				String col[] = {"review", "review"};
				if(list.size() % 2 == 0)
				{
					size_t = list.size() / 2;
				}
				else
				{
					size_t = (list.size() / 2) + 1;
				}
				reviewBean[][] rows = new reviewBean[size_t][2];
				for(int i=0; i < list.size(); i++)
				{
					if(i%2 == 0)
					{
						rows[i/2][0] = list.get(i);
					}
					else
					{
						rows[i/2][1] = list.get(i);
					}
				}
				reviewBean temp_r = new reviewBean();
				temp_r.setUid("");
				temp_r.setImg("error.png");
				if(list.size() % 2 == 1) { rows[size_t - 1][1] = temp_r; }
				DefaultTableModel model = new DefaultTableModel(rows,col) {
					public boolean isCellEditable(int row, int column){
						return false;
					}
				};
				table.setModel(model);
				table.getColumnModel().getColumn(0).setCellRenderer(new LabelRendar());
				table.getColumnModel().getColumn(1).setCellRenderer(new LabelRendar());
				updateRowHeights();
				scrollPaneReview.setVisible(true);
				panel_5.setVisible(false);
				panel_6.setVisible(false);
				panel_13.setVisible(false);
				panel_10.setVisible(false);
				panel_17.setVisible(false);
				panel_16.setVisible(true);
			}
			// 찜 상품을 서버로부터 받아오는 부분 
			else 
			{
				DefaultListModel<productBean> list = new DefaultListModel<>();
				list=getProduct.GetBasket(ID);
				ImageMap = createImageMap(list);
				B_list.setModel(list);
				B_list.setCellRenderer(new BasketListRenderer());
				scrollPurchase.setVisible(false);
				scrollSell.setVisible(false);
				scrollPane.setVisible(true);
			}
		}
	};
	// 공지 타이머 이벤트 처리 부분
	class action implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			count++; // 공지 선택 카운터
			if(count == 1)
			{
				Labelimg.setIcon(ImageMap4.get("notice1"));
			}
			else if(count == 2)
			{
				Labelimg.setIcon(ImageMap4.get("notice2"));
			}
			else if(count == 3)
			{
				Labelimg.setIcon(ImageMap4.get("notice3"));
			}
			else if(count == 4)
			{
				Labelimg.setIcon(ImageMap4.get("notice4"));
				count = 0;
			}
		}
	}
	public void actionPerformed(ActionEvent e) {
		ID = textID.getText();
		// 로그인 처리 부분
		if (e.getSource() == ButtonLogin) {
			char []PW_t = textPW.getPassword();
			String PW = new String(PW_t);
			PW = sha256.SHA256(PW);
			// ID, PW 유효성 검사 부분
			if(ID.equals(""))
			{
				JOptionPane.showMessageDialog(null, "아이디를 입력해 주세요.");
			}
			else if(PW.equals(""))
			{
				JOptionPane.showMessageDialog(null, "비밀번호를 입력해 주세요.");
			}
			else			
			{
				boolean existLogin = Login.login(ID,PW);
				if(existLogin)
				{
					IsLogin=true;
					if(IsLogin)
					{
						JOptionPane.showMessageDialog(null, "로그인 성공 !");
						U_temp = UserInfo.GetuserInfo(ID);
						LabelName.setText(U_temp.getID());
						LabelEmail.setText(U_temp.getEmail());
						LabelGradeU.setText(U_temp.getGrade());
						LabelPointU.setText(Integer.toString(U_temp.getPoint())+"P");
						panel_5.setVisible(false);
						panel_6.setVisible(true);
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "로그인 실패 !");
				}
			}
		} 
		// 검색 탭 이벤트 처리 부분
		else if (e.getSource() == ButtonSearch)
		{
			DefaultListModel<productBean> list = new DefaultListModel<>();
			// 각 검색 카테고리에 따라 다른 리스트를 서버로부터 받아오는 부분
			if (combo_s.equals("즉시 판매가순"))
			{
				list = search.GetSell(textSearch.getText());
			}
			else if(combo_s.equals("즉시 구매가순"))
			{
				list = search.GetPurchase(textSearch.getText());
			}
			else
			{
				list = search.GetTotal(textSearch.getText());
			}
			ImageMap2 = createImageMap2(list);
			S_list.setModel(list);
			S_list.setCellRenderer(new SearchListRenderer());
			scrollPane_1.setVisible(true);
		}
		// ID / PW 찾기 프레임 생성 부분
		else if (e.getSource() == ButtonFindID )
		{
			if(findIDF == null)
				findIDF = new FindIDF();
		}
		else if (e.getSource() == ButtonFindPW )
		{
			if(findPWF == null)
				findPWF = new FindPWF();
		}
		// 회원가입 프레임 생성 부분
		else if (e.getSource() == ButtonRegister )
		{
			if(registerF == null)
				registerF = new RegisterF();
		}
		// 포인트 적립
		else if(e.getSource() == ButtonPoint)
		{
			if(!History.Review_p(R_img))
			{
				ReviewF.review_p(R_UID, R_img);
				JOptionPane.showMessageDialog(null, "적립완료 !");
			}
			else
			{
				JOptionPane.showMessageDialog(null, "이미 적립이 완료되었습니다!");
			}
		}
	}
	// 리뷰 테이블 셀 크기 조정
	public void updateRowHeights()
	{
	       for (int row = 0; row < table.getRowCount(); row++)
	       {
	           int rowHeight = table.getRowHeight();
	           
	           for (int column = 0; column < table.getColumnCount(); column++)
	           {
	               Component comp = table.prepareRenderer(table.getCellRenderer(row, column), row, column);
	               rowHeight = Math.max(rowHeight, comp.getPreferredSize().height);
	           }
	           table.setRowHeight(row, rowHeight);
	       }
	}
	// 리뷰 내역 커스텀화 부분
	 class LabelRendar implements TableCellRenderer{
		    @Override
		    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
		    	reviewBean temp = (reviewBean)value;
		    	JLabel temp_l = new JLabel();
		    	temp_l.setIcon(ImageMap7.get(temp.getImg()));
		    	temp_l.setText("<html>"+temp.getUid()+"<br>"+" "+"<br>");
				temp_l.setHorizontalTextPosition(JLabel.CENTER); 
				temp_l.setVerticalTextPosition(JLabel.BOTTOM); 
		        return temp_l;
		    }

		}
	// 구매 내역 커스텀화 부분
	public class PurchaseListRenderer extends DefaultListCellRenderer{
		Font font = new Font("a뉴굴림3",Font.PLAIN, 13);
		@Override
		public Component getListCellRendererComponent(
				JList list, Object value, int index,
				boolean isSelected, boolean cellHasFocus) {
			detail temp = (detail)value;
			String temp_s;
			String status;
			if(temp.getConfirm())
				temp_s = "완료";
			else
				temp_s = "진행중";
			
			if(temp.getStatus() == 0) { status = "입금 요망"; }
			else if(temp.getStatus() == 1) { status = "배송 준비"; }
			else if(temp.getStatus() == 2) { status = "배송 출발"; }
			else if(temp.getStatus() == 3) { status = "배송 완료"; }
			else if(temp.getStatus() == 4) { status = "취소 요청"; }
			else { status = "취소 완료"; }
			
			JLabel label = (JLabel)super.getListCellRendererComponent(list, temp, index, isSelected, cellHasFocus); 
			label.setIcon(ImageMap5.get(temp.getModel())); 
			label.setText("<html>"+"모델명:"+temp.getModel()+"<br>"+"사이즈:"+temp.getSize()+"<br>"+"검수:"+temp_s+"<br>"+"진행 상태:"+status+"<br>"+"가격:"+temp.getR_Price());
			label.setHorizontalTextPosition(JLabel.CENTER); 
			label.setVerticalTextPosition(JLabel.BOTTOM); 
			label.setFont(font); 
			return label;
		}
	}
	// 판매 내역 커스텀화 부분
	public class SellListRenderer extends DefaultListCellRenderer{
		Font font = new Font("a뉴굴림3",Font.PLAIN, 10);
		@Override
		public Component getListCellRendererComponent(
				JList list, Object value, int index,
				boolean isSelected, boolean cellHasFocus) {
			detail temp = (detail)value;
			String temp_s;
			String status;
			if(temp.getConfirm())
				temp_s = "완료";
			else
				temp_s = "진행중";
			if(temp.getStatus() == 0) { status = "입금 요망"; }
			else if(temp.getStatus() == 1) { status = "배송 준비"; }
			else if(temp.getStatus() == 2) { status = "배송 출발"; }
			else if(temp.getStatus() == 3) { status = "배송 완료"; }
			else if(temp.getStatus() == 4) { status = "취소 요청"; }
			else { status = "취소 완료"; }
			
			JLabel label = (JLabel)super.getListCellRendererComponent(list, temp, index, isSelected, cellHasFocus); 
			label.setIcon(ImageMap6.get(temp.getModel())); 
			label.setText("<html>"+"모델명:"+temp.getModel()+"<br>"+"사이즈:"+temp.getSize()+"<br>"+"검수:"+temp_s+"<br>"+"진행 상태:"+status+"<br>"+"가격:"+temp.getR_Price());
			label.setHorizontalTextPosition(JLabel.CENTER); 
			label.setVerticalTextPosition(JLabel.BOTTOM); 
			label.setFont(font); 
			return label;
		}
	}
	// 찜 목록 커스텀화 부분
	public class BasketListRenderer extends DefaultListCellRenderer{
		Font font = new Font("a뉴굴림3",Font.PLAIN, 10);
		@Override
		public Component getListCellRendererComponent(
				JList list, Object value, int index,
				boolean isSelected, boolean cellHasFocus) {
			productBean temp = (productBean)value;
			JLabel label = (JLabel)super.getListCellRendererComponent(list, temp, index, isSelected, cellHasFocus);
			label.setIcon(ImageMap.get(temp.getModel())); 
			label.setText("<html>"+"모델명:"+temp.getModel()+"<br>"+"브랜드:"+temp.getBrand());
			label.setHorizontalTextPosition(JLabel.CENTER); 
			label.setVerticalTextPosition(JLabel.BOTTOM);
			label.setFont(font); 
			return label;
		}
	}
	// 발매 정보 커스텀화 부분
	public class ReleaseListRenderer extends DefaultListCellRenderer{
		Font font = new Font("a뉴굴림3",Font.PLAIN, 10);
		@Override
		public Component getListCellRendererComponent(
				JList list, Object value, int index,
				boolean isSelected, boolean cellHasFocus) {
			productBean temp = (productBean)value;
			JLabel label = (JLabel)super.getListCellRendererComponent(list, temp, index, isSelected, cellHasFocus); 
			label.setIcon(ImageMap3.get(temp.getModel())); 
			label.setText("<html>"+"모델명:"+temp.getModel()+"<br>"+"브랜드:"+temp.getBrand()+"<br>"+"발매일:"+temp.getDate()+"<br>"+"발매가:"+temp.getPrice()+"</html>");
			label.setHorizontalTextPosition(JLabel.CENTER); 
			label.setVerticalTextPosition(JLabel.BOTTOM);
			label.setFont(font); 
			return label;
		}
	}
	// 검색 리스트 커스텀화 부분
	public class SearchListRenderer extends DefaultListCellRenderer{
		private JPanel panel = new JPanel(new BorderLayout());
		private JLabel RightL = new JLabel();
		
		public SearchListRenderer()
		{
			panel.add(RightL,BorderLayout.EAST);
		}
		Font font = new Font("a뉴굴림3",Font.PLAIN, 15);
		@Override
		public Component getListCellRendererComponent(
				JList list, Object value, int index,
				boolean isSelected, boolean cellHasFocus) {
			productBean temp = (productBean)value;
			JLabel label = (JLabel) super.getListCellRendererComponent(list, temp.getModel(), index, isSelected, cellHasFocus);
			if(isSelected) {
				label.setBackground(Color.white);
			}
			label.setIcon(ImageMap2.get(temp.getModel()));
			RightL.setText(Integer.toString(temp.getPrice())+"  ");
			RightL.setFont(font);
			RightL.setOpaque(true);
			RightL.setBackground(Color.WHITE);
			panel.add(label,BorderLayout.CENTER);
			return panel;
		}
	}
	// 찜 상품 이미지를 서버로부터 다운 받아서 변수에 넣는 부분
	private Map<String, ImageIcon> createImageMap(DefaultListModel<productBean> list)
	{
		Map<String, ImageIcon> map = new HashMap<>();
		GetImage.ConSSH();
		try {
			for(int i=0; i<list.getSize(); i++)
			{
				ImageIcon temp = new ImageIcon(GetImage.download(list.get(i).getImg()));
				Image original = temp.getImage();
				Image change = original.getScaledInstance(300, 200, Image.SCALE_SMOOTH);
				temp = new ImageIcon(change);
				map.put(list.get(i).getModel(),temp);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		GetImage.disconnection();
		return map;
	}
	// 공지 이미지를 서버로부터 다운 받아서 변수에 넣는 부분
	private Map<String, ImageIcon> createImageMap4(DefaultListModel<String> list)
	{
		Map<String, ImageIcon> map = new HashMap<>();
		GetImage.ConSSH();
		try {
			for(int i=0; i<list.getSize(); i++)
			{
				ImageIcon temp = new ImageIcon(GetImage.download(list.get(i)));
				Image original = temp.getImage();
				Image change = original.getScaledInstance(565, 365, Image.SCALE_SMOOTH);
				temp = new ImageIcon(change);
				map.put(list.get(i).substring(0,list.get(i).length()-4),temp);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		GetImage.disconnection();
		return map;
	}
	// 발매 정보 이미지를 서버로부터 다운 받아서 변수에 넣는 부분
	private Map<String, ImageIcon> createImageMap3(DefaultListModel<productBean> list)
	{
		Map<String, ImageIcon> map = new HashMap<>();
		GetImage.ConSSH();
		try {
			for(int i=0; i<list.getSize(); i++)
			{
				ImageIcon temp = new ImageIcon(GetImage.download(list.get(i).getImg()));
				Image original = temp.getImage();
				Image change = original.getScaledInstance(300, 170, Image.SCALE_SMOOTH);
				temp = new ImageIcon(change);
				map.put(list.get(i).getImg().substring(0,list.get(i).getImg().length()-4),temp);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		GetImage.disconnection();
		return map;
	}
	// 검색 리스트 이미지를 서버로부터 다운 받아서 변수에 넣는 부분
	private Map<String, ImageIcon> createImageMap2(DefaultListModel<productBean> list)
	{
		Map<String, ImageIcon> map = new HashMap<>();
		GetImage.ConSSH();
		try {
			for(int i=0; i<list.getSize(); i++)
			{
				ImageIcon temp = new ImageIcon(GetImage.download(list.get(i).getImg()));
				Image original = temp.getImage();
				Image change = original.getScaledInstance(150, 100, Image.SCALE_SMOOTH);
				temp = new ImageIcon(change);
				map.put(list.get(i).getImg().substring(0,list.get(i).getImg().length()-4),temp);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		GetImage.disconnection();
		return map;
	}
	// 구매 내역 이미지를 서버로부터 다운 받아서 변수에 넣는 부분
	private Map<String, ImageIcon> createImageMap5(DefaultListModel<detail> list)
	{
		Map<String, ImageIcon> map = new HashMap<>();
		GetImage.ConSSH();
		try {
			for(int i=0; i<list.getSize(); i++)
			{
				ImageIcon temp = new ImageIcon(GetImage.download(list.get(i).getImg()));
				Image original = temp.getImage();
				Image change = original.getScaledInstance(240, 140, Image.SCALE_SMOOTH);
				temp = new ImageIcon(change);
				map.put(list.get(i).getModel(),temp);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		GetImage.disconnection();
		return map;
	}
	// 판매 내역 이미지를 서버로부터 다운 받아서 변수에 넣는 부분
	private Map<String, ImageIcon> createImageMap6(DefaultListModel<detail> list)
	{
		Map<String, ImageIcon> map = new HashMap<>();
		GetImage.ConSSH();
		try {
			for(int i=0; i<list.getSize(); i++)
			{
				ImageIcon temp = new ImageIcon(GetImage.download(list.get(i).getImg()));
				Image original = temp.getImage();
				Image change = original.getScaledInstance(250, 150, Image.SCALE_SMOOTH);
				temp = new ImageIcon(change);
				map.put(list.get(i).getModel(),temp);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		GetImage.disconnection();
		return map;
	}
	// 리뷰 내역 이미지를 서버로부터 다운 받는 부분
	private Map<String, ImageIcon> createImageMap7(DefaultListModel<reviewBean> list)
	{
		Map<String, ImageIcon> map = new HashMap<>();
		GetImage.ConSSH();
		try {
			for(int i=0; i<list.getSize(); i++)
			{
				ImageIcon temp = new ImageIcon(GetImage.download(list.get(i).getImg()));
				Image original = temp.getImage();
				Image change = original.getScaledInstance(290, 180, Image.SCALE_SMOOTH);
				temp = new ImageIcon(change);
				map.put(list.get(i).getImg(),temp);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		GetImage.disconnection();
		return map;
	}
}