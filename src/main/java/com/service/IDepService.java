package com.service;

import java.util.List;

import com.po.Dep;
import com.po.PageBean;

public interface IDepService {
	public  List<Dep> findAll();
	//���
	public boolean depsave(Dep dep);
	//�޸�
	public boolean depupdate(Dep dep);
	//��ѯ����
		public Dep  find(Integer depid);
		 public int findmaxrows();
		 public List<Dep> findpage(PageBean pb);

}
