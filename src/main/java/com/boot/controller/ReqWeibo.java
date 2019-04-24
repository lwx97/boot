package com.boot.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boot.service.DeptService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("req")
public class ReqWeibo {
	@Autowired
	@Qualifier("DeptServiceImpl")
	private DeptService deptService;
 
	@RequestMapping("weibo")
	@ResponseBody
	public void reqWeibo(HttpServletRequest req,HttpServletResponse res) {
		String imgurl="";
		try {
			imgurl = deptService.reqWeibo(req);
		} catch (IOException | InterruptedException e1) {
			e1.printStackTrace();
		}
		HashMap<String, String> map = new HashMap<String,String>();
		map.put("imgurl", imgurl);
		JSONObject json = JSONObject.fromObject(map);
		PrintWriter writer = null;
		try {
			res.setCharacterEncoding("UTF-8");
			writer = res.getWriter();
			writer.write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				writer.flush();
				writer.close();
			}
		}
	}
	@RequestMapping("weiboQG")
	@ResponseBody
	public void weiboQG(HttpServletRequest req,HttpServletResponse res) {
		HashMap<String, Object> map = null;
		try {
			map = deptService.weiboQG(req);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		JSONObject json = JSONObject.fromObject(map);
		PrintWriter writer = null;
		try {
			res.setCharacterEncoding("UTF-8");
			writer = res.getWriter();
			writer.write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				writer.flush();
				writer.close();
			}
		}
	}
}
