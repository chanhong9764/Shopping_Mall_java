package sch;
import javax.swing.*;
import java.awt.event.*;
import java.util.regex.Pattern;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color;


public class FindPWF extends JFrame implements ActionListener, WindowListener {
	private JTextField textID;
	private JTextField textEmail;
	private JLabel LabelPW;
	private JLabel LabelRPW;
	private JLabel LabelID;
	private JLabel LabelEmail;
	private JLabel LabelAU;
	private JPasswordField textAU;
	private JPasswordField textPW;
	private JPasswordField textRPW;
	private JButton ButtonAU;
	private JButton ButtonAU2;
	private JButton ButtonPW;
	private boolean t;
	private int AUNumber;
	
	public FindPWF() 
	{
		setIconImage(MainF.Icon_t);
		setSize(300,200);
		setTitle("PW 찾기");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.addWindowListener(this);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setLayout(null);
		getContentPane().add(panel);
		
		LabelID = new JLabel("ID");
		LabelID.setFont(new Font("a뉴굴림3", Font.PLAIN, 12));
		LabelID.setBounds(22, 23, 52, 15);
		panel.add(LabelID);
		
		LabelEmail = new JLabel("이메일");
		LabelEmail.setFont(new Font("a뉴굴림3", Font.PLAIN, 12));
		LabelEmail.setBounds(22, 58, 52, 15);
		panel.add(LabelEmail);
		
		textID = new JTextField();
		textID.setFont(new Font("a뉴굴림1", Font.PLAIN, 12));
		textID.setBounds(77, 15, 186, 25);
		panel.add(textID);
		textID.setColumns(10);
		
		textEmail = new JTextField();
		textEmail.setFont(new Font("a뉴굴림1", Font.PLAIN, 12));
		textEmail.setBounds(77, 50, 186, 25);
		panel.add(textEmail);
		textEmail.setColumns(10);
		
		LabelAU = new JLabel("인증번호");
		LabelAU.setFont(new Font("a뉴굴림3", Font.PLAIN, 12));
		LabelAU.setBounds(22, 93, 52, 15);
		panel.add(LabelAU);
		
		textAU = new JPasswordField();
		textAU.setFont(new Font("굴림", Font.PLAIN, 12));
		textAU.setEnabled(false);
		textAU.setBounds(77, 85, 186, 25);
		panel.add(textAU);
		
		ButtonAU = new JButton("인증번호 받기");
		ButtonAU.setBackground(Color.WHITE);
		ButtonAU.setFont(new Font("a뉴굴림3", Font.PLAIN, 12));
		ButtonAU.setBounds(22, 120, 241, 31);
		panel.add(ButtonAU);
		
		ButtonAU2 = new JButton("인증하기");
		ButtonAU2.setBackground(Color.WHITE);
		ButtonAU2.setFont(new Font("a뉴굴림3", Font.PLAIN, 12));
		ButtonAU2.setBounds(22, 120, 241, 31);
		panel.add(ButtonAU2);
		ButtonAU2.setVisible(false);
		
		LabelPW = new JLabel("PW");
		LabelPW.setFont(new Font("a뉴굴림3", Font.PLAIN, 12));
		LabelPW.setBounds(22, 58, 52, 15);
		panel.add(LabelPW);
		LabelPW.setVisible(false);
		
		textPW = new JPasswordField();
		textPW.setFont(new Font("굴림", Font.PLAIN, 12));
		textPW.setBounds(77, 50, 186, 25);
		panel.add(textPW);
		textPW.setVisible(false);
		
		LabelRPW = new JLabel("PW 확인");
		LabelRPW.setFont(new Font("a뉴굴림3", Font.PLAIN, 12));
		LabelRPW.setBounds(22, 93, 52, 15);
		panel.add(LabelRPW);
		LabelRPW.setVisible(false);
		
		textRPW = new JPasswordField();
		textRPW.setFont(new Font("굴림", Font.PLAIN, 12));
		textRPW.setBounds(77, 85, 186, 25);
		panel.add(textRPW);
		textRPW.setVisible(false);
		
		ButtonPW = new JButton("확인");
		ButtonPW.setBackground(Color.WHITE);
		ButtonPW.setFont(new Font("a뉴굴림3", Font.PLAIN, 12));
		ButtonPW.setBounds(22, 120, 241, 31);
		panel.add(ButtonPW);
		ButtonPW.setVisible(false);
		
		ButtonAU.addActionListener(this);
		ButtonAU2.addActionListener(this);
		ButtonPW.addActionListener(this);
		setVisible(true);
	}
		public void actionPerformed(ActionEvent e) {
			String ID = textID.getText();
			String Email = textEmail.getText();
			char []AU_t = textAU.getPassword();
			String AU = new String(AU_t);
			// 입력 유효성 검사
			if(e.getSource() == ButtonAU)
			{
				if(ID.equals(""))
				{
					JOptionPane.showMessageDialog(null, "ID를 입력해 주세요.");
				}
				else if(Email.equals(""))
				{
					JOptionPane.showMessageDialog(null, "이메일을 입력해 주세요.");
				}
				else
				{
					// 계정 존재 여부 확인
					UserBean db = new UserBean();
					db.setID(ID);
					db.setEmail(Email);
					t = FindPW.findPW(db);

					if(t == false)
					{
						JOptionPane.showMessageDialog(null, "계정이 존재하지 않습니다.");
					}
					else
					{
						textID.setEnabled(false);
						textEmail.setEnabled(false);
						textAU.setEnabled(true);
						ButtonAU.setVisible(false);
						ButtonAU2.setVisible(true);
						// 인증번호 발송
						AUNumber = sendMail.AUSend(Email);
					}
				}
			}
			// 인증 번호 유효성 체크
			else if (e.getSource() == ButtonAU2)
			{
				if(AU.equals(""))
				{
					JOptionPane.showMessageDialog(null, "인증번호를 입력해 주세요.");
				}
				else
				{
					int I_AU = Integer.parseInt(AU);
					{
						if(I_AU == AUNumber)// 인증번호 인증
						{
							textEmail.setVisible(false);
							textAU.setVisible(false);
							LabelEmail.setVisible(false);
							LabelAU.setVisible(false);
							ButtonAU2.setVisible(false);
							ButtonPW.setVisible(true);
							LabelPW.setVisible(true);
							LabelRPW.setVisible(true);
							textPW.setVisible(true);
							textRPW.setVisible(true);
							JOptionPane.showMessageDialog(null, "인증되었습니다 !");
						}
						else
						{
							JOptionPane.showMessageDialog(null, "인증번호가 올바르지 않습니다.");
						}
					}
				}
			}
			// 패스워드 변경 부분
			else
			{
				char []PW_t = textPW.getPassword();
				String PW = new String(PW_t);
				char []RPW_t = textRPW.getPassword();
				String RPW = new String(RPW_t);
				// 유효성 체크
				if(PW.equals(""))
				{
					JOptionPane.showMessageDialog(null, "PW를 입력해 주세요.");
				}
				else if(RPW.equals(""))
				{
					JOptionPane.showMessageDialog(null, "PW 확인을 입력해 주세요.");
				}
				else if (!Pattern.matches("^.*(?=^.{8,15}$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$", PW))
				{
					JOptionPane.showMessageDialog(null, "비밀번호 형식이 올바르지 않습니다. (숫자, 문자, 특수문자 포함 8~15자리 이내)");
				}
				else
				{
					// 암호화 부분
					PW=sha256.SHA256(PW);
					RPW=sha256.SHA256(RPW);
					if(!PW.equals(RPW))
					{
						JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다 !");
					}
					else		
					{		
						FindPW.ChangePW(ID, PW);
						JOptionPane.showMessageDialog(null, "비밀번호가 변경되었습니다 !");
						this.dispose();
						MainF.findPWF = null;
					}
					
				}
			}
		}
		public void windowOpened(WindowEvent e) {}
		public void windowClosing(WindowEvent e) {
			this.dispose();
			MainF.findPWF = null;
		}
		public void windowClosed(WindowEvent e) {}
		public void windowIconified(WindowEvent e) {}
		public void windowDeiconified(WindowEvent e) {}
		public void windowActivated(WindowEvent e) {}
		public void windowDeactivated(WindowEvent e) {	
		}
	}