package com.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.po.Emp;
import com.po.PageBean;

public interface IEmpService {
	public boolean save(Emp emp);
	//分页查询
		public List<Emp> findAllpage(PageBean pb);
		//查询总记录数
		public Integer findMaxRows();
		//删除员工
		public boolean delById(Integer eid);       
		//查询单个
		public Emp findById(Integer eid);
		//修改
	    public boolean update(Emp emp);	

}
