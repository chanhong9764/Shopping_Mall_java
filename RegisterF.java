package sch;

import javax.swing.*;
import java.awt.event.*;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.regex.Pattern;
import java.awt.Window;
import java.awt.Color;

public class RegisterF extends JFrame implements ActionListener, WindowListener {
	private JTextField textID;
	private JTextField textName;
	private static JTextField textAddress;
	private JTextField textEmail;
	private JPasswordField textPW;
	private JTextField textPhone;
	private JPasswordField textRPW;
	private JButton ButtonSummit;
	static AddressFindF addressFindF;
	
	public RegisterF() 
	{
		setIconImage(MainF.Icon_t);
		setSize(600,800);
		setTitle("회원가입");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.addWindowListener(this);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setLayout(null);
		getContentPane().add(panel);
		
		textID = new JTextField();
		textID.setFont(new Font("굴림", Font.PLAIN, 20));
		textID.setBounds(150, 48, 300, 45);
		panel.add(textID);
		textID.setColumns(10);
		
		JLabel LabelID = new JLabel("아이디");
		LabelID.setFont(new Font("a뉴굴림3", Font.PLAIN, 22));
		LabelID.setBounds(150, 10, 98, 46);
		panel.add(LabelID);
		
		JLabel LabelPW = new JLabel("비밀번호");
		LabelPW.setFont(new Font("a뉴굴림3", Font.PLAIN, 22));
		LabelPW.setBounds(150, 110, 98, 35);
		panel.add(LabelPW);
		
		textName = new JTextField();
		textName.setBounds(150, 348, 300, 45);
		panel.add(textName);
		textName.setColumns(10);
		
		textAddress = new JTextField("Click");
		textAddress.setHorizontalAlignment(SwingConstants.LEFT);
		textAddress.setFont(new Font("a뉴굴림3", Font.PLAIN, 14));
		textAddress.setBounds(150, 448, 300, 45);
		panel.add(textAddress);
		textAddress.setColumns(10);
		textAddress.addMouseListener(m);
		textAddress.enable(false);
		
		textEmail = new JTextField();
		textEmail.setBounds(150, 548, 300, 45);
		panel.add(textEmail);
		textEmail.setColumns(10);
		
		textPW = new JPasswordField();
		textPW.setBounds(150, 148, 300, 45);
		panel.add(textPW);
		
		textPhone = new JTextField();
		textPhone.setBounds(150, 648, 300, 45);
		panel.add(textPhone);
		textPhone.setColumns(10);
		
		textRPW = new JPasswordField();
		textRPW.setBounds(150, 248, 300, 45);
		panel.add(textRPW);
		
		JLabel LabelRPW = new JLabel("비밀번호 재확인");
		LabelRPW.setFont(new Font("a뉴굴림3", Font.PLAIN, 22));
		LabelRPW.setBounds(150, 210, 161, 35);
		panel.add(LabelRPW);
		
		JLabel LabelName = new JLabel("이름");
		LabelName.setFont(new Font("a뉴굴림3", Font.PLAIN, 22));
		LabelName.setBounds(150, 310, 123, 28);
		panel.add(LabelName);
		
		JLabel LabelAddress = new JLabel("주소");
		LabelAddress.setFont(new Font("a뉴굴림3", Font.PLAIN, 22));
		LabelAddress.setBounds(150, 410, 123, 28);
		panel.add(LabelAddress);
		
		JLabel LabelEmail = new JLabel("이메일");
		LabelEmail.setFont(new Font("a뉴굴림3", Font.PLAIN, 22));
		LabelEmail.setBounds(150, 515, 98, 23);
		panel.add(LabelEmail);
		
		JLabel LabelPhone = new JLabel("휴대전화");
		LabelPhone.setFont(new Font("a뉴굴림3", Font.PLAIN, 22));
		LabelPhone.setBounds(150, 615, 98, 23);
		panel.add(LabelPhone);
		
		ButtonSummit = new JButton("가입하기");
		ButtonSummit.setBackground(Color.WHITE);
		ButtonSummit.setFont(new Font("a뉴굴림3", Font.PLAIN, 18));
		ButtonSummit.setBounds(150, 708, 300, 35);
		panel.add(ButtonSummit);
		ButtonSummit.addActionListener(this);
		setVisible(true);
	}
	
	MouseListener m = new MouseAdapter() {
		public void mouseClicked(MouseEvent e)
		{
			if(e.getSource() instanceof JTextField)
			{
				// 주소 검색창 띄우기
				if(addressFindF == null)
				{
					addressFindF = new AddressFindF();
					addressFindF.setVisible(true);
				}
			}
		}
	};
	// 주소 검색에서 받아온 정보 입력
	public static void SetAddress(String temp)
	{
		textAddress.setText(temp);
		textAddress.setEnabled(false);
	}
	
