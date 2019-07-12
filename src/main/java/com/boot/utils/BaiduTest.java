package com.boot.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.json.JSONObject;

import com.baidu.aip.nlp.AipNlp;


public class BaiduTest {
	//设置APPID/AK/SK
    public static final String APP_ID = "16069019";
    public static final String API_KEY = "C0jTGV04RMQTqnFNTKXe1zT7";
    public static final String SECRET_KEY = "DyuzsKaN36Kt29XCfVeYgR5N4vG12721";

    public static void main(String[] args) throws UnsupportedEncodingException {
//        // 初始化一个AipNlp
//        AipNlp client = new AipNlp(APP_ID, API_KEY, SECRET_KEY);
//
//        // 可选：设置网络连接参数
//        client.setConnectionTimeoutInMillis(2000);
//        client.setSocketTimeoutInMillis(60000);
//
//
//        // 调用接口
//        String text = "百度是一家高科技公司";
//        //情感倾向分析
////        JSONObject res = client.sentimentClassify(text, null);
//        //词法分析
//        JSONObject res = client.lexer(text, null);
//        System.out.println(res.toString(2));
    	getURLContent("https://m.weibo.cn/api/comments/show?id=4362895472547300&page=1");
    }
    public static String baidu(String text) {
//    	  初始化一个AipNlp
      AipNlp client = new AipNlp(APP_ID, API_KEY, SECRET_KEY);

      // 可选：设置网络连接参数
      client.setConnectionTimeoutInMillis(2000);
      client.setSocketTimeoutInMillis(60000);


      // 调用接口
//      String text = "百度是一家高科技公司";
      //情感倾向分析
//      JSONObject res = client.sentimentClassify(text, null);
      //词法分析
      JSONObject res = client.sentimentClassify(text, null);
      System.out.println(res.toString());
    	return res.toString();
    }
    
    public static String baidufenci(String text) {
    	 AipNlp client = new AipNlp(APP_ID, API_KEY, SECRET_KEY);

         // 可选：设置网络连接参数
         client.setConnectionTimeoutInMillis(2000);
         client.setSocketTimeoutInMillis(60000);


         // 调用接口
//         String text = "百度是一家高科技公司";
         //情感倾向分析
//         JSONObject res = client.sentimentClassify(text, null);
         //词法分析
         JSONObject res = client.lexer(text, null);
//         System.out.println(res.toString());
         return res.toString();
    }
  //parm：请求的url链接  返回的是json字符串
  	public static void getURLContent(String urlStr) throws UnsupportedEncodingException {
        String param = "{\"url\":\"中文\"}";
        param = URLEncoder.encode(param, "UTF-8");
            PrintWriter out = null;
            BufferedReader in = null;
            String result = "";
            try {
               URL realUrl = new URL(urlStr);
               // 打开和URL之间的连接
               URLConnection conn = realUrl.openConnection();
               // 发送POST请求必须设置如下两行
               conn.setDoOutput(true);
               conn.setDoInput(true);
               // 获取URLConnection对象对应的输出流
               out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(),"UTF-8"));
               // 发送请求参数
               out.print(param);          
               // flush输出流的缓冲
               out.flush();
               // 定义BufferedReader输入流来读取URL的响应
               in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
               String line;
               while ((line = in.readLine()) != null) {
                   result += line;
               }
               //解析json对象
//               JSONObject jsStr = JSONObject.fromObject(result);
//               System.out.println(jsStr.get("firstName"));
               System.out.println(result);
            } catch (Exception e) {
               e.printStackTrace();
            } 
  		
      }
	public static String selectQg(String str) {
		AipNlp client = new AipNlp(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);


        // 调用接口
//        String text = "百度是一家高科技公司";
        //情感倾向分析
        JSONObject res = client.sentimentClassify(str, null);
        return res.toString();
	}  
}
