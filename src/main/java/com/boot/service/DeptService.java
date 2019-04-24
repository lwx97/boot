/**
 * 
 */
package com.boot.service;
 
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.boot.model.Dept;
 
 
public interface DeptService {
	
	public List<String> findAll();

	public String reqWeibo(HttpServletRequest req) throws IOException, InterruptedException;

	public HashMap<String, Object> weiboQG(HttpServletRequest req) throws InterruptedException;
	
}