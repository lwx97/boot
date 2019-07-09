/**
 * 
 */
package com.boot.serviceImpl;
 
import java.awt.Color;
import java.awt.Dimension;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
import com.boot.dao.DeptDAO;
import com.boot.model.Dept;
import com.boot.service.DeptService;
import com.boot.utils.BaiduTest;
import com.boot.utils.HttpClientTest;
import com.kennycason.kumo.CollisionMode;
import com.kennycason.kumo.WordCloud;
import com.kennycason.kumo.WordFrequency;
import com.kennycason.kumo.bg.CircleBackground;
import com.kennycason.kumo.bg.PixelBoundryBackground;
import com.kennycason.kumo.font.KumoFont;
import com.kennycason.kumo.font.scale.SqrtFontScalar;
import com.kennycason.kumo.nlp.FrequencyAnalyzer;
import com.kennycason.kumo.nlp.tokenizers.ChineseWordTokenizer;
import com.kennycason.kumo.palette.LinearGradientColorPalette;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
 
 
@Service("DeptServiceImpl")
public class DeptServiceImpl implements DeptService{
	
	@Autowired
	private DeptDAO deptdao;
 
	@Override
	public List<String> findAll() {
		return new ArrayList<String>();
	}
	private static String txturl = "D:\\app\\output.txt";
//	private static String txturl = "/root/opt/huangjuan/output.txt";
	private static String imgurl1 = "D:\\app\\mygit\\boot\\src\\main\\webapp\\img\\123.png";
//	private static String imgurl1 = "/root/opt/huangjuan/boot/src/main/webapp/img/123.png";
	@Override
	public String reqWeibo(HttpServletRequest req) throws IOException, InterruptedException {
		File writeName = new File(txturl);
		FileWriter writer = new FileWriter(writeName); 
		PrintWriter pw = new PrintWriter(writer);
		String imgurl = "";
		String url = req.getParameter("weiboUrl");
		Set<String> set = HttpClientTest.getUrl(url);
		for(String s : set) {
			String hu = "https://m.weibo.cn/api/comments/show?id="+s+"&page=1";
			List<String> weibo = HttpClientTest.weibo(hu);
			for(String text:weibo) {
				try {
					String str = BaiduTest.baidufenci(text);
					JSONObject json = JSONObject.fromObject(str);
					JSONArray jsonArray = null;
					try {
						jsonArray = json.getJSONArray("items");
					}catch (Exception e) {
						continue;
					}
					for(int i=0;i<jsonArray.size();i++) {
						JSONObject json1 = jsonArray.getJSONObject(i);
						String ci = (String) json1.get("item");
						//到时候存在数据库中。
						System.out.println(ci);
						pw.println(ci);
					}
					System.out.println("********************");
					System.out.println(str);
					System.out.println("********************");
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			Thread.sleep(200);
		}
		
		pw.flush(); 
		try {
			imgurl = ciyun();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return imgurl;
	}
	public static void writeFile(String text) { 
		try { 
			File writeName = new File("D:\\app\\output.txt"); 
			// 相对路径，如果没有则要建立一个新的output.txt文件 writeName.createNewFile(); 
			// 创建新文件,有同名的文件的话直接覆盖 
			try (
					FileWriter writer = new FileWriter(writeName); 
					BufferedWriter out = new BufferedWriter(writer) 
					) 
			{ 
				out.write("text\r\n"); 
				// \r\n即为换行
				out.flush(); 
				// 把缓存区内容压入文件 
				} 
			} catch (IOException e) { 
				e.printStackTrace(); 
			}
		}
	@Test
	public void t() {
		try {
			ciyun();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static String ciyun() throws IOException {
		String str = "";
		//建立词频分析器，设置词频，以及词语最短长度，此处的参数配置视情况而定即可  
	       FrequencyAnalyzer frequencyAnalyzer = new FrequencyAnalyzer();  
	       frequencyAnalyzer.setWordFrequenciesToReturn(600);  
	       frequencyAnalyzer.setMinWordLength(1);  
	  
	       //引入中文解析器  
	       frequencyAnalyzer.setWordTokenizer(new ChineseWordTokenizer());  
	//指定文本文件路径，生成词频集合  
	       final List<WordFrequency> wordFrequencyList = frequencyAnalyzer.load(txturl);  
	//设置图片分辨率  
	       Dimension dimension = new Dimension(1920,1080);  
	//此处的设置采用内置常量即可，生成词云对象  
	       WordCloud wordCloud = new WordCloud(dimension,CollisionMode.PIXEL_PERFECT);  
	       //设置边界及字体  
	wordCloud.setPadding(2);  
	       java.awt.Font font = new java.awt.Font("STSong-Light", 2, 20);  
	//设置词云显示的三种颜色，越靠前设置表示词频越高的词语的颜色  
	       wordCloud.setColorPalette(new LinearGradientColorPalette(Color.RED, Color.BLUE, Color.GREEN, 30, 30));  
	       wordCloud.setKumoFont(new KumoFont(font));  
	//设置背景色  
	       wordCloud.setBackgroundColor(new Color(255,255,255));  
	//设置背景图片  
//	       wordCloud.setBackground(new PixelBoundryBackground("D:\\app\\timg.jpg"));  
	//设置背景图层为圆形  
	wordCloud.setBackground(new CircleBackground(255));  
	       wordCloud.setFontScalar(new SqrtFontScalar(12, 45));  
	//生成词云  
	       wordCloud.build(wordFrequencyList);    
	       String img = UUID.randomUUID().toString().replace("-", "");
	       wordCloud.writeToFile("C:\\Users\\huangjuan\\Desktop\\boot\\src\\main\\webapp\\img\\"+img+".png");
	       str ="img\\"+img+".png";
	       System.out.println(str);
	       return str;
	}

	@Override
	public HashMap<String, Object> weiboQG(HttpServletRequest req) throws InterruptedException {
		HashMap<String, Object> map = new HashMap<String,Object>();
		String url = req.getParameter("weiboUrl");
		Set<String> set = HttpClientTest.getUrl(url);
		int zheng = 0;
		int fu = 0;
		int zhong = 0;
		for(String sq : set) {
			String hu = "https://m.weibo.cn/api/comments/show?id="+sq+"&page=1";
			List<String> weibo = HttpClientTest.weibo(hu);
			for(String str:weibo) {
				try {
					String s = BaiduTest.selectQg(str);
					JSONObject json = JSONObject.fromObject(s);
					JSONObject j = null;
					try {
						j = JSONObject.fromObject(json.getJSONArray("items").get(0));
					}catch(Exception e) {
						continue;
					}
					//表示情感极性分类结果，0:负向，1:中性，2:正向
					Integer o = (Integer) j.get("sentiment");
					if(o==0) {
						System.out.println(str+":负向");
						fu++;
					}else if(o==1) {
						System.out.println(str+":中性");
						zhong++;
					}else if(o==2) {
						System.out.println(str+":正向");
						zheng++;
					}
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
			}
			Thread.sleep(500);
		}
		map.put("zheng", zheng);
		map.put("fu", fu);
		map.put("zhong", zhong);
		System.out.println("---------------------------执行结束--------------------------------------------------");
		return map;
	}	
	
}