package sch;
import javax.swing.*;

import java.awt.Toolkit;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Font;

// ID 찾는 폼
public class FindIDF extends JFrame implements ActionListener, WindowListener {
	private JTextField textName;
	private JTextField textEmail;
	private JPasswordField textAU;
	private JButton ButtonAU;
	private JButton ButtonAU2;
	private resultID t;
	private int AUNumber;
	
	public FindIDF() 
	{
		setIconImage(MainF.Icon_t);
		setSize(300,200);
		setTitle("ID 찾기");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.addWindowListener(this);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setLayout(null);
		getContentPane().add(panel);
		
		JLabel LabelName = new JLabel("이름");
		LabelName.setFont(new Font("a뉴굴림3", Font.PLAIN, 12));
		LabelName.setBounds(22, 23, 52, 15);
		panel.add(LabelName);
		
		JLabel LabelEmail = new JLabel("이메일");
		LabelEmail.setFont(new Font("a뉴굴림3", Font.PLAIN, 12));
		LabelEmail.setBounds(22, 58, 52, 15);
		panel.add(LabelEmail);
		
		textName = new JTextField();
		textName.setFont(new Font("a뉴굴림1", Font.PLAIN, 11));
		textName.setBounds(77, 15, 186, 25);
		panel.add(textName);
		textName.setColumns(10);
		
		textEmail = new JTextField();
		textEmail.setFont(new Font("a뉴굴림1", Font.PLAIN, 11));
		textEmail.setBounds(77, 55, 186, 25);
		panel.add(textEmail);
		textEmail.setColumns(10);
		
		JLabel LabelAU = new JLabel("인증번호");
		LabelAU.setFont(new Font("a뉴굴림3", Font.PLAIN, 12));
		LabelAU.setBounds(22, 93, 52, 15);
		panel.add(LabelAU);
		
		textAU = new JPasswordField();
		textAU.setFont(new Font("굴림", Font.PLAIN, 11));
		textAU.setEnabled(false);
		textAU.setBounds(77, 90, 186, 21);
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
		
		ButtonAU.addActionListener(this);
		ButtonAU2.addActionListener(this);
		setVisible(true);
	}
		public void actionPerformed(ActionEvent e) {
			String Name = textName.getText();
			String Email = textEmail.getText();
			char []AU_t = textAU.getPassword();
			String AU = new String(AU_t);
			
			if(e.getSource() == ButtonAU)
			{
				// 계정 검색 부분
				if(Name.equals(""))
				{
					JOptionPane.showMessageDialog(null, "이름을 입력해주세요.");
				}
				else if(Email.equals(""))
				{
					JOptionPane.showMessageDialog(null, "이메일을 입력해주세요.");
				}
				else
				{
					UserBean db = new UserBean();
					db.setName(Name);
					db.setEmail(Email);
					t = FindID.findID(db);

					if(t.getexistID() == false)
					{
						JOptionPane.showMessageDialog(null, "계정이 존재하지 않습니다.");
					}
					else
					{
						textName.setEnabled(false);
						textEmail.setEnabled(false);
						textAU.setEnabled(true);
						ButtonAU.setVisible(false);
						// 인증번호 발송
						AUNumber = sendMail.AUSend(Email);
					}
				}
			}
			// 인증 번호 처리 부분
			else if (e.getSource() == ButtonAU2)
			{
				if(AU.equals(""))
				{
					JOptionPane.showMessageDialog(null, "인증번호를 입력해주세요.");
				}
				else
				{
					int I_AU = Integer.parseInt(AU);
					{
						if(I_AU == AUNumber)// 인증번호 인증
						{
							JOptionPane.showMessageDialog(null, "고객님의 ID는 "+t.getgetpass()+"입니다.");
							MainF.findIDF = null;
							this.dispose();
						}
						else
						{
							JOptionPane.showMessageDialog(null, "인증번호가 올바르지 않습니다.");
						}
					}
				}
			}
			
		}
		public void windowOpened(WindowEvent e) {}
		public void windowClosing(WindowEvent e) {
			this.dispose();
			MainF.findIDF = null;
		}
		public void windowClosed(WindowEvent e) {}
		public void windowIconified(WindowEvent e) {}
		public void windowDeiconified(WindowEvent e) {}
		public void windowActivated(WindowEvent e) {}
		public void windowDeactivated(WindowEvent e) {	
		}
	}