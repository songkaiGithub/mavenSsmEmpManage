package com.service;

import java.util.List;

import com.po.Dep;
import com.po.PageBean;

public interface IDepService {
	public  List<Dep> findAll();
	//添加
	public boolean depsave(Dep dep);
	//修改
	public boolean depupdate(Dep dep);
	//查询单个
		public Dep  find(Integer depid);
		 public int findmaxrows();
		 public List<Dep> findpage(PageBean pb);

}
