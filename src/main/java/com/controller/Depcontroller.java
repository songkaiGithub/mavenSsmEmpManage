package com.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.po.Dep;
import com.po.Emp;
import com.po.PageBean;
import com.util.AjAxUtils;
import com.util.BizServiceUtil;

@Controller
public class Depcontroller {
		@Resource(name="BizService")
	  private BizServiceUtil bizService;

		public BizServiceUtil getBizService() {
			return bizService;
		}

		public void setBizService(BizServiceUtil bizService) {
			this.bizService = bizService;
		}
		@RequestMapping(value="depsave_Dep.do")
		public String depsave(HttpServletRequest request,HttpServletResponse response,Dep dep){
			boolean flag=bizService.getDepservice().depsave(dep);
			if(flag){
			  	AjAxUtils.printString(response,"1");
			}else{
				AjAxUtils.printString(response, "0");
			}
			return null;
			}
		@RequestMapping(value="findPageAll_Dep.do")
		public String findAllPage(HttpServletRequest request,HttpServletResponse response,Integer page,Integer rows){
			System.out.println("分页查询。。。。"+page+"/"+rows);
			//因为easeui分页所需的参数是固定的复杂格式
			Map<String,Object> map=new HashMap<String,Object>();
			PageBean pb=new PageBean();	
			page=page==null||page<0?pb.getPage():page;
			rows=rows==null||rows<0?pb.getRows():rows;
			if(rows>20){
				rows=20;
			}
			pb.setPage(page);
			pb.setRows(rows);
			List<Dep> lsemp=bizService.getDepservice().findpage(pb);
			int maxRows=bizService.getDepservice().findmaxrows();
			//拼接前台所需要的数据
			map.put("page", page);
			map.put("rows", lsemp);
			map.put("total", maxRows);
			 PropertyFilter propertyFilter=AjAxUtils.filterProperts("birthday");
			  //将查询结果集合转化为json字符串
			  	String jsonstr=JSONObject.toJSONString(map,propertyFilter,SerializerFeature.DisableCircularReferenceDetect);
			  	System.out.println("json:"+jsonstr);
			  	//将本方法执行结果的json字符串返回到前台
			  	AjAxUtils.printString(response, jsonstr);
			return null;
			}
		//修改查询单个
		@RequestMapping(value="find_dep.do")
		public String find(HttpServletRequest request,HttpServletResponse response,Integer depid){
			Dep olddep=bizService.getDepservice().find(depid);
			 PropertyFilter propertyFilter=AjAxUtils.filterProperts("birthday","pic");
			  //将查询结果集合转化为json字符串
			  	String jsonstr=JSONObject.toJSONString(olddep,propertyFilter,SerializerFeature.DisableCircularReferenceDetect);
			  	System.out.println("json:"+jsonstr);
			  	//将本方法执行结果的json字符串返回到前台
			  	AjAxUtils.printString(response, jsonstr);
			return null;
			}
		//部门修改
		@RequestMapping(value="depupdate_Dep.do")
		public String depupdate(HttpServletRequest request,HttpServletResponse response,Dep dep){
			boolean flag=bizService.getDepservice().depupdate(dep);
			if(flag){
			  	AjAxUtils.printString(response,"1");
			}else{
				AjAxUtils.printString(response, "0");
			}
			return null;
			}
		
	}	
	
		