	public void actionPerformed(ActionEvent e) {
		String ID = textID.getText();
		String Name = textName.getText();
		String Address = textAddress.getText();
		String Email = textEmail.getText();
		String Phone = textPhone.getText();
		
		// 가입 버튼
		if(e.getSource() == ButtonSummit)
		{
			char []PW_t = textPW.getPassword();
			String PW = new String(PW_t);
			char []RPW_T = textRPW.getPassword();
			String RPW = new String(RPW_T);
			
			// 각 항목 유효성 체크
			if(ID.equals(""))
			{
				JOptionPane.showMessageDialog(null, "아이디를 입력해 주세요.");
			}
			else if(PW.equals(""))
			{
				JOptionPane.showMessageDialog(null, "비밀번호를 입력해 주세요.");
			}
			else if(RPW.equals(""))
			{
				JOptionPane.showMessageDialog(null, "비밀번호 재확인을 입력해 주세요.");
			}
			else if(Name.equals(""))
			{
				JOptionPane.showMessageDialog(null, "이름을 입력해 주세요.");
			}
			else if(Address.equals(""))
			{
				JOptionPane.showMessageDialog(null, "주소를 입력해 주세요.");
			}
			else if(Email.equals(""))
			{
				JOptionPane.showMessageDialog(null, "이메일을 입력해 주세요.");
			}
			else if (Phone.equals(""))
			{
				JOptionPane.showMessageDialog(null, "핸드폰을 입력해 주세요.");
			}
			// 정규식으로 형식 체크
			else
			{
				if(!Pattern.matches("^[a-zA-Z]{1}[a-zA-Z0-9_]{4,11}$", ID))
				{
					JOptionPane.showMessageDialog(null, "ID 형식이 올바르지 않습니다. (5~12자의 영문 소문자, 숫자와 특수기호(_)만 사용 가능합니다.)");
				}
				else if(!Pattern.matches("^.*(?=^.{8,15}$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$", PW))
				{
					JOptionPane.showMessageDialog(null, "비밀번호 형식이 올바르지 않습니다. (숫자, 문자, 특수문자 포함 8~15자리 이내)");
				}
				else if(!Pattern.matches("^[가-힣]*$", Name))
				{
					JOptionPane.showMessageDialog(null, "이름 형식이 올바르지 않습니다.");
				}
				else if(!Pattern.matches("^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$", Email))
				{
					JOptionPane.showMessageDialog(null, "이메일 형식이 올바르지 않습니다.");
				}
				else if(!Pattern.matches("^\\d{3}-\\d{3,4}-\\d{4}$", Phone))
				{
					JOptionPane.showMessageDialog(null, "전화번호 형식이 올바르지 않습니다. EX) 010-0000-0000");
				}
				else if(textAddress.getText().equals("Click"))
				{
					JOptionPane.showMessageDialog(null, "주소가 올바르지 않습니다.");
				}
				else
				{
					// 패스워드 암호화 부분
					PW = sha256.SHA256(PW);
					RPW = sha256.SHA256(RPW);
					// 패스워드 동일한지 체크
					if(!PW.equals(RPW))
					{
						JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.");
					}
					else
					{
						UserBean db = new UserBean();
						db.setID(ID);
						db.setPW(PW);
						db.setName(Name);
						db.setAddress(Address);
						db.setEmail(Email);
						db.setPhone(Phone);
						Register temp = new Register(); 
						// 중복체크
						if(temp.CheckID(db))
						{
							JOptionPane.showMessageDialog(null, "아이디가 중복입니다.");
						}
						else
						{
							if(temp.CheckInfo(db))
							{
								JOptionPane.showMessageDialog(null, "회원정보가 존재합니다.");
							}
							// 회원가입 성공
							else
							{
								temp.register(db);
								JOptionPane.showMessageDialog(null, "회원가입 완료 !");
								this.dispose();
								MainF.registerF = null;
							}
						}
					}
				}
			}
		}
	}
	public void windowOpened(WindowEvent e) {}
	public void windowClosing(WindowEvent e) {
		this.dispose();
		MainF.registerF = null;
	}
	public void windowClosed(WindowEvent e) {}
	public void windowIconified(WindowEvent e) {}
	public void windowDeiconified(WindowEvent e) {}
	public void windowActivated(WindowEvent e) {}
	public void windowDeactivated(WindowEvent e) {	
	}
}