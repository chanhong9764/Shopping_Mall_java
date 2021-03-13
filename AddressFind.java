package sch;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import com.google.gson.*;
import javax.swing.*;

// 주소 api를 이용하여 json 형태로 받은 다음 파싱하여 처리하는 부분
public class AddressFind {
    public static DefaultListModel<String> addressFind(String keyword) {
        BufferedReader in = null;
        String resultType = "json";
        String currentPage = "1";
        String countPerPage = "100";
        String confmKey = "devU01TX0FVVEgyMDIwMTExNTAwNTg0MDExMDQxNTQ=";
        String Keyword = keyword;
        String apiURL = "";
        DefaultListModel<String> list = new DefaultListModel<>();
        
        JsonParser Parser = new JsonParser();
 
        try {
        	apiURL = "http://www.juso.go.kr/addrlink/addrLinkApi.do?currentPage="+currentPage
					+"&countPerPage="+countPerPage+"&keyword="+URLEncoder.encode(Keyword,"UTF-8")
					+"&confmKey="+confmKey+"&resultType="+resultType;
            URL obj = new URL(apiURL); // 호출할 url
            HttpURLConnection con = (HttpURLConnection)obj.openConnection();
 
            con.setRequestMethod("GET");
 
            in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
        
            String line;
            while((line = in.readLine()) != null) { // response를 차례대로 출력
            	JsonObject jsonObj = (JsonObject)Parser.parse(line);
            	String temp = jsonObj.get("results").toString();
            	JsonObject jsonObj2 = (JsonObject)Parser.parse(temp);
            	JsonArray jsonArray = (JsonArray)jsonObj2.get("juso");

                for(int i = 0; i < jsonArray.size(); i++)
                {
                	JsonObject object = (JsonObject)jsonArray.get(i);
                	list.addElement(object.get("roadAddr").getAsString());
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            if(in != null) try { in.close(); } catch(Exception e) { e.printStackTrace(); }
        }
        return list;
    }
}

