package com.mapper;

import org.springframework.stereotype.Service;

import com.po.Emp;
import com.po.Salary;

@Service("ISalaryMapper")
public interface ISalaryMapper {
	//н�����
	public int save(Salary salary);
	//ɾ��
	public int delById(Integer eid);
	//ͨ��Ա����Ų�ѯ��Ա����н��
	public Salary findById(Integer eid);
	//ͨ��Ա������޸ĸ�Ա����н��
	public int update(Salary salary);

}
