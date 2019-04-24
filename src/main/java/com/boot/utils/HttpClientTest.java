package com.boot.utils;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.ws.Response;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class HttpClientTest {
	public static void main(String[] args) {
		String post = testPost("https://m.weibo.cn/profile/info?uid=2194868354");
		
		if(post!=null) {
			System.err.println("----------------------------------------------");
			System.out.println(post);
			JSONObject json = JSONObject.fromObject(post);
			JSONArray jsonArray = json.getJSONObject("data").getJSONArray("statuses");
			for(int i = 0;i<jsonArray.size();i++) {
				JSONObject j = jsonArray.getJSONObject(i);
				String text = (String) j.get("mid");
				System.out.println("!!!!!!!!!!!!!!!!!!");
				System.out.println(text.replaceAll("<[.[^>]]*>",""));
				System.out.println("!!!!!!!!!!!!!!!!!!");
			}
		}
//		getTest();
//		
	}
	public static Set<String> getUrl(String url){
String post = testPost("https://m.weibo.cn/profile/info?uid=2194868354");
		HashSet<String> set = new HashSet<String>();
		if(post!=null) {
			System.err.println("----------------------------------------------");
			System.out.println(post);
			JSONObject json = JSONObject.fromObject(post);
			JSONArray jsonArray = json.getJSONObject("data").getJSONArray("statuses");
			for(int i = 0;i<jsonArray.size();i++) {
				JSONObject j = jsonArray.getJSONObject(i);
				String text = (String) j.get("mid");
				set.add(text);
				System.out.println("!!!!!!!!!!!!!!!!!!");
				System.out.println(text.replaceAll("<[.[^>]]*>",""));
				System.out.println("!!!!!!!!!!!!!!!!!!");
			}
		}
		return set;
	}
	
	public static List<String> weibo(String url) {
		String post = testPost(url);
		ArrayList<String> list = new ArrayList<>();
		if(post!=null) {
			System.err.println("----------------------------------------------");
			System.out.println(post);
			JSONObject json = JSONObject.fromObject(post);
			JSONArray jsonArray = json.getJSONObject("data").getJSONArray("data");
			for(int i = 0;i<jsonArray.size();i++) {
				JSONObject j = jsonArray.getJSONObject(i);
				String text = (String) j.get("text");
				System.out.println("!!!!!!!!!!!!!!!!!!");
				text = text.replaceAll("<[.[^>]]*>","");
				text = text.replace(" ", "");
				System.out.println(text);
				list.add(text);
				System.out.println("!!!!!!!!!!!!!!!!!!");
			}
			try {
			JSONArray jsonArray1 = json.getJSONObject("data").getJSONArray("hot_data");
			for(int i = 0;i<jsonArray1.size();i++) {
				JSONObject j = jsonArray1.getJSONObject(i);
				String text = (String) j.get("text");
				System.out.println("!!!!!!!!!!!!!!!!!!");
				text = text.replaceAll("<[.[^>]]*>","");
				text = text.replace(" ", "");
				System.out.println(text);
				list.add(text);
				System.out.println("!!!!!!!!!!!!!!!!!!");
			
			}
			}catch(Exception e) {
				return list;
			}
		}
		return list;
	}
	@Test
	public static  String testPost(String url){
		//创建httpClient实例
		CloseableHttpClient httpClient = HttpClients.createDefault();
		//创建HTTPPOST
//		String url = "http://192.168.181.71:9006/ci/nexusWarManage/downloadNexusWarPost.do";
//		String url ="https://m.weibo.cn/api/comments/show?id=4362895472547300&page=2";
		HttpPost httpPost = new HttpPost(url);
		httpPost.addHeader("Content-Type", "text/xml; charset=utf-8");
		//创建参数队列
		ArrayList<NameValuePair> list = new ArrayList<NameValuePair>();
//		list.add(new BasicNameValuePair("pip_id","1231"));
//		list.add(new BasicNameValuePair("nexus_groupId","com.zxc"));
//		list.add(new BasicNameValuePair("nexus_artifactId", "ccc"));
//		list.add(new BasicNameValuePair("nexus_version", "v1.0"));
//		list.add(new BasicNameValuePair("nexus_type", "war"));
		
		list.add(new BasicNameValuePair("jsonData","{\"isPublic\":1,\"proName\":\"043c46fca44eb681\",\"users\":[{\"userName\":\"root\",\"userRoleValue\":\"1\"}]}"));
		String post = null;
		UrlEncodedFormEntity entity;
		try {
			entity = new UrlEncodedFormEntity(list,"UTF-8");
			httpPost.setEntity(entity);
			System.out.println("executing request:" + httpPost.getURI());
			CloseableHttpResponse execute = httpClient.execute(httpPost);
			try{
//				httpPost.abort();
//				
//				int i = execute.getStatusLine().getStatusCode();
//				System.out.println(i);
//				if(i==302){
//					Header header = execute.getFirstHeader("location"); // 跳转的目标地址是在 HTTP-HEAD 中的
//		            String newuri = header.getValue(); // 这就是跳转后的地址，再向这个地址发出新申请，以便得到跳转后的信息是啥。
//		            System.out.println(newuri);
//		            System.out.println(i);
//		 
//		            httpPost = new HttpPost(newuri);
//		            httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");
//		 
//		 
//		            httpPost.setEntity(entity);
//		            System.out.println();
//		            execute = httpClient.execute(httpPost);
//		            i = execute.getStatusLine().getStatusCode();
//		            System.out.println("login" + i);
//
//				}
				HttpEntity entity2 = execute.getEntity();
				if(entity2 != null){
					System.out.println("---------------");
//					System.out.println("Post Response content:" + EntityUtils.toString(entity2));
					post =  EntityUtils.toString(entity2);
					System.out.println("Post Response content:"+post);
					System.out.println("________________");
				}
			}finally{
				execute.close();
			}
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return post;
	}
	@Test
	public static  void getTest(){
		//创建HttpClient
		CloseableHttpClient httpClient = HttpClients.createDefault();
		//创建GET
		try{
			HttpGet httpGet = new HttpGet("https://m.weibo.cn/api/comments/show?id=4362895472547300&page=2");
			httpGet.addHeader("Content-Type", "text/xml; charset=utf-8");
			httpGet.addHeader("Host", "m.weibo.cn");
			httpGet.addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64; rv:56.0) Gecko/20100101 Firefox/56.0");
			httpGet.addHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			httpGet.addHeader("Accept-Encoding","gzip, deflate, br");
			httpGet.addHeader("Cookie","	ALF=1558496789; SSOLoginState=1555904790; SCF=Am30IiX3_rDmX0xODFFDZJhHRSnQFwsKjIWl65GpJoSLUT7Txvo0Q1CwL6CFRw_FyLBEWc-HzJHVJZ0S6iP1Zz0.; SUB=_2A25xuUlGDeRhGeNJ6VUU8y3IzjiIHXVTQlcOrDV6PUNbktAKLWjSkW1NS_RcwnbIc-k5KasdamolXlVRq_gSoKTV; SUBP=0033WrSXqPxfM725Ws9jqgMF55529P9D9WWGaaNb_7gO9Klxj71Gzgj65JpX5KMhUgL.Fo-NeoMfe0eXSKB2dJLoI0YLxKqL1hnL1K2LxKBLB.zL12-LxKBLBonL12BLxK-L1K5L1heLxKnL1K2LBo2LxK-LBKML1-eLxKnL1KzL1-et; SUHB=05i6-P4OA84szq; MLOGIN=1; _T_WM=4f2f9eab1d129400165f641d6dd1bac1; XSRF-TOKEN=8a81f6; WEIBOCN_FROM=1110006030; M_WEIBOCN_PARAMS=oid%3D4362895472547300%26luicode%3D20000174%26lfid%3D102803");
			httpGet.addHeader("Connection","keep-alive");
			httpGet.addHeader("Upgrade-Insecure-Requests","1");
			System.out.println("executing request: " + httpGet.getURI());
			CloseableHttpResponse response = httpClient.execute(httpGet);
			try {
				HttpEntity entity = response.getEntity();
				System.out.println("________________________");
				if(entity != null){
					System.out.println("Response content length: " + entity.getContentLength());
					System.out.println("Get Response content: " + EntityUtils.toString(entity));
				}
				System.out.println("_________________________");	
			}finally{
				response.close();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}
