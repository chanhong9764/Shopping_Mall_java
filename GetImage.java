package sch;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
//SSH를 이용하여 연결 후 SFTP를 이용해 서버로부터 파일을 받아 오는 부분
public class GetImage {
	static Session session = null;
	static Channel channel = null;
	static ChannelSftp channelSftp = null;
	// SSH를 연결 부분
	public static void ConSSH()
	{
		JSch jsch = new JSch();
		try {
	        session = jsch.getSession("root", "3.35.26.136", 54806);
	        session.setPassword("1234");

	        java.util.Properties config = new java.util.Properties();
	        config.put("StrictHostKeyChecking", "no");
	        session.setConfig(config);
	        session.connect();

	        channel = session.openChannel("sftp");
	        channel.connect();
	    } catch (JSchException e) {
	        e.printStackTrace();
	    }
	    channelSftp = (ChannelSftp) channel;
	}
	// 서버로 파일 업로드 부분
    public static void upload(File file) {
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            channelSftp.cd("/workspace/shoping/image");
            channelSftp.put(in, file.getName());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    // 서버로부터 파일 다운로드 부분
    public static BufferedImage download(String downloadFileName) {
        InputStream in = null;
        BufferedImage b = null;
        try {
            channelSftp.cd("/workspace/shoping/image");
            in = channelSftp.get(downloadFileName);
        } catch (SftpException e) {
            e.printStackTrace();
        }
        try {
        	b = ImageIO.read(in);
        }
        catch(Exception e) {
        	e.printStackTrace();
        }finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return b;
    }
    // 서버와 SSH 연결 종료
    public static void disconnection() {
        channelSftp.quit();
    }
}
