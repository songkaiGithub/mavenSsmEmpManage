package com.util;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mapper.IDepMapper;
import com.mapper.IWelfareMapper;
import com.service.IDepService;
import com.service.IEmpService;
import com.service.IWelfareService;

@Service("BizService")
public class BizServiceUtil {
	@Resource(name="DepServiceImpl")
	private IDepService depservice;
	@Resource(name="WelfareServiceImpl")
	private IWelfareService welfareservice;
	 @Resource(name="EmpServiceImpl")
	  private  IEmpService empService;
	public IDepService getDepservice() {
		return depservice;
	}
	public void setDepservice(IDepService depservice) {
		this.depservice = depservice;
	}
	public IWelfareService getWelfareservice() {
		return welfareservice;
	}
	public void setWelfareservice(IWelfareService welfareservice) {
		this.welfareservice = welfareservice;
	}
	public IEmpService getEmpService() {
		return empService;
	}
	public void setEmpService(IEmpService empService) {
		this.empService = empService;
	}
	
	


}
