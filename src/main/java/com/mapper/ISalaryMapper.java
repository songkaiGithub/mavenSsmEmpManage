package com.mapper;

import org.springframework.stereotype.Service;

import com.po.Emp;
import com.po.Salary;

@Service("ISalaryMapper")
public interface ISalaryMapper {
	//薪资添加
	public int save(Salary salary);
	//删除
	public int delById(Integer eid);
	//通过员工编号查询该员工的薪资
	public Salary findById(Integer eid);
	//通过员工编号修改该员工的薪资
	public int update(Salary salary);

}
