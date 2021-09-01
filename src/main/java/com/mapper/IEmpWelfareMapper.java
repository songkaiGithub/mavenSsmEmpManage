package com.mapper;

import java.util.List;

import org.springframework.stereotype.Service;

import com.po.Emp;
import com.po.EmpWelfare;
import com.po.Welfare;

@Service("IEmpWelfareMapper")
public interface IEmpWelfareMapper {
	//员工福利添加添加
	public int save(EmpWelfare empWelfare);
	//删除
	public int delById(Integer eid);
	//通过该员工的编号查询该员工的福利
	public List<Welfare> findById(Integer eid);
}
