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
import com.po.Welfare;
import com.util.AjAxUtils;
import com.util.BizServiceUtil;

@Controller
public class EmpController {
	@Resource(name="BizService")
  private BizServiceUtil bizService;

	public BizServiceUtil getBizService() {
		return bizService;
	}

	public void setBizService(BizServiceUtil bizService) {
		this.bizService = bizService;
	}
	@RequestMapping(value="doinit_Emp.do")
	public String doinit(HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> map=new HashMap<String,Object>();
		List<Dep> lsdep=bizService.getDepservice().findAll();
		List<Welfare> lswf=bizService.getWelfareservice().findAll();
		map.put("lsdep", lsdep);
		map.put("lswf", lswf);
		 PropertyFilter propertyFilter=AjAxUtils.filterProperts("birthday","pic");
		  //����ѯ�������ת��Ϊjson�ַ���
		  	String jsonstr=JSONObject.toJSONString(map,propertyFilter,SerializerFeature.DisableCircularReferenceDetect);
		  	System.out.println("json:"+jsonstr);
		  	//��������ִ�н����json�ַ������ص�ǰ̨
		  	AjAxUtils.printString(response, jsonstr);
		return null;
		}
	//Ա����ӡ�
	@RequestMapping(value="save_Emp.do")
	public String save(HttpServletRequest request,HttpServletResponse response,Emp emp){
		System.out.println("��ӷ���...."+emp.toString());  
		//��վ��·��
		String realpath=request.getRealPath("/");
		System.out.println("��վ��·��:"+realpath);
		//��ȡ�ϴ���Ƭ����
		MultipartFile multipartFile=emp.getPic();
		if(multipartFile!=null&&!multipartFile.isEmpty()){
			//��ȡ�ϴ��ļ�����
			String fname=multipartFile.getOriginalFilename();
			//����
			if(fname.lastIndexOf(".")!=-1){
				String ext=fname.substring(fname.lastIndexOf("."));
				if(ext.equalsIgnoreCase(".jpg")){
					String newfname=new Date().getTime()+ext;
					//�����ļ�����ָ���ļ�·��
					File dostFile=new File(realpath+"/uppic/"+newfname);
					try {
						//�ϴ�(�����󴫵ݵ��ļ����ݸ���һ�ݵ��ղ����ɵ��ļ���)
						FileUtils.copyInputStreamToFile(multipartFile.getInputStream(),dostFile);
						emp.setPhoto(newfname);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		boolean flag=bizService.getEmpService().save(emp);
		if(flag){
		  	AjAxUtils.printString(response,"1");
		}else{
			AjAxUtils.printString(response, "0");
		}
		return null;
		}
	//��ҳ��ѯ
	@RequestMapping(value="findAllPage_Emp.do")
	public String findAllPage(HttpServletRequest request,HttpServletResponse response,Integer page,Integer rows){
		System.out.println("��ҳ��ѯ��������"+page+"/"+rows);
		//��Ϊeaseui��ҳ����Ĳ����ǹ̶��ĸ��Ӹ�ʽ
		Map<String,Object> map=new HashMap<String,Object>();
		PageBean pb=new PageBean();	
		page=page==null||page<0?pb.getPage():page;
		rows=rows==null||rows<0?pb.getRows():rows;
		if(rows>20){
			rows=20;
		}
		pb.setPage(page);
		pb.setRows(rows);
		List<Emp> lsemp=bizService.getEmpService().findAllpage(pb);
		int maxRows=bizService.getEmpService().findMaxRows();
		//ƴ��ǰ̨����Ҫ������
		map.put("page", page);
		map.put("rows", lsemp);
		map.put("total", maxRows);
		 PropertyFilter propertyFilter=AjAxUtils.filterProperts("birthday");
		  //����ѯ�������ת��Ϊjson�ַ���
		  	String jsonstr=JSONObject.toJSONString(map,propertyFilter,SerializerFeature.DisableCircularReferenceDetect);
		  	System.out.println("json:"+jsonstr);
		  	//��������ִ�н����json�ַ������ص�ǰ̨
		  	AjAxUtils.printString(response, jsonstr);
		return null;
		}
	//ɾ������
	@RequestMapping(value="delById_Emp.do")
	public String delById(HttpServletRequest request,HttpServletResponse response,Integer eid){
		boolean flag=bizService.getEmpService().delById(eid);
		if(flag){
		  	AjAxUtils.printString(response,"1");
		}else{
			AjAxUtils.printString(response, "0");
		}
		return null;
		}
	//�޸Ĳ�ѯ����
	@RequestMapping(value="findById_Emp.do")
	public String findById(HttpServletRequest request,HttpServletResponse response,Integer eid){
		Emp oldemp=bizService.getEmpService().findById(eid);
		 PropertyFilter propertyFilter=AjAxUtils.filterProperts("birthday","pic");
		  //����ѯ�������ת��Ϊjson�ַ���
		  	String jsonstr=JSONObject.toJSONString(oldemp,propertyFilter,SerializerFeature.DisableCircularReferenceDetect);
		  	System.out.println("json:"+jsonstr);
		  	//��������ִ�н����json�ַ������ص�ǰ̨
		  	AjAxUtils.printString(response, jsonstr);
		return null;
		}
	//�޸�
	@RequestMapping(value="update_Emp.do")
	public String update(HttpServletRequest request,HttpServletResponse response, Emp emp){
		boolean flag=bizService.getEmpService().update(emp);
		if(flag){
		  	AjAxUtils.printString(response,"1");
		}else{
			AjAxUtils.printString(response, "0");
		}
		return null;
		}
	//��ѯ����
		@RequestMapping(value="editById_Emp.do")
		public String editById(HttpServletRequest request,HttpServletResponse response,Integer eid){
			Emp oldemp=bizService.getEmpService().findById(eid);
			 PropertyFilter propertyFilter=AjAxUtils.filterProperts("birthday","pic");
			  //����ѯ�������ת��Ϊjson�ַ���
			  	String jsonstr=JSONObject.toJSONString(oldemp,propertyFilter,SerializerFeature.DisableCircularReferenceDetect);
			  	System.out.println("json:"+jsonstr);
			  	//��������ִ�н����json�ַ������ص�ǰ̨
			  	AjAxUtils.printString(response, jsonstr);
			return null;
			}
		
				
		
}
