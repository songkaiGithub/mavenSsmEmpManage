package com.util;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mapper.IDepMapper;
import com.mapper.IEmpMapper;
import com.mapper.IEmpWelfareMapper;
import com.mapper.ISalaryMapper;
import com.mapper.IWelfareMapper;

@Service("DaoService")
public class DaoServiceUtil {
	@Resource(name="IDepMapper")
	private IDepMapper depMapper;
	@Resource(name="IWelfareMapper")
	private IWelfareMapper welfareMapper;
	 @Resource(name="IEmpMapper")
	   private IEmpMapper empMapper;
	   @Resource(name="ISalaryMapper")
	   private ISalaryMapper salaryMapper;
	   @Resource(name="IEmpWelfareMapper")
	   private IEmpWelfareMapper empwelfareMapper;
	public IDepMapper getDepMapper() {
		return depMapper;
	}
	public void setDepMapper(IDepMapper depMapper) {
		this.depMapper = depMapper;
	}
	public IWelfareMapper getWelfareMapper() {
		return welfareMapper;
	}
	public void setWelfareMapper(IWelfareMapper welfareMapper) {
		this.welfareMapper = welfareMapper;
	}
	public IEmpMapper getEmpMapper() {
		return empMapper;
	}
	public void setEmpMapper(IEmpMapper empMapper) {
		this.empMapper = empMapper;
	}
	public ISalaryMapper getSalaryMapper() {
		return salaryMapper;
	}
	public void setSalaryMapper(ISalaryMapper salaryMapper) {
		this.salaryMapper = salaryMapper;
	}
	public IEmpWelfareMapper getEmpwelfareMapper() {
		return empwelfareMapper;
	}
	public void setEmpwelfareMapper(IEmpWelfareMapper empwelfareMapper) {
		this.empwelfareMapper = empwelfareMapper;
	}
	


}
