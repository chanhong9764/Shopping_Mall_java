package sch;

import java.util.Properties;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.AddressException;
import javax.mail.Session;
import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.PasswordAuthentication;
import javax.mail.MessagingException; 

// 인증번호 발송 부분
public class sendMail {
	// 인증번호 생성 부분
    public static String AUNumber_f() {
        java.util.Random generator = new java.util.Random();
        generator.setSeed(System.currentTimeMillis());
        String numStr = ""; //난수가 저장될 변수
        numStr+=Integer.toString(generator.nextInt(9)+1);
        
        for(int i=1;i<6;i++) {
            numStr += Integer.toString(generator.nextInt(10));
        }
        return numStr;
    }
	
	public static int AUSend(String Email) {
	    final String user = "aasd"; // gmail 계정
	    final String password = "1234";   // 패스워드
	    int temp=0;

	    // SMTP 서버 정보를 설정한다.
	    Properties prop = new Properties();
	    prop.put("mail.smtp.host", "smtp.gmail.com"); 
	    prop.put("mail.smtp.port", 465); 
	    prop.put("mail.smtp.auth", "true"); 
	    prop.put("mail.smtp.ssl.enable", "true"); 
	    prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
	    
	    Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
	        protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(user, password);
	        }
	    });

	    try {
	        MimeMessage message = new MimeMessage(session);
	        message.setFrom(new InternetAddress(user));

	        // 수신자메일주소
	        message.addRecipient(Message.RecipientType.TO, new InternetAddress(Email)); 

	        // Subject
	        message.setSubject("SCH 인증번호"); //메일 제목을 입력

	        // Text
	        temp = Integer.parseInt(AUNumber_f());
	        message.setText("인증번호:"+temp);  //메일 내용을 입력

	        // send the message
	        Transport.send(message); //전송
	        
	    } catch (AddressException e) {
	        e.printStackTrace();
	    } catch (MessagingException e) {
	        e.printStackTrace();
	    }
	    return temp;
	}
}