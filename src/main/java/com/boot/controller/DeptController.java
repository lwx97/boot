
package com.boot.controller;
 
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
 
import com.boot.model.Dept;
import com.boot.service.DeptService;
 
 
@Controller
@RequestMapping("main")
public class DeptController {
	@Autowired
	@Qualifier("DeptServiceImpl")
	private DeptService deptService;
 
	@RequestMapping("dept")
	@ResponseBody
	public String findAll(HttpServletRequest req) {
//		List<String> list=deptService.findAll();
//		System.out.println("list.size()：               "+list.size());
//		for (String str : list) {
//			System.out.println(str);
//		}
		String string = req.getParameter("test");
		System.out.println(string);
		return "测试数据："+string;
	}
 
}
