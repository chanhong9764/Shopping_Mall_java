package sch;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

// 주소를 가져오는 프레임
public class AddressFindF extends JFrame implements ActionListener, WindowListener {
	private JTextField textSearch;
	private JButton ButtonSearch;
	private JPanel panel;
	private JList listA;
	private JScrollPane scrollPane;
	private JPanel panel_1;
	private JLabel LabelRoad;
	private JButton ButtonInsert;
	DefaultListModel<String> list = new DefaultListModel<>();
	private final JTextField textRoad = new JTextField();
	private JLabel LabelRoad2;
	private JTextField textRoad2;
	
	public AddressFindF() 
	{
		setIconImage(MainF.Icon_t);
		setSize(600,400);
		setTitle("주소 검색");
		this.setResizable(false);
		this.addWindowListener(this);
		this.setLocationRelativeTo(null);
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setLayout(null);
		getContentPane().add(panel);
		
		textSearch = new JTextField();
		textSearch.setFont(new Font("a뉴굴림1", Font.PLAIN, 12));
		textSearch.setBounds(50, 30, 400, 70);
		panel.add(textSearch);
		textSearch.setColumns(10);
		
		ButtonSearch = new JButton("검색");
		ButtonSearch.setFont(new Font("a뉴굴림3", Font.PLAIN, 12));
		ButtonSearch.setBackground(Color.WHITE);
		ButtonSearch.setBounds(460, 30, 80, 70);
		panel.add(ButtonSearch);
		
		panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(50, 110, 488, 253);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		listA = new JList();
		//listA.setBounds(50, 110, 490, 230);
		listA.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane = new JScrollPane(listA);
		scrollPane.setBounds(-1, 5, 490, 235);
		scrollPane.setPreferredSize(new Dimension(490, 235));
		panel_1.add(scrollPane);
		textRoad.setFont(new Font("a뉴굴림1", Font.PLAIN, 12));
		
		textRoad.setBounds(95, 20, 394, 65);
		textRoad.setColumns(10);
		textRoad.setVisible(false);
		panel_1.add(textRoad);
		
		LabelRoad = new JLabel("도로명 주소");
		LabelRoad.setFont(new Font("a뉴굴림3", Font.PLAIN, 12));
		LabelRoad.setBounds(-1, 20, 91, 65);
		panel_1.add(LabelRoad);
		LabelRoad.setVisible(false);
		
		LabelRoad2 = new JLabel("상세주소입력");
		LabelRoad2.setFont(new Font("a뉴굴림3", Font.PLAIN, 12));
		LabelRoad2.setBounds(-1, 100, 91, 65);
		panel_1.add(LabelRoad2);
		LabelRoad2.setVisible(false);
		
		textRoad2 = new JTextField();
		textRoad2.setFont(new Font("a뉴굴림1", Font.PLAIN, 12));
		textRoad2.setBounds(95, 100, 394, 65);
		panel_1.add(textRoad2);
		textRoad2.setColumns(10);
		textRoad2.setVisible(false);
		
		ButtonInsert = new JButton("주소입력");
		ButtonInsert.setBackground(Color.WHITE);
		ButtonInsert.setFont(new Font("a뉴굴림3", Font.PLAIN, 12));
		ButtonInsert.setBounds(180, 180, 125, 45);
		panel_1.add(ButtonInsert);
		ButtonInsert.setVisible(false);
	
		// 주소 검색한 결과 리스트를 선택 처리 부분
		listA.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if(!e.getValueIsAdjusting())
				{
					String temp = (String)listA.getSelectedValue();
					textRoad.setText(temp);
					listA.setVisible(false);
					scrollPane.setVisible(false);
					textRoad.setVisible(true);
					LabelRoad.setVisible(true);
					textRoad2.setVisible(true);
					LabelRoad2.setVisible(true);
					ButtonInsert.setVisible(true);
				}
			}
		});
		
		ButtonSearch.addActionListener(this);
		ButtonInsert.addActionListener(this);
		setVisible(true);
	}

		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == ButtonSearch)
			{
				if(textSearch.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "주소를 입력해주세요.");
				}
				else
				{
					// 검색 부분
					list = AddressFind.addressFind(textSearch.getText());
					listA.setModel(list);
					textRoad.setVisible(false);
					LabelRoad.setVisible(false);
					textRoad2.setVisible(false);
					LabelRoad2.setVisible(false);
					ButtonInsert.setVisible(false);
					listA.setVisible(true);
					scrollPane.setVisible(true);
				}
			}
			// 회왼가입 화면에 결과 반환
			else if(e.getSource() == ButtonInsert)
			{
				String temp = new String(textRoad.getText()+","+textRoad2.getText());
				RegisterF.SetAddress(temp);
				RegisterF.addressFindF = null;
				this.dispose();
			}
			
		}
		public void windowOpened(WindowEvent e) {}
		public void windowClosing(WindowEvent e) {
			this.dispose();
			RegisterF.addressFindF = null;
		}
		public void windowClosed(WindowEvent e) {}
		public void windowIconified(WindowEvent e) {}
		public void windowDeiconified(WindowEvent e) {}
		public void windowActivated(WindowEvent e) {}
		public void windowDeactivated(WindowEvent e) {	
		}
	}
