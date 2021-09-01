package com.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.po.Dep;
import com.po.Salary;

@Service("IDepMapper")
public interface IDepMapper {
	//��ѯ
	public  List<Dep> findAll();
	//��Ӱ༶
	public int depsave(Dep dep);
	//�޸�
	public int depupdate(Dep dep);
	//��ѯ����
	public Dep find(Integer depid);
	 public int findmaxrows();
	
	 public List<Dep> findpage(@Param(value = "page") Integer page, @Param(value = "rows") Integer rows); // ��ҳ��ѯ

}
